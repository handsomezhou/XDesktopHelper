package com.handsomezhou.xdesktophelper.util;


import com.handsomezhou.xdesktophelper.constant.Constant;
import com.handsomezhou.xdesktophelper.constant.TimePatternConstant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhoujq.
 */

public class TimeUtil {
    public static final int HOUR_PER_DAY = 24;              //1day=24h
    public static final int MINUTE_PER_HOUR = 60;           //1h=60min
    public static final int SECOND_PER_MINUTE=60;           //1min=60s
    public static final int MILLISECOND_PER_SECOND=1000;    //1s=1000ms
    /**
     * millisecond to second
     * 
     * @param ms
     * @return
     */
    public static int ms2sec(long ms){
        int sec=(int) (ms/TimeUtil.MILLISECOND_PER_SECOND);
        
        return sec;
    }
    
    /**
     * millisecond to minute
     * @param ms
     * @return
     */
    public static int ms2min(long ms){
        int min=(int) (ms/TimeUtil.MILLISECOND_PER_SECOND/TimeUtil.SECOND_PER_MINUTE);
        
        return min;
    }
    
    /**
     * millisecond to hour
     * @param ms
     * @return
     */
    public static int ms2hour(long ms){
        int hour=(int) (ms/TimeUtil.MILLISECOND_PER_SECOND/TimeUtil.SECOND_PER_MINUTE/TimeUtil.MINUTE_PER_HOUR);
        
        return hour;
    }

    /**
     * millisecond to day
     * @param ms
     * @return
     */
    public static int ms2day(long ms){
        int day=(int)(ms/TimeUtil.MILLISECOND_PER_SECOND/TimeUtil.SECOND_PER_MINUTE/TimeUtil.MINUTE_PER_HOUR/TimeUtil.HOUR_PER_DAY);
        return day;
    }

    /**
     * second to millisecond
     * @param sec
     * @return
     */
    public static long sec2ms(int sec){
        long ms=(sec*TimeUtil.MILLISECOND_PER_SECOND);
        return ms;
    }
    
    /**
     * second to minute
     * @param sec
     * @return
     */
    public static int sec2min(int sec){
        int min=sec/TimeUtil.SECOND_PER_MINUTE;
        return min;
    }
    
    /**
     * second to hour
     * @param sec
     * @return
     */
    public static int sec2hour(int sec){
        int hour=sec/TimeUtil.SECOND_PER_MINUTE/TimeUtil.MINUTE_PER_HOUR;
        return hour;
    }

    /**
     * second to day
     * @param sec
     * @return
     */
    public static int sec2day(int sec){
        int day=sec/TimeUtil.SECOND_PER_MINUTE/TimeUtil.MINUTE_PER_HOUR/TimeUtil.HOUR_PER_DAY;
        return day;
    }

    /**
     * minute to millisecond
     * @param min
     * @return
     */
    public static long min2ms(int min){
        long ms=((long) min)*TimeUtil.SECOND_PER_MINUTE*TimeUtil.MILLISECOND_PER_SECOND;
        return ms;
    }
    
    /**
     * minute to second
     * @param min
     * @return
     */
    public static int min2sec(int min){
        int sec=min*TimeUtil.SECOND_PER_MINUTE;
        return sec;
    }
    
    /**
     * minute to hour
     * @param min
     * @return
     */
    public static int min2hour(int min){
        int hour=min/TimeUtil.MINUTE_PER_HOUR;
        
        return hour;
    }

    /**
     * minute to day
     * @param min
     * @return
     */
    public static int min2day(int min){
        int day=min/TimeUtil.MINUTE_PER_HOUR/TimeUtil.HOUR_PER_DAY;

        return day;
    }
    
    /**
     * hour to millisecond
     * @param hour
     * @return
     */
    public static long hour2ms(int hour){
        long ms=((long) hour)*TimeUtil.MINUTE_PER_HOUR*TimeUtil.SECOND_PER_MINUTE*TimeUtil.MILLISECOND_PER_SECOND;
        
        return ms;
    }
    
