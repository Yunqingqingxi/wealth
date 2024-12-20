package com.example.wealth.presentation.model;

import lombok.Data;

@Data
public class Transaction {
    private String id;
    private int iconResId;
    private String category;
    private double amount;
    private String date;
    
    // 主构造函数
    public Transaction(String id, int iconResId, String category, double amount, String date) {
        this.id = id;
        this.iconResId = iconResId;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // 简化构造函数 - 用于快速创建交易记录
    public Transaction(int iconResId, String category, double amount) {
        this.iconResId = iconResId;
        this.category = category;
        this.amount = amount;
        this.date = java.time.LocalDate.now().toString(); // 使用当前日期
        this.id = java.util.UUID.randomUUID().toString(); // 生成随机ID
    }
} 