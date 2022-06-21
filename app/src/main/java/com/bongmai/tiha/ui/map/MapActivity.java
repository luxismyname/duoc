                    package com.bongmai.tiha.ui.map;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.RouteInfo;
import com.bongmai.tiha.data.entities.TblConfigInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.prefs.AppPreference;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.list.GiaoNhanHangActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.bongmai.tiha.utils.aes.AESUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapContract.View, BaseActivity, View.OnClickListener {
    private GoogleMap mMap;
    private Button btnFindPath, btnGoogleMap;
    private EditText etOrigin, etBatDau;
    private EditText etDestination;
    private TextView etSoCT, etSoCT1, etSolandi, etNVCL;
    private TextView etMaTrangThai;
    private EditText etKM;
    private TextView tvDistance;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    EmployeeGiaoHangInfo employeeGiaoHangInfo;

    AppPreference appPreference;

    ThongKeGiaoNhanHangInfo thongke;

    //latitude: Vi do, longitude: Kinh do
    private ImageButton btnClear, btnClear1, btnThem;
    boolean isNew = false;
    String endAddress = "", endLatitude = "", endLongitude = "";
    String startAddress = "", startLatitude = "", startLongitude = "";
    String origin = "";
    String destination = "";
    String originFinal = "", origin1Final ="",destinationFinal = "";
    String soct = "", soct1 = "",solandi = "", matrangthai = "", batdau ="", database ="";
    Toolbar toolbar;
    Dialog progressDialog;
    String linkGoogleMap;
    MapPresenter mapPresenter;
    boolean isView = false;
    String dcbd = "", dcgh = "";


    ConstraintLayout ctlOrigin, ctlBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        btnFindPath = findViewById(R.id.btnFindPath);
        btnGoogleMap = findViewById(R.id.btnGoogleMap);
        etOrigin = findViewById(R.id.etOrigin);
        etBatDau = findViewById(R.id.etBatDau);
        etDestination = findViewById(R.id.etDestination);
        btnClear1 = findViewById(R.id.btnClear1);

        btnThem = findViewById(R.id.btnAdd);
        toolbar = findViewById(R.id.toolbar);
        ctlOrigin = findViewById(R.id.ctlOrigin);
        ctlBatDau = findViewById(R.id.ctlBatdau);

        etSolandi = findViewById(R.id.etSoLanDi);
        etKM = findViewById(R.id.etKM);
        etSoCT = findViewById(R.id.etSoCT);
        etSoCT1 = findViewById(R.id.etSoCT1);
        etMaTrangThai = findViewById(R.id.etMaTrangThai);
        etNVCL = findViewById(R.id.etNVCL);
        tvDistance = findViewById(R.id.tvDistance);
        btnFindPath.setOnClickListener(this);
        btnGoogleMap.setOnClickListener(this);
//        btnClear.setOnClickListener(this);
        btnClear1.setOnClickListener(this);
        btnThem.setOnClickListener(this);



        etDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    btnThem.setVisibility(View.VISIBLE);
                else
                    btnThem.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    btnClear1.setVisibility(View.VISIBLE);
                else
                    btnClear1.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (database.toString()){
                    case "TIHALOC":
                        if(btnGoogleMap.getText().toString().equals("Hoàn thành")){

                            if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                                builder.setMessage("Hoàn thành đơn hàng?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                if(TextUtils.isEmpty(etSoCT.getText().toString())){
                                                    thongke.setSoCt(etSoCT1.getText().toString());
                                                } else {
                                                    thongke.setSoCt(etSoCT.getText().toString());
                                                }

                                                thongke.setMaTrangThaiCu("10");
                                                thongke.setMaTrangThai("11");
                                                thongke.setTenTrangThai("Đã giao");
                                                mapPresenter.UpdateTrangThaiPhieuXuat(thongke);
                                                mapPresenter.CapNhatNhanVienGiaoHang(etSoCT.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builder.create();
                                alert.show();
                                return;

                            } else {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                                builder.setMessage("Hoàn thành đơn hàng?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {

                                                if(TextUtils.isEmpty(etSoCT.getText().toString())){
                                                    thongke.setSoCt(etSoCT1.getText().toString());
                                                } else {
                                                    thongke.setSoCt(etSoCT.getText().toString());
                                                }
                                                thongke.setMaTrangThaiCu("10");
                                                thongke.setMaTrangThai("11");
                                                thongke.setTenTrangThai("Đã giao");
                                                mapPresenter.UpdateTrangThaiPhieuXuat(thongke);
                                                mapPresenter.CapNhatNhanVienGiaoHang(etSoCT.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builder.create();
                                alert.show();
                                return;
                            }

                        } else if(btnGoogleMap.getText().toString().equals("Quay về"))
                        {
                            ctlOrigin.setVisibility(View.VISIBLE);
                            ctlBatDau.setVisibility(View.GONE);

                            polylinePaths.clear();
                            String originBack = AppUtils.chuyenCoDauThanhKhongDau(etDestination.getText().toString());
                            String destinationBack = AppUtils.chuyenCoDauThanhKhongDau(etOrigin.getText().toString());
                            mapPresenter.DowloadDataMap(originFinal, destinationFinal);


                            btnGoogleMap.setText("Đến nơi");

                        } else if(btnGoogleMap.getText().toString().equals("Đến nơi")){

                            if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
                                final AlertDialog.Builder builder2 = new AlertDialog.Builder(MapActivity.this);
                                builder2.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert2 = builder2.create();
                                alert2.show();
                                return;
                            } else {
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(MapActivity.this);
                                builder1.setMessage("Bạn có muốn cập nhật số km quay về từ đơn?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                if(TextUtils.isEmpty(etBatDau.getText().toString())){

                                                    int sld = Integer.parseInt(etSolandi.getText().toString());
                                                    employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                                                    employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                                                    employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                            employeeGiaoHangInfo.setDiachigiaohang("Quay về kho từ đơn :" +etSoCT.getText().toString());
                                                    employeeGiaoHangInfo.setDiachigiaohang(etOrigin.getText().toString());
                                                    employeeGiaoHangInfo.setDiachibatdau(etDestination.getText().toString());
                                                    employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                                    employeeGiaoHangInfo.setSolandi(1);
//                                            if(sld == 1){
//                                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld == 2){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld >= 3){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            }


                                                    mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);
                                                    etBatDau.setText("");
                                                    finish();

                                                } else {
                                                    int sld = Integer.parseInt(etSolandi.getText().toString());
                                                    employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                                                    employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                                                    employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                            employeeGiaoHangInfo.setDiachigiaohang("Quay về kho từ đơn :" +etSoCT.getText().toString());

                                                    employeeGiaoHangInfo.setDiachigiaohang(etOrigin.getText().toString());
                                                    employeeGiaoHangInfo.setDiachibatdau(etDestination.getText().toString());
                                                    employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                                    employeeGiaoHangInfo.setSolandi(1);
//                                            if(sld == 1){
//                                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld == 2){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld >= 3){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            }
                                                    mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);

                                                    MapActivity.this.finish();

                                                }

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert1 = builder1.create();
                                alert1.show();
                                return;
                            }
                        }
                        break;
                    case "TIHADUOCQ10":
                        if(btnGoogleMap.getText().toString().equals("Hoàn thành")){

                            if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                                builder.setMessage("Hoàn thành đơn hàng?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                if(TextUtils.isEmpty(etSoCT.getText().toString())){
                                                    thongke.setSoCt(etSoCT1.getText().toString());
                                                } else {
                                                    thongke.setSoCt(etSoCT.getText().toString());
                                                }

                                                thongke.setMaTrangThaiCu("10");
                                                thongke.setMaTrangThai("11");
                                                thongke.setTenTrangThai("Đã giao");
                                                mapPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);
                                                mapPresenter.CapNhatNhanVienGiaoHangCuuLong(etSoCT.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builder.create();
                                alert.show();
                                return;

                            } else {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                                builder.setMessage("Hoàn thành đơn hàng?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {

                                                if(TextUtils.isEmpty(etSoCT.getText().toString())){
                                                    thongke.setSoCt(etSoCT1.getText().toString());
                                                } else {
                                                    thongke.setSoCt(etSoCT.getText().toString());
                                                }
                                                thongke.setMaTrangThaiCu("10");
                                                thongke.setMaTrangThai("11");
                                                thongke.setTenTrangThai("Đã giao");
                                                mapPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);
                                                mapPresenter.CapNhatNhanVienGiaoHangCuuLong(etSoCT.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builder.create();
                                alert.show();
                                return;
                            }

                        } else if(btnGoogleMap.getText().toString().equals("Quay về"))
                        {
                            ctlOrigin.setVisibility(View.VISIBLE);
                            ctlBatDau.setVisibility(View.GONE);

                            polylinePaths.clear();
                            String originBack = AppUtils.chuyenCoDauThanhKhongDau(etDestination.getText().toString());
                            String destinationBack = AppUtils.chuyenCoDauThanhKhongDau(etOrigin.getText().toString());
                            mapPresenter.DowloadDataMap(originFinal, destinationFinal);


                            btnGoogleMap.setText("Đến nơi");

                        } else if(btnGoogleMap.getText().toString().equals("Đến nơi")){

                            if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
                                final AlertDialog.Builder builder2 = new AlertDialog.Builder(MapActivity.this);
                                builder2.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert2 = builder2.create();
                                alert2.show();
                                return;
                            } else {
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(MapActivity.this);
                                builder1.setMessage("Bạn có muốn cập nhật số km quay về từ đơn?")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                if(TextUtils.isEmpty(etBatDau.getText().toString())){

                                                    int sld = Integer.parseInt(etSolandi.getText().toString());
                                                    employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                                                    employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                                                    employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                            employeeGiaoHangInfo.setDiachigiaohang("Quay về kho từ đơn :" +etSoCT.getText().toString());
                                                    employeeGiaoHangInfo.setDiachigiaohang(etOrigin.getText().toString());
                                                    employeeGiaoHangInfo.setDiachibatdau(etDestination.getText().toString());
                                                    employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                                    employeeGiaoHangInfo.setSolandi(1);
//                                            if(sld == 1){
//                                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld == 2){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld >= 3){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            }


                                                    mapPresenter.HoanThanhDonHangCuuLong(employeeGiaoHangInfo);
                                                    etBatDau.setText("");
                                                    finish();

                                                } else {
                                                    int sld = Integer.parseInt(etSolandi.getText().toString());
                                                    employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                                                    employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                                                    employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                            employeeGiaoHangInfo.setDiachigiaohang("Quay về kho từ đơn :" +etSoCT.getText().toString());

                                                    employeeGiaoHangInfo.setDiachigiaohang(etOrigin.getText().toString());

                                                    employeeGiaoHangInfo.setDiachibatdau(etDestination.getText().toString());
                                                    employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                                    employeeGiaoHangInfo.setSolandi(1);
//                                            if(sld == 1){
//                                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld == 2){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            } else if(sld >= 3){
//                                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                                employeeGiaoHangInfo.setSolandi(sld);
//                                            }
                                                    mapPresenter.HoanThanhDonHangCuuLong(employeeGiaoHangInfo);

                                                    MapActivity.this.finish();

                                                }

                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert1 = builder1.create();
                                alert1.show();
                                return;
                            }
                        }
                        break;
                    default:
                        break;
                }

            }

        });

