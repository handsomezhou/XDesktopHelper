package com.handsomezhou.xdesktophelper.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.handsomezhou.xdesktophelper.R;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.PermissionItem;


/**
 * Created by zhoujq on 2018/12/5.
 */
public class PermissionUtil {
    /**
     * 是否需要动态申请权限
     */
    public static boolean needDynamicPermission(){
        boolean needDynamicPermission=false;
        needDynamicPermission=isGreaterOrEqualToAndroid6_0();

        return needDynamicPermission;
    }

    /**
     * 大于等于android
     * @return
     */
    public static boolean isGreaterOrEqualToAndroid6_0(){
        boolean notLessThanAndroid6_0=false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            notLessThanAndroid6_0=true;
        }

        return notLessThanAndroid6_0;
    }

    /**
     * 是否有读写存储器权限
     * @param context
     * @return
     */
    public static boolean hasWriteAndReadExternalStoragePermission(Context context){
        boolean hasWriteAndReadExternalStoragePermission=true;
        do{
            if(false==needDynamicPermission()){
                //hasWriteAndReadExternalStoragePermission=true;
                break;
            }

           int  permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                hasWriteAndReadExternalStoragePermission=false;
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                hasWriteAndReadExternalStoragePermission=false;
                break;
            }

        }while (false);

        return hasWriteAndReadExternalStoragePermission;
    }

    public static boolean hasRecordAudioPermission(Context context){
        boolean hasWriteAndReadExternalStoragePermission=true;
        do{
            if(false==needDynamicPermission()){
                //hasWriteAndReadExternalStoragePermission=true;
                break;
            }

            int  permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.RECORD_AUDIO);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                hasWriteAndReadExternalStoragePermission=false;
                break;
            }


        }while (false);

        return hasWriteAndReadExternalStoragePermission;
    }

   /* public static boolean hasSystemAlertWindowPermission(Context context){
        boolean hasPermission=true;
        do{
            if(false==needDynamicPermission()){
                //hasWriteAndReadExternalStoragePermission=true;
                break;
            }

            int  permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.SYSTEM_ALERT_WINDOW);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                hasPermission=false;
                break;
            }


        }while (false);

        return hasPermission;
    }*/
    /**
     *
     * @param activity
     */
    public static void reqWriteAndReadExternalStoragePermission(Activity activity) {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        int permissionCheck = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "read_external_storage", R.mipmap.ic_launcher));
        }

        permissionCheck = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "write_external_storage", R.mipmap.ic_launcher));
        }

        /**
         * 无页面直接申请
         */
        if (permissionItems.size() > 0) {
            String[] strs = PermissionUtil.getPermissionStrArray(permissionItems);
            ActivityCompat.requestPermissions(activity, strs, 0);
        }
    }

    public static void reqRecordAudioPermission(Activity activity) {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        int permissionCheck = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, activity.getString(R.string.record_audio_permission), R.mipmap.ic_launcher));
        }

        /**
         * 无页面直接申请
         */
        if (permissionItems.size() > 0) {
            String[] strs = PermissionUtil.getPermissionStrArray(permissionItems);
            ActivityCompat.requestPermissions(activity, strs, 0);
        }
    }

  /*  public static void reqSystemAlertWindowPermission(Activity activity) {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();

        int permissionCheck = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.SYSTEM_ALERT_WINDOW);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {

            permissionItems.add(new PermissionItem(Manifest.permission.SYSTEM_ALERT_WINDOW, activity.getString(R.string.system_alert_window_permission), R.mipmap.ic_launcher));
        }

        *//**
         * 无页面直接申请
         *//*
        if (permissionItems.size() > 0) {
            String[] strs = PermissionUtil.getPermissionStrArray(permissionItems);
            ActivityCompat.requestPermissions(activity, strs, 0);
        }
    }*/

    public static String[] getPermissionStrArray(List<PermissionItem> permissionItems ) {
        String[] str = new String[permissionItems.size()];
        for (int i = 0; i < permissionItems.size(); i++) {
            str[i] = permissionItems.get(i).Permission;
        }
        return str;
    }

    /**
     * 是否有所有权限
     * @param context
     * @return
     */
    public static boolean hasAllPermission(Context context){
        boolean hasAllPermission=false;

        do{
            if(false==needDynamicPermission()){
                hasAllPermission=true;
                break;
            }

            int permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.READ_CONTACTS);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.GET_ACCOUNTS);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }


            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.ACCESS_COARSE_LOCATION);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.RECORD_AUDIO);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.READ_PHONE_STATE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.CALL_PHONE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            permissionCheck = ContextCompat.checkSelfPermission(context,    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionCheck== PackageManager.PERMISSION_DENIED) {
                break;
            }

            hasAllPermission=true;

        }while (false);

        return hasAllPermission;
    }


}
