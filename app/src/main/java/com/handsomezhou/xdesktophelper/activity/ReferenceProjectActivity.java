package com.handsomezhou.xdesktophelper.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.ReferenceProjectFragment;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;
import com.xiaomi.mistatistic.sdk.MiStatInterface;

public class ReferenceProjectActivity extends BaseSingleFragmentActivity {
	private static final String TAG = "ReferenceProjectActivity";
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

	public static void launch(Context context){
		ActivityUtil.launch(context, ReferenceProjectActivity.class);
		return;
	}
}
