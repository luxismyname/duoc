package com.bongmai.tiha.ui.danhmuc.employee.updatethongtinnhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.ui.danhmuc.employee.fragment.ThongTinNhanVienAdapter;
import com.bongmai.tiha.ui.danhmuc.employee.fragment.ThongTinNhanVienFragment;
import com.bongmai.tiha.ui.danhmuc.employee.themnhanvien.ThemNhanVienActivity;
import com.bongmai.tiha.utils.CommonUtils;
import com.google.android.material.tabs.TabLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class UpdateThongTinNhanVienActivity extends AppCompatActivity implements UpdateThongTinNhanVienContract.View{

    Toolbar toolbar;
    ThongTinNhanVienAdapter adapter;
    EmployeeInfo employeeInfo;
    ViewPager viewPager;
    TabLayout tabLayout;
    String employeeID = "";
    UpdateThongTinNhanVienPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_thong_tin_nhan_vien);
        onInit();
        onConfigToolbar();
        onLoadData();
    }




    private void onInit() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void onConfigToolbar() {

        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.updateKhachHang_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void onLoadData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            EmployeeInfo employeeInfo= (EmployeeInfo) bundle.getSerializable("EmployeeInfo");
            employeeID = employeeInfo == null ? "" : employeeInfo.getEmployeeID();
        }
        presenter = new UpdateThongTinNhanVienPresenter(this);
        presenter.getEmployee(employeeID);


    }

    private void setUpViewPageBar() {
        int tabCount = 1;
        // tabLayout = new TabLayout(this);
        adapter = new ThongTinNhanVienAdapter( getSupportFragmentManager(), UpdateThongTinNhanVienActivity.this, employeeInfo, tabCount );
        viewPager.setOffscreenPageLimit(tabCount);
        adapter.addFragment(new ThongTinNhanVienFragment().newInstance(employeeInfo), getResources().getString(R.string.fragment_ThongtinNhanvien_detail),0);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }



    @Override
    public void onGetEmployeeSuccess(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
        setUpViewPageBar();

    }

    @Override
    public void onGetEmployeeError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onDeleteEmployeeSuccess() {
//        presenter.DeleteEmployee(employeeInfo);
        Toast.makeText(this, "Xóa nhân viên thành công!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDeleteEmployeeError(String error) {
        CommonUtils.showMessageError(this, error);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_supplier, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:

                if (employeeInfo == null) break;
                Intent intent = new Intent(UpdateThongTinNhanVienActivity.this, ThemNhanVienActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putSerializable("EmployeeID", employeeInfo.getEmployeeID());
                intent.putExtras(bundle);
                startActivity(intent);

                break;


            case R.id.action_delete:
                if (employeeInfo == null) break;
//                if (baoCaoDichVuInfo.getTrangThai() == BaoCaoDichVuInfo.TRANGTHAI_HOANTHANH) {
//                    Toast.makeText(ServiceReportDetailActivity.this, "Service report đã " + getResources().getString(R.string.trangthai_servicereport_hoanthanh)
//                            + " bạn không được phép xóa. Vui lòng liên hệ Admin để được hỗ trợ!", Toast.LENGTH_LONG).show();
//                    break;
//                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(UpdateThongTinNhanVienActivity.this);
                builder.setTitle("XÓA NHÂN VIÊN")
                        .setMessage("Bạn có chắc muốn xóa nhân viên này?")
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                presenter.DeleteEmployee(employeeInfo);

                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                //navigationView.setCheckedItem(itemIdLast);

                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}