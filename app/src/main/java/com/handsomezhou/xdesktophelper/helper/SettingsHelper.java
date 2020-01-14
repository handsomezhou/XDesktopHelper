package com.handsomezhou.xdesktophelper.helper;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.sharedPreferences.MenuPositionModeSp;
import com.handsomezhou.xdesktophelper.sharedPreferences.SearchModeSp;
import com.handsomezhou.xdesktophelper.constant.MenuPositionMode;
import com.handsomezhou.xdesktophelper.constant.SearchMode;
import com.handsomezhou.xdesktophelper.util.SharedPreferencesUtil;

public class SettingsHelper {
	private static final String TAG = SettingsHelper.class.getSimpleName();
	private static final int FLOATING_WINDOW_POS_X=0;
	private static final int FLOATING_WINDOW_POS_Y=0;

	public static final String KEY_USER_GUIDE_TIPS="KEY_USER_GUIDE_TIPS";
	public static final String KEY_APP_SHARE_SHOW="KEY_APP_SHARE_SHOW";
	public static final String KEY_SEARCH_DATA_COUNT_SHOW="KEY_SEARCH_DATA_COUNT_SHOW";
	public static final String KEY_SMART_SORTING="KEY_SMART_SORTING";
	public static final String KEY_VOICE_SEARCH_ENABLE="KEY_VOICE_SEARCH_ENABLE";
	public static final String KEY_FLOATING_WINDOW_SHOW="KEY_FLOATING_WINDOW_SHOW";
	public static final String KEY_EXIT_APP_PROMPT="KEY_EXIT_APP_PROMPT";

	public static final String KEY_FLOATING_WINDOW_POS_X="KEY_FLOATING_WINDOW_POS_X";
	public static final String KEY_FLOATING_WINDOW_POS_Y="KEY_FLOATING_WINDOW_POS_Y";

	private static SettingsHelper mInstance;
	private MenuPositionMode mMenuPositionMode;

	private SearchMode mSearchMode;
	private boolean mUserGuideTips = true;
	private boolean mAppShareShow =true;
	private boolean mSearchDataCountShow=true;
	private boolean mVoiceSearchEnable=true;

	private boolean mSmartSorting;
	private boolean mFloatingWindowShow=true;
	private boolean mExitAppPrompt;

	private int mFloatingWindowPosX=0;
	private int mFloatingWindowPosY=0;



	public static SettingsHelper getInstance() {
		if (null == mInstance) {
			mInstance = new SettingsHelper();
		}

		return mInstance;
	}

	private SettingsHelper() {
		initSettingsHelper();
	}

	private void initSettingsHelper() {
		mMenuPositionMode = MenuPositionModeSp.getMenuPositionMode();
		mSearchMode = SearchModeSp.getSearchMode();
		mUserGuideTips = SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_USER_GUIDE_TIPS, true);
		mAppShareShow = SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_APP_SHARE_SHOW, true);
		mSearchDataCountShow = SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_SEARCH_DATA_COUNT_SHOW, true);
		mVoiceSearchEnable=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(),KEY_VOICE_SEARCH_ENABLE,true);
		mSmartSorting=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_SMART_SORTING, true);
		mFloatingWindowShow=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(),KEY_FLOATING_WINDOW_SHOW , false);
		mExitAppPrompt= SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_EXIT_APP_PROMPT, true);
		mFloatingWindowPosX= SharedPreferencesUtil.getInt(XDesktopHelperApplication.getContext(), KEY_FLOATING_WINDOW_POS_X, FLOATING_WINDOW_POS_X);
		mFloatingWindowPosY= SharedPreferencesUtil.getInt(XDesktopHelperApplication.getContext(), KEY_FLOATING_WINDOW_POS_Y, FLOATING_WINDOW_POS_Y);

		return;
	}

	public MenuPositionMode getMenuPositionMode() {
		return mMenuPositionMode;
	}

	public void setMenuPositionMode(MenuPositionMode menuPositionMode) {
		MenuPositionModeSp.saveMenuPositionMode(menuPositionMode);
		mMenuPositionMode = menuPositionMode;
	}

	public SearchMode getSearchMode() {
		return mSearchMode;
	}

	public void setSearchMode(SearchMode searchMode) {
		SearchModeSp.saveSearchMode(searchMode);
		mSearchMode = searchMode;
	}

	public boolean isUserGuideTips() {
		return mUserGuideTips;
	}

	public void setUserGuideTips(boolean userGuideTips) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_USER_GUIDE_TIPS, userGuideTips);
		mUserGuideTips = userGuideTips;
	}

	public boolean isAppShareShow() {
		return mAppShareShow;
	}

	public void setAppShareShow(boolean appShareShow) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_APP_SHARE_SHOW, appShareShow);
		mAppShareShow = appShareShow;
	}

	public boolean isSearchDataCountShow() {
		return mSearchDataCountShow;
	}

	public void setSearchDataCountShow(boolean searchDataCountShow) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_SEARCH_DATA_COUNT_SHOW, searchDataCountShow);
		mSearchDataCountShow = searchDataCountShow;
	}


	public boolean isVoiceSearchEnable() {
		return mVoiceSearchEnable;
	}

	public void setVoiceSearchEnable(boolean voiceSearchEnable) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_VOICE_SEARCH_ENABLE, voiceSearchEnable);
		mVoiceSearchEnable = voiceSearchEnable;
	}

	public boolean isSmartSorting() {
		return mSmartSorting;
	}

	public void setSmartSorting(boolean smartSorting) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_SMART_SORTING, smartSorting);
		mSmartSorting = smartSorting;
	}

	public boolean isFloatingWindowShow() {
		return mFloatingWindowShow;
	}

	public void setFloatingWindowShow(boolean floatingWindowShow) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(),KEY_FLOATING_WINDOW_SHOW, floatingWindowShow);
		mFloatingWindowShow = floatingWindowShow;
	}

	public boolean isExitAppPrompt() {
		return mExitAppPrompt;
	}

	public void setExitAppPrompt(boolean exitAppPrompt) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_EXIT_APP_PROMPT, exitAppPrompt);
		mExitAppPrompt = exitAppPrompt;
	}

	public int getFloatingWindowPosX() {
		return mFloatingWindowPosX;
	}

	public void setFloatingWindowPosX(int floatingWindowPosX) {
		SharedPreferencesUtil.putInt(XDesktopHelperApplication.getContext(), KEY_FLOATING_WINDOW_POS_X, floatingWindowPosX);
		mFloatingWindowPosX = floatingWindowPosX;
	}

	public int getFloatingWindowPosY() {
		return mFloatingWindowPosY;
	}

	public void setFloatingWindowPosY(int floatingWindowPosY) {
		SharedPreferencesUtil.putInt(XDesktopHelperApplication.getContext(), KEY_FLOATING_WINDOW_POS_Y, floatingWindowPosY);
		mFloatingWindowPosY = floatingWindowPosY;
	}
}
