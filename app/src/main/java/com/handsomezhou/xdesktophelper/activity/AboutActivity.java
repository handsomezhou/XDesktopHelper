package com.handsomezhou.xdesktophelper.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.AboutFragment;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;


public class AboutActivity extends BaseSingleFragmentActivity {
	private static final String TAG = "AboutActivity";
	@Override
	protected Fragment createFragment() {

		return new AboutFragment();
	}

	@Override
	protected boolean isRealTimeLoadFragment() {

		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();


	}

	public static void launch(Context context){
		ActivityUtil.launch(context, AboutActivity.class);
		return;
	}
}
