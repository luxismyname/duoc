package com.bongmai.tiha.ui.chungtu.nhapsoluongsanpham;


import com.bongmai.tiha.data.entities.ProductInfo;

import java.util.Date;

public interface NhapSoLuongSanPhamContract {
    interface View {
        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);

        void onGetProductTonKhoSuccess(double valueResult);

        void onGetProductTonKhoError(String error);

        void onGetProductDonGiaSuccess(double valueResult);

        void onGetProductDonGiaError(String error);

        void onKiemTraDuocXuatHangSuccess();

        void onKiemTraDuocXuatHangError(String error);

        void showProgressBar();

        void hideProgressBar();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void GetProduct(String productID);
        void GetProductTonKho(String maKho, String productID, Date ngay);
        void GetProductDonGia(String loaiPhieu, String supplierID, String productID, Date ngay, double soLuong, String donViTinh);
        void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong);
    }
}
