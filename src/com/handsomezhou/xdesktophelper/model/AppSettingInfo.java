package com.handsomezhou.xdesktophelper.model;

public class AppSettingInfo {
	private String mKey;
	private long mSetToTop;

	public AppSettingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppSettingInfo(String key, long setToTop) {
		super();
		mKey = key;
		mSetToTop = setToTop;
	}

	public String getKey() {
		return mKey;
	}

	public void setKey(String key) {
		mKey = key;
	}

	public long getSetToTop() {
		return mSetToTop;
	}

	public void setSetToTop(long setToTop) {
		mSetToTop = setToTop;
	}

}
