package com.bongmai.tiha.ui.chungtu.phieuxuat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BMConfigInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.nhapsoluongsanpham.NhapSoLuongSanPhamActivity;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;
import com.bongmai.tiha.ui.danhmuc.kho.list.KhoListActivity;
import com.bongmai.tiha.ui.danhmuc.product.allproduct.GetMultipleProductActivity;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.ui.khac.QuetMaVachActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.bongmai.tiha.utils.sunmiprinter.SunmiPrintHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhieuBanSiActivity extends AppCompatActivity implements BaseActivity, PhieuBanSiContract.View,
        View.OnClickListener {

    Toolbar toolbar;
    RecyclerView rvData;

    List<VattuxuatInfo> list;
    List<ProductInfo> listProduct;

    EditText
            etTimKiem,
            etKhachHang,
            etDDNguoiMua,
            etDienThoai,
            etDiaChi,
            etGhiChu,
            etKho,
            etNhanVienBanHang,
            etNhanVienGiaoHang,
            etNgay,
            etSoCT;
    TextView
            tvTongSL,
            tvTongTienHang,
            tvTongChietKhau,
            tvTongTienTT;
    ImageView btnBarcode;
    Button btnChonSanPham, btnLuu;
    ConstraintLayout ctlChuaCoSanPham;
//    FloatingActionButton fab;


    private static final int REQUEST_SANPHAM = 1;
    private static final int REQUEST_KHACHHANG = 2;
    private static final int REQUEST_NHANVIENBANHANG = 3;
    private static final int REQUEST_NHANVIENGIAOHANG = 4;
    private static final int REQUEST_MAVACH = 5;
    private static final int REQUEST_KHO = 6;
    private static final int REQUEST_SP = 7;
    private static final int ZXING_CAMERA_PERMISSION_REQUEST = 999;

    PhieuBanSiAdapter adapterData;

    PhieuXuatInfo phieuXuatCurrent;
    PhieuBanSiPresenter phieuXuatPresenter;
    String soPhieu;
    private static final String LOAI_NHANVIENBANHANG = "NVBH";
    private static final String LOAI_NHANVIENGIAOHANG = "NVGH";


    boolean isAdd = false, isEdit = false;
    int positionHandle = -1;
    List<VattuxuatInfo> listVtx;
    BMConfigInfo bmConfigInfo;
    BaseService BService;
    //Gan gia tri khi quet ma vach
    String maVach;
    Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieubansi);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);

        rvData = findViewById(R.id.rvData);

        etTimKiem = findViewById(R.id.etTimKiem);
        etKhachHang = findViewById(R.id.etKhachHang);
        etDDNguoiMua = findViewById(R.id.cboKhachHang);
        etDienThoai = findViewById(R.id.etDienThoai);
        etDiaChi = findViewById(R.id.etDiaChi);
        etGhiChu = findViewById(R.id.etGhiChu);
        etKho = findViewById(R.id.etKho);
        etNhanVienBanHang = findViewById(R.id.etNhanVienBanHang);
        etNhanVienGiaoHang = findViewById(R.id.etNhanVienGiaoHang);
        etNgay = findViewById(R.id.etNgay);
        etSoCT = findViewById(R.id.etSoCT);
        tvTongSL = (TextView) findViewById(R.id.tvTongSL);
        tvTongTienHang = findViewById(R.id.tvTongTienHang);
        tvTongChietKhau = findViewById(R.id.tvTongChietKhau);
        tvTongTienTT = findViewById(R.id.tvTongTienTT);

        btnBarcode = findViewById(R.id.btnBarcode);

        btnChonSanPham = findViewById(R.id.btnChonSanPham);
        btnLuu = findViewById(R.id.btnLuu);


        ctlChuaCoSanPham = findViewById(R.id.ctlChuaCoSanPham);

