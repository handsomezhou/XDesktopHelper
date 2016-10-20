package com.handsomezhou.xdesktophelper.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.dialog.CommonDialog;
import com.handsomezhou.xdesktophelper.dialog.CommonDialog.OnCommonDialog;
import com.handsomezhou.xdesktophelper.helper.AppInfoHelper;
import com.handsomezhou.xdesktophelper.helper.SettingsHelper;
import com.handsomezhou.xdesktophelper.model.MenuPositionMode;
import com.handsomezhou.xdesktophelper.model.SearchMode;
import com.handsomezhou.xdesktophelper.util.ToastUtil;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout.OnNavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.SegmentedGroup;
import com.handsomezhou.xdesktophelper.view.switchbutton.SwitchButton;

public class
SettingsFragment extends BaseFragment implements OnNavigationBarLayout ,OnCommonDialog {
	private static final String TAG = SettingsFragment.class.getSimpleName();
	private static final int MAX_MENU_POSITION_MODE_SETTING_PROMPT_COUNT = 3;

	private NavigationBarLayout mNavigationBarLayout;

	private String mTitle;
	/* start: menu position mode */
	private SegmentedGroup mMenuPositionModeSegmented;
	private RadioButton mLeftRadioBtn;
	private RadioButton mRightRadioBtn;
	private static int  mMenuPpsitionModeSettingPromptCount= 0;
	/* end: menu position mode */
	
	/* start: search mode */
	private SegmentedGroup mSearchModeSegmented;
	private RadioButton mT9RadioBtn;
	private RadioButton mQwertyRadioBtn;
	/* end: search mode */

	private Button mOneKeyResetSequenceBtn;
	private SwitchButton mVoiceSearchEnableBtn;
	private SwitchButton mVoiceStartAppBtn;
	private SwitchButton mSmartSortingSwitchBtn;
	/* start : exit_app_prompt switch button */
	private SwitchButton mExitAppPromptSwitchBtn;
	/* end : exit_app_prompt switch button */


	private CommonDialog mCommonDialog;

	private enum DialogType {
		ONE_KEY_RESET_SEQUENCE,
	}

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

		mOneKeyResetSequenceBtn=(Button) view.findViewById(R.id.one_key_reset_sequence_btn);

		mExitAppPromptSwitchBtn = (SwitchButton) view
				.findViewById(R.id.exit_app_prompt_switch_btn);


		mVoiceSearchEnableBtn=(SwitchButton) view.findViewById(R.id.voice_search_enable_switch_btn);
		boolean voiceSearchEnable=SettingsHelper.getInstance().isVoiceSearchEnable();
		mVoiceSearchEnableBtn.setChecked(voiceSearchEnable);

		mVoiceStartAppBtn=(SwitchButton) view.findViewById(R.id.voice_start_app_switch_btn);
		boolean voiceStartApp=SettingsHelper.getInstance().isVoiceStartApp();
		mVoiceStartAppBtn.setChecked(voiceStartApp);

		mSmartSortingSwitchBtn= (SwitchButton) view
				.findViewById(R.id.smart_sorting_switch_btn);
		boolean smartSorting=SettingsHelper.getInstance().isSmartSorting();
		mSmartSortingSwitchBtn.setChecked(smartSorting);

		boolean exitAppPrompt = SettingsHelper.getInstance()
				.isExitAppPrompt();
		mExitAppPromptSwitchBtn.setChecked(exitAppPrompt);
		
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

		mOneKeyResetSequenceBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				oneKeyResetSequence();
			}
		});


		mVoiceSearchEnableBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsHelper.getInstance().setVoiceSearchEnable(isChecked);
			}
		});

		mVoiceStartAppBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsHelper.getInstance().setVoiceStartApp(isChecked);
			}
		});

		mSmartSortingSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsHelper.getInstance().setSmartSorting(isChecked);
			}
		});

		mExitAppPromptSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				SettingsHelper.getInstance().setExitAppPrompt(isChecked);
			}
		});
	}

	/*start: OnCommonDialog*/
    @Override
	public void onCommonDialogOk(Object dialogType, Object dialogData) {
		switch ((DialogType) dialogType) {
			case ONE_KEY_RESET_SEQUENCE:
				boolean resetAllSequenceSuccess=AppInfoHelper.getInstance().resetAllSequence();
				if(true==resetAllSequenceSuccess){
					ToastUtil.toastLengthshort(getContext(),R.string.one_key_reset_sequence_success);
				}else {
					ToastUtil.toastLengthshort(getContext(),R.string.one_key_reset_sequence_failed);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void onCommonDialogCancel(Object dialogType, Object dialogData) {

	}
	/*end: OnCommonDialog*/


	/* Start: OnNavigationBarLayout */
	@Override
	public void onBack() {
		back();

	}

	/* End: OnNavigationBarLayout */

	private void refreshView(){

		return;
	}

	public static int getMenuPpsitionModeSettingPromptCount() {
		return mMenuPpsitionModeSettingPromptCount;
	}

	public static void setMenuPpsitionModeSettingPromptCount(
			int menuPpsitionModeSettingPromptCount) {
		SettingsFragment.mMenuPpsitionModeSettingPromptCount = menuPpsitionModeSettingPromptCount
				% Integer.MAX_VALUE;
	}

	public CommonDialog getCommonDialog(DialogType dialogType, Object object) {
		if (null == dialogType || null == object) {
			return null;
		}

		if (null == mCommonDialog) {
			mCommonDialog = new CommonDialog(getContext());
			mCommonDialog.setCancelable(true);
			mCommonDialog.setCanceledOnTouchOutside(true);
			mCommonDialog.setOnCommonDialog(this);
		}

		mCommonDialog.setDialogType(dialogType);
		switch (dialogType) {
			case ONE_KEY_RESET_SEQUENCE:
				mCommonDialog.setDialogData(object);
				mCommonDialog.getTitleTv().setText(R.string.one_key_reset_sequence);

				mCommonDialog.getMessageTv().setText(
						R.string.sure_to_one_key_reset_sequence);
				break;

			default:
				break;
		}

		return mCommonDialog;
	}

	public void setCommonDialog(CommonDialog commonDialog) {
		mCommonDialog = commonDialog;
	}

	private void back() {
		getActivity().finish();
	}

	private void oneKeyResetSequence(){
		getCommonDialog(DialogType.ONE_KEY_RESET_SEQUENCE,new Object()).show();
	}
}
