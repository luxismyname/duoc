package com.bongmai.tiha.ui.danhmuc.product.list.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.danhmuc.product.ThemSanPhamContract;
import com.bongmai.tiha.ui.danhmuc.product.ThemSanPhanPresenter;


public class ThemSanPhamFragmentDetail extends Fragment implements BaseFragment {
    EditText
            etMaSanPham,
            etMaVach,
            etTenSanPham,
            etDongGoi,
            etDongGoi2,
            etGiaNhap,
            etGiaBanLe,
            etGiaBanSi,
            etGiaBanNo,
            etNhomSanPham,
            etLoaiSanPham,
            etDienGiai,
            etSLToiDa,
            etSLToiThieu,
            etThueSuat,
            etNhaCungCap;
    AutoCompleteTextView
            etDonViTinh,
            etDonViTinh2;
    ImageButton
            btnQuetMaVach,
            btnShowPopupDonViTinh,
            btnShowPopupDonViTinh2;
    Button btnMore;
    ConstraintLayout ctlMoreInfo, ctlMore;
//    Dialog progressDialog;
//
//    ProductInfo productCurrent;
//    Boolean isAdd, isEdit;
//    Boolean isCapNhatSanPham;
   ProductInfo productInfo;


    public static ThemSanPhamFragmentDetail newInstance(ProductInfo productInfo) {
        ThemSanPhamFragmentDetail fragment = new ThemSanPhamFragmentDetail();
        Bundle args = new Bundle();
        args.putSerializable("ProductInfo", productInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInfo = (getArguments() != null) ? (ProductInfo) getArguments().getSerializable("ProductInfo") : new ProductInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them_san_pham_detail, container, false);
        // onInit(view);
        //  onLoadData();
        //  return view;

    }

    @Override
    public void onInit(View view) {
        etMaSanPham = view.findViewById(R.id.etMaSanPham);
        etMaVach = view.findViewById(R.id.etMaVach);
        etTenSanPham = view.findViewById(R.id.etTenSanPham);
        etDongGoi = view.findViewById(R.id.etDongGoi);
        etDongGoi2 = view.findViewById(R.id.etDongGoi2);
        etGiaNhap = view.findViewById(R.id.etGiaNhap);
        etGiaBanLe = view.findViewById(R.id.etGiaBanLe);
        etGiaBanSi = view.findViewById(R.id.etGiaBanSi);
        etGiaBanNo = view.findViewById(R.id.etGiaBanNo);
        etNhomSanPham = view.findViewById(R.id.etNhomSanPham);
        etLoaiSanPham = view.findViewById(R.id.etLoaiSanPham);
        etDienGiai = view.findViewById(R.id.etDienGiai);
        etSLToiDa = view.findViewById(R.id.etSLToiDa);
        etSLToiThieu = view.findViewById(R.id.etSLToiThieu);
        etThueSuat = view.findViewById(R.id.etThueSuat);
        etNhaCungCap = view.findViewById(R.id.etNhaCungCap);
        etDonViTinh = view.findViewById(R.id.etDonViTinh);
        etDonViTinh2 = view.findViewById(R.id.etDonViTinh2);
        btnQuetMaVach = view.findViewById(R.id.btnQuetMaVach);
        btnShowPopupDonViTinh = view.findViewById(R.id.btnShowPopupDonViTinh);
        btnShowPopupDonViTinh2 = view.findViewById(R.id.btnShowPopupDonViTinh2);
        btnMore = view.findViewById(R.id.btnMore);
        ctlMoreInfo = view.findViewById(R.id.ctlMoreInfo);
        ctlMore = view.findViewById(R.id.ctlMore);


    }

    @Override
    public void onLoadData() {

    }
}

