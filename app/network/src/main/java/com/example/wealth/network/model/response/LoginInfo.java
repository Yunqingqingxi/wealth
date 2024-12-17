package com.example.wealth.network.model.response;

import lombok.Data;

@Data
public class LoginInfo {
    private String token;
    private String refreshToken;
    private UserInfo userInfo;

} 