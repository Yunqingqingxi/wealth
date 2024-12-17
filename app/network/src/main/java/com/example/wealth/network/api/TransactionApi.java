package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.CategoryRequest;
import com.example.wealth.network.model.request.TransactionRequest;
import com.example.wealth.network.model.response.CategoryInfo;
import com.example.wealth.network.model.response.TransactionInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 交易相关API接口
 * 包含交易记录和分类管理的接口方法
 */
public interface TransactionApi {
    
    /**
     * 添加交易记录
     *
     * @param request 交易记录请求体，包含类型、金额、分类等信息
     * @return 包含交易记录信息的响应
     */
    @POST("/api/transactions")
    Call<BaseResponse<TransactionInfo>> addTransaction(@Body TransactionRequest request);
    
    /**
     * 添加交易分类
     *
     * @param request 分类信息请求体，包含名称、类型、图标等信息
     * @return 包含分类信息的响应
     */
    @POST("/api/categories")
    Call<BaseResponse<CategoryInfo>> addCategory(@Body CategoryRequest request);
} 