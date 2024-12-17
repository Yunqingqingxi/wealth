package com.example.wealth.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    
    /**
     * 将对象转换为JSON字符串
     */
    public static String toJson(Object obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将JSON字符串转换为对象
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将JSON字符串转换为对象列表
     */
    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        try {
            Type listType = TypeToken.getParameterized(List.class, classOfT).getType();
            return gson.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 检查字符串是否为有效的JSON格式
     */
    public static boolean isValidJson(String json) {
        try {
            gson.fromJson(json, Object.class);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
} 