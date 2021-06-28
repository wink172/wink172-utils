package com.wink_172.library.model;

import android.os.Environment;

public class Constants {
    public static boolean TEST_MODEL = false;
    public static boolean SHOW_LANGUAGE_SETTING = true;//语言控制开关显示
    public static final int TIME_SAPCE_TEST = 10;//获取短信的间隔10秒
    public static final int TIME_SAPCE = 60;//获取短信的间隔10秒

    public static String TEST_PIC_URL = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1819216937,2118754409&fm=26&gp=0.jpg";
    public static final String TAG = "TAG";


    public static final String DOWNLOAD_FOLDER = "/download/";
    final public static String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOAD_FOLDER;




    /**
     * 网络请求方法名
     */
    final public static String task = "v1/user/task";
    final public static String app_notice = "v1/common/notice";




    public static String CropPath = "/sdcard/SelectImage/";
    public static String CropImageName = "crop.jpg";//裁剪缓存路径
    public static String CropVideoName = "cropVideo.mp4";//视频缓存路径
    public static String CropOutPutVideoName = "outputVideo.mp4";//视频缓存路径
    public static String ThumbnailImageName = "Thumbnail.jpg";//视频图


    public static final String PARAM_APPID = "appid";
    public static final String PARAM_DEVICE = "device";
    public static final String PARAM_DATA1 = "data1";
    public static final String PARAM_DATA2 = "data2";
    public static final String PARAM_DATA3 = "data3";
    public static final String PARAM_DATA4 = "data5";

    public static int COUNT = 20;
    public final static int VIDEO_COUNT = 3;
    public final static String max_num = "999999";

    /**
     * 动作事件类型
     */

    public final static int EVENT_01 = 117;
    public final static int EVENT_02 = 118;
    public final static int EVENT_03 = 119;
    public final static int EVENT_04 = 119;
    public final static int EVENT_05 = 119;
    public final static int EVENT_06 = 119;
    public final static int EVENT_07 = 119;

    public static final int ACTIVITY_REQUEST_CODE3001 = 3001;
    public static final int ACTIVITY_REQUEST_CODE3002 = 3002;
    public static final int ACTIVITY_REQUEST_CODE3003 = 3003;
    public static final int ACTIVITY_REQUEST_CODE3004 = 3004;
    public static final int ACTIVITY_REQUEST_CODE3005 = 3005;

    /**
     * sharePerfer 参数
     */
    public final static String LOGIN_INFO = "login_data";
    public final static String USER_INFO = "user_info";
    public final static String SEARCH_HISTORY = "search_history";

    public static boolean PRINT_LOG = false;//是否打印日志
}
