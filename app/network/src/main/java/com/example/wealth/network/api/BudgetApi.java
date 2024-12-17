package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.BudgetRequest;
import com.example.wealth.network.model.response.BudgetInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 预算管理相关API接口
 * 用于设置和管理月度预算
 */
public interface BudgetApi {
    
    /**
     * 设置月度预算
     *
     * @param request 预算设置请求体，包含分类、金额、月份等信息
     * @return 包含预算详情的响应
     */
    @POST("/api/budgets")
    Call<BaseResponse<BudgetInfo>> setBudget(@Body BudgetRequest request);
} 