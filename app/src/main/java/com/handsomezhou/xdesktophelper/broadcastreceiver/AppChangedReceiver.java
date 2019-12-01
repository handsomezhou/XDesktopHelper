package com.handsomezhou.xdesktophelper.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.service.XDesktopHelperService;

public class AppChangedReceiver extends BroadcastReceiver {
	private static final String TAG="AppChangedReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		/**
		 * update app :ACTION_PACKAGE_REMOVED->ACTION_PACKAGE_ADDED
		 */
		if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
			Log.i(TAG, "ACTION_PACKAGE_ADDED");
			String packageName = intent.getData().getSchemeSpecificPart();
			Log.i(TAG, "packageName "+packageName);
		/*	if(false==AppInfoHelper.getInstance().isAppExist(packageName)){
			    AppInfoHelper.getInstance().add(packageName);
				//AppInfoHelper.getInstance().setAppInfoChanged(true);
			}*/
			
			//XDesktopHelperService.startService(context);
			AppInfoHelper.getInstance().setAppInfoChanged(true);
		} else if (intent.getAction().equals(Intent.ACTION_PACKAGE_CHANGED)) {
			Log.i(TAG, "ACTION_PACKAGE_CHANGED");
			//AppInfoHelper.getInstance().setAppInfoChanged(true);
			//XDesktopHelperService.startService(context);
			AppInfoHelper.getInstance().setAppInfoChanged(true);
		} else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
			AppInfoHelper.getInstance().setAppInfoChanged(true);
			Log.i(TAG, "ACTION_PACKAGE_REMOVED");
			String packageName = intent.getData().getSchemeSpecificPart();
			Log.i(TAG, "packageName "+packageName);		
			//AppInfoHelper.getInstance().remove(packageName);

			//AppInfoHelper.getInstance().setAppInfoChanged(true);
			//XDesktopHelperService.startService(context);
		}
	}
	


}
