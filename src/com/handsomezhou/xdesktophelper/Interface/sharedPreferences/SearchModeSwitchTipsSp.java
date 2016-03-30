package com.handsomezhou.xdesktophelper.Interface.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;

public class SearchModeSwitchTipsSp {
	private static final String SEARCH_MODE_SWITCH_TIPS="SEARCH_MODE_SWITCH_TIPS";
	
	public static boolean isSearchModeSwitchTips(){
	
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		
		boolean searchModeSwitchTips=sp.getBoolean(SEARCH_MODE_SWITCH_TIPS, true);

		
		return searchModeSwitchTips;
	}
	
	public static void saveSearchMode(boolean searchModeSwitchTips){
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putBoolean(SEARCH_MODE_SWITCH_TIPS,searchModeSwitchTips);
		editor.commit();
	}
}
