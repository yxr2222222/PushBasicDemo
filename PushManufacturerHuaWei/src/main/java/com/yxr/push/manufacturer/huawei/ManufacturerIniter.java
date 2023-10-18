package com.yxr.push.manufacturer.huawei;

import android.app.Application;

import androidx.annotation.NonNull;

import com.yxr.pushbasic.config.IManufacturerIniter;
import com.yxr.pushbasic.config.Manufacturer;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.util.PackageUtil;

import org.android.agoo.huawei.HuaWeiRegister;

public class ManufacturerIniter implements IManufacturerIniter {

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.HUAWEI;
    }

    @Override
    public void init(@NonNull Application application) {
        HuaWeiRegister.register(application);

        String appid = PackageUtil.getMetaData(application, "com.huawei.hms.client.appid");
        UmengManager.getInstance().log("init huaweiAppid: " + appid);
    }
}
