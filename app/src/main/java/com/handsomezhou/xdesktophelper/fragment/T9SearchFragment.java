package com.handsomezhou.xdesktophelper.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.adapter.AppInfoAdapter;
import com.handsomezhou.xdesktophelper.dialog.AppOperationDialog;
import com.handsomezhou.xdesktophelper.dialog.AppOperationDialog.OnAppOperationDialog;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.helper.AppSettingInfoHelper;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.AppOperationType;
import com.handsomezhou.xdesktophelper.util.AppUtil;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.handsomezhou.xdesktophelper.util.ViewUtil;
import com.handsomezhou.xdesktophelper.view.T9TelephoneDialpadView;
import com.handsomezhou.xdesktophelper.view.T9TelephoneDialpadView.OnT9TelephoneDialpadView;

public class T9SearchFragment extends BaseFragment implements
		OnT9TelephoneDialpadView,OnAppOperationDialog{
	private static final String TAG="T9SearchFragment";
	private GridView mT9SearchGv;
	private TextView mSearchResultPromptTv;
	private T9TelephoneDialpadView mT9TelephoneDialpadView;
	private View mKeyboardSwitchLayout;
	private ImageView mKeyboardSwitchIv;
	private AppInfoAdapter mAppInfoAdapter;
	private AppOperationDialog mAppOperationDialog;
	private boolean mStartAppSuccess=false;
	private OnT9SearchFragment mOnT9SearchFragment;
	private boolean mVoiceSearch=false;
	public interface OnT9SearchFragment{
		void onT9SearchVoiceInput();
	}

    @Override
	public void onResume() {
		if(null!=mT9TelephoneDialpadView){
			mT9TelephoneDialpadView.setVoiceSearchEnable(SettingsHelper.getInstance().isVoiceSearchEnable());
		}
    	if(true==mStartAppSuccess){
			mStartAppSuccess=false;
			mT9TelephoneDialpadView.getT9InputEt().setText(null);
		}
        showKeyboard();
		refreshView();
		super.onResume();
	}

	@Override
	protected void initData() {
		setContext(getActivity());
		mAppInfoAdapter = new AppInfoAdapter(getContext(),
				R.layout.app_info_grid_item, AppInfoHelper.getInstance()
						.getT9SearchAppInfos());
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_t9_search, container,
				false);
		mT9SearchGv = (GridView) view.findViewById(R.id.t9_search_grid_view);
		mT9SearchGv.setAdapter(mAppInfoAdapter);
		mSearchResultPromptTv = (TextView) view
				.findViewById(R.id.search_result_prompt_text_view);
		mT9TelephoneDialpadView = (T9TelephoneDialpadView) view
				.findViewById(R.id.t9_telephone_dialpad_view);
		mT9TelephoneDialpadView.setOnT9TelephoneDialpadView(this);
		mKeyboardSwitchLayout = view.findViewById(R.id.keyboard_switch_layout);
		mKeyboardSwitchIv = (ImageView) view
				.findViewById(R.id.keyboard_switch_image_view);
		showKeyboard();
		return view;
	}

	@Override
	protected void initListener() {
		mT9SearchGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
				mStartAppSuccess=AppUtil.startApp(getContext(), appInfo);
				
			}
		});

		mT9SearchGv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
	
				getAppOperationDialog(appInfo).show();
			
				return true;
			}
		});
		
		mT9SearchGv.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {  
                    case OnScrollListener.SCROLL_STATE_IDLE: //  
                        Log.i(TAG, "SCROLL_STATE_IDLE");
                        // mBusy = false;  
                        break;  
                    case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  
                        Log.i(TAG, "SCROLL_STATE_TOUCH_SCROLL");

                        // mBusy = true;  
                        
                        break;  
                    case OnScrollListener.SCROLL_STATE_FLING:  
                        Log.i(TAG, "SCROLL_STATE_FLING");
                
                    
                        hideKeyboard();
          
                        break;  
                    }  
                
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {
                // TODO Auto-generated method stub
                
            }
        });
		mKeyboardSwitchLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switchKeyboard();
			}
		});

		mKeyboardSwitchIv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switchKeyboard();
			}
		});

	}

	/* start: OnT9TelephoneDialpadView */
	@Override
	public void onAddDialCharacter(String addCharacter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeleteDialCharacter(String deleteCharacter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDialInputTextChanged(String curCharacter) {
		if(true==isVoiceSearch()){
			voiceTextSearch(curCharacter);
			refreshView();
			if(true== SettingsHelper.getInstance().isVoiceStartApp()) {
				voiceStartApp();
			}
			setVoiceSearch(false);
		}else {
			search(curCharacter);
			refreshView();
		}


	}

	@Override
	public void onHideT9TelephoneDialpadView() {
		hideKeyboard();

	}

	@Override
	public void onT9SearchVoiceInput() {
		//ToastUtil.toastLengthshort(getContext(),"onT9SearchVoiceInput");
		if(null!=mOnT9SearchFragment){
			mOnT9SearchFragment.onT9SearchVoiceInput();
		}
	}

	/* end: OnT9TelephoneDialpadView */

	/*start: OnAppOperationDialog*/
    @Override
    public void onPortfolioStockOperation(AppOperationType appOperationType, Object dialogData) {
         AppInfo appInfo=(AppInfo)dialogData;
        switch (appOperationType) {
            case SET_TO_TOP:
                boolean setToTopSuccess=AppSettingInfoHelper.getInstance().setToTop(appInfo);
                if(true==setToTopSuccess){
                    search();
                    refreshView();
                }
                break;
            case RESET_SEQUENCE:
                boolean resetSequenceSuccess=AppInfoHelper.getInstance().resetSequence(appInfo);
                AppSettingInfoHelper.getInstance().cancelSetToTop(appInfo);
                if(true==resetSequenceSuccess){
                    search();
                    refreshView();
                }
                
                break;
            case UNINSTALL:
                AppUtil.uninstallApp(getContext(),(AppInfo)dialogData);
               
                break;
            case VIEW_APP_INFO:
                AppUtil.viewApp(getContext(), (AppInfo)dialogData);
                break;

            default:
                break;
        }
        
    }
    /*end: OnAppOperationDialog*/
    
    public AppOperationDialog getAppOperationDialog(AppInfo appInfo) {
        if (null == mAppOperationDialog) {
            mAppOperationDialog = new AppOperationDialog(getContext());
            mAppOperationDialog.setOnAppOperationialog(this);
            mAppOperationDialog.setCanceledOnTouchOutside(true);
        }

        mAppOperationDialog.setDialogData(appInfo);

        return mAppOperationDialog;
    }

    public void setAppOperationDialog(AppOperationDialog appOperationDialog) {
        mAppOperationDialog = appOperationDialog;
    }

	public OnT9SearchFragment getOnT9SearchFragment() {
		return mOnT9SearchFragment;
	}

	public void setOnT9SearchFragment(OnT9SearchFragment onT9SearchFragment) {
		mOnT9SearchFragment = onT9SearchFragment;
	}

	public boolean isVoiceSearch() {
		return mVoiceSearch;
	}

	public void setVoiceSearch(boolean voiceSearch) {
		mVoiceSearch = voiceSearch;
	}

	public void refreshView() {
		refreshT9SearchGv();
		refreshT9TelephoneDialpadView();
	}

	public void search(){
		if(null==mT9TelephoneDialpadView){
			return;
		}
		
		search(mT9TelephoneDialpadView.getT9Input());
	}

	public void voiceSearch(String voiceText){
		do{
			if(CommonUtil.isEmpty(voiceText)){
				break;
			}

			setVoiceSearch(true);
			mT9TelephoneDialpadView.getT9InputEt().setText(voiceText);
		}while (false);

		return;
	}

	private void search(String keyword) {
		search(keyword,false);
	}

	private void voiceTextSearch(String keyword){
		search(keyword,true);
	}

	private void search(String keyword, boolean voiceSearch) {
		Log.i(TAG, "keyword=["+keyword+"]");
		String curCharacter;
		if (null == keyword) {
			curCharacter = keyword;
		} else {
			curCharacter = keyword.trim();
		}

		if (TextUtils.isEmpty(curCharacter)) {
			AppInfoHelper.getInstance().t9Search(null,voiceSearch);
		} else {
			AppInfoHelper.getInstance().t9Search(curCharacter,voiceSearch);
		}
	}

	private void switchKeyboard() {
		if (ViewUtil.getViewVisibility(mT9TelephoneDialpadView) == View.GONE) {
			showKeyboard();
		} else {
			hideKeyboard();
		}
	}

	private void hideKeyboard() {
		ViewUtil.hideView(mT9TelephoneDialpadView);
		mKeyboardSwitchIv
				.setBackgroundResource(R.drawable.keyboard_show_selector);
	}

	private void showKeyboard() {
		ViewUtil.showView(mT9TelephoneDialpadView);
		mKeyboardSwitchIv
				.setBackgroundResource(R.drawable.keyboard_hide_selector);
	}

	private void refreshT9SearchGv() {
		if (null == mT9SearchGv) {
			return;
		}

		BaseAdapter baseAdapter = (BaseAdapter) mT9SearchGv.getAdapter();
		Log.i(TAG, "getCount"+baseAdapter.getCount()+"");
		if (null != baseAdapter) {
			baseAdapter.notifyDataSetChanged();
			if (baseAdapter.getCount() > 0) {
				ViewUtil.showView(mT9SearchGv);
				ViewUtil.hideView(mSearchResultPromptTv);
			} else {
				ViewUtil.hideView(mT9SearchGv);
				ViewUtil.showView(mSearchResultPromptTv);
			}
		}
	}
	
	private void refreshT9TelephoneDialpadView(){
		if(null==mT9TelephoneDialpadView){
			return;
		}
		
		mT9TelephoneDialpadView.refreshView();
	}
	

	private void voiceStartApp(){
		if(1==mAppInfoAdapter.getCount()){
			AppInfo appInfo=mAppInfoAdapter.getItem(0);
			mStartAppSuccess=AppUtil.startApp(getContext(), appInfo);
		}
	}

}
