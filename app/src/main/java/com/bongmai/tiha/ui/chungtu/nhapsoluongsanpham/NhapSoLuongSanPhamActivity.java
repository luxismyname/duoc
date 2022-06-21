package com.bongmai.tiha.ui.chungtu.nhapsoluongsanpham;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.NumberTextWatcher;

import java.util.ArrayList;

public class NhapSoLuongSanPhamActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener, NhapSoLuongSanPhamContract.View {
    Toolbar toolbar;
    EditText
            etMaSanPham,
            etTenSanPham,
            etTonKho,
            etDonGiaThuc,
            etSoLuongThuc,
            etChietKhau,
            etThanhTien,
            etGhiChu;
    ImageView
            btnMinus,
            btnPlus;
    Spinner spinnerDonViTinh;
    NhapSoLuongSanPhamInfo itemNhapSoLuongSP;
    ProductInfo productInfo;
    NhapSoLuongSanPhamPresenter sanPhamPresenter;
    boolean isNew = false;
    boolean isAdd = false;
    ArrayList<String> listDonViTinh;
    Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhapsoluongsanpham);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        etMaSanPham = findViewById(R.id.etMaSanPham);
        etTenSanPham = findViewById(R.id.etTenSanPham);
        etTonKho = findViewById(R.id.etTonKho);
        etDonGiaThuc = findViewById(R.id.etDonGiaThuc);
        etSoLuongThuc = findViewById(R.id.etSoLuongThuc);
        etChietKhau = findViewById(R.id.etChietKhau);
        etThanhTien = findViewById(R.id.etThanhTien);
        etGhiChu = findViewById(R.id.etGhiChu);

        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

        spinnerDonViTinh = (Spinner) findViewById(R.id.spinnerDonViTinh);

        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        etSoLuongThuc.addTextChangedListener(new NumberTextWatcher(etSoLuongThuc, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double soLuongThuc = 0;
                try {
                    soLuongThuc = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }
                itemNhapSoLuongSP.setSoLuongThuc(soLuongThuc);
                itemNhapSoLuongSP.setSL(soLuongThuc);
                TinhThanhTien();
            }
        }));

        etDonGiaThuc.addTextChangedListener(new NumberTextWatcher(etDonGiaThuc, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double donGiaThuc = 0;
                try {
                    donGiaThuc = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }
                itemNhapSoLuongSP.setDonGiaThuc(donGiaThuc);
                itemNhapSoLuongSP.setGiaban(donGiaThuc);
                itemNhapSoLuongSP.setDongia(itemNhapSoLuongSP.getGiaban() - itemNhapSoLuongSP.getGiatriDiscount());
                TinhThanhTien();
            }
        }));

        etChietKhau.addTextChangedListener(new NumberTextWatcher(etChietKhau, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double chietKhau = 0;
                try {
                    chietKhau = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }
                itemNhapSoLuongSP.setGiatriDiscount(chietKhau);
                itemNhapSoLuongSP.setDongia(itemNhapSoLuongSP.getGiaban() - itemNhapSoLuongSP.getGiatriDiscount());
                TinhThanhTien();
            }
        }));
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.nhapsoluong_tieudeform);
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
        sanPhamPresenter = new NhapSoLuongSanPhamPresenter(this);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            itemNhapSoLuongSP = (NhapSoLuongSanPhamInfo) bundle.getSerializable("NhapSoLuongSanPhamInfo");
            isNew = bundle.getBoolean("isNew");
        }
        if (itemNhapSoLuongSP == null) finish();
        sanPhamPresenter.GetProduct(itemNhapSoLuongSP.getProduct_ID());
    }

    private void LoadDonViTinh() {
        listDonViTinh = new ArrayList<>();
        if (productInfo.getDonVitinh() != null && !productInfo.getDonVitinh().isEmpty()) {
            listDonViTinh.add(productInfo.getDonVitinh());
        }
        if (productInfo.getDvt2() != null && !productInfo.getDvt2().isEmpty()) {
            listDonViTinh.add(productInfo.getDvt2());
        }
        if (productInfo.getDvt3() != null && !productInfo.getDvt3().isEmpty()) {
            listDonViTinh.add(productInfo.getDvt3());
        }
        if (productInfo.getDVT4() != null && !productInfo.getDVT4().isEmpty()) {
            listDonViTinh.add(productInfo.getDVT4());
        }



        ArrayAdapter<String> adapterChiNhanh = new ArrayAdapter<>(this, R.layout.item_spinner, listDonViTinh);
        spinnerDonViTinh.setAdapter(adapterChiNhanh);
        spinnerDonViTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                itemNhapSoLuongSP.setDvt(listDonViTinh.get(position));
                sanPhamPresenter.GetProductDonGia("XUAT", itemNhapSoLuongSP.getSupplierID(), productInfo.getProduct_ID(),
                        itemNhapSoLuongSP.getNgay(), itemNhapSoLuongSP.getSL(), itemNhapSoLuongSP.getDvt());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        int position = -1;
        for (int i = 0; i < listDonViTinh.size(); i++) {
            if (listDonViTinh.get(i).equals(itemNhapSoLuongSP.getDvt())) {
                position = i;
                break;
            }
        }
        spinnerDonViTinh.setSelection(position);
    }

    @Override
    public void onClick(View view) {
        double soLuong = itemNhapSoLuongSP.getSoLuongThuc();
        switch (view.getId()) {
            case R.id.btnMinus:
                soLuong += -1;
                itemNhapSoLuongSP.setSoLuongThuc(soLuong);
                itemNhapSoLuongSP.setSL(soLuong);
                etSoLuongThuc.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getSoLuongThuc()));
                TinhThanhTien();
                break;
            case R.id.btnPlus:
                soLuong += 1;
                itemNhapSoLuongSP.setSoLuongThuc(soLuong);
                itemNhapSoLuongSP.setSL(soLuong);
                etSoLuongThuc.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getSoLuongThuc()));
                TinhThanhTien();
                break;
            default:
                break;
        }
    }

    private void TinhThanhTien() {
        itemNhapSoLuongSP.setThanh_Tien(itemNhapSoLuongSP.getSL() * itemNhapSoLuongSP.getDongia() * (1 + itemNhapSoLuongSP.getThue()));
        etThanhTien.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getThanh_Tien()));
    }

    @Override
    public void onGetProductSuccess(ProductInfo itemResult) {

        productInfo = itemResult;
        sanPhamPresenter.GetProductTonKho(itemNhapSoLuongSP.getMaKho(), productInfo.getProduct_ID(), itemNhapSoLuongSP.getNgay());
        if(productInfo.getDonvitinhDongia() != null){

            String DVTDG = productInfo.getDonvitinhDongia().toUpperCase().trim();

            if(DVTDG == null || DVTDG == ""){
                itemNhapSoLuongSP.setDvt(productInfo.getDonVitinh());
            } else if(productInfo.getDonVitinh() != null && !productInfo.getDonVitinh().isEmpty() && DVTDG.equals(productInfo.getDonVitinh().toUpperCase().trim())){
                itemNhapSoLuongSP.setDvt(productInfo.getDonvitinhDongia());
            } else if(productInfo.getDvt2() != null && !productInfo.getDvt2().isEmpty() && DVTDG.equals(productInfo.getDvt2().toUpperCase().trim())){
                itemNhapSoLuongSP.setDvt(productInfo.getDonvitinhDongia());
            } else if(productInfo.getDvt3() != null && !productInfo.getDvt3().isEmpty() && DVTDG.equals(productInfo.getDvt3().toUpperCase().trim())){
                itemNhapSoLuongSP.setDvt(productInfo.getDonvitinhDongia());
            } else if(productInfo.getDVT4() != null && !productInfo.getDVT4().isEmpty() && DVTDG.equals(productInfo.getDVT4().toUpperCase().trim())){
                itemNhapSoLuongSP.setDvt(productInfo.getDonvitinhDongia());
            }

        }
        LoadDonViTinh();
        etMaSanPham.setText(productInfo.getProduct_ID());
        etTenSanPham.setText(productInfo.getProduct_Name());
        itemNhapSoLuongSP.setProduct_Name(productInfo.getProduct_Name());
        etSoLuongThuc.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getSoLuongThuc()));
        if (isNew) {
            sanPhamPresenter.GetProductDonGia("XUAT", itemNhapSoLuongSP.getSupplierID(), productInfo.getProduct_ID(),
                    itemNhapSoLuongSP.getNgay(), itemNhapSoLuongSP.getSL(), productInfo.getDonVitinh());
        } else

            etDonGiaThuc.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getDongia()));
        etChietKhau.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getGiatriDiscount()));
        etThanhTien.setText(AppUtils.formatNumber("N0").format(itemNhapSoLuongSP.getThanh_Tien()));
        etGhiChu.setText(itemNhapSoLuongSP.getGhichu());
        TinhThanhTien();
    }

    @Override
    public void onGetProductError(String error) {
//        CommonUtils.showMessageError(this, error);
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
    public void onGetProductTonKhoSuccess(double valueResult) {
        etTonKho.setText(AppUtils.formatNumber("N0").format(valueResult));
    }

    @Override
    public void onGetProductTonKhoError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onGetProductDonGiaSuccess(double valueResult) {
        itemNhapSoLuongSP.setGiaban(valueResult);
        itemNhapSoLuongSP.setDonGiaThuc(valueResult);
        itemNhapSoLuongSP.setDongia(itemNhapSoLuongSP.getGiaban() - itemNhapSoLuongSP.getGiatriDiscount());
        etDonGiaThuc.setText(AppUtils.formatNumber("N0").format(valueResult));
        TinhThanhTien();
    }

    @Override
    public void onGetProductDonGiaError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onKiemTraDuocXuatHangSuccess() {
        //hideProgressDialog();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NhapSoLuongSanPhamInfo", itemNhapSoLuongSP);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onKiemTraDuocXuatHangError(String error) {
        //hideProgressDialog();
        CommonUtils.showMessageError(this, error);
    }

    private void Luu() {
        //showProgressDialog();
        if (etSoLuongThuc.getText().length() == 0 || itemNhapSoLuongSP.getSL() == 0) {
            CommonUtils.showMessageError(this, "Bạn chưa nhập số lượng!");
           // hideProgressDialog();
            return;
        }
        itemNhapSoLuongSP.setGhichu(etGhiChu.getText().toString());
//        int count = 0;
//        switch (loaiPhieu) {
//            case LoaiPhieuInfo.LOAI_PHIEUXUAT:
//                if (listVattuxuat != null && listVattuxuat.size() > 0) {
//                    for (int i = 0; i < listVattuxuat.size(); i++) {
//                        if (listVattuxuat.get(i).getProduct_ID().equals(itemNhapSoLuongSP.getProduct_ID()))
//                            count++;
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//        if(count > 1)
//        {
//            CommonUtils.showMessageError(this, "Mã hàng !");
//            hideProgressDialog();
//            return;
//        }

        sanPhamPresenter.KiemTraDuocXuatHang(itemNhapSoLuongSP.getSoCT(), itemNhapSoLuongSP.getMaKho(), itemNhapSoLuongSP.getProduct_ID(),
                itemNhapSoLuongSP.getNgay(), itemNhapSoLuongSP.getSL());
    }

    @Override
    public void showProgressBar() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgressBar() {
        showProgressDialog(false);
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
        getMenuInflater().inflate(R.menu.menu_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:
                Luu();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
