package com.example.wealth.presentation;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        // 隐藏ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 设置状态栏颜色为白色
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white, null));
        // 设置状态栏文字颜色为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 设置取消按钮
        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());

        // 设置确定按钮
        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            // TODO: 保存记账数据
            finish();
        });
    }
} 