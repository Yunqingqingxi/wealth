package com.example.wealth.network.model.response;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 交易记录响应模型
 * 包含交易的完整信息
 */
@Data
public class TransactionInfo {
    /** 交易ID */
    private Long id;
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
    /** 记录创建时间 */
    private String createdAt;
} 