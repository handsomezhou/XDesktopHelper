package com.handsomezhou.xdesktophelper.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
import com.handsomezhou.xdesktophelper.view.SearchBox;
import com.handsomezhou.xdesktophelper.view.SearchBox.OnSearchBox;

public class QwertySearchFragment extends BaseFragment implements OnSearchBox,OnAppOperationDialog{
	private static final String TAG=QwertySearchFragment.class.getSimpleName();
	private GridView mQwertySearchGv;
	private TextView mSearchResultPromptTv;
	private SearchBox mSearchBox;
	private AppInfoAdapter mAppInfoAdapter;
	private AppOperationDialog mAppOperationDialog;
	private boolean mStartAppSuccess=false;
	private OnQwertySearchFragment mOnQwertySearchFragment;
	private boolean mVoiceSearch=false;
	public interface OnQwertySearchFragment{
		void onQwertySearchVoiceInput();
	}

	@Override
	public void onResume() {
		if(null!=mSearchBox){
			mSearchBox.setVoiceSearchEnable(SettingsHelper.getInstance().isVoiceSearchEnable());
		}
		if(true==mStartAppSuccess){
			mStartAppSuccess=false;
			mSearchBox.getSearchEt().setText(null);
		}
		refreshView();
		super.onResume();
	}
	
	@Override
	protected void initData() {
		setContext(getActivity());
		mAppInfoAdapter = new AppInfoAdapter(getContext(),
				R.layout.app_info_grid_item, AppInfoHelper.getInstance()
						.getQwertySearchAppInfos());
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_qwerty_search, container, false);
		mQwertySearchGv=(GridView) view.findViewById(R.id.qwerty_search_grid_view);
		mQwertySearchGv.setAdapter(mAppInfoAdapter);
		mSearchResultPromptTv = (TextView) view
				.findViewById(R.id.search_result_prompt_text_view);
		mSearchBox=(SearchBox) view.findViewById(R.id.search_box);
		mSearchBox.setOnSearchBox(this);
		return view;
	}

	@Override
	protected void initListener() {
		mQwertySearchGv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
				mStartAppSuccess=AppUtil.startApp(getContext(), appInfo);
				
			}
		});

		mQwertySearchGv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppInfo appInfo=(AppInfo) parent.getItemAtPosition(position);
	
				getAppOperationDialog(appInfo).show();
			
				return true;
			}
		});

	}

	/*start: OnSearchBox*/
	@Override
	public void onSearchTextChanged(String curCharacter) {
		search(curCharacter);
		refreshView();

		if(true==isVoiceSearch()){
			if(true== SettingsHelper.getInstance().isVoiceStartApp()) {
				voiceStartApp();
			}
			setVoiceSearch(false);
		}

	}

	@Override
	public void onQwertySearchVoiceInput() {
//		ToastUtil.toastLengthshort(getContext(),"onQwertySearchVoiceInput");
		if(null!=mOnQwertySearchFragment){
			mOnQwertySearchFragment.onQwertySearchVoiceInput();
		}
	}
	/*end: OnSearchBox*/
	
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

	public OnQwertySearchFragment getOnQwertySearchFragment() {
		return mOnQwertySearchFragment;
	}

	public void setOnQwertySearchFragment(OnQwertySearchFragment onQwertySearchFragment) {
		mOnQwertySearchFragment = onQwertySearchFragment;
	}

	public boolean isVoiceSearch() {
		return mVoiceSearch;
	}

	public void setVoiceSearch(boolean voiceSearch) {
		mVoiceSearch = voiceSearch;
	}

	public void refreshView() {
		refreshQwertySearchGv();
		refreshSearchBox();
	}
	
	public void search(){
		if(null==mSearchBox){
			return;
		}
		search(mSearchBox.getSearchEtInput());
	
	}

	public void voiceSearch(String voiceText){
		do{
			if(CommonUtil.isEmpty(voiceText)){
				break;
			}

			setVoiceSearch(true);
			mSearchBox.getSearchEt().setText(voiceText);
			mSearchBox.getSearchEt().setSelection(mSearchBox.getSearchEt().length());

			//ToastUtil.toastLengthshort(getContext(),voiceText);
		}while (false);

		return;
	}

	private void refreshQwertySearchGv() {
		if (null == mQwertySearchGv) {
			return;
		}

		BaseAdapter baseAdapter = (BaseAdapter) mQwertySearchGv.getAdapter();
		Log.i(TAG, "getCount"+baseAdapter.getCount()+"");
		if (null != baseAdapter) {
			baseAdapter.notifyDataSetChanged();
			if (baseAdapter.getCount() > 0) {
				ViewUtil.showView(mQwertySearchGv);
				ViewUtil.hideView(mSearchResultPromptTv);
			} else {
				ViewUtil.hideView(mQwertySearchGv);
				ViewUtil.showView(mSearchResultPromptTv);
			}
		}
	}

	private void refreshSearchBox(){
		if(null==mSearchBox){
			return;
		}

		mSearchBox.refreshView();
	}
	private void search(String keyword) {
		Log.i(TAG, "keyword=["+keyword+"]");
		String curCharacter;
		if (null == keyword) {
			curCharacter = keyword;
		} else {
			curCharacter = keyword.trim();
		}
		
		if (TextUtils.isEmpty(curCharacter)) {
			AppInfoHelper.getInstance().qwertySearch(null);
	       
		} else {
			AppInfoHelper.getInstance().qwertySearch(curCharacter);
		}
	}

	private void voiceStartApp(){
		if(1==mAppInfoAdapter.getCount()){
			AppInfo appInfo=mAppInfoAdapter.getItem(0);
			mStartAppSuccess=AppUtil.startApp(getContext(), appInfo);
		}
	}
	

}
