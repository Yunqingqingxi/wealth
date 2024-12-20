package com.example.wealth.presentation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

import com.example.wealth.presentation.adapter.CategoryPagerAdapter;

public class AddRecordActivity extends AppCompatActivity {

    private TextView tvExpense;
    private TextView tvIncome;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        initViews();
        setupViewPager();
        setupListeners();
    }

    private void initViews() {
        tvExpense = findViewById(R.id.tv_expense);
        tvIncome = findViewById(R.id.tv_income);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setupViewPager() {
        CategoryPagerAdapter adapter = new CategoryPagerAdapter(this);
        viewPager.setAdapter(adapter);
        
        // 监听页面切换
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateTabSelection(position);
            }
        });
    }

    private void setupListeners() {
        tvExpense.setOnClickListener(v -> {
            viewPager.setCurrentItem(0, true);
        });

        tvIncome.setOnClickListener(v -> {
            viewPager.setCurrentItem(1, true);
        });

        findViewById(R.id.tv_cancel).setOnClickListener(v -> finish());
    }

    private void updateTabSelection(int position) {
        if (position == 0) {
            tvExpense.setTextColor(getResources().getColor(android.R.color.black));
            tvExpense.setTypeface(null, Typeface.BOLD);
            tvIncome.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvIncome.setTypeface(null, Typeface.NORMAL);
        } else {
            tvIncome.setTextColor(getResources().getColor(android.R.color.black));
            tvIncome.setTypeface(null, Typeface.BOLD);
            tvExpense.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tvExpense.setTypeface(null, Typeface.NORMAL);
        }
    }
} 