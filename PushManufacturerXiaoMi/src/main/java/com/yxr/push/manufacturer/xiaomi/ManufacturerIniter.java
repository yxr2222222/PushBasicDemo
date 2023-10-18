package com.yxr.push.manufacturer.xiaomi;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.pushbasic.config.PushConfig;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.xiaomi.MiPushRegistar;

public class ManufacturerIniter implements IManufacturerIniter {
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.XIAOMI;
    }

    @Override
    public void init(@NonNull Application application) {
        String xiaomiId = PackageUtil.getMetaData(application, "xiaomi_id");
        String xiaomiKey = PackageUtil.getMetaData(application, "xiaomi_key");

        PushConfig pushConfig = PushManager.getInstance().getPushConfig();
        boolean isDebug = pushConfig != null && pushConfig.getUmengConfig() != null && pushConfig.getUmengConfig().isDebug();
        MiPushRegistar.register(application, xiaomiId, xiaomiKey, isDebug);

        UmengManager.getInstance().log("init xiaomiId: " + xiaomiId + ", xiaomiKey: " + xiaomiKey);
    }
}
