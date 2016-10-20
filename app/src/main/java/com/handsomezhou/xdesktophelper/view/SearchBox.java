package com.handsomezhou.xdesktophelper.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsomezhou.xdesktophelper.R;
import com.handsomezhou.xdesktophelper.util.ViewUtil;

public class SearchBox extends LinearLayout {
	private Context mContext;
	/* start: search box */
	private View mSearchBox;

	private EditText mSearchEt;

	private ImageView mDeleteIv;
	private ImageView mVoiceInputIv;
	private boolean mVoiceSearchEnable=true;
	/* end: search box */
	private OnSearchBox mOnSearchBox;

	public interface OnSearchBox {
		void onSearchTextChanged(String curCharacter);
		void onQwertySearchVoiceInput();
	}

	public SearchBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		initData();
		initListener();
	}


	public boolean isVoiceSearchEnable() {
		return mVoiceSearchEnable;
	}

	public void setVoiceSearchEnable(boolean voiceSearchEnable) {
		mVoiceSearchEnable = voiceSearchEnable;
	}

	public OnSearchBox getOnSearchBox() {
		return mOnSearchBox;
	}

	public void setOnSearchBox(OnSearchBox onSearchBox) {
		mOnSearchBox = onSearchBox;
	}


	public EditText getSearchEt() {
		return mSearchEt;
	}

	public void setSearchEt(EditText searchEt) {
		mSearchEt = searchEt;
	}

	public String getSearchEtInput() {
		return mSearchEt.getText().toString();
	}

	public void refreshView(){
		if(TextUtils.isEmpty(mSearchEt.getText().toString())){
			ViewUtil.hideView(mDeleteIv);
			if(true==mVoiceSearchEnable) {
				ViewUtil.showView(mVoiceInputIv);
			}else {
				ViewUtil.hideView(mVoiceInputIv);
			}
		}else{
			ViewUtil.showView(mDeleteIv);
			ViewUtil.hideView(mVoiceInputIv);
		}
	}
	private void initView() {
		LayoutInflater.from(mContext).inflate(R.layout.search_box, this);
		mSearchBox = findViewById(R.id.search_box);

		mSearchEt = (EditText) findViewById(R.id.search_edit_text);
		mDeleteIv = (ImageView) findViewById(R.id.delete_image_view);
		mVoiceInputIv=(ImageView) findViewById(R.id.voice_input_image_view);

		return;
	}

	private void initData() {

		return;
	}

	private void initListener() {
		mSearchEt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (null != mOnSearchBox) {
					String inputStr=s.toString();
					mOnSearchBox.onSearchTextChanged(inputStr);
					if(TextUtils.isEmpty(inputStr)){
						ViewUtil.hideView(mDeleteIv);
						if(true==mVoiceSearchEnable) {
							ViewUtil.showView(mVoiceInputIv);
						}else{
							ViewUtil.hideView(mVoiceInputIv);
						}
					}else{
						ViewUtil.showView(mDeleteIv);
						ViewUtil.hideView(mVoiceInputIv);
					}
				}

			}
		});

		mDeleteIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				delete();
			}
		});

		mVoiceInputIv.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				voiceInput();
			}
		});

		return;
	}

	private void delete() {
		mSearchEt.setText("");
		ViewUtil.hideView(mDeleteIv);
		if(true==mVoiceSearchEnable) {
			ViewUtil.showView(mVoiceInputIv);
		}else{
			ViewUtil.hideView(mVoiceInputIv);
		}
	}

	private void voiceInput(){
		if(null!=mOnSearchBox){
			mOnSearchBox.onQwertySearchVoiceInput();
		}
	}

}
