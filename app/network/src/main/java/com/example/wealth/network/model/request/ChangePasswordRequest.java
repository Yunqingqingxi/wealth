package com.example.wealth.network.model.request;

import lombok.Data;

/**
 * 密码修改请求模型
 * 用于用户修改登录密码
 */
@Data
public class ChangePasswordRequest {
    /** 原密码 */
    private String oldPassword;
    /** 新密码 */
    private String newPassword;
    /** 确认新密码 */
    private String confirmPassword;

    /**
     * 默认构造函数
     */
    public ChangePasswordRequest() {
    }

    /**
     * 全参数构造函数
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param confirmPassword 确认新密码
     */
    public ChangePasswordRequest(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
} 