package com.bongmai.tiha;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.BaoCaoListFragment;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.QuangDuongNhanVienGiaoHangActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.luongtheonvgh.ThongKeLuongNhanVienGiaoHangTheoQuangDuongActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke.ThongKeQuangDuongNhanVienGiaoHangActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.ThongKeGiaoNhanHangFragment;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiActivity;
import com.bongmai.tiha.ui.chungtu.phieuxuat.list.PhieuBanSiListFragment;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;

import com.bongmai.tiha.ui.danhmuc.kho.list.KhoListActivity;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.ui.danhmuc.soduvattu.list.ListSoDuVatTuActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.ui.hethong.config.ConfigActivity;
import com.bongmai.tiha.ui.hethong.main.MainContract;
import com.bongmai.tiha.ui.hethong.main.MainPresenter;
import com.bongmai.tiha.ui.hethong.userinfo.ProfileActivity;
import com.bongmai.tiha.ui.tonghop.tongquan.TongQuanFragment;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.LetterTileProvider;
import com.bongmai.tiha.utils.PublicVariables;
import com.bongmai.tiha.utils.sunmiprinter.SunmiPrintHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, MainContract.View {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FloatingActionButton fabThemMoiPhieuXuat;
    ImageView imageAvatar;
    TextView tvTenNhanVien;
    TextView tvEmail;

    //Danh sach quyen bat buoc phai duoc cap phep
    String[] permissionsRequired = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,

    };
    private final int REQUEST_MULTIPLE_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[3]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[4]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[5]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[6]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissionsRequired[7]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionsRequired, REQUEST_MULTIPLE_PERMISSIONS);
            //return;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fabThemMoiPhieuXuat = findViewById(R.id.fabThemMoiPhieuXuat);
        View headerLayout = navigationView.getHeaderView(0);
        imageAvatar = headerLayout.findViewById(R.id.imageAvatar);
        tvTenNhanVien = headerLayout.findViewById(R.id.tvTenNhanVien);
        tvEmail = headerLayout.findViewById(R.id.tvEmail);


        fabThemMoiPhieuXuat.setOnClickListener(this);
        imageAvatar.setOnClickListener(this);

        BaseService service = new BaseService(this);
        PublicVariables.bmConfigInfo = service.GetConfig();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        PublicVariables.NgayLamViec = simpleDateFormat.format(c.getTime());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        setDefaulSelectedNavigationview();

        //Create sunmi service printer
        SunmiPrintHelper.getInstance().initSunmiPrinterService(this);

        setEmployeeInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Kiem tra tat ca quyen can cap
        boolean allgranted = false;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                allgranted = true;
            } else {
                allgranted = false;
                break;
            }
        }
        switch (requestCode) {
            case REQUEST_MULTIPLE_PERMISSIONS:
                if (!allgranted && (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[2])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[3])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[4])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[5])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[6])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, permissionsRequired[7]))) {
                    showMessagePermissions();
                } else {
                    // onLoadData();
                }
                break;
            default:
                break;
        }
    }

    //tat floatingactionbutton
    public void showFloatingActionButton() {
        fabThemMoiPhieuXuat.show();
    };

    public void hideFloatingActionButton() {
        fabThemMoiPhieuXuat.hide();
    };
    //Hien thi thong bao ly do tai sao phai can cap nhung quyen nay cho ung dung
    private void showMessagePermissions() {
        new AlertDialog.Builder(this)
                .setTitle("QUYỀN ỨNG DỤNG")
                .setMessage("Ứng dụng cần được cấp nhiều quyền hơn.")
                .setPositiveButton("CẤP QUYỀN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(MainActivity.this, permissionsRequired, REQUEST_MULTIPLE_PERMISSIONS);
                    }
                })
                .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
