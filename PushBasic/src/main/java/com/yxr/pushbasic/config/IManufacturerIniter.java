package com.yxr.pushbasic.config;

import android.app.Application;

import androidx.annotation.NonNull;

/**
 * 厂商推送配置
 */
public interface IManufacturerIniter {

    Manufacturer getManufacturer();

    void init(@NonNull Application application);
}
