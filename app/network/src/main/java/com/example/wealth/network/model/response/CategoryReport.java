package com.example.wealth.network.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 分类支出统计报表响应模型
 * 包含各分类的支出统计信息
 */
@Data
public class CategoryReport {
    /** 统计开始日期，格式：YYYY-MM-DD */
    private String startDate;
    /** 统计结束日期，格式：YYYY-MM-DD */
    private String endDate;
    /** 总支出金额 */
    private BigDecimal totalAmount;
    /** 各分类支出详情列表 */
    private List<CategoryDetail> details;

    /**
     * 分类支出详情内部类
     */
    @Data
    public static class CategoryDetail {
        /** 支出分类 */
        private String category;
        /** 支出金额 */
        private BigDecimal amount;
        /** 占总支出的百分比 */
        private double percentage;
    }
} 