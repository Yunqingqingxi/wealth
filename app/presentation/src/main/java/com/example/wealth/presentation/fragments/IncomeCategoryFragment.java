package com.example.wealth.presentation.fragments;

import com.example.wealth.presentation.R;
import com.example.wealth.presentation.base.BaseCategoryFragment;
import com.example.wealth.presentation.model.CategoryItem;
import java.util.Arrays;
import java.util.List;

public class IncomeCategoryFragment extends BaseCategoryFragment {
    
    @Override
    protected List<CategoryItem> getCategoryItems() {
        return Arrays.asList(
            new CategoryItem("工资", R.drawable.ic_salary),
            new CategoryItem("兼职", R.drawable.ic_part_time),
            new CategoryItem("理财", R.drawable.ic_investment),
            new CategoryItem("礼分", R.drawable.ic_gift_money),
            new CategoryItem("生意", R.drawable.ic_business),
            new CategoryItem("设置", R.drawable.ic_settings)
        );
    }
} 