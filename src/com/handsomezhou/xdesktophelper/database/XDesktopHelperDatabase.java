package com.handsomezhou.xdesktophelper.database;

import android.provider.BaseColumns;

public class XDesktopHelperDatabase {
    public static final String DB_NAME = "xdesktophelper.db";
    public static final int DB_VERSION = 2;
    
    public interface Table {

        public interface AppStartRecord{
            public final String APP_START_RECORD_TABLE = "app_start_record";
        }
        
        public interface AppSettingInfo{
            public final String APP_INFO_TABLE = "app_setting_info";
        }
       
    }

    public interface AppSettingInfoColumns extends BaseColumns {
        public final String ID="id";
        public final String KEY = "key";
        public final String SET_TO_TOP="set_to_top";//set set_to_top time(currentTimeMillis)
    }
   
    public interface AppStartRecordColumns extends BaseColumns {
        public final String ID="id";
        public final String KEY = "key";
        public final String START_TIME="start_time";// start time(currentTimeMillis)
       // public final String SET_TO_TOP="set_to_top";//set set_to_top time(currentTimeMillis)
    } 
}
