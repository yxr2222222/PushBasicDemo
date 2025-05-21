package com.yxr.pushbasic.activity;

import android.os.Bundle;

import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;
import com.yxr.pushbasic.PushManager;
import com.yxr.pushbasic.config.PushConfig;

import java.util.HashMap;
import java.util.Map;

public class MfrMessageActivity extends UmengNotifyClickActivity {

    @Override
    protected void onMessage(UMessage msg) {
        PushConfig pushConfig = PushManager.getInstance().getPushConfig();

        if (pushConfig != null && pushConfig.getOnNotifyClickCallback() != null) {
            Map<String, String> extraMap = null;
            if (msg != null) extraMap = msg.getExtra();
            if (extraMap == null || extraMap.isEmpty()) extraMap = new HashMap<>();

            if (extraMap.isEmpty()) {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    for (String key : extras.keySet()) {
                        Object value = extras.get(key);
                        extraMap.put(key, value == null ? null : String.valueOf(value));
                    }
                }
            }

            Map<String, String> finalExtraMap = extraMap;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pushConfig.getOnNotifyClickCallback().onNotifyClick(finalExtraMap);
                    finish();
                }
            });
        } else {
            finish();
        }
    }
}