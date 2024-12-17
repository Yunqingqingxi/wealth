package com.example.wealth.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault());
    
    /**
     * 将日期转换为字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_FORMAT);
    }
    
    /**
     * 将日期转换为指定格式的字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }
    
    /**
     * 将字符串转换为日期
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_FORMAT);
    }
    
    /**
     * 将字符串按指定格式转换为日期
     */
    public static Date parseDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 获取友好的时间显示
     */
    public static String getFriendlyTimeSpan(Date date) {
        if (date == null) return "";
        
        long diff = System.currentTimeMillis() - date.getTime();
        long minutes = diff / (1000 * 60);
        long hours = diff / (1000 * 60 * 60);
        long days = diff / (1000 * 60 * 60 * 24);
        
        if (minutes < 1) {
            return "刚刚";
        } else if (minutes < 60) {
            return minutes + "分钟前";
        } else if (hours < 24) {
            return hours + "小时前";
        } else if (days < 30) {
            return days + "天前";
        } else {
            return formatDate(date, "yyyy-MM-dd");
        }
    }
} 