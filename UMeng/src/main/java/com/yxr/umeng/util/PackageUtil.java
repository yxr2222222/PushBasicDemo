package com.yxr.umeng.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

public class PackageUtil {
    private PackageUtil() {
    }

    public static String getMetaData(@NonNull Context context, @NonNull String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object value = appInfo.metaData.get(key);
            return value == null ? null : String.valueOf(value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
