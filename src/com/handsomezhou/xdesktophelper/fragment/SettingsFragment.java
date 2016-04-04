package com.handsomezhou.xdesktophelper.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.Interface.sharedPreferences.SmartSortingSp;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.model.ExitAppPromptMode;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;
import com.handsomezhou.xdesktophelper.model.SearchMode;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout.OnNavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.SegmentedGroup;
import com.handsomezhou.xdesktophelper.view.switchbutton.SwitchButton;

public class SettingsFragment extends BaseFragment implements
		OnNavigationBarLayout {
	private static final String TAG = SettingsFragment.class.getSimpleName();
	private static final int MAX_MENU_POSITION_MODE_SETTING_PROMPT_COUNT = 3;

	private NavigationBarLayout mNavigationBarLayout;

	private String mTitle;
	/* start: menu position mode */
	private SegmentedGroup mMenuPositionModeSegmented;
	private RadioButton mLeftRadioBtn;
	private RadioButton mRightRadioBtn;
	private static int mMenuPpsitionModeSettingPromptCount = 0;
	/* end: menu position mode */
	
	/* start: search mode */
	private SegmentedGroup mSearchModeSegmented;
	private RadioButton mT9RadioBtn;
	private RadioButton mQwertyRadioBtn;
	/* end: search mode */

	/* start : exit_app_prompt switch button */
	private SwitchButton mExitAppPromptSwitchBtn;
	/* end : exit_app_prompt switch button */
	private SwitchButton mSmartSortingSwitchBtn;



	
	@Override
	public void onResume() {
		refreshView();
		super.onResume();
	}

	
    @Override
	protected void initData() {
		setContext(getActivity());
		mTitle = getContext().getString(R.string.settings);
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_settings, container,
				false);
		mNavigationBarLayout = (NavigationBarLayout) view
				.findViewById(R.id.navigation_bar_layout);
		mNavigationBarLayout.setOnNavigationBarLayout(this);
		mNavigationBarLayout.setTitle(mTitle);

		mMenuPositionModeSegmented = (SegmentedGroup) view
				.findViewById(R.id.reside_menu_position_segmented);
		mLeftRadioBtn = (RadioButton) view.findViewById(R.id.left_radio_btn);
		mRightRadioBtn = (RadioButton) view.findViewById(R.id.right_radio_btn);
		MenuPositionMode menuPositionMode = SettingsHelper.getInstance()
				.getMenuPositionMode();
		if (menuPositionMode == MenuPositionMode.RIGHT) {
			mRightRadioBtn.setChecked(true);
		} else {
			mLeftRadioBtn.setChecked(true);
		}
		
		mSearchModeSegmented = (SegmentedGroup) view
				.findViewById(R.id.search_mode_segmented);
		mT9RadioBtn = (RadioButton) view.findViewById(R.id.t9_radio_btn);
		mQwertyRadioBtn = (RadioButton) view
				.findViewById(R.id.qwerty_radio_btn);
		SearchMode searchMode = SettingsHelper.getInstance().getSearchMode();
		if (SearchMode.QWERTY == searchMode) {
			mQwertyRadioBtn.setChecked(true);
		} else {
			mT9RadioBtn.setChecked(true);
		}
		
		mExitAppPromptSwitchBtn = (SwitchButton) view
				.findViewById(R.id.exit_app_prompt_switch_btn);

		ExitAppPromptMode exitAppPromptModel = SettingsHelper.getInstance()
				.getExitAppPromptMode();
		if (ExitAppPromptMode.PROMPT_NO == exitAppPromptModel) {
			mExitAppPromptSwitchBtn.setChecked(false);
		} else {
			mExitAppPromptSwitchBtn.setChecked(true);
		}

		mSmartSortingSwitchBtn= (SwitchButton) view
				.findViewById(R.id.smart_sorting_switch_btn);
		boolean smartSorting=SettingsHelper.getInstance().isSmartSorting();
		if (false == smartSorting) {
			mSmartSortingSwitchBtn.setChecked(false);
		} else {
			mSmartSortingSwitchBtn.setChecked(true);
		}
		
		return view;
	}

	@Override
	protected void initListener() {
		mMenuPositionModeSegmented
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.left_radio_btn:
					SettingsHelper.getInstance().setMenuPositionMode(
							MenuPositionMode.LEFT);
					break;
				case R.id.right_radio_btn:
					SettingsHelper.getInstance().setMenuPositionMode(
							MenuPositionMode.RIGHT);
					break;
				default:
					break;
				}
				if (getMenuPpsitionModeSettingPromptCount() < MAX_MENU_POSITION_MODE_SETTING_PROMPT_COUNT) {
					Toast.makeText(getContext(),
							R.string.app_restart_to_take_effect,
							Toast.LENGTH_SHORT).show();
				}
				setMenuPpsitionModeSettingPromptCount(getMenuPpsitionModeSettingPromptCount() + 1);
			}
		});


		mSearchModeSegmented.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.qwerty_radio_btn:
					SettingsHelper.getInstance().setSearchMode(SearchMode.QWERTY);
					break;
				case R.id.t9_radio_btn:
					SettingsHelper.getInstance().setSearchMode(SearchMode.T9);
					break;
				default:
					break;
				}

			}
		});

	mExitAppPromptSwitchBtn
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				ExitAppPromptMode exitAppPromptModel = (false == isChecked) ? (ExitAppPromptMode.PROMPT_NO)
						: (ExitAppPromptMode.PROMPT_YES);
				SettingsHelper.getInstance().setExitAppPromptMode(
						exitAppPromptModel);
			}
		});


	mSmartSortingSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			boolean smartSorting = isChecked;
			SettingsHelper.getInstance().setSmartSorting(smartSorting);
		}
	});
	}

	private void refreshView(){
		
		return;
	}



	/* Start: OnNavigationBarLayout */
	@Override
	public void onBack() {
		back();

	}

	/* End: OnNavigationBarLayout */

	public static int getMenuPpsitionModeSettingPromptCount() {
		return mMenuPpsitionModeSettingPromptCount;
	}

	public static void setMenuPpsitionModeSettingPromptCount(
			int menuPpsitionModeSettingPromptCount) {
		SettingsFragment.mMenuPpsitionModeSettingPromptCount = menuPpsitionModeSettingPromptCount
				% Integer.MAX_VALUE;
	}

	private void back() {
		getActivity().finish();
	}


}
