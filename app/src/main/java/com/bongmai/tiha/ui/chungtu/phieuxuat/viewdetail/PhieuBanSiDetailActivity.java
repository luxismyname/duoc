package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseActivity;

import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat.PhieuBanSiChiTietPhieuXuatFragment;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat.PhieuBanSiChiTietPhieuXuatPresenter;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.generalinfo.PhieuBanSiThongTinPhieuFragment;
import com.bongmai.tiha.utils.CommonUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class PhieuBanSiDetailActivity extends AppCompatActivity implements BaseActivity, PhieuBanSiDetailContract.View {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View progressBar;
    View ctlMain;
    String soPhieu = "";
    PhieuBanSiDetailPagerAdapter viewPageAdapter;
    PhieuXuatInfo phieuXuatInfo;
    VattuxuatInfo vattuxuatInfo;
    List<VattuxuatInfo> listChiTietXuat;
    String soPhieuVattu = "";

    PhieuBanSiDetailPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieubansidetail);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        progressBar = findViewById(R.id.progressBar);
        ctlMain = findViewById(R.id.ctlMain);
    }

    private void setupViewPager() {
        int tabCount = 2;
        viewPageAdapter = new PhieuBanSiDetailPagerAdapter(getSupportFragmentManager(),this,phieuXuatInfo,  tabCount);
        viewPager.setOffscreenPageLimit(tabCount);
        //General info
        viewPageAdapter.addFragment(new PhieuBanSiChiTietPhieuXuatFragment().newInstance(listChiTietXuat),
                getResources().getString(R.string.fragment_phieubansi_detail_chitietphieuxuat_title), 0);
        viewPageAdapter.addFragment(new PhieuBanSiThongTinPhieuFragment().newInstance(phieuXuatInfo),
                getResources().getString(R.string.fragment_phieubansi_detail_generalinfo_title), 1);

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.phieubansidetail_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLoadData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            soPhieu = bundle.getString("SoCT");
          //  bundle.getSerializable("listVattuxuat");
//            bundle.getSerializable("VattuXuatInfo");
        }

        presenter = new PhieuBanSiDetailPresenter(this);
        presenter.GetPhieuXuat(soPhieu);
        presenter.GetPhieuXuatChitiet(soPhieu);



    }

    @Override
    public void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult) {
        this.phieuXuatInfo = phieuXuatResult;
        setupViewPager();
    }

    @Override
    public void onGetPhieuXuatError(String error) {

    }

    @Override
    public void onGetPhieuXuatChiTietSuccess(List<VattuxuatInfo> list) {
        this.listChiTietXuat = list;

        setupViewPager();

    }

    @Override
    public void onGetPhieuXuatChiTietError(String error) {

    }

}
