package com.wink_172.library.utils;

import android.content.Context;

import com.tencent.mmkv.MMKV;


/**
 * 腾讯开发的
 * 类似于 SharedPreferencesTool使用方式
 * 但是更加精简高效的存储方式
 *
 * */
public class MMKVTool {

    public final static int getInt(Context context, String key,int defValue) {
        MMKV kv = MMKV.defaultMMKV();
        int iValue = kv.decodeInt(key,defValue);//取
        return iValue;
    }

    public final static void setInt(Context context, String key, int value) {
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(key,value);
    }

    public final static Float getFloat(Context context, String key, float defValue) {
        MMKV kv = MMKV.defaultMMKV();
        float iValue = kv.decodeFloat(key,defValue);//取
        return iValue;
    }

    public final static void setFloat(Context context, String key, float value) {
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(key,value);
    }

    public final static Long getLong(Context context, String key, Long defValue) {
        MMKV kv = MMKV.defaultMMKV();
        long iValue = kv.decodeLong(key,defValue);//取
        return iValue;
    }

    public final static void setLong(Context context, String key, long value) {
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(key,value);
    }

    public final static String getString(Context context, String key, String defValue) {
        MMKV kv = MMKV.defaultMMKV();
        String iValue = kv.decodeString(key,defValue);//取
        return iValue;
    }

    public final static void setString(Context context, String key, String value) {
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(key,value);
    }

    public final static boolean getBoolean(Context context, String key, Boolean defValue) {
        MMKV kv = MMKV.defaultMMKV();
        boolean iValue = kv.decodeBool(key,defValue);//取
        return iValue;
    }

    public final static void setBoolean(Context context, String key, Boolean value) {
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(key,value);
    }

    public final static void remove(Context context, String key) {
        MMKV kv = MMKV.defaultMMKV();
        kv.remove(key);
    }

    public final static void clear(Context context) {
        MMKV kv = MMKV.defaultMMKV();
        kv.clearAll();
    }
}
