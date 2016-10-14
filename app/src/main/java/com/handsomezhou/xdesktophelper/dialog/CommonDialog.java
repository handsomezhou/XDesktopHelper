package com.handsomezhou.xdesktophelper.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;


/**
 * ͨ�öԻ���
 * 
 * @author tony
 *
 */
public class CommonDialog extends BaseDialog {

 private OnCommonDialog mOnCommonDialog;
     private Object mDialogType;//�Ի�������
     private Object mDialogData;//�Ի�������
    //Start: CommonDialog Data
    private int mIcon;
    private String mTitle;
    private String mMessage;
    private String mOk;
    private String mCancel;
    //End: CommonDialog Data
    
    private View mCommonDialogLayout;
    //Start: CommonDialog View
    private ImageView mIconIv;
    private TextView mTitleTv;
    private TextView mMessageTv;
    private Button mOkBtn;
    private Button mCancelBtn;
    //End: CommonDialog View
    

    public CommonDialog(Context context) {
        super(context);

        initData();
        initListener();
    }

    public interface OnCommonDialog{
        void onCommonDialogOk(Object dialogType, Object dialogData);
        void onCommonDialogCancel(Object dialogType, Object dialogData);
    }
    
    /*Start : BaseDialog*/
    @Override
    protected View getView() {
        return initView();
    }   
    /*End : BaseDialog*/
    public Object getDialogType() {
        return mDialogType;
    }


    public void setDialogType(Object dialogType) {
        mDialogType = dialogType;
    }
  
    public Object getDialogData() {
        return mDialogData;
    }
    
    public void setDialogData(Object dialogData) {
        mDialogData = dialogData;
    }
    
    public int getIcon() {
        return mIcon;
    }

 
    public OnCommonDialog getOnCommonDialog() {
        return mOnCommonDialog;
    }


    public void setOnCommonDialog(OnCommonDialog onCommonDialog) {
        mOnCommonDialog = onCommonDialog;
    }


    public void setIcon(int icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getOk() {
        return mOk;
    }

    public void setOk(String ok) {
        mOk = ok;
    }

    public String getCancel() {
        return mCancel;
    }

    public void setCancel(String cancel) {
        mCancel = cancel;
    }


   
    public View getCommonDialogLayout() {
        return mCommonDialogLayout;
    }

    public void setCommonDialogLayout(View commonDialogLayout) {
        mCommonDialogLayout = commonDialogLayout;
    }

    public ImageView getIconIv() {
        return mIconIv;
    }

    public void setIconIv(ImageView iconIv) {
        mIconIv = iconIv;
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public void setTitleTv(TextView titleTv) {
        mTitleTv = titleTv;
    }

    public TextView getMessageTv() {
        return mMessageTv;
    }

    public void setMessageTv(TextView messageTv) {
        mMessageTv = messageTv;
    }

    public Button getOkBtn() {
        return mOkBtn;
    }

    public void setOkBtn(Button okBtn) {
        mOkBtn = okBtn;
    }

    public Button getCancelBtn() {
        return mCancelBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        mCancelBtn = cancelBtn;
    }

    private void initData() {

        return;
    }

    @SuppressLint("InflateParams")
    private View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        setCommonDialogLayout(inflater.inflate(R.layout.dialog_common, null));
        setIconIv((ImageView)getCommonDialogLayout().findViewById(R.id.icon_image_view));
        setTitleTv((TextView)getCommonDialogLayout().findViewById(R.id.title_text_view));
        setMessageTv((TextView)getCommonDialogLayout().findViewById(R.id.message_text_view));
        setOkBtn((Button)getCommonDialogLayout().findViewById(R.id.ok_btn));
        setCancelBtn((Button)getCommonDialogLayout().findViewById(R.id.cancel_btn));
        
        return getCommonDialogLayout();
    }

    private void initListener() {
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                clickOkBtn();
            }
        });
        
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                clickCancelBtn();
            }
        });
        
        return;
    }
    
    private void clickOkBtn(){
        
        if(null!=mOnCommonDialog){
            mOnCommonDialog.onCommonDialogOk(getDialogType(),getDialogData());
        }
        this.dismiss();
        
        return;
    }
    
    private void clickCancelBtn(){
        
        if(null!=mOnCommonDialog){
            mOnCommonDialog.onCommonDialogCancel(getDialogType(),getDialogData());
        }
        
        this.dismiss();
        
        return;
    }

}
