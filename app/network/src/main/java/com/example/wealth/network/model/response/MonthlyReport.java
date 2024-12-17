package com.example.wealth.network.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 月度收支报表响应模型
 * 包含月度收支汇总和明细
 */
@Data
public class MonthlyReport {
    /** 年份 */
    private int year;
    /** 月份 */
    private int month;
    /** 月度总收入 */
    private BigDecimal totalIncome;
    /** 月度总支出 */
    private BigDecimal totalExpense;
    /** 月度结余 */
    private BigDecimal balance;
    /** 收支明细列表 */
    private List<MonthlyDetail> details;

    /**
     * 收支明细内部类
     */
    @Data
    public static class MonthlyDetail {
        /** 交易日期，格式：YYYY-MM-DD */
        private String date;
        /** 交易类型（INCOME/EXPENSE） */
        private String type;
        /** 交易分类 */
        private String category;
        /** 交易金额 */
        private BigDecimal amount;
        /** 交易描述 */
        private String description;
    }
} 