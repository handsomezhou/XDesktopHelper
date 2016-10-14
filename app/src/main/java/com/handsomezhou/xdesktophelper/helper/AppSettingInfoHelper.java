package com.handsomezhou.xdesktophelper.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.handsomezhou.xdesktophelper.database.AppSettingInfoDataBaseHelper;
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.AppSettingInfo;
import com.handsomezhou.xdesktophelper.model.AppStartRecord;
import com.handsomezhou.xdesktophelper.model.LoadStatus;
import com.handsomezhou.xdesktophelper.util.AppCommonWeightsUtil;

public class AppSettingInfoHelper {
    private static final String TAG=AppSettingInfoHelper.class.getSimpleName();
    private static AppSettingInfoHelper mInstance;
    private OnAppSettingInfoLoad mOnAppSettingInfoLoad;
    private List<AppSettingInfo> mAppSettingInfos;  
    private HashMap<String, AppSettingInfo> mAppSettingInfoHashMap=null;
    private AsyncTask<Object, Object, List<AppSettingInfo>> mLoadTask=null;

    private LoadStatus mAppSettingInfoLoadStatus=LoadStatus.NOT_LOADED;
    
    public interface OnAppSettingInfoLoad{
        void onAppSettingInfoLoadSuccess();
        void onAppSettingInfoLoadFailed();
    }
    
    public static AppSettingInfoHelper getInstance(){
        if(null==mInstance){
            mInstance=new AppSettingInfoHelper();
        }
        
        return mInstance;
    }
    
    private AppSettingInfoHelper(){
        initAppSettingInfoHelper();
    }
    
    private void initAppSettingInfoHelper(){
        initAppSettingInfos();
        initAppSettingInfoHashMap();
        setAppSettingInfoLoadStatus(LoadStatus.NOT_LOADED);
        return;
    }
    
   
    public OnAppSettingInfoLoad getOnAppSettingInfoLoad() {
        return mOnAppSettingInfoLoad;
    }

    public void setOnAppSettingInfoLoad(OnAppSettingInfoLoad onAppSettingInfoLoad) {
        mOnAppSettingInfoLoad = onAppSettingInfoLoad;
    }
    
    public List<AppSettingInfo> getAppSettingInfos() {
        return mAppSettingInfos;
    }

    public void setAppSettingInfos(List<AppSettingInfo> appSettingInfos) {
        mAppSettingInfos = appSettingInfos;
    }
    
    public HashMap<String, AppSettingInfo> getAppSettingInfoHashMap() {
        return mAppSettingInfoHashMap;
    }

    public void setAppSettingInfoHashMap(HashMap<String, AppSettingInfo> appSettingInfoHashMap) {
        mAppSettingInfoHashMap = appSettingInfoHashMap;
    }
    
    public LoadStatus getAppSettingInfoLoadStatus() {
        return mAppSettingInfoLoadStatus;
    }

    public void setAppSettingInfoLoadStatus(LoadStatus appSettingInfoLoadStatus) {
        mAppSettingInfoLoadStatus = appSettingInfoLoadStatus;
    }
    
    public boolean setToTop(final AppInfo appinfo){
        boolean setToTopSuccess=false;
        do{
            if(null==appinfo){
                setToTopSuccess=false;
                break;
            }
            AppSettingInfo appSettingInfo=new AppSettingInfo();
            appSettingInfo.setKey(appinfo.getKey());
            setToTopSuccess=setToTop(appSettingInfo);
        }while(false);
        
        return setToTopSuccess;
    }
    
    public boolean cancelSetToTop(final AppInfo appinfo){
        boolean cancelSetToTop=false;
        do{
            if(null==appinfo){
                cancelSetToTop=false;
                break;
            }
            AppSettingInfo appSettingInfo=new AppSettingInfo();
            appSettingInfo.setKey(appinfo.getKey());
            cancelSetToTop=cancelSetToTop(appSettingInfo);
        }while(false);
        
        return cancelSetToTop;
    }
    
    public boolean parseAppSettingInfo(){
        boolean parseSuccess=false;
        
        do{
           
         
            if(mAppSettingInfos.size()<=0){
                parseSuccess=false;
                break;
            }
            
            AppInfo appInfo=null;
            for(AppSettingInfo asi:mAppSettingInfos){
                if(true==AppInfoHelper.getInstance().getBaseAllAppInfosHashMap().containsKey(asi.getKey())){
                    appInfo= AppInfoHelper.getInstance().getBaseAllAppInfosHashMap().get(asi.getKey());
                    appInfo.setSetToTop(asi.getSetToTop());
                }
            }
            Collections.sort(AppInfoHelper.getInstance().getBaseAllAppInfos(), AppInfo.mSortByDefault);
            for(int i=0; i<AppInfoHelper.getInstance().getBaseAllAppInfos().size() ; i++){
                Log.i(TAG, AppInfoHelper.getInstance().getBaseAllAppInfos().get(i).getLabel()+" SetToTop:"+AppInfoHelper.getInstance().getBaseAllAppInfos().get(i).getSetToTop());
            }
            parseSuccess=true;
        }while(false);
        
        return parseSuccess;
    }
    
    public boolean setToTop(final AppSettingInfo appSettingInfo){
        boolean setToTopSuccess=updateSetToTop(appSettingInfo, true);
        if(true==setToTopSuccess){
            setToTopSuccess=AppInfoHelper.getInstance().updateSetToTop(appSettingInfo.getKey(), appSettingInfo.getSetToTop());
        }
        return setToTopSuccess;
    }
    
    
    public boolean cancelSetToTop(final AppSettingInfo appSettingInfo){
        boolean cancelToTopSuccess=updateSetToTop(appSettingInfo, false);
        if(true==cancelToTopSuccess){
            cancelToTopSuccess=AppInfoHelper.getInstance().updateSetToTop(appSettingInfo.getKey(), appSettingInfo.getSetToTop());
        }
        return cancelToTopSuccess;
    }
    
