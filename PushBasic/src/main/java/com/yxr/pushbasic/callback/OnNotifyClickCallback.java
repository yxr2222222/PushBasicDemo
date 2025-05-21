package com.yxr.pushbasic.callback;

import androidx.annotation.NonNull;

import com.umeng.message.entity.UMessage;

import java.util.Map;

public interface OnNotifyClickCallback {
    void onNotifyClick(@NonNull Map<String, String> extraMap);
}
