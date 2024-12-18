package com.example.wealth.network.api;

import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.response.ReportInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 报表相关API接口
 * 用于获取收支统计报表
 */
public interface ReportApi {

    /**
     * 获取月度收支报表
     *
     * @param month 月份，格式：YYYY-MM
     * @return 包含月度收支统计信息的响应
     */
    @GET("/api/reports/monthly")
    Call<BaseResponse<ReportInfo>> getMonthlyReport(@Query("month") String month);

    /**
     * 获取年度收支报表
     *
     * @param year 年份，格式：YYYY
     * @return 包含年度收支统计信息的响应
     */
    @GET("/api/reports/yearly")
    Call<BaseResponse<ReportInfo>> getYearlyReport(@Query("year") String year);
}