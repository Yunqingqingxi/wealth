package com.example.wealth.network.model.response;

import lombok.Data;

/**
 * 用户信息响应模型
 * 包含用户的基本信息
 */
@Data
public class UserInfo {
    /** 用户ID */
    private Long id;
    /** 用户名 */
    private String username;
    /** 邮箱地址 */
    private String email;
    /** 账号创建时间 */
    private String createdAt;
    /** 最后登录时间 */
    private String lastLoginAt;
    /** 用户头像 */
    private String avatar;
} 