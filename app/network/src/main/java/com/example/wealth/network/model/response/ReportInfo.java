package com.example.wealth.network.model.response;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 收支报表响应模型
 * 用于返回收支统计信息
 */
@Data
public class ReportInfo {
    /** 统计时间（月份YYYY-MM或年份YYYY） */
    private String period;

    /** 总收入 */
    private BigDecimal totalIncome;

    /** 总支出 */
    private BigDecimal totalExpense;

    /** 结余（收入-支出） */
    private BigDecimal balance;
}