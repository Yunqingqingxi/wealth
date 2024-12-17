package com.example.wealth;

import android.app.Application;
import com.example.wealth.network.RetrofitClient;
import com.example.wealth.common.utils.CacheUtils;
import com.example.wealth.common.utils.LogUtils;

public class WealthApplication extends Application {
    private static WealthApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeComponents();
    }

    private void initializeComponents() {
        // 初始化日志工具（根据BuildConfig.DEBUG判断是否为调试模式）
        LogUtils.init(this, true);
        
        // 初始化网络客户端
        RetrofitClient.getInstance();
        
        // 初始化缓存工具
        CacheUtils.getInstance(this);
        
        // 可以添加其他初始化操作
        // 比如数据库、日志工具等
    }

    public static WealthApplication getInstance() {
        return instance;
    }
}
