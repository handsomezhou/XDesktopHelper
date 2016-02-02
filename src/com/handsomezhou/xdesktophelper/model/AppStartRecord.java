
package com.handsomezhou.xdesktophelper.model;

public class AppStartRecord {
    private String mPackageName;
    private long mStartTime;

    public AppStartRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AppStartRecord(String packageName, long startTime) {
        super();
        mPackageName = packageName;
        mStartTime = startTime;
    }


    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }

}
