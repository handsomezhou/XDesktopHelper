package com.handsomezhou.xdesktophelper.util;

public class AppCommonWeightsUtil {
    public static final long COMMON_WEIGHTS_DEFAULT=0;
    public static final int WEIGHTS_COUNT=7;
    public static long getCommonWeights(long currentTimeMs,long startTimeMs){
        long weight=COMMON_WEIGHTS_DEFAULT;
        long intervalTimeMs=currentTimeMs-startTimeMs;
        do{
            if(intervalTimeMs<=0){
                weight=COMMON_WEIGHTS_DEFAULT;
                break;
            }
            
            long intervalday=intervalTimeMs/(TimeUtil.HOUR_PER_DAY*TimeUtil.MINUTE_PER_HOUR*TimeUtil.SECOND_PER_MINUTE*TimeUtil.MILLISECOND_PER_SECOND);
            if(intervalday>=WEIGHTS_COUNT){
                weight=COMMON_WEIGHTS_DEFAULT;
                break;
            }
            
            weight=(WEIGHTS_COUNT-intervalday);
        }while(false);
        return weight;
    }
    
    public static long getCommonWeights(long startTimeMs){
        
        return getCommonWeights(System.currentTimeMillis(), startTimeMs);
    }
}
