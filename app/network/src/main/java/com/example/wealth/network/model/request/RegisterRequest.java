package com.example.wealth.network.model.request;

import lombok.Data;

/**
 * 用户注册请求模型
 * 用于新用户注册
 */
@Data
public class RegisterRequest {
    /** 用户名 */
    private String username;
    /** 注册密码 */
    private String password;
    /** 邮箱地址 */
    private String email;

    /**
     * 默认构造函数
     */
    public RegisterRequest() {
    }

    /**
     * 全参数构造函数
     *
     * @param username 用户名
     * @param password 注册密码
     * @param email 邮箱地址
     */
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
} 