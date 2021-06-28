package com.wink_172.library.utils;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
  *设备信息工具
 */
public class SystemInfoUtils {
    private static final String TAG = "SystemInfoUtils";

    public static Map<String, String> getInfo(Context context) {
        Map<String, String> infoMap = new LinkedHashMap<>();
        infoMap.put("设备品牌名称：", Build.BRAND);
        infoMap.put("设备名称：", Build.ID);
        infoMap.put("设备的型号：", Build.MODEL);
        infoMap.put("设备制造商：", Build.MANUFACTURER);
        infoMap.put("设备主板名称: ", Build.BOARD);
        infoMap.put("设备硬件名称：", Build.HARDWARE);
        infoMap.put("设备驱动名称：", Build.DEVICE);
        infoMap.put("Android ID：", Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        infoMap.put("身份识别码MEID：", getPhoneMEID(context));
        infoMap.put("设备身份码IMEI：", getPhoneIMEI(context));
        infoMap.put("序列号: ", getSerial());
        infoMap.put("网口 MAC：", getEth0MacAddress());
        infoMap.put("WIFI MAC：", getWiFiMacId(context));
        infoMap.put("设备的唯一标识：(由设备的多个信息拼接合成)", Build.FINGERPRINT);
        infoMap.put("Android版本：", Build.VERSION.RELEASE);
        infoMap.put("设备版本：", Build.VERSION.INCREMENTAL);
        infoMap.put("内核版本：", getKernelVersion());
        return infoMap;
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneMEID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        String meid = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e(TAG, "getPhoneMEID: " + tm.getMeid());
            meid = tm.getMeid();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            meid = tm.getDeviceId();
        }

        Log.e(TAG, "getPhoneMEID: ====>>"+meid );
        return meid;
    }
    @SuppressLint("MissingPermission")
    public static String getPhoneIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List<String> imeiList = new ArrayList<>();
        StringBuffer imeiStr = new StringBuffer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for (int slot = 0; slot < telephonyManager.getPhoneCount(); slot++) {
                String imei = telephonyManager.getImei(slot);
                Log.i(TAG, "getPhoneIMEI: " + imei + " " + telephonyManager.getImei() + " " + telephonyManager.getDeviceId());
                imeiList.add(imei);
            }
        } else {
            imeiList.add(telephonyManager.getDeviceId());
        }
        if (imeiList != null) {
            int count = 0;
            for (String imei : imeiList) {
                if (count > 0) {
                    imeiStr.append("|");
                }
                imeiStr.append(imei);
                count++;
            }
        }
        return imeiStr.toString();
    }

    public static String getWiFiMacId(Context context) {
        String macAddress = "02:00:00:00:00:02";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StringBuffer buf = new StringBuffer();
            NetworkInterface networkInterface = null;
            try {
                networkInterface = NetworkInterface.getByName("wlan0");
                if (networkInterface == null) {
                    Log.i(TAG, "getWiFiMacId: networkInterface == null");
                    return macAddress;
                }
                byte[] addr = networkInterface.getHardwareAddress();
                for (byte b : addr) {
                    buf.append(String.format("%02X:", b));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                macAddress = buf.toString();
            } catch (SocketException e) {
                e.printStackTrace();
                return macAddress;
            }
        } else {
            WifiManager mWifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (mWifi.isWifiEnabled()) {
                WifiInfo wifiInfo = mWifi.getConnectionInfo();
                macAddress = wifiInfo != null ? wifiInfo.getMacAddress() : "";
            }
        }
        return macAddress;
    }

    public static String getEth0MacAddress() {
        String macSerial = "";
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/eth0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    Log.i(TAG, "getEth0MacAddress: " + macSerial);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return macSerial;
    }

    public static String getKernelVersion() {
        String kernelVersion = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            kernelVersion = Build.VERSION.BASE_OS;
        }
        if (TextUtils.isEmpty(kernelVersion)) {
            kernelVersion = System.getProperty("os.version");
        }
        return kernelVersion;
    }

    @SuppressLint("MissingPermission")
    public static String getSerial() {
        String serial;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            serial = Build.getSerial();
        } else {
            serial = Build.SERIAL;
        }
        return serial;
    }

}

