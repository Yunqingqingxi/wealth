package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.request.RegisterRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.network.model.response.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 认证相关API接口
 * 包含登录、注册、用户信息和token刷新等接口方法
 */
public interface AuthApi {
    
    /**
     * 用户登录
     *
     * @param request 登录请求体，包含用户名和密码
     * @return 包含token和用户信息的响应
     */
    @POST("/api/auth/login")
    Call<BaseResponse<TokenInfo>> login(@Body LoginRequest request);
    
    /**
     * 用户注册
     *
     * @param request 注册请求体，包含用户名、密码、邮箱等信息
     * @return 包含用户信息的响应
     */
    @POST("/api/auth/register")
    Call<BaseResponse<UserInfo>> register(@Body RegisterRequest request);
    
    /**
     * 获取用户信息
     *
     * @param token 用户token
     * @return 包含用户详细信息的响应
     */
    @GET("/api/auth/userinfo")
    Call<BaseResponse<UserInfo>> getUserInfo(@Header("Authorization") String token);
    
    /**
     * 刷新token
     *
     * @param refreshToken 刷新token
     * @return 包含新token的响应
     */
    @POST("/api/auth/refresh")
    Call<BaseResponse<TokenInfo>> refreshToken(@Header("Authorization") String refreshToken);
}


