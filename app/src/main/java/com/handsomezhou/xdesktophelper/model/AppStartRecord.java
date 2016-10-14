
package com.handsomezhou.xdesktophelper.model;

public class AppStartRecord {
	private String mKey;
    private long mStartTime;

   

    public AppStartRecord() {
        super();
       setStartTime(0);
    }

    public AppStartRecord(String key, long startTime) {
        super();
        mKey = key;
        mStartTime = startTime;

    }

    public String getKey() {
		return mKey;
	}

	public void setKey(String key) {
		mKey = key;
	}

	public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }

}
