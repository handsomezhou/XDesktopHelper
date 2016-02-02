package com.handsomezhou.xdesktophelper.database;

import android.provider.BaseColumns;

public class XDesktopHelperDatabase {
    public static final String DB_NAME = "xdesktophelper.db";
    public static final int DB_VERSION = 1;
    
    public interface Table {

        public interface AppStartRecord{
            public final String APP_START_RECORD_TABLE = "app_start_record";
        }
       
    }

    public interface AppStartRecordColumns extends BaseColumns {
        public final String ID="id";
        public final String PACKAGE_NAME = "package_name";
        public final String START_TIME="start_time";// start time(currentTimeMillis)
    }
    
   
}
