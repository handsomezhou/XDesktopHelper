package com.handsomezhou.xdesktophelper.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.text.TextUtils;
import android.util.Log;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.database.AppStartRecordDateBaseHelper;
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.AppInfo.SearchByType;
import com.handsomezhou.xdesktophelper.model.AppType;
import com.handsomezhou.xdesktophelper.model.LoadStatus;
import com.handsomezhou.xdesktophelper.util.AppCommonWeightsUtil;
import com.handsomezhou.xdesktophelper.util.AppUtil;
import com.handsomezhou.xdesktophelper.util.CommonUtil;
import com.pinyinsearch.model.PinyinSearchUnit;
import com.pinyinsearch.util.PinyinUtil;
import com.pinyinsearch.util.QwertyUtil;
import com.pinyinsearch.util.T9Util;

public class AppInfoHelper {
	private static final String TAG="AppInfoHelper";
	private static Character THE_LAST_ALPHABET='z';
	private Context mContext;
	private static AppInfoHelper mInstance;
	
	private AppType mCurrentAppType;
	private List<AppInfo> mBaseAllAppInfos;
	private LoadStatus mBaseAllAppInfosLoadStatus;
	

    private HashMap<String, AppInfo> mBaseAllAppInfosHashMap=null;
	


    private List<AppInfo> mQwertySearchAppInfos;
	private List<AppInfo> mT9SearchAppInfos;
	
	private StringBuffer mFirstNoQwertySearchResultInput=null;
	private StringBuffer mFirstNoT9SearchResultInput=null;
	
	private AsyncTask<Object, Object, List<AppInfo>> mLoadAppInfoTask=null;
	private OnAppInfoLoad mOnAppInfoLoad;
	private boolean mAppInfoChanged=true;

	public interface OnAppInfoLoad{
		void onAppInfoLoadSuccess();
		void onAppInfoLoadFailed();
	}
	
	public static AppInfoHelper getInstance(){
		if(null==mInstance){
			mInstance=new AppInfoHelper();
		}
		
		return mInstance;
	} 
	
	private AppInfoHelper(){
		initAppInfoHelper();
		
		return;
	}
	
	private void initAppInfoHelper(){
		mContext=XDesktopHelperApplication.getContext();
		setCurrentAppType(AppType.ALL_APP);
		setBaseAllAppInfosLoadStatus(LoadStatus.NOT_LOADED);
		clearAppInfoData();
		
		return;
	}

	
	public AppType getCurrentAppType() {
		return mCurrentAppType;
	}

	public void setCurrentAppType(AppType currentAppType) {
		mCurrentAppType = currentAppType;
	}
		
	public List<AppInfo> getBaseAllAppInfos() {
		return mBaseAllAppInfos;
	}

	public void setBaseAllAppInfos(List<AppInfo> baseAllAppInfos) {
		mBaseAllAppInfos = baseAllAppInfos;
	}
	
	public LoadStatus getBaseAllAppInfosLoadStatus() {
        return mBaseAllAppInfosLoadStatus;
    }

    public void setBaseAllAppInfosLoadStatus(LoadStatus baseAllAppInfosLoadStatus) {
        mBaseAllAppInfosLoadStatus = baseAllAppInfosLoadStatus;
    }
    
   public HashMap<String, AppInfo> getBaseAllAppInfosHashMap() {
        return mBaseAllAppInfosHashMap;
    }

    public void setBaseAllAppInfosHashMap(HashMap<String, AppInfo> baseAllAppInfosHashMap) {
        mBaseAllAppInfosHashMap = baseAllAppInfosHashMap;
    }
	    
	public List<AppInfo> getQwertySearchAppInfos() {
		return mQwertySearchAppInfos;
	}

	public void setQwertySearchAppInfos(List<AppInfo> qwertySearchAppInfos) {
		mQwertySearchAppInfos = qwertySearchAppInfos;
	}

	public List<AppInfo> getT9SearchAppInfos() {
		return mT9SearchAppInfos;
	}

	public void setT9SearchAppInfos(List<AppInfo> t9SearchAppInfos) {
		mT9SearchAppInfos = t9SearchAppInfos;
	}

	public OnAppInfoLoad getOnAppInfoLoad() {
		return mOnAppInfoLoad;
	}

	public void setOnAppInfoLoad(OnAppInfoLoad onAppInfoLoad) {
		mOnAppInfoLoad = onAppInfoLoad;
	}

