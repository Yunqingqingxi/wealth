package com.example.wealth.network.model.response;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 预算信息响应模型
 * 包含预算详情及使用情况
 */
@Data
public class BudgetInfo {
    /** 预算ID */
    private Long id;
    /** 支出分类 */
    private String category;
    /** 预算金额 */
    private BigDecimal amount;
    /** 预算月份，格式：YYYY-MM */
    private String month;
    /** 当前已支出金额 */
    private BigDecimal spent;
    /** 剩余预算金额 */
    private BigDecimal remaining;
    /** 预算状态（NORMAL/WARNING/EXCEEDED） */
    private String status;

    /**
     * 默认构造函数
     */
    public BudgetInfo() {
    }

    /**
     * 全参数构造函数
     *
     * @param id 预算ID
     * @param category 支出分类
     * @param amount 预算金额
     * @param month 预算月份
     * @param spent 已支出金额
     * @param remaining 剩余金额
     * @param status 预算状态
     */
    public BudgetInfo(Long id, String category, BigDecimal amount, String month,
                     BigDecimal spent, BigDecimal remaining, String status) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.month = month;
        this.spent = spent;
        this.remaining = remaining;
        this.status = status;
    }
} 