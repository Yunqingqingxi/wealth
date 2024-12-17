package com.example.wealth.network.model.request;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 交易记录请求模型
 * 用于添加收入或支出记录
 */
@Data
public class TransactionRequest {
    /** 交易类型（INCOME/EXPENSE） */
    private String type;
    /** 交易金额 */
    private BigDecimal amount;
    /** 交易分类 */
    private String category;
    /** 交易描述 */
    private String description;
    /** 交易日期，格式：YYYY-MM-DD */
    private String date;

    /**
     * 默认构造函数
     */
    public TransactionRequest() {
    }

    /**
     * 全参数构造函数
     *
     * @param type 交易类型
     * @param amount 交易金额
     * @param category 交易分类
     * @param description 交易描述
     * @param date 交易日期
     */
    public TransactionRequest(String type, BigDecimal amount, String category, 
                            String description, String date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }
} 