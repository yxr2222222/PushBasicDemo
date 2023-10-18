package com.yxr.push.manufacturer.vivo;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.vivo.VivoRegister;

public class ManufacturerIniter implements IManufacturerIniter {
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.VIVO;
    }

    @Override
    public void init(@NonNull Application application) {
        String vivoAppid = PackageUtil.getMetaData(application, "com.vivo.push.app_id");
        String vivoAppkey = PackageUtil.getMetaData(application, "com.vivo.push.api_key");

        VivoRegister.register(application);

        UmengManager.getInstance().log("init vivoAppid: " + vivoAppid + ", vivoAppkey: " + vivoAppkey);
    }
}
