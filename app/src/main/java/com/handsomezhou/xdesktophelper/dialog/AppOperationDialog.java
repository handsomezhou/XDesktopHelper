package com.handsomezhou.xdesktophelper.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.model.AppOperationType;
import com.handsomezhou.xdesktophelper.util.ViewUtil;

public class AppOperationDialog extends BaseDialog {

	/* Start: AppOperationDialog View */
	private Object mDialogData;

	private View mAppOperationDialogLayout;
	private Button mSetToTopBtn;
	private Button mResetSequenceBtn;
	private Button mUninstallBtn;    
	private Button mViewAppInfoBtn;    
	private AppOperationType mAppOperationType;
    /* End: AppOperationDialog View */
	private OnAppOperationDialog mOnAppOperationDialog;

	public AppOperationDialog(Context context) {
		super(context);
		initData();
		initListener();
	}

	public interface OnAppOperationDialog {
		void onPortfolioStockOperation(AppOperationType appOperationType, Object dialogData);
	}

	
	@Override
	protected View getView() {
		return initView();
	}

	public View getAppOperationDialogLayout() {
        return mAppOperationDialogLayout;
    }

    public void setAppOperationDialogLayout(View appOperationDialogLayout) {
        mAppOperationDialogLayout = appOperationDialogLayout;
    }

    public OnAppOperationDialog getOnAppOperationialog() {
        return mOnAppOperationDialog;
    }

    public void setOnAppOperationialog(OnAppOperationDialog onAppOperationDialog) {
        mOnAppOperationDialog = onAppOperationDialog;
    }

    public Object getDialogData() {
		return mDialogData;
	}

	public void setDialogData(Object dialogData) {
		mDialogData = dialogData;
	}

	   public AppOperationType getAppOperationType() {
	        return mAppOperationType;
	    }

	    public void setAppOperationType(AppOperationType appOperationType) {
	        mAppOperationType = appOperationType;
	    }


	private void initData() {
		
		return;
	}

	private View initView() {
		LayoutInflater inflater = LayoutInflater.from(getContext());

		setAppOperationDialogLayout(inflater.inflate(R.layout.dialog_app_operation,null));
	

		mSetToTopBtn=(Button)getAppOperationDialogLayout().findViewById(R.id.set_to_top_btn);
		mResetSequenceBtn=(Button)getAppOperationDialogLayout().findViewById(R.id.reset_sequence_btn);
		mUninstallBtn=(Button)getAppOperationDialogLayout().findViewById(R.id.uninstall_btn);
		mViewAppInfoBtn=(Button)getAppOperationDialogLayout().findViewById(R.id.view_app_info_btn);
		//refreshSetToTop(getSetToTopType());
		return getAppOperationDialogLayout();
	}

	private void initListener() {

		setOnClickListener(mSetToTopBtn, AppOperationType.SET_TO_TOP);
		setOnClickListener(mResetSequenceBtn, AppOperationType.RESET_SEQUENCE);
		setOnClickListener(mUninstallBtn, AppOperationType.UNINSTALL);
		setOnClickListener(mViewAppInfoBtn, AppOperationType.VIEW_APP_INFO);
		return;
	}

	

	
	private void setOnClickListener(View view,final AppOperationType appOperationType){
		if(null==view){
			return;
		}
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    appOperation(appOperationType);
				
			}
		});
	}

	
	private void appOperation(AppOperationType appOperationType){
		if(null!=mOnAppOperationDialog){
			mOnAppOperationDialog.onPortfolioStockOperation(appOperationType,getDialogData());
		}
		
        this.dismiss();

        return;
	}
	
	
}
