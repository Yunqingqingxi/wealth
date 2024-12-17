package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.ChangePasswordRequest;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.request.RegisterRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.network.model.response.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 用户管理相关API接口
 * 包含用户注册、登录、密码修改等功能
 */
public interface UserApi {

    /**
     * 用户注册
     *
     * @param request 注册信息请求体，包含用户名、密码、邮箱等信息
     * @return 包含用户信息的响应
     */
    @POST("/api/users/register")
    Call<BaseResponse<UserInfo>> register(@Body RegisterRequest request);

    /**
     * 用户登录
     *
     * @param request 登录信息请求体，可使用用户名或邮箱登录
     * @return 包含访问令牌的响应
     */
    @POST("/api/users/login")
    Call<BaseResponse<TokenInfo>> login(@Body LoginRequest request);

    /**
     * 修改密码
     *
     * @param request 密码修改请求体，包含旧密码和新密码
     * @return 空响应，仅包含状态码和消息
     */
    @POST("/api/users/change-password")
    Call<BaseResponse<Void>> changePassword(@Body ChangePasswordRequest request);

    /**
     * 获取当前用户信息
     *
     * @return 包含用户信息的响应
     */
    @GET("/api/users/me")
    Call<BaseResponse<UserInfo>> getUserInfo();

    /**
     * 更新用户头像
     *
     * @param avatarUrl 头像URL
     * @return 包含更新后的用户信息的响应
     */
    @PUT("/api/users/avatar")
    Call<BaseResponse<UserInfo>> updateAvatar(@Body String avatarUrl);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return 包含更新后的用户信息的响应
     */
    @PUT("/api/users/{userId}")
    Call<BaseResponse<UserInfo>> updateUserInfo(@Path("userId") Long userId, @Body UserInfo userInfo);

    /**
     * 检查用户名是否可用
     *
     * @param username 用户名
     * @return 包含可用状态的响应
     */
    @GET("/api/users/check-username/{username}")
    Call<BaseResponse<Boolean>> checkUsername(@Path("username") String username);

    /**
     * 检查邮箱是否可用
     *
     * @param email 邮箱
     * @return 包含可用状态的响应
     */
    @GET("/api/users/check-email/{email}")
    Call<BaseResponse<Boolean>> checkEmail(@Path("email") String email);
} 