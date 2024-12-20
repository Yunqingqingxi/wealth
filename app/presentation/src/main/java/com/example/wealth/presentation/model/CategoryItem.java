package com.example.wealth.presentation.model;

public class CategoryItem {
    private String name;
    private int iconResId;

    public CategoryItem(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }
} 