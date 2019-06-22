package com.handsomezhou.xdesktophelper.baidu.aip.util;


import com.handsomezhou.xdesktophelper.util.CommonUtil;

import java.util.List;

/**
 * Created by handsomezhou on 2018/2/8.
 * 数字工具类
 */

public class NumberUtil {
    public static int getNumberSum(List<String> numbers){
        int numberSum=0;
        do{
            if(null==numbers||numbers.size()<=0){
                break;
            }

            for (String num:numbers) {
                numberSum=numberSum+getNumber(num);
            }
        }while (false);
        return numberSum;
    }

    public static int getNumber(String number){
        int num=0;
        do{
            if(true==CommonUtil.isEmpty(number)){
                break;
            }

            if(true==isNumber(number)){
                num= Integer.valueOf(number);
            }else {
                num=getConvertNumber(number);
            }

        }while (false);

        return num;
    }

    public static boolean isNumber(String number){
        boolean isNumber=false;
        try {
            Integer.valueOf(number);
            isNumber=true;
        }catch (NumberFormatException ex){
            isNumber=false;
        }

        return isNumber;
    }

    /**
     * 获取转换数字
     * @param number
     * @return
     */
    public static  int getConvertNumber(String number) {
        int num=0;
        do{
            if(CommonUtil.isEmpty(number)||number.length()<=0){
                break;
            }

            /**
             * 避免 三杯咖啡  number="三杯"类似这种情况
             */
            String singleNumber= String.valueOf(number.charAt(0));
            switch (singleNumber) {
                case "一":
                    num = 1;
                    break;
                case "二":
                case "两":
                    num = 2;
                    break;
                case "三":
                    num = 3;
                    break;
                case "四":
                    num = 4;
                    break;
                case "五":
                    num = 5;
                    break;
                case "六":
                    num = 6;
                    break;
                case "七":
                    num = 7;
                    break;
                case "八":
                    num = 8;
                    break;
                case "九":
                    num = 9;
                    break;
                case "十":
                    num = 10;
                    break;
                default:
                    break;
            }

        }while (false);


        return num;
    }
}
