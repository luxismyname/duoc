package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail;

import android.content.Context;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;

import java.util.ArrayList;
import java.util.List;




public class PhieuBanSiDetailPagerAdapter extends FragmentStatePagerAdapter {

    Context mContext;

    PhieuXuatInfo phieuXuatInfo;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    public PhieuBanSiDetailPagerAdapter(FragmentManager fm, Context mContext, PhieuXuatInfo phieuXuatInfo,  int mTabCount) {
        super(fm);
        this.mContext = mContext;
        this.phieuXuatInfo = phieuXuatInfo;

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

