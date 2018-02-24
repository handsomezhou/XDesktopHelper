package com.handsomezhou.xdesktophelper.activity;

import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.SettingsFragment;
import com.xiaomi.mistatistic.sdk.MiStatInterface;

public class SettingsActivity extends BaseSingleFragmentActivity {
	private static final String TAG = "SettingsActivity";

	@Override
	protected Fragment createFragment() {
		
		return new SettingsFragment();
	}

	@Override
	protected boolean isRealTimeLoadFragment() {
		
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		/**
		 * start:小米统计
		 */
		MiStatInterface.recordPageStart(this, TAG);

	}

	@Override
	protected void onPause() {
		super.onPause();
		/**
		 * end:小米统计
		 */
		MiStatInterface.recordPageEnd();
	}

}
