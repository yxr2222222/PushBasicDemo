package com.yxr.pushbasic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.umeng.message.UmengNotifyClick;
import com.umeng.message.entity.UMessage;
import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;
import com.yxr.umeng.UmengManager;

public class MfrMessageActivity extends Activity {

    private final UmengNotifyClick mNotificationClick = new UmengNotifyClick() {
        @Override
        public void onMessage(UMessage msg) {
            PushConfig pushConfig = PushManager.getInstance().getPushConfig();
            final String body = msg == null || msg.getRaw() == null ? null : msg.getRaw().toString();
            UmengManager.getInstance().log("UmengNotifyClick body: " + body);

            if (pushConfig != null && pushConfig.getOnNotifyClickCallback() != null && !TextUtils.isEmpty(body)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pushConfig.getOnNotifyClickCallback().onNotifyClick(body);
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