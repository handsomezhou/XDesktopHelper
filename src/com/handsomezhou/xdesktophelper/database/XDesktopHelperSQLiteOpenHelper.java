
package com.handsomezhou.xdesktophelper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class XDesktopHelperSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "XDesktopHelperSQLiteOpenHelper";
    private static XDesktopHelperSQLiteOpenHelper mInstance;

    private static final String CREATE_APP_INFO_TABLE = "create table "
            + XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE
            + "("
            + XDesktopHelperDatabase.AppSettingInfoColumns.ID + " integer unique,"
            + XDesktopHelperDatabase.AppSettingInfoColumns.KEY + " text,"
            + XDesktopHelperDatabase.AppSettingInfoColumns.SET_TO_TOP + " integer"
            + ")";
    
    private static final String CREATE_APP_START_RECORD_TABLE = "create table "
            + XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE
            + "("
            + XDesktopHelperDatabase.AppStartRecordColumns.ID + " integer unique,"
            + XDesktopHelperDatabase.AppStartRecordColumns.KEY + " text,"
            + XDesktopHelperDatabase.AppStartRecordColumns.START_TIME + " integer"
            + ")";

    public static synchronized XDesktopHelperSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new XDesktopHelperSQLiteOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation. make call to static method
     * "getInstance()" instead.
     */
    private XDesktopHelperSQLiteOpenHelper(Context context) {
        super(context, XDesktopHelperDatabase.DB_NAME, null,
                XDesktopHelperDatabase.DB_VERSION);
        // Log.i(TAG, "DB_NAME:"+OscillationWaveDatabase.DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_APP_START_RECORD_TABLE);
        db.execSQL(CREATE_APP_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ XDesktopHelperDatabase.Table.AppStartRecord.APP_START_RECORD_TABLE);
        db.execSQL("drop table if exists "+ XDesktopHelperDatabase.Table.AppSettingInfo.APP_INFO_TABLE);
        
        onCreate(db);
    }

}
