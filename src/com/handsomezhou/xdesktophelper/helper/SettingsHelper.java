package com.handsomezhou.xdesktophelper.helper;

import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SearchModeSp;
import com.handsomezhou.xdesktophelper.model.SearchMode;

public class SettingsHelper {
	private static final String TAG = SettingsHelper.class.getSimpleName();

	private static SettingsHelper mInstance;

	private SearchMode mSearchMode;


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
		return;
	}

	
	public SearchMode getSearchMode() {
		return mSearchMode;
	}

	public void setSearchMode(SearchMode searchMode) {
		SearchModeSp.saveSearchMode(searchMode);
		mSearchMode = searchMode;
	}
	
	
	
	
}
