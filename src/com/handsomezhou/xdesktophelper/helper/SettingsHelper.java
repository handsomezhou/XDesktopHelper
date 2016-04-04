package com.handsomezhou.xdesktophelper.helper;

import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.ExitAppPromptModelSp;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.MenuPositionModeSp;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SearchModeSp;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SearchModeSwitchTipsSp;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SmartSortingSp;
import com.handsomezhou.xdesktophelper.model.ExitAppPromptMode;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;
import com.handsomezhou.xdesktophelper.model.SearchMode;

public class SettingsHelper {
	private static final String TAG = SettingsHelper.class.getSimpleName();

	private static SettingsHelper mInstance;
	private MenuPositionMode mMenuPositionMode;

	private SearchMode mSearchMode;
	private boolean mSearchModeSwitchTips = true;
	private boolean mSmartSorting;
	private ExitAppPromptMode mExitAppPromptMode;



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
		mSearchModeSwitchTips = SearchModeSwitchTipsSp.isSearchModeSwitchTips();
		mSmartSorting=SmartSortingSp.isSmartSorting();
		mExitAppPromptMode = ExitAppPromptModelSp.getExitAppPromptMode();
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
		SearchModeSwitchTipsSp.saveSearchMode(searchModeSwitchTips);
		mSearchModeSwitchTips = searchModeSwitchTips;
	}

	public ExitAppPromptMode getExitAppPromptMode() {
		return mExitAppPromptMode;
	}

	public void setExitAppPromptMode(ExitAppPromptMode exitAppPromptMode) {
		ExitAppPromptModelSp.saveExitAppPromptMode(exitAppPromptMode);
		mExitAppPromptMode = exitAppPromptMode;
	}
	
	public boolean isSmartSorting() {
		return mSmartSorting;
	}

	public void setSmartSorting(boolean smartSorting) {
		SmartSortingSp.saveSmartSorting(smartSorting);
		mSmartSorting = smartSorting;
	}


}