	public boolean isAppInfoChanged() {
		return mAppInfoChanged;
	}

	public void setAppInfoChanged(boolean appInfoChanged) {
		mAppInfoChanged = appInfoChanged;
	}
	
	public boolean startLoadAppInfo(){
		if(true==isAppInfoLoading()){
			return false;
		}
		
		if(false==isAppInfoChanged()){
			return false;
		}
		
		clearAppInfoData();
		mLoadAppInfoTask=new AsyncTask<Object, Object, List<AppInfo>>(){

			@Override
			protected List<AppInfo> doInBackground(Object... params) {
				// TODO Auto-generated method stub
				return loadAppInfo(mContext);
			}

			@Override
			protected void onPostExecute(List<AppInfo> result) {
				parseAppInfo(result);
				super.onPostExecute(result);
				//setAppInfoChanged(false);
				mLoadAppInfoTask=null;
			}
			
		}.execute();
		setAppInfoChanged(false);
		return true;
		
	}
	
	@SuppressLint("DefaultLocale")
	public List<AppInfo> loadAppInfo(Context context){
		List<AppInfo> appInfos=new ArrayList<AppInfo>();
		List<AppInfo> kanjiStartAppInfos = new ArrayList<AppInfo>();
		List<AppInfo> nonKanjiStartAppInfos = new ArrayList<AppInfo>();
		do{
			if(null==context){
				break;
			}
			
			PackageManager pm=context.getPackageManager();
			
			long startLoadTime=System.currentTimeMillis();
			int flags = PackageManager.GET_UNINSTALLED_PACKAGES;
			
			setBaseAllAppInfosLoadStatus(LoadStatus.LOADING);
			List<PackageInfo> packageInfos=pm.getInstalledPackages(flags);
			Log.i(TAG, packageInfos.size()+"");
			for(PackageInfo pi:packageInfos){
				boolean canLaunchTheMainActivity=AppUtil.appCanLaunchTheMainActivity(mContext, pi.packageName);
				if(true==canLaunchTheMainActivity){
					AppInfo appInfo=getAppInfo(pm, pi);
					if(null==appInfo){
						continue;
					}
					
					if(TextUtils.isEmpty(appInfo.getLabel())){
						continue;
					}
					appInfo.getLabelPinyinSearchUnit().setBaseData(appInfo.getLabel());
					PinyinUtil.parse(appInfo.getLabelPinyinSearchUnit());
					String sortKey=PinyinUtil.getSortKey(appInfo.getLabelPinyinSearchUnit()).toUpperCase();
					appInfo.setSortKey(praseSortKey(sortKey));
					boolean isKanji=PinyinUtil.isKanji(appInfo.getLabel().charAt(0));
					if(true==isKanji){
						kanjiStartAppInfos.add(appInfo);
					}else{
						nonKanjiStartAppInfos.add(appInfo);
					}
					
				}
			}
			long endLoadTime=System.currentTimeMillis();
			Log.i(TAG, "endLoadTime-startLoadTime["+(endLoadTime-startLoadTime)+"]");
			//Toast.makeText(mContext, "endLoadTime-startLoadTime["+(endLoadTime-startLoadTime)+"]", Toast.LENGTH_LONG).show();
			break;
		}while(false);
		
		long sortStartTime=System.currentTimeMillis();
		
		Collections.sort(kanjiStartAppInfos, AppInfo.mSortBySortKeyAsc);
		Collections.sort(nonKanjiStartAppInfos, AppInfo.mSortBySortKeyAsc);
		
		//appInfos.addAll(nonKanjiStartAppInfos);
		appInfos.addAll(kanjiStartAppInfos);
	
		/*Start: merge nonKanjiStartAppInfos and kanjiStartAppInfos*/
		int lastIndex=0;
		boolean shouldBeAdd=false;
		for(int i=0; i<nonKanjiStartAppInfos.size(); i++){
			String nonKanfirstLetter=PinyinUtil.getFirstLetter(nonKanjiStartAppInfos.get(i).getLabelPinyinSearchUnit());
			//Log.i(TAG, "nonKanfirstLetter=["+nonKanfirstLetter+"]["+nonKanjiStartAppInfos.get(i).getLabel()+"]["+Integer.valueOf(nonKanjiStartAppInfos.get(i).getLabel().charAt(0))+"]");
			int j=0;
			for(j=0+lastIndex; j<appInfos.size(); j++){
				String firstLetter=PinyinUtil.getFirstLetter(appInfos.get(j).getLabelPinyinSearchUnit());
				lastIndex++;
				if(nonKanfirstLetter.charAt(0)<firstLetter.charAt(0)||nonKanfirstLetter.charAt(0)>THE_LAST_ALPHABET){
					shouldBeAdd=true;
					break;
				}else{
					shouldBeAdd=false;
				}
			}
			
			if(lastIndex>=appInfos.size()){
				lastIndex++;
				shouldBeAdd=true;
				//Log.i(TAG, "lastIndex="+lastIndex);
			}
			
			if(true==shouldBeAdd){
				appInfos.add(j, nonKanjiStartAppInfos.get(i));
				shouldBeAdd=false;
			}
		}
		/*End: merge nonKanjiStartAppInfos and kanjiStartAppInfos*/
	
		
/*		for(int i=0; i<appInfos.size(); i++){
			Log.i(TAG, i+"["+appInfos.get(i).getLabel()+"]");
		}*/
		
		long sortEndTime=System.currentTimeMillis();
		Log.i(TAG, "sortEndTime-sortStartTime["+(sortEndTime-sortStartTime)+"]");
	
		Log.i(TAG, "appInfos.size()"+ appInfos.size());
		//Toast.makeText(context,"["+ appInfos.get(0).getLabel()+"]["+appInfos.get(0).getPackageName()+"]", Toast.LENGTH_LONG).show();
		return appInfos;
	}
	
