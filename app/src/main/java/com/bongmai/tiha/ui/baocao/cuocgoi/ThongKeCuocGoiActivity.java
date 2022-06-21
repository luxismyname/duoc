package com.bongmai.tiha.ui.baocao.cuocgoi;

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
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;


public class ThongKeCuocGoiActivity extends AppCompatActivity implements BaoCaoCuocGoiContract.View {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvDanhSachCuocGoi;
    TextView
            tvTongSL,
            tvTongTienHang,
            tvTongTienThu,
            tvTongCuocGoi;
    EditText etTimKiem;
    ImageButton btnClear;
    BaseService service;
    ThongKeCuocGoiRecyclerViewAdapter adapterCuocGoi;
    List<DanhSachCuocGoiInfo> listCuocGoi;
    Dialog progressDialog;
    DieuKienLocInfo dieuKienLoc;
    BaoCaoCuocGoiPresenter baoCaoPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_thongkecuocgoi);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvDanhSachCuocGoi = (RecyclerView) findViewById(R.id.rvDanhSachCuocGoi);
        tvTongSL = (TextView) findViewById(R.id.tvTongSL);
        tvTongTienHang = (TextView) findViewById(R.id.tvTongTienHang);
        tvTongTienThu = (TextView) findViewById(R.id.tvTongTienThu);
        tvTongCuocGoi = (TextView) findViewById(R.id.tvTongCuocGoi);

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
                adapterCuocGoi.getFilter().filter(s);
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

        rvDanhSachCuocGoi.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvDanhSachCuocGoi.setLayoutManager(llm);
        rvDanhSachCuocGoi.setItemAnimator(new DefaultItemAnimator());
        rvDanhSachCuocGoi.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        listCuocGoi = new ArrayList<>();
        onBindData();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Thông kê cuộc gọi";
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
        baoCaoPresenter = new BaoCaoCuocGoiPresenter(this);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            dieuKienLoc = (DieuKienLocInfo) bundle.getSerializable("DieuKienLoc");
            if (menu != null)
                menu.findItem(R.id.action_titledate).setTitle(dieuKienLoc.getThoiGianXemBaoCao());
            //getSupportActionBar().setTitle(dieuKienLoc.getTenMauBaoCao());

            GetListData();
        }
    }

    private void GetListData() {
        showProgressDialog(true);
        baoCaoPresenter.GetListThongKeCuocGoi(dieuKienLoc);
    }

    @Override
    public void onGetListThongKeCuocGoiSuccess(List<DanhSachCuocGoiInfo> listResult) {
        showProgressDialog(false);
        listCuocGoi = listResult;
        onBindData();
    }

    @Override
    public void onGetListThongKeCuocGoiError(String error) {
        showProgressDialog(false);
        listCuocGoi = new ArrayList<>();
        onBindData();
        Toast.makeText(ThongKeCuocGoiActivity.this, "Lấy danh sách cuộc gọi thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    private void onBindData() {
        adapterCuocGoi = new ThongKeCuocGoiRecyclerViewAdapter(ThongKeCuocGoiActivity.this, listCuocGoi);
        adapterCuocGoi.DataSourceChanged(new ThongKeCuocGoiRecyclerViewAdapter.DataSourceChangedListener() {
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });
        adapterCuocGoi.ItemClick(new ThongKeCuocGoiRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(DanhSachCuocGoiInfo danhSachCuocGoiInfo, View view, int position) {

            }

            @Override
            public void onItemLongClick(DanhSachCuocGoiInfo danhSachCuocGoiInfo, View view, int position) {

            }
        });
        rvDanhSachCuocGoi.setAdapter(adapterCuocGoi);
        adapterCuocGoi.notifyDataSetChanged();
        //Filter data
        if (!etTimKiem.getText().toString().isEmpty()) {
            adapterCuocGoi.getFilter().filter(etTimKiem.getText());
        }
    }

    private void SummaryItems() {
        double tongSoLuong = 0, tongTienHang = 0, tongTienThu = 0;
        for (DanhSachCuocGoiInfo itemCuocGoi : adapterCuocGoi.getListAllData()) {
            tongSoLuong += itemCuocGoi.getSL();
            tongTienHang += itemCuocGoi.getTienBan();
            tongTienThu += itemCuocGoi.getTienThu();
        }

        tvTongSL.setText(AppUtils.formatNumber("N0").format(tongSoLuong));
        tvTongTienHang.setText(AppUtils.formatNumber("N0").format(tongTienHang));
        tvTongTienThu.setText(AppUtils.formatNumber("N0").format(tongTienThu));
        tvTongCuocGoi.setText(AppUtils.formatNumber("N0").format(adapterCuocGoi.getListAllData() == null ? 0 : adapterCuocGoi.getListAllData().size()));
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
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), ThongKeCuocGoiActivity.this,
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
