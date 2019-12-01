package com.handsomezhou.xdesktophelper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.ToolFragment;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;

/**
 * Created by handsomezhou on 2019/4/21.
 */

public class ToolActivity extends BaseSingleFragmentActivity {
    private static final String TAG = "ToolActivity";
    private ToolFragment mToolFragment;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(null!=mToolFragment){
            mToolFragment.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected Fragment createFragment() {
        return mToolFragment=new ToolFragment();
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
        ActivityUtil.launch(context, ToolActivity.class);
        return;
    }
}
