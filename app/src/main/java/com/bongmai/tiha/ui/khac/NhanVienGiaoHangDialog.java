package com.bongmai.tiha.ui.khac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;

public class NhanVienGiaoHangDialog extends Activity implements BaseActivity, View.OnClickListener {

    public static final String LOAI_NHANVIENBANHANG = "NVBH";
    public static final String LOAI_NHANVIENGIAOHANG = "NVGH";
    private static final int REQUEST_NHANVIEN = 789;
    String mEmployeeID, mEmployeeName;
    TextView tvTitle;
    EditText etNhanVien;
    Button btnOK, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien_giao_hang_dialog);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnOK:
                intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("EmployeeID", mEmployeeID);
                bundle.putString("EmployeeName", mEmployeeName);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
            case R.id.etNhanVien:
                intent = new Intent(this, EmployeeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_NHANVIEN);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInit() {
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        this.getWindow().setLayout(width, ActionBar.LayoutParams.WRAP_CONTENT);

        tvTitle = findViewById(R.id.tvTitle);
        etNhanVien = findViewById(R.id.etNhanVien);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        etNhanVien.setOnClickListener(this);
    }

    @Override
    public void onConfigToolbar() {

    }

    @Override
    public void onLoadData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            String employeeID = bundle.getString("EmployeeID");
            String employeeName = bundle.getString("EmployeeName");
            tvTitle.setText(getString(R.string.dialog_capnhatnhanviengiaohang));
            etNhanVien.setText(employeeName);
            this.mEmployeeID = employeeID;
            this.mEmployeeName = employeeName;
        }
    }
}