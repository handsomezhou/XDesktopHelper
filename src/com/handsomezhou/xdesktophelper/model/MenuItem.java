package com.handsomezhou.xdesktophelper.model;

public class MenuItem {
	private String mTitle;
	private int mTitleColor;
	private int mIcon;
	
	
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

/*	public MenuItem(String title, int icon) {
		super();
		mTitle = title;
		mIcon = icon;
	}*/
	
	public MenuItem(String title, int titleColor, int icon) {
		super();
		mTitle = title;
		mTitleColor = titleColor;
		mIcon = icon;
	}

	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public int getTitleColor() {
		return mTitleColor;
	}

	public void setTitleColor(int titleColor) {
		mTitleColor = titleColor;
	}
	
	public int getIcon() {
		return mIcon;
	}
	
	public void setIcon(int icon) {
		mIcon = icon;
	}

}
