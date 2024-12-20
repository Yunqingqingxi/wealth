package com.example.wealth.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wealth.presentation.R;
import com.example.wealth.presentation.model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private final List<Transaction> transactions;
    private OnItemClickListener onItemClickListener;

    // 定义点击事件接口
    public interface OnItemClickListener {
        void onItemClick(Transaction transaction, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();
    }

    public void updateData(List<Transaction> newTransactions) {
        this.transactions.clear();
        if (newTransactions != null) {
            this.transactions.addAll(newTransactions);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.iconView.setImageResource(transaction.getIconResId());
        holder.categoryText.setText(transaction.getCategory());
        
        // 格式化金额显示，添加货币符号和小数点
        String amountText = String.format("¥%.2f", transaction.getAmount());
        holder.amountText.setText(amountText);

        // 设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(transaction, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView iconView;
        public final TextView categoryText;
        public final TextView amountText;

        public ViewHolder(@NonNull View view) {
            super(view);
            iconView = view.findViewById(R.id.transaction_icon);
            categoryText = view.findViewById(R.id.category_text);
            amountText = view.findViewById(R.id.amount_text);
        }
    }

    // 获取指定位置的交易记录
    public Transaction getItem(int position) {
        if (position >= 0 && position < transactions.size()) {
            return transactions.get(position);
        }
        return null;
    }

    // 添加单个交易记录
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
            notifyItemInserted(transactions.size() - 1);
        }
    }

    // 删除交易记录
    public void removeTransaction(int position) {
        if (position >= 0 && position < transactions.size()) {
            transactions.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, transactions.size());
        }
    }
} 