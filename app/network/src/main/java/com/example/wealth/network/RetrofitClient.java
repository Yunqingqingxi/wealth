package com.example.wealth.network;

import com.example.wealth.network.interceptor.NetworkInterceptorConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit客户端单例类
 * 用于创建和管理API服务接口的实例
 */
public class RetrofitClient {
    /** API基础URL */
    private static final String BASE_URL = "http://localhost:7079/";
    /** 单例实例 */
    private static RetrofitClient instance;
    /** Retrofit实例 */
    private final Retrofit retrofit;

    /**
     * 私有构造函数
     * 配置OkHttpClient和Retrofit实例
     */
    private RetrofitClient() {
        // 创建 OkHttpClient
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        // 添加所有拦截器
        NetworkInterceptorConfig.addInterceptors(httpClient);

        // 构建 Retrofit 实例
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    /**
     * 获取RetrofitClient单例实例
     *
     * @return RetrofitClient实例
     */
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    /**
     * 创建API服务接口实例
     *
     * @param serviceClass API服务接口类
     * @param <T> API服务接口类型
     * @return API服务接口实例
     */
    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
