package com.bongmai.tiha.ui.danhmuc.trangthai;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class TrangThaiActivity extends AppCompatActivity implements BaseActivity, TrangThaiContract.View {

    Toolbar toolbar;
    BaseService service;
    Spinner spnSpinnerTrangthai;
    List<TrangThaiLoaiPhieuInfo> listData;
    TrangThaiPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_trang_thai);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spnSpinnerTrangthai = findViewById(R.id.spnTrangthai);
        listData = new ArrayList<>();

        onBindData();
    }

    private void onBindData() {
        //trangThaiInfo = new TrangThaiInfo()

        TrangThaiAdapter adapterTrangThai = new TrangThaiAdapter(this, listData);
        spnSpinnerTrangthai.setAdapter(adapterTrangThai);
        spnSpinnerTrangthai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               onItemSelectedHandler(parent, view, position, id);
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }


    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        TrangThaiLoaiPhieuInfo employee = (TrangThaiLoaiPhieuInfo) adapter.getItem(position);

        Toast.makeText(getApplicationContext(), "Selected Employee: " + employee.getTenTrangThai() ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.trangthai_txt);
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
    public void onLoadData() {
        presenter = new TrangThaiPresenter(this);

        loadTrangthai();
    }

    private void loadTrangthai() {
        presenter.GetListTrangThai("PHIEUXUAT", PublicVariables.nguoiDungInfo.getUserName());
    }

    @Override
    public void onGetListTrangThaiSuccess(List<TrangThaiLoaiPhieuInfo> listResult) {
        listData = listResult;
//
        onBindData();
    }

    @Override
    public void onGetListTrangThaiError(String error) {

    }

//    @Override
//    public void onGetListTrangThaiSuccess(List<TrangThaiInfo> listResult) {
//        listData = listResult;
//
//        onBindData();
//    }
//
//    @Override
//    public void onGetListTrangThaiError(String error) {
//
//    }
}