	public void getQwertySearchAppInfo(String search){
		List<AppInfo> baseAppInfos=getBaseAppInfo();
		if(null!=mQwertySearchAppInfos){
			mQwertySearchAppInfos.clear();
		}else{
			mQwertySearchAppInfos=new ArrayList<AppInfo>();
		}
		
		if(TextUtils.isEmpty(search)){
			for(AppInfo ai:baseAppInfos){
				ai.setSearchByType(SearchByType.SearchByNull);
				ai.clearMatchKeywords();
				ai.setMatchStartIndex(-1);
				ai.setMatchLength(0);
			}
			mQwertySearchAppInfos.addAll(baseAppInfos);
			
			mFirstNoQwertySearchResultInput.delete(0, mFirstNoQwertySearchResultInput.length());
			Log.i(TAG, "null==search,mFirstNoQwertySearchResultInput.length()="+ mFirstNoQwertySearchResultInput.length());
			return;
		}
		
		if (mFirstNoQwertySearchResultInput.length() > 0) {
			if (search.contains(mFirstNoQwertySearchResultInput.toString())) {
				Log.i(TAG,
						"no need  to search,null!=search,mFirstNoQwertySearchResultInput.length()="
								+ mFirstNoQwertySearchResultInput.length() + "["
								+ mFirstNoQwertySearchResultInput.toString() + "]"
								+ ";searchlen=" + search.length() + "["
								+ search + "]");
				return;
			} else {
				Log.i(TAG,
						"delete  mFirstNoQwertySearchResultInput, null!=search,mFirstNoQwertySearchResultInput.length()="
								+ mFirstNoQwertySearchResultInput.length()
								+ "["
								+ mFirstNoQwertySearchResultInput.toString()
								+ "]"
								+ ";searchlen="
								+ search.length()
								+ "["
								+ search + "]");
				mFirstNoQwertySearchResultInput.delete(0,mFirstNoQwertySearchResultInput.length());
			}
		}
		
		mQwertySearchAppInfos.clear();
		int baseAppInfosCount=baseAppInfos.size();
		for(int i=0; i<baseAppInfosCount; i++){
			PinyinSearchUnit labelPinyinSearchUnit=baseAppInfos.get(i).getLabelPinyinSearchUnit();
			boolean match=QwertyUtil.match(labelPinyinSearchUnit,search);
			
			
			if (true == match) {// search by LabelPinyinUnits;
				AppInfo appInfo = baseAppInfos.get(i);
				appInfo.setSearchByType(SearchByType.SearchByLabel);
				appInfo.setMatchKeywords(labelPinyinSearchUnit.getMatchKeyWord().toString());
				appInfo.setMatchStartIndex(appInfo.getLabel().indexOf(appInfo.getMatchKeywords().toString()));
				appInfo.setMatchLength(appInfo.getMatchKeywords().length());
				
				mQwertySearchAppInfos.add(appInfo);

				continue;
			}
		}
		
		if (mQwertySearchAppInfos.size() <= 0) {
			if (mFirstNoQwertySearchResultInput.length() <= 0) {
				mFirstNoQwertySearchResultInput.append(search);
				Log.i(TAG,
						"no search result,null!=search,mFirstNoQwertySearchResultInput.length()="
								+ mFirstNoQwertySearchResultInput.length() + "["
								+ mFirstNoQwertySearchResultInput.toString() + "]"
								+ ";searchlen=" + search.length() + "["
								+ search + "]");
			} else {

			}
		}else{
			Collections.sort(mQwertySearchAppInfos, AppInfo.mSortBySearch);
		}
		return;
	}
	
