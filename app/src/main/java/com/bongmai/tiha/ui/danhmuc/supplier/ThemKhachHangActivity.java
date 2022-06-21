package com.bongmai.tiha.ui.danhmuc.supplier;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.ChiNhanhInfo;
import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.HuongCotGiaInfo;
import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.danhmuc.diachi.DiaChiListActivity;
import com.bongmai.tiha.ui.danhmuc.employee.EmployeeListActivity;
import com.bongmai.tiha.ui.danhmuc.nhomkhachhang.NhomKhachHangListActivity;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NumberTextWatcher;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class ThemKhachHangActivity extends AppCompatActivity implements BaseActivity, ThemKhachHangContract.View, View.OnClickListener {
    private static final String TAG = ThemKhachHangActivity.class.getName();
    Toolbar toolbar;
    AutoCompleteTextView etTimKiem;
    EditText
            etMaKhachHang,
            etTenKhachHang,
            etPhone,
            etDTDD,
            etSDT1,
            etSDT2,
            etDiaChiSo,
            etDuong,
            etPhuongXa,
            etQuanHuyen,
            etTinhThanhPho,
            etSanPhamSuDung,
            etNhomKhachHang,
            etDacDiemKhachHang,
            etDDBH,
            etNoCu,
            etViDo,
            etKinhDo,
            etBangGia;
    Button btnMore;
    ConstraintLayout ctlMoreInfo, ctlMore;
    Spinner
            spinnerChiNhanh,
            spinnerHuongCotGia;
    ThemKhachHangPresenter themKhachHangPresenter;
    SupplierInfo supplierCurrent;
    Boolean isAdd, isEdit;
    Boolean
//            isCuocGoi,
//            isLuuVaBan,
            isCapNhatKhachHang;
    DiaChiInfo diaChiTinhInfo, diaChiQuanInfo, diaChiPhuongInfo, diaChiDuongInfo;
    private static final int REQUEST_DIACHI = 1;
    private static final int REQUEST_SANPHAMSUDUNG = 2;
    private static final int REQUEST_NHOMKHACHHANG = 3;
    private static final int REQUEST_DAIDIENBANHANG = 4;
    private static final int REQUEST_INSERTPHIEUXUAT = 5;
    private static final int REQUEST_UPDATEKHACHHANG = 6;
    List<HuongCotGiaInfo> listHuongCotGia;
    List<BangGiaGroupInfo> listBangGia;
    //    DanhSachCuocGoiInfo danhSachCuocGoiInfo;
    Dialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themkhachhang);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);

        etTimKiem = findViewById(R.id.etTimKiem);
        etMaKhachHang = findViewById(R.id.etMaKhachHang);
        etTenKhachHang = findViewById(R.id.etTenKhachHang);
        etPhone = findViewById(R.id.etPhone);
        etDTDD = findViewById(R.id.etDTDD);
        etSDT1 = findViewById(R.id.etSDT1);
        etSDT2 = findViewById(R.id.etSDT2);
        etDiaChiSo = findViewById(R.id.etDiaChiSo);
        etDuong = findViewById(R.id.etDuong);
        etPhuongXa = findViewById(R.id.etPhuongXa);
        etQuanHuyen = findViewById(R.id.etQuanHuyen);
        etTinhThanhPho = findViewById(R.id.etTinhThanhPho);
        etSanPhamSuDung = findViewById(R.id.etSanPhamSuDung);
        etNhomKhachHang = findViewById(R.id.etNhomKhachHang);
        etDacDiemKhachHang = findViewById(R.id.etDacDiemKhachHang);
        etDDBH = findViewById(R.id.etDDBH);
        etNoCu = findViewById(R.id.etNoCu);
        etViDo = findViewById(R.id.etViDo);
        etKinhDo = findViewById(R.id.etKinhDo);

        ctlMoreInfo = findViewById(R.id.ctlMoreInfo);
        ctlMore = findViewById(R.id.ctlMore);

        btnMore = findViewById(R.id.btnMore);

        spinnerChiNhanh = findViewById(R.id.spinnerChiNhanh);
        spinnerHuongCotGia = findViewById(R.id.spinnerHuongCotGia);
        etBangGia = findViewById(R.id.etBangGia);

        etNoCu.addTextChangedListener(new NumberTextWatcher(etNoCu, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etDuong.setOnClickListener(this);
        etPhuongXa.setOnClickListener(this);
        etQuanHuyen.setOnClickListener(this);
        etTinhThanhPho.setOnClickListener(this);
        etSanPhamSuDung.setOnClickListener(this);
        etDDBH.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        etNhomKhachHang.setOnClickListener(this);
        etBangGia.setOnClickListener(this);

        ctlMoreInfo.setVisibility(View.GONE);

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.themkhachhang_tieudeform);
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
        themKhachHangPresenter = new ThemKhachHangPresenter(this);
        themKhachHangPresenter.GetListBangGia();
        LoadChiNhanh();
        LoadHuongCotGia();

        String supplierID = null;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            supplierID = bundle.getString("SupplierID");
            isCapNhatKhachHang = bundle.getBoolean("isCapNhatKhachHang");
        }
        if (supplierID == null || supplierID.isEmpty())//Edit
        {
            AddNew();
        } else//Add new
        {
            Edit();
        }
    }


    private void LoadBangGia() {
        ArrayList<String> listBangGiaStr = new ArrayList<>();
        for (BangGiaGroupInfo itemBangGia : listBangGia) {
            listBangGiaStr.add(itemBangGia.getTenBangGia());
        }
        final CharSequence[] items = listBangGiaStr.toArray(new CharSequence[listBangGia.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("CHỌN BẢNG GIÁ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                BangGiaGroupInfo itemBangGia = listBangGia.get(position);
                supplierCurrent.setMaSoBangGia(itemBangGia.getMaSoBangGia());
                etBangGia.setText(itemBangGia.getTenBangGia());
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void LoadChiNhanh() {
        ArrayList<String> listChiNhanh = new ArrayList<>();
        for (ChiNhanhInfo itemChiNhanh : PublicVariables.nguoiDungInfo.getListChiNhanh()) {
            listChiNhanh.add(itemChiNhanh.getTenChiNhanh());
        }
        ArrayAdapter<String> adapterChiNhanh = new ArrayAdapter<>(this, R.layout.item_spinner, listChiNhanh);
        spinnerChiNhanh.setAdapter(adapterChiNhanh);
    }

    private void LoadHuongCotGia() {
        listHuongCotGia = HuongCotGiaInfo.GetListHuongCotGia();
        ArrayList<String> listCotGia = new ArrayList<>();
        for (HuongCotGiaInfo itemCotGia : listHuongCotGia) {
            listCotGia.add(itemCotGia.getTenCotGia());
        }
        ArrayAdapter<String> adapterCotGia = new ArrayAdapter<>(this, R.layout.item_spinner, listCotGia);
        spinnerHuongCotGia.setAdapter(adapterCotGia);
    }

    private void SetSupplier(SupplierInfo supplierInfo) {

        etMaKhachHang.setText(supplierInfo.getSupplier_ID());
        etTenKhachHang.setText(supplierInfo.getCompany_Name());
        etPhone.setText(supplierInfo.getPhone());
        etDTDD.setText(supplierInfo.getDTDD());
        etSDT1.setText(supplierInfo.getSoDT1());
        etSDT2.setText(supplierInfo.getSoDT2());
        etDiaChiSo.setText(supplierInfo.getSo());
        etDuong.setText(supplierInfo.getDuong());
        etPhuongXa.setText(supplierInfo.getPhuong());
        etQuanHuyen.setText(supplierInfo.getQuan());
        etTinhThanhPho.setText(supplierInfo.getTinh());
        etSanPhamSuDung.setText(supplierInfo.getSPSuDung());
        etNhomKhachHang.setText(supplierInfo.getTenDDBH());
        etDacDiemKhachHang.setText(supplierInfo.getDacdiem());
        etDDBH.setText(supplierInfo.getTenDDBH());
        etNoCu.setText(AppUtils.formatNumber("N0").format(supplierInfo.getDunoMo()));
        etViDo.setText(supplierInfo.getX());
        etKinhDo.setText(supplierInfo.getY());
//        spinnerChiNhanh
//        spinnerHuongCotGia
//        spinnerBangGia
    }

    private SupplierInfo GetSupplier() {
        supplierCurrent.setSupplier_ID(etMaKhachHang.getText().toString());
        supplierCurrent.setCompany_Name(etTenKhachHang.getText().toString());
        supplierCurrent.setPhone(etPhone.getText().toString());
        supplierCurrent.setDTDD(etDTDD.getText().toString());
        supplierCurrent.setSoDT1(etSDT1.getText().toString());
        supplierCurrent.setSoDT2(etSDT2.getText().toString());
        supplierCurrent.setSo(etDiaChiSo.getText().toString());
        supplierCurrent.setDuong(etDuong.getText().toString());
        supplierCurrent.setPhuong(etPhuongXa.getText().toString());
        supplierCurrent.setQuan(etQuanHuyen.getText().toString());
        supplierCurrent.setTinh(etTinhThanhPho.getText().toString());
        supplierCurrent.setSPSuDung(etSanPhamSuDung.getText().toString());
        supplierCurrent.setDacdiem(etDacDiemKhachHang.getText().toString());
        double noCu = 0;
        try {
            noCu = Double.parseDouble(etNoCu.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        supplierCurrent.setDunoMo(noCu);
        supplierCurrent.setX(etViDo.getText().toString());
        supplierCurrent.setY(etKinhDo.getText().toString());
        supplierCurrent.setCotgia(listHuongCotGia.get(spinnerHuongCotGia.getSelectedItemPosition()).getMaCotGia());
        supplierCurrent.setChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanh().get(spinnerChiNhanh.getSelectedItemPosition()).getChiNhanhID());

        return supplierCurrent;
    }

    private void AddNew() {
        isAdd = true;
        isEdit = false;
        supplierCurrent = new SupplierInfo();
//        supplierCurrent.setPhone(danhSachCuocGoiInfo.getPhone());
        SetSupplier(supplierCurrent);
    }

    private void Edit() {
        isAdd = false;
        isEdit = true;
        etMaKhachHang.setEnabled(false);
        //SetSupplier(supplierCurrent);
      //  GetSupplier();
        themKhachHangPresenter.GetSupplier(this.getIntent().getExtras().getString("SupplierID"));


    }

    private void Save() {
        showProgressDialog(true);
        SupplierInfo supplierSave = GetSupplier();
        if (isAdd)
            themKhachHangPresenter.InsertSupplier(supplierSave);
        else
            themKhachHangPresenter.UpdateSupplier(supplierSave);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle bundle;
        switch (view.getId()) {
            case R.id.etDuong:
                StartActivityDiaChi(DiaChiInfo.DUONG);
                break;
            case R.id.etPhuongXa:
                StartActivityDiaChi(DiaChiInfo.PHUONG);
                break;
            case R.id.etQuanHuyen:
                StartActivityDiaChi(DiaChiInfo.QUAN);
                break;
            case R.id.etTinhThanhPho:
                StartActivityDiaChi(DiaChiInfo.TINH);
                break;
            case R.id.etSanPhamSuDung:
                intent = new Intent(ThemKhachHangActivity.this, ProductListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.putBoolean("isCombobox", true);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_SANPHAMSUDUNG);
                break;
            case R.id.etDDBH:
                intent = new Intent(ThemKhachHangActivity.this, EmployeeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_DAIDIENBANHANG);
                break;
            case R.id.etNhomKhachHang:
                intent = new Intent(ThemKhachHangActivity.this, NhomKhachHangListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_NHOMKHACHHANG);
                break;
            case R.id.etBangGia:
                LoadBangGia();
                break;
            case R.id.btnMore:
                ctlMoreInfo.setVisibility(View.VISIBLE);
                ctlMore.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void StartActivityDiaChi(String loaiDiaChi) {
        Intent intent;
        Bundle bundle;
        intent = new Intent(ThemKhachHangActivity.this, DiaChiListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        bundle = new Bundle();
        bundle.putString("LoaiDiaChi", loaiDiaChi);
        switch (loaiDiaChi) {
            case DiaChiInfo.DUONG:
//                bundle.putInt("ParentID", diaChiPhuongInfo == null? 0 : diaChiPhuongInfo.getID());
            case DiaChiInfo.QUAN:
                bundle.putInt("ParentID", diaChiTinhInfo == null ? 0 : diaChiTinhInfo.getID());
                break;
            case DiaChiInfo.PHUONG:
                bundle.putInt("ParentID", diaChiQuanInfo == null ? 0 : diaChiQuanInfo.getID());
                break;
            case DiaChiInfo.TINH:
                bundle.putInt("ParentID", 0);
                break;
            default:
                break;
        }

        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_DIACHI);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_DIACHI:
                    bundle = intent.getExtras();
                    DiaChiInfo diaChiInfo = (DiaChiInfo) bundle.getSerializable("DiaChiInfo");
                    String loaiDiaChi = bundle.getString("LoaiDiaChi");
                    switch (loaiDiaChi) {
                        case DiaChiInfo.DUONG:
                            diaChiDuongInfo = diaChiInfo;
                            supplierCurrent.setDuong(diaChiInfo.getTenDiaChi());
                            etDuong.setText(supplierCurrent.getDuong());
                            break;
                        case DiaChiInfo.PHUONG:
                            diaChiPhuongInfo = diaChiInfo;
                            supplierCurrent.setPhuong(diaChiInfo.getTenDiaChi());
                            etPhuongXa.setText(supplierCurrent.getPhuong());
                            break;
                        case DiaChiInfo.QUAN:
                            diaChiQuanInfo = diaChiInfo;
                            supplierCurrent.setQuan(diaChiInfo.getTenDiaChi());
                            etQuanHuyen.setText(supplierCurrent.getQuan());
                            break;
                        case DiaChiInfo.TINH:
                            diaChiTinhInfo = diaChiInfo;
                            supplierCurrent.setTinh(diaChiInfo.getTenDiaChi());
                            etTinhThanhPho.setText(supplierCurrent.getTinh());
                            break;
                        default:
                            break;
                    }
                    break;
                case REQUEST_SANPHAMSUDUNG:
                    bundle = intent.getExtras();
                    ProductInfo productInfo = (ProductInfo) bundle.getSerializable("ProductInfo");
                    supplierCurrent.setMaSP(productInfo.getProduct_ID());
                    supplierCurrent.setSPSuDung(productInfo.getProduct_Name());
                    etSanPhamSuDung.setText(productInfo.getProduct_Name());
                    break;
                case REQUEST_NHOMKHACHHANG:
                    bundle = intent.getExtras();
                    PhuongThucTTInfo phuongThucTTInfo = (PhuongThucTTInfo) bundle.getSerializable("PhuongThucTTInfo");
                    supplierCurrent.setPTTT(phuongThucTTInfo.getMSPTTT());
                    etNhomKhachHang.setText(phuongThucTTInfo.getPTTT());
                    break;
                case REQUEST_DAIDIENBANHANG:
                    bundle = intent.getExtras();
                    EmployeeInfo employeeInfo = (EmployeeInfo) bundle.getSerializable("EmployeeInfo");
                    supplierCurrent.setDDBH(employeeInfo.getEmployeeID());
                    etDDBH.setText(employeeInfo.getEmployeeName());
                    break;
                case REQUEST_INSERTPHIEUXUAT:
                    intent = new Intent();
                    setResult(RESULT_OK, intent);
                    ThemKhachHangActivity.this.finish();
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onGetSupplierSuccess(SupplierInfo itemResult) {
        supplierCurrent = itemResult;
        SetSupplier(itemResult);
    }

    @Override
    public void onGetSupplierError(String error) {

    }

    @Override
    public void onInsertSupplierSuccess(SupplierInfo itemResult) {
        showProgressDialog(false);
        supplierCurrent.setSupplier_ID(itemResult.getSupplier_ID());
        etMaKhachHang.setText(itemResult.getSupplier_ID());
        Toast.makeText(this, "Lưu khách hàng thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SupplierInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();


//        if (isCuocGoi) {
//            if (danhSachCuocGoiInfo == null || danhSachCuocGoiInfo.getID() == 0) {
//                danhSachCuocGoiInfo = new DanhSachCuocGoiInfo();
//                danhSachCuocGoiInfo.setPhone(itemResult.getPhone());
//                danhSachCuocGoiInfo.setLine("0");
//                danhSachCuocGoiInfo.setDiaChi(AppUtils.GetDiaChi(itemResult.getSo(), itemResult.getDuong(), itemResult.getKhuPho(),
//                        itemResult.getPhuong(), itemResult.getQuan(), itemResult.getTinh()));
//                danhSachCuocGoiInfo.setTenKhachHang(itemResult.getCompany_Name());
//                danhSachCuocGoiInfo.setSanPham(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSanPhamMua(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSupplierID(itemResult.getSupplier_ID());
//                danhSachCuocGoiInfo.setMaSP(itemResult.getMaSP());
//                danhSachCuocGoiInfo.setSoID(0);
//                danhSachCuocGoiInfo.setDacDiemKH(itemResult.getDacdiem());
//                danhSachCuocGoiInfo.setNhomKH(itemResult.getPTTT());
////                themKhachHangPresenter.InsertCuocGoi(danhSachCuocGoiInfo);
//            } else {
//                danhSachCuocGoiInfo.setDiaChi(AppUtils.GetDiaChi(itemResult.getSo(), itemResult.getDuong(), itemResult.getKhuPho(),
//                        itemResult.getPhuong(), itemResult.getQuan(), itemResult.getTinh()));
//                danhSachCuocGoiInfo.setTenKhachHang(itemResult.getCompany_Name());
//                danhSachCuocGoiInfo.setSanPham(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSanPhamMua(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSupplierID(itemResult.getSupplier_ID());
//                danhSachCuocGoiInfo.setMaSP(itemResult.getMaSP());
//                danhSachCuocGoiInfo.setDacDiemKH(itemResult.getDacdiem());
//                danhSachCuocGoiInfo.setNhomKH(itemResult.getPTTT());
////                themKhachHangPresenter.UpdateCuocGoi(danhSachCuocGoiInfo);
//            }
//        }

    }

    @Override
    public void onInsertSupplierError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onUpdateSupplierSuccess(SupplierInfo itemResult) {
        showProgressDialog(false);
        supplierCurrent.setSupplier_ID(itemResult.getSupplier_ID());
//        etMaKhachHang.setText(itemResult.getSupplier_ID());
//        etTenKhachHang.setText(itemResult.getCompany_Name());
//        etDTDD.setText(itemResult.getDTDD());
//        etSDT1.setText(itemResult.getSoDT1());
//        etSDT2.setText(itemResult.getSoDT2());
//        etDiaChiSo.setText(itemResult.getSo());
//        etTinhThanhPho.setText(itemResult.getTinh());
//        etQuanHuyen.setText(itemResult.getQuan());
//        etPhuongXa.setText(itemResult.getPhuong());
//        etDuong.setText(itemResult.getPhuong());
//        etSanPhamSuDung.setText(itemResult.getSPSuDung());
//        etNhomKhachHang.setText(itemResult.getNhomKhachHang());
        Toast.makeText(this, "Lưu khách hàng thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SupplierInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
//        if (isCuocGoi) {
//            if (danhSachCuocGoiInfo == null || danhSachCuocGoiInfo.getID() == 0) {
//                danhSachCuocGoiInfo = new DanhSachCuocGoiInfo();
//                danhSachCuocGoiInfo.setPhone(itemResult.getPhone());
//                danhSachCuocGoiInfo.setLine("0");
//                danhSachCuocGoiInfo.setDiaChi(AppUtils.GetDiaChi(itemResult.getSo(), itemResult.getDuong(), itemResult.getKhuPho(),
//                        itemResult.getPhuong(), itemResult.getQuan(), itemResult.getTinh()));
//                danhSachCuocGoiInfo.setTenKhachHang(itemResult.getCompany_Name());
//                danhSachCuocGoiInfo.setSanPham(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSanPhamMua(itemResult.getSPSuDung());
//                danhSachCuocGoiInfo.setSupplierID(itemResult.getSupplier_ID());
//                danhSachCuocGoiInfo.setMaSP(itemResult.getMaSP());
//                danhSachCuocGoiInfo.setSoID(0);
//                danhSachCuocGoiInfo.setDacDiemKH(itemResult.getDacdiem());
//                danhSachCuocGoiInfo.setNhomKH(itemResult.getPTTT());
////                themKhachHangPresenter.InsertCuocGoi(danhSachCuocGoiInfo);
//            } else {
////                themKhachHangPresenter.UpdateCuocGoi(danhSachCuocGoiInfo);
//            }
//        }
//        if (isLuuVaBan) {
//            Intent intent = new Intent(this, PhieuBanSiActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("DanhSachCuocGoiInfo", danhSachCuocGoiInfo);
//            intent.putExtras(bundle);
//            startActivityForResult(intent, REQUEST_INSERTPHIEUXUAT);
//        } else {
//            Intent intent = new Intent();
//            setResult(RESULT_OK, intent);
//            finish();
//        }
    }

    @Override
    public void onUpdateSupplierError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onGetListBangGiaSuccess(List<BangGiaGroupInfo> listResult) {
        listBangGia = listResult;
    }

    @Override
    public void onGetListBangGiaError(String error) {
        Toast.makeText(ThemKhachHangActivity.this, "Lấy danh sách bảng giá thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgressDialog() {
        showProgressDialog(false);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themkhachhang, menu);

//        MenuItem item = menu.findItem(R.id.action_luuvaban);
//        if (isCuocGoi != null && isCuocGoi && !isCapNhatKhachHang
//        if (!isCapNhatKhachHang)
//            item.setVisible(true);
//        else
//            item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:

//                isLuuVaBan = false;
                Save();
                break;
            case R.id.action_edit:
                Edit();

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
