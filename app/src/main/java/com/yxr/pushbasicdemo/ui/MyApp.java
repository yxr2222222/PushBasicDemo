package com.yxr.pushbasicdemo.ui;

import android.app.Application;

import androidx.annotation.NonNull;

import com.umeng.message.api.UPushRegisterCallback;
import com.yxr.pushbasic.callback.OnNotifyClickCallback;
import com.yxr.pushbasicdemo.BuildConfig;

import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;
import com.yxr.umeng.UmengManager;
import com.yxr.umeng.config.UmengConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PushManager.getInstance().preInit(this, new PushConfig.Builder()
                .setUmengConfog(new UmengConfig.Builder()
                        .setAppKey("友盟AppKey")
                        .setChannel("友盟渠道")
                        .setMessageSecret("友盟MessageSecret")
                        .setDebug(BuildConfig.DEBUG)
                        .build())
                .setPushRegisterCallback(new UPushRegisterCallback() {
                    @Override
                    public void onSuccess(String deviceToken) {
                        UmengManager.getInstance().log("onSuccess deviceToken: " + deviceToken);
                    }

                    @Override
                    public void onFailure(String errCode, String errDesc) {
                        UmengManager.getInstance().log("onFailure errCode: " + errCode + ", errDesc: " + errDesc);
                    }
                })
                .setOnNotifyClickCallback(new OnNotifyClickCallback() {
                    @Override
                    public void onNotifyClick(@NonNull String body) {
                        UmengManager.getInstance().log("onNotifyClick body: " + body);
                    }
                })
                .build());
    }
}
