package com.wink_172.library;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.mmkv.MMKV;


import java.util.Arrays;
import java.util.Stack;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class LibManager {
    private static LibManager instance;

    private LibManager() {
    }

    /**
     * 单一实例
     */
    public static LibManager initLibManager(Context context) {
        if (instance == null) {
            instance = new LibManager();

        }
        return instance;
    }


    /**
     * 初始化MMKV
     */
    public void initMMKV(Context context) {
        String rootDir = MMKV.initialize(context);
    }



}