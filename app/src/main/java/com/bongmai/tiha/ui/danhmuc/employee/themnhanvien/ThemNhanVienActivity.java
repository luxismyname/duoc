package com.bongmai.tiha.ui.danhmuc.employee.themnhanvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.ChucVuInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.chucvu.ChucVuListActivity;
import com.bongmai.tiha.ui.danhmuc.employee.bophannhanvien.ListBoPhanActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;

import java.util.List;

public class ThemNhanVienActivity extends AppCompatActivity implements ThemNhanVienContract.View, View.OnClickListener {

    DateDialogAdapter adapterDateDialog;
    private Context mContext;

    Toolbar toolbar;
    AutoCompleteTextView
            etTimKiem;
    EditText
            tvMaNV,
            tvTenNV,
            tvSDT1,
            tvNgaySinh,
            tvDiaChi,
            tvEmail,
            tvCMND,
            tvBHXH,
            tvBoPhan,
            tvVitri,

            tvBHYT;

    RadioButton
            rdoNam,
            rdoNu;
    Boolean
            isAdd,
            isEdit;
    EmployeeInfo
            employeeCurrent;

    ThemNhanVienPresenter
            presenter;

    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);

        onInit();
        onConfigToolbar();
        onLoadData();

    }

    private void onInit() {

        toolbar = findViewById(R.id.toolbar);

        etTimKiem = findViewById(R.id.etTimKiem);
        tvMaNV = findViewById(R.id.tvMaNV);
        tvTenNV = findViewById(R.id.tvTenNV);
        tvSDT1 = findViewById(R.id.tvSDT1);
        tvNgaySinh = findViewById(R.id.tvNgaySinh);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvEmail = findViewById(R.id.tvEmail);
        tvCMND = findViewById(R.id.tvCMND);
        tvBHXH = findViewById(R.id.tvBHXH);
        tvBHYT = findViewById(R.id.tvBHYT);
        rdoNam = findViewById(R.id.rdoNam);
        rdoNu = findViewById(R.id.rdoNu);
        tvBoPhan = findViewById(R.id.tvBoPhan);
        tvVitri = findViewById(R.id.tvViTri);
//        tvHeSoLuong = findViewById(R.id.tvHeSoLuong);
        tvBoPhan.setOnClickListener(this);
        tvVitri.setOnClickListener(this);
        tvNgaySinh.setOnClickListener(this);

    }

    private void onLoadData() {
        presenter = new ThemNhanVienPresenter(this);
        String employeeID = null;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            employeeID = bundle.getString("EmployeeID");

        }
        if (employeeID == null || employeeID.isEmpty())//Edit
        {
            AddNew();
        } else//Add new
        {
            Edit();
        }
    }

    private void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.themnhanvien_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AddNew() {
        isAdd = true;
        isEdit = false;
        employeeCurrent = new EmployeeInfo();
        SetEmployee(employeeCurrent);
    }

    private void Edit() {
        isAdd = false;
        isEdit = true;
        tvMaNV.setEnabled(false);
        presenter.GetEmployee(this.getIntent().getExtras().getString("EmployeeID"));

    }

    private void Save() {
        showProgressDialog(true);
        EmployeeInfo employeeSave = GetEmployee();

        if (isAdd)
            presenter.InsertEmployee(employeeSave);
        else
            presenter.UpdateEmployee(employeeSave);

    }



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

    void SetEmployee(EmployeeInfo employeeInfo){

                tvMaNV.setText(employeeInfo.getEmployeeID());
                tvTenNV.setText(employeeInfo.getEmployeeName());
                tvSDT1.setText(employeeInfo.getTel());

                if(employeeInfo.getSex() == EmployeeInfo.GIOITINH_NAM){
                    rdoNam.setChecked(true);
                } else if(employeeInfo.getSex() == EmployeeInfo.GIOITINH_NU){
                    rdoNu.setChecked(true);
                }

                tvNgaySinh.setText(AppUtils.formatDateToString(employeeInfo.getBirthDate(), "dd/MM/yyyy"));
                tvDiaChi.setText(employeeInfo.getAddress());
                tvEmail.setText(employeeInfo.getEmail());
                tvBoPhan.setText(employeeInfo.getDepartmentID());
                tvVitri.setText(employeeInfo.getPosition());
//                tvHeSoLuong.setText(employeeInfo.getBasicSalary());
                tvCMND.setText(employeeInfo.getPassportNo());
                tvBHXH.setText(employeeInfo.getSocialInsuranceNo());
                tvBHYT.setText(employeeInfo.getSecurityNo());

    }

    private EmployeeInfo GetEmployee() {

        employeeCurrent.setEmployeeID(tvMaNV.getText().toString());
        employeeCurrent.setEmployeeName(tvTenNV.getText().toString());
        employeeCurrent.setTel(tvSDT1.getText().toString());
        if(rdoNam.isChecked()){
            employeeCurrent.setSex(EmployeeInfo.GIOITINH_NAM);
        } else if (rdoNu.isChecked()){
            employeeCurrent.setSex(EmployeeInfo.GIOITINH_NU);
        }
        employeeCurrent.setBirthDate(AppUtils.formatStringToDateUtil(tvNgaySinh.getText().toString(), "dd/MM/yyyy"));
        employeeCurrent.setAddress(tvDiaChi.getText().toString());
        employeeCurrent.setEmail(tvEmail.getText().toString());
        employeeCurrent.setDepartmentID(tvBoPhan.getText().toString());
        employeeCurrent.setPosition(tvVitri.getText().toString());
        employeeCurrent.setBasicSalary("0");
        employeeCurrent.setPassportNo(tvCMND.getText().toString());
        employeeCurrent.setSocialInsuranceNo(tvBHXH.getText().toString());
        employeeCurrent.setSecurityNo(tvBHYT.getText().toString());

        return employeeCurrent;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themkhachhang, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onGetEmployeeSuccess(EmployeeInfo itemResult) {
        employeeCurrent = itemResult;
        SetEmployee(itemResult);

    }

    @Override
    public void onGetEmployeeError(String error) {

    }

    @Override
    public void onInsertEmployeeSuccess(EmployeeInfo itemResult) {
        showProgressDialog(false);
        employeeCurrent.setEmployeeID(itemResult.getEmployeeID());
        tvTenNV.setText(itemResult.getEmployeeID());
        Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EmployeeInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();


    }

    @Override
    public void onInsertEmployeeError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);

    }

    @Override
    public void onUpdateEmployeeSuccess(EmployeeInfo itemResult) {
        showProgressDialog(false);

        employeeCurrent.setEmployeeID(itemResult.getEmployeeID());
        tvMaNV.setText(itemResult.getEmployeeID());

        Toast.makeText(this, "Cập nhật thông tin nhân viên thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("EmployeeInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onUpdateEmployeeError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

//    @Override
//    public void onGetListBoPhanSuccess(EmployeeInfo itemResult) {
//        this.employeeCurrent = itemResult;
//    }
//
//    @Override
//    public void onGetListBoPhanError(String error) {
//
//    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgressDialog() {
        showProgressDialog(false);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:

//                isLuuVaBan = false;
                Save();
//                AddNew();
                break;
            case R.id.action_edit:
                Edit();

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//intent giữa các activity khác nhau
    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()){
            case R.id.tvBoPhan:
                intent = new Intent(ThemNhanVienActivity.this, ListBoPhanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.getSerializable("BoPhanInfo");
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_BOPHAN);
                break;
            case R.id.tvViTri:
                intent = new Intent(ThemNhanVienActivity.this, ChucVuListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.getSerializable("ChucVuInfo");
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CHUCVU);
                break;
            case R.id.tvNgaySinh:
                try {
                    adapterDateDialog = new DateDialogAdapter(v, tvNgaySinh.getText().toString());
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

    private static final int REQUEST_BOPHAN = 1;
    private static final int REQUEST_CHUCVU = 2;


    //bundle dữ liệu giữa các activity khác nhau với nhau
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    Bundle bundle;
    if(resultCode == RESULT_OK){
        switch (requestCode){
            case REQUEST_BOPHAN:
                bundle = intent.getExtras();
                BoPhanInfo boPhanInfo = (BoPhanInfo) bundle.getSerializable("BoPhanInfo");
                employeeCurrent.setDepartmentID(boPhanInfo.getDepartmentID());
                tvBoPhan.setText(boPhanInfo.getDepartmentID());
                break;
            case  REQUEST_CHUCVU:
                bundle = intent.getExtras();
                ChucVuInfo chucVuInfo = (ChucVuInfo) bundle.getSerializable("ChucVuInfo");
                employeeCurrent.setPosition(chucVuInfo.getCVID());
                tvVitri.setText(chucVuInfo.getCVID());
                break;
            default:
                break;
        }
    }
    }
}