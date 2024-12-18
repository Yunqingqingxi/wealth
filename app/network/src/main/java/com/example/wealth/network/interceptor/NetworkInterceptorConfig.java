package com.example.wealth.network.interceptor;

import com.example.wealth.common.utils.CacheUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkInterceptorConfig {
    private static final String KEY_TOKEN = "user_token";  // 与UserImpl中保持一致

    /**
     * 配置通用请求头拦截器
     */
    public static Interceptor getHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", getToken())
                    .method(originalRequest.method(), originalRequest.body());

            return chain.proceed(builder.build());
        };
    }

    /**
     * 获取Token
     *
     * @return 用户token，如果未登录则返回空字符串
     */
    private static String getToken() {
        try {
            String token = (String) CacheUtils.get(KEY_TOKEN, "");
            return token != null ? token : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
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
     * 配置响应拦截器，用于获取token
     */
    public static Interceptor getResponseInterceptor() {
        return chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String token = response.header("Authorization");
            if (token != null && !token.isEmpty()) {
                try {
                    CacheUtils.put(KEY_TOKEN, token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return response;
        };
    }

    /**
     * 为OkHttpClient添加所有拦截器
     */
    public static OkHttpClient.Builder addInterceptors(OkHttpClient.Builder builder) {
        // 添加通用请求头拦截器
        builder.addInterceptor(getHeaderInterceptor());

        // 添加响应拦截器
        builder.addInterceptor(getResponseInterceptor());

        // 添加日志拦截器
        builder.addInterceptor(getLoggingInterceptor());

        return builder;
    }
}