package com.handsomezhou.xdesktophelper.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.activity.QrCodeParseActivity;
import com.handsomezhou.xdesktophelper.activity.ScanQrCodeActivity;
import com.handsomezhou.xdesktophelper.model.QrCodeContentParameter;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.handsomezhou.xdesktophelper.util.LogUtil;
import com.handsomezhou.xdesktophelper.util.ToastUtil;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by handsomezhou on 2019/4/21.
 */

public class ToolFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout {
    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;
    private View mScanCodeLayout;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(IntentIntegrator.REQUEST_CODE==requestCode){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            // This is important, otherwise the result will not be passed to the fragment
            if (result != null) {
                if (CommonUtil.isEmpty(result.getContents())) {
                    Toast.makeText(getContext(), R.string.cancel_scan, Toast.LENGTH_LONG).show();
                } else {
                    LogUtil.i(TAG,"result.getContents():["+result.getContents()+"]");
                    ToastUtil.toastLengthLong(getContext(),result.getContents());
                    QrCodeParseActivity.launch(getActivity(), new QrCodeContentParameter(result.getContents()));

                }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);

        }

    }

    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.tool);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_tool, container, false);
        mNavigationBarLayout = (NavigationBarLayout) view.findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mNavigationBarLayout.setTitle(mTitle);

        mScanCodeLayout=view.findViewById(R.id.scan_code_layout);
        return view;
    }

    @Override
    protected void initListener() {
        mScanCodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanQrCodeActivity.launch(getActivity());
            }
        });
    }

    /**
     * start:  NavigationBarLayout.OnNavigationBarLayout
     */
    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /**
     * end:  NavigationBarLayout.OnNavigationBarLayout
     */
}
