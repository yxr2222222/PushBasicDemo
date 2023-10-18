package com.yxr.umeng.config;

import androidx.annotation.NonNull;

public class UmengConfig {
    public UmengConfig() {
    }

    private boolean debug;
    private String appKey;
    private String channel;
    private String messageSecret;

    public boolean isDebug() {
        return debug;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessageSecret() {
        return messageSecret;
    }

    @NonNull
    @Override
    public String toString() {
        return "{debug: " + debug + ", " +
                "appKey: " + appKey + ", " +
                "channel: " + channel + ", " +
                "messageSecret: " + messageSecret + "}";
    }

    public static class Builder {
        private final UmengConfig umengConfig = new UmengConfig();

        public Builder setDebug(boolean debug) {
            umengConfig.debug = debug;
            return this;
        }

        public <T extends  Builder> T setAppKey(String appKey) {
            umengConfig.appKey = appKey;
            return (T) this;
        }

        public Builder setChannel(String channel) {
            umengConfig.channel = channel;
            return this;
        }

        public Builder setMessageSecret(String messageSecret) {
            umengConfig.messageSecret = messageSecret;
            return this;
        }

        public UmengConfig build() {
            return umengConfig;
        }
    }
}
