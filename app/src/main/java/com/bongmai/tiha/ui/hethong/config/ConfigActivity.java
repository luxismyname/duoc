package com.bongmai.tiha.ui.hethong.config;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BMConfigInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;

import java.util.List;

public class ConfigActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener, ConfigContract.View {

    Toolbar toolbar;
    String meid = "";
    String versionInfo = "";
    EditText
            etKhachHangMacDinh,
            etCompany,
            etAddress,
            etTel,
            etLuuY,
            etMeid;
    TextView tvVersion;
    Button btnSave, btnMeid;
    BaseService BService;
    BMConfigInfo bmConfigInfo = new BMConfigInfo();
    ConfigPresenter configPresenter;
    private static final int REQUEST_KHACHHANG = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);

        etKhachHangMacDinh = findViewById(R.id.etKhachHangMacDinh);
        etCompany = findViewById(R.id.etCompany);
        etAddress = findViewById(R.id.etAddress);
        etTel = findViewById(R.id.etTel);
        etLuuY = findViewById(R.id.etLuuY);
        etMeid = findViewById(R.id.edtMeid);
        tvVersion = findViewById(R.id.tvVersion);
        btnSave = findViewById(R.id.btnSave);
        btnMeid = findViewById(R.id.btnMEID);

        btnSave.setOnClickListener(this);
        etKhachHangMacDinh.setOnClickListener(this);

        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (telephonyManager != null) {
                    try {
                        meid = telephonyManager.getImei();
                    } catch (Exception e) {
                        e.printStackTrace();
                        meid = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

                    }
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (telephonyManager != null) {
                    meid = telephonyManager.getDeviceId();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
            }
        }

        btnMeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMeid.setText(meid);
            }
        });


        Context context = getApplicationContext(); // or activity.getApplicationContext()
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

         versionInfo = "Không thể tìm thấy phiên bản phần mềm"; // initialize String

        try {
            versionInfo = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvVersion.setText("Phiên bản hiện tại: " + versionInfo);

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.config_tieudeform);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onLoadData() {
        BService = new BaseService(this);
        configPresenter = new ConfigPresenter(this);
        configPresenter.GetListKhoCuuLong();
        SetConfig();
    }

    private void SetConfig() {
        if (!TextUtils.isEmpty(bmConfigInfo.getKhachHangMacDinh())) {
            configPresenter.GetSupplier(bmConfigInfo.getKhachHangMacDinh());
        }
        etCompany.setText(bmConfigInfo.getCompany());
        etAddress.setText(bmConfigInfo.getAddress());
        etTel.setText(bmConfigInfo.getTel());
        etLuuY.setText(bmConfigInfo.getLuuY());
    }

    private BMConfigInfo GetConfig() {
        bmConfigInfo.setCompany(etCompany.getText().toString());
        bmConfigInfo.setAddress(etAddress.getText().toString());
        bmConfigInfo.setTel(etTel.getText().toString());
        bmConfigInfo.setLuuY(etLuuY.getText().toString());
        return bmConfigInfo;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnSave:
                BService.UpdateConfig(GetConfig());
                Toast.makeText(this, "Cập nhật cấu hình thành công!", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.etKhachHangMacDinh:
                intent = new Intent(this, SupplierListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("isCombobox", true);
                intent.putExtras(bundle1);
                startActivityForResult(intent, REQUEST_KHACHHANG);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_KHACHHANG:
                    bundle = intent.getExtras();
                    SupplierInfo supplierInfo = (SupplierInfo) bundle.getSerializable("SupplierInfo");
                    if (supplierInfo == null) return;
                    bmConfigInfo.setKhachHangMacDinh(supplierInfo.getSupplier_ID());
                    etKhachHangMacDinh.setText(supplierInfo.getCompany_Name());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onGetSupplierSuccess(SupplierInfo itemResult) {
        etKhachHangMacDinh.setText(itemResult.getCompany_Name());
        bmConfigInfo.setKhachHangMacDinh(itemResult.getSupplier_ID());
    }

    @Override
    public void onGetSupplierError(String error) {
        Toast.makeText(this, "Lấy thông tin khách hàng mặc định thất bại!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult) {

        String listkho2 ="";
        for (KhoInfo itemKho : listResult) {
            listkho2 += itemKho.getMSK() + ",";
        }
        etLuuY.setText(listkho2);

    }

    @Override
    public void onGetListKhoCuuLongError(String error) {

    }
}
