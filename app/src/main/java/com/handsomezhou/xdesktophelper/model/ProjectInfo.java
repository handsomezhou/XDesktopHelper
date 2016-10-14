package com.handsomezhou.xdesktophelper.model;

/**
 * Reference Project Information
 * 
 * @author handsomezhou
 *
 */
public class ProjectInfo implements Cloneable {
	public static String KEY_PROJECT_NAME="project_name";
	public static String KEY_PROJECT_ADDRESS="project_address";
	public static String KEY_WHETHER_OPEN_SOURCE="whether_open_source";
	private String mProjectName;
	private String mProjectAddress;
	private boolean mWhetherOpenSource;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String getProjectName() {
		return mProjectName;
	}

	public void setProjectName(String projectName) {
		mProjectName = projectName;
	}

	public String getProjectAddress() {
		return mProjectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		mProjectAddress = projectAddress;
	}

	public boolean isWhetherOpenSource() {
		return mWhetherOpenSource;
	}

	public void setWhetherOpenSource(boolean whetherOpenSource) {
		mWhetherOpenSource = whetherOpenSource;
	}

}
