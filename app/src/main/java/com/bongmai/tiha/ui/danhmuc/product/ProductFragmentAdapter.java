package com.bongmai.tiha.ui.danhmuc.product;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bongmai.tiha.data.entities.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class ProductFragmentAdapter extends FragmentStatePagerAdapter {


    Context context;
    ProductInfo productInfo;
    int tab;

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public ProductFragmentAdapter(FragmentManager fm, Context context, ProductInfo productInfo, int tab) {
        super(fm);
        this.context = context;
        this.productInfo = productInfo;
        //this.tab = tab;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title, int position) {
        fragmentList.add(position, fragment);
        fragmentTitleList.add(position, title);
    }

    public void removeFragment(Fragment fragment, int position) {
        fragmentList.remove(position);
        fragmentTitleList.remove(position);
    }

    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return mContext.getResources().getString(R.string.fragment_servicereport_detail_generalinfo_title);
//            case 1:
//                return mContext.getResources().getString(R.string.fragment_servicereport_detail_testresults_title);
//            case 2:
//                return mContext.getResources().getString(R.string.fragment_servicereport_detail_picture_title);
//            case 3:
//                return mContext.getResources().getString(R.string.fragment_servicereport_detail_sanphamthaythe_title);
//            default:
//                return null;
//        }
        return fragmentTitleList.get(position);
    }

}
