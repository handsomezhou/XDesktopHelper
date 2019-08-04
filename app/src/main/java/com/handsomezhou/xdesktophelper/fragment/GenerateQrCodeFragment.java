package com.handsomezhou.xdesktophelper.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.util.ActivityUtil;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.handsomezhou.xdesktophelper.util.ImageUtil;
import com.handsomezhou.xdesktophelper.util.QRCodeUtil;
import com.handsomezhou.xdesktophelper.util.ShareUtil;
import com.handsomezhou.xdesktophelper.util.ToastUtil;
import com.handsomezhou.xdesktophelper.util.ViewUtil;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;

/**
 * Created by handsomezhou on 2019/8/1.
 */

public class GenerateQrCodeFragment extends BaseFragment  implements NavigationBarLayout.OnNavigationBarLayout{
    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;
    private EditText mQrCodeEt;
    private Button mGenerateQrCodeBtn;
    private TextView mViewQrCodeImageTv;
    private ImageView mQrCodeIv;
    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle=getString(R.string.qr_code_generator);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_generate_qr_code,container,false);

        mNavigationBarLayout=(NavigationBarLayout) view.findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.getTitleTv().setText(mTitle);
        mNavigationBarLayout.setOnNavigationBarLayout(this);

        mQrCodeEt =view.findViewById(R.id.qr_code_edit_text);
        mGenerateQrCodeBtn=view.findViewById(R.id.generate_qr_code_btn);
        mViewQrCodeImageTv=view.findViewById(R.id.view_qr_code_image_text_view);
        mQrCodeIv=view.findViewById(R.id.qr_code_image_view);

        return view;
    }

    @Override
    protected void initListener() {
        mGenerateQrCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQrCode();
                //ViewUtil.showView(mViewQrCodeImageTv);
                //unok
                ViewUtil.hideView(mViewQrCodeImageTv);
            }
        });

        mQrCodeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=getString(R.string.share_qr_code);
                String imageName=mQrCodeEt.getText().toString();
                ImageUtil.saveImageToGallery(mQrCodeIv,imageName);
                ShareUtil.shareImageToMore( getContext(), title,imageName);
            }
        });

        mViewQrCodeImageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName=mQrCodeEt.getText().toString();
                ImageUtil.saveImageToGallery(mQrCodeIv,imageName);
                ShareUtil.openGallery(getContext());
            }
        });
    }

    /*start:OnNavigationBarLayout*/
    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /*end:OnNavigationBarLayout*/

    private void generateQrCode(){
        String tips;
        do{
            String qrCodeStr= mQrCodeEt.getText().toString();
            if(CommonUtil.isEmpty(qrCodeStr)){
                tips=getString(R.string.qr_code_content_can_not_be_empty);
                break;
            }
            QRCodeUtil.createQRImage(mQrCodeIv,QRCodeUtil.QR_HEIGHT,QRCodeUtil.QR_HEIGHT,qrCodeStr);
            tips=getString(R.string.generate_qr_code_success);
        }while (false);

        if(false==CommonUtil.isEmpty(tips)){
            ToastUtil.toastLengthshort(getContext(),tips);
        }
        return;
    }
}
