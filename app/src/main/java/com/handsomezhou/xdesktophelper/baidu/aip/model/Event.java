package com.handsomezhou.xdesktophelper.baidu.aip.model;

import com.handsomezhou.xdesktophelper.baidu.aip.util.NumberUtil;

/**
 * Created by handsomezhou on 2018/2/13.
 * 事件
 */

public class Event {
    /**
     * 动作
     */
    private String mAction;

    /**
     * 数量词(数+单位)
     */
    private String mNum;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 内容
     */
    private String mContext;

    public Event() {

    }

    public Event(String action, String context) {
        mAction = action;
        mContext = context;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mAction='" + mAction + '\'' +
                ", mNum='" + mNum + '\'' +
                ", numvalue='" + getNumValue() + '\'' +
                ", mUnit='" + mUnit + '\'' +
                ", mContext='" + mContext + '\'' +
                '}';
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public String getNum() {
        return mNum;
    }

    public void setNum(String num) {
        mNum = num;
    }

    public int getNumValue(){
        return NumberUtil.getNumber(mNum);
    }
    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    public String getContext() {
        return mContext;
    }

    public void setContext(String context) {
        mContext = context;
    }


}
