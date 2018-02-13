package com.handsomezhou.xdesktophelper.util;

import android.util.Log;

public class LogUtil {
    private static boolean LOG = true;
    private static boolean LOGV = true;
    private static boolean LOGD = true;
    private static boolean LOGI = true;
    private static boolean LOGW = true;
    private static boolean LOGE = true;

    public static void v(String tag, String mess) {
        if (LOG && LOGV) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (LOG && LOGD) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (LOG && LOGI) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (LOG && LOGW) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (LOG && LOGE) {
            Log.e(tag, mess);
        }
    }
}
