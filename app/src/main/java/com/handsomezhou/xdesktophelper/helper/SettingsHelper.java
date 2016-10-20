package com.handsomezhou.xdesktophelper.helper;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.sharedPreferences.MenuPositionModeSp;
import com.handsomezhou.xdesktophelper.sharedPreferences.SearchModeSp;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;
import com.handsomezhou.xdesktophelper.model.SearchMode;
import com.handsomezhou.xdesktophelper.util.SharedPreferencesUtil;

public class SettingsHelper {
	private static final String TAG = SettingsHelper.class.getSimpleName();
	public static final String KEY_SEARCH_MODE_SWITCH_TIPS="KEY_SEARCH_MODE_SWITCH_TIPS";
	public static final String KEY_SMART_SORTING="KEY_SMART_SORTING";
	public static final String KEY_VOICE_SEARCH_ENABLE="KEY_VOICE_SEARCH_ENABLE";
	public static final String KEY_VOICE_START_APP="KEY_VOICE_START_APP";
	public static final String KEY_EXIT_APP_PROMPT="KEY_EXIT_APP_PROMPT";

	private static SettingsHelper mInstance;
	private MenuPositionMode mMenuPositionMode;

	private SearchMode mSearchMode;
	private boolean mSearchModeSwitchTips = true;
	private boolean mVoiceSearchEnable=true;
	private boolean mVoiceStartApp=true;
	private boolean mSmartSorting;
	private boolean mExitAppPrompt;



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
		mSearchModeSwitchTips = SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_SEARCH_MODE_SWITCH_TIPS, true);
		mVoiceSearchEnable=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(),KEY_VOICE_SEARCH_ENABLE,true);
		mVoiceStartApp=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(),KEY_VOICE_START_APP,true);
		mSmartSorting=SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_SMART_SORTING, true);
		mExitAppPrompt= SharedPreferencesUtil.getBoolean(XDesktopHelperApplication.getContext(), KEY_EXIT_APP_PROMPT, true);

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

	public boolean isSearchModeSwitchTips() {
		return mSearchModeSwitchTips;
	}

	public void setSearchModeSwitchTips(boolean searchModeSwitchTips) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_SEARCH_MODE_SWITCH_TIPS, searchModeSwitchTips);
		mSearchModeSwitchTips = searchModeSwitchTips;
	}

	public boolean isVoiceStartApp() {
		return mVoiceStartApp;
	}

	public void setVoiceStartApp(boolean voiceStartApp) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_VOICE_START_APP, voiceStartApp);
		mVoiceStartApp = voiceStartApp;
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

	public boolean isExitAppPrompt() {
		return mExitAppPrompt;
	}

	public void setExitAppPrompt(boolean exitAppPrompt) {
		SharedPreferencesUtil.putBoolean(XDesktopHelperApplication.getContext(), KEY_EXIT_APP_PROMPT, exitAppPrompt);
		mExitAppPrompt = exitAppPrompt;
	}
}