	public void getT9SearchAppInfo(String search){
		List<AppInfo> baseAppInfos=getBaseAppInfo();
		Log.i(TAG, "baseAppInfos["+baseAppInfos.size()+"]");
		if(null!=mT9SearchAppInfos){
			mT9SearchAppInfos.clear();
		}else{
			mT9SearchAppInfos=new ArrayList<AppInfo>();
		}
		
		if(TextUtils.isEmpty(search)){
			for(AppInfo ai:baseAppInfos){
				ai.setSearchByType(SearchByType.SearchByNull);
				ai.clearMatchKeywords();
				ai.setMatchStartIndex(-1);
				ai.setMatchLength(0);
			}
			
			mT9SearchAppInfos.addAll(baseAppInfos);
			
			mFirstNoT9SearchResultInput.delete(0, mFirstNoT9SearchResultInput.length());
			Log.i(TAG, "null==search,mFirstNoT9SearchResultInput.length()="+ mFirstNoT9SearchResultInput.length());
			return;
		}
		
		if (mFirstNoT9SearchResultInput.length() > 0) {
			if (search.contains(mFirstNoT9SearchResultInput.toString())) {
				Log.i(TAG,
						"no need  to search,null!=search,mFirstNoT9SearchResultInput.length()="
								+ mFirstNoT9SearchResultInput.length() + "["
								+ mFirstNoT9SearchResultInput.toString() + "]"
								+ ";searchlen=" + search.length() + "["
								+ search + "]");
				return;
			} else {
				Log.i(TAG,
						"delete  mFirstNoT9SearchResultInput, null!=search,mFirstNoT9SearchResultInput.length()="
								+ mFirstNoT9SearchResultInput.length()
								+ "["
								+ mFirstNoT9SearchResultInput.toString()
								+ "]"
								+ ";searchlen="
								+ search.length()
								+ "["
								+ search + "]");
				mFirstNoT9SearchResultInput.delete(0,mFirstNoT9SearchResultInput.length());
			}
		}
		
		mT9SearchAppInfos.clear();
		int baseAppInfosCount=baseAppInfos.size();
		for(int i=0; i<baseAppInfosCount; i++){
			PinyinSearchUnit labelPinyinSearchUnit=baseAppInfos.get(i).getLabelPinyinSearchUnit();
			boolean match=T9Util.match(labelPinyinSearchUnit, search);
			
			if (true == match) {// search by LabelPinyinUnits;
				AppInfo appInfo = baseAppInfos.get(i);
				appInfo.setSearchByType(SearchByType.SearchByLabel);
				appInfo.setMatchKeywords(labelPinyinSearchUnit.getMatchKeyWord().toString());
				appInfo.setMatchStartIndex(appInfo.getLabel().indexOf(appInfo.getMatchKeywords().toString()));
				appInfo.setMatchLength(appInfo.getMatchKeywords().length());
				mT9SearchAppInfos.add(appInfo);
				continue;
			}
		}
		
		if (mT9SearchAppInfos.size() <= 0) {
			if (mFirstNoT9SearchResultInput.length() <= 0) {
				mFirstNoT9SearchResultInput.append(search);
				Log.i(TAG,
						"no search result,null!=search,mFirstNoT9SearchResultInput.length()="
								+ mFirstNoT9SearchResultInput.length() + "["
								+ mFirstNoT9SearchResultInput.toString() + "]"
								+ ";searchlen=" + search.length() + "["
								+ search + "]");
			} else {

			}
		}else{
			Collections.sort(mT9SearchAppInfos, AppInfo.mSortBySearch);
		}
		return;
	}
	
