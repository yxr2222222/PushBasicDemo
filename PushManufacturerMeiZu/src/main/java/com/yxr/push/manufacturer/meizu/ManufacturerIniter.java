package com.yxr.push.manufacturer.meizu;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.mezu.MeizuRegister;

public class ManufacturerIniter implements IManufacturerIniter {

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.MEIZU;
    }

    @Override
    public void init(@NonNull Application application) {
        String meizuAppid = PackageUtil.getMetaData(application, "meizu_appid");
        String meizuAppkey = PackageUtil.getMetaData(application, "meizu_appkey");

        MeizuRegister.register(application, meizuAppid, meizuAppkey);

        UmengManager.getInstance().log("init meizuAppid: " + meizuAppid + ", meizuAppkey: " + meizuAppkey);
    }
}
