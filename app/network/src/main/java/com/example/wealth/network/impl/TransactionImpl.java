package com.example.wealth.network.impl;

import com.example.wealth.network.RetrofitClient;
import com.example.wealth.network.api.TransactionApi;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.CategoryRequest;
import com.example.wealth.network.model.request.TransactionRequest;
import com.example.wealth.network.model.response.CategoryInfo;
import com.example.wealth.network.model.response.TransactionInfo;
import retrofit2.Call;

/**
 * 交易管理实现类
 * 封装了交易相关的所有网络请求操作，包括交易记录和分类管理
 */
public class TransactionImpl {
    /** API接口实例 */
    private final TransactionApi transactionApi;
    /** 单例实例 */
    private static TransactionImpl instance;

    /**
     * 获取单例实例
     */
    public static synchronized TransactionImpl getInstance() {
        if (instance == null) {
            instance = new TransactionImpl();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private TransactionImpl() {
        this.transactionApi = RetrofitClient.getInstance().createService(TransactionApi.class);
    }

    /**
     * 添加交易记录
     *
     * @param request 交易记录请求体，包含类型、金额、分类等信息
     * @return 包含交易记录信息的网络请求Call对象
     */
    public Call<BaseResponse<TransactionInfo>> addTransaction(TransactionRequest request) {
        return transactionApi.addTransaction(request);
    }

    /**
     * 添加交易分类
     *
     * @param request 分类信息请求体，包含名称、类型、图标等信息
     * @return 包含分类信息的网络请求Call对象
     */
    public Call<BaseResponse<CategoryInfo>> addCategory(CategoryRequest request) {
        return transactionApi.addCategory(request);
    }
} 