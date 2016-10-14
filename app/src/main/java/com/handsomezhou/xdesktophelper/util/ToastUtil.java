package com.handsomezhou.xdesktophelper.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void toast(Context context,int resId,int duration){
		Toast.makeText(context, resId, duration).show();
	}
	
	public static void toast(Context context,String text,int duration){
		Toast.makeText(context, text, duration).show();
	}
	
	public static void toastLengthshort(Context context,String text){
		ToastUtil.toast(context, text, Toast.LENGTH_SHORT);
	}
	
	public static void toastLengthLong(Context context,String text){
		ToastUtil.toast(context, text, Toast.LENGTH_LONG);
	}
	
	public static void toastLengthshort(Context context,int resId){
		ToastUtil.toast(context, resId, Toast.LENGTH_SHORT);
	}
	
	public static void toastLengthLong(Context context,int resId){
		ToastUtil.toast(context, resId, Toast.LENGTH_LONG);
	}
}
