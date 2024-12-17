package com.example.wealth.network.model.request;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 预算设置请求模型
 * 用于创建或更新月度预算
 */
@Data
public class BudgetRequest {
    /** 支出分类 */
    private String category;
    /** 预算金额 */
    private BigDecimal amount;
    /** 预算月份，格式：YYYY-MM */
    private String month;

    /**
     * 默认构造函数
     */
    public BudgetRequest() {
    }

    /**
     * 全参数构造函数
     *
     * @param category 支出分类
     * @param amount 预算金额
     * @param month 预算月份
     */
    public BudgetRequest(String category, BigDecimal amount, String month) {
        this.category = category;
        this.amount = amount;
        this.month = month;
    }
}