package com.example.financeuser;

import com.example.wealth.network.RetrofitClient;
import com.example.wealth.network.api.UserApi;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.ChangePasswordRequest;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.request.RegisterRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.network.model.response.UserInfo;

import retrofit2.Call;

public class UserImpl {
    private UserApi userApi;

    public UserImpl() {
        this.userApi = RetrofitClient.getInstance().createService(UserApi.class);
    }

    public Call<BaseResponse<UserInfo>> register(RegisterRequest request) {
        return userApi.register(request);
    }

    public Call<BaseResponse<TokenInfo>> login(LoginRequest request) {
        return userApi.login(request);
    }

    public Call<BaseResponse<Void>> changePassword(ChangePasswordRequest request) {
        return userApi.changePassword(request);
    }

    public Call<BaseResponse<UserInfo>> getUserInfo() {
        return userApi.getUserInfo();
    }

    public Call<BaseResponse<UserInfo>> updateAvatar(String avatarUrl) {
        return userApi.updateAvatar(avatarUrl);
    }

    public Call<BaseResponse<UserInfo>> updateUserInfo(Long userId, UserInfo userInfo) {
        return userApi.updateUserInfo(userId, userInfo);
    }

    public Call<BaseResponse<Boolean>> checkUsername(String username) {
        return userApi.checkUsername(username);
    }

    public Call<BaseResponse<Boolean>> checkEmail(String email) {
        return userApi.checkEmail(email);
    }
}
