package com.handsomezhou.xdesktophelper.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
//import android.util.Log;

import com.handsomezhou.xdesktophelper.database.AppStartRecordDataBaseHelper;
import com.handsomezhou.xdesktophelper.model.AppInfo;
import com.handsomezhou.xdesktophelper.model.AppStartRecord;
import com.handsomezhou.xdesktophelper.model.LoadStatus;
import com.handsomezhou.xdesktophelper.util.AppCommonWeightsUtil;



public class AppStartRecordHelper {
    private static final String TAG=AppStartRecordHelper.class.getSimpleName();
    private static AppStartRecordHelper mInstance;
    private List<AppStartRecord> mAppStartRecords;
    private LoadStatus mAppStartRecordsLoadStatus;
    private AsyncTask<Object, Object, List<AppStartRecord>> mLoadAppStartRecordTask=null;
    private OnAppStartRecordLoad mOnAppStartRecordLoad;
    
   
    public interface OnAppStartRecordLoad{
        void onAppStartRecordSuccess();
        void onAppStartRecordFailed();
    }
    
    public static AppStartRecordHelper getInstance(){
        
        if(null==mInstance){
            mInstance=new AppStartRecordHelper();
        }
        
        return mInstance;
    }
    
    private AppStartRecordHelper(){
        initAppStartRecordHelper();
    }
    
    public LoadStatus getAppStartRecordsLoadStatus() {
        return mAppStartRecordsLoadStatus;
    }

    public void setAppStartRecordsLoadStatus(LoadStatus appStartRecordsLoadStatus) {
        mAppStartRecordsLoadStatus = appStartRecordsLoadStatus;
    }

    public OnAppStartRecordLoad getOnAppStartRecordLoad() {
        return mOnAppStartRecordLoad;
    }

    public void setOnAppStartRecordLoad(OnAppStartRecordLoad onAppStartRecordLoad) {
        mOnAppStartRecordLoad = onAppStartRecordLoad;
    }

    
    private void initAppStartRecordHelper(){
       if(null==mAppStartRecords){
           mAppStartRecords=new ArrayList<AppStartRecord>();
       }else{
           mAppStartRecords.clear();
       }
       setAppStartRecordsLoadStatus(LoadStatus.NOT_LOADED);
    }
    
    public boolean startLoadAppStartRecord(){
        if(true==isAppStartRecordLoading()){
            return false;
        }
        
        mLoadAppStartRecordTask=new AsyncTask<Object, Object, List<AppStartRecord>>(){

            @Override
            protected List<AppStartRecord> doInBackground(Object... params) {
                // TODO Auto-generated method stub
                return loadAppStartRecord();
            }

            @Override
            protected void onPostExecute(List<AppStartRecord> result) {
                parseAppStartRecord(result);
                mLoadAppStartRecordTask=null;
                super.onPostExecute(result);
               
            }
            
           
        }.execute();
        
        return true;
    }
    
    public boolean parseAppStartRecord(){
        boolean parseSuccess=false;
        
        do{
            AppInfo appInfo=null;
            long currentTimeMs=System.currentTimeMillis();
            if(mAppStartRecords.size()<=0){
                parseSuccess=false;
                break;
            }
            
            for(AppStartRecord asr:mAppStartRecords){
                if(true==AppInfoHelper.getInstance().getBaseAllAppInfosHashMap().containsKey(asr.getKey())){
                    appInfo= AppInfoHelper.getInstance().getBaseAllAppInfosHashMap().get(asr.getKey());
                    appInfo.setCommonWeights(appInfo.getCommonWeights()+AppCommonWeightsUtil.getCommonWeights(currentTimeMs, asr.getStartTime()));
                }
            }
            Collections.sort(AppInfoHelper.getInstance().getBaseAllAppInfos(), AppInfo.mSortByDefault);
           /* for(int i=0; i<AppInfoHelper.getInstance().getBaseAllAppInfos().size() ; i++){
                Log.i(TAG, AppInfoHelper.getInstance().getBaseAllAppInfos().get(i).getLabel()+":"+AppInfoHelper.getInstance().getBaseAllAppInfos().get(i).getCommonWeights());
            }*/
            parseSuccess=true;
        }while(false);
        
        return parseSuccess;
    }
    private boolean isAppStartRecordLoading(){
        return ((null!=mLoadAppStartRecordTask)&&(mLoadAppStartRecordTask.getStatus()==Status.RUNNING));

    }
    
    private List<AppStartRecord> loadAppStartRecord(){
        setAppStartRecordsLoadStatus(LoadStatus.LOADING);
        List<AppStartRecord> appStartRecords=new ArrayList<AppStartRecord>();
        AppStartRecordDataBaseHelper.getInstance().queryAllStocks(appStartRecords);
       // Log.i(TAG, "appStartRecords.size()="+appStartRecords.size());
      //  long currentTimeMs=System.currentTimeMillis();
//        for(AppStartRecord asr:appStartRecords){
//            Log.i(TAG, asr.getKey()+":"+asr.getStartTime()+":"+AppCommonWeightsUtil.getCommonWeights(currentTimeMs, asr.getStartTime()));
//             
//        }
        return appStartRecords;
    }
    
    private void parseAppStartRecord(List<AppStartRecord> appStartRecords){
        if(null==appStartRecords){
            setAppStartRecordsLoadStatus(LoadStatus.NOT_LOADED);
            if(null!=mOnAppStartRecordLoad){
                mOnAppStartRecordLoad.onAppStartRecordFailed();
            }
            return;
        }
        
        mAppStartRecords.clear();
        mAppStartRecords.addAll(appStartRecords);
        
        setAppStartRecordsLoadStatus(LoadStatus.LOAD_FINISH);
        if(null!=mOnAppStartRecordLoad){
            mOnAppStartRecordLoad.onAppStartRecordSuccess();
        }
        
      
    
    }
}
