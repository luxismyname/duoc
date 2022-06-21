package com.bongmai.tiha.ui.chungtu.phieugiaohang.chonnhanviengiaohang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChonEmployeeActivity extends AppCompatActivity implements View.OnClickListener, ChonEmployeeContract.View  {

    Toolbar toolbar;
    Button btnApDung;
    ImageView btnClear;
    RecyclerView rvDanhSachKho;
    CheckBox chkTatCa;
    View progressBar, ctlMain;
    EditText etTimKiem;
    ChonEmployeeAdapter adapterEmployeeChecked;
    String danhSachEmployeeStr = "";
    Dialog progressDialog;
    ChonEmployeePresenter checkPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_employee);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        findViewById();
        configToolbar();
        loadData();
    }

    private void findViewById() {
        toolbar = findViewById(R.id.toolbar);

        etTimKiem = findViewById(R.id.etTimKiem);

        btnApDung = findViewById(R.id.btnApDung);
        btnClear = findViewById(R.id.btnClear);

        rvDanhSachKho = findViewById(R.id.rvDanhSachKho);

        progressBar = findViewById(R.id.progressBar);
        ctlMain = findViewById(R.id.ctlMain);

        chkTatCa = findViewById(R.id.chkTatCa);

        btnApDung.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty())
                    btnClear.setVisibility(View.VISIBLE);
                else
                    btnClear.setVisibility(View.GONE);
                adapterEmployeeChecked.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        chkTatCa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                adapterEmployeeChecked.checkAllData(isChecked);
            }
        });
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.chonkholist_tieudeform);
        CommonUtils.configToolbar(ChonEmployeeActivity.this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        checkPresenter = new ChonEmployeePresenter(this);
        rvDanhSachKho.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChonEmployeeActivity.this);
        rvDanhSachKho.setLayoutManager(linearLayoutManager);
        rvDanhSachKho.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //get bunble
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            danhSachEmployeeStr = bundle.getString("DanhSachEmployee");
        }

        if(PublicVariables.nguoiDungInfo.getListEmployee() != null && PublicVariables.nguoiDungInfo.getListEmployee().size() > 0)
        {
            onBindDataSourceRV(true);
        }
        else {
            showProgressDialog(true);
            checkPresenter.GetListEmployee();
        }
    }

    private void onBindDataSourceRV(Boolean isAddKhoChon) {
        List<EmployeeInfo> listKhoChon = new ArrayList<>();
        if (isAddKhoChon) {
            for (EmployeeInfo itemKho : PublicVariables.nguoiDungInfo.getListEmployee()) {
                if (isKhoFilter(itemKho.getEmployeeID())) {
                    itemKho.setChon(true);
                    listKhoChon.add(itemKho);
                } else {
                    itemKho.setChon(false);
                }
            }
        }

        adapterEmployeeChecked = new ChonEmployeeAdapter(this, PublicVariables.nguoiDungInfo.getListEmployee());
        rvDanhSachKho.setAdapter(adapterEmployeeChecked);
        adapterEmployeeChecked.notifyDataSetChanged();
        adapterEmployeeChecked.setListEmployeeChon(listKhoChon);
    }

    private boolean isKhoFilter(String msk) {
        String mskFilter = "";
        for (int i = 0; i < danhSachEmployeeStr.split(",").length; i++) {
            mskFilter = danhSachEmployeeStr.split(",")[i];
            if (msk.equals(mskFilter))
                return true;
        }
        return false;
    }

    //on click control
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnApDung:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                Map<String, String> mapListKhoChon = adapterEmployeeChecked.getMapKhoChon();
                bundle.putString("MaNV", mapListKhoChon.get("MaNV"));
                bundle.putString("TenNV", mapListKhoChon.get("TenNV"));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.btnClear:
                etTimKiem.setText("");
                break;
            default:
                break;
        }
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


    @Override
    public void onGetListEmployeeSuccess(List<EmployeeInfo> listResult) {
        showProgressDialog(false);
        PublicVariables.nguoiDungInfo.setListEmployee(listResult);
        onBindDataSourceRV(false);
    }

    @Override
    public void onGetListEmployeeError(String error) {
        showProgressDialog(false);
        Toast.makeText(ChonEmployeeActivity.this, "Lấy danh sách nhân viên thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }
}