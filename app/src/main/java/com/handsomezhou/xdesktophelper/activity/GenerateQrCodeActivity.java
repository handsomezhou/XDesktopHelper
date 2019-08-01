package com.handsomezhou.xdesktophelper.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.GenerateQrCodeFragment;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;

/**
 * Created by handsomezhou on 2019/8/1.
 */

public class GenerateQrCodeActivity extends BaseSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GenerateQrCodeFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }

    public static void launch(Context context){
        ActivityUtil.launch(context, GenerateQrCodeActivity.class);
        return;
    }
}
