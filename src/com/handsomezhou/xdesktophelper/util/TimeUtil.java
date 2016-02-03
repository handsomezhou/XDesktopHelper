package com.handsomezhou.xdesktophelper.util;

import java.util.Calendar;

public class TimeUtil {
    public static final int HOUR_PER_DAY = 24;              //1day=24h
    public static final int MINUTE_PER_HOUR = 60;           //1h=60min
    public static final int SECOND_PER_MINUTE=60;           //1min=60s
    public static final int MILLISECOND_PER_SECOND=1000;    //1s=1000ms
    
    /**
     * {@link Calendar#YEAR}
     * @return
     */
    public static int getYear(){
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        
        return year;
    }
    

    /**
     * {@link Calendar#MONTH}
     * @return
     */
    public static int getMonth(){
        Calendar calendar = Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        
        return month;
    }
    
    /**
     * get days since 1970.1.1 to timeInMillis when timeInMillis>=0;
     * get days since 1970.1.1 to now when timeInMillis<0.
     * @return
     */
    public static int getDaySince1970January1(long timeInMillis){
        long milliSecond=timeInMillis;
        if(timeInMillis<0){
            Calendar calendar = Calendar.getInstance();
            milliSecond=calendar.getTimeInMillis();
        }
        
        int days=(int) (milliSecond/TimeUtil.MILLISECOND_PER_SECOND/TimeUtil.SECOND_PER_MINUTE/TimeUtil.MINUTE_PER_HOUR/TimeUtil.HOUR_PER_DAY);
        
        return days;
    }
    
    /**
     * {@link Calendar#DAY_OF_MONTH}
     * @return
     */
    public static int getDayOfMonth(){
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        
        return dayOfMonth;
    }
    
    /**
     * {@link Calendar#DAY_OF_YEAR}
     * @return
     */
    public static int getDayOfYear(){
        Calendar calendar = Calendar.getInstance();
        int dayOfYear=calendar.get(Calendar.DAY_OF_YEAR);
        
        return dayOfYear;
    }
    
    /**
     * {@link Calendar#HOUR_OF_DAY}
     * @return
     */
    public static int getHourOfDay(){
        Calendar calendar = Calendar.getInstance();
        int hour_of_day=calendar.get(Calendar.HOUR_OF_DAY);
        
        return hour_of_day;
    }
    
    /**
     * {@link Calendar#MINUTE}
     * @return
     */
    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        
        int minute=calendar.get(Calendar.MINUTE);
        
        return minute;
    }

    /**
     *  Indicating the minute of the day.
     *  E.g., at 10:04:15.250 PM the {@code TimeUtil#getMinuteOfDay()} is (22*60+04).
     * @return
     */
    public static int getMinuteOfDay(){
        int minuteOfDay=(TimeUtil.getHourOfDay()*TimeUtil.MINUTE_PER_HOUR)+TimeUtil.getMinute();
        
        return minuteOfDay;
    }
    
    /**
     * {@link Calendar#SECOND}
     * @return
     */
    public static int getSecond(){
        Calendar calendar = Calendar.getInstance();
        
        int second=calendar.get(Calendar.SECOND);
        
        return second;
    }
    
    /**
     *  Indicating the second of the day.
     *  E.g., at 10:04:15.250 PM the {@code TimeUtil#getSecondOfDay()} is (22*60+04)*60+15.
     * @return
     */
    public static int getSecondOfDay(){
        int secondOfDay=(TimeUtil.getMinuteOfDay()*TimeUtil.SECOND_PER_MINUTE)+TimeUtil.getSecond();
        
        return secondOfDay;
    }
    
    /**
     * {@link Calendar#MILLISECOND}
     * @return
     */
    public static int getMilliSecond(){
        Calendar calendar = Calendar.getInstance();
        
        int milliSecond=calendar.get(Calendar.MILLISECOND);
        
        return milliSecond;
    }
    
    /**
     *  Indicating the millisecond of the day.
     *  E.g., at 10:04:15.250 PM the {@code TimeUtil#getMilliSecondOfDay()} is ((22*60+04)*60+15)*1000+250.
     * @return
     */
    public static int getMilliSecondOfDay(){
        int milliSecondOfDay=(TimeUtil.getSecondOfDay()*TimeUtil.MILLISECOND_PER_SECOND)+TimeUtil.getMilliSecond();
        
        return milliSecondOfDay;
    }
}
