package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.request.CategoryRequest;
import com.example.wealth.network.model.response.CategoryInfo;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

/**
 * 分类管理相关API接口
 */
public interface CategoryApi {

    /**
     * 添加分类
     *
     * @param request 分类信息请求体
     * @return 包含新建分类信息的响应
     */
    @POST("/api/categories")
    Call<BaseResponse<CategoryInfo>> addCategory(@Body CategoryRequest request);

    /**
     * 获取分类列表
     *
     * @param type 可选的分类类型过滤（INCOME/EXPENSE）
     * @return 包含分类列表的响应
     */
    @GET("/api/categories")
    Call<BaseResponse<List<CategoryInfo>>> getCategories(@Query("type") String type);

    /**
     * 更新分类
     *
     * @param categoryId 分类ID
     * @param request 更新的分类信息
     * @return 包含更新后分类信息的响应
     */
    @PUT("/api/categories/{categoryId}")
    Call<BaseResponse<CategoryInfo>> updateCategory(@Path("categoryId") Long categoryId,
                                                    @Body CategoryRequest request);

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     * @return 空响应
     */
    @DELETE("/api/categories/{categoryId}")
    Call<BaseResponse<Void>> deleteCategory(@Path("categoryId") Long categoryId);
}