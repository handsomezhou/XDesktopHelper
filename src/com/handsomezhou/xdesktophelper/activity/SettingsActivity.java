package com.handsomezhou.xdesktophelper.activity;

import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.SettingsFragment;

public class SettingsActivity extends BaseSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		
		return new SettingsFragment();
	}

	@Override
	protected boolean isRealTimeLoadFragment() {
		
		return false;
	}

}
