package com.handsomezhou.xdesktophelper.fragment;

import java.util.Collections;

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
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.AppOperationType;
import com.handsomezhou.xdesktophelper.util.AppUtil;
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
	@Override
	public void onResume() {
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
				AppUtil.startApp(getContext(), appInfo);
				
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
		updateSearch(curCharacter);
		refreshView();
		
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
                    updateSearch();
                    refreshView();
                }
                break;
            case RESET_SEQUENCE:
                boolean resetSequenceSuccess=AppInfoHelper.getInstance().resetSequence(appInfo);
                AppSettingInfoHelper.getInstance().cancelSetToTop(appInfo);
                if(true==resetSequenceSuccess){
                    updateSearch();
                    refreshView();
                }
                
                break;
            case UNINSTALL:
                AppUtil.uninstallApp(getContext(),(AppInfo)dialogData);
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
	public void refreshView() {
		refreshQwertySearchGv();
	}
	
	public void updateSearch(){
		if(null==mSearchBox){
			return;
		}
		updateSearch(mSearchBox.getSearchEtInput());
	
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
	
	private void updateSearch(String search) {
		Log.i(TAG, "search=["+search+"]");
		String curCharacter;
		if (null == search) {
			curCharacter = search;
		} else {
			curCharacter = search.trim();
		}
		
		if (TextUtils.isEmpty(curCharacter)) {
			AppInfoHelper.getInstance().getQwertySearchAppInfo(null);
	       
		} else {
			AppInfoHelper.getInstance().getQwertySearchAppInfo(curCharacter);
		}
	}
	

}
