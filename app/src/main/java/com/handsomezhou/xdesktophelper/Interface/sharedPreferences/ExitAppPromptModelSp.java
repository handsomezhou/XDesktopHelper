package com.handsomezhou.xdesktophelper.Interface.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.database.XDesktopHelperDatabase;
import com.handsomezhou.xdesktophelper.model.ExitAppPromptMode;

public class ExitAppPromptModelSp {
	private static final String TAG="ExitAppPromptModelSp";
	private static final String EXIT_APP_PROMPT="EXIT_APP_PROMPT";
	
	public static ExitAppPromptMode getExitAppPromptMode(){
		ExitAppPromptMode exitAppPromptModel=ExitAppPromptMode.PROMPT_YES;
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		String exitAppPromptModelStr=sp.getString(EXIT_APP_PROMPT, ExitAppPromptMode.PROMPT_YES.toString());
		if(exitAppPromptModelStr.equals(ExitAppPromptMode.PROMPT_NO.toString())){
			exitAppPromptModel=ExitAppPromptMode.PROMPT_NO;
		}else{
			exitAppPromptModel=ExitAppPromptMode.PROMPT_YES;
		}
		
		return exitAppPromptModel;
	}
	
	public static void saveExitAppPromptMode(ExitAppPromptMode exitAppPromptMode){
		Context context = XDesktopHelperApplication.getContext();
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(EXIT_APP_PROMPT, exitAppPromptMode.toString());
		
		editor.commit();
	}
}
