package com.handsomezhou.xdesktophelper.helper;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.Log;

import com.handsomezhou.xdesktophelper.database.AppStartRecordDateBaseHelper;
import com.handsomezhou.xdesktophelper.model.AppStartRecord;



public class AppStartRecordHelper {
    private static final String TAG="AppStartRecordHelper";
    private static AppStartRecordHelper mInstance;
    private List<AppStartRecord> mAppStartRecords;
    private AsyncTask<Object, Object, List<AppStartRecord>> mLoadAppStartRecordTask=null;
    public static AppStartRecordHelper getInstance(){
        
        if(null==mInstance){
            mInstance=new AppStartRecordHelper();
        }
        
        return mInstance;
    }
    
    private AppStartRecordHelper(){
        initAppStartRecordHelper();
    }
    
    private void initAppStartRecordHelper(){
       if(null==mAppStartRecords){
           mAppStartRecords=new ArrayList<AppStartRecord>();
       }else{
           mAppStartRecords.clear();
       }
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
            
        }.execute();
        
        return true;
    }
    
    private boolean isAppStartRecordLoading(){
        return ((null!=mLoadAppStartRecordTask)&&(mLoadAppStartRecordTask.getStatus()==Status.RUNNING));

    }
    
    private List<AppStartRecord> loadAppStartRecord(){
        List<AppStartRecord> appStartRecords=new ArrayList<AppStartRecord>();
        AppStartRecordDateBaseHelper.getInstance().queryAllStocks(appStartRecords);
        for(AppStartRecord asr:appStartRecords){
            Log.i(TAG, asr.getPackageName()+":"+asr.getStartTime());
        }
        return appStartRecords;
    }
}
