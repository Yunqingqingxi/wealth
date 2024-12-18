@Override
public void onFailure(@NonNull Call<BaseResponse<TokenInfo>> call, Throwable t) {
    binding.btnLogin.setEnabled(true);
    binding.progressBar.setVisibility(View.GONE);
    
    // 打印详细错误信息
    Log.e("LoginActivity", "网络请求失败", t);
    String errorMessage = "网络错误: " + t.getMessage();
    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
}

@Override
public void onResponse(@NonNull Call<BaseResponse<TokenInfo>> call, Response<BaseResponse<TokenInfo>> response) {
    binding.btnLogin.setEnabled(true);
    binding.progressBar.setVisibility(View.GONE);

    if (response.isSuccessful() && response.body() != null) {
        BaseResponse<TokenInfo> baseResponse = response.body();
        
        // 添加日志打印完整响应
        Log.d("LoginActivity", "完整响应: " + new Gson().toJson(baseResponse));
        
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