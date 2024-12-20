package com.example.wealth.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financeuser.UserImpl;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.presentation.MainActivity;
import com.example.wealth.presentation.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserImpl userImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userImpl = UserImpl.getInstance();
        setupListeners();
    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(v -> handleLogin());
        binding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }

    private void handleLogin() {
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(username, password,true);
        
        binding.btnLogin.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        userImpl.login(loginRequest).enqueue(new Callback<BaseResponse<TokenInfo>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<TokenInfo>> call, Response<BaseResponse<TokenInfo>> response) {
                binding.btnLogin.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    BaseResponse<TokenInfo> baseResponse = response.body();

                    // 添加日志打印完整响应
                    Log.d("LoginActivity", "完整响应Success: " + new Gson().toJson(baseResponse));

                    if (baseResponse.getCode() == 200) {
                        TokenInfo tokenInfo = baseResponse.getData();
                        if (tokenInfo != null) {
                            String token = tokenInfo.getToken();
                            // 保存token
                            userImpl.saveToken(token);

                            // 打印token用于调试
                            Log.d("LoginActivity", "获取到的Token: " + token);

                            Toast.makeText(LoginActivity.this,
                                    "登录成功", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Log.e("LoginActivity", "TokenInfo为空");
                            Toast.makeText(LoginActivity.this,
                                    "登录返回数据异常", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("LoginActivity", "业务错误: " + baseResponse.getMessage());
                        Toast.makeText(LoginActivity.this,
                                baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody() != null ?
                                response.errorBody().string() : "未知错误";
                        Log.e("LoginActivity", "HTTP错误: " + response.code() + ", body: " + errorBody);
                        Toast.makeText(LoginActivity.this,
                                "请求失败: " + response.code() + " " + errorBody,
                                Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this,
                                "登录失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<TokenInfo>> call, Throwable t) {
                binding.btnLogin.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);

                // 打印详细错误信息
                Log.e("LoginActivity", "网络请求失败", t);
                String errorMessage = "网络错误: " + t.getMessage();
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
