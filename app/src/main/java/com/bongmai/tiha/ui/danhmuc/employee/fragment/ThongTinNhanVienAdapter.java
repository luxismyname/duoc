package com.bongmai.tiha.ui.danhmuc.employee.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bongmai.tiha.data.entities.EmployeeInfo;

import java.util.ArrayList;
import java.util.List;

public class ThongTinNhanVienAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    EmployeeInfo employeeInfo;
    int tabCount;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ThongTinNhanVienAdapter(FragmentManager fm, Context mContext, EmployeeInfo employeeInfo, int tabCount) {
        super(fm);
        this.mContext = mContext;
        this.employeeInfo = employeeInfo;

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
