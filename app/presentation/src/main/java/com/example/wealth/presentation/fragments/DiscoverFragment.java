package com.example.wealth.presentation.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wealth.presentation.R;

public class DiscoverFragment extends Fragment {
    private TextView btnSetBudget;
    private ImageView ivBudgetArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        
        btnSetBudget = view.findViewById(R.id.btn_set_budget);
        ivBudgetArrow = view.findViewById(R.id.iv_budget_arrow);

        // 检查是否已设置预算
        checkBudgetStatus();

        // 设置预算按钮点击事件
        btnSetBudget.setOnClickListener(v -> {
            // TODO: 打开设置预算界面
            // 设置成功后调用 updateBudgetView(true)
        });

        return view;
    }

    private void checkBudgetStatus() {
        // TODO: 从数据源获取预算状态
        boolean hasBudget = false; // 这里需要替换为实际的预算检查逻辑
        updateBudgetView(hasBudget);
    }

    private void updateBudgetView(boolean hasBudget) {
        if (hasBudget) {
            btnSetBudget.setVisibility(View.GONE);
            ivBudgetArrow.setVisibility(View.VISIBLE);
        } else {
            btnSetBudget.setVisibility(View.VISIBLE);
            ivBudgetArrow.setVisibility(View.GONE);
        }
    }
} 