package com.handsomezhou.xdesktophelper.util;

import java.util.Random;

import android.text.TextUtils;

import com.handsomezhou.xdesktophelper.constant.Constant;


public class CommonUtil {
	/**
	 * get double compare  result 
	 * @param result
	 * @return
	 */
	public static int compare(double result){
		return (result>Constant.ZERO_OF_DOUBLE_BOUNDARY)?(1):((result<(-Constant.ZERO_OF_DOUBLE_BOUNDARY))?(-1):(0));
	}
	
	/**
	 * get double compare  result 
	 * @param result
	 * @return
	 */
	public static int compare(long result){
		return (result>Constant.ZERO_OF_LONG)?(1):((result<Constant.ZERO_OF_LONG)?(-1):(0));
	}
	
	/**
	 * Judge whether the value is true
	 * @param value
	 * @return
	 */
	public static boolean isTrue(int value){
	    return (Constant.ZERO_OF_INTEGER!=value);
	}
	
	/**
	 * Judge whether the value is false
	 * @param value
	 * @return
	 */
	public static boolean isFalse(int value){
	    return !isTrue(value);
	}
	
	/**
	 * switch value of int to value of boolean
	 * @param value
	 * @return
	 */
	public static boolean getBoolean(int value){
	    
	    return (Constant.ZERO_OF_INTEGER!=value);
	}
	
	/**
	 * switch value of boolean to value of int
	 * @param value
	 * @return
	 */
	public static int getInteger(boolean value){
	    return (false==value)?(Constant.FALSE):(Constant.TRUE);
	}
	
	/**
	 * judge  whether the value is zero
	 * @param value
	 * @return
	 */
	public static boolean isZero(double value){
		boolean isZero=false;
		if((value>-Constant.ZERO_OF_DOUBLE_BOUNDARY)&&(value<Constant.ZERO_OF_DOUBLE_BOUNDARY)){
			isZero=true;
		}
		return isZero;
	}
	
	public static boolean isEmpty(String value){
        boolean empty=true;
        
        if(value == null || value.length() <= 0){
            empty=true;
        }else{
            empty=false;
        }
        
        return empty;
    }
    
	public static boolean isEmptyString(String value){
		 boolean empty=true;
	        
	        if(TextUtils.isEmpty(value)||value.equals(Constant.NULL)){
	            empty=true;
	        }else{
	            empty=false;
	        }
	        
	        return empty;
	}
	
    /**
     * whether the value in the all-close range [minValue, MaxValue].
     * 
     * @param minValue
     * @param MaxValue
     * @param value
     * @return
     */
    public static boolean isInRange(int minValue,int MaxValue,int value){
        boolean inRange=false;
        int min=Math.min(minValue, MaxValue);
        int max=Math.max(minValue, MaxValue);
        if(value>=min&&(value<=max)){
            inRange=true;
        }
        return inRange;
    }
    
    /**
     * Returns a pseudo-random uniformly distributed int in the all-close range [min, max].
     * @param min
     * @param max
     * @return
     */
    public static int random(int min,int max){
        int minValue=min;
        int maxValue=max;
        
        if(min>max){
            minValue=max;
            maxValue=min;
        }
        
        Random random = new Random();

        int randomNum = random.nextInt(maxValue+1)%(maxValue-minValue+1) + minValue;
        
        return randomNum;
    }

}

