package com.wink_172.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.xutils.x;

/**
 * 暂时被MMKV替换
 * 保留代码 说不定以后会用到
 * */
public class SharedPreferencesTool {
    private static String SHARE_PREFERENCE_FILE_NAME=x.app().getPackageName();

    //  http://blog.csdn.net/baidu_31093133/article/details/51476726
    public final static int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }

    public final static void setInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public final static Float getFloat(Context context, String key, float defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defValue);
    }

    public final static void setFloat(Context context, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    public final static Long getLong(Context context, String key, Long defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    public final static void setLong(Context context, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public final static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }

    public final static void setString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public final static boolean getBoolean(Context context, String value, Boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(value, defValue);
    }

    public final static void setBoolean(Context context, String key, Boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value).commit();
    }

    public final static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key).commit();
    }

    public final static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }
}
