package com.example.wealth.common.utils;

import android.os.Handler;
import android.os.Looper;

public class DebounceUtils {
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static Runnable lastRunnable;
    
    /**
     * 防抖函数，在指定时间内只执行最后一次
     * @param runnable 需要执行的任务
     * @param delayMillis 延迟时间（毫秒）
     */
    public static void debounce(Runnable runnable, long delayMillis) {
        if (lastRunnable != null) {
            mainHandler.removeCallbacks(lastRunnable);
        }
        lastRunnable = runnable;
        mainHandler.postDelayed(runnable, delayMillis);
    }
    
    /**
     * 清除待执行的防抖任务
     */
    public static void clear() {
        if (lastRunnable != null) {
            mainHandler.removeCallbacks(lastRunnable);
            lastRunnable = null;
        }
    }
} 