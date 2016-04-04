package com.handsomezhou.xdesktophelper.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.model.ProjectInfo;

public class ReferenceProjectAdapter extends ArrayAdapter<ProjectInfo> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public ReferenceProjectAdapter(Context context, int textViewResourceId,
			List<ProjectInfo> objects) {
		super(context, textViewResourceId, objects);
		mContext=context;
		mTextViewResourceId=textViewResourceId;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;
		ViewHolder viewHolder;
		ProjectInfo projectInfo=getItem(position);
		if(null==convertView){
			view=LayoutInflater.from(mContext).inflate(mTextViewResourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.mProjectNameTv=(TextView)view.findViewById(R.id.project_name_text_view);
			viewHolder.mProjectAddressTv=(TextView)view.findViewById(R.id.project_address_text_view);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		
		viewHolder.mProjectNameTv.setText(projectInfo.getProjectName());
		viewHolder.mProjectAddressTv.setText(projectInfo.getProjectAddress());
		
		return view;
	}


	private class ViewHolder{
		TextView mProjectNameTv;
		TextView mProjectAddressTv;
	}

}
