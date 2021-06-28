package com.wink_172.library.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.net.http.SslError;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.SslErrorHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;


import com.wink_172.library.model.Constants;

import org.xutils.common.Callback;
import org.xutils.common.util.MD5;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class CommonUtils {
    private static final String TAG = "TAG";

    /**
     * 生成一维码后面的那几位数     功能1去除kg   2保留小数点后两位   3.去除小数点   4.去除前面n多个无用的0
     */
    public static String getGoodWeightBarStr(String weigth) {

        String strWeight = weigth.replace("kg", "");
        strWeight = new DecimalFormat("0.00").format(Double.parseDouble(strWeight));//格式化
        strWeight = strWeight.replace(".", "/");
        String[] gg = strWeight.split("/");

        if (gg == null || gg.length == 0) {
            return "00000";
        }

        if (gg[1].length() == 1) {
            strWeight = strWeight + "0";
        }

        strWeight = strWeight.replace("/", "").replace(" ", "");


        if (strWeight.length() == 1) {
            strWeight = "0000" + strWeight;
        } else if (strWeight.length() == 2) {
            strWeight = "000" + strWeight;
        } else if (strWeight.length() == 3) {
            strWeight = "00" + strWeight;
        } else if (strWeight.length() == 4) {
            strWeight = "0" + strWeight;
        }

        if (weigth.length() > 5) {
//            weigth.substring()
        }

        return strWeight;
    }


    /**
     * 功能1保留小数点后两位     4.去除前面n多个无用的0
     */
    public static String getGoodWeightStr(String weigth) {

//        if(TextUtils.isEmpty(weigth)){
//            ConmonUtils.showToast("getGoodWeightStr====>>重量为空");
//            return "";
//        }
        weigth = new DecimalFormat("0.00").format(Double.parseDouble(weigth));//格式化

        return weigth;
    }

    /**
     * 功能1保留小数点后两位     4.去除前面n多个无用的0
     */
    public static String getDoubleStr(double weigth) {

        String strWeight = new DecimalFormat("0.00").format(weigth);//格式化

        return strWeight;
    }

    /**
     * 格式化数字 保留两位小数
     * 前提是number已经有两位小数以上
     */
    public static String change(String number) {
        String result = BigDecimal.valueOf(Double.parseDouble(number)).stripTrailingZeros().toPlainString();
        Log.e(TAG, "change: ====>>number:" + number + "      result:" + result);
        return result;
    }



    /*
       校验邮箱格式
     * " \w"：匹配字母、数字、下划线。等价于'[A-Za-z0-9_]'。
     * "|"  : 或的意思，就是二选一
     * "*" : 出现0次或者多次
     * "+" : 出现1次或者多次
     * "{n,m}" : 至少出现n个，最多出现m个
     * "$" : 以前面的字符结束
     */
    public static boolean checkEmailFormat(String content) {
        String REGEX = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
        Pattern p = Pattern.compile(REGEX);
        Matcher matcher = p.matcher(content);

        return matcher.matches();
    }

    /**
     * 功能1保留小数点后两位     4.去除前面n多个无用的0
     */
    public static String getFormatStr(String weigth) {

        String strWeight = new DecimalFormat("0.00").format(Double.parseDouble(weigth));//格式化

        return strWeight;
    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    public static void showSslError(Activity activity, final SslErrorHandler handler, SslError error) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        String message = "SSL Certificate error.";
        int bb = error.getPrimaryError();
        if (Constants.TEST_MODEL) {
            bb = SslError.SSL_UNTRUSTED;
        }

        switch (bb) {
            case SslError.SSL_UNTRUSTED:
                message = "The certificate authority is not trusted.";
                break;
            case SslError.SSL_EXPIRED:
                message = "The certificate has expired.";
                break;
            case SslError.SSL_IDMISMATCH:
                message = "The certificate Hostname mismatch.";
                break;
            case SslError.SSL_NOTYETVALID:
                message = "The certificate is not yet valid.";
                break;
            case SslError.SSL_DATE_INVALID:
                message = "The date of the certificate is invalid";
                break;
            case SslError.SSL_INVALID:
            default:
                message = "A generic error occurred";
                break;
        }
        message += " Do you want to continue anyway?";
        builder.setTitle("SSL Certificate Error");
        builder.setMessage(message);

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.proceed();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 判断应用是否安装
     */
    public static boolean isContainPackName(String packName) {
        boolean isContainPack = false;
        try {
            PackageManager packageManager = x.app().getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(packName, PackageManager.GET_SIGNATURES);
            if (info != null) {
                isContainPack = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return isContainPack;
    }


    /**
     * 根据签名文件获取散列密钥
     * 注意: 如果debug和release签名文件不一致,需要重新生成配置到Facebook
     */
    public static String getKeyHash() {
        try {
            PackageInfo info = x.app().getPackageManager().getPackageInfo(
                    x.app().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            Log.e(TAG, "onCreate: ====>>package_name:" + x.app().getPackageName());
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e(TAG, "KeyHash:====>>" + key);
                return key;
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * SHA1获取
     */
    public static String getSHA1() {
        try {
            PackageInfo info = x.app().getPackageManager().getPackageInfo(
                    x.app().getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            Log.e(TAG, "getSHA1: ====>>" + hexString.toString());
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;

    }

    /**
     * 选择照片
     */
    public static void startActionPick(Activity activity, int request_code) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra("return-data", true);// 无论是否设置都不会返回bitmap对象
        activity.startActivityForResult(intent, request_code);
    }

    /**
     * 拍摄照片
     */
    public static void startTakePhoto(Activity activity, int request_code) {
        FileUtil.makeRootDirectory(Constants.CropPath);


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            //判断是否自定义路径并且是否合法
            File file = new File(Constants.CropPath, Constants.CropImageName);

            Log.e(TAG, "startTakePhoto: ====file:" + file.getAbsolutePath());

            Uri fileUri = UriUtils.getUri(activity, file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            activity.startActivityForResult(takePictureIntent, request_code);
        }
    }


    /**
     * 获取文件名及后缀
     */
    public static String getFileNameWithSuffix(String path) {
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        int start = path.lastIndexOf("/");
        if (start != -1) {
            return path.substring(start + 1);
        } else {
            return "";
        }
    }
    public static void startSystemBrowser(Context context, String url) {

        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            Uri contentUri = Uri.parse(url.trim());
            intent.setData(contentUri);

            if (hasbrowser(context, intent)) {
                intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
            }
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    public static boolean hasbrowser(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolve : list) {
            if (resolve.activityInfo.packageName.contains("com.android.browser")) {
                return true;
            }
        }
        return false;
    }




    //base64加密
    public static String getBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //base64解密
    public static String getFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 生成四位数字符串随机码
     */
    public static String generateWord() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字      
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static Bitmap getVideoThumbnail(String filePath, int width_, int height_, int kind) {
        Bitmap bitmap = null;
        try {
            bitmap = ThumbnailUtils.createVideoThumbnail(filePath, kind);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width_, height_,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap == null) return null;
        return bitmap;
    }

    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 获取当前设备运行网络IP
     */
    public static String getIp(Context context) {
        String ip = new String();
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            ip = int2ip(i);
            Log.e(TAG, "拿到的ip地址 " + ip);
        } catch (Exception ex) {

        }
        return ip;
    }





}
