
package com.handsomezhou.xdesktophelper.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by handsomezhou.
 */

public class ActivityUtil {
    public static void launch(Context context, Class<?> cls) {
        do {
            if ((null == context) || (null == cls)) {
                break;
            }

            Intent intent = new Intent(context, cls);
            context.startActivity(intent);
        } while (false);

        return;
    }

    /**
     * start activity slowly
     * @param context
     * @param cls
     * @param flags for example: Intent.FLAG_ACTIVITY_NEW_TASK
     */
   /* public static void launch(Context context, Class<?> cls, int flags) {
        do {
            if ((null == context) || (null == cls)) {
                break;
            }

            Intent intent = new Intent(context, cls);
            intent.addFlags(flags);
            context.startActivity(intent);
        } while (false);

        return;
    }*/


    /**
     * start activity quickly
     * @param context
     * @param cls
     * @param flags
     */
    public static void launch(Context context, Class<?> cls, int flags) {
        do {
            if ((null == context) || (null == cls)) {
                break;
            }

            Intent intent = new Intent(context, cls);
            intent.addFlags(flags);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }

        } while (false);

        return;
    }

    public static void launch(Context context, Class<?> cls, String key, Serializable value) {
        do {
            if ((null == context) || (null == cls) || (TextUtils.isEmpty(key)) || (null == value)) {
                break;
            }

            Intent intent = new Intent(context, cls);
            Bundle bundle = new Bundle();
            bundle.putSerializable(key, value);

            intent.putExtras(bundle);

            context.startActivity(intent);
        } while (false);

        return;
    }

    public static void back(Activity activity){
        if(null!=activity){
            activity.finish();
        }
    }
}
