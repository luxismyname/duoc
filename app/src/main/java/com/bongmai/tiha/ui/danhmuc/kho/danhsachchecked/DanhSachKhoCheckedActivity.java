package com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class DanhSachKhoCheckedActivity extends AppCompatActivity implements View.OnClickListener, DanhSachKhoCheckedContract.View {
    Toolbar toolbar;
    Button btnApDung;
    ImageView btnClear;
    RecyclerView rvDanhSachKho;
    CheckBox chkTatCa;
    View progressBar, ctlMain;
    EditText etTimKiem;
    DanhSachKhoCheckedAdapter adapterDanhSachKho;
    String danhSachKhoStr = "";
    Dialog progressDialog;
    DanhSachKhoCheckedPresenter danhSachKhoCheckedPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonkho);
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
                adapterDanhSachKho.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        chkTatCa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                adapterDanhSachKho.checkAllData(isChecked);
            }
        });
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.chonkholist_tieudeform);
        CommonUtils.configToolbar(DanhSachKhoCheckedActivity.this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        danhSachKhoCheckedPresenter = new DanhSachKhoCheckedPresenter(this);
        rvDanhSachKho.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachKhoCheckedActivity.this);
        rvDanhSachKho.setLayoutManager(linearLayoutManager);
        rvDanhSachKho.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //get bunble
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            danhSachKhoStr = bundle.getString("DanhSachKho");
        }

        if(PublicVariables.nguoiDungInfo.getListKho() != null && PublicVariables.nguoiDungInfo.getListKho().size() > 0)
        {
            onBindDataSourceRV(true);
        }
        else {
            showProgressDialog(true);
            danhSachKhoCheckedPresenter.GetListKhoByUser(PublicVariables.nguoiDungInfo.getUserName());
        }
    }

    private void onBindDataSourceRV(Boolean isAddKhoChon) {
        List<KhoInfo> listKhoChon = new ArrayList<>();
        if (isAddKhoChon) {
            for (KhoInfo itemKho : PublicVariables.nguoiDungInfo.getListKho()) {
                if (isKhoFilter(String.valueOf(itemKho.getMSK()))) {
                    itemKho.setChon(true);
                    listKhoChon.add(itemKho);
                } else {
                    itemKho.setChon(false);
                }
            }
        }

        adapterDanhSachKho = new DanhSachKhoCheckedAdapter(this, PublicVariables.nguoiDungInfo.getListKho());
        rvDanhSachKho.setAdapter(adapterDanhSachKho);
        adapterDanhSachKho.notifyDataSetChanged();
        adapterDanhSachKho.setListKhoChon(listKhoChon);
    }

    private boolean isKhoFilter(String msk) {
        String mskFilter = "";
        for (int i = 0; i < danhSachKhoStr.split(",").length; i++) {
            mskFilter = danhSachKhoStr.split(",")[i];
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
                Map<String, String> mapListKhoChon = adapterDanhSachKho.getMapKhoChon();
                bundle.putString("MaKho", mapListKhoChon.get("MaKho"));
                bundle.putString("TenKho", mapListKhoChon.get("TenKho"));
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
    public void onGetListKhoByUserSuccess(List<KhoInfo> listResult) {
        showProgressDialog(false);
        PublicVariables.nguoiDungInfo.setListKho(listResult);
        onBindDataSourceRV(false);
    }

    @Override
    public void onGetListKhoByUserError(String error) {
        showProgressDialog(false);
        Toast.makeText(DanhSachKhoCheckedActivity.this, "Lấy danh sách kho thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }
}
