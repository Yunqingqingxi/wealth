package com.example.wealth;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载presentation模块中的主界面
        loadPresentationModule();
        finish();
    }

    private void loadPresentationModule() {
        // TODO: 使用类似这样的方式加载presentation模块的Activity
        // "com.example.wealth.presentation.ui.main.PresentationMainActivity"
        try {
            Class<?> presentationMainActivity = Class.forName("com.example.wealth.presentation.MainActivity");
            Intent intent = new Intent(this, presentationMainActivity);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // TODO: 处理异常情况
        }
    }
}