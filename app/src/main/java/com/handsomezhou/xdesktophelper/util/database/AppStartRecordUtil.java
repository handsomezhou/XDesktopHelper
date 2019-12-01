package com.handsomezhou.xdesktophelper.util.database;

import com.handsomezhou.xdesktophelper.model.database.AppStartRecord;
import com.handsomezhou.xdesktophelper.util.CommonUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2019/12/1.
 */

public class AppStartRecordUtil {
    private static String DELETE_APP_START_RECORD_BY_KEY = AppStartRecord.KEY_KEY +" = ? ";

    public static boolean update(List<AppStartRecord> appStartRecords){
        boolean updateSuccess=false;
        do{
            if(null==appStartRecords||appStartRecords.size()<=0){
                break;
            }
            DataSupport.saveAll(appStartRecords);
            updateSuccess=true;
        }while (false);


        return updateSuccess;
    }

    public static boolean update(AppStartRecord appSettingInfo){
        boolean updateSuccess=false;
        do{
            if(null==appSettingInfo){
                break;
            }
            List<AppStartRecord> appSettingInfos=new ArrayList<>();
            appSettingInfos.add(appSettingInfo);
            updateSuccess= AppStartRecordUtil.update(appSettingInfos);
        }while (false);

        return updateSuccess;
    }

    public static boolean delete(String key){
        boolean deleteSuccess=false;

        do{
            if(true==CommonUtil.isEmpty(key)){
                break;
            }

            deleteSuccess=DataSupport.deleteAll(AppStartRecord.class, DELETE_APP_START_RECORD_BY_KEY , String.valueOf(key))>0;

        }while (false);

        return deleteSuccess;
    }

    public static boolean delete(AppStartRecord appStartRecord){
        boolean deleteSuccess=false;
        do{
            if(null==appStartRecord){
                break;
            }

            deleteSuccess=AppStartRecordUtil.delete(appStartRecord.getKey());
        }while (false);
        return deleteSuccess;
    }

    public static boolean deleteAll(){
        boolean deleteSuccess=false;
        deleteSuccess= DataSupport.deleteAll(AppStartRecord.class)>0;
        return deleteSuccess;
    }

    public static List<AppStartRecord> findAll(){
        return DataSupport.findAll(AppStartRecord.class);
    }


}
