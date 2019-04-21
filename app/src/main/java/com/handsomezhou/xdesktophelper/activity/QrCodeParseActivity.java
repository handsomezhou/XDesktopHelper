package com.handsomezhou.xdesktophelper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.handsomezhou.xdesktophelper.fragment.QrCodeParseFragment;
import com.handsomezhou.xdesktophelper.model.QrCodeContentParameter;


/**
 * Created by handsomezhou.
 */

public class QrCodeParseActivity extends BaseSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        QrCodeContentParameter qrCodeContentParameter=(QrCodeContentParameter) getIntent().getSerializableExtra(QrCodeParseFragment.EXTRA_QR_CODE_CONTENT_PARAMETER);

        return  QrCodeParseFragment.newInstance(qrCodeContentParameter);
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }

    public static void launch(Activity activity, QrCodeContentParameter qrCodeContentParameter){
        if(null==activity||null==qrCodeContentParameter){
            return;
        }

        Intent intent=new Intent(activity, QrCodeParseActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(QrCodeParseFragment.EXTRA_QR_CODE_CONTENT_PARAMETER, qrCodeContentParameter);
        intent.putExtras(bundle);

        activity.startActivity(intent);

        return;
    }
}
