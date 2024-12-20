package com.example.wealth.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financeuser.UserImpl;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.request.RegisterRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.network.model.response.UserInfo;
import com.example.wealth.presentation.MainActivity;
import com.example.wealth.presentation.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private UserImpl userImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userImpl = UserImpl.getInstance();
        setupListeners();
    }

    private void setupListeners() {
        binding.btnRegister.setOnClickListener(v -> handleRegister());
        binding.tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void handleRegister() {
        String username = binding.etUsername.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        String confirmPassword = binding.etConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "请输入有效的邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "密码长度不能少于6位", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        
        binding.btnRegister.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);

        userImpl.register(registerRequest).enqueue(new Callback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<UserInfo>> call, 
                                 @NonNull Response<BaseResponse<UserInfo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BaseResponse<UserInfo> baseResponse = response.body();
                    if (baseResponse.getCode() == 200) {
                        // 注册成功后直接登录
                        handleLogin(username, password);
                    } else {
                        binding.btnRegister.setEnabled(true);
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this,
                                baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    binding.btnRegister.setEnabled(true);
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this,
                            "注册失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<UserInfo>> call, @NonNull Throwable t) {
                binding.btnRegister.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this,
                        "网络错误: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLogin(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password, true);
        
        userImpl.login(loginRequest).enqueue(new Callback<BaseResponse<TokenInfo>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<TokenInfo>> call,
                                 @NonNull Response<BaseResponse<TokenInfo>> response) {
                binding.btnRegister.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    BaseResponse<TokenInfo> baseResponse = response.body();
                    if (baseResponse.getCode() == 200) {
                        TokenInfo tokenInfo = baseResponse.getData();
                        if (tokenInfo != null) {
                            // 保存token
                            userImpl.saveToken(tokenInfo.getToken());
                            Toast.makeText(RegisterActivity.this,
                                    "注册成功并已登录", Toast.LENGTH_SHORT).show();
                            // 直接跳转到主界面
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "登录返回数据异常", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "登录失败，请手动登录", Toast.LENGTH_SHORT).show();
                    // 登录失败时跳转到登录界面
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<TokenInfo>> call, @NonNull Throwable t) {
                binding.btnRegister.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this,
                        "网络错误，请手动登录", Toast.LENGTH_SHORT).show();
                // 网络错误时跳转到登录界面
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
} 