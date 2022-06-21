package com.bongmai.tiha.ui.danhmuc.product.list.fragment;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LoaiSanPhamInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.utils.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditSanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditSanPhamFragment extends Fragment implements BaseFragment {
    TextView
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
            btnShowPopupDonViTinh,
            btnShowPopupDonViTinh2;
    Button btnMore;
    ConstraintLayout ctlMoreInfo, ctlMore;

    ProductInfo productInfo;

    public EditSanPhamFragment() {
        // Required empty public constructor
    }


    public static EditSanPhamFragment newInstance(ProductInfo productInfo) {
        EditSanPhamFragment fragment = new EditSanPhamFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_san_pham, container, false);

        onInit(view);
        onLoadData();
        return view;


    }


    @Override
    public void onInit(View view) {
        etMaSanPham = view.findViewById(R.id.etMaSanPham);
        etMaVach = view.findViewById(R.id.etMaVach);
        etTenSanPham = view.findViewById(R.id.etTenSanPham);
        etDonViTinh = view.findViewById(R.id.etDonViTinh);
        etDongGoi = view.findViewById(R.id.etDongGoi);
        etDonViTinh2 = view.findViewById(R.id.etDonViTinh2);
        etDongGoi2 = view.findViewById(R.id.etDongGoi2);
        etGiaNhap = view.findViewById(R.id.etGiaNhap);
        etGiaBanLe = view.findViewById(R.id.etGiaBanLe);
        etGiaBanSi = view.findViewById(R.id.etGiaBanSi);
        etGiaBanNo = view.findViewById(R.id.etGiaBanNo);
        etNhomSanPham = view.findViewById(R.id.etNhomSanPham);
        etLoaiSanPham = view.findViewById(R.id.etLoaiSanPham);
        etDienGiai = view.findViewById(R.id.etDienGiai);
        etThueSuat = view.findViewById(R.id.etThueSuat);
        etSLToiDa = view.findViewById(R.id.etSLToiDa);
        etSLToiThieu = view.findViewById(R.id.etSLToiThieu);
        etNhaCungCap = view.findViewById(R.id.etNhaCungCap);
        btnShowPopupDonViTinh = view.findViewById(R.id.btnShowPopupDonViTinh);
        btnShowPopupDonViTinh2 = view.findViewById(R.id.btnShowPopupDonViTinh2);
        btnMore = view.findViewById(R.id.btnMore);
        ctlMoreInfo = view.findViewById(R.id.ctlMoreInfo);
        ctlMore = view.findViewById(R.id.ctlMore);

    }

    @Override
    public void onLoadData() {



        if (productInfo !=null){

            etMaSanPham.setText(productInfo.getProduct_ID());
            etMaVach.setText(productInfo.getMaVachID());
            etTenSanPham.setText(productInfo.getProduct_Name());
            etDonViTinh.setText(productInfo.getDonVitinh());
            etDongGoi.setText(AppUtils.formatNumber("N0").format(productInfo.getDonggoi()));
            etDonViTinh2.setText(productInfo.getDvt2());
            etDongGoi2.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi3()));
            etGiaNhap.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaMua()));
            etGiaBanLe.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanLe()));
            etGiaBanNo.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanDLTP()));
            etGiaBanSi.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanDLT()));
            etNhomSanPham.setText(productInfo.getCategory_ID());
            etLoaiSanPham.setText(productInfo.getLoai());
//            if (!TextUtils.isEmpty(productInfo.getCategory_ID()))
//                themSanPhanPresenter.GetLoaiHang(productInfo.getCategory_ID());
//            for (LoaiSanPhamInfo itemLoaiSanPham : LoaiSanPhamInfo.loadLoaiSanPham()) {
//                if (itemLoaiSanPham.getMaLoaiSanPham().equals(productInfo.getLoai())) {
//                    etLoaiSanPham.setText(itemLoaiSanPham.getTenLoaiSanPham());
//                    break;
//                }
//            }

            etDienGiai.setText(productInfo.getDescription());

        }



    }


}