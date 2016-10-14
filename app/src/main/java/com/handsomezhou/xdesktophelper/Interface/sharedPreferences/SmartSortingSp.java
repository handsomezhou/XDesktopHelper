package com.handsomezhou.xdesktophelper.Interface.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.database.XDesktopHelperDatabase;
import com.handsomezhou.xdesktophelper.model.ExitAppPromptMode;

public class SmartSortingSp {
	private static final String TAG=SmartSortingSp.class.getSimpleName();
	private static final String SMART_SORTING="SMART_SORTING";
	
	public static boolean isSmartSorting(){
		
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean smartSorting=sp.getBoolean(SMART_SORTING, true);
		
		
		return smartSorting;
	}
	
	public static void saveSmartSorting(boolean smartSorting){
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putBoolean(SMART_SORTING, smartSorting);
		
		editor.commit();
	}
}