    /**
     * hour to second
     * @param hour
     * @return
     */
    public static int hour2sec(int hour){
        int sec=hour*TimeUtil.MINUTE_PER_HOUR*TimeUtil.SECOND_PER_MINUTE;
        
        return sec;
    }
    
    /**
     * hour to minute
     * @param hour
     * @return
     */
    public static int hour2min(int hour){
        int min=hour*TimeUtil.MINUTE_PER_HOUR;
        
        return min;
    }

    /**
     * hour to day
     * @param hour
     * @return
     */
    public static int hour2day(int hour){
        int day=hour/TimeUtil.HOUR_PER_DAY;

        return day;
    }


    /**
     * day to ms
     * @param day
     * @return
     */
    public static long day2ms(int day){
        long ms=((long)day)*TimeUtil.HOUR_PER_DAY*TimeUtil.MINUTE_PER_HOUR*TimeUtil.SECOND_PER_MINUTE*TimeUtil.MILLISECOND_PER_SECOND;

        return ms;
    }

    /**
     * day to sec
     * @param day
     * @return
     */
    public static int day2sec(int day){
        int sec=day*TimeUtil.HOUR_PER_DAY*TimeUtil.MINUTE_PER_HOUR*TimeUtil.SECOND_PER_MINUTE;

        return sec;
    }

    /**
     * day to min
     * @param day
     * @return
     */
    public static int day2min(int day){
        int min=day*TimeUtil.HOUR_PER_DAY*TimeUtil.MINUTE_PER_HOUR;

        return min;
    }

    /**
     * day to hour
     * @param day
     * @return
     */
    public static int day2hour(int day){
        int hour=day*TimeUtil.HOUR_PER_DAY;

        return hour;
    }

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
     * {@link Calendar#YEAR}
     * @param ms
     * @return
     */
    public static int getYear(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#MONTH}
     * @return
     */
    public static int getMonth(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        int month=calendar.get(Calendar.MONTH);

        return month;
    }
    
    
    /**
     * {@link Calendar#DAY_OF_WEEK}
     * @return
     */
    public static int getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.DAY_OF_WEEK);
        
        return year;
    }

    /**
     * {@link Calendar#DAY_OF_WEEK}
     * @return
     */
    public static int getDayOfWeek(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        int year=calendar.get(Calendar.DAY_OF_WEEK);

        return year;
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
     * {@link Calendar#DAY_OF_MONTH}
     * @return
     */
    public static int getDayOfMonth(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#DAY_OF_YEAR}
     * @return
     */
    public static int getDayOfYear(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#HOUR_OF_DAY}
     * @return
     */
    public static int getHourOfDay(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#MINUTE}
     * @return
     */
    public static int getMinute(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#SECOND}
     * @return
     */
    public static int getSecond(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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
     * {@link Calendar#MILLISECOND}
     * @return
     */
    public static int getMilliSecond(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

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

    /**
     * get format time
     * @param ms
     * @param pattern for example:"MM-dd"
     * @return
     */
    public static String getFormatTime(long ms, String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return  simpleDateFormat.format(ms);
    }


    /**
     * get log time
     * @return
     */
    public static String getLogTime(){
        return  Constant.NULL_STRING+Constant.LEFT_SQUARE_BRACKETS+TimeUtil.getFormatTime(System.currentTimeMillis(), TimePatternConstant.LOG_TIME_PATTERN)+ Constant.RIGHT_SQUARE_BRACKETS;
    }

    /**
     *
     * @param srcMs
     * @param dstMs
     * @return
     */
    public static boolean isSameDay(long srcMs,long dstMs){
        return (TimeUtil.ms2day(srcMs)==TimeUtil.ms2day(dstMs));
    }


    public static long getStartTimeOfToday(){
        return System.currentTimeMillis()- TimeUtil.getMilliSecondOfDay();
    }

    public static long getEndTimeMsOfToday(){
        return TimeUtil.getStartTimeOfToday()+TimeUtil.hour2ms(24);
    }
}
