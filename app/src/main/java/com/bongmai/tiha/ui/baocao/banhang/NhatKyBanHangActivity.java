package com.bongmai.tiha.ui.baocao.banhang;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.PhieuBanSiDetailActivity;
import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class NhatKyBanHangActivity extends AppCompatActivity {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvDanhSachXuat;
    TextView
            tvTongSoPhieu,
            tvTongTien;
    EditText etTimKiem;
    ImageButton btnClear;
    BaseService service;
    NhatKyBanHangRecyclerViewAdapter adapterDanhSachXuat;
    List<DanhSachXuatInfo> listPhieuXuat;
    Dialog progressDialog;
    DieuKienLocInfo dieuKienLoc;
    BaoCaoBanHangPresenter baoCaoPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_nhatkybanhang);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvDanhSachXuat = (RecyclerView) findViewById(R.id.rvDanhSachXuat);
        tvTongSoPhieu = (TextView) findViewById(R.id.tvTongSoPhieu);
        tvTongTien = (TextView) findViewById(R.id.tvTongTien);

        etTimKiem = (EditText) findViewById(R.id.etTimKiem);
        btnClear = (ImageButton) findViewById(R.id.btnClear);

        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    btnClear.setVisibility(View.VISIBLE);
                else
                    btnClear.setVisibility(View.GONE);
                adapterDanhSachXuat.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTimKiem.setText("");
            }
        });

        rvDanhSachXuat.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvDanhSachXuat.setLayoutManager(llm);
        rvDanhSachXuat.setItemAnimator(new DefaultItemAnimator());
        rvDanhSachXuat.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        listPhieuXuat = new ArrayList<>();
        onBindData();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Thông kê bán hàng";
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        baoCaoPresenter = new BaoCaoBanHangPresenter();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            dieuKienLoc = (DieuKienLocInfo) bundle.getSerializable("DieuKienLoc");
            if (menu != null)
                menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
            getSupportActionBar().setTitle(dieuKienLoc.getTenMauBaoCao());

            GetListData();
        }
    }

    private void GetListData() {
        showProgressDialog(true);
        baoCaoPresenter.GetListNhatKyBanHang(dieuKienLoc, new IBaoCaoModel.IOnGetListNhatKyBanHangFinishedListener() {
            @Override
            public void onSuccess(List<DanhSachXuatInfo> listResult) {
                showProgressDialog(false);
                listPhieuXuat = listResult;
                onBindData();
            }

            @Override
            public void onError(String error) {
                showProgressDialog(false);
                listPhieuXuat = new ArrayList<>();
                onBindData();
                Toast.makeText(NhatKyBanHangActivity.this, "Lấy danh sách phiếu xuất thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void onBindData() {
        adapterDanhSachXuat = new NhatKyBanHangRecyclerViewAdapter(NhatKyBanHangActivity.this, listPhieuXuat);
        adapterDanhSachXuat.DataSourceChanged(new NhatKyBanHangRecyclerViewAdapter.DataSourceChangedListener() {
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });

        adapterDanhSachXuat.setClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                DanhSachXuatInfo danhSachXuatInfo = adapterDanhSachXuat.getItem(position);
                List<VattuxuatInfo> list = new ArrayList<>();
                if (danhSachXuatInfo == null) return;
                else {
                    Intent intent = new Intent(getApplicationContext(), PhieuBanSiDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("SoCT", danhSachXuatInfo.getSoCt());
                    bundle.putSerializable("listVattuxuat", (Serializable) list);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


        rvDanhSachXuat.setAdapter(adapterDanhSachXuat);
        adapterDanhSachXuat.notifyDataSetChanged();
        //Filter data
        if (!etTimKiem.getText().toString().isEmpty()) {
            adapterDanhSachXuat.getFilter().filter(etTimKiem.getText());
        }
    }

    private void SummaryItems() {
        double tongTien = 0;
        for (DanhSachXuatInfo item : adapterDanhSachXuat.getListAllData()) {
            tongTien += item.getTT();
        }

        tvTongTien.setText(AppUtils.formatNumber("N0").format(tongTien));
        tvTongSoPhieu.setText(AppUtils.formatNumber("N0").format(adapterDanhSachXuat.getListAllData() == null ? 0 : adapterDanhSachXuat.getListAllData().size()));
    }

    //region Menu toolbar
    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbar.getMenu().clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_choose_date, menu);
        menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                View menuItemView = findViewById(R.id.action_date);
                showMenu(menuItemView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int position = DateReportInfo.getPositionDateReport(item.getItemId());
                if (position == DateReportInfo.LuaChonKhac) {
                    dieuKienLoc.setTuNgay(dieuKienLoc.getTuNgayTuChon() == null ? PublicVariables.NgayLamViec : dieuKienLoc.getTuNgayTuChon());
                    dieuKienLoc.setDenNgay(dieuKienLoc.getDenNgayTuChon() == null ? PublicVariables.NgayLamViec : dieuKienLoc.getDenNgayTuChon());
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), NhatKyBanHangActivity.this,
                            new NhapThoiGianBaoCaoDialog.DialogClickInterface() {
                                @Override
                                public void onClickPositiveButton(String startDate, String endDate) {
                                    dieuKienLoc.setTuNgay(startDate);
                                    dieuKienLoc.setDenNgay(endDate);
                                    dieuKienLoc.setTuNgayTuChon(startDate);
                                    dieuKienLoc.setDenNgayTuChon(endDate);
                                    dieuKienLoc.setThoiGianXemBaoCao(startDate + " - " + endDate);
                                    menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
                                    GetListData();
                                }

                                @Override
                                public void onClickNegativeButton() {

                                }
                            });
                } else {
                    DateReportInfo dateReport = DateReportInfo.GetDateReport(position);
                    dieuKienLoc.setTuNgay(dateReport.StartDate);
                    dieuKienLoc.setDenNgay(dateReport.EndDate);
                    dieuKienLoc.setThoiGianXemBaoCao(dateReport.Name);
                    menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
                    GetListData();
                }
                return true;
            }
        });
        popup.inflate(R.menu.menu_thoigianxembaocao);
        popup.show();
    }
    //endregion

    private void showProgressDialog(boolean isShow) {
        if (isShow) {
            progressDialog = new Dialog(this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.getWindow().setBackgroundDrawableResource(R.color.colorTransparent);
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.dialog_progressbar_waiting);
            progressDialog.show();

        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }
}
