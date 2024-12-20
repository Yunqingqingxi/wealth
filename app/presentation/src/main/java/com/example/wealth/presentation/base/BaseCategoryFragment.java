package com.example.wealth.presentation.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.example.wealth.presentation.R;
import com.example.wealth.presentation.adapter.CategoryAdapter;
import com.example.wealth.presentation.model.CategoryItem;

import java.util.List;

public abstract class BaseCategoryFragment extends Fragment {
    
    protected GridView categoryGrid;
    protected View selectedCategoryView;

    // 子类必须实现此方法提供类别数据
    protected abstract List<CategoryItem> getCategoryItems();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        
        categoryGrid = view.findViewById(R.id.category_grid);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), getCategoryItems());
        categoryGrid.setAdapter(adapter);
        
        setupClickListener();
        
        return view;
    }

    private void setupClickListener() {
        categoryGrid.setOnItemClickListener((parent, view1, position, id) -> {
            // 重置所有item的背景
            for (int i = 0; i < parent.getChildCount(); i++) {
                parent.getChildAt(i).findViewById(R.id.iv_category)
                    .setBackgroundResource(R.drawable.circle_background);
            }
            
            // 保存选中的视图引用
            selectedCategoryView = view1.findViewById(R.id.iv_category);
            // 设置选中item的背景
            selectedCategoryView.setBackgroundResource(R.drawable.circle_yellow_background);
                
            // 显示金额输入界面
            showAmountInput();
        });
    }

    private void showAmountInput() {
        View amountView = getLayoutInflater().inflate(R.layout.layout_amount_input, null);
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(amountView);
        
        setupAmountInput(amountView, dialog);
        dialog.show();
    }

    private void setupAmountInput(View view, BottomSheetDialog dialog) {
        TextView tvAmount = view.findViewById(R.id.tv_amount);
        StringBuilder amountBuilder = new StringBuilder();
        StringBuilder operatorBuilder = new StringBuilder();
        StringBuilder firstNumberBuilder = new StringBuilder();
        
        View keypadContainer = view.findViewById(R.id.keypad_container);
        ImageView btnBackspace = view.findViewById(R.id.btn_backspace);
        TextView btnComplete = view.findViewById(R.id.btn_complete);
        
        setupNumberKeys(keypadContainer, amountBuilder, firstNumberBuilder, operatorBuilder, tvAmount, btnComplete);
        
        btnBackspace.setOnClickListener(v -> {
            if (amountBuilder.length() > 0) {
                if (isOperator(amountBuilder.charAt(amountBuilder.length() - 1))) {
                    operatorBuilder.setLength(0);
                    updateCompleteButton(btnComplete, false);
                }
                amountBuilder.setLength(amountBuilder.length() - 1);
                updateAmount(tvAmount, amountBuilder.toString());
            }
        });
        
        btnComplete.setOnClickListener(v -> {
            if (operatorBuilder.length() > 0) {
                calculateResult(amountBuilder, firstNumberBuilder, operatorBuilder);
                updateAmount(tvAmount, amountBuilder.toString());
                updateCompleteButton(btnComplete, false);
            } else {
                // 恢复选中图标的背景色
                if (selectedCategoryView != null) {
                    selectedCategoryView.setBackgroundResource(R.drawable.circle_background);
                }
                dialog.dismiss();
            }
        });
    }

    private void setupNumberKeys(View container, StringBuilder amountBuilder, 
                               StringBuilder firstNumberBuilder, StringBuilder operatorBuilder, 
                               TextView tvAmount, TextView btnComplete) {
        if (container instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) container;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                if (child instanceof TextView) {
                    TextView key = (TextView) child;
                    key.setOnClickListener(v -> {
                        String keyText = key.getText().toString();
                        handleKeyPress(keyText, amountBuilder, firstNumberBuilder, operatorBuilder, tvAmount, btnComplete);
                    });
                } else if (child instanceof ViewGroup) {
                    setupNumberKeys(child, amountBuilder, firstNumberBuilder, operatorBuilder, tvAmount, btnComplete);
                }
            }
        }
    }

    private void handleKeyPress(String keyText, StringBuilder amountBuilder, 
                              StringBuilder firstNumberBuilder, StringBuilder operatorBuilder, 
                              TextView tvAmount, TextView btnComplete) {
        switch (keyText) {
            case "+":
            case "-":
                if (operatorBuilder.length() > 0) {
                    calculateResult(amountBuilder, firstNumberBuilder, operatorBuilder);
                }
                firstNumberBuilder.setLength(0);
                firstNumberBuilder.append(amountBuilder);
                operatorBuilder.setLength(0);
                operatorBuilder.append(keyText);
                amountBuilder.append(keyText);
                tvAmount.setText(amountBuilder.toString());
                updateCompleteButton(btnComplete, true);
                break;
            case ".":
                if (!amountBuilder.toString().contains(".")) {
                    amountBuilder.append(keyText);
                    tvAmount.setText(amountBuilder.toString());
                }
                break;
            default:
                if (amountBuilder.length() < 10) {
                    amountBuilder.append(keyText);
                    updateAmount(tvAmount, amountBuilder.toString());
                }
                break;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-';
    }

    private void calculateResult(StringBuilder amountBuilder, StringBuilder firstNumberBuilder, 
                               StringBuilder operatorBuilder) {
        try {
            double firstNumber = Double.parseDouble(firstNumberBuilder.toString());
            String secondNumberStr = amountBuilder.substring(firstNumberBuilder.length() + 1);
            double secondNumber = Double.parseDouble(secondNumberStr);
            double result;
            switch (operatorBuilder.toString()) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                default:
                    return;
            }
            amountBuilder.setLength(0);
            amountBuilder.append(String.format("%.2f", result));
            operatorBuilder.setLength(0);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            amountBuilder.setLength(0);
            amountBuilder.append("0.00");
        }
    }

    private void updateAmount(TextView tvAmount, String amount) {
        if (amount.isEmpty()) {
            tvAmount.setText("0.00");
        } else {
            try {
                if (!amount.contains("+") && !amount.contains("-")) {
                    double value = Double.parseDouble(amount);
                    tvAmount.setText(String.format("%.2f", value));
                } else {
                    tvAmount.setText(amount);
                }
            } catch (NumberFormatException e) {
                tvAmount.setText("0.00");
            }
        }
    }

    private void updateCompleteButton(TextView btnComplete, boolean isCalculating) {
        btnComplete.setText(isCalculating ? "=" : "完成");
    }
} 