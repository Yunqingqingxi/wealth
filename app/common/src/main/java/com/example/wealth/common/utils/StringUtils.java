package com.example.wealth.common.utils;

import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    
    /**
     * 检查字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * 检查字符串是否为有效的邮箱地址
     */
    public static boolean isValidEmail(String email) {
        return !isEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * 检查字符串是否为有效的手机号
     */
    public static boolean isValidPhoneNumber(String phone) {
        return !isEmpty(phone) && PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * 获取字符串的安全长度
     */
    public static int safeLength(String str) {
        return str == null ? 0 : str.length();
    }
    
    /**
     * 安全地截取字符串
     */
    public static String safeSubstring(String str, int start, int end) {
        if (str == null) return "";
        int len = str.length();
        if (start > len) return "";
        if (end > len) end = len;
        return str.substring(start, end);
    }
} 