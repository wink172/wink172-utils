package com.wink_172.library;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.tencent.mmkv.MMKV;

import org.xutils.x;

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


    public void initXutils(Context context,String[] urlArray) {
        x.Ext.init(x.app());
        // 全局默认信任所有https域名 或 仅添加信任的https域名
//         使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                if (TextUtils.isEmpty(hostname)) {
                    return false;
                }
                return !Arrays.asList(urlArray).contains(hostname);
            }
        });
    }

}