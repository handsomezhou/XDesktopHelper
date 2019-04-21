package com.handsomezhou.xdesktophelper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;


/**
 * Created by handsomezhou on 2016/11/16.
 */

public class ScanQrCodeFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout{
    private CaptureManager mCaptureManager;
    private DecoratedBarcodeView mDecoratedBarcodeView;
    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCaptureManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCaptureManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCaptureManager.onSaveInstanceState(outState);
    }



    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDecoratedBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }*/
    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.scan_code);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_scan_qr_code,container,false);

        mNavigationBarLayout=(NavigationBarLayout) view.findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.getTitleTv().setText(mTitle);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mDecoratedBarcodeView = (DecoratedBarcodeView)view.findViewById(R.id.zxing_barcode_scanner);

        mCaptureManager = new CaptureManager(getActivity(), mDecoratedBarcodeView);
        //mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
        mCaptureManager.decode();

        return view;
    }

    @Override
    protected void initListener() {

    }

    /*start:OnNavigationBarLayout*/
    @Override
    public void onBack() {
        back();
    }
    /*end:OnNavigationBarLayout*/

    private void back(){
        if(null!=getActivity()){
            getActivity().finish();
        }
    }

}
