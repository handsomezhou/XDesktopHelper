
package com.handsomezhou.xdesktophelper.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.Interface.OnTabChange;
import com.handsomezhou.xdesktophelper.adapter.CustomPartnerViewPagerAdapter;
import com.handsomezhou.xdesktophelper.dialog.BaseProgressDialog;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper.OnAppInfoLoad;
import com.handsomezhou.xdesktophelper.helper.AppSettingInfoHelper;
import com.handsomezhou.xdesktophelper.helper.AppSettingInfoHelper.OnAppSettingInfoLoad;
import com.handsomezhou.xdesktophelper.helper.AppStartRecordHelper;
import com.handsomezhou.xdesktophelper.helper.AppStartRecordHelper.OnAppStartRecordLoad;
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.IconButtonData;
import com.handsomezhou.xdesktophelper.model.IconButtonValue;
import com.handsomezhou.xdesktophelper.model.LoadStatus;
import com.handsomezhou.xdesktophelper.model.PartnerView;
import com.handsomezhou.xdesktophelper.model.SearchMode;
import com.handsomezhou.xdesktophelper.util.ViewUtil;
import com.handsomezhou.xdesktophelper.view.CustomViewPager;
import com.handsomezhou.xdesktophelper.view.TopTabView;

public class MainFragment extends BaseFragment implements OnAppInfoLoad, OnAppStartRecordLoad,OnAppSettingInfoLoad,
        OnTabChange {
    private static final String TAG = MainFragment.class.getSimpleName();
    private List<PartnerView> mPartnerViews;
    private TopTabView mTopTabView;
    private CustomViewPager mCustomViewPager;
    private CustomPartnerViewPagerAdapter mCustomPartnerViewPagerAdapter;
    private SearchMode mSearchMode;
    private BaseProgressDialog mBaseProgressDialog;
    private PopupWindow mSearchModeSwitchTipsPw;

    @Override
    public void onResume() {
        Log.i(TAG, "appStartRecords.size()="
                + AppInfoHelper.getInstance().getBaseAllAppInfos().size());

        for (AppInfo asr : AppInfoHelper.getInstance().getBaseAllAppInfos()) {
            Log.i(TAG, asr.getLabel() + ":" + ":" + asr.getCommonWeights());

        }
    
        refreshView();
        super.onResume();
    }

    @Override
    protected void initData() {
        setContext(getActivity());

        mPartnerViews = new ArrayList<PartnerView>();
        /* start: T9 search view */
        PartnerView t9PartnerView = new PartnerView(SearchMode.T9,
                new T9SearchFragment());
        mPartnerViews.add(t9PartnerView);

        /* end: T9 search view */

        /* start: Qwerty search view */
        PartnerView qwertyPartnerView = new PartnerView(SearchMode.QWERTY,
                new QwertySearchFragment());
        mPartnerViews.add(qwertyPartnerView);
        /* end: Qwerty search view */

        AppStartRecordHelper.getInstance().setOnAppStartRecordLoad(this);
        AppStartRecordHelper.getInstance().startLoadAppStartRecord();

        AppInfoHelper.getInstance().setOnAppInfoLoad(this);
        boolean startLoadSuccess = AppInfoHelper.getInstance()
                .startLoadAppInfo();
        if (true == startLoadSuccess) {
            getBaseProgressDialog().show(
                    getContext().getString(R.string.app_info_loading));
        }

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mCustomViewPager = (CustomViewPager) view
                .findViewById(R.id.custom_view_pager);
        mCustomViewPager.setPagingEnabled(false);

        mTopTabView = (TopTabView) view.findViewById(R.id.top_tab_view);
        mTopTabView.setTextColorFocused(getContext().getResources().getColor(R.color.sea_green4));
        mTopTabView.setTextColorUnfocused(getContext().getResources().getColor(R.color.dim_grey));
        mTopTabView.setTextColorUnselected(getContext().getResources().getColor(R.color.dim_grey));
        mTopTabView.setHideIcon(true);
        mTopTabView.removeAllViews();

        /* start: T9 search tab */
        IconButtonValue t9IconBtnValue = new IconButtonValue(SearchMode.T9, 0, R.string.t9_search);
        t9IconBtnValue.setHideIcon(mTopTabView.isHideIcon());
        IconButtonData t9IconBtnData = new IconButtonData(getContext(),
                t9IconBtnValue);
        mTopTabView.addIconButtonData(t9IconBtnData);
        
        /* end: T9 search tab */

        /* start: Qwerty search tab */
        IconButtonValue qwertyIconBtnValue = new IconButtonValue(
                SearchMode.QWERTY, 0, R.string.qwerty_search);
        t9IconBtnValue.setHideIcon(mTopTabView.isHideIcon());
        IconButtonData qwertyIconBtnData = new IconButtonData(getContext(),
                qwertyIconBtnValue);
        mTopTabView.addIconButtonData(qwertyIconBtnData);
        /* end: Qwerty search tab */

        mTopTabView.setOnTabChange(this);
        
        return view;
    }

    @Override
    protected void initListener() {
        FragmentManager fm = getChildFragmentManager();
        mCustomPartnerViewPagerAdapter = new CustomPartnerViewPagerAdapter(fm,
                mPartnerViews);
        mCustomViewPager.setAdapter(mCustomPartnerViewPagerAdapter);
        mCustomViewPager
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageSelected(int pos) {

                        PartnerView partnerView = mPartnerViews.get(pos);
                        // Toast.makeText(getContext(),addressBookView.getTag().toString()+"+++"
                        // , Toast.LENGTH_LONG).show();
                        mTopTabView.setCurrentTabItem(partnerView.getTag());
                        SettingsHelper.getInstance().setSearchMode((SearchMode)partnerView.getTag());
                        SettingsHelper.getInstance().setSearchModeSwitchTips(false);
                        refreshView();
                    }

                    @Override
                    public void onPageScrolled(int pos, float posOffset,
                            int posOffsetPixels) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        
       
        mCustomViewPager.setCurrentItem(getPartnerViewItem(SettingsHelper.getInstance().getSearchMode()));

    }

    /* start: OnAppInfoLoad */
    @Override
    public void onAppInfoLoadSuccess() {
        getBaseProgressDialog().hide();

        if (AppStartRecordHelper.getInstance().getAppStartRecordsLoadStatus() == LoadStatus.LOAD_FINISH) {
            Log.i(TAG, "onAppInfoLoadSuccess before");
            if (true == AppStartRecordHelper.getInstance().parseAppStartRecord()) {
                Log.i(TAG, "onAppInfoLoadSuccess ture");
            } else {
                Log.i(TAG, "onAppInfoLoadSuccess false");
            }
            AppSettingInfoHelper.getInstance().startLoadAppSettingInfo(this);
        }
        AppInfoHelper.getInstance().getQwertySearchAppInfo(null);
        AppInfoHelper.getInstance().getT9SearchAppInfo(null);
        refreshView();

    }

    @Override
    public void onAppInfoLoadFailed() {
        getBaseProgressDialog().hide();
        refreshView();
    }

    /* end: OnAppInfoLoad */

    /* start: OnAppStartRecordLoad */
    @Override
    public void onAppStartRecordSuccess() {
        if (AppInfoHelper.getInstance().getBaseAllAppInfosLoadStatus() == LoadStatus.LOAD_FINISH) {
            Log.i(TAG, "onAppStartRecordSuccess before");
            if (true == AppStartRecordHelper.getInstance().parseAppStartRecord()) {
                Log.i(TAG, "onAppStartRecordSuccess ture");
                refreshView();
            } else {
                Log.i(TAG, "onAppStartRecordSuccess false");
            }
            AppSettingInfoHelper.getInstance().startLoadAppSettingInfo(this);
        }

    }

    @Override
    public void onAppStartRecordFailed() {
        // TODO Auto-generated method stub

    }

    /* end: OnAppStartRecordLoad */

    /*start: OnAppSettingInfoLoad*/
    @Override
    public void onAppSettingInfoLoadSuccess() {
        AppSettingInfoHelper.getInstance().parseAppSettingInfo();
        refreshView();
        if(true==SettingsHelper.getInstance().isSearchModeSwitchTips()){
            getSearchModeSwitchTipsPw().showAsDropDown(mTopTabView);
        }
    }

    @Override
    public void onAppSettingInfoLoadFailed() {
        // TODO Auto-generated method stub
        
    }
    /*end: OnAppSettingInfoLoad*/
    
    /* start: OnTabChange */
    @Override
    public void onChangeToTab(Object fromTab, Object toTab,
            TAB_CHANGE_STATE tabChangeState) {
        int item = getPartnerViewItem(toTab);
        mCustomViewPager.setCurrentItem(item);

    }

    @Override
    public void onClickTab(Object currentTab, TAB_CHANGE_STATE tabChangeState) {
        Fragment fragment = mPartnerViews.get(getPartnerViewItem(currentTab))
                .getFragment();
        switch ((SearchMode) currentTab) {
            case T9:
                onChangeToTab(SearchMode.T9, SearchMode.QWERTY, tabChangeState);
               /* if (fragment instanceof T9SearchFragment) {
                    // ((T9SearchFragment) fragment).updateView(tabChangeState);
                    ((T9SearchFragment) fragment).refreshView();
                }*/
                break;
            case QWERTY:
                onChangeToTab(SearchMode.QWERTY, SearchMode.T9, tabChangeState);
               /* if (fragment instanceof QwertySearchFragment) {
                    ((QwertySearchFragment) fragment).refreshView();
                }*/
                break;
            default:
                break;
        }

    }

    /* end: OnTabChange */

    public SearchMode getSearchMode() {
        return mSearchMode;
    }

    public void setSearchMode(SearchMode searchMode) {
        mSearchMode = searchMode;
    }

    public BaseProgressDialog getBaseProgressDialog() {
        if (null == mBaseProgressDialog) {
            mBaseProgressDialog = new BaseProgressDialog(getContext());
        }
        return mBaseProgressDialog;
    }

    public void setBaseProgressDialog(BaseProgressDialog baseProgressDialog) {
        mBaseProgressDialog = baseProgressDialog;
    }

  
    public PopupWindow getSearchModeSwitchTipsPw() {
        if(null==mSearchModeSwitchTipsPw){
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View conference_start_tips_popup_window =(View) inflater.inflate(
                    R.layout.popup_window_search_mode_switch_tips, null);
            mSearchModeSwitchTipsPw=new PopupWindow(conference_start_tips_popup_window,
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ColorDrawable dw = new ColorDrawable(R.color.grey21_transparent);
            mSearchModeSwitchTipsPw.setBackgroundDrawable(dw);
            mSearchModeSwitchTipsPw.setOutsideTouchable(true);
         /*   mSearchModeSwitchTipsPw.setFocusable(true);*/
            mSearchModeSwitchTipsPw.setTouchable(true);
         
            conference_start_tips_popup_window.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    mSearchModeSwitchTipsPw.dismiss();
                    
                }
            });
        }

        return mSearchModeSwitchTipsPw;
    }

    public void setSearchModeSwitchTipsPw(PopupWindow searchModeSwitchTipsPw) {
        mSearchModeSwitchTipsPw = searchModeSwitchTipsPw;
    }

    private void refreshView() {
        Object currentTab = mTopTabView.getCurrentTab();
        showTopTabView(SettingsHelper.getInstance().getSearchMode());
        int itemIndex = getPartnerViewItem(currentTab);
        Fragment fragment = mPartnerViews.get(itemIndex).getFragment();
        switch ((SearchMode) currentTab) {
            case T9:
                if (fragment instanceof T9SearchFragment) {
                    ((T9SearchFragment) fragment).updateSearch();
                    ((T9SearchFragment) fragment).refreshView();
                }
                break;
            case QWERTY:
               
                if (fragment instanceof QwertySearchFragment) {
                    ((QwertySearchFragment) fragment).updateSearch();
                    ((QwertySearchFragment) fragment).refreshView();
                }
                break;
            default:
                break;
        }
    }

    private int getPartnerViewItem(Object tag) {
        int item = 0;
        ;
        do {
            if (null == tag) {
                break;
            }

            for (int i = 0; i < mPartnerViews.size(); i++) {
                if (mPartnerViews.get(i).getTag().equals(tag)) {
                    item = i;
                    break;
                }
            }
        } while (false);

        return item;
    }
    
    private void showTopTabView(SearchMode searchMode){
        for(int i=0; i<mTopTabView.getChildCount(); i++){
            ViewUtil.hideView(mTopTabView.getChildAt(i));
        }
        ViewUtil.showView(mTopTabView.getChildAt(searchMode.ordinal()));
       
    }

   
}
