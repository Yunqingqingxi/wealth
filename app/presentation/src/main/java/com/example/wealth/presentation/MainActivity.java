package com.example.wealth.presentation;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout navDetail, navChart, navDiscover, navProfile;
    private ImageView ivDetail, ivChart, ivDiscover, ivProfile;
    private TextView tvDetail, tvChart, tvDiscover, tvProfile;

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

        ivDetail = findViewById(R.id.iv_detail);
        ivChart = findViewById(R.id.iv_chart);
        ivDiscover = findViewById(R.id.iv_discover);
        ivProfile = findViewById(R.id.iv_profile);

        tvDetail = findViewById(R.id.tv_detail);
        tvChart = findViewById(R.id.tv_chart);
        tvDiscover = findViewById(R.id.tv_discover);
        tvProfile = findViewById(R.id.tv_profile);
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