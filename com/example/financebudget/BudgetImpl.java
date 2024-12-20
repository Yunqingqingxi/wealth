package com.example.financebudget;

import com.example.wealth.network.RetrofitClient;
import com.example.wealth.network.api.BudgetApi;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.BudgetRequest;
import com.example.wealth.network.model.response.BudgetInfo;
import retrofit2.Call;

/**
 * 预算管理实现类
 * 封装了预算相关的所有网络请求操作，包括预算的设置和管理
 */
public class BudgetImpl {
    /** API接口实例 */
    private final BudgetApi budgetApi;
    /** 单例实例 */
    private static BudgetImpl instance;

    /**
     * 获取单例实例
     */
    public static synchronized BudgetImpl getInstance() {
        if (instance == null) {
            instance = new BudgetImpl();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private BudgetImpl() {
        this.budgetApi = RetrofitClient.getInstance().createService(BudgetApi.class);
    }

    /**
     * 设置月度预算
     *
     * @param request 预算设置请求体，包含分类、金额、月份等信息
     * @return 包含预算详情的网络请求Call对象
     */
    public Call<BaseResponse<BudgetInfo>> setBudget(BudgetRequest request) {
        return budgetApi.setBudget(request);
    }
} 