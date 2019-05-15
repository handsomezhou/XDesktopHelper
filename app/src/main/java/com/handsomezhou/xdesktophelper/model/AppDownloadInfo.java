package com.handsomezhou.xdesktophelper.model;

public class AppDownloadInfo {
	public static String KEY_ID="id";
	public static String KEY_APP_MARKET="app_market";
	public static String KEY_DOWNLOAD_ADDRESS="download_address";
	
	private int mId;
	private String mAppMarket;
	private String mDownloadAddress;

	public AppDownloadInfo() {
		super();

	}

	public AppDownloadInfo(int id, String appMarket, String downloadAddress) {
		super();
		mId = id;
		mAppMarket = appMarket;
		mDownloadAddress = downloadAddress;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getAppMarket() {
		return mAppMarket;
	}

	public void setAppMarket(String appMarket) {
		mAppMarket = appMarket;
	}

	public String getDownloadAddress() {
		return mDownloadAddress;
	}

	public void setDownloadAddress(String downloadAddress) {
		mDownloadAddress = downloadAddress;
	}

}
