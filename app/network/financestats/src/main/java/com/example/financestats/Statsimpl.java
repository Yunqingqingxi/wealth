package com.example.financestats;

import com.example.wealth.network.model.response.TransactionInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 财务统计工具类
 * 提供交易数据的统计分析功能
 */
public class Statsimpl {
    /** 单例实例 */
    private static Statsimpl instance;

    /**
     * 获取单例实例
     */
    public static synchronized Statsimpl getInstance() {
        if (instance == null) {
            instance = new Statsimpl();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private Statsimpl() {
    }

    /**
     * 计算总支出
     */
    public BigDecimal calculateTotalExpenses(List<TransactionInfo> transactions) {
        return transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .map(TransactionInfo::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 计算总收入
     */
    public BigDecimal calculateTotalIncome(List<TransactionInfo> transactions) {
        return transactions.stream()
                .filter(t -> t.getType().equals("income"))
                .map(TransactionInfo::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 计算净收入（收入-支出）
     */
    public BigDecimal calculateNetIncome(List<TransactionInfo> transactions) {
        BigDecimal totalIncome = calculateTotalIncome(transactions);
        BigDecimal totalExpenses = calculateTotalExpenses(transactions);
        return totalIncome.subtract(totalExpenses);
    }

    /**
     * 按类别统计支出
     */
    public Map<String, BigDecimal> calculateExpensesByCategory(List<TransactionInfo> transactions) {
        return transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .collect(Collectors.groupingBy(
                        TransactionInfo::getCategory,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                TransactionInfo::getAmount,
                                BigDecimal::add
                        )
                ));
    }

    /**
     * 计算支出占比
     */
    public Map<String, Double> calculateExpensePercentages(List<TransactionInfo> transactions) {
        Map<String, BigDecimal> expensesByCategory = calculateExpensesByCategory(transactions);
        BigDecimal totalExpenses = calculateTotalExpenses(transactions);
        
        return expensesByCategory.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .divide(totalExpenses, 4, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100"))
                                .doubleValue()
                ));
    }
}
