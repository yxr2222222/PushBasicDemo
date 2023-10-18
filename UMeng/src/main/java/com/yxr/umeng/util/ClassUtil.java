package com.yxr.umeng.util;

import androidx.annotation.NonNull;

public class ClassUtil {
    private ClassUtil() {
    }

    public static boolean classExists(@NonNull String className) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return cls != null;
    }

    public static <T> T reflexClass(@NonNull String className) {
        try {
            Class<?> cls = Class.forName(className);
            return (T) cls.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
