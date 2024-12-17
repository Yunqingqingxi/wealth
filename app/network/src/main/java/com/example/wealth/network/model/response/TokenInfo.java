package com.example.wealth.network.model.response;

import lombok.Data;

/**
 * 登录令牌响应模型
 * 包含访问令牌和刷新令牌信息
 */
@Data
public class TokenInfo {
    /** 访问令牌 */
    private String accessToken;
    /** 刷新令牌 */
    private String refreshToken;
    /** 访问令牌过期时间（秒） */
    private long expiresIn;
} 