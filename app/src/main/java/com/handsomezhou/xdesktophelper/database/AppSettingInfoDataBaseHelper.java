package com.handsomezhou.xdesktophelper.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.model.AppSettingInfo;


public class AppSettingInfoDataBaseHelper {
	private static AppSettingInfoDataBaseHelper mInstance;
	 private XDesktopHelperSQLiteOpenHelper mXDesktopHelperSQLiteOpenHelper;
	 public static AppSettingInfoDataBaseHelper getInstance(){
		 if(null==mInstance){
			 mInstance=new AppSettingInfoDataBaseHelper();
		 }
		    
		 return mInstance;
	 }
		
	 private AppSettingInfoDataBaseHelper(){
		 initAppInfoDataBaseHelper();
	 }
		
	 private void initAppInfoDataBaseHelper(){
		 mXDesktopHelperSQLiteOpenHelper=XDesktopHelperSQLiteOpenHelper.getInstance(XDesktopHelperApplication.getContext());
		 return;
	 }
		
    /*start: create database*/
    public void createDatabase(){
        mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
    }
    /*end: create database*/
    
    /*start: insert*/
    public boolean insert(List<AppSettingInfo> appSettingInfo){
        boolean insertSuccess=false;
        do{
            if((null==appSettingInfo)||appSettingInfo.size()<1){
                insertSuccess=false;
                break;
            }
            
             SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
             if(null!=db){
                ContentValues contentValues=new ContentValues();
                for(AppSettingInfo asi:appSettingInfo){
                    contentValues.clear();
                    contentValues.put(XDesktopHelperDatabase.AppSettingInfoColumns.KEY,asi.getKey());
                    contentValues.put(XDesktopHelperDatabase.AppSettingInfoColumns.SET_TO_TOP,asi.getSetToTop());
                   
                    db.insert(XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE, null, contentValues);
                }
                db.close();
                insertSuccess=true;
             }
               
        }while(false);
        return insertSuccess;
    }
    
    public boolean insert(AppSettingInfo appSettingInfo){
        boolean insertSuccess=false;
        do{
            if(null==appSettingInfo){
                insertSuccess=false;
                break;
            }
            
            List<AppSettingInfo> appSettingInfos=new ArrayList<AppSettingInfo>();
            appSettingInfos.add(appSettingInfo);
            insertSuccess=insert(appSettingInfos);
            
        }while(false);
    
        return insertSuccess;
    }
    /*end: insert*/

    /*start: delete*/
    public boolean deleteAll(){
        boolean deleteSuccess=false;
        do{
            SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
            if(null!=db){
                db.delete(XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE, null, null);
                db.close();
                deleteSuccess=true;
            }


        }while(false);

        return deleteSuccess;
    }
    /*end: delete*/

    /*start: query*/
    public List<AppSettingInfo> queryAllAppSettingInfo(List<AppSettingInfo> appSettingInfos){
        do{
            if(null==appSettingInfos){
                break;
            }
            appSettingInfos.clear();
            SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
            if(null==db){ 
                break;
            }
            
            String[] appSettingInfosColumns={
                    XDesktopHelperDatabase.AppSettingInfoColumns.KEY,
                    XDesktopHelperDatabase.AppSettingInfoColumns.SET_TO_TOP,
 
            };
            
            String appSettingInfosOrderBy=XDesktopHelperDatabase.AppSettingInfoColumns.KEY+" ASC";//" DESC";
            Cursor appSettingInfosCursor=db.query(XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE, appSettingInfosColumns, null, null, null, null, appSettingInfosOrderBy);
            if(null!=appSettingInfosCursor){
                int keyColumnIndex=appSettingInfosCursor.getColumnIndex(appSettingInfosColumns[0]);
                int setToTopColumnIndex=appSettingInfosCursor.getColumnIndex(appSettingInfosColumns[1]);
               
                while(appSettingInfosCursor.moveToNext()){
                	AppSettingInfo appSettingInfo=new AppSettingInfo();
                   
                    String key=appSettingInfosCursor.getString(keyColumnIndex);
                    long setToTop=appSettingInfosCursor.getLong(setToTopColumnIndex);
                    
                    appSettingInfo.setKey(key);
                    appSettingInfo.setSetToTop(setToTop);
                   
                    appSettingInfos.add(appSettingInfo);
                }
                appSettingInfosCursor.close();
            }
            db.close();
        }while(false);
        
        return appSettingInfos;
    }
    /*end: query*/
    
    /*start: update*/
    public boolean update(final AppSettingInfo appSettingInfo,long setToTop){
    	boolean updateSuccess=false;
    	do{
    		if(null==appSettingInfo){
    			updateSuccess=false;
    			break;
    		}
    		SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
            if(null==db){ 
            	updateSuccess=false;
                break;
            }
            
            String whereClause=XDesktopHelperDatabase.AppSettingInfoColumns.KEY+" = ?";
            ContentValues values=new ContentValues();
            String[] whereArgs=new String[]{String.valueOf(appSettingInfo.getKey())};
            values.put(XDesktopHelperDatabase.AppSettingInfoColumns.SET_TO_TOP, setToTop);
            
            db.update(XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE, values, whereClause, whereArgs);
           
            db.close();
            updateSuccess=true;
    		
    	}while(false);
    	
    	return updateSuccess;
    	
    }
    /*end: update*/

}