//                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void setEmployeeInfo() {
        MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.GetHinhAnhByEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
        tvTenNhanVien.setText(PublicVariables.nguoiDungInfo.getEmployeeName());
        tvEmail.setText(PublicVariables.nguoiDungInfo.getDTNoio());
    }

    private void setDefaulSelectedNavigationview() {
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_tongquan));
        navigationView.setCheckedItem(R.id.nav_tongquan);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
        return false;
    }

    MenuItem menuItemLast;
    int itemIdLast;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        try {
            Fragment fragment = null;
            Class fragmentClass = null;
            Intent intent;
            if (menuItemLast != null) {
                itemIdLast = menuItemLast.getItemId();
            }
            drawerLayout.closeDrawer(GravityCompat.START);

                switch (menuItem.getItemId()) {
                    case R.id.nav_tongquan:
                        fragmentClass = TongQuanFragment.class;
                        break;
                    case R.id.nav_banhang:
                        fragmentClass = PhieuBanSiListFragment.class;
                        break;

                    case R.id.nav_giaonhan:
                        fragmentClass = ThongKeGiaoNhanHangFragment.class;
                        break;
                    case R.id.nav_giaohangreport:
                        intent = new Intent(MainActivity.this, QuangDuongNhanVienGiaoHangActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        break;

                    case R.id.nav_giaohangreportchitiet:
                        intent = new Intent(MainActivity.this, QuangDuongNhanVienGiaoHangChiTietActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;
                    case R.id.nav_thongkenvgh:
                        intent = new Intent(MainActivity.this, ThongKeQuangDuongNhanVienGiaoHangActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;

                    case R.id.nav_luongnhanvien:
                        intent = new Intent(MainActivity.this, ThongKeLuongNhanVienGiaoHangTheoQuangDuongActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;

                    case R.id.nav_khachhang:
                        intent = new Intent(MainActivity.this, SupplierListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;
//                    break;
                    case R.id.nav_nhanvien:
                        intent = new Intent(MainActivity.this, EmployeeListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;
//                    break;
                    case R.id.nav_hanghoa:
                        intent = new Intent(MainActivity.this, ProductListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;
                    case R.id.nav_vattu:
                        intent = new Intent(MainActivity.this, ListSoDuVatTuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;
//                case R.id.nav_loaihang:
//                    intent = new Intent(MainActivity.this, LoaiHangListActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    navigationView.setCheckedItem(itemIdLast);
//                    break;
                    case R.id.nav_baocao:
                        fragmentClass = BaoCaoListFragment.class;
                        break;
                    //            case R.id.nav_logout:
                    //                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    //                builder.setTitle("ĐĂNG XUẤT")
                    //                        .setMessage("Bạn có chắc muốn đăng xuất ứng dụng?")
                    //                        .setCancelable(false)
                    //                        .setPositiveButton("ĐĂNG XUẤT", new DialogInterface.OnClickListener() {
                    //                            public void onClick(final DialogInterface dialog, final int id) {
                    //                                PublicVariables.ClearData();
                    //                                AppPreference appPreference = new AppPreference(MainActivity.this);
                    //                                appPreference.setLogin(false);
                    //                                appPreference.setTenDangNhap("");
                    //                                appPreference.setMatKhau("");
                    //                                appPreference.setNguoiDungID("");
                    //                                Intent intent = getBaseContext().getPackageManager().
                    //                                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                    //                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //                                startActivity(intent);
                    //                                MainActivity.this.finish();
                    //                            }
                    //                        })
                    //                        .setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
                    //                            public void onClick(final DialogInterface dialog, final int id) {
                    //                                dialog.cancel();
                    //                                navigationView.setCheckedItem(itemIdLast);
                    //                            }
                    //                        });
                    //                final AlertDialog alert = builder.create();
                    //                alert.show();
                    //                break;

//                case R.id.nav_trangthai:
//                    intent = new Intent(MainActivity.this, TrangThaiActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    navigationView.setCheckedItem(itemIdLast);
//                    return false;
                    case R.id.nav_cauhinh:
                        intent = new Intent(MainActivity.this, KhoListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        navigationView.setCheckedItem(itemIdLast);
                        return false;


                    default:
                        //fragmentClass = FragmentDanhSachGiaoGas.class;
                        break;
                }


            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                //e.printStackTrace();
            }
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
                setTitle(menuItem.getTitle());
            }
            menuItemLast = menuItem;
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("THOÁT ỨNG DỤNG")
                    .setMessage("Bạn có chắc muốn thoát ứng dụng?")
                    .setCancelable(false)
                    .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            moveTaskToBack(true);
                            finishAffinity();
                        }
                    })
                    .setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fabThemMoiPhieuXuat:
                intent = new Intent(this, PhieuBanSiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.imageAvatar:
                intent = new Intent(this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onGetHinhAnhByEmployeeIDSuccess(String stringResult) {
        imageAvatar.setImageBitmap(AppUtils.formatStringToBitMap(stringResult));
    }

    @Override
    public void onGetHinhAnhByEmployeeIDError(String error) {
        LetterTileProvider mLetterTileProvider = new LetterTileProvider(this);
        try {
            imageAvatar.setImageBitmap(mLetterTileProvider.getCircularLetterTile(PublicVariables.nguoiDungInfo.getEmployeeName()));
        } catch (Exception e) {
            Log.d("SetEmployeeInfo", e.getMessage());
        }
    }
}
