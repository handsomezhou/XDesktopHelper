package com.handsomezhou.xdesktophelper.util.database;

import com.handsomezhou.xdesktophelper.model.database.AppSettingInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2019/12/1.
 */

public class AppSettingInfoUtil {
    public static boolean update(List<AppSettingInfo> appSettingInfos){
        boolean updateSuccess=false;
        do{
            if(null==appSettingInfos||appSettingInfos.size()<=0){
                break;
            }
            DataSupport.saveAll(appSettingInfos);
            updateSuccess=true;
        }while (false);


        return updateSuccess;
    }

    public static boolean update(AppSettingInfo appSettingInfo){
        boolean updateSuccess=false;
        do{
            if(null==appSettingInfo){
                break;
            }
            List<AppSettingInfo> appSettingInfos=new ArrayList<>();
            appSettingInfos.add(appSettingInfo);
            updateSuccess= AppSettingInfoUtil.update(appSettingInfos);
        }while (false);

        return updateSuccess;
    }


    public static boolean deleteAll(){
        boolean deleteSuccess=false;
        deleteSuccess= DataSupport.deleteAll(AppSettingInfo.class)>=0;
        return deleteSuccess;
    }

    public static List<AppSettingInfo> findAll(){
        return DataSupport.findAll(AppSettingInfo.class);
    }

    public static  boolean update(final AppSettingInfo appSettingInfo, long setToTop){
        boolean updateSuccess=false;
        do{
            if(null==appSettingInfo){
                break;
            }

            appSettingInfo.setSetToTop(setToTop);
            updateSuccess=AppSettingInfoUtil.update(appSettingInfo);
        }while (false);
        return updateSuccess;
    }
}
