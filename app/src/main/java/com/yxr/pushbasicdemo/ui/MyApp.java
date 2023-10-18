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

        // 如果只集成了友盟基础服务，则只需要进行基础服务预初始化
//        UmengManager.getInstance().preInit(this, new UmengConfig.Builder()
//                .setAppKey("友盟AppKey")
//                .setChannel("友盟渠道")
//                .setMessageSecret("友盟MessageSecret")
//                .setDebug(BuildConfig.DEBUG)
//                .build());


        // 如果集成了友盟推送服务，则需要进行推送服务预初始化（内部会初始化友盟基础服务）
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
