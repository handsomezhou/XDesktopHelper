package com.handsomezhou.xdesktophelper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.model.QrCodeContentParameter;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.handsomezhou.xdesktophelper.util.ShareUtil;
import com.handsomezhou.xdesktophelper.util.ToastUtil;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;


/**
 * Created by handsomezhou.
 */

public class QrCodeParseFragment extends BaseFragment implements View.OnClickListener, NavigationBarLayout.OnNavigationBarLayout{
    public static final String EXTRA_QR_CODE_CONTENT_PARAMETER = "QrCodeParseFragment.EXTRA_QR_CODE_CONTENT_PARAMETER";
    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;

    private QrCodeContentParameter mQrCodeContentParameter;
    private TextView mQrCodeContentTv;
    private TextView mCopyTv;
    private TextView mShareTv;

    public static QrCodeParseFragment newInstance( QrCodeContentParameter qrCodeContentParameter) {
        Bundle bundle = new Bundle();
        if (null != qrCodeContentParameter) {
            bundle.putSerializable(EXTRA_QR_CODE_CONTENT_PARAMETER,
                    qrCodeContentParameter);
        } else {
            bundle.putSerializable(EXTRA_QR_CODE_CONTENT_PARAMETER,
                    new QrCodeContentParameter());
        }

        QrCodeParseFragment fragment = new QrCodeParseFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(EXTRA_QR_CODE_CONTENT_PARAMETER)) {
            mQrCodeContentParameter = (QrCodeContentParameter) getArguments().getSerializable(EXTRA_QR_CODE_CONTENT_PARAMETER);
        } else {
            mQrCodeContentParameter = new QrCodeContentParameter();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView();
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle=getContext().getString(R.string.scan_code_content);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_qr_code_parse,container,false);
        mNavigationBarLayout=(NavigationBarLayout) view.findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.getTitleTv().setText(mTitle);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mQrCodeContentTv=(TextView) view.findViewById(R.id.qr_code_content_text_view);
        mCopyTv=(TextView) view.findViewById(R.id.copy_text_view);

        mCopyTv.setOnClickListener(this);

        mShareTv=(TextView) view.findViewById(R.id.share_text_view);
        mShareTv.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.copy_text_view:
                copy();
                break;
            case R.id.share_text_view:
                share();
                break;
            default:
                break;
        }
    }
    /*start: NavigationBarLayout.OnNavigationBarLayout*/
    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /*end: NavigationBarLayout.OnNavigationBarLayout*/


    private void copy(){

        String qrCodeContent=mQrCodeContentTv.getText().toString();
        if(false== CommonUtil.isEmpty(qrCodeContent)){
            ShareUtil.copyText(getContext(),qrCodeContent);
            ToastUtil.toastLengthshort(getContext(),getString(R.string.copy_content_success,qrCodeContent));
        }else {
            ToastUtil.toastLengthshort(getContext(),R.string.copy_content_is_empty);
        }


    }

    private void share(){
        String qrCodeContent=mQrCodeContentTv.getText().toString();
        if(false== CommonUtil.isEmpty(qrCodeContent)){
            ShareUtil.shareTextToMore(getContext(),getString(R.string.share),qrCodeContent);
        }else {
            ToastUtil.toastLengthshort(getContext(),R.string.share_content_can_not_be_empty);
        }
    }

    private void refreshView(){
        refreshQrCodeContentTv();
        return;
    }

    private void refreshQrCodeContentTv(){
        if(null!=mQrCodeContentParameter){
            mQrCodeContentTv.setText(mQrCodeContentParameter.getContent());
        }
        return;
    }
}
