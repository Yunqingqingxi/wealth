package com.example.wealth;

import android.app.Application;

public class WealthApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在这里进行应用级初始化
        initializeComponents();
    }

    private void initializeComponents() {
        // TODO: 初始化各种组件，比如：
        // - 数据库
        // - 网络客户端
        // - 依赖注入
        // - 日志工具
    }
}
