package com.example.wealth.network.interceptor;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkInterceptorConfig {
    
    /**
     * 配置通用请求头拦截器
     */
    public static Interceptor getHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    // 可以添加更多通用请求头
                    // .header("Authorization", "Bearer " + getToken())
                    .method(originalRequest.method(), originalRequest.body());
            
            return chain.proceed(builder.build());
        };
    }

    /**
     * 配置日志拦截器
     */
    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // 可以根据构建类型设置不同的日志级别
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    /**
     * 为OkHttpClient添加所有拦截器
     */
    public static OkHttpClient.Builder addInterceptors(OkHttpClient.Builder builder) {
        // 添加通用请求头拦截器
        builder.addInterceptor(getHeaderInterceptor());
        
        // 添加日志拦截器
        builder.addInterceptor(getLoggingInterceptor());
        
        return builder;
    }
} 