package com.example.wealth.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.LinearLayout;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    private LinearLayout navDetail, navChart, navDiscover, navProfile;
    private FrameLayout navAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


}