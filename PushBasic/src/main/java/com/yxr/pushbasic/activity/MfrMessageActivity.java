package com.yxr.pushbasic.activity;

import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;
import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;

public class MfrMessageActivity extends UmengNotifyClickActivity {

    @Override
    protected void onMessage(UMessage msg) {
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
}