	public boolean isAppExist(String packageName){
		boolean appExist=false;
		do{
			if(TextUtils.isEmpty(packageName)){
			    appExist=false;
				break;
			}
			
			/*for(AppInfo ai:mBaseAllAppInfos){
				if(ai.getPackageName().equals(packageName)){
					appExist=true;
					break;
				}
			}*/
			if(mBaseAllAppInfosHashMap.containsKey(packageName)){
			    appExist=true;
			    break;
			}
		}while(false);
		
		return appExist;
	}
	
	public boolean add(String packageName){
	    boolean addSuccess=false;
	    do{
	        if(TextUtils.isEmpty(packageName)){
	            addSuccess=false;
                break;
            } 
	        boolean canLaunchTheMainActivity=AppUtil.appCanLaunchTheMainActivity(mContext,packageName);

	        if(true==canLaunchTheMainActivity){
	            PackageManager pm=mContext.getPackageManager();
	            int flags = PackageManager.GET_UNINSTALLED_PACKAGES;
	            PackageInfo pi=null;
                try {
                    pi = pm.getPackageInfo(packageName, flags);
                } catch (NameNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                if(null!=pi){
                    AppInfo appInfo=getAppInfo(pm, pi);
                    if(TextUtils.isEmpty(appInfo.getLabel())){
                        addSuccess=false;
                        break;
                    }
                    appInfo.getLabelPinyinSearchUnit().setBaseData(appInfo.getLabel());
                    PinyinUtil.parse(appInfo.getLabelPinyinSearchUnit());
                    String sortKey=PinyinUtil.getSortKey(appInfo.getLabelPinyinSearchUnit()).toUpperCase();
                    appInfo.setSortKey(praseSortKey(sortKey));
                    
                    mBaseAllAppInfosHashMap.put(appInfo.getPackageName(), appInfo);
                    mBaseAllAppInfos.add(appInfo);
                    Collections.sort(mBaseAllAppInfos, AppInfo.mSortByDefault);
                    addSuccess=true;
                }
            }
	       
	    }while(false);
	    return addSuccess;
	}
	
	/**
	 
	boolean canLaunchTheMainActivity=AppUtil.appCanLaunchTheMainActivity(mContext, pi.packageName);
                if(true==canLaunchTheMainActivity){
                    AppInfo appInfo=getAppInfo(pm, pi);
                    if(null==appInfo){
                        continue;
                    }
                    
                    if(TextUtils.isEmpty(appInfo.getLabel())){
                        continue;
                    }
                    appInfo.getLabelPinyinSearchUnit().setBaseData(appInfo.getLabel());
                    PinyinUtil.parse(appInfo.getLabelPinyinSearchUnit());
                    String sortKey=PinyinUtil.getSortKey(appInfo.getLabelPinyinSearchUnit()).toUpperCase();
                    appInfo.setSortKey(praseSortKey(sortKey));
                    boolean isKanji=PinyinUtil.isKanji(appInfo.getLabel().charAt(0));
                    if(true==isKanji){
                        kanjiStartAppInfos.add(appInfo);
                    }else{
                        nonKanjiStartAppInfos.add(appInfo);
                    }
                    
                }

	 */
	
	public boolean resetSequence(String packageName){
	    boolean resetSequenceSuccess=false;
	    do{
	        if(TextUtils.isEmpty(packageName)){
	            resetSequenceSuccess=false;
                break;
            } 
	        
	        AppStartRecordDateBaseHelper.getInstance().delete(packageName);
	        
	        if(mBaseAllAppInfosHashMap.containsKey(packageName)){
	            mBaseAllAppInfosHashMap.get(packageName).setCommonWeights(AppCommonWeightsUtil.COMMON_WEIGHTS_DEFAULT);
	            Collections.sort(mBaseAllAppInfos, AppInfo.mSortByDefault);
	        }
	        
	        resetSequenceSuccess=true;
	    }while(false);
	    
	    return resetSequenceSuccess;
	}
	
	public boolean remove(String packageName){
	    boolean removeSuccess=false;
	    
	    do{
	        if(TextUtils.isEmpty(packageName)){
	            removeSuccess=false;
	            break;
	        }
	        
	        AppStartRecordDateBaseHelper.getInstance().delete(packageName);
	        
	        mBaseAllAppInfosHashMap.remove(packageName);
	        for(int i=0; i<mBaseAllAppInfos.size(); i++){
	            if(mBaseAllAppInfos.get(i).getPackageName().equals(packageName)){
	                mBaseAllAppInfos.remove(i);
	                break;
	            }
	        }
	        removeSuccess=true;
	    }while(false);
	    return removeSuccess;
	}
	
	private void clearAppInfoData(){
		
		if(null==mBaseAllAppInfos){
			mBaseAllAppInfos=new ArrayList<AppInfo>();
		}else{
		    mBaseAllAppInfos.clear();
		}
		
		if(null==mBaseAllAppInfosHashMap){
		    mBaseAllAppInfosHashMap=new HashMap<String, AppInfo>();
		}else{
		    mBaseAllAppInfosHashMap.clear();
		}
		
		if(null==mQwertySearchAppInfos){
			mQwertySearchAppInfos=new ArrayList<AppInfo>();
		}else{
		    mQwertySearchAppInfos.clear();
		}
		
		if(null==mT9SearchAppInfos){
			mT9SearchAppInfos=new ArrayList<AppInfo>();
		}else{
		    mT9SearchAppInfos.clear();
		}
		
		if(null==mFirstNoQwertySearchResultInput){
			mFirstNoQwertySearchResultInput=new StringBuffer();
		}else{
			mFirstNoQwertySearchResultInput.delete(0, mFirstNoQwertySearchResultInput.length());
		}
		
		if(null==mFirstNoT9SearchResultInput){
			mFirstNoT9SearchResultInput=new StringBuffer();
		}else{
			mFirstNoT9SearchResultInput.delete(0, mFirstNoT9SearchResultInput.length());
		}
		
		return;
	}
	
	private AppInfo getAppInfo(PackageManager pm,PackageInfo packageInfo){
		if((null==pm)||(null==packageInfo)){
			return null;
		}
		AppInfo appInfo=new AppInfo();
		appInfo.setIcon(packageInfo.applicationInfo.loadIcon(pm));
		appInfo.setLabel((String)packageInfo.applicationInfo.loadLabel(pm));
		appInfo.setPackageName(packageInfo.packageName);
		return appInfo;
		
	}
	private boolean isAppInfoLoading(){
		return ((null!=mLoadAppInfoTask)&&(mLoadAppInfoTask.getStatus()==Status.RUNNING));
	}
	
	private void parseAppInfo(List<AppInfo> appInfos){
		Log.i(TAG, "parseAppInfo");
		if(null==appInfos||appInfos.size()<1){
		    setBaseAllAppInfosLoadStatus(LoadStatus.NOT_LOADED);
			if(null!=mOnAppInfoLoad){
				mOnAppInfoLoad.onAppInfoLoadFailed();
			}
			return;
		}
		
		Log.i(TAG, "before appInfos.size()"+ appInfos.size());
		mBaseAllAppInfos.clear();
		mBaseAllAppInfos.addAll(appInfos);
		
		mBaseAllAppInfosHashMap.clear();
		for(AppInfo ai:mBaseAllAppInfos){
		    mBaseAllAppInfosHashMap.put(ai.getPackageName(), ai);
		}
		
		Log.i(TAG, "after appInfos.size()"+ appInfos.size());
		
		setBaseAllAppInfosLoadStatus(LoadStatus.LOAD_FINISH);
		if(null!=mOnAppInfoLoad){
			mOnAppInfoLoad.onAppInfoLoadSuccess();
		}
		
		
		return;
	}
	
	private String praseSortKey(String sortKey) {
		if (null == sortKey || sortKey.length() <= 0) {
			return null;
		}

		if ((sortKey.charAt(0) >= 'a' && sortKey.charAt(0) <= 'z')
				|| (sortKey.charAt(0) >= 'A' && sortKey.charAt(0) <= 'Z')) {
			return sortKey;
		}

		return String.valueOf(/*QuickAlphabeticBar.DEFAULT_INDEX_CHARACTER*/'#')
				+ sortKey;
	}
	
	private List<AppInfo> getBaseAppInfo(){
		List<AppInfo> baseAppInfos=null;
		switch (getCurrentAppType()) {
		//case ALL_APP:
		default:
			baseAppInfos=mBaseAllAppInfos;
			break;
		}
		return baseAppInfos;
	}
}
