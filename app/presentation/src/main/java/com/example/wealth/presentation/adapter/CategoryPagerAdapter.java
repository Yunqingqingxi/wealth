package com.example.wealth.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wealth.presentation.ExpenseCategoryFragment;
import com.example.wealth.presentation.IncomeCategoryFragment;

public class CategoryPagerAdapter extends FragmentStateAdapter {

    public CategoryPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new ExpenseCategoryFragment() : new IncomeCategoryFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
} 