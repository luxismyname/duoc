package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet;

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
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.QuangDuongNhanVienGiaoHangActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;
import com.bongmai.tiha.ui.map.MapActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class QuangDuongNhanVienGiaoHangChiTietActivity  extends AppCompatActivity implements QuangDuongNhanVienGiaoHangChiTietContract.View, View.OnClickListener{


    Toolbar toolbar;
    RecyclerView rvListData;
    TextView
            tvTongNV,
            tvTongKM;
    EditText etFromDate, etToDate, etChooseEmployee;
    Button btnView, btnCustomerView;

    ConstraintLayout ctlView, ctlCustomer;
    BaseService service;
    QuangDuongNhanVienGiaoHangChiTietAdapter adapterListData;
    List<EmployeeGiaoHangInfo> listData;
    EmployeeGiaoHangInfo employeeGiaoHangInfo;
    EmployeeGiaoHangCondition condition;
    QuangDuongNhanVienGiaoHangChiTietPresenter presenter;

    private static final int REQUEST_NHANVIENBANHANG = 0;

    DateDialogAdapter adapterDateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quang_duong_nhan_vien_giao_hang_chi_tiet);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void loadData() {

        presenter = new QuangDuongNhanVienGiaoHangChiTietPresenter(this);
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
                presenter.GetListQuangDuongNhanVienGiaoHangChiTiet(condition);
            }
        });

        btnCustomerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListData.getListAllData().clear();
                condition.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                condition.setNgayBD(etFromDate.getText().toString());
                condition.setNgayKT(etToDate.getText().toString());
                presenter.GetListQuangDuongNhanVienGiaoHangChiTiet(condition);
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
        presenter.GetListQuangDuongNhanVienGiaoHangChiTiet(condition);

    }

    private void onBindData() {
        adapterListData = new QuangDuongNhanVienGiaoHangChiTietAdapter(QuangDuongNhanVienGiaoHangChiTietActivity.this, listData);
        adapterListData.DataSourceChanged(new QuangDuongNhanVienGiaoHangChiTietAdapter.DataSourceChangedListener() {
            @Override
            public void onDataSourceChanged() {
                SummaryItems();
            }
        });


        adapterListData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                EmployeeGiaoHangInfo itemGiaoHang = adapterListData.getItem(position);

                if(itemGiaoHang.getDiachibatdau() == null) {
                    CommonUtils.showMessage(QuangDuongNhanVienGiaoHangChiTietActivity.this, "Không thể xem bản đồ ở đơn hàng không có địa chỉ bắt đầu, vui lòng chọn đơn hàng khác!");
                }
                else {

                        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("dcbd", itemGiaoHang.getDiachibatdau());
                        bundle.putString("dcgh", itemGiaoHang.getDiachigiaohang());
                        bundle.putBoolean("isView", true);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();


                }

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
    public void onGetListQuangDuongNhanVienGiaoHangChiTietSuccess(List<EmployeeGiaoHangInfo> listResult) {
        listData = listResult;
        adapterListData.addAll(listResult);
        presenter.GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(condition);
        SummaryItems();
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangChiTietError(String error) {
        CommonUtils.showMessageError(getApplicationContext(), error);
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietSuccess(List<EmployeeGiaoHangInfo> listResult) {
        listData = listResult;
        adapterListData.addAll(listResult);
    }

    @Override
    public void onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietError(String error) {

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