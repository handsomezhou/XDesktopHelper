package com.handsomezhou.xdesktophelper.database;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.handsomezhou.xdesktophelper.application.XDesktopHelperApplication;
import com.handsomezhou.xdesktophelper.model.AppStartRecord;


public class AppStartRecordDataBaseHelper {
    private static AppStartRecordDataBaseHelper mInstance;
    private XDesktopHelperSQLiteOpenHelper mXDesktopHelperSQLiteOpenHelper;
    public static AppStartRecordDataBaseHelper getInstance(){
        if(null==mInstance){
            mInstance=new AppStartRecordDataBaseHelper();
        }
        
        return mInstance;
    }
    
    private AppStartRecordDataBaseHelper(){
        initAppStartRecordDateBaseHelper();
    }
    
    private void initAppStartRecordDateBaseHelper(){
        mXDesktopHelperSQLiteOpenHelper=XDesktopHelperSQLiteOpenHelper.getInstance(XDesktopHelperApplication.getContext());
        return;
    }
    
    /*start: create database*/
    public void createDatabase(){
        mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
    }
    /*end: create database*/
    
    /*start: insert*/
    public boolean insert(List<AppStartRecord> appStartRecords){
        boolean insertSuccess=false;
        do{
            if((null==appStartRecords)||appStartRecords.size()<1){
                insertSuccess=false;
                break;
            }
            
             SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
             if(null!=db){
                ContentValues conferenceMemberValues=new ContentValues();
                for(AppStartRecord asr:appStartRecords){
                    conferenceMemberValues.clear();
                    conferenceMemberValues.put(XDesktopHelperDatabase.AppStartRecordColumns.KEY,asr.getKey());
                    conferenceMemberValues.put(XDesktopHelperDatabase.AppStartRecordColumns.START_TIME,asr.getStartTime());
                   
                    db.insert(XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE, null, conferenceMemberValues);
                }
                db.close();
                insertSuccess=true;
             }
               
        }while(false);
        return insertSuccess;
    }
    
    public boolean insert(AppStartRecord appStartRecord){
        boolean insertSuccess=false;
        do{
            if(null==appStartRecord){
                insertSuccess=false;
                break;
            }
            
            List<AppStartRecord> appStartRecords=new ArrayList<AppStartRecord>();
            appStartRecords.add(appStartRecord);
            insertSuccess=insert(appStartRecords);
            
        }while(false);
    
        return insertSuccess;
    }
    
    /*start: delete*/
    public boolean delete(String key){
        boolean deleteSuccess=false;
        do{
            if(TextUtils.isEmpty(key)){
                deleteSuccess=false;
                break;
            }
            
              SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
              if(null!=db){
                  String whereClause=XDesktopHelperDatabase.AppStartRecordColumns.KEY+" =?";
                  String[] whereArgs=new String[]{key};
                  db.delete(XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE, whereClause, whereArgs);
                  db.close();
                  deleteSuccess=true;
              }

                
                
        }while(false);
        
        return deleteSuccess;
    }
    
    public boolean delete(AppStartRecord appStartRecord){
        boolean deleteSuccess=false;
        do{
            if(null==appStartRecord){
                deleteSuccess=false;
                break;
            }
            
              SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
              if(null!=db){
                  String whereClause=XDesktopHelperDatabase.AppStartRecordColumns.KEY+" =?";
                  String[] whereArgs=new String[]{appStartRecord.getKey()};
                  db.delete(XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE, whereClause, whereArgs);
                  db.close();
                  deleteSuccess=true;
              }

                
                
        }while(false);
        
        return deleteSuccess;
    }
    
    public boolean deleteAll(){
        boolean deleteSuccess=false;
        do{
              SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
              if(null!=db){
                  db.delete(XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE, null, null);
                  db.close();
                  deleteSuccess=true;
              }
                               
                
        }while(false);
        
        return deleteSuccess;
    }
    
    /*end: delete*/
    
    /*start: query*/
    public List<AppStartRecord> queryAllStocks(List<AppStartRecord> stocks){
        do{
            if(null==stocks){
                break;
            }
            stocks.clear();
            SQLiteDatabase db=mXDesktopHelperSQLiteOpenHelper.getWritableDatabase();
            if(null==db){ 
                break;
            }
            
            String[] appStartRecordColumns={
                    XDesktopHelperDatabase.AppStartRecordColumns.KEY,
                    XDesktopHelperDatabase.AppStartRecordColumns.START_TIME,
 
            };
            
            String appStartRecordOrderBy=XDesktopHelperDatabase.AppStartRecordColumns.KEY+" ASC";//" DESC";
            Cursor appStartRecordCursor=db.query(XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE, appStartRecordColumns, null, null, null, null, appStartRecordOrderBy);
            if(null!=appStartRecordCursor){
                int keyColumnIndex=appStartRecordCursor.getColumnIndex(appStartRecordColumns[0]);
                int startTimeColumnIndex=appStartRecordCursor.getColumnIndex(appStartRecordColumns[1]);
               
                while(appStartRecordCursor.moveToNext()){
                    AppStartRecord appStartRecord=new AppStartRecord();
                   
                    String key=appStartRecordCursor.getString(keyColumnIndex);
                    long startTime=appStartRecordCursor.getLong(startTimeColumnIndex);
                    
                    appStartRecord.setKey(key);
                    appStartRecord.setStartTime(startTime);
                   
                    stocks.add(appStartRecord);
                }
                appStartRecordCursor.close();
            }
            db.close();
        }while(false);
        
        return stocks;
    }
    /*end: query*/
    
    /*start: update*/
    
    
    
    /*end: update*/
}
