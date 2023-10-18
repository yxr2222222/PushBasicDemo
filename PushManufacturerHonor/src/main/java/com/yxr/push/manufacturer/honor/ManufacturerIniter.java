package com.yxr.push.manufacturer.honor;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.honor.HonorRegister;

public class ManufacturerIniter implements IManufacturerIniter {
    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.HONOR;
    }

    @Override
    public void init(@NonNull Application application) {
        String honorAppid = PackageUtil.getMetaData(application, "com.hihonor.push.app_id");

        HonorRegister.register(application);

        UmengManager.getInstance().log("init honorAppid: " + honorAppid);
    }
}
