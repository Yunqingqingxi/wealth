package com.example.wealth.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wealth.presentation.R;
import com.example.wealth.presentation.model.CategoryItem;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryItem> categories;

    public CategoryAdapter(Context context, List<CategoryItem> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CategoryItem getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.iv_category);
            holder.name = convertView.findViewById(R.id.tv_category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CategoryItem item = getItem(position);
        holder.icon.setImageResource(item.getIconResId());
        holder.name.setText(item.getName());

        return convertView;
    }

    private static class ViewHolder {
        ImageView icon;
        TextView name;
    }
} 