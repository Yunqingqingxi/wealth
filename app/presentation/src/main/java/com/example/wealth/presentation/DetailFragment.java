package com.example.wealth.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.example.wealth.presentation.adapter.TransactionAdapter;
import com.example.wealth.presentation.model.Transaction;
import com.example.wealth.presentation.IncomeDetailActivity;
import com.example.wealth.presentation.ExpenseDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    private TextView tvIncome, tvExpense;
    private RecyclerView rvTransactions;
    private TransactionAdapter adapter;
    private LinearLayout datePickerLayout;
    private TextView yearMonthText;
    private TextView tvYear;
    private LinearLayout incomeLayout, expenseLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initViews(view);
        setupRecyclerView();
        setupClickListeners();
        loadData();
        return view;
    }

    private void initViews(View view) {
        // 初始化视图
        tvIncome = view.findViewById(R.id.tvIncome);
        tvExpense = view.findViewById(R.id.tvExpense);
        rvTransactions = view.findViewById(R.id.rvTransactions);
        
        // 添加收入支出区域的布局引用
        incomeLayout = view.findViewById(R.id.income_layout);
        expenseLayout = view.findViewById(R.id.expense_layout);

        // 添加日期选择器相关的初始化
        datePickerLayout = view.findViewById(R.id.date_picker_layout);
        yearMonthText = view.findViewById(R.id.year_month_text);
        tvYear = view.findViewById(R.id.tv_year);

        // 设置初始数据
        tvIncome.setText("0.00");
        tvExpense.setText("55696.00");
        tvYear.setText("2024年");
        yearMonthText.setText("12月");
    }

    private void setupRecyclerView() {
        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TransactionAdapter(new ArrayList<>());
        rvTransactions.setAdapter(adapter);
    }

    private void setupClickListeners() {
        datePickerLayout.setOnClickListener(v -> showDatePicker());

        // 添加收入区域点击事件
        incomeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), IncomeDetailActivity.class);
            startActivity(intent);
        });

        // 添加支出区域点击事件
        expenseLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ExpenseDetailActivity.class);
            startActivity(intent);
        });
    }

    private void loadData() {
        // 修改为使用正确的图标资源
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(R.drawable.ic_wine, "烟酒", 55588));
        transactions.add(new Transaction(R.drawable.ic_clothes, "服饰", 23));
        transactions.add(new Transaction(R.drawable.ic_wine, "烟酒", 85));
        adapter.updateData(transactions);
    }

    private void showDatePicker() {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_date_picker, null);
        
        NumberPicker yearPicker = view.findViewById(R.id.yearPicker);
        NumberPicker monthPicker = view.findViewById(R.id.monthPicker);
        TextView btnCancel = view.findViewById(R.id.btnCancel);
        TextView btnConfirm = view.findViewById(R.id.btnConfirm);

        // 设置年份范围
        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(2125);
        yearPicker.setValue(2024); // 默认当前年份

        // 设置月份范围
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(1); // 默认1月

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYear.setText(yearPicker.getValue() + "年");
                yearMonthText.setText(monthPicker.getValue() + "月");
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }
} 