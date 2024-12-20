package com.example.wealth.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.LinearLayout;
import android.widget.FrameLayout;

import com.example.financeuser.UserImpl;
import com.example.wealth.presentation.activities.AddRecordActivity;
import com.example.wealth.presentation.activities.LoginActivity;
import com.example.wealth.presentation.fragments.ChartFragment;
import com.example.wealth.presentation.fragments.DetailFragment;
import com.example.wealth.presentation.fragments.DiscoverFragment;
import com.example.wealth.presentation.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout navDetail, navChart, navDiscover, navProfile;
    private FrameLayout navAdd;
    private UserImpl userImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        userImpl = UserImpl.getInstance();
        
        // 检查登录状态
        if (!isUserLoggedIn()) {
            // 如果未登录，跳转到登录界面
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return;
        }
        
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
        
        // 默认选中明细
        updateNavSelection(navDetail);
        
        // 默认显示明细页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DetailFragment())
                .commit();
    }

    private void initViews() {
        navDetail = findViewById(R.id.nav_detail);
        navChart = findViewById(R.id.nav_chart);
        navDiscover = findViewById(R.id.nav_discover);
        navProfile = findViewById(R.id.nav_profile);
        navAdd = findViewById(R.id.nav_add);
    }

    private void setupListeners() {
        navDetail.setOnClickListener(v -> {
            updateNavSelection(navDetail);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DetailFragment())
                    .commit();
        });

        navChart.setOnClickListener(v -> {
            updateNavSelection(navChart);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ChartFragment())
                    .commit();
        });

        navDiscover.setOnClickListener(v -> {
            updateNavSelection(navDiscover);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DiscoverFragment())
                    .commit();
        });

        navProfile.setOnClickListener(v -> {
            updateNavSelection(navProfile);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();
        });

        navAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
            startActivity(intent);
        });


    }

    private void updateNavSelection(View selectedNav) {
        // 重置所有导航项
        navDetail.setSelected(false);
        navChart.setSelected(false);
        navDiscover.setSelected(false);
        navProfile.setSelected(false);

        // 设置选中项
        selectedNav.setSelected(true);
    }

    // 检查用户是否已登录的方法
    private boolean isUserLoggedIn() {
        return userImpl.getToken() != null && !userImpl.getToken().isEmpty();
    }

}