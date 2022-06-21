package com.bongmai.tiha.ui.danhmuc.soduvattu.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.PhieuBanSiDetailPagerAdapter;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.PhieuBanSiDetailPresenter;
import com.bongmai.tiha.ui.danhmuc.soduvattu.them.ThemSoDuVatTuActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.ThemKhachHangActivity;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class SoDuVatTuDetailActivity extends AppCompatActivity implements BaseActivity, SoDuVatTuDauDetailActivityContact.View {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View progressBar;
    View ctlMain;
    SoDuVatTuDauDetailActivityAdapter adapterSoDuVatTuDau;
    SoDuVatTuDauInfo soDuVatTuDauInfo;
    String soDuVatTuDauID = "";
    SoDuVatTuDauDetailActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_du_vat_tu_detail);
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

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.soduvattu_tieudeform);
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
            SoDuVatTuDauInfo soDuVatTuDauInfo = (SoDuVatTuDauInfo) bundle.getSerializable("SoDuVatTuDauInfo");
            soDuVatTuDauID = soDuVatTuDauInfo == null ? "" : String.valueOf(soDuVatTuDauInfo.getID());
        }

        presenter = new SoDuVatTuDauDetailActivityPresenter(this);
        presenter.GetSoDuVatTuDau(soDuVatTuDauID);
    }

    private void setUpViewPager(){
        int tabCount = 1;
        adapterSoDuVatTuDau = new SoDuVatTuDauDetailActivityAdapter(getSupportFragmentManager(), getApplicationContext(), soDuVatTuDauInfo, tabCount);
        adapterSoDuVatTuDau.addFragment(SoDuVatTuDauFragment.newInstance(soDuVatTuDauInfo), getResources().getString(R.string.fragment_soduvattu_detail_chitiet), 0);
        viewPager.setAdapter(adapterSoDuVatTuDau);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {
        this.soDuVatTuDauInfo = itemResult;
        setUpViewPager();
    }

    @Override
    public void onGetSoDuVatTuDauError(String error) {
        error = error.isEmpty() ? getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(this)) {
            error = getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        if (!this.isFinishing()) {
            alert.show();
        }
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(this, "Xóa số dư vật tư đầu thành công!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDeleteError(String error) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_supplier, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:

                if (soDuVatTuDauInfo == null) break;
                Intent intent = new Intent(SoDuVatTuDetailActivity.this, ThemSoDuVatTuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SoDuVatTuDauInfo", soDuVatTuDauInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.action_delete:
                if (soDuVatTuDauInfo == null) break;
//                if (baoCaoDichVuInfo.getTrangThai() == BaoCaoDichVuInfo.TRANGTHAI_HOANTHANH) {
//                    Toast.makeText(ServiceReportDetailActivity.this, "Service report đã " + getResources().getString(R.string.trangthai_servicereport_hoanthanh)
//                            + " bạn không được phép xóa. Vui lòng liên hệ Admin để được hỗ trợ!", Toast.LENGTH_LONG).show();
//                    break;
//                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(SoDuVatTuDetailActivity.this);
                builder.setTitle("XÓA SỐ DƯ VẬT TƯ")
                        .setMessage("Bạn có chắc muốn xóa số dư vật tư này?")
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                presenter.DeleteSoDuVatTuDau(soDuVatTuDauInfo);
//                                editKhachHangPresenter.DeleteSupplier(supplierInfo);
//                                serviceReportDetailPresenter.DeleteBaoCaoDichVu(baoCaoDichVuInfo);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                //navigationView.setCheckedItem(itemIdLast);
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}