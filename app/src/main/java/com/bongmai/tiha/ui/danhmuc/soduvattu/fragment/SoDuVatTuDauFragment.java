package com.bongmai.tiha.ui.danhmuc.soduvattu.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.utils.AppUtils;

public class SoDuVatTuDauFragment extends Fragment implements BaseFragment {

    EditText
            etKho,
            etNgay,
            etSanPham,
            etLoaiHang,
            etDVT,
            etSLThuc,
            etSL,
            etDonGiaThuc,
            etDG,
            etThanhTien;

    SoDuVatTuDauInfo soDuVatTuDauInfo;

    public static SoDuVatTuDauFragment newInstance(SoDuVatTuDauInfo soDuVatTuDauInfo) {
        SoDuVatTuDauFragment fragment = new SoDuVatTuDauFragment();
        Bundle args = new Bundle();
        args.putSerializable("SoDuVatTuDauInfo", soDuVatTuDauInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soDuVatTuDauInfo = (getArguments() != null) ? (SoDuVatTuDauInfo) getArguments().getSerializable("SoDuVatTuDauInfo") : new SoDuVatTuDauInfo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_so_du_vat_tu_dau, container, false);
        onInit(view);
        onLoadData();
        return view;
    }

    @Override
    public void onInit(View view) {
        etKho = view.findViewById(R.id.etKho);
        etNgay = view.findViewById(R.id.etNgay);
        etSanPham = view.findViewById(R.id.etSanPham);
        etLoaiHang = view.findViewById(R.id.etLoaiHang);
        etDVT = view.findViewById(R.id.etDVT);
        etSLThuc = view.findViewById(R.id.etSoluongThuc);
        etSL = view.findViewById(R.id.etSoLuong);
        etDonGiaThuc = view.findViewById(R.id.etDongiaThuc);
        etDG = view.findViewById(R.id.etDonGia);
        etThanhTien = view.findViewById(R.id.etThanhTien);
    }

    @Override
    public void onLoadData() {
        if(soDuVatTuDauInfo != null){
            etKho.setText(soDuVatTuDauInfo.getMSk());
            etNgay.setText(soDuVatTuDauInfo.getNgay());
            etSanPham.setText(soDuVatTuDauInfo.getProduct_Name());
            etDVT.setText(soDuVatTuDauInfo.getDVT());
            etSLThuc.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSoLuongThuc()));
            etSL.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSODUDAU()));
            etDonGiaThuc.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getDonGiaThuc()));
            etDG.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getDONGIA()));
            etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getThanhTien()));
        }

    }
}