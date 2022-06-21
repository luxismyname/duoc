package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.list;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.ThongKeGiaoNhanHangApdapter;
import com.bongmai.tiha.ui.map.MapActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiaoNhanHangActivity extends AppCompatActivity implements BaseActivity, GiaoNhanHangContract.View{
    ThongKeGiaoNhanHangApdapter adapterData;
    GiaoNhanHangPresenter giaoNhanHangPresenter;
    ThongKeGiaoNhanHangInfo thongke;
    RecyclerView rvData;
    TextView tvAddress, tvSoCT, tvSolandi, tvKho1, tvKho2;
    String address = "", soct = "", solandi = "";
    ConstraintLayout ctlMain;
    String listkho1 = "", listkho2 = "";
    List<KhoInfo> kholist;
    TextView
            tvTongTien,
            tvTongSL;

    EditText etTimKiem;
    Toolbar toolbar;
    ConstraintLayout ctlTotal;
    PhieuGiaoHangCondition condition;
    List<ThongKeGiaoNhanHangInfo> thongkeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_nhan_hang);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onGetListThongKeGiaoNhanHangSuccess(List<ThongKeGiaoNhanHangInfo> listResult) {
        this.thongkeList = listResult;
        adapterData.addAll(thongkeList);
        SumTongTien();

    }

    @Override
    public void onGetListThongKeGiaoNhanHangError(String error) {
    }

    @Override
    public void onGetSupplierSuccess(SupplierInfo itemResult) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("SoCT1", thongke.getSoCt());
        bundle.putString("SoCT", soct);
        String strAddress = tvAddress.getText().toString();
        bundle.putString("strAddress", strAddress);
        bundle.putBoolean("isNew", true);
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        bundle.putString("MaTrangThai", thongke.getMaTrangThai());
        bundle.putString("Solandi", solandi);
        bundle.putString("database", thongke.getTenTaiXe());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onGetSupplierError(String error) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetSupplierCuuLongSuccess(SupplierInfo itemResult) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("SoCT1", thongke.getSoCt());
        bundle.putString("SoCT", soct);
        String strAddress = tvAddress.getText().toString();
        bundle.putString("strAddress", strAddress);
        bundle.putBoolean("isNew", true);
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        bundle.putString("MaTrangThai", thongke.getMaTrangThai());
        bundle.putString("Solandi", solandi);
        bundle.putString("database", thongke.getTenTaiXe());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetSupplierCuuLongError(String error) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult) {
        kholist = listResult;
        for (KhoInfo itemKho : kholist) {
            listkho2 += itemKho.getMSK() + ",";
        }
        tvKho2.setText(listkho2);
    }

    @Override
    public void onGetListKhoCuuLongError(String error) {

    }

    @Override
    public void onGetListKhoSuccess(List<KhoInfo> listResult) {
        kholist = listResult;
        for (KhoInfo itemKho : kholist) {
            listkho1 += itemKho.getMSK() + ",";
        }
        tvKho1.setText(listkho1);


    }

    @Override
    public void onGetListKhoError(String error) {

    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);
        rvData = findViewById(R.id.rvData);
        ctlMain = findViewById(R.id.ctlMain);
        etTimKiem = findViewById(R.id.etTimKiem);
        tvTongTien = findViewById(R.id.tvTongTien);
        tvTongSL = findViewById(R.id.tvTongSL);
        tvAddress = findViewById(R.id.tvAddress);
        tvSoCT = findViewById(R.id.tvSoCT);
        tvSolandi = findViewById(R.id.tvSolandi);
        ctlTotal = findViewById(R.id.ctlTotal);
        tvKho1 = findViewById(R.id.tvKho1);
        tvKho2 = findViewById(R.id.tvKho2);
        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.title_activity_phieu_giao_nhan);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());

//        setSupportActionBar(toolbar);
//        String tieuDeForm = getResources().getString(R.string.giaohangform);
//        CommonUtils.configToolbar(GiaoNhanHangActivity.this, getSupportActionBar(), tieuDeForm, 0);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    public void onLoadData() {
        giaoNhanHangPresenter = new GiaoNhanHangPresenter(this);
        thongke = new ThongKeGiaoNhanHangInfo();
        giaoNhanHangPresenter.GetListKho();
//        giaoNhanHangPresenter.GetListKhoCuuLong();
        thongkeList = new ArrayList<>();
        kholist = new ArrayList<>();
        Bundle bundle = this.getIntent().getExtras();
        address = bundle.getString("strAddress");
        soct = bundle.getString("SoCT");
        solandi = bundle.getString("Solandi");
        tvAddress.setText(address);
        tvSoCT.setText(soct);
        tvSolandi.setText(solandi);
        onBindData();
        loadListPhieuGiaoHang();

    }

    void onBindData(){

        adapterData = new ThongKeGiaoNhanHangApdapter(this, thongkeList);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ThongKeGiaoNhanHangInfo itemThongke = adapterData.getItem(position);
                thongke = itemThongke;
//
//                Intent intent = new Intent(GiaoNhanHangActivity.this, MapActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("strAddress", address);
//                bundle.putBoolean("isNew", true);
//                bundle.putString("SoCT", thongke.getSoCt());
//                String strAddress = tvAddress.getText().toString();
//                bundle.putString("strAddress", strAddress);
//                bundle.putString("DiaChi", thongke.getDiachigiaohang());
//                bundle.putString("MaTrangThai", thongke.getMaTrangThai());
//                intent.putExtras(bundle);
//                startActivity(intent);
                Bundle bundle = new Bundle();
                switch (itemThongke.getTenTaiXe()){
                    case "TIHALOC":
                        bundle.putString("SoCT1", thongke.getSoCt());
                        giaoNhanHangPresenter.GetSupplier(thongke.getSupplierID());
                        finish();
                        break;
                    case "TIHADUOCQ10":
                        bundle.putString("SoCT1", thongke.getSoCt());
                        giaoNhanHangPresenter.GetSupplierCuuLong(thongke.getSupplierID());
                        finish();
                        break;
                    default:
                        break;
                }


            }
        });


        adapterData.setOnDataSetChangedListener(new BaseRecyclerViewEvent.OnDataSetChangedListener() {
            @Override
            public void onDataSetChanged() {
                SumTongTien();
            }
        });

        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    private void SumTongTien() {
        double tongTien = 0;
        for (ThongKeGiaoNhanHangInfo item : adapterData.getListAllData()) {
            tongTien += item.getThanhTien();
        }
        tvTongTien.setText(AppUtils.formatNumber("N0").format(tongTien));
        tvTongSL.setText(AppUtils.formatNumber("N0").format(adapterData.getListAllData().size()));

        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){

            ctlTotal.setVisibility(View.VISIBLE);

        } else {

            ctlTotal.setVisibility(View.GONE);

        }

    }


    private void loadListPhieuGiaoHang() {
        adapterData.getListAllData().clear();
        if (condition == null) {
            condition = new PhieuGiaoHangCondition();
            condition.setListKho("");
//            condition.setNgayBD(PublicVariables.NgayLamViec);
            DateReportInfo dateReportInfo = DateReportInfo.GetDateReport(DateReportInfo.ThangNay);
            condition.setNgayBD(dateReportInfo.StartDate);
//            condition.setNgayBD("23/11/2021");
            condition.setNgayKT(PublicVariables.NgayLamViec);
            condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
            condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
            condition.setTrangThai("10");


        }
        giaoNhanHangPresenter.GetListThongKeGiaoNhanHang(condition);


    }

}