package com.example.wealth.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.File;

public class CacheUtils {
    private static final String PREF_NAME = "app_cache";
    private static final Gson gson = new Gson();
    private static Context mContext;
    
    /**
     * 初始化方法
     */
    public static void getInstance(Context context) {
        mContext = context.getApplicationContext();
    }
    
    /**
     * 获取Context
     */
    private static Context getContext() {
        if (mContext == null) {
            throw new IllegalStateException("CacheUtils未初始化，请先调用g()方法");
        }
        return mContext;
    }
    
    /**
     * 保存数据到SharedPreferences
     */
    public static void put(String key, Object object) {
        put(getContext(), key, object);
    }
    
    /**
     * 保存数据到SharedPreferences（带Context参数）
     */
    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, gson.toJson(object));
        }
        
        editor.apply();
    }
    
    /**
     * 从SharedPreferences获取数据
     */
    public static Object get(String key, Object defaultObject) {
        return get(getContext(), key, defaultObject);
    }
    
    /**
     * 从SharedPreferences获取数据（带Context参数）
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        
        return null;
    }
    
    /**
     * 从SharedPreferences获取对象
     */
    public static <T> T getObject(Context context, String key, Class<T> clazz) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sp.getString(key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 清除SharedPreferences中的所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
    
    /**
     * 获取缓存大小
     */
    public static String getCacheSize(Context context) {
        long size = 0;
        // 获取内部缓存大小
        File internalCacheDir = context.getCacheDir();
        size += FileUtils.getFileSize(internalCacheDir);
        // 获取外部缓存大小
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            size += FileUtils.getFileSize(externalCacheDir);
        }
        return FileUtils.formatFileSize(size);
    }
} 