package com.example.financereport;

import com.example.wealth.network.RetrofitClient;
import com.example.wealth.network.api.ReportApi;
import com.example.wealth.network.model.BaseResponse;
import com.example.wealth.network.model.response.ReportInfo;
import retrofit2.Call;

/**
 * 报表管理实现类
 * 封装了报表相关的所有网络请求操作，包括月度和年度收支统计报表
 */
public class ReportImpl {
    /** API接口实例 */
    private final ReportApi reportApi;
    /** 单例实例 */
    private static ReportImpl instance;

    /**
     * 获取单例实例
     */
    public static synchronized ReportImpl getInstance() {
        if (instance == null) {
            instance = new ReportImpl();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private ReportImpl() {
        this.reportApi = RetrofitClient.getInstance().createService(ReportApi.class);
    }

    /**
     * 获取月度收支报表
     *
     * @param month 月份，格式：YYYY-MM
     * @return 包含月度收支统计信息的网络请求Call对象
     */
    public Call<BaseResponse<ReportInfo>> getMonthlyReport(String month) {
        return reportApi.getMonthlyReport(month);
    }

    /**
     * 获取年度收支报表
     *
     * @param year 年份，格式：YYYY
     * @return 包含年度收支统计信息的网络请求Call对象
     */
    public Call<BaseResponse<ReportInfo>> getYearlyReport(String year) {
        return reportApi.getYearlyReport(year);
    }
}