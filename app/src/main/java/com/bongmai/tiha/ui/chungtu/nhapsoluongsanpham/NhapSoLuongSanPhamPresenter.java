package com.bongmai.tiha.ui.chungtu.nhapsoluongsanpham;

import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.common.CommonModel;
import com.bongmai.tiha.data.network.common.ICommonModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;

import java.util.Date;

public class NhapSoLuongSanPhamPresenter implements NhapSoLuongSanPhamContract.Presenter {
    NhapSoLuongSanPhamContract.View view;
    ProductModel productModel;
    CommonModel commonModel;

    public NhapSoLuongSanPhamPresenter(NhapSoLuongSanPhamContract.View view) {
        this.view = view;
        this.productModel = new ProductModel();
        this.commonModel = new CommonModel();
    }

    @Override
    public void GetProduct(String productID) {
        productModel.GetProduct(productID, new IProductModel.IOnGetProductFinishedListener() {
            @Override
            public void onGetProductSuccess(ProductInfo itemResult) {
                view.onGetProductSuccess(itemResult);
            }

            @Override
            public void onGetProductError(String error) {
                view.onGetProductError(error);
            }
        });
    }

    @Override
    public void GetProductTonKho(String maKho, String productID, Date ngay) {
        productModel.GetProductTonKho(maKho, productID, ngay, new IProductModel.IOnGetProductTonKhoFinishedListener() {
            @Override
            public void onGetProductTonKhoSuccess(double valueResult) {
                view.onGetProductTonKhoSuccess(valueResult);
            }

            @Override
            public void onGetProductTonKhoError(String error) {
                view.onGetProductTonKhoError(error);
            }
        });
    }

    @Override
    public void GetProductDonGia(String loaiPhieu, String supplierID, String productID, Date ngay, double soLuong, String donViTinh) {
        productModel.GetProductDonGia(loaiPhieu, supplierID, productID, ngay, soLuong, donViTinh, new IProductModel.IOnGetProductDonGiaFinishedListener() {
            @Override
            public void onGetProductDonGiaSuccess(double valueResult) {
                view.onGetProductDonGiaSuccess(valueResult);
            }

            @Override
            public void onGetProductDonGiaError(String error) {
                view.onGetProductDonGiaError(error);
            }
        });
    }

    @Override
    public void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong) {
        commonModel.KiemTraDuocXuatHang(soPhieu, maKho, productID, ngay, soLuong, new ICommonModel.IOnKiemTraDuocXuatHangFinishedListener() {
            @Override
            public void onSuccess() {
                view.onKiemTraDuocXuatHangSuccess();
            }

            @Override
            public void onError(String error) {
                view.onKiemTraDuocXuatHangError(error);
            }
        });
    }
}
