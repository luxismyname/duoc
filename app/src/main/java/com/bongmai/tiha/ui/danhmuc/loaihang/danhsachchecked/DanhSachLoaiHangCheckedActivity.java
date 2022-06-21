package com.bongmai.tiha.ui.danhmuc.loaihang.danhsachchecked;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class DanhSachLoaiHangCheckedActivity extends AppCompatActivity implements View.OnClickListener, DanhSachLoaiHangCheckedContract.View {
    Toolbar toolbar;
    Button btnApDung;
    ImageView btnClear;
    RecyclerView rvDanhSachLoaiHang;
    CheckBox chkTatCa;
    View progressBar, ctlMain;
    EditText etTimKiem;
    DanhSachLoaiHangCheckedAdapter adapterDanhSachLoaiHang;
    String danhSachLoaiHangStr = "";
    Dialog progressDialog;
    DanhSachLoaiHangCheckedPresenter danhSachLoaiHangCheckedPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonloaihang);
        //Khong hien thi ban phim luc load form
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

        rvDanhSachLoaiHang = findViewById(R.id.rvDanhSachLoaiHang);

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
                adapterDanhSachLoaiHang.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        chkTatCa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                adapterDanhSachLoaiHang.checkAllData(isChecked);
            }
        });
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.chonloaihanglist_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        danhSachLoaiHangCheckedPresenter = new DanhSachLoaiHangCheckedPresenter(this);

        rvDanhSachLoaiHang.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachLoaiHangCheckedActivity.this);
        rvDanhSachLoaiHang.setLayoutManager(linearLayoutManager);
        rvDanhSachLoaiHang.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //get bunble
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            danhSachLoaiHangStr = bundle.getString("DanhSachLoaiHang");
        }

        if (PublicVariables.listLoaiHang != null && PublicVariables.listLoaiHang.size() > 0) {
            onBindDataSourceRV(true);
        } else {
            showProgressDialog(true);
            danhSachLoaiHangCheckedPresenter.GetListLoaiHangByUser(PublicVariables.nguoiDungInfo.getUserName());
        }
    }

    private void onBindDataSourceRV(Boolean isAddChon) {
        List<LoaiHangInfo> listLoaiHangChon = new ArrayList<>();
        if (isAddChon) {
            for (LoaiHangInfo itemLoaiHang : PublicVariables.listLoaiHang) {
                if (isLoaiHangFilter(String.valueOf(itemLoaiHang.getCategory_ID()))) {
                    itemLoaiHang.setChon(true);
                    listLoaiHangChon.add(itemLoaiHang);
                } else {
                    itemLoaiHang.setChon(false);
                }
            }
        }

        adapterDanhSachLoaiHang = new DanhSachLoaiHangCheckedAdapter(this, PublicVariables.listLoaiHang);
        rvDanhSachLoaiHang.setAdapter(adapterDanhSachLoaiHang);
        adapterDanhSachLoaiHang.notifyDataSetChanged();
        adapterDanhSachLoaiHang.setListLoaiHangChon(listLoaiHangChon);
    }

    private boolean isLoaiHangFilter(String msk) {
        if (TextUtils.isEmpty(danhSachLoaiHangStr)) return false;
        String mskFilter = "";
        for (int i = 0; i < danhSachLoaiHangStr.split(",").length; i++) {
            mskFilter = danhSachLoaiHangStr.split(",")[i];
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
                Map<String, String> mapListLoaiHangChon = adapterDanhSachLoaiHang.getMapLoaiHangChon();
                bundle.putString("MaLoaiHang", mapListLoaiHangChon.get("MaLoaiHang"));
                bundle.putString("TenLoaiHang", mapListLoaiHangChon.get("TenLoaiHang"));
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
    public void onGetListLoaiHangByUserSuccess(List<LoaiHangInfo> listResult) {
        showProgressDialog(false);
        PublicVariables.listLoaiHang = listResult;
        onBindDataSourceRV(false);
    }

    @Override
    public void onGetListLoaiHangByUserError(String error) {
        showProgressDialog(false);
        Toast.makeText(DanhSachLoaiHangCheckedActivity.this, "Lấy danh sách loại hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }
}
