package com.yxr.pushbasic;

import android.app.Application;

import androidx.annotation.NonNull;

import com.umeng.message.PushAgent;
import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.pushbasic.config.PushConfig;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.exception.UmengException;
import com.yxr.umeng.util.ClassUtil;

public class PushManager {
    private Application application;
    private PushConfig pushConfig;
    private boolean preInited = false;
    private boolean inited = false;

    private PushManager() {
    }

    public static PushManager getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 预初始化，同意隐私前可直接调用
     */
    public void preInit(@NonNull Application application, @NonNull PushConfig pushConfig) {
        if (!preInited) {
            if (pushConfig.getUmengConfig() == null) {
                throw new UmengException("UmengConfig 不能为空");
            }
            this.preInited = true;
            this.application = application;
            this.pushConfig = pushConfig;
            UmengManager.getInstance().preInit(application, pushConfig.getUmengConfig());
        }
    }

    /**
     * 初始化，请务必在同意隐私政策之后调用
     */
    public void init() {
        if (pushConfig == null) {
            throw new UmengException("请先preInit");
        }

        if (!inited) {
            this.inited = true;

            UmengManager.getInstance().init();

            if (pushConfig.getPushRegisterCallback() != null
                    && ClassUtil.classExists("com.umeng.message.PushAgent")) {
                // 注册推送
                PushAgent.getInstance(application).register(pushConfig.getPushRegisterCallback());

                // 应用活跃统计
                PushAgent.getInstance(application).onAppStart();

                // 遍历反射初始化厂商渠道

            }

            for (Manufacturer manufacturer : Manufacturer.values()) {
                String className = "com.yxr.push.manufacturer." +
                        manufacturer.getManufacturer() +
                        ".ManufacturerIniter";

                // 反射获取厂商渠道初始化器
                IManufacturerIniter initer = ClassUtil.reflexClass(className);
                if (initer == null) {
                    UmengManager.getInstance().log(manufacturer.getManufacturer() + " 的 ManufacturerIniter 不存在");
                    continue;
                }

                try {
                    initer.init(application);
                } catch (Throwable e) {
                    UmengManager.getInstance().log(manufacturer.getManufacturer() + "初始化失败: " + e.getMessage());
                }
            }
        }
    }

    public PushConfig getPushConfig() {
        return pushConfig;
    }

    private static final class InstanceHolder {
        static final PushManager instance = new PushManager();
    }
}
