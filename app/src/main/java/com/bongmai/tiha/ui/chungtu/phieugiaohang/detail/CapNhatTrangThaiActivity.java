package com.bongmai.tiha.ui.chungtu.phieugiaohang.detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.ThongKeGiaoNhanHangFragment;
import com.bongmai.tiha.ui.danhmuc.employee.themnhanvien.ThemNhanVienPresenter;
import com.bongmai.tiha.ui.danhmuc.kho.list.KhoListActivity;
import com.bongmai.tiha.ui.danhmuc.trangthai.TrangThaiActivity;

import java.io.Serializable;

public class CapNhatTrangThaiActivity extends AppCompatActivity implements CapNhatTrangThaiContract.View, View.OnClickListener {
    PhieuXuatInfo phieuXuatInfo;
    ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo;
    CapNhatTrangThaiPresenter presenter;
    private static final int GETTRANGTHAI = 0;
    Toolbar toolbar;
    EditText etSoCT, etMaTrangThaiHienTai, etTenTrangThaiHientai, etMaTrangThaiCu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_trang_thai);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    private void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etSoCT = findViewById(R.id.etSoCT);
        etMaTrangThaiHienTai = findViewById(R.id.etMaTrangThaiHienTai);
        etTenTrangThaiHientai = findViewById(R.id.etTenTrangThaiHienTai);
        etMaTrangThaiCu = findViewById(R.id.etMaTrangThaiCu);
    }

    private void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.TrangThailist_tieudeform);
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

    private void onLoadData() {
        presenter = new CapNhatTrangThaiPresenter(this);
        String soct = null;
        thongKeGiaoNhanHangInfo = new ThongKeGiaoNhanHangInfo();

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            soct = bundle.getString("SoCT");
        }

        presenter.GetPhieuXuat(soct);

    }

    private ThongKeGiaoNhanHangInfo GetPhieuGiaoNhan(){
            thongKeGiaoNhanHangInfo.setSoCt(etSoCT.getText().toString());
            thongKeGiaoNhanHangInfo.setMaTrangThai(etMaTrangThaiHienTai.getText().toString());
            thongKeGiaoNhanHangInfo.setTenTrangThai(etTenTrangThaiHientai.getText().toString());
            thongKeGiaoNhanHangInfo.setMaTrangThaiCu(etMaTrangThaiCu.getText().toString());
        return thongKeGiaoNhanHangInfo;
    }
    void CapNhatTrangThai(PhieuXuatInfo phieuXuatInfo){
        etSoCT.setText(phieuXuatInfo.getSoCt());
        etMaTrangThaiCu.setText(phieuXuatInfo.getTrangThai());

        etMaTrangThaiHienTai.setOnClickListener(this);
    }



    @Override
    public void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult) {
        phieuXuatInfo = phieuXuatResult;
        CapNhatTrangThai(phieuXuatResult);
    }

    @Override
    public void onGetPhieuXuatError(String error) {

    }

    @Override
    public void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult) {
        thongKeGiaoNhanHangInfo = itemResult;
        thongKeGiaoNhanHangInfo.setSoCt(etSoCT.getText().toString());
        thongKeGiaoNhanHangInfo.setMaTrangThai(etMaTrangThaiHienTai.getText().toString());
        thongKeGiaoNhanHangInfo.setTenTrangThai(etTenTrangThaiHientai.getText().toString());
        thongKeGiaoNhanHangInfo.setMaTrangThaiCu(etMaTrangThaiCu.getText().toString());
        Toast.makeText(this, "Cập nhật thông tin nhân viên thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ThongKeGiaoNhanHangInfo",  itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onUpdateTrangThaiPhieuXuatError(String error) {
        Toast.makeText(this, "Cập nhật trạng thái lỗi. Chi tiết: " + error, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.etMaTrangThaiHienTai:

                intent = new Intent(this, TrangThaiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, GETTRANGTHAI);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        TrangThaiInfo trangThaiInfo;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GETTRANGTHAI:
                    bundle = intent.getExtras();
                    trangThaiInfo = (TrangThaiInfo) bundle.getSerializable("TrangThaiInfo");
                    phieuXuatInfo.setTrangThai(trangThaiInfo.getMaTrangThai());
                    etMaTrangThaiHienTai.setText(phieuXuatInfo.getTrangThai());
                    etTenTrangThaiHientai.setText(trangThaiInfo.getTenTrangThai());
                    break;
                default:
                    break;
            }
        }
    }

    private void Save() {

        presenter.UpdateTrangThaiPhieuXuat(GetPhieuGiaoNhan());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_ok, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_luu:
                Save();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}