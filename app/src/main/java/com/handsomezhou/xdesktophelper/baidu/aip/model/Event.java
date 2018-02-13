package com.handsomezhou.xdesktophelper.baidu.aip.model;

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
     * 内容
     */
    private String mContext;

    public Event() {

    }

    public Event(String action, String context) {
        mAction = action;
        mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public String getContext() {
        return mContext;
    }

    public void setContext(String context) {
        mContext = context;
    }
}
