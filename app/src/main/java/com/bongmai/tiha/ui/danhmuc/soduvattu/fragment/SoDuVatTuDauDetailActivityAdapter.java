package com.bongmai.tiha.ui.danhmuc.soduvattu.fragment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;

import java.util.ArrayList;
import java.util.List;

public class SoDuVatTuDauDetailActivityAdapter extends FragmentStatePagerAdapter {

    Context mContext;

    int mTabCount;
    SoDuVatTuDauInfo soDuVatTuDauInfo;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SoDuVatTuDauDetailActivityAdapter(FragmentManager fm, Context mContext, SoDuVatTuDauInfo soDuVatTuDauInfo, int mTabCount) {
        super(fm);
        this.mContext = mContext;
        this.soDuVatTuDauInfo = soDuVatTuDauInfo;
    }

    @Override
    public Fragment getItem(int position) {


        return mFragmentList.get(position);

    }

    public void addFragment(Fragment fragment, String title, int position) {
        mFragmentList.add(position, fragment);
        mFragmentTitleList.add(position, title);
    }

    public void removeFragment(Fragment fragment, int position) {
        mFragmentList.remove(position);
        mFragmentTitleList.remove(position);
    }

    @Override
    public int getCount() {
//        return mTabCount;
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return mFragmentTitleList.get(position);
    }
}
