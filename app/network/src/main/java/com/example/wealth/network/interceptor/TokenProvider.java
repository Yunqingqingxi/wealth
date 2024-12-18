package com.example.wealth.network.interceptor;

public interface TokenProvider {
    String getToken();
    void saveToken(String token);
}