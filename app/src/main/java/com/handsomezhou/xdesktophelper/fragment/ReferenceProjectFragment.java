package com.handsomezhou.xdesktophelper.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.adapter.ReferenceProjectAdapter;
import com.handsomezhou.xdesktophelper.model.ProjectInfo;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout;
import com.handsomezhou.xdesktophelper.view.NavigationBarLayout.OnNavigationBarLayout;

public class ReferenceProjectFragment extends BaseFragment implements OnNavigationBarLayout{
	private NavigationBarLayout mNavigationBarLayout;
	private String mTitle;
	private ListView mReferenceProjectLv;
	private ReferenceProjectAdapter mReferenceProjectAdapter;
	private List<ProjectInfo> mProjectInfo;
//	private 
	@Override
	protected void initData() {
		setContext(getActivity());
		mTitle = getContext().getString(R.string.reference_project);
		mProjectInfo=loadReferenceProjectInfo();
		
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view=inflater.inflate(R.layout.fragment_reference_project, container, false);
		mNavigationBarLayout = (NavigationBarLayout) view
				.findViewById(R.id.navigation_bar_layout);
		mNavigationBarLayout.setOnNavigationBarLayout(this);
		mNavigationBarLayout.setTitle(mTitle);
		mReferenceProjectLv=(ListView)view.findViewById(R.id.reference_project_list_view);
		mReferenceProjectAdapter=new ReferenceProjectAdapter(getContext(), R.layout.project_info_list_item, mProjectInfo);
		mReferenceProjectLv.setAdapter(mReferenceProjectAdapter);
		
		return view;
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBack() {
		back();
	}
	
	private void back(){
		getActivity().finish();
	}
	
	private List<ProjectInfo> loadReferenceProjectInfo(){
		String[] projectInfoItems=getContext().getResources().getStringArray(R.array.reference_project);
		
		List<ProjectInfo> projectInfos=new ArrayList<ProjectInfo>();

		for(String item:projectInfoItems){
			try {
				JSONObject jsonObject=new JSONObject(item);
				if(null!=jsonObject){
					ProjectInfo projectInfo=new ProjectInfo();
					if(jsonObject.has(ProjectInfo.KEY_PROJECT_NAME)){
						projectInfo.setProjectName(jsonObject.getString(ProjectInfo.KEY_PROJECT_NAME));
					}
					
					if(jsonObject.has(ProjectInfo.KEY_PROJECT_ADDRESS)){
						projectInfo.setProjectAddress(jsonObject.getString(ProjectInfo.KEY_PROJECT_ADDRESS));
					}
					
					if(jsonObject.has(ProjectInfo.KEY_WHETHER_OPEN_SOURCE)){
						projectInfo.setWhetherOpenSource(jsonObject.getBoolean(ProjectInfo.KEY_WHETHER_OPEN_SOURCE));
					}
					
					projectInfos.add(projectInfo);
					
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return projectInfos;
	}

}
