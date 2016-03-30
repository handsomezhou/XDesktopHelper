package com.handsomezhou.xdesktophelper.helper;

import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SearchModeSp;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SearchModeSwitchTipsSp;
import com.handsomezhou.xdesktophelper.model.SearchMode;

public class SettingsHelper {
	private static final String TAG = SettingsHelper.class.getSimpleName();

	private static SettingsHelper mInstance;

	private SearchMode mSearchMode;
	private boolean mSearchModeSwitchTips=true;

	
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
		mSearchMode = SearchModeSp.getSearchMode();
		mSearchModeSwitchTips=SearchModeSwitchTipsSp.isSearchModeSwitchTips();
		return;
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

	
	
	
	
}
