package com.wink_172.library.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 屏幕像素转换工具类
 */
public class DisplayUtil {
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDisplayDensity(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 移除该viewGoup的所有子元素以及每个子元素和其父亲的关系
     */
    public static void removeAllChilds(View group) {
        if (null == group) {
            return;
        }

        if (group instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) group).getChildCount(); i++) {
                removeAllChilds(((ViewGroup) group).getChildAt(i));
            }
            ((ViewGroup) group).removeAllViews();
        }
    }

    public static void setCustomParam(){
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewBinding.balanceView.getLayoutParams();
//        params.width = DisplayUtil.getScreenWidth(mContext) / 2 - DisplayUtil.dp2px(mContext, 20);
//        viewBinding.balanceView.setLayoutParams(params);
    }
}
