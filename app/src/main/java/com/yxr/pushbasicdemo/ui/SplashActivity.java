package com.yxr.pushbasicdemo.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasicdemo.R;
import com.yxr.umeng.UmengManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 如果只集成了友盟基础服务，则只需要进行基础服务预初始化
//        UmengManager.getInstance().init();


        // 如果集成了友盟推送服务，则需要进行推送服务预初始化（内部会初始化友盟基础服务）
        PushManager.getInstance().init();
    }
}
