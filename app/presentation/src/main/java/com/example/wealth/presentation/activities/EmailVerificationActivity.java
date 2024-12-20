package com.example.wealth.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wealth.presentation.databinding.ActivityEmailVerificationBinding;

public class EmailVerificationActivity extends AppCompatActivity {
    private ActivityEmailVerificationBinding binding;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        binding.ivBack.setOnClickListener(v -> finish());
        
        binding.btnSendCode.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "请输入有效的邮箱地址", Toast.LENGTH_SHORT).show();
                return;
            }
            // TODO: 调用发送验证码的API
            startCountDownTimer();
            Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        });

        binding.btnVerify.setOnClickListener(v -> {
            String code = binding.etVerificationCode.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (code.length() != 6) {
                Toast.makeText(this, "请输入6位验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            // TODO: 调用验证码验证API
            // 验证成功后跳转到登录页面
            Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void startCountDownTimer() {
        if (isTimerRunning) {
            return;
        }

        binding.btnSendCode.setEnabled(false);
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.btnSendCode.setText(millisUntilFinished / 1000 + "秒后重试");
                isTimerRunning = true;
            }

            @Override
            public void onFinish() {
                binding.btnSendCode.setText("发送验证码");
                binding.btnSendCode.setEnabled(true);
                isTimerRunning = false;
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
} 