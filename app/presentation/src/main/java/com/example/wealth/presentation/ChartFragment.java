package com.example.wealth.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {
    private TextView tvTotalExpense;
    private TextView tvAverageExpense;
    private LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        // 初始化视图
        initViews(view);
        // 设置数据
        setData();
        // 配置图表
        setupChart();

        return view;
    }

    private void initViews(View view) {
        tvTotalExpense = view.findViewById(R.id.tvTotalExpense);
        tvAverageExpense = view.findViewById(R.id.tvAverageExpense);
        lineChart = view.findViewById(R.id.lineChart);

        // 设置示例数据
        tvTotalExpense.setText("总支出：85.00");
        tvAverageExpense.setText("平均值：28.33");
    }

    private void setupChart() {
        if (lineChart == null) return;

        // 配置X轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.parseColor("#666666"));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getDates()));
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);

        // 配置Y轴
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.parseColor("#EEEEEE"));
        leftAxis.setTextColor(Color.parseColor("#666666"));
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setLabelCount(5, true);

        // 禁用右侧Y轴
        lineChart.getAxisRight().setEnabled(false);

        // 其他配置
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(false);
        lineChart.setViewPortOffsets(50f, 0f, 30f, 30f);
    }

    private void setData() {
        if (lineChart == null) return;

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 0));  // 12-16
        entries.add(new Entry(1, 0));  // 12-17
        entries.add(new Entry(2, 85)); // 12-18
        entries.add(new Entry(3, 0));  // 12-19
        entries.add(new Entry(4, 0));  // 12-20
        entries.add(new Entry(5, 0));  // 12-21
        entries.add(new Entry(6, 0));  // 12-22

        LineDataSet dataSet = new LineDataSet(entries, "支出");
        dataSet.setColor(Color.BLACK);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.LINEAR);
        dataSet.setCubicIntensity(0.2f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        dates.add("12-16");
        dates.add("12-17");
        dates.add("12-18");
        dates.add("12-19");
        dates.add("12-20");
        dates.add("12-21");
        dates.add("12-22");
        return dates;
    }

    // ... 其他方法与 ChartActivity 相同
} 