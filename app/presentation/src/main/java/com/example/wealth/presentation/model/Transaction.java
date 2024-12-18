package com.example.wealth.presentation.model;

public class Transaction {
    private int iconResId;
    private String category;
    private double amount;
    private String date;
    private double dayTotal;

    public Transaction(int iconResId, String category, double amount) {
        this.iconResId = iconResId;
        this.category = category;
        this.amount = amount;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(double dayTotal) {
        this.dayTotal = dayTotal;
    }
} 