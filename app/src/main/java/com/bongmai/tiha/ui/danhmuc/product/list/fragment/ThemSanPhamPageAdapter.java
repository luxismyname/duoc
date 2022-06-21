package com.bongmai.tiha.ui.danhmuc.product.list.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;

import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamPageAdapter extends FragmentStatePagerAdapter {

    Context context;
    ProductInfo productInfo;
    int tabCount;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ThemSanPhamPageAdapter(FragmentManager fm, Context context, ProductInfo productInfo, int tabCount) {
        super(fm);
        this.context = context;
        this.productInfo = productInfo;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, int position) {
        mFragmentList.add(position, fragment);
        mFragmentTitleList.add(position, title);
    }

    public void removeFragment(Fragment fragment, int position) {
        mFragmentList.remove(position);
        mFragmentTitleList.remove(position);
    }

    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

}
