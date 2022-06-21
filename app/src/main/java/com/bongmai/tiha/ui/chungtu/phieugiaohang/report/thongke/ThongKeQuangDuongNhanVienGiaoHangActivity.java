package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke;

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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class ThongKeQuangDuongNhanVienGiaoHangActivity extends AppCompatActivity implements ThongKeQuangDuongNhanVienGiaoHangContract.View, View.OnClickListener {

    Toolbar toolbar;
    RecyclerView rvListData;
    TextView
            tvTongNV,
            tvTongKM,
            tvTongLanDi;
    EditText etFromDate, etToDate;
    Button btnView;

    ConstraintLayout  ctlCustomer;
    BaseService service;
    ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter adapterListData;
    List<ThongKeKMNVGHInfo> listData;
    ThongKeKMNVGHInfo employeeGiaoHangInfo;
    ThongKeQuangDuongNhanVienGiaoHangPresenter presenter;
    DateDialogAdapter adapterDateDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_thong_ke_quang_duong_nhan_vien_giao_hang);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();

    }

    public void findViewById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvListData = (RecyclerView) findViewById(R.id.rvListData);
        tvTongNV = (TextView) findViewById(R.id.tvTongNV);
        tvTongKM = (TextView) findViewById(R.id.tvTongKM);
        tvTongLanDi = findViewById(R.id.tvTongLanDi);
        btnView = findViewById(R.id.btnCustomerView);
        etFromDate = findViewById(R.id.etFromDate);
        etToDate = findViewById(R.id.etToDate);

        ctlCustomer = findViewById(R.id.ctlCustomer);

        rvListData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListData.setLayoutManager(llm);
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListData.getListAllData().clear();
                presenter.GetListThongKeKMNVGH(etFromDate.getText().toString(), etToDate.getText().toString());
            }
        });

    }

    public void loadData() {
        presenter = new ThongKeQuangDuongNhanVienGiaoHangPresenter(this);
        employeeGiaoHangInfo = new ThongKeKMNVGHInfo();
        listData = new ArrayList<>();
        etFromDate.setText(PublicVariables.NgayLamViec);
        etToDate.setText(PublicVariables.NgayLamViec);
        onBindData();

    }

    public void onBindData() {

        adapterListData = new ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter(ThongKeQuangDuongNhanVienGiaoHangActivity.this, listData);

        adapterListData.DataSourceChanged(new ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter.DataSourceChangedListener() {
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });

        adapterListData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ThongKeKMNVGHInfo itemThongke = adapterListData.getItem(position);
                Intent intent = new Intent(ThongKeQuangDuongNhanVienGiaoHangActivity.this, QuangDuongNhanVienGiaoHangChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isThongke", true);
                bundle.putString("ngayBD", etFromDate.getText().toString());
                bundle.putString("ngayKT", etToDate.getText().toString());
                bundle.putString("maNV", itemThongke.getEmployeeID());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        rvListData.setAdapter(adapterListData);
        adapterListData.notifyDataSetChanged();
        //Filter data

    }

    private void SummaryItems() {
        double  tongKM = 0, tonglandi = 0, tongnv = 0;
        for (ThongKeKMNVGHInfo item : adapterListData.getListAllData()) {
            tongKM += item.getTongKM();
            tonglandi += item.getSoChuyenDi();
            //tongnv += Double.parseDouble(item.getEmployeeID());
        }
        tvTongKM.setText(AppUtils.formatNumber("N1").format(tongKM) + "km");
        tvTongNV.setText(AppUtils.formatNumber("N0").format(adapterListData.getListAllData() == null ? 0 : adapterListData.getListAllData().size()));
        tvTongLanDi.setText(AppUtils.formatNumber("N0").format(tonglandi));
    }

    private void configToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Thống kê NVGH";
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



    @Override
    public void onGetListThongKeKMNVGHSuccess(List<ThongKeKMNVGHInfo> listResult) {
        listData = listResult;
        presenter.GetListThongKeKMNVGHCuuLong(etFromDate.getText().toString(), etToDate.getText().toString());
        adapterListData.addAll(listResult);
        SummaryItems();
    }

    @Override
    public void onGetListThongKeKMNVGHError(String error) {
        CommonUtils.showMessageError(getApplicationContext(), error);
    }

    @Override
    public void onGetListThongKeKMNVGHCuuLongSuccess(List<ThongKeKMNVGHInfo> listResult) {
        listData = listResult;
        adapterListData.addAll(listResult);
    }

    @Override
    public void onGetListThongKeKMNVGHCuuLongError(String error) {

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
            default:
                break;
        }
    }
}