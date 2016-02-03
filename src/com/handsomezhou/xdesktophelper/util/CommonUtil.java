package com.handsomezhou.xdesktophelper.util;

import com.handsomezhou.xdesktophelper.model.Constant;

public class CommonUtil {
	/**
	 * get double compare  result 
	 * @param result
	 * @return
	 */
	public static int compare(double result){
		return (result>Constant.ZERO_OF_DOUBLE)?(1):((result<(-Constant.ZERO_OF_DOUBLE))?(-1):(0));
	}
	
	/**
	 * get double compare  result 
	 * @param result
	 * @return
	 */
	public static int compare(long result){
		return (result>Constant.ZERO_OF_LONG)?(1):((result<Constant.ZERO_OF_LONG)?(-1):(0));
	}
}