//        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (btnGoogleMap.getText().toString().equals("Hoàn thành")) {
//
//
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
//                    builder.setMessage("Hoàn thành đơn hàng?")
//                            .setCancelable(false)
//                            .setTitle("Thông báo")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(final DialogInterface dialog, final int id) {
//                                    thongke.setSoCt(etSoCT.getText().toString());
//                                    thongke.setMaTrangThaiCu("10");
//                                    thongke.setMaTrangThai("11");
//                                    thongke.setTenTrangThai("Đã giao");
//                                    mapPresenter.UpdateTrangThaiPhieuXuat(thongke);
//                                    mapPresenter.CapNhatNhanVienGiaoHang(etSoCT.getText().toString(), PublicVariables.nguoiDungInfo.getEmployeeID());
//
//                                }
//                            })
//                            .setNegativeButton("", new DialogInterface.OnClickListener() {
//                                public void onClick(final DialogInterface dialog, final int id) {
//                                    dialog.cancel();
//                                }
//                            });
//                    final AlertDialog alert = builder.create();
//                    alert.show();
//                    return;
//
//
//
//                } else if(btnGoogleMap.getText().toString().equals("Quay về")){
//
//                    polylinePaths.clear();
//                    String originBack = AppUtils.chuyenCoDauThanhKhongDau(etDestination.getText().toString());
//                    String destinationBack = AppUtils.chuyenCoDauThanhKhongDau(etOrigin.getText().toString());
//                    mapPresenter.DowloadDataMap(originBack, destinationBack);
//                    btnGoogleMap.setText("Đến nơi");
//
//                } else if(btnGoogleMap.getText().toString().equals("Đến nơi")){
//
//
//                    if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
//                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(MapActivity.this);
//                        builder2.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                                .setCancelable(false)
//                                .setTitle("Thông báo")
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, final int id) {
//                                        dialog.cancel();
//                                    }
//                                })
//                                .setNegativeButton("", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, final int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                        final AlertDialog alert2 = builder2.create();
//                        alert2.show();
//                        return;
//                    } else {
//                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(MapActivity.this);
//                        builder1.setMessage("Bạn có muốn cập nhật số km quay về?")
//                                .setCancelable(false)
//                                .setTitle("Thông báo")
//                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, final int id) {
//                                        employeeGiaoHangInfo.setSoCT(etSoCT.getText().toString());
//                                        employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
//                                        employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                        employeeGiaoHangInfo.setDiachigiaohang("Quay về kho từ đơn :" +etSoCT.getText().toString());
//                                        employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                        mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);
//                                        finish();
//                                    }
//                                })
//                                .setNegativeButton("", new DialogInterface.OnClickListener() {
//                                    public void onClick(final DialogInterface dialog, final int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                        final AlertDialog alert1 = builder1.create();
//                        alert1.show();
//                        return;
//                    }
//               }
//            }
//
//        });
//
//

//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strOrigin = etDestination.getText().toString();
//                String soCT = etSoCT1.getText().toString();
//                Intent intent = new Intent(MapActivity.this, GiaoNhanHangActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("strAddress", strOrigin);
//                bundle.putString("SoCT", soCT);
//                int i = Integer.parseInt(etSolandi.getText().toString()) + 1;
//                bundle.putString("Solandi", String.valueOf(i));
//                intent.putExtras(bundle);
//                startActivity(intent);
//                btnGoogleMap.setText("Hoàn thành");
//                ctlBatDau.setVisibility(View.VISIBLE);
//                ctlOrigin.setVisibility(View.GONE);
//                finish();
//
//            }
//        });
////            btnGoogleMap.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    polylinePaths.clear();
////                    String originBack = AppUtils.chuyenCoDauThanhKhongDau(etDestination.getText().toString());
////                    String destinationBack = AppUtils.chuyenCoDauThanhKhongDau(etOrigin.getText().toString());
////                    mapPresenter.DowloadDataMap(originBack, destinationBack);
////
////                }
////            });
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.map_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLoadData() {
        appPreference = new AppPreference(this);
        employeeGiaoHangInfo = new EmployeeGiaoHangInfo();
        thongke = new ThongKeGiaoNhanHangInfo();
        mapPresenter = new MapPresenter(this);
        Bundle bundle = this.getIntent().getExtras();

        database = bundle.getString("database");
        soct = bundle.getString("SoCT");
        soct1 = bundle.getString("SoCT1");

        etSoCT1.setText(soct);
        matrangthai = bundle.getString("MaTrangThai");
        batdau = bundle.getString("strAddress");
        etBatDau.setText(batdau);
        solandi = bundle.getString("Solandi");
        etSolandi.setText(solandi);
        dcbd = bundle.getString("dcbd");
        dcgh = bundle.getString("dcgh");
        isView = bundle.getBoolean("isView");
        etOrigin.setText(dcbd);
        etDestination.setText(dcgh);

        try {
            endAddress = bundle.getString("DiaChi");
            endAddress = (endAddress == null) ? "" : endAddress;
        } catch (Exception e) {
        }
        try {
            endLatitude = bundle.getString("ViDo");
            endLatitude = (endLatitude == null) ? "" : endLatitude;
        } catch (Exception e) {
            endLatitude = "";

        }
        try {
            endLongitude = bundle.getString("KinhDo");
            endLongitude = (endLongitude == null) ? "" : endLongitude;
        } catch (Exception e) {
            endLongitude = "";
        }
            if(database.equals("TIHALOC")) {
               // mapPresenter.GetKho(PublicVariables.nguoiDungInfo.getKhomacdinh());
                mapPresenter.GetTblConfig(PublicVariables.nguoiDungInfo.getUserName());
                if (TextUtils.isEmpty(etBatDau.getText().toString())) {
                    mapPresenter.GetPhieuXuat(soct);

                } else {
                    mapPresenter.GetPhieuXuat(soct1);
                }
            } else if (database.equals("TIHADUOCQ10")){
                //mapPresenter.GetKhoCuuLong(PublicVariables.nguoiDungCuuLongInfo.getKhomacdinh());
                mapPresenter.GetTblConfigCuuLong(PublicVariables.nguoiDungInfo.getUserName());
                if (TextUtils.isEmpty(etBatDau.getText().toString())) {
                    mapPresenter.GetPhieuXuatCuuLong(soct);

                } else {
                    mapPresenter.GetPhieuXuatCuuLong(soct1);
                }
            }

    }


//    private EmployeeGiaoHangInfo HoanThanhDonHang(){
//
//        employeeGiaoHangInfo.setSoCT(etSoCT.getText().toString());
//        employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//        employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());
//        if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
//            final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                    .setCancelable(false)
//                    .setTitle("Thông báo")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    })
//                    .setNegativeButton("", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    });
//            final AlertDialog alert = builder.create();
//            alert.show();
//        } else {
//            employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//        }
//        employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//        employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
//        return employeeGiaoHangInfo;
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFindPath:
                sendRequest();
                break;
//            case R.id.btnGoogleMap:
////                if (!CreateAddress()) return;
////                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destinationFinal + "");
////                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
////                mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
////                if (mapIntent.resolveActivity(getPackageManager()) != null) {
////                    startActivity(mapIntent);
////                }
//
//                break;
//            case R.id.btnClear:
//                etDestination.setText("");
//                break;
            case R.id.btnClear1:
                etOrigin.setText("");
                break;
            case R.id.btnAdd:
                String strOrigin = etDestination.getText().toString();
                String soCT = etSoCT1.getText().toString();
                Intent intent = new Intent(MapActivity.this, GiaoNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("strAddress", strOrigin);
                bundle.putString("SoCT", soCT);
                int i = Integer.parseInt(etSolandi.getText().toString()) + 1;
                bundle.putString("Solandi", String.valueOf(i));
                intent.putExtras(bundle);
                startActivity(intent);
                btnGoogleMap.setText("Hoàn thành");
                ctlBatDau.setVisibility(View.VISIBLE);
                ctlOrigin.setVisibility(View.GONE);
                finish();
            default:
                break;
        }

    }

    @Override
    public void onGetKhoSuccess(KhoInfo khoInfo) {
                setDiaChiXem(khoInfo);
                sendRequest();

    }

    @Override
    public void onGetKhoError(String error) {
        setDiaChiXem(null);
        sendRequest();
    }

    @Override
    public void onGetKhoCuuLongSuccess(KhoInfo khoInfo) {
        setDiaChiXem(khoInfo);
        sendRequest();

    }

    @Override
    public void onGetKhoCuuLongError(String error) {
        setDiaChiXem(null);
        sendRequest();
    }

    private void setDiaChiXem(KhoInfo khoInfo) {
        //Neu kho co toa do thi lay dia chi theo toa do
        //Nguoc lai lay theo dia chi trong kho
        //Neu kho ko co toa do va dia chi thi lay dia chi hien tai
        //region Origin
        if (khoInfo != null) {
            startAddress = khoInfo.getDiachi() == null ? "" : khoInfo.getDiachi();
            startLatitude = khoInfo.getX() == null ? "" : khoInfo.getX();
            startLongitude = khoInfo.getY() == null ? "" : khoInfo.getY();
        }
        if (!startLatitude.isEmpty() && !startLatitude.equals("0") && !startLongitude.isEmpty() && !startLongitude.equals("0")) {
            origin = startLatitude + "," + startLongitude;
            try {
                startAddress = getAddressByLocation(Double.parseDouble(startLatitude), Double.parseDouble(startLongitude));
            } catch (NumberFormatException e) {
            }
        } else {
                origin = startAddress;
        }
        //Nếu kho mặc định không có địa chỉ thì lấy vị trí hiện tại làm điểm bắt đầu
        if (origin == null || origin.isEmpty()) {
            double latitude = 0, longitude = 0;
            GPSTracker tracker = new GPSTracker(this);
            if (!tracker.canGetLocation()) {
                tracker.showSettingsAlert();
            } else {
                latitude = tracker.getLatitude();
                longitude = tracker.getLongitude();
            }
            startAddress = getAddressByLocation(latitude, longitude);
            origin = startAddress;
        }
        etOrigin.setText(startAddress);
        if (!startAddress.isEmpty()) {
            btnClear1.setVisibility(View.VISIBLE);
        }
        //endregion

        //Neu khach hang co toa do thi lay dia chi theo toa do
        //Nguoc lai lay theo dia chi khach hang
        //region Destination
        if ((!endLatitude.isEmpty() && !endLatitude.equals("0.0") && !endLatitude.equals("0"))
                && (!endLongitude.isEmpty() && !endLongitude.equals("0.0") && !endLongitude.equals("0"))) {
            destination = endLatitude + "," + endLongitude;
            try {
                endAddress = getAddressByLocation(Double.parseDouble(endLatitude), Double.parseDouble(endLongitude));
            } catch (NumberFormatException e) {

            }
        } else {
            destination = endAddress;
        }
        etDestination.setText(endAddress);
//        if (!endAddress.isEmpty()) {
//            btnClear.setVisibility(View.VISIBLE);
//        }
        //endregion
    }

    private String getAddressByLocation(double latitude, double longitude) {
        String So = "", Duong = "", Phuong = "", Quan = "", Tinh = "", QuocGia = "";
        List<Address> addresses = new ArrayList<>();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
        }
        if (addresses != null && addresses.size() > 0) {
            So = addresses.get(0).getSubThoroughfare() == null ? "" : addresses.get(0).getSubThoroughfare();
            Duong = addresses.get(0).getThoroughfare() == null ? "" : addresses.get(0).getThoroughfare();
            Phuong = addresses.get(0).getSubLocality() == null ? "" : addresses.get(0).getSubLocality();
            Quan = addresses.get(0).getSubAdminArea() == null ? "" : addresses.get(0).getSubAdminArea();
            Tinh = addresses.get(0).getAdminArea() == null ? "" : addresses.get(0).getAdminArea();
            QuocGia = addresses.get(0).getCountryName() == null ? "" : addresses.get(0).getCountryName();
            return So + " " + Duong + " " + Phuong + " " + Quan + " " + Tinh + " " + QuocGia;
        }
        return "";
    }

    private boolean CreateAddress() {


            String originA = etOrigin.getText().toString();
            if (!startLatitude.isEmpty() && !startLongitude.isEmpty() && startAddress.equals(originA)) {
                originA = startLatitude + "," + startLongitude;
            }
            String originB = etBatDau.getText().toString();
            if (!startLatitude.isEmpty() && !startLongitude.isEmpty() && startAddress.equals(originB)) {
                originB = startLatitude + "," + startLongitude;
            }

            String destinationB = etDestination.getText().toString();
            if (!endLatitude.isEmpty() && !endLongitude.isEmpty() && endAddress.equals(destinationB)) {
                originA = startLatitude + "," + startLongitude;
            }
            if (originA.isEmpty()) {
//            Toast.makeText(this, "Vui lòng nhập địa chỉ đi", Toast.LENGTH_SHORT).show();
                return false;

            }


            if (destinationB.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập địa chỉ đến", Toast.LENGTH_SHORT).show();
                return false;

            }


                originA = AppUtils.chuyenCoDauThanhKhongDau(originA);
                originFinal = originA;
                originB = AppUtils.chuyenCoDauThanhKhongDau(originB);
                origin1Final = originB;
                destinationB = AppUtils.chuyenCoDauThanhKhongDau(destinationB);
                destinationFinal = destinationB;


            return true;

    }

    private void sendRequest() {

        if(isView){

                etOrigin.setText(dcbd);
                etDestination.setText(dcgh);
                mapPresenter.DowloadDataMap(AppUtils.chuyenCoDauThanhKhongDau(dcbd), AppUtils.chuyenCoDauThanhKhongDau(dcgh));


        } else {

            if (!CreateAddress()) return;

            if (TextUtils.isEmpty(etBatDau.getText().toString())) {
                ctlOrigin.setVisibility(View.VISIBLE);
                ctlBatDau.setVisibility(View.GONE);
                mapPresenter.DowloadDataMap(originFinal, destinationFinal);
            } else {
                ctlBatDau.setVisibility(View.VISIBLE);
                ctlOrigin.setVisibility(View.GONE);
                mapPresenter.DowloadDataMap(origin1Final, destinationFinal);
            }


        }

    }