//        fab = findViewById(R.id.fabThem);

        etTimKiem.setOnClickListener(this);
        etDDNguoiMua.setOnClickListener(this);
        etNhanVienBanHang.setOnClickListener(this);
        etNhanVienGiaoHang.setOnClickListener(this);
        btnBarcode.setOnClickListener(this);
        btnChonSanPham.setOnClickListener(this);

        btnLuu.setOnClickListener(this);
        etKho.setOnClickListener(this);
//        fab.setOnClickListener(this);


        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());
        rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        etGhiChu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                return false;
            }

        });


    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    @Override
    public void onLoadData() {
        phieuXuatPresenter = new PhieuBanSiPresenter(this);
        BService = new BaseService(this);
        onBindData();

//        String soPhieu = "";
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            soPhieu = bundle.getString("SoCT");
            bundle.getSerializable("NhapSoLuongSanPhamInfo");
            bundle.getSerializable("VattuxuatInfo");
            bundle.getSerializable("ListVattuxuat");
            bundle.getSerializable("ProductInfo");
            bundle.getSerializable("listProduct");
        }
        if (TextUtils.isEmpty(soPhieu)) {
            AddNew();
        } else {
            showProgressDialog(true);
            Edit();
        }
    }

    private void AddNew() {
        isAdd = true;
        isEdit = false;
        phieuXuatCurrent = new PhieuXuatInfo();
        phieuXuatCurrent.setNgay(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy"));
        bmConfigInfo = BService.GetConfig();
        if (!TextUtils.isEmpty(bmConfigInfo.getKhachHangMacDinh())) {
            phieuXuatCurrent.setSupplier_ID(bmConfigInfo.getKhachHangMacDinh());
        }
        phieuXuatCurrent.setDDBH(PublicVariables.nguoiDungInfo.getEmployeeID());
//        etNhanVienBanHang.setText(PublicVariables.nguoiDungInfo.getEmployeeName());


        for (KhoInfo itemKho : PublicVariables.nguoiDungInfo.getListKho()) {
            if (itemKho.getMSK().equals(PublicVariables.nguoiDungInfo.getKhomacdinh())) {
                phieuXuatCurrent.setMSK(itemKho.getMSK());
                break;
            }
        }

        SetPhieuXuat(phieuXuatCurrent);
    }

    private void Edit() {
        isAdd = false;
        isEdit = true;
        phieuXuatPresenter.GetPhieuXuat(soPhieu);
        phieuXuatPresenter.GetListVattuXuat(soPhieu);
    }

    private void Save() {
        showProgressDialog(true);
        if (isAdd) {
            phieuXuatPresenter.InsertPhieuXuat(GetPhieuXuat(), adapterData.getListAllData());
        } else if (isEdit) {
            phieuXuatPresenter.UpdatePhieuXuat(GetPhieuXuat(), adapterData.getListAllData());
        }
    }

    private void SetPhieuXuat(PhieuXuatInfo phieuXuatInfo) {
        etSoCT.setText(phieuXuatInfo.getSoCt());
        etNgay.setText(AppUtils.formatDateToString(phieuXuatInfo.getNgay(), "dd/MM/yyyy"));
        etDienThoai.setText(phieuXuatInfo.getDTDD());
        etDiaChi.setText(phieuXuatInfo.getDiachi());
        etKhachHang.setText(phieuXuatInfo.getTenDDnguoimua());
        if (!TextUtils.isEmpty(phieuXuatInfo.getSupplier_ID()))
            phieuXuatPresenter.GetSupplier(phieuXuatInfo.getSupplier_ID());
        if (!TextUtils.isEmpty(phieuXuatInfo.getMSK()))
            phieuXuatPresenter.GetKho(phieuXuatInfo.getMSK());
        if (!TextUtils.isEmpty(phieuXuatInfo.getDDBH()))
            phieuXuatPresenter.GetEmployee(phieuXuatInfo.getDDBH(), LOAI_NHANVIENBANHANG);
        if (!TextUtils.isEmpty(phieuXuatInfo.getMSNguoigiao()))
            phieuXuatPresenter.GetEmployee(phieuXuatInfo.getMSNguoigiao(), LOAI_NHANVIENGIAOHANG);
        etGhiChu.setText(phieuXuatInfo.getGhichu());
        TinhThanhTien();

    }


    private PhieuXuatInfo GetPhieuXuat() {
        phieuXuatCurrent.setTenDDnguoimua(etKhachHang.getText().toString());
        phieuXuatCurrent.setDTDD(etDienThoai.getText().toString());
        phieuXuatCurrent.setDiachi(etDiaChi.getText().toString());
        phieuXuatCurrent.setGhichu(etGhiChu.getText().toString());
        return phieuXuatCurrent;
    }

    private void onBindData() {
        List<VattuxuatInfo> listData = new ArrayList<>();
        adapterData = new PhieuBanSiAdapter(PhieuBanSiActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {


            }
        });
        adapterData.setOnDataSetChangedListener(new BaseRecyclerViewEvent.OnDataSetChangedListener() {
            @Override
            public void onDataSetChanged() {
                if (adapterData.getListAllData().size() == 0) {
                    SetVisibleChuaCoSanPham(true);
                } else {
                    SetVisibleChuaCoSanPham(false);
                }
                TinhThanhTien();
            }
        });
        adapterData.setOnButtonClickListener(new BaseRecyclerViewEvent.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                positionHandle = position;
                VattuxuatInfo vtx = adapterData.getItem(position);
                switch (view.getId()) {
                    case R.id.btnPlus:
                        phieuXuatPresenter.KiemTraDuocXuatHang(phieuXuatCurrent.getSoCt(), phieuXuatCurrent.getMSK(), vtx.getProduct_ID(),
                                phieuXuatCurrent.getNgay(), vtx.getSL() + 1);
                        break;
                    case R.id.btnMinus:
                        vtx.setSL(vtx.getSL() - 1);
                        vtx.setSoLuongThuc(vtx.getSL());
                        vtx.setThanh_Tien(vtx.getSL() * vtx.getDongia() * (1 + vtx.getThue()));
                        if (vtx.getSL() == 0) {
                            adapterData.removeItem(position);
                        } else {
                            adapterData.updateItem(position, vtx);
                        }
                        break;
                    default:
                        break;
                }
            }
        });


        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                VattuxuatInfo vattuxuatInfo = new VattuxuatInfo();
                Intent intent = new Intent(PhieuBanSiActivity.this, ProductListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putSerializable("VattuxuatInfo", vattuxuatInfo);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_SP);
            }
        });

        adapterData.setOnClickListener((view, position) -> {
            VattuxuatInfo vtx = adapterData.getItem(position);
            NhapSoLuongSanPhamInfo itemNhapSoLuongSP = new NhapSoLuongSanPhamInfo(phieuXuatCurrent, vtx);
            Intent intent = new Intent(PhieuBanSiActivity.this, NhapSoLuongSanPhamActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle = new Bundle();
            bundle.putSerializable("NhapSoLuongSanPhamInfo", itemNhapSoLuongSP);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_SANPHAM);
        });
        adapterData.setOnLongClickListener((view, position) -> {
            ArrayList<String> listLongClick = new ArrayList<>();
            listLongClick.add("Cập nhật");
            listLongClick.add("Xóa");
            listLongClick.add("Xóa tất cả");

            final CharSequence[] items = listLongClick.toArray(new CharSequence[listLongClick.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(PhieuBanSiActivity.this);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, final int position) {
                    switch (position) {
                        case 0:
                            VattuxuatInfo vtx = adapterData.getItem(position);
                            NhapSoLuongSanPhamInfo itemNhapSoLuongSP = new NhapSoLuongSanPhamInfo(phieuXuatCurrent, vtx);
                            Intent intent = new Intent(PhieuBanSiActivity.this, NhapSoLuongSanPhamActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("NhapSoLuongSanPhamInfo", itemNhapSoLuongSP);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, REQUEST_SANPHAM);
                            break;
                        case 1:
                            final AlertDialog.Builder builderDelete = new AlertDialog.Builder(PhieuBanSiActivity.this);
                            builderDelete.setTitle("XÓA SẢN PHẨM")
                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                                    .setCancelable(false)
                                    .setPositiveButton(PhieuBanSiActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            adapterData.removeItem(position);
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton(PhieuBanSiActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            dialog.cancel();
                                        }
                                    });
                            final AlertDialog alert = builderDelete.create();
                            alert.show();
                            break;
                        case 2:
                            final AlertDialog.Builder builderDeleteAll = new AlertDialog.Builder(PhieuBanSiActivity.this);
                            builderDeleteAll.setTitle("XÓA SẢN PHẨM")
                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                                    .setCancelable(false)
                                    .setPositiveButton(PhieuBanSiActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            adapterData.setData(new ArrayList<>());
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton(PhieuBanSiActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDeleteAll = builderDeleteAll.create();
                            alertDeleteAll.show();
                            break;

                        default:
                            break;
                    }


                    dialog.dismiss();
                }
            });
            builder.show();
        });

        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();
    }

    private void TinhThanhTien() {
        double tongSoLuong = 0, tongTienHang = 0, tongChietKhau = 0, tongThanhTien = 0;
        for (VattuxuatInfo vtx : adapterData.getListAllData()) {
            tongSoLuong += vtx.getSL();
            tongTienHang += vtx.getSL() * vtx.getGiaban();
            tongChietKhau += vtx.getSL() * vtx.getGiatriDiscount();
            tongThanhTien += vtx.getThanh_Tien();
        }
        tvTongSL.setText(AppUtils.formatNumber("N0").format(tongSoLuong));
        tvTongTienHang.setText(AppUtils.formatNumber("N0").format(tongTienHang));
        tvTongChietKhau.setText(AppUtils.formatNumber("N0").format(tongChietKhau));
        tvTongTienTT.setText(AppUtils.formatNumber("N0").format(tongThanhTien));
    }

    private void SetVisibleChuaCoSanPham(boolean isShow) {
        ctlChuaCoSanPham.setVisibility(isShow ? View.VISIBLE : View.GONE);
        rvData.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onInsertPhieuXuatSuccess(PhieuXuatInfo itemResult) {
        showProgressDialog(false);
        SunmiPrintHelper.getInstance().printPhieuXuatPOS(this, "PhieuXuatDefaultReport", itemResult, adapterData.listAllData);
        Toast.makeText(PhieuBanSiActivity.this, "Lưu phiếu thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onInsertPhieuXuatError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onUpdatePhieuXuatSuccess(PhieuXuatInfo itemResult) {
        showProgressDialog(false);
        SunmiPrintHelper.getInstance().printPhieuXuatPOS(this, "PhieuXuatDefaultReport", itemResult, adapterData.listAllData);
        Toast.makeText(PhieuBanSiActivity.this, "Lưu phiếu thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onUpdatePhieuXuatError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onKiemTraDuocXuatHangSuccess() {
        VattuxuatInfo vtx = adapterData.getItem(positionHandle);
        vtx.setSL(vtx.getSL() + 1);
        vtx.setSoLuongThuc(vtx.getSL());
        vtx.setThanh_Tien(vtx.getSL() * vtx.getDongia() * (1 + vtx.getThue()));
        adapterData.updateItem(positionHandle, vtx);
    }

    @Override
    public void onKiemTraDuocXuatHangError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onGetSupplierSuccess(SupplierInfo itemResult) {
        phieuXuatCurrent.setSupplier_ID(itemResult.getSupplier_ID());
        etKhachHang.setText(itemResult.getCompany_Name());
        if (isAdd) {
            phieuXuatCurrent.setTennguoimua(itemResult.getCompany_Name());
            phieuXuatCurrent.setTenDDnguoimua(itemResult.getCompany_Name());
            phieuXuatCurrent.setDiachi(AppUtils.GetDiaChi(itemResult.getSo(), itemResult.getDuong(), itemResult.getKhuPho(), itemResult.getPhuong(), itemResult.getQuan(), itemResult.getTinh()));
            etDiaChi.setText(phieuXuatCurrent.getDiachi());
            phieuXuatCurrent.setDTDD(itemResult.getPhone());
            etDienThoai.setText(phieuXuatCurrent.getDTDD());
        }
    }

    @Override
    public void onGetSupplierError(String error) {
        Toast.makeText(this, "Lấy thông tin khách hàng lỗi. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetProductSuccess(ProductInfo itemResult) {
        int position = -1;
        List<VattuxuatInfo> listVattuxuat = adapterData.getListAllData();
        for (int i = 0; i < listVattuxuat.size(); i++) {
            if (listVattuxuat.get(i).getProduct_ID().equals(itemResult.getProduct_ID())) {
                position = i;
                break;
            }
        }
        if (position == -1) {
            Intent intent = new Intent(this, GetMultipleProductActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProduct_ID(itemResult.getProduct_ID());
            productInfo.setProduct_Name(itemResult.getProduct_Name());
            productInfo.setSoLuong(itemResult.getSoLuong());
            productInfo.setGiaBanLe(itemResult.getGiaBanLe());
            Bundle bundle = new Bundle();
            bundle.putSerializable("ProductInfo", productInfo);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_SP);


            Intent intent2 = new Intent(this, NhapSoLuongSanPhamActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo = new NhapSoLuongSanPhamInfo();
            nhapSoLuongSanPhamInfo.setSoCT(phieuXuatCurrent.getSoCt());
            nhapSoLuongSanPhamInfo.setNgay(phieuXuatCurrent.getNgay());
            nhapSoLuongSanPhamInfo.setSupplierID(phieuXuatCurrent.getSupplier_ID());
            nhapSoLuongSanPhamInfo.setMaKho(phieuXuatCurrent.getMSK());
            nhapSoLuongSanPhamInfo.setSL(1.0);
            nhapSoLuongSanPhamInfo.setSoLuongThuc(nhapSoLuongSanPhamInfo.getSL());
            nhapSoLuongSanPhamInfo.setProduct_ID(itemResult.getProduct_ID());

            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
            bundle2.putSerializable("ListVattuxuat", (Serializable) adapterData.getListAllData());
            intent.putExtras(bundle2);
            startActivityForResult(intent2, REQUEST_SANPHAM);
        } else {
            VattuxuatInfo vattuxuatInfo = listVattuxuat.get(position);
            phieuXuatPresenter.KiemTraDuocXuatHang(phieuXuatCurrent.getSoCt(), phieuXuatCurrent.getMSK(), vattuxuatInfo.getProduct_ID(),
                    phieuXuatCurrent.getNgay(), vattuxuatInfo.getSL() + 1);
            positionHandle = position;
        }
    }

    @Override
    public void onGetProductError(String error) {
        Intent intent = new Intent(this, NhapSoLuongSanPhamActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo = new NhapSoLuongSanPhamInfo();
        nhapSoLuongSanPhamInfo.setSoCT(phieuXuatCurrent.getSoCt());
        nhapSoLuongSanPhamInfo.setNgay(phieuXuatCurrent.getNgay());
        nhapSoLuongSanPhamInfo.setSupplierID(phieuXuatCurrent.getSupplier_ID());
        nhapSoLuongSanPhamInfo.setMaKho(phieuXuatCurrent.getMSK());
        nhapSoLuongSanPhamInfo.setSL(1.0);
        nhapSoLuongSanPhamInfo.setSoLuongThuc(nhapSoLuongSanPhamInfo.getSL());
        nhapSoLuongSanPhamInfo.setProduct_ID(maVach);

        Bundle bundle = new Bundle();
        bundle.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
        bundle.putSerializable("listVattuxuat", (Serializable) adapterData.getListAllData());
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_SANPHAM);
    }

    @Override
    public void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult) {
        showProgressDialog(false);

        phieuXuatCurrent = phieuXuatResult;
        SetPhieuXuat(phieuXuatResult);
    }

    @Override
    public void onGetPhieuXuatError(String error) {
        showProgressDialog(false);
        error = error.isEmpty() ? this.getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(this)) {
            error = this.getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getResources().getString(R.string.title_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onGetListVattuXuatSuccess(List<VattuxuatInfo> vattuxuatInfos) {
        list = vattuxuatInfos == null ? new ArrayList<>() : vattuxuatInfos;
        //adapterData.setData(vattuxuatInfos);
        //adapterData = new PhieuBanSiAdapter(this, vattuxuatInfos);
        adapterData.addAll(vattuxuatInfos);

//        list = vattuxuatInfos == null ? new ArrayList<>() : vattuxuatInfos;
//        adapterData.setData(vattuxuatInfos);
//        adapterData = new PhieuBanSiAdapter(this, vattuxuatInfos);

//        this.list = vattuxuatInfos;
//        adapterData.setData(vattuxuatInfos);
//        adapterData = new PhieuBanSiAdapter(this, vattuxuatInfos);
    }

    @Override
    public void onGetListVattuXuatError(String error) {

    }


    @Override
    public void onGetKhoSuccess(KhoInfo itemResult) {
        etKho.setText(itemResult.getTenkho());
    }

    @Override
    public void onGetKhoError(String error) {
        Toast.makeText(this, "Lấy thông tin kho lỗi. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetEmployeeSuccess(EmployeeInfo itemResult, String loai) {
        if (loai.equals(LOAI_NHANVIENBANHANG)) {
            etNhanVienBanHang.setText(itemResult.getEmployeeName());
        } else {
            etNhanVienGiaoHang.setText(itemResult.getEmployeeName());
        }
    }

    @Override
    public void onGetEmployeeError(String error, String loai) {
        Toast.makeText(this, "Lấy thông tin " + (loai.equals(LOAI_NHANVIENBANHANG) ? "nhân viên bán hàng" : "nhân viên giao hàng") + " lỗi. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo = new NhapSoLuongSanPhamInfo();
        VattuxuatInfo vattuxuatInfo = new VattuxuatInfo();
        ProductInfo productInfo = new ProductInfo();

        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        switch (view.getId()) {
            case R.id.etTimKiem:
            case R.id.btnChonSanPham:
                if (phieuXuatCurrent == null) return;
                if (phieuXuatCurrent.getSupplier_ID() == null || phieuXuatCurrent.getSupplier_ID().isEmpty()) {
                    CommonUtils.showMessageError(this, "Bạn chưa chọn khách hàng!");
                    return;
                }
//                intent = new Intent(this, GetMultipleProductActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                vattuxuatInfo.setProduct_ID(productInfo.getProduct_ID());
//                vattuxuatInfo.setProduct_Name(productInfo.getProduct_Name());
//                vattuxuatInfo.setSoLuongThuc(productInfo.getSoLuong());
//                vattuxuatInfo.setDongia(productInfo.getGiaBanLe());
//                vattuxuatInfo.setGiaban(productInfo.getGiaBanLe());
//                vattuxuatInfo.setSL(productInfo.getSoLuong());
//                vattuxuatInfo.setThanh_Tien(productInfo.getSoLuong() * productInfo.getGiaBanLe());
//                bundle.putSerializable("VattuxuatInfo", vattuxuatInfo);
//                intent.putExtras(bundle);
//                startActivityForResult(intent, REQUEST_SP);

                Intent intent2 = new Intent(this, ProductListActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nhapSoLuongSanPhamInfo.setSoCT(phieuXuatCurrent.getSoCt());
                nhapSoLuongSanPhamInfo.setNgay(phieuXuatCurrent.getNgay());
                nhapSoLuongSanPhamInfo.setSupplierID(phieuXuatCurrent.getSupplier_ID());
                nhapSoLuongSanPhamInfo.setMaKho(phieuXuatCurrent.getMSK());
                nhapSoLuongSanPhamInfo.setSL(1.0);
                nhapSoLuongSanPhamInfo.setSoLuongThuc(nhapSoLuongSanPhamInfo.getSL());
                bundle2.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
                bundle2.putSerializable("ListVattuxuat", (Serializable) adapterData.getListAllData());
                bundle2.putSerializable("PhieuXuatInfo", phieuXuatCurrent);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, REQUEST_SANPHAM);

                break;

            case R.id.cboKhachHang:
                intent = new Intent(this, SupplierListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("isCombobox", true);
                intent.putExtras(bundle1);
                startActivityForResult(intent, REQUEST_KHACHHANG);
                break;
            case R.id.etNhanVienBanHang:
                intent = new Intent(this, EmployeeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_NHANVIENBANHANG);
                break;
            case R.id.etNhanVienGiaoHang:
                intent = new Intent(this, EmployeeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_NHANVIENGIAOHANG);
                break;
            case R.id.btnBarcode:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION_REQUEST);
                } else {
                    intent = new Intent(this, QuetMaVachActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent, REQUEST_MAVACH);
                }
                break;
            case R.id.etKho:
                intent = new Intent(this, KhoListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_KHO);
                break;

            case R.id.btnLuu:
                Save();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        EmployeeInfo employeeInfo;
        VattuxuatInfo vattuxuatInfo;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_SANPHAM:
                    bundle = intent.getExtras();
                    NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo = (NhapSoLuongSanPhamInfo) bundle.getSerializable("NhapSoLuongSanPhamInfo");
                    vattuxuatInfo = new VattuxuatInfo().getVattuxuatByNhapSL(nhapSoLuongSanPhamInfo);

                    int position = -1;
                    List<VattuxuatInfo> listVattuxuat = adapterData.getListAllData();
                    for (int i = 0; i < listVattuxuat.size(); i++) {
                        if (listVattuxuat.get(i).getProduct_ID().equals(vattuxuatInfo.getProduct_ID())) {
                            position = i;
                            break;
                        }
                    }
                    if (position == -1) {
                        adapterData.addItem(vattuxuatInfo);
                    } else {
                        adapterData.removeItem(position);
                        adapterData.addItem(position, vattuxuatInfo);
                    }

                    btnChonSanPham.setVisibility(View.VISIBLE);
                    break;

                case REQUEST_SP:
                    bundle = intent.getExtras();
//                    ProductInfo productInfo = (ProductInfo) bundle.getSerializable("ProductInfo");
//                    vattuxuatInfo = new VattuxuatInfo().getVattuxuatByProduct(productInfo);
//                    int pos = -1;
//                    if(vattuxuatInfo == null) return;
//                    List<VattuxuatInfo> listVtx = adapterData.getListAllData();
//                    for(int i = 0; i < listVtx.size(); i++){
//                        if(listVtx.get(i).getProduct_ID().equals(vattuxuatInfo.getProduct_ID())){
//                            pos = i;
//                            break;
//                        }
//                    }
//                    if(pos == -1){
//                        adapterData.addItem(vattuxuatInfo);
//
//                    } else {
//                        adapterData.removeItem(pos);
//                        adapterData.addItem(pos, vattuxuatInfo);
//
//                    }
//                    btnChonSanPham.setVisibility(View.VISIBLE);
                    List<VattuxuatInfo> listVattuxuatChon = (List<VattuxuatInfo>) bundle.getSerializable("ListVattuxuat");
                    VattuxuatInfo vattuxuatChon;
                    VattuxuatInfo vattuxuat;
                    int pos = -1;
                    for (int i = 0; i < listVattuxuatChon.size(); i++) {
                        vattuxuatChon = listVattuxuatChon.get(i);
                        for (int j = 0; j < adapterData.getListAllData().size(); j++) {
                            vattuxuat = adapterData.getListAllData().get(j);
                            if (vattuxuatChon.getProduct_ID().equals(vattuxuat.getProduct_ID())) {
                                pos = j;
                                break;
                            }
                        }
                        //Neu khong tim thay thi them moi
                        //Nguoc lai thi cong them so luong
                        if (pos == -1) {
                            adapterData.addItem(vattuxuatChon);
                        } else {
                            vattuxuat = adapterData.getListAllData().get(pos);
                            vattuxuat.setSoLuongThuc(vattuxuat.getSoLuongThuc() + vattuxuatChon.getSoLuongThuc());
                            vattuxuat.setSL(vattuxuat.getSoLuongThuc());
                            vattuxuat.setThanh_Tien(vattuxuat.getSL() * vattuxuat.getDongia() * (1 + vattuxuat.getThue()));
                            adapterData.notifyDataSetChanged();
                        }
                    }

                    break;
                case REQUEST_KHACHHANG:
                    bundle = intent.getExtras();
                    SupplierInfo supplierInfo = (SupplierInfo) bundle.getSerializable("SupplierInfo");
                    if (supplierInfo == null) return;
                    phieuXuatCurrent.setSupplier_ID(supplierInfo.getSupplier_ID());
                    phieuXuatCurrent.setTennguoimua(supplierInfo.getCompany_Name());
                    phieuXuatCurrent.setTenDDnguoimua(supplierInfo.getCompany_Name());
                    phieuXuatCurrent.setDiachi(AppUtils.GetDiaChi(supplierInfo.getSo(), supplierInfo.getDuong(), supplierInfo.getKhuPho(),
                            supplierInfo.getPhuong(), supplierInfo.getQuan(), supplierInfo.getTinh()));
                    phieuXuatCurrent.setDTDD(supplierInfo.getPhone());

                    etKhachHang.setText(phieuXuatCurrent.getTenDDnguoimua());
                    etDiaChi.setText(phieuXuatCurrent.getDiachi());
                    etDienThoai.setText(phieuXuatCurrent.getDTDD());
                    break;
                case REQUEST_NHANVIENBANHANG:
                    bundle = intent.getExtras();
                    employeeInfo = (EmployeeInfo) bundle.getSerializable("EmployeeInfo");
                    if (employeeInfo == null) return;
                    phieuXuatCurrent.setDDBH(employeeInfo.getEmployeeID());
                    etNhanVienBanHang.setText(employeeInfo.getEmployeeName());
                    break;
                case REQUEST_NHANVIENGIAOHANG:
                    bundle = intent.getExtras();
                    employeeInfo = (EmployeeInfo) bundle.getSerializable("EmployeeInfo");
                    if (employeeInfo == null) return;
                    phieuXuatCurrent.setMSNguoigiao(employeeInfo.getEmployeeID());
                    etNhanVienGiaoHang.setText(employeeInfo.getEmployeeName());
                    break;
                case REQUEST_MAVACH:
                    bundle = intent.getExtras();
                    maVach = bundle.getString("Code");
                    if (TextUtils.isEmpty(maVach)) break;
                    phieuXuatPresenter.GetProduct(maVach);

                    break;
                case REQUEST_KHO:
                    bundle = intent.getExtras();
                    KhoInfo khoInfo = (KhoInfo) bundle.getSerializable("KhoInfo");
                    if (khoInfo == null) return;
                    phieuXuatCurrent.setMSK(khoInfo.getMSK());
                    etKho.setText(khoInfo.getTenkho());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    private void finishActivity() {
//        if (!isAdd && !isEdit) return;
        final AlertDialog.Builder builder = new AlertDialog.Builder(PhieuBanSiActivity.this);
        builder.setTitle("THOÁT")
                .setMessage("Bạn có chắc muốn thoát khỏi nghiệp vụ này?")
                .setCancelable(false)
                .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        finish();
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


}
