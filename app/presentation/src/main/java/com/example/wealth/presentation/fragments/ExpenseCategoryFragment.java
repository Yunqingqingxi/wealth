package com.example.wealth.presentation.fragments;

import com.example.wealth.presentation.R;
import com.example.wealth.presentation.base.BaseCategoryFragment;
import com.example.wealth.presentation.model.CategoryItem;
import java.util.Arrays;
import java.util.List;

public class ExpenseCategoryFragment extends BaseCategoryFragment {
    
    @Override
    protected List<CategoryItem> getCategoryItems() {
        return Arrays.asList(
            new CategoryItem("餐饮", R.drawable.ic_food),
            new CategoryItem("购物", R.drawable.ic_shopping),
            new CategoryItem("日用", R.drawable.ic_daily),
            new CategoryItem("交通", R.drawable.ic_transport),
            new CategoryItem("蔬菜", R.drawable.ic_vegetable),
            new CategoryItem("水果", R.drawable.ic_fruit),
            new CategoryItem("零食", R.drawable.ic_snack),
            new CategoryItem("运动", R.drawable.ic_sports),
            new CategoryItem("娱乐", R.drawable.ic_entertainment),
            new CategoryItem("通讯", R.drawable.ic_communication),
            new CategoryItem("服饰", R.drawable.ic_clothing),
            new CategoryItem("美容", R.drawable.ic_beauty),
            new CategoryItem("住房", R.drawable.ic_house),
            new CategoryItem("居家", R.drawable.ic_furniture),
            new CategoryItem("孩子", R.drawable.ic_child),
            new CategoryItem("长辈", R.drawable.ic_elder),
            new CategoryItem("社交", R.drawable.ic_social),
            new CategoryItem("旅行", R.drawable.ic_travel),
            new CategoryItem("烟酒", R.drawable.ic_wine),
            new CategoryItem("数码", R.drawable.ic_digital),
            new CategoryItem("汽车", R.drawable.ic_car),
            new CategoryItem("医疗", R.drawable.ic_medical),
            new CategoryItem("书籍", R.drawable.ic_book),
            new CategoryItem("学习", R.drawable.ic_study),
            new CategoryItem("宠物", R.drawable.ic_pet),
            new CategoryItem("礼金", R.drawable.ic_gift_money),
            new CategoryItem("礼物", R.drawable.ic_gift),
            new CategoryItem("办公", R.drawable.ic_work),
            new CategoryItem("维修", R.drawable.ic_repair),
            new CategoryItem("情感", R.drawable.ic_love),
            new CategoryItem("票务", R.drawable.ic_ticket),
            new CategoryItem("家政", R.drawable.ic_home_service),
            new CategoryItem("快递", R.drawable.ic_express)
        );
    }
} 