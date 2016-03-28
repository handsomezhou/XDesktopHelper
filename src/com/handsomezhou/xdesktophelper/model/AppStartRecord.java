
package com.handsomezhou.xdesktophelper.model;

public class AppStartRecord {
    private String mPackageName;
    private long mStartTime;
    private long mSetToTop;

   

    public AppStartRecord() {
        super();
       setStartTime(0);
       setSetToTop(0);
    }

    public AppStartRecord(String packageName, long startTime, long setToTop) {
        super();
        mPackageName = packageName;
        mStartTime = startTime;
        mSetToTop=setToTop;
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

    public long getSetToTop() {
        return mSetToTop;
    }

    public void setSetToTop(long setToTop) {
        mSetToTop = setToTop;
    }
}
