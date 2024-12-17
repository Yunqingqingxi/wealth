package com.example.wealth.network.model.request;

import lombok.Data;

/**
 * 分类创建请求模型
 * 用于添加新的交易分类
 */
@Data
public class CategoryRequest {
    /** 分类名称 */
    private String name;
    /** 分类类型（INCOME/EXPENSE） */
    private String type;
    /** 分类图标 */
    private String icon;
    /** 分类颜色 */
    private String color;
    
    /**
     * 默认构造函数
     */
    public CategoryRequest() {
    }
    
    /**
     * 全参数构造函数
     *
     * @param name 分类名称
     * @param type 分类类型
     * @param icon 分类图标
     * @param color 分类颜色
     */
    public CategoryRequest(String name, String type, String icon, String color) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
    }
} 