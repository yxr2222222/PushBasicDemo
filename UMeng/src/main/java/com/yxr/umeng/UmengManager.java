package com.yxr.umeng;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.yxr.umeng.config.UmengConfig;
import com.yxr.umeng.exception.UmengException;

import java.util.Map;

public class UmengManager {
    private static final String TAG = "UmengManager";
    private Application application;
    private UmengConfig umengConfig;
    private boolean preInited = false;
    private boolean inited = false;

    private UmengManager() {
    }

    public static UmengManager getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 预初始化，同意隐私前可直接调用
     */
    public void preInit(@NonNull Application application, @NonNull UmengConfig umengConfig) {
        if (!preInited) {
            if (umengConfig.getAppKey() == null) {
                throw new UmengException("AppKey 不能为空");
            }

            this.preInited = true;
            this.application = application;
            this.umengConfig = umengConfig;

            UMConfigure.setLogEnabled(umengConfig.isDebug());
            UMConfigure.preInit(application, umengConfig.getAppKey(), umengConfig.getChannel());

        }
    }

    /**
     * 初始化，请务必在同意隐私政策之后调用
     */
    public void init() {
        if (umengConfig == null) {
            throw new UmengException("请先preInit");
        }

        if (!inited) {
            this.inited = true;

            UMConfigure.init(application
                    , umengConfig.getAppKey()
                    , umengConfig.getChannel()
                    , UMConfigure.DEVICE_TYPE_PHONE
                    , umengConfig.getMessageSecret());
        }
    }

    /**
     * 埋点
     *
     * @param eventID 事件ID
     * @param map     自定义键值对
     */
    public void track(@NonNull String eventID, @Nullable Map<String, Object> map) {
        if (application != null) {
            MobclickAgent.onEventObject(application, eventID, map);
        }
    }

    public UmengConfig getUmengConfig() {
        return umengConfig;
    }

    public void log(String msg) {
        log(TAG, msg);
    }

    public void log(String tag, String msg) {
        if (umengConfig == null || umengConfig.isDebug()) {
            Log.d(tag, msg);
        }
    }

    private static final class InstanceHolder {
        static final UmengManager instance = new UmengManager();
    }
}
