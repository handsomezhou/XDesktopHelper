package com.handsomezhou.xdesktophelper.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;

public class MenuPositionModeSp {
	private static final String TAG="MenuPositionModeSp";
	private static final String MENU_POSITION_MODE="MENU_POSITION_MODE";
	
	public static MenuPositionMode getMenuPositionMode(){
		MenuPositionMode menuPositionModel=MenuPositionMode.LEFT;
		
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		String menuPositionModelStr=sp.getString(MENU_POSITION_MODE,MenuPositionMode.LEFT.toString());
		
		if(menuPositionModelStr.equals(MenuPositionMode.LEFT.toString())){
			menuPositionModel=MenuPositionMode.LEFT;
		}else{
			menuPositionModel=MenuPositionMode.RIGHT;
		}
		return menuPositionModel;
	}
	
	public static void saveMenuPositionMode(MenuPositionMode menuPositionMode){
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(MENU_POSITION_MODE, menuPositionMode.toString());
		editor.commit();
	}
}

