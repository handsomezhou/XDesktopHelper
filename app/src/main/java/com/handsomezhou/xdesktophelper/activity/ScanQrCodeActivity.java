package com.handsomezhou.xdesktophelper.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.handsomezhou.xdesktophelper.fragment.ScanQrCodeFragment;


/**
 * Created by handsomezhou on 2016/11/16.
 */

public class ScanQrCodeActivity extends BaseSingleFragmentActivity {
    private ScanQrCodeFragment mScanQrCodeFragment;

  /*  @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/

    @Override
    protected Fragment createFragment() {

        return mScanQrCodeFragment=new ScanQrCodeFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }

    public static void launch(Activity activity) {
        do{
            if(null==activity){
                break;
            }

            new IntentIntegrator(activity).setOrientationLocked(false).setCaptureActivity(ScanQrCodeActivity.class).initiateScan();
        }while (false);

        return;
    }



}
