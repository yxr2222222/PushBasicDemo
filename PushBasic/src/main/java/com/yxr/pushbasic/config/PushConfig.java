package com.yxr.pushbasic.config;

import androidx.annotation.NonNull;

import com.umeng.message.api.UPushRegisterCallback;
import com.yxr.pushbasic.callback.OnNotifyClickCallback;
import com.yxr.umeng.config.UmengConfig;

public class PushConfig {
    private PushConfig() {
    }

    private UmengConfig umengConfig;

    private UPushRegisterCallback pushRegisterCallback;

    private OnNotifyClickCallback onNotifyClickCallback;

    public UmengConfig getUmengConfig() {
        return umengConfig;
    }

    public UPushRegisterCallback getPushRegisterCallback() {
        return pushRegisterCallback;
    }

    public OnNotifyClickCallback getOnNotifyClickCallback() {
        return onNotifyClickCallback;
    }

    public static class Builder {
        private final PushConfig pushConfig = new PushConfig();

        public Builder setUmengConfog(@NonNull UmengConfig umengConfig) {
            pushConfig.umengConfig = umengConfig;
            return this;
        }

        public Builder setPushRegisterCallback(UPushRegisterCallback pushRegisterCallback) {
            pushConfig.pushRegisterCallback = pushRegisterCallback;
            return this;
        }

        public Builder setOnNotifyClickCallback(OnNotifyClickCallback onNotifyClickCallback) {
            pushConfig.onNotifyClickCallback = onNotifyClickCallback;
            return this;
        }

        public PushConfig build() {
            return pushConfig;
        }
    }
}
