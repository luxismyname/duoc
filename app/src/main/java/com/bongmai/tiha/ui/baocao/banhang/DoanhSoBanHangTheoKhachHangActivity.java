package com.bongmai.tiha.ui.baocao.banhang;

import android.app.Dialog;
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
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;


public class DoanhSoBanHangTheoKhachHangActivity extends AppCompatActivity {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvListData;
    TextView
            tvTongSoDong,
            tvTongSL,
            tvTongThanhTien;
    EditText etTimKiem;
    ImageButton btnClear;
    BaseService service;
    DoanhSoBanHangTheoKhachHangRecyclerViewAdapter adapterListData;
    List<DoanhSoKhachHangInfo> listData;
    Dialog progressDialog;
    DieuKienLocInfo dieuKienLoc;
    BaoCaoBanHangPresenter baoCaoPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_doanhsobanhangtheokhachhang);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvListData = (RecyclerView) findViewById(R.id.rvListData);
        tvTongSoDong = (TextView) findViewById(R.id.tvTongSoDong);
        tvTongSL = (TextView) findViewById(R.id.tvTongSL);
        tvTongThanhTien = (TextView) findViewById(R.id.tvTongThanhTien);

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
                adapterListData.getFilter().filter(s);
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

        rvListData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListData.setLayoutManager(llm);
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        listData = new ArrayList<>();
        onBindData();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Doanh số theo nhân viên";
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
            dieuKienLoc.setLoaiNhanVien(bundle.getInt("LoaiNhanVien"));
            if (menu != null)
                menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
            getSupportActionBar().setTitle(dieuKienLoc.getTenMauBaoCao());

            GetListData();
        }
    }

    private void GetListData() {
        showProgressDialog(true);
        baoCaoPresenter.GetListDoanhSoBanHangTheoKhachHang(dieuKienLoc, new IBaoCaoModel.IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener() {
            @Override
            public void onSuccess(List<DoanhSoKhachHangInfo> listResult) {
                showProgressDialog(false);
                listData = listResult;
                onBindData();
            }

            @Override
            public void onError(String error) {
                showProgressDialog(false);
                listData = new ArrayList<>();
                onBindData();
                Toast.makeText(DoanhSoBanHangTheoKhachHangActivity.this, "Lấy danh sách thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onBindData() {
        adapterListData = new DoanhSoBanHangTheoKhachHangRecyclerViewAdapter(DoanhSoBanHangTheoKhachHangActivity.this, listData);
        adapterListData.DataSourceChanged(new DoanhSoBanHangTheoKhachHangRecyclerViewAdapter.DataSourceChangedListener() {
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });
        rvListData.setAdapter(adapterListData);
        adapterListData.notifyDataSetChanged();
        //Filter data
        if (!etTimKiem.getText().toString().isEmpty()) {
            adapterListData.getFilter().filter(etTimKiem.getText());
        }
    }

    private void SummaryItems() {
        double tongSl = 0, tongThanhTien = 0;
        for (DoanhSoKhachHangInfo item : adapterListData.getListAllData()) {
            tongSl += item.getSoLuong();
            tongThanhTien += item.getThanhTien();
        }
        tvTongSL.setText(AppUtils.formatNumber("N0").format(tongSl));
        tvTongThanhTien.setText(AppUtils.formatNumber("N0").format(tongThanhTien));
        tvTongSoDong.setText(AppUtils.formatNumber("N0").format(adapterListData.getListAllData() == null ? 0 : adapterListData.getListAllData().size()));
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
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), DoanhSoBanHangTheoKhachHangActivity.this,
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
