package com.yxr.push.manufacturer.oppo;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.oppo.OppoRegister;

public class ManufacturerIniter implements IManufacturerIniter {
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.OPPO;
    }

    @Override
    public void init(@NonNull Application application) {
        String oppoAppkey = PackageUtil.getMetaData(application, "oppo_appkey");
        String oppoAppsecret = PackageUtil.getMetaData(application, "oppo_appsecret");

        OppoRegister.register(application, oppoAppkey, oppoAppsecret);

        UmengManager.getInstance().log("init oppoAppkey: " + oppoAppkey + ", oppoAppsecret: " + oppoAppsecret);
    }
}
