package com.example.wealth.common.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUtils {
    private static final String TAG = "WealthApp";
    private static boolean isDebug = true;  // 默认为调试模式
    private static String logFilePath;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
    
    /**
     * 初始化日志工具
     * @param context 上下文
     * @param isDebugMode 是否为调试模式
     */
    public static void init(Context context, boolean isDebugMode) {
        isDebug = isDebugMode;
        if (!isDebug) {
            Toast.makeText(context, "日志已关闭", Toast.LENGTH_SHORT).show();
            // 在发布模式下，设置日志文件路径
            File logDir = new File(context.getExternalFilesDir(null), "logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            // 使用当前日期作为文件名
            Toast.makeText(context, "dd", Toast.LENGTH_SHORT).show();
            String fileName = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()) + ".log";
            logFilePath = new File(logDir, fileName).getAbsolutePath();
        }
    }

    public static void v(String msg) {
        log(Log.VERBOSE, null, msg);
    }

    public static void v(String tag, String msg) {
        log(Log.VERBOSE, tag, msg);
    }

    public static void d(String msg) {
        log(Log.DEBUG, null, msg);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg);
    }

    public static void i(String msg) {
        log(Log.INFO, null, msg);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg);
    }

    public static void w(String msg) {
        log(Log.WARN, null, msg);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, msg);
    }

    public static void e(String msg) {
        log(Log.ERROR, null, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg);
    }

    public static void e(String msg, Throwable tr) {
        log(Log.ERROR, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    private static void log(int priority, String tag, String msg) {
        String finalTag = tag == null ? TAG : tag;
        String timeStamp = sdf.format(new Date());
        String priorityStr = getPriorityString(priority);
        String threadInfo = Thread.currentThread().getName();
        String finalMsg = String.format("%s %s/%s [%s]: %s", timeStamp, priorityStr, finalTag, threadInfo, msg);

        if (isDebug) {
            // 调试模式下输出到Logcat
            Log.println(priority, finalTag, msg);
        } else {
            // 发布模式下写入文件
            writeToFile(finalMsg);
        }
    }

    private static String getPriorityString(int priority) {
        switch (priority) {
            case Log.VERBOSE: return "V";
            case Log.DEBUG: return "D";
            case Log.INFO: return "I";
            case Log.WARN: return "W";
            case Log.ERROR: return "E";
            default: return "?";
        }
    }

    private static synchronized void writeToFile(String msg) {
        if (logFilePath == null) return;

        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.append(msg).append('\n');
            writer.flush();
        } catch (IOException e) {
            Log.e(TAG, "写入日志文件失败: " + e.getMessage());
        }
    }

    /**
     * 获取日志文件内容
     */
    public static String getLogContent() {
        if (logFilePath == null) return "";
        
        File logFile = new File(logFilePath);
        if (!logFile.exists()) return "";
        
        try {
            return FileUtils.readFileToString(logFile);
        } catch (IOException e) {
            Log.e(TAG, "读取日志文件失败: " + e.getMessage());
            return "";
        }
    }

    /**
     * 清除日志文件
     */
    public static void clearLogFile() {
        if (logFilePath == null) return;
        
        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            logFile.delete();
        }
    }
} 