//
//    private void sendRequestTurnback() {
//        if (!CreateAddress()) return;
//        mapPresenter.DowloadDataMapTurnBack(destinationFinal, originFinal);
//    }

    @Override
    public void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult) {
        Toast.makeText(this, "Đơn hàng đã giao thành công", Toast.LENGTH_SHORT).show();

        if(TextUtils.isEmpty(etBatDau.getText().toString())){
            final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có muốn cập nhật km đơn hàng "+etSoCT.getText().toString()+" không?").
                    setCancelable(false)
                    .setTitle("Thông báo")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            if(etKM.getText().toString().equals("0") || TextUtils.isEmpty(etKM.getText().toString())){
//                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
//                                builder1.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                                        .setCancelable(false)
//                                        .setTitle("Thông báo")
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(final DialogInterface dialog, final int id) {
//                                                dialog.cancel();
//                                            }
//                                        })
//                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
//                                            public void onClick(final DialogInterface dialog, final int id) {
//                                                dialog.cancel();
//                                            }
//                                        });
//                                final AlertDialog alert1 = builder1.create();
//                                alert1.show();
//                                return;
//                            } else
//                                {
                            int sld = Integer.parseInt(etSolandi.getText().toString());
                            employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                            employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                            employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
                            employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());

                            if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                employeeGiaoHangInfo.setDiachibatdau(etOrigin.getText().toString());
                            } else {
                                employeeGiaoHangInfo.setDiachibatdau(etBatDau.getText().toString());
                            }

                            if(sld == 1){
                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                employeeGiaoHangInfo.setSolandi(sld);
                            } else if(sld >= 2){
                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.2;
                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                employeeGiaoHangInfo.setSolandi(sld);
                            }
//                            else if(sld >= 3){
//                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                employeeGiaoHangInfo.setSolandi(sld);
//                            }
                            mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);

//                            }
                        }
                    })
                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });

            final AlertDialog alert = builder.create();
            alert.show();
            return;
        } else {
             final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn cập nhật km đơn hàng "+etSoCT.getText().toString()+" không?").
                setCancelable(false)
                .setTitle("Thông báo")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(etKM.getText().toString().equals("0") || TextUtils.isEmpty(etKM.getText().toString())){
                            final AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                            builder1.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                                    .setCancelable(false)
                                    .setTitle("Thông báo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            dialog.cancel();
                                        }
                                    });
                            final AlertDialog alert1 = builder1.create();
                            alert1.show();
                            return;
                        } else {

                            int sld = Integer.parseInt(etSolandi.getText().toString());
                            employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                            employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                            employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
                            employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());
                            if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                employeeGiaoHangInfo.setDiachibatdau(etOrigin.getText().toString());
                            } else {
                                employeeGiaoHangInfo.setDiachibatdau(etBatDau.getText().toString());
                            }
                            if(sld == 1){
                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                employeeGiaoHangInfo.setSolandi(sld);
                            } else if(sld == 2){
                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                employeeGiaoHangInfo.setSolandi(sld);
                            } else if(sld >= 3){
                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                employeeGiaoHangInfo.setSolandi(sld);
                            }
                            mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);


