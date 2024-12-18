package com.example.wealth.presentation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        // 检查登录状态
//        if (!isLoggedIn()) {
//            // 未登录，跳转到登录界面
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish(); // 结束当前 Activity
//            return;
//        }
        
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginButton), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    
    /**
     * 检查是否已登录
     * 这里先使用 SharedPreferences 简单实现，后续可以改用更安全的方式
     */
    private boolean isLoggedIn() {
        return getSharedPreferences("app_prefs", MODE_PRIVATE)
            .getBoolean("is_logged_in", false);
    }
}