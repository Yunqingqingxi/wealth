package com.example.wealth.network.model.request;

import lombok.Data;

/**
 * 用户登录请求模型
 * 支持使用用户名或邮箱登录
 */
@Data
public class LoginRequest {
    /** 邮箱地址 */
    private String email;
    /** 用户名 */
    private String username;
    /** 登录密码 */
    private String password;

    /**
     * 默认构造函数
     */
    public LoginRequest() {
    }

    /**
     * 使用邮箱登录的构造函数
     *
     * @param email 邮箱地址
     * @param password 登录密码
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * 使用用户名登录的构造函数
     *
     * @param username 用户名
     * @param password 登录密码
     * @param useUsername 标识使用用户名登录的标志参数
     */
    public LoginRequest(String username, String password, boolean useUsername) {
        this.username = username;
        this.password = password;
    }
} 