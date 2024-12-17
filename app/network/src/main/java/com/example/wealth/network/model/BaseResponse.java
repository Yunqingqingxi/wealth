package com.example.wealth.network.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API响应统一包装类
 * 用于统一处理所有API的响应格式
 *
 * @param <T> 响应数据的类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    /** 响应状态码 */
    private int code;
    /** 响应消息 */
    private String message;
    /** 响应数据 */
    private T data;

    /**
     * 判断请求是否成功
     *
     * @return true表示请求成功，false表示请求失败
     */
    public boolean isSuccess() {
        return code == 200;
    }
} 