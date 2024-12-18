package com.example.wealth.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.example.wealth.presentation.adapter.TransactionAdapter;
import com.example.wealth.presentation.model.Transaction;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailFragment extends Fragment {
    private TextView tvIncome, tvExpense;
    private RecyclerView rvTransactions;
    private TransactionAdapter adapter;

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

        // 设置初始数据
        tvIncome.setText("0.00");
        tvExpense.setText("55696.00");
    }

    private void setupRecyclerView() {
        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TransactionAdapter(new ArrayList<>());
        rvTransactions.setAdapter(adapter);
    }

    private void setupClickListeners() {

    }

    private void loadData() {
        // 修改为使用正确的图标资源
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(R.drawable.ic_wine, "烟酒", 55588));
        transactions.add(new Transaction(R.drawable.ic_clothes, "服饰", 23));
        transactions.add(new Transaction(R.drawable.ic_wine, "烟酒", 85));
        adapter.updateData(transactions);
    }
} 