//                                employeeGiaoHangInfo.setSoCT(etSoCT.getText().toString());
//                                employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
//                                employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());
//                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);

                        }
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
        return;
        }





//        if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
//            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getApplicationContext());
//            builder.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                    .setCancelable(false)
//                    .setTitle("Thông báo")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    })
//                    .setNegativeButton("", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    });
//            final AlertDialog alert2 = builder2.create();
//            alert2.show();
//        } else {
//            employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//        }
//        employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));

//        mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);


    }

    @Override
    public void onUpdateTrangThaiPhieuXuatError(String error) {
        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateTrangThaiPhieuXuatCuuLongSuccess(ThongKeGiaoNhanHangInfo itemResult) {
        if(TextUtils.isEmpty(etBatDau.getText().toString())){
            final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có muốn cập nhật km đơn hàng "+etSoCT.getText().toString()+" không?").
                    setCancelable(false)
                    .setTitle("Thông báo")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            if(etKM.getText().toString().equals("0") || TextUtils.isEmpty(etKM.getText().toString())){
//                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
//                                builder1.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                                        .setCancelable(false)
//                                        .setTitle("Thông báo")
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(final DialogInterface dialog, final int id) {
//                                                dialog.cancel();
//                                            }
//                                        })
//                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
//                                            public void onClick(final DialogInterface dialog, final int id) {
//                                                dialog.cancel();
//                                            }
//                                        });
//                                final AlertDialog alert1 = builder1.create();
//                                alert1.show();
//                                return;
//                            } else
//                                {
                            int sld = Integer.parseInt(etSolandi.getText().toString());
                            employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                            employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                            employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
                            employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());

                            if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                employeeGiaoHangInfo.setDiachibatdau(etOrigin.getText().toString());
                            } else {
                                employeeGiaoHangInfo.setDiachibatdau(etBatDau.getText().toString());
                            }

                            if(sld == 1){
                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                employeeGiaoHangInfo.setSolandi(sld);
                            } else if(sld >= 2){
                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.2;
                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                employeeGiaoHangInfo.setSolandi(sld);
                            }
//                            else if(sld >= 3){
//                                double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
//                                double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
//                                employeeGiaoHangInfo.setQuangDuong(kmbonus);
//                                employeeGiaoHangInfo.setSolandi(sld);
//                            }
                            mapPresenter.HoanThanhDonHangCuuLong(employeeGiaoHangInfo);

//                            }
                        }
                    })
                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });

            final AlertDialog alert = builder.create();
            alert.show();
            return;
        } else {
            final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có muốn cập nhật km đơn hàng "+etSoCT.getText().toString()+" không?").
                    setCancelable(false)
                    .setTitle("Thông báo")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(etKM.getText().toString().equals("0") || TextUtils.isEmpty(etKM.getText().toString())){
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
                                builder1.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                                        .setCancelable(false)
                                        .setTitle("Thông báo")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert1 = builder1.create();
                                alert1.show();
                                return;
                            } else {

                                int sld = Integer.parseInt(etSolandi.getText().toString());
                                employeeGiaoHangInfo.setSoCT(etSoCT1.getText().toString());
                                employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
                                employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
                                employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());
                                if(TextUtils.isEmpty(etBatDau.getText().toString())){
                                    employeeGiaoHangInfo.setDiachibatdau(etOrigin.getText().toString());
                                } else {
                                    employeeGiaoHangInfo.setDiachibatdau(etBatDau.getText().toString());
                                }
                                if(sld == 1){
                                    employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
                                    employeeGiaoHangInfo.setSolandi(sld);
                                } else if(sld == 2){
                                    double bonus = Double.parseDouble(etKM.getText().toString()) * 0.3;
                                    double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                    employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                    employeeGiaoHangInfo.setSolandi(sld);
                                } else if(sld >= 3){
                                    double bonus = Double.parseDouble(etKM.getText().toString()) * 0.4;
                                    double kmbonus = Double.parseDouble(etKM.getText().toString()) + bonus;
                                    employeeGiaoHangInfo.setQuangDuong(kmbonus);
                                    employeeGiaoHangInfo.setSolandi(sld);
                                }
                                mapPresenter.HoanThanhDonHangCuuLong(employeeGiaoHangInfo);


//                                employeeGiaoHangInfo.setSoCT(etSoCT.getText().toString());
//                                employeeGiaoHangInfo.setEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
//                                employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//                                employeeGiaoHangInfo.setDiachigiaohang(etDestination.getText().toString());
//                                employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//                                mapPresenter.HoanThanhDonHang(employeeGiaoHangInfo);

                            }
                        }
                    })
                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });

            final AlertDialog alert = builder.create();
            alert.show();
            return;
        }
    }

    @Override
    public void onUpdateTrangThaiPhieuXuatCuuLongError(String error) {
        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult) {

        etSoCT.setText(phieuXuatResult.getSoCt());
        etSoCT.setText(phieuXuatResult.getSoCt());
        etMaTrangThai.setText(phieuXuatResult.getTrangThai());
    }

    @Override
    public void onGetPhieuXuatError(String error) {
//        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult) {
        etSoCT.setText(phieuXuatResult.getSoCt());
        etSoCT.setText(phieuXuatResult.getSoCt());
        etMaTrangThai.setText(phieuXuatResult.getTrangThai());
    }

    @Override
    public void onGetPhieuXuatCuuLongError(String error) {

    }

    @Override
    public void onHoanThanhDonHangSuccess(EmployeeGiaoHangInfo itemResult) {
         itemResult = employeeGiaoHangInfo;
         itemResult.setSoCT(etSoCT.getText().toString());
         itemResult.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
         itemResult.setDiachigiaohang(endAddress);
         if(TextUtils.isEmpty(etBatDau.getText().toString())){
             itemResult.setDiachibatdau(startAddress);
         } else {
             itemResult.setDiachibatdau(etOrigin.getText().toString());
         }
         itemResult.setSolandi(Integer.parseInt(etSolandi.getText().toString()));
         if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
             final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
             builder.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                     .setCancelable(false)
                     .setTitle("Thông báo")
                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         public void onClick(final DialogInterface dialog, final int id) {
                             dialog.cancel();
                         }
                     })
                     .setNegativeButton("", new DialogInterface.OnClickListener() {
                         public void onClick(final DialogInterface dialog, final int id) {
                             dialog.cancel();
                         }
                     });
             final AlertDialog alert = builder.create();
             alert.show();
            return;

         } else {
             itemResult.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
         }
        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();

         btnGoogleMap.setText("Quay về");




