package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.luongtheonvgh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietAdapter;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietPresenter;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke.ThongKeQuangDuongNhanVienGiaoHangActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke.ThongKeQuangDuongNhanVienGiaoHangPresenter;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke.ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class ThongKeLuongNhanVienGiaoHangTheoQuangDuongActivity extends AppCompatActivity implements ThongKeLuongNhanVienGiaoHangTheoQuangDuongContract.View, View.OnClickListener {

    Toolbar toolbar;
    RecyclerView rvListData;
    TextView
            tvTongLanDi, tvTongQD, tvTongLuong;
    EditText etFromDate, etToDate, edtEmployee;
    Button btnView;

    ConstraintLayout  ctlCustomer;
    BaseService service;
    ConstraintLayout ctlEmployee;
    ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter adapterListData;
    List<LuongNVGHTheoKMInfo> listData;
    LuongNVGHTheoKMInfo luongNVGHTheoKMInfo;
    ThongKeLuongNhanVienGiaoHangTheoQuangDuongPresenter presenter;
    DateDialogAdapter adapterDateDialog;
    private static final int REQUEST_NHANVIENBANHANG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_thong_ke_luong_nhan_vien_giao_hang_theo_quang_duong);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }


    public void findViewById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvListData = (RecyclerView) findViewById(R.id.rvListData);

        tvTongLanDi = findViewById(R.id.tvTongLanDi);
        tvTongQD = findViewById(R.id.tvTongQD);
        tvTongLuong = findViewById(R.id.tvTongLuong);

        btnView = findViewById(R.id.btnCustomerView);
        etFromDate = findViewById(R.id.etFromDate);
        etToDate = findViewById(R.id.etToDate);
        edtEmployee = findViewById(R.id.edtEmployee);
        ctlCustomer = findViewById(R.id.ctlCustomer);
        ctlEmployee = findViewById(R.id.ctlEmployee);

        rvListData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListData.setLayoutManager(llm);
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            ctlEmployee.setVisibility(View.VISIBLE);
        }

        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);
        edtEmployee.setOnClickListener(this);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListData.getListAllData().clear();
                if(!PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")) {
                    presenter.ThongKeLuongNhanVienGiaoHangTheoQuangDuong(etFromDate.getText().toString(), etToDate.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());
                } else {
                    presenter.ThongKeLuongNhanVienGiaoHangTheoQuangDuong(etFromDate.getText().toString(), etToDate.getText().toString(), edtEmployee.getText().toString());
                }
            }
        });

    }

    private void configToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Thống Kê Lương NVGH Theo KM";
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

    public void loadData() {
        presenter = new ThongKeLuongNhanVienGiaoHangTheoQuangDuongPresenter(this);
        luongNVGHTheoKMInfo = new LuongNVGHTheoKMInfo();
        listData = new ArrayList<>();
        etFromDate.setText(PublicVariables.NgayLamViec);
        etToDate.setText(PublicVariables.NgayLamViec);
        onBindData();

    }

    public void onBindData() {

        adapterListData = new ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter(ThongKeLuongNhanVienGiaoHangTheoQuangDuongActivity.this, listData);

        adapterListData.DataSourceChanged(new ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter.DataSourceChangedListener(){
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });


        rvListData.setAdapter(adapterListData);
        adapterListData.notifyDataSetChanged();
        //Filter data

    }

    private void SummaryItems() {
        double tonglandi = 0, tongkm = 0, tongtien = 0;
        for (LuongNVGHTheoKMInfo item : adapterListData.getListAllData()) {
            tonglandi += item.getLanDiSo();
            tongkm += item.getTongQuangDuong();
            tongtien += item.getSotiennhap();
            //tongnv += Double.parseDouble(item.getEmployeeID());
        }
        tvTongLanDi.setText(AppUtils.formatNumber("N0").format(tonglandi));
        tvTongQD.setText(AppUtils.formatNumber("N1").format(tongkm));
        tvTongLuong.setText(AppUtils.formatNumber("N1").format(tongtien));
    }

    @Override
    public void onThongKeLuongNhanVienGiaoHangTheoQuangDuongSuccess(List<LuongNVGHTheoKMInfo> listResult) {
        listData = listResult;
        presenter.ThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLong(etFromDate.getText().toString(), etToDate.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());
        adapterListData.addAll(listResult);
        SummaryItems();
    }

    @Override
    public void onThongKeLuongNhanVienGiaoHangTheoQuangDuongError(String error) {

    }

    @Override
    public void onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongSuccess(List<LuongNVGHTheoKMInfo> listResult) {
        listData = listResult;
        adapterListData.addAll(listResult);
    }

    @Override
    public void onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongError(String error) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()) {
            case R.id.etFromDate:
                try {
                    adapterDateDialog = new DateDialogAdapter(v, etFromDate.getText().toString());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.etToDate:
                try {
                    adapterDateDialog = new DateDialogAdapter(v, etToDate.getText().toString());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.edtEmployee:
                intent = new Intent(this, EmployeeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_NHANVIENBANHANG);
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        EmployeeInfo employeeInfo;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
//                case FILTER_EMPLOYEE_REQUESTCODE:
//                    bundle = intent.getExtras();
//                    if (bundle == null) return;
//                    danhSachEmployeeStr = bundle.getString("MaNV");
//                    etChooseEmployee.setText(bundle.getString("MaNV"));
//                    break;
                case REQUEST_NHANVIENBANHANG:
                    bundle = intent.getExtras();
                    employeeInfo = (EmployeeInfo) bundle.getSerializable("EmployeeInfo");
                    if (employeeInfo == null) return;
                    //luongNVGHTheoKMInfo.setTenNVGH(employeeInfo.getEmployeeID());
                    edtEmployee.setText(employeeInfo.getEmployeeID());

                default:
                    break;
            }
        }
    }

}