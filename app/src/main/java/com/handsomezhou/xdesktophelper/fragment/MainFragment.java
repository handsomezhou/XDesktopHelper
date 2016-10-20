
package com.handsomezhou.xdesktophelper.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.handsomezhou.xdesktophelper.Interface.OnTabChange;
import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.adapter.CustomPartnerViewPagerAdapter;
import com.handsomezhou.xdesktophelper.dialog.BaseProgressDialog;
import com.handsomezhou.xdesktophelper.fragment.T9SearchFragment.OnT9SearchFragment;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper.OnAppInfoLoad;
import com.handsomezhou.xdesktophelper.helper.AppSettingInfoHelper;
import com.handsomezhou.xdesktophelper.helper.AppSettingInfoHelper.OnAppSettingInfoLoad;
import com.handsomezhou.xdesktophelper.helper.AppStartRecordHelper;
import com.handsomezhou.xdesktophelper.helper.AppStartRecordHelper.OnAppStartRecordLoad;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.model.IconButtonData;
import com.handsomezhou.xdesktophelper.model.IconButtonValue;
import com.handsomezhou.xdesktophelper.model.LoadStatus;
import com.handsomezhou.xdesktophelper.model.PartnerView;
import com.handsomezhou.xdesktophelper.model.SearchMode;
import com.handsomezhou.xdesktophelper.util.ViewUtil;
import com.handsomezhou.xdesktophelper.util.XfyunErrorCodeUtil;
import com.handsomezhou.xdesktophelper.view.CustomViewPager;
import com.handsomezhou.xdesktophelper.view.TopTabView;
import com.handsomezhou.xdesktophelper.xfyun.setting.IatSettings;
import com.handsomezhou.xdesktophelper.xfyun.util.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainFragment extends BaseFragment implements OnAppInfoLoad, OnAppStartRecordLoad,OnAppSettingInfoLoad,
        OnTabChange ,OnT9SearchFragment,QwertySearchFragment.OnQwertySearchFragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private List<PartnerView> mPartnerViews;
    private TopTabView mTopTabView;
    private CustomViewPager mCustomViewPager;
    private CustomPartnerViewPagerAdapter mCustomPartnerViewPagerAdapter;
    private BaseProgressDialog mBaseProgressDialog;
    private PopupWindow mSearchModeSwitchTipsPw;

    /*start: xfyun voice input*/
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private Toast mToast;
    private SharedPreferences mSharedPreferences;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    /*end: xfyun voice input*/

    private boolean mVoiceSearch=false;
    private String mVoiceText=null;

    @Override
    public void onResume() {
        /*Log.i(TAG, "appStartRecords.size()="
                + AppInfoHelper.getInstance().getBaseAllAppInfos().size());

        for (AppInfo asr : AppInfoHelper.getInstance().getBaseAllAppInfos()) {
            Log.i(TAG, asr.getLabel() + ":" + ":" + asr.getCommonWeights());

        }*/
       // mTopTabView.setCurrentTabItem(SettingsHelper.getInstance().getSearchMode());
        mCustomViewPager.setCurrentItem(getPartnerViewItem(SettingsHelper.getInstance().getSearchMode()));
        refreshView();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //退出时释放连接
        if(null!=mIat){
            mIat.cancel();
            mIat.destroy();
        }
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        initSpeechRecognizer();
        mPartnerViews = new ArrayList<PartnerView>();
        /* start: T9 search view */
        T9SearchFragment t9SearchFragment=  new T9SearchFragment();
        t9SearchFragment.setOnT9SearchFragment(this);

        PartnerView t9PartnerView = new PartnerView(SearchMode.T9, t9SearchFragment);
        mPartnerViews.add(t9PartnerView);

        /* end: T9 search view */

        /* start: Qwerty search view */
        QwertySearchFragment qwertySearchFragment=  new QwertySearchFragment();
        qwertySearchFragment.setOnQwertySearchFragment(this);
        PartnerView qwertyPartnerView = new PartnerView(SearchMode.QWERTY,
                qwertySearchFragment);
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
        AppInfoHelper.getInstance().qwertySearch(null);
        AppInfoHelper.getInstance().t9Search(null,false);
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
           // Log.i(TAG, "onAppStartRecordSuccess before");
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

    /*start: OnT9SearchFragment*/
    @Override
    public void onT9SearchVoiceInput() {
        //ToastUtil.toastLengthshort(getContext(),"onT9SearchVoiceInput main");
        startVoiceInput();
    }
    /*end: OnT9SearchFragment*/

    /*start: OnQwertySearchFragment*/
    @Override
    public void onQwertySearchVoiceInput() {
        //ToastUtil.toastLengthshort(getContext(),"onQwertySearchVoiceInput main");
        startVoiceInput();
    }
    /*end: OnQwertySearchFragment*/

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
            ColorDrawable dw = new ColorDrawable(getContext().getResources().getColor(R.color.grey21_transparent));
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

    public boolean isVoiceSearch() {
        return mVoiceSearch;
    }

    public void setVoiceSearch(boolean voiceSearch) {
        mVoiceSearch = voiceSearch;
    }

    public String getVoiceText() {
        return mVoiceText;
    }

    public void setVoiceText(String voiceText) {
        mVoiceText = voiceText;
    }

    private void refreshView() {
        showTopTabView(SettingsHelper.getInstance().getSearchMode());
        Object currentTab = mTopTabView.getCurrentTab();
        int itemIndex = getPartnerViewItem(currentTab);
        Fragment fragment = mPartnerViews.get(itemIndex).getFragment();
        switch ((SearchMode) currentTab) {
            case T9:
                if (fragment instanceof T9SearchFragment) {
                    if(true==isVoiceSearch()){
                        ((T9SearchFragment) fragment).voiceSearch(getVoiceText());
                    }else {
                        ((T9SearchFragment) fragment).search();
                    }
                    ((T9SearchFragment) fragment).refreshView();
                }
                break;
            case QWERTY:
               
                if (fragment instanceof QwertySearchFragment) {
                    if(true==isVoiceSearch()){
                        ((QwertySearchFragment) fragment).voiceSearch(getVoiceText());
                    }else {
                        ((QwertySearchFragment) fragment).search();
                    }
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

    /*start: xfyun voice input*/
    private void startVoiceInput(){
        // 移动数据分析，收集开始听写事件
        //FlowerCollector.onEvent(getActivity(), "iat_recognize");
        int ret = 0; // 函数调用返回值
        mIatResults.clear();
        // 设置参数
        setParam();
        boolean isShowDialog = mSharedPreferences.getBoolean(
                getString(R.string.pref_key_iat_show), true);
        if (isShowDialog) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            showTip(getString(R.string.text_begin));
        } else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败,错误码：" + ret);
            } else {
                showTip(getString(R.string.text_begin));
            }
        }



    }

    private void initSpeechRecognizer(){
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);

        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getActivity(), mInitListener);

        mSharedPreferences = getContext().getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 参数设置
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "0"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            String voiceText=parseResult(results);
            if(true==isLast) {
                setVoiceSearch(true);
                setVoiceText(voiceText);
                //ToastUtil.toastLengthshort(getContext(), "2" + voiceText);
                refreshView();
                setVoiceSearch(false);
            }
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {

           // showTip(error.getPlainDescription(true));
            dealSpeechError(error);
        }

    };

    private void dealSpeechError(SpeechError error){
        do {
            if(null==error){
                break;
            }
            int errorCode=error.getErrorCode();
            if(errorCode==ErrorCode.MSP_ERROR_NO_DATA){
                break;
            }
            String xfyunErrorCodeDescription= XfyunErrorCodeUtil.getXfyunErrorCodeDescription(getContext(),errorCode);
            //  showTip(error.getPlainDescription(true));
            showTip(xfyunErrorCodeDescription);
        }while (false);

        return;
    }

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。

            //showTip(error.getPlainDescription(true));
            dealSpeechError(error);
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            String voiceText=parseResult(results);
         //   ToastUtil.toastLengthshort(getContext(),"1"+voiceText);
            if (isLast) {
                setVoiceSearch(true);
                setVoiceText(voiceText);
                refreshView();
                setVoiceSearch(false);
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private String parseResult(RecognizerResult results) {
        String iatResultStr=null;
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        iatResultStr=resultBuffer.toString();

        return  iatResultStr;
    }
    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /*end: xfyun voice input*/
}
