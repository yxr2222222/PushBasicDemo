package com.yxr.pushbasic.callback;

import androidx.annotation.Nullable;

import com.umeng.message.entity.UMessage;

import java.util.Map;

public interface OnNotifyClickCallback {
    void onNotifyClick(@Nullable UMessage msg);
}
