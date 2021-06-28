package com.wink_172.library.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public final static String FORMAT_YEAR = "yyyy";
    public final static String FORMAT_MONTH_DAY = "MM月dd日";
    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_TIME = "HH:mm";
    public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日  hh:mm";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_DATE_TIME02 = "yyyyMMddH:mm";
    public final static String FORMAT_DATE1_TIME = "yyyy/MM/dd HH:mm";
    public final static String FORMAT_DATE_TIME_SECOND = "yyyy/MM/dd HH:mm:ss";
    public final static String FORMAT_DATE_TIME_SECOND02 = "yyyyMMddHHmmss";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 365 * 24 * 60 * 60;// 年
    private static final int MONTH = 30 * 24 * 60 * 60;// 月
    private static final int DAY = 24 * 60 * 60;// 天
    private static final int HOUR = 60 * 60;// 小时
    private static final int MINUTE = 60;// 分钟

    public static String getTime02(Date date, String fotmat) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(fotmat);
        return format.format(date);
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_TIME);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    public static String getTimeYear(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getTimeMonth(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 距离秒字符串
     */
    public static long getDescriptionTimeFromTimestamp02(long timestamp) {
        long currentTime = System.currentTimeMillis();
        // 与现在时间相差总秒数
        long timeGap = (currentTime - timestamp) / 1000;
        Log.e(TAG, "getDescriptionTimeFromTimestamp02:====>> timestamp:" + timestamp + "     currentTime:" + currentTime + "    timeGap：" + timeGap);

        return timeGap;
    }


    public static String getTimeDay(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(date);
    }

    public static String getImageTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar imageTime = Calendar.getInstance();
        imageTime.setTimeInMillis(time);
        if (sameDay(calendar, imageTime)) {
            return "今天";
        } else if (sameWeek(calendar, imageTime)) {
            return "本周";
        } else if (sameMonth(calendar, imageTime)) {
            return "本月";
        } else {
            Date date = new Date(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
            return sdf.format(date);
        }
    }

    public static boolean sameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean sameWeek(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR);
    }

    public static boolean sameMonth(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 根据时间戳获取描述性时间，如3分钟后，1天后
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp3(long timestamp) {
        long currentTime = System.currentTimeMillis();
        // 与现在时间相差秒数
        long timeGap = (timestamp - currentTime) / 1000;
        System.out.println("timeGap: " + timeGap);
        String timeStr;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年后";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月后";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天后";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时后";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟后";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        // 与现在时间相差秒数
        long timeGap = (currentTime - timestamp) / 1000;
        System.out.println("timeGap: " + timeGap);
        String timeStr;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     */
    public static String getCurrentTime(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * date类型转换为String类型
     * formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * data Date类型的时间
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * long类型转换为String类型
     * currentTime要转换的long类型的时间
     * formatType要转换的string类型的时间格式
     */
    public static String longToString(long currentTime, String formatType) {
        String strTime;
        // long类型转成Date类型
        Date date = longToDate(currentTime, formatType);
        // date类型转成String
        strTime = dateToString(date, formatType);
        return strTime;
    }

    /**
     * string类型转换为date类型
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     */
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * long转换为Date类型
     * currentTime要转换的long类型的时间
     * formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     */
    public static Date longToDate(long currentTime, String formatType) {
        // 根据long类型的毫秒数生命一个date类型的时间
        Date dateOld = new Date(currentTime);
        // 把date类型的时间转换为string
        String sDateTime = dateToString(dateOld, formatType);
        // 把String类型转换为Date类型
        Date date = stringToDate(sDateTime, formatType);
        return date;
    }

    /**
     * string类型转换为long类型
     * strTime要转换的String类型的时间
     * formatType时间格式
     * strTime的时间格式和formatType的时间格式必须相同
     */
    public static long stringToLong(String strTime, String formatType) {
        // String类型转成date类型
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            return dateToLong(date);
        }
    }

    /**
     * date类型转换为long类型
     * date要转换的date类型的时间
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }
    //    public static String getTimeStr(long timeStamp){
//        if (timeStamp==0) return "";
//        Calendar inputTime = Calendar.getInstance();
//        inputTime.setTimeInMillis(timeStamp*1000);
//        Date currenTimeZone = inputTime.getTime();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        if (calendar.before(inputTime)){
//            //今天23:59在输入时间之前，解决一些时间误差，把当天时间显示到这里
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + MyApplication.getContext().getResources().getString(R.string.time_year)+"MM"+MyApplication.getContext().getResources().getString(R.string.time_month)+"dd"+MyApplication.getContext().getResources().getString(R.string.time_day));
//            return sdf.format(currenTimeZone);
//        }
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        if (calendar.before(inputTime)){
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//            return sdf.format(currenTimeZone);
//        }
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
//        if (calendar.before(inputTime)){
//            return MyApplication.getContext().getResources().getString(R.string.time_yesterday);
//        }else{
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            if (calendar.before(inputTime)){
//                SimpleDateFormat sdf = new SimpleDateFormat("M"+MyApplication.getContext().getResources().getString(R.string.time_month)+"d"+MyApplication.getContext().getResources().getString(R.string.time_day));
//                return sdf.format(currenTimeZone);
//            }else{
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + MyApplication.getContext().getResources().getString(R.string.time_year)+"MM"+MyApplication.getContext().getResources().getString(R.string.time_month)+"dd"+MyApplication.getContext().getResources().getString(R.string.time_day));
//                return sdf.format(currenTimeZone);
//
//            }
//
//        }
//
//    }

    private static final String TAG = "TAG";

    /**
     * 参数为秒
     * 返回 h:m:s后
     */
    public static String getStrBySecond(int time) {//30s====>>00:30
        return String.format("%02d m: %02d s", time / 60 * 60, time % 60);
    }

    //参数秒级
    public static String getSendTimeBySecond(long time) {//30s====>>00:30
        return String.format("%02d:%02d", time / 60, time % 60);
//        StringBuffer str = new StringBuffer();
//        if (time <= 60) {
//            if (time < 10) {
//                str.append("00:0" + time);
//            } else {
//                str.append("00:" + time);
//            }
//
//        } else {
//            //其他的后期加上
//        }
//
//        return str.toString();
    }

    /***********************************************************************/
    public static Calendar getTimeByStamp(long timeStamp) {//根据时间戳获取时间   success
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);
//        Log.e(TAG, "parseSetAlarmResult: ====>>" + inputTime.get(Calendar.YEAR) + " " + inputTime.get(Calendar.MONTH)
//                + " " + inputTime.get(Calendar.DAY_OF_MONTH) + " " +
//                inputTime.get(Calendar.HOUR_OF_DAY) + " " + inputTime.get(Calendar.MINUTE));
        return inputTime;
    }

    //获取当天的时间
    public static String[] getTimeStrInDay(long timeStamp) {//7:30 AM
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);

        String[] strings = new String[2];
        Date date = inputTime.getTime();

        int hour = date.getHours();
        int mintues = date.getMinutes();
        String m = "" + (mintues < 10 ? ("0" + mintues) : mintues);

        if (hour >= 12) {
            hour = hour - 12;
            String h = "" + (hour < 10 ? ("0" + hour) : hour);
            strings[0] = h + ":" + m;
            strings[1] = "PM";
        } else {
            String h = "" + (hour < 10 ? ("0" + hour) : hour);
            strings[0] = h + ":" + m;
            strings[1] = "AM";
        }
        return strings;
    }

    /**
     * 时间转化为聊天界面显示字符串
     *
     * @param timeStamp 单位为秒
     */
    public static String getChatTimeStr(long timeStamp) {
        if (timeStamp == 0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp * 1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        if (!calendar.after(inputTime)) {
            //当前时间在输入时间之前
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.before(inputTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "昨天" + " " + sdf.format(currenTimeZone);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("M" + "月" + "d" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日" + " HH:mm");
                return sdf.format(currenTimeZone);
            }
        }
    }

    public static long getTimeInterval(long currentTime, long plan_time) {//比较两个时间搓的间隔
        long interval = currentTime - plan_time;
        return interval;
    }

    public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);//month-1
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar;
    }

    /**
     * 获取该时间戳往前Y天加N个小时的时间戳
     */
    public static long getBeforeTimeStamp(long plan_time, int day, int hour) {
        long result = plan_time;

        long minute = 60;//s
        long oneHour = 60 * minute;//m
        long oneDay = 24 * oneHour;//h

        result -= oneDay * day + oneHour * hour;
        return result;
    }

    /**
     * 获取两个时间戳的间隔天数
     */
    public static int getIntervalDay(long currentTime, long plan_time) {//
        int result = 0;
        long interval = plan_time - currentTime / 1000;
        long minute = 60;//s
        long oneHour = 60 * minute;//m
        long oneDay = 24 * oneHour;//h

        if (interval > oneDay) {
            result = (int) (interval / oneDay);
        }

        return result;
    }

    /**
     * 参数 秒      70
     * 返回 d:h:m:s     00:00:01:10
     */
    public static String getSecondText(int second) {//00:00:52:00
        StringBuffer result = new StringBuffer();

        int day = second / (3600 * 24);
        int hour = (second - day * (3600 * 24)) / 3600;
        int min = (second - hour * 3600 - day * (3600 * 24)) / 60;
        int se = second % 60;

        if (day > 0) {
            result.append(day);
            result.append("D");
        }

        if (hour > 0) {
            result.append(hour);
            result.append("h");
        }

        if (min > 0) {
            if (min < 10) {
                result.append("0" + min);
            } else {
                result.append(min);
            }
            result.append("min");
        }

        if (se < 10) {
            result.append("0" + se);
        } else {
            result.append(se);
        }
        result.append("s");

        return result.toString();
    }

    public static String getIntervalLastString(String compareTime) {
        String result = "";
        long lastTime = Long.parseLong(compareTime);
        long currentTime = System.currentTimeMillis();
        long interval = (currentTime / 1000 - lastTime);//second 间隔秒

        long minute = 60;//s
        long oneHour = 60 * 60;//s
        long oneDay = 24 * oneHour;//h

        if (interval > oneDay) {
            result = interval / oneDay + "天前";
        } else if (interval < oneDay && interval >= oneHour) {
            result = (interval / oneHour) + "小时之前";
        } else if (interval < oneHour && interval >= minute) {
            result = (interval / minute) + "分钟之前";
        } else {
            result = "刚刚";
        }
        return result;
    }

    //   将时间戳转换为时间
    public static String stampToDate(String s) {
        String result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//"yyyy-MM-dd HH:mm:ss"
        long lt = Long.parseLong(s);
        result = simpleDateFormat.format(lt * 1000);
        return result;
    }

    //   将时间戳转换为时间   *1000  参数必须是毫秒级别的 *1000
    public static String stampToDate2(long s) {
        String result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy-MM-dd HH:mm:ss"
        result = simpleDateFormat.format(s);
        return result;
    }

    //   将时间戳转换为Date  1520402475
    public static Date stampToDate3(String s) throws ParseException {//fail
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//"yyyy-MM-dd HH:mm:ss"
        date = simpleDateFormat.parse(s);
        return date;
    }

    //   将时间转换为时间戳
    public static String dateToStamp(String s) throws ParseException {
        String result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//"yyyy-MM-dd HH:mm:ss"
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        result = String.valueOf(ts);
        return result;
    }

    public static int getMonthDays(int year, int month) {//获取该月的天数
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // 闰年2月29天
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }

        return days;
    }

    public static String getDateString() {//201712
        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH) + 1;
        String month_ = month < 10 ? "0" + month : "" + month;

        String result = "" + calendar.get(Calendar.YEAR) + month_ + "/";
        return result;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth2() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }


    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }


    public static int[] getWeekSunday(int year, int month, int day, int pervious) {
        int[] time = new int[3];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.DAY_OF_MONTH, pervious);
        time[0] = c.get(Calendar.YEAR);
        time[1] = c.get(Calendar.MONTH) + 1;
        time[2] = c.get(Calendar.DAY_OF_MONTH);
        return time;

    }

    public static int getWeekDayFromDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFromString(year, month));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    public static Date getDateFromString2(int year, int month, int day) {
        String dateString = year + "-" +
                (month > 9 ? month : ("0" + month)) + "-"
                + (day > 9 ? day : ("0" + day)) + " "
                + " 00:00:01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month)) + "-01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }


    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static SimpleDateFormat formatDay = new SimpleDateFormat("d", Locale.getDefault());
    private static SimpleDateFormat formatMonthDay = new SimpleDateFormat("M-d", Locale.getDefault());
    private static SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * 解析日期
     */
    public static Date parseDateTime(String datetime) {
        Date date = null;
        try {
            date = formatDateTime.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 以友好的方式显示时间
     */
    public static String friendlyTime(long timeStamp) {//sdate必须是毫秒级别的  *1000   success
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp);
        Date time = inputTime.getTime();
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = formatDate.format(cal.getTime());
        String paramDate = formatDate.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = formatDate.format(time);
        }
        return ftime;
    }

    /**
     * 根据日期获取当期是周几
     */
    public static String getWeek(Date date) {
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }


}
