package com.handsomezhou.xdesktophelper.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.sharedPreferences.MenuPositionModeSp;
import com.handsomezhou.xdesktophelper.fragment.MainFragment;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.model.MenuItem;
import com.handsomezhou.xdesktophelper.model.MenuItemIndex;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;
import com.handsomezhou.xdesktophelper.service.XDesktopHelperService;
import com.handsomezhou.xdesktophelper.view.ResideMenu;
import com.handsomezhou.xdesktophelper.view.ResideMenuItem;

@SuppressLint("ResourceAsColor")
public class MainActivity extends BaseSingleFragmentActivity implements
		OnClickListener {
	private static final String TAG = "MainActivity";
	private Context mContext;
	private MainFragment mMainFragment;
	private ResideMenu mResideMenu;
	private List<MenuItem> mMenuItems;
	private static final int DOUBLE_CLICK_EXIT_TIME_INTERVAL_MILLIS = 2000;// ms
	private static long mBackPressedTimeMillis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mContext = this;
		setFullScreen(false);
		initData();
		initView();
		initListener();
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected Fragment createFragment() {

		return mMainFragment = new MainFragment();
	}

	@Override
	protected boolean isRealTimeLoadFragment() {

		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		return mResideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onKeyDown(int keycode, KeyEvent e) {
		switch (keycode) {
		case KeyEvent.KEYCODE_MENU:
			enterMenu();
			return true;
		}

		return super.onKeyDown(keycode, e);
	}

	@Override
	public void onBackPressed() {
		if (mResideMenu.isOpened()) {
			mResideMenu.closeMenu();
		} else {
			if (SettingsHelper.getInstance().isExitAppPrompt() ==false) {
				runInBackgroud();
			} else {
				doubleClickExit();
			}

		}

	}

	@Override
	public void onClick(View v) {
		int index = (Integer) v.getTag();

		switch (index) {
		case MenuItemIndex.HOME_PAGE:
			enterHomePage();
			break;
		case MenuItemIndex.SETTINGS:
			enterSettings();
			break;
		case MenuItemIndex.ABOUT:
			enterAbout();
			break;
		case MenuItemIndex.MORE:
			enterMore();
			break;
		default:
			break;
		}

	}

	public ResideMenu getResideMenu() {
		return mResideMenu;
	}

	public void setResideMenu(ResideMenu resideMenu) {
		mResideMenu = resideMenu;
	}

	private void initData() {
		mMenuItems = new ArrayList<MenuItem>();
		int titleColor = mContext.getResources().getColor(R.color.white);
		/* Start: home page */
		MenuItem homePageMenuItem = new MenuItem(
				mContext.getString(R.string.home_page), titleColor,
				R.mipmap.home_page);
		mMenuItems.add(MenuItemIndex.HOME_PAGE, homePageMenuItem);
		/* End: home page */

		/* Start: settings */
		MenuItem settingsMenuItem = new MenuItem(
				mContext.getString(R.string.settings), titleColor,
				R.mipmap.settings);
		mMenuItems.add(MenuItemIndex.SETTINGS, settingsMenuItem);
		/* End: settings */

		/* Start: about */
		MenuItem aboutMenuItem = new MenuItem(
				mContext.getString(R.string.about), titleColor,
				R.mipmap.about);
		mMenuItems.add(MenuItemIndex.ABOUT, aboutMenuItem);
		/* End: about */

		/* start: more */
		/*
		 * MenuItem moreMenuItem = new MenuItem(
		 * mContext.getString(R.string.more), titleColor, R.drawable.more);
		 * mMenuItems.add(MenuItemIndex.MORE, moreMenuItem);
		 */
		/* end: more */
		return;
	}

	private void initView() {
		setUpMenu();
		return;
	}

	private void initListener() {

		return;
	}

	private void setUpMenu() {

		// attach to current activity;
		mResideMenu = new ResideMenu(this);
		mResideMenu.setUse3D(false);
		mResideMenu.setBackground(R.color.green);
		mResideMenu.attachToActivity(this);
		mResideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		mResideMenu.setScaleValue(0.6f);

		// create menu items;
		MenuPositionMode menuPositionMode = MenuPositionModeSp
				.getMenuPositionMode();
		createMenuItems(menuPositionMode);
	}

	private void createMenuItems(MenuPositionMode menuPositionMode) {
		int resideMenuDirection = ResideMenu.DIRECTION_LEFT;
		int disableDirection = ResideMenu.DIRECTION_RIGHT;

		if (menuPositionMode == MenuPositionMode.RIGHT) {
			resideMenuDirection = ResideMenu.DIRECTION_RIGHT;
			disableDirection = ResideMenu.DIRECTION_LEFT;
		} else {
			resideMenuDirection = ResideMenu.DIRECTION_LEFT;
			disableDirection = ResideMenu.DIRECTION_RIGHT;
		}

		// create menu items;
		for (int i = 0; i < mMenuItems.size(); i++) {
			ResideMenuItem item = new ResideMenuItem(this, mMenuItems.get(i)
					.getIcon(), mMenuItems.get(i).getTitle(), mMenuItems.get(i)
					.getTitleColor());
			item.setOnClickListener(this);
			item.setTag(i);
			mResideMenu.addMenuItem(item, resideMenuDirection);

		}

		// You can disable a direction by setting ->
		mResideMenu.setSwipeDirectionDisable(disableDirection);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			/*
			 * Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT)
			 * .show();
			 */
			Log.i(TAG, "Menu is opened!");
		}

		@Override
		public void closeMenu() {
			/*
			 * Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT)
			 * .show();
			 */
			Log.i(TAG, "Menu is closed!");
		}
	};

	private void doubleClickExit() {
		if (mBackPressedTimeMillis + DOUBLE_CLICK_EXIT_TIME_INTERVAL_MILLIS > System
				.currentTimeMillis()) {

			runInBackgroud();
		} else {
			String DoubleBackPressExitApp = mContext.getString(
					R.string.double_back_press_exit_app,
					mContext.getString(R.string.app_name));
			Toast.makeText(mContext, DoubleBackPressExitApp, Toast.LENGTH_SHORT)
					.show();
		}

		mBackPressedTimeMillis = System.currentTimeMillis();
	}

	private void runInBackgroud() {
		moveTaskToBack(true);
		XDesktopHelperService.startService(getApplicationContext());

	}

	private void enterMenu() {
		if (mResideMenu.isOpened()) {
			mResideMenu.closeMenu();
		} else {
			MenuPositionMode menuPositionMode = SettingsHelper.getInstance()
					.getMenuPositionMode();
			if (menuPositionMode == MenuPositionMode.RIGHT) {
				mResideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			} else {
				mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		}
	}

	private void enterHomePage() {
		if (mResideMenu.isOpened()) {
			mResideMenu.closeMenu();
		}
		return;
	}

	private void enterSettings() {
		Intent intent = new Intent(getContext(), SettingsActivity.class);
		startActivity(intent);
	}



	private void enterMore() {
		Toast.makeText(getContext(), "enterMore ", Toast.LENGTH_SHORT).show();
	}


	private void enterAbout() {
		Intent intent = new Intent(getContext(), AboutActivity.class);
		startActivity(intent);
	}
}