    /*start: insert*/
    public boolean insert(AppSettingInfo appSettingInfo){
        boolean insertSuccess=false;
        do{
            if(null==appSettingInfo){
                break;
            }
            
            List<AppSettingInfo> appSettingInfos=new ArrayList<AppSettingInfo>();
            appSettingInfos.add(appSettingInfo);
            insertSuccess=insert(appSettingInfos);
        }while(false);
        return insertSuccess;
    }
    
    public boolean insert(List<AppSettingInfo> appSettingInfos){
        boolean insertSuccess=false;
        do{
            if(null==appSettingInfos){
                insertSuccess=false;
                break;
            }
            List<AppSettingInfo> insertAppSettingInfos=new ArrayList<AppSettingInfo>();
            for(AppSettingInfo asi:appSettingInfos){
                if(mAppSettingInfoHashMap.containsKey(asi.getKey())==false){
                    insertAppSettingInfos.add(asi);
                }
            }
            
            if(insertAppSettingInfos.size()>0){
                
                insertSuccess=AppSettingInfoDataBaseHelper.getInstance().insert(appSettingInfos);
                if(true==insertSuccess){
                    for(AppSettingInfo asi:insertAppSettingInfos){
                        if(null!=mAppSettingInfoHashMap){
                            mAppSettingInfoHashMap.put(asi.getKey(), asi); 
                        }
                        
                        if(null!=mAppSettingInfos){
                            mAppSettingInfos.add(asi);
                        }
                    }
                }
            }
        }while(false);
        return insertSuccess;
    }
    
    /*end: insert*/
    /*start: delete*/
    /*end: delete*/
    /*start: query*/
    public boolean startLoadAppSettingInfo(OnAppSettingInfoLoad onAppSettingInfoLoad){
        this.mOnAppSettingInfoLoad=onAppSettingInfoLoad;
        
        if(LoadStatus.NOT_LOADED!=getAppSettingInfoLoadStatus()){
            return false;
        }
        
        mLoadTask= new AsyncTask<Object, Object, List<AppSettingInfo>>(){

            @Override
            protected List<AppSettingInfo> doInBackground(Object... params) {
                // TODO Auto-generated method stub
                return loadAppSettingInfos();
            }
            
            @Override
            protected void onPostExecute(List<AppSettingInfo> result) {
                mLoadTask = null;
                parseAppSettingInfos(result);
                super.onPostExecute(result);
            }
        }.execute(); 
        
        return true;
    }
    /*end: query*/
    /*start: update*/
    private boolean updateSetToTop(AppSettingInfo appSettingInfo,boolean isSetToTop){
        boolean updateSuccess=false;
        do{
            if(null==appSettingInfo){
                updateSuccess=false;
                break;
            }
            long setToTop=(true==isSetToTop)?(System.currentTimeMillis()):(0);
            if(mAppSettingInfoHashMap.containsKey(appSettingInfo.getKey())){
                updateSuccess=AppSettingInfoDataBaseHelper.getInstance().update(appSettingInfo, setToTop);
                if(true==updateSuccess){
                    appSettingInfo.setSetToTop(setToTop);
                }
            }else{
                long srcSetToTop=appSettingInfo.getSetToTop();
                appSettingInfo.setSetToTop(setToTop);
                updateSuccess=AppSettingInfoDataBaseHelper.getInstance().insert(appSettingInfo);
                if(false==updateSuccess){
                    appSettingInfo.setSetToTop(srcSetToTop);
                }else{
                    if(null!=mAppSettingInfoHashMap){
                        mAppSettingInfoHashMap.put(appSettingInfo.getKey(), appSettingInfo); 
                    }    
                }
            }
            
           
        }while(false);
        
        return updateSuccess;
    }
    /*end: update*/
    
    private void initAppSettingInfos(){
        if(null==mAppSettingInfos){
            mAppSettingInfos=new ArrayList<AppSettingInfo>();
        }else{
            mAppSettingInfos.clear();
        }
    }
    
    private void initAppSettingInfoHashMap(){
        if(null==mAppSettingInfoHashMap){
            mAppSettingInfoHashMap=new HashMap<String, AppSettingInfo>();
        }else{
            mAppSettingInfoHashMap.clear();
        }
    }
    
    private List<AppSettingInfo> loadAppSettingInfos(){
        initAppSettingInfos();
        return AppSettingInfoDataBaseHelper.getInstance().queryAllAppSettingInfo(mAppSettingInfos);
    }
    
    private void parseAppSettingInfos( List<AppSettingInfo> appSettingInfos){
        if (null == appSettingInfos) {
           setAppSettingInfoLoadStatus(LoadStatus.NOT_LOADED);
            if (null != mOnAppSettingInfoLoad) {
                mOnAppSettingInfoLoad.onAppSettingInfoLoadFailed();
            }
            
            return;
        }

        initAppSettingInfoHashMap();
        
        for(AppSettingInfo asi:appSettingInfos){

            mAppSettingInfoHashMap.put(asi.getKey(), asi);
        }
        
        //search(null,SettingsHelper.getInstance().getSearchMode());
        setAppSettingInfoLoadStatus(LoadStatus.LOAD_FINISH);
        
        if (null != mOnAppSettingInfoLoad) {
            mOnAppSettingInfoLoad.onAppSettingInfoLoadSuccess();
        }
    }
    
}
