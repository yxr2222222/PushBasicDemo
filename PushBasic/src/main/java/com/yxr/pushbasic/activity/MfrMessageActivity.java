package com.yxr.pushbasic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.message.UmengNotifyClick;
import com.umeng.message.entity.UMessage;
import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;

public class MfrMessageActivity extends Activity {

    private final UmengNotifyClick mNotificationClick = new UmengNotifyClick() {
        @Override
        public void onMessage(UMessage msg) {
            PushConfig pushConfig = PushManager.getInstance().getPushConfig();

            if (pushConfig != null && pushConfig.getOnNotifyClickCallback() != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pushConfig.getOnNotifyClickCallback().onNotifyClick(msg);
                        finish();
                    }
                });
            } else {
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mNotificationClick.onCreate(this, getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mNotificationClick.onNewIntent(intent);
    }
}