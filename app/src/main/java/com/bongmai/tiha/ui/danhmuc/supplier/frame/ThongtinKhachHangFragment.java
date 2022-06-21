package com.bongmai.tiha.ui.danhmuc.supplier.frame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseFragment;

public class ThongtinKhachHangFragment extends Fragment implements BaseFragment {

    TextView edtMaKH,
            edtTenKH,
            edtPhone,
            edtDiachiso,
            edtTinhThanhPho,
            edtQuanHuyen,
            edtPhuongXa,
            edtDuong,
            edtSPSD,
            edtNhomKH;
    SupplierInfo supplierInfo;

    public static ThongtinKhachHangFragment newInstance(SupplierInfo supplierInfo) {
        ThongtinKhachHangFragment fragment = new ThongtinKhachHangFragment();
        Bundle args = new Bundle();
        args.putSerializable("SupplierInfo", supplierInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supplierInfo = (getArguments() != null) ? (SupplierInfo) getArguments().getSerializable("SupplierInfo") : new SupplierInfo();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thongtinkhachhang, container, false);
        onInit(view);
        onLoadData();
        return view;

    }


    @Override
    public void onInit(View view) {
                edtMaKH = view.findViewById(R.id.edtMaKH);
                edtTenKH = view.findViewById(R.id.edtTenKH);
                edtPhone = view.findViewById(R.id.edtPhone);
                edtDiachiso = view.findViewById(R.id.edtDiachiso);
                edtTinhThanhPho = view.findViewById(R.id.edtTinhThanhPho);
                edtQuanHuyen = view.findViewById(R.id.edtQuanHuyen);
                edtPhuongXa = view.findViewById(R.id.edtPhuongXa);
                edtDuong = view.findViewById(R.id.edtDuong);
                edtSPSD = view.findViewById(R.id.edtSPSD);
                edtNhomKH = view.findViewById(R.id.edtNhomKH);

    }

    @Override
    public void onLoadData() {
        if(supplierInfo !=null) {
            edtMaKH.setText(supplierInfo.getSupplier_ID()   );
            edtTenKH.setText(supplierInfo.getCompany_Name());
            edtPhone.setText(supplierInfo.getPhone());
            edtDiachiso.setText(supplierInfo.getSo());
            edtTinhThanhPho.setText(supplierInfo.getTinh());
            edtQuanHuyen.setText(supplierInfo.getQuan());
            edtPhuongXa.setText(supplierInfo.getPhuong());
            edtDuong.setText(supplierInfo.getDuong());
            edtSPSD.setText(supplierInfo.getSPSuDung());
            edtNhomKH.setText(supplierInfo.getNhomKhachHang());


        }
        edtMaKH.setEnabled(false);
        edtTenKH.setEnabled(false);
        edtPhone.setEnabled(false);
        edtDiachiso.setEnabled(false);
        edtTinhThanhPho.setEnabled(false);
        edtQuanHuyen.setEnabled(false);
        edtPhuongXa.setEnabled(false);
        edtDuong.setEnabled(false);
        edtSPSD.setEnabled(false);
        edtNhomKH.setEnabled(false);
    }


}