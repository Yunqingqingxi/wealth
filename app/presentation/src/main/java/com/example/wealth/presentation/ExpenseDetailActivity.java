package com.example.wealth.presentation;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseDetailActivity extends AppCompatActivity {
    
    private RecyclerView rvExpenseList;
    private View btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        rvExpenseList = findViewById(R.id.rv_expense_list);
        btnBack = findViewById(R.id.btn_back);
        rvExpenseList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupListeners() {
        // 返回按钮
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
} 