package com.bongmai.tiha.ui.chungtu.phieugiaohang.report;

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
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.chonnhanviengiaohang.ChonEmployeeActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class QuangDuongNhanVienGiaoHangActivity extends AppCompatActivity implements QuangDuongNhanVienGiaoHangContract.View, View.OnClickListener {

    Toolbar toolbar;
    RecyclerView rvListData;
    TextView
            tvTongNV,
            tvTongKM;
    EditText etFromDate, etToDate, etChooseEmployee;
    Button btnView, btnCustomerView;

    ConstraintLayout ctlView, ctlCustomer;
    BaseService service;
    QuangDuongNhanVienGiaoHangRecycleViewAdapter adapterListData;
    List<EmployeeGiaoHangInfo> listData;
    EmployeeGiaoHangInfo employeeGiaoHangInfo;
    EmployeeGiaoHangCondition condition;
    QuangDuongNhanVienGiaoHangPresenter presenter;

    private static final int REQUEST_NHANVIENBANHANG = 0;

    DateDialogAdapter adapterDateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_quang_duong_nhan_vien_giao_hang);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void loadData() {

        presenter = new QuangDuongNhanVienGiaoHangPresenter(this);
        employeeGiaoHangInfo = new EmployeeGiaoHangInfo();
        listData = new ArrayList<>();
        etFromDate.setText(PublicVariables.NgayLamViec);
        etToDate.setText(PublicVariables.NgayLamViec);
        onBindData();
        loadListQuangDuongNhanVienGiaoHang();


    }

    private void configToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = "Quãng đường nhân viên giao hàng";
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


    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvListData = (RecyclerView) findViewById(R.id.rvListData);
        tvTongNV = (TextView) findViewById(R.id.tvTongNV);
        tvTongKM = (TextView) findViewById(R.id.tvTongKM);
        btnView = findViewById(R.id.btnView);
        etFromDate = findViewById(R.id.etFromDate);
        etToDate = findViewById(R.id.etToDate);
        etChooseEmployee = findViewById(R.id.etChooseEmployee);

        ctlView = findViewById(R.id.ctlView);
        ctlCustomer = findViewById(R.id.ctlCustomer);
        btnCustomerView = findViewById(R.id.btnCustomerView);

        rvListData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListData.setLayoutManager(llm);
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        listData = new ArrayList<>();

        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            ctlCustomer.setVisibility(View.GONE);
            ctlView.setVisibility(View.VISIBLE);
        } else {
            ctlView.setVisibility(View.GONE);
            ctlCustomer.setVisibility(View.VISIBLE);
        }

        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);
        etChooseEmployee.setOnClickListener(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListData.getListAllData().clear();
                if(TextUtils.isEmpty(etChooseEmployee.getText().toString())){
                    condition.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                } else {
                    condition.setEmployeeID(etChooseEmployee.getText().toString());
                }

                condition.setNgayBD(etFromDate.getText().toString());
                condition.setNgayKT(etToDate.getText().toString());
                presenter.GetListQuangDuongNhanVienGiaoHang(condition);
            }
        });

        btnCustomerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListData.getListAllData().clear();
                condition.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                condition.setNgayBD(etFromDate.getText().toString());
                condition.setNgayKT(etToDate.getText().toString());
                presenter.GetListQuangDuongNhanVienGiaoHang(condition);
            }
        });



    }

    private void loadListQuangDuongNhanVienGiaoHang() {

        adapterListData.getListAllData().clear();
        if (condition == null) {
            condition = new EmployeeGiaoHangCondition();
            condition.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
            condition.setNgayBD(PublicVariables.NgayLamViec);
            condition.setNgayKT(PublicVariables.NgayLamViec);

        }
        presenter.GetListQuangDuongNhanVienGiaoHang(condition);

    }

    private void onBindData() {
        adapterListData = new QuangDuongNhanVienGiaoHangRecycleViewAdapter(QuangDuongNhanVienGiaoHangActivity.this, listData);
        adapterListData.DataSourceChanged(new QuangDuongNhanVienGiaoHangRecycleViewAdapter.DataSourceChangedListener() {
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
        double  tongKM = 0;
        for (EmployeeGiaoHangInfo item : adapterListData.getListAllData()) {
            tongKM += item.getQuangDuong();
        }
        tvTongKM.setText(AppUtils.formatNumber("N1").format(tongKM) + "km");
        tvTongNV.setText(AppUtils.formatNumber("N0").format(adapterListData.getListAllData() == null ? 0 : adapterListData.getListAllData().size()) );
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangSuccess(List<EmployeeGiaoHangInfo> listResult) {
        listData = listResult;
        employeeGiaoHangInfo = new EmployeeGiaoHangInfo();
        String stt = "";
        for (int i = 0; i<listData.size(); i++){

            employeeGiaoHangInfo = listData.get(i);
            stt = AppUtils.formatNumber("N0").format(i+1);


        }

        presenter.GetListQuangDuongNhanVienGiaoHangCuuLong(condition);
        adapterListData.addAll(listResult);

        SummaryItems();
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangError(String error) {
        CommonUtils.showMessageError(getApplicationContext(), error);
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult) {
        listData = listResult;
        adapterListData.addAll(listResult);
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangCuuLongError(String error) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()){
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

//            case R.id.etChooseEmployee:
//                intent = new Intent(this, ChonEmployeeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                bundle = new Bundle();
//                bundle.putString("DanhSachEmployee", danhSachEmployeeStr);
//                intent.putExtras(bundle);
//                startActivityForResult(intent, FILTER_EMPLOYEE_REQUESTCODE);
//                break;

            case R.id.etChooseEmployee:
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
                    employeeGiaoHangInfo.setEmployeeID(employeeInfo.getEmployeeID());
                    etChooseEmployee.setText(employeeInfo.getEmployeeID());

                default:
                    break;
            }
        }
    }
}