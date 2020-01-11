package com.handsomezhou.xdesktophelper.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;

public class AppChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "AppChangedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"AppChangedReceiver :"+intent.getAction());
        /**
         * update app :ACTION_PACKAGE_REMOVED->ACTION_PACKAGE_ADDED
         */
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED) || intent.getAction().equals(Intent.ACTION_PACKAGE_CHANGED) || intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED) || intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String schemeSpecificPart = null;
            if(null!=intent.getData()) {
                schemeSpecificPart=intent.getData().getSchemeSpecificPart();

            }

            Log.i(TAG, "action["+intent.getAction()+"]["+schemeSpecificPart+"]");

            AppInfoHelper.getInstance().setAppInfoChanged(true);
        }

    }


}
