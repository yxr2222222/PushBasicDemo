package com.yxr.pushbasic.callback;

import androidx.annotation.Nullable;

import java.util.Map;

public interface OnNotifyClickCallback {
    void onNotifyClick(@Nullable Map<String, String> extra);
}