//        finish();
    }



    @Override
    public void onHoanThanhDonHangError(String error) {
        CommonUtils.showMessageError(MapActivity.this, error);

//        employeeGiaoHangInfo.setSoCT(etSoCT.getText().toString());
//        employeeGiaoHangInfo.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
//        employeeGiaoHangInfo.setDiachigiaohang(endAddress);
//        if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
//            final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
//                    .setCancelable(false)
//                    .setTitle("Thông báo")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    })
//                    .setNegativeButton("", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    });
//            final AlertDialog alert = builder.create();
//            alert.show();
//            return;
//
//        } else {
//            employeeGiaoHangInfo.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
//        }
//        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
//
//        btnGoogleMap.setText("Quay về");

    }

    @Override
    public void onHoanThanhDonHangCuuLongSuccess(EmployeeGiaoHangInfo itemResult) {
        itemResult = employeeGiaoHangInfo;
        itemResult.setSoCT(etSoCT.getText().toString());
        itemResult.setNgayGiaoHang(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
        itemResult.setDiachigiaohang(endAddress);
        if(TextUtils.isEmpty(etBatDau.getText().toString())){
            itemResult.setDiachibatdau(startAddress);
        } else {
            itemResult.setDiachibatdau(etOrigin.getText().toString());
        }
        itemResult.setSolandi(Integer.parseInt(etSolandi.getText().toString()));
        if(TextUtils.isEmpty(etKM.getText().toString()) && etKM.getText().toString().equals("0")){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Đơn hàng chưa có km, vui lòng chờ một xíu để hoàn thành!")
                    .setCancelable(false)
                    .setTitle("Thông báo")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
            return;

        } else {
            itemResult.setQuangDuong(Double.parseDouble(etKM.getText().toString()));
        }
        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();

        btnGoogleMap.setText("Quay về");

    }

    @Override
    public void onHoanThanhDonHangCuuLongError(String error) {
        CommonUtils.showMessageError(MapActivity.this, error);
    }

    @Override
    public void onDirectionFinderStart() {
        showProgressBar(true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDowloadDataMapSuccess(List<RouteInfo> routes, String link) {
        linkGoogleMap = link;
        showProgressBar(false);
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (RouteInfo route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.getStartLocation(), 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.getDuration().text);
            tvDistance.setText(route.getDistance().text);
            String strDistance = tvDistance.getText().toString();
//            strDistance.replaceAll();
            etKM.setText(strDistance.replace("km", ""));

            //Xóa polyline cũ trước khi tạo polyline mới
            mMap.clear();
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_start_location))
                    .title(route.getStartAddress())
                    .position(route.getStartLocation())));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_end_location))
                    .title(route.getEndAddress())
                    .position(route.getEndLocation())));
            int colorPolyLine = Color.parseColor("#5F98F3");

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(colorPolyLine).
                    width(20);


            for (int i = 0; i < route.getPoints().size(); i++)
                polylineOptions.add(route.getPoints().get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));

        }
    }

    @Override
    public void onDowloadDataMapError(String error) {
        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangSuccess() {
        Toast.makeText(MapActivity.this, "Cập nhật nhân viên giao hàng thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangError(String error) {
        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangCuuLongSuccess() {
        Toast.makeText(MapActivity.this, "Cập nhật nhân viên giao hàng thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangCuuLongError(String error) {
        Toast.makeText(MapActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetTblConfigSuccess(TblConfigInfo itemResult) {
        mapPresenter.GetKho(itemResult.getKhomacdinh());
    }

    @Override
    public void onGetTblConfigError(String error) {

    }

    @Override
    public void onGetTblConfigCuuLongSuccess(TblConfigInfo itemResult) {
        mapPresenter.GetKhoCuuLong(itemResult.getKhomacdinh());
    }

    @Override
    public void onGetTblConfigCuuLongError(String error) {

    }

    //Vi tri bat dau
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        GPSTracker tracker = new GPSTracker(MapActivity.this);
        if (!tracker.canGetLocation()) {
            tracker.showSettingsAlert();
        }
        LatLng locationCty = new LatLng(tracker.getLatitude(), tracker.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCty, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("CTY")
                .position(locationCty)));
        if (this.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && this.checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && this.checkCallingOrSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    public void showProgressBar(boolean isShow) {
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

    public void onBackPressed() {
        finish();
    }

}
