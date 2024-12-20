package com.example.financeuser;

import com.example.wealth.network.RetrofitClient;
import com.example.wealth.network.api.UserApi;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.ChangePasswordRequest;
import com.example.wealth.network.model.request.LoginRequest;
import com.example.wealth.network.model.request.RegisterRequest;
import com.example.wealth.network.model.response.TokenInfo;
import com.example.wealth.network.model.response.UserInfo;
import com.example.wealth.common.utils.CacheUtils;

import retrofit2.Call;

/**
 * 用户管理实现类
 * 封装了用户相关的所有网络请求操作，包括注册、登录、信息更新等功能
 */
public class UserImpl {
    private final UserApi userApi;
    private static UserImpl instance;
    private static final String KEY_TOKEN = "user_token";  // token的存储键名

    /**
     * 获取单例实例
     */
    public static synchronized UserImpl getInstance() {
        if (instance == null) {
            instance = new UserImpl();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private UserImpl() {
        this.userApi = RetrofitClient.getInstance().createService(UserApi.class);
    }

    /**
     * 用户注册
     *
     * @param request 注册请求，包含用户名、密码等注册信息
     * @return 包含用户信息的网络请求Call对象
     */
    public Call<BaseResponse<UserInfo>> register(RegisterRequest request) {
        return userApi.register(request);
    }

    /**
     * 用户登录
     *
     * @param request 登录请求，包含用户名/邮箱和密码
     * @return 包含登录令牌信息的网络请求Call对象
     */
    public Call<BaseResponse<TokenInfo>> login(LoginRequest request) {
        return userApi.login(request);
    }

    /**
     * 修改用户密码
     *
     * @param request 密码修改请求，包含旧密码和新密码
     * @return 修改结果的网络请求Call对象
     */
    public Call<BaseResponse<Void>> changePassword(ChangePasswordRequest request) {
        return userApi.changePassword(request);
    }

    /**
     * 获取当前用户信息
     *
     * @return 包含用户信息的网络请求Call对象
     */
    public Call<BaseResponse<UserInfo>> getUserInfo() {
        return userApi.getUserInfo();
    }

    /**
     * 更新用户头像
     *
     * @param avatarUrl 新的头像URL地址
     * @return 包含更新后用户信息的网络请求Call对象
     */
    public Call<BaseResponse<UserInfo>> updateAvatar(String avatarUrl) {
        return userApi.updateAvatar(avatarUrl);
    }

    /**
     * 更新用户信息
     *
     * @param userId 要更新的用户ID
     * @param userInfo 新的用户信息
     * @return 包含更新后用户信息的网络请求Call对象
     */
    public Call<BaseResponse<UserInfo>> updateUserInfo(Long userId, UserInfo userInfo) {
        return userApi.updateUserInfo(userId, userInfo);
    }

    /**
     * 检查用户名是否可用
     *
     * @param username 要检查的用户名
     * @return 包含用户名可用状态的网络请求Call对象
     */
    public Call<BaseResponse<Boolean>> checkUsername(String username) {
        return userApi.checkUsername(username);
    }

    /**
     * 检查邮箱是否可用
     *
     * @param email 要检查的邮箱地址
     * @return 包含邮箱可用状态的网址请求Call对象
     */
    public Call<BaseResponse<Boolean>> checkEmail(String email) {
        return userApi.checkEmail(email);
    }

    /**
     * 实现接口方法，获取token
     *
     * @return 当前保存的token，如果未登录则返回null
     */
    public String getToken() {
        return (String) CacheUtils.get(KEY_TOKEN, "");
    }

    /**
     * 实现接口方法，保存token
     *
     * @param token 要保存的token
     */
    public void saveToken(String token) {
        CacheUtils.put(KEY_TOKEN, token);
    }

    /**
     * 清除token
     */
    public void clearToken() {
        CacheUtils.put(KEY_TOKEN, "");
    }
}
