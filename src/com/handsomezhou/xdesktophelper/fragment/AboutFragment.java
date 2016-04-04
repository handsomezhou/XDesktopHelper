package com.handsomezhou.xdesktophelper.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.activity.ReferenceProjectActivity;
import com.handsomezhou.xdesktophelper.util.AppUtil;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout.OnNavigationBarLayout;

public class AboutFragment extends BaseFragment implements
		OnNavigationBarLayout {

	private NavigationBarLayout mNavigationBarLayout;
	private TextView mVersionNameTv;
	private View mReferenceProjectLayout;
	private String mTitle;
	private String mVersionName;
	

	@Override
	public void onResume() {
		refreshView();
		super.onResume();
	}

	@Override
	protected void initData() {
		setContext(getActivity());

		mTitle = getContext().getString(R.string.about);

		mVersionName = getContext().getString(R.string.version_name)
				+ getContext().getString(R.string.colon)
				+ AppUtil.getVersionName(getContext(), getContext()
						.getPackageName());
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_about, container, false);
		mNavigationBarLayout = (NavigationBarLayout) view
				.findViewById(R.id.navigation_bar_layout);
		mNavigationBarLayout.setOnNavigationBarLayout(this);
		mNavigationBarLayout.setTitle(mTitle);
		mVersionNameTv=(TextView) view.findViewById(R.id.version_name_text_view);
		mVersionNameTv.setText(mVersionName);
		mReferenceProjectLayout = view
				.findViewById(R.id.reference_project_layout);
		return view;
	}

	@Override
	protected void initListener() {
		mReferenceProjectLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				viewReferenceProject();

			}
		});
		return;
	}

	/* Start: OnNavigationBarLayout */
	@Override
	public void onBack() {
		back();

	}

	/* End: OnNavigationBarLayout */

	private void refreshView() {

		

	}

	private void back() {
		getActivity().finish();
	}
	
	private void viewReferenceProject() {
		Intent intent = new Intent(getContext(), ReferenceProjectActivity.class);
		startActivity(intent);
	}


}
