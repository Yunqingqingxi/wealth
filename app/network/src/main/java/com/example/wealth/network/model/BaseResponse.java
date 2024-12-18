package com.example.wealth.network.model;

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
public class BaseResponse<T> {
    /** 响应状态码 */
    private int code;
    /** 响应消息 */
    private String message;
    /** 响应数据 */
    private T data;

    /**
     * 构造函数
     *
     * @param code 响应状态码
     * @param message 响应消息
     * @param data 响应数据
     */
    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 判断请求是否成功
     *
     * @return true表示请求成功，false表示请求失败
     */
    public boolean isSuccess() {
        return code == 200;
    }

    /**
     * 获取响应状态码
     *
     * @return 响应状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置响应状态码
     *
     * @param code 响应状态码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取响应消息
     *
     * @return 响应消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置响应消息
     *
     * @param message 响应消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取响应数据
     *
     * @return 响应数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置响应数据
     *
     * @param data 响应数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 创建一个成功的响应
     *
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功的响应对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "操作成功", data);
    }

    /**
     * 创建一个成功的响应（无数据）
     *
     * @param <T> 数据类型
     * @return 成功的响应对象
     */
    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    /**
     * 创建一个失败的响应
     *
     * @param code 错误码
     * @param message 错误信息
     * @param <T> 数据类型
     * @return 失败的响应对象
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    /**
     * 创建一个失败的响应（使用默认错误码500）
     *
     * @param message 错误信息
     * @param <T> 数据类型
     * @return 失败的响应对象
     */
    public static <T> BaseResponse<T> error(String message) {
        return error(500, message);
    }
}