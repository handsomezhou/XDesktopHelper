package com.handsomezhou.xdesktophelper.activity;

import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.ReferenceProjectFragment;

public class ReferenceProjectActivity extends BaseSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ReferenceProjectFragment();
	}

	@Override
	protected boolean isRealTimeLoadFragment() {
		// TODO Auto-generated method stub
		return false;
	}

}
