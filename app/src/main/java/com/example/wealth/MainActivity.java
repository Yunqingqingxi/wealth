package com.example.wealth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wealth.common.utils.DeviceUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 检查网络状态
        if (!DeviceUtils.isNetworkAvailable(this)) {
            // 可以在这里处理网络不可用的情况
            // 比如显示提示对话框
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }

        // 检查是否已初始化
        if (WealthApplication.getInstance() == null) {
            // 如果 Application 未正确初始化，可以在这里处理
            return;
        }
        
        // 启动 presentation 模块的 MainActivity
        Intent intent = new Intent(this, com.example.wealth.presentation.MainActivity.class);
        startActivity(intent);
        finish();
    }
}