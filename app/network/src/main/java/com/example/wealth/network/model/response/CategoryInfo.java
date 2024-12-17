package com.example.wealth.network.model.response;

import lombok.Data;

/**
 * 交易分类信息响应模型
 * 包含分类的基本信息和显示属性
 */
@Data
public class CategoryInfo {
    /** 分类ID */
    private Long id;
    /** 分类名称 */
    private String name;
    /** 分类类型（INCOME/EXPENSE） */
    private String type;
    /** 分类图标标识 */
    private String icon;
    /** 分类显示颜色（十六进制颜色值） */
    private String color;

    /**
     * 默认构造函数
     */
    public CategoryInfo() {
    }

    /**
     * 全参数构造函数
     *
     * @param id 分类ID
     * @param name 分类名称
     * @param type 分类类型
     * @param icon 分类图标
     * @param color 分类颜色
     */
    public CategoryInfo(Long id, String name, String type, String icon, String color) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
    }
} 