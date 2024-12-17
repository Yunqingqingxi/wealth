package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.response.CategoryReport;
import com.example.wealth.network.model.response.MonthlyReport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 报表统计相关API接口
 * 包含月度收支报表和支出分类分析
 */
public interface ReportApi {
    
    /**
     * 获取月度收支报表
     *
     * @param year 年份
     * @param month 月份（1-12）
     * @return 包含月度收支详情的响应
     */
    @GET("/api/reports/monthly")
    Call<BaseResponse<MonthlyReport>> getMonthlyReport(@Query("year") int year,
                                                      @Query("month") int month);
    
    /**
     * 获取支出分类分析报表
     *
     * @param startDate 开始日期，格式：YYYY-MM-DD
     * @param endDate 结束日期，格式：YYYY-MM-DD
     * @return 包含分类支出统计的响应
     */
    @GET("/api/reports/category")
    Call<BaseResponse<CategoryReport>> getCategoryReport(@Query("startDate") String startDate,
                                                       @Query("endDate") String endDate);
} 