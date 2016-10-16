
package com.handsomezhou.xdesktophelper.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil
{
    public static final String SharedPreferencesName = "SHARED_PREFERENCES_NAME";

    public static void putString(Context context, String key, String value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void putInt(Context context, String key, int value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static void putLong(Context context, String key, long value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public static void putFloat(Context context, String key, float value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    public static void putBoolean(Context context, String key, Boolean value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context context, String key)
    {

        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }

    public static int getInt(Context context, String key)
    {

        return getInt(context, key, 0);
    }

    public static int getInt(Context context, String key, int defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }

    public static long getLong(Context context, String key)
    {

        return getLong(context, key, 0);
    }

    public static long getLong(Context context, String key, long defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defValue);
    }

    public static float getFloat(Context context, String key)
    {

        return getFloat(context, key, 0);
    }

    public static float getFloat(Context context, String key, float defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defValue);
    }

    public static Boolean getBoolean(Context context, String key)
    {

        return getBoolean(context, key, false);
    }

    public static Boolean getBoolean(Context context, String key, boolean defValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void remove(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesName,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }

}
