package com.bongmai.tiha.ui.danhmuc.product;

import android.text.TextUtils;

import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.loaihang.ILoaiHangModel;
import com.bongmai.tiha.data.network.loaihang.LoaiHangModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;

public class ThemSanPhanPresenter implements ThemSanPhamContract.Presenter {
    ThemSanPhamContract.View view;
    ProductModel productModel;
    LoaiHangModel loaiHangModel;


    public ThemSanPhanPresenter(ThemSanPhamContract.View view) {
        this.view = view;
        this.productModel = new ProductModel();
        this.loaiHangModel = new LoaiHangModel();
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
    public void InsertProduct(ProductInfo productInfo) {
        if (TextUtils.isEmpty(productInfo.getProduct_Name())) {
            view.onInsertProductError("Bạn chưa nhập tên sản phẩm!");
            return;
        }
        if (TextUtils.isEmpty(productInfo.getCategory_ID())) {
            view.onInsertProductError("Bạn chưa chọn nhóm sản phẩm!");
            return;
        }
        productModel.InsertProduct(productInfo, new IProductModel.IOnInsertProductFinishedListener() {
            @Override
            public void onInsertProductSuccess(ProductInfo itemResult) {
                view.onInsertProductSuccess(itemResult);
            }

            @Override
            public void onInsertProductError(String error) {
                view.onInsertProductError(error);
            }
        });
    }

    @Override
    public void UpdateProduct(ProductInfo productInfo) {
        if (TextUtils.isEmpty(productInfo.getProduct_Name())) {
            view.onInsertProductError("Bạn chưa nhập tên sản phẩm!");
            return;
        }
        if (TextUtils.isEmpty(productInfo.getCategory_ID())) {
            view.onInsertProductError("Bạn chưa chọn nhóm sản phẩm!");
            return;
        }
        productModel.UpdateProduct(productInfo, new IProductModel.IOnUpdateProductFinishedListener() {
            @Override
            public void onUpdateProductSuccess(ProductInfo itemResult) {
                view.onUpdateProductSuccess(itemResult);
            }

            @Override
            public void onUpdateProductError(String error) {
                view.onUpdateProductError(error);
            }
        });
    }

    @Override
    public void GetLoaiHang(String categoryID) {
        loaiHangModel.GetLoaiHang(categoryID, new ILoaiHangModel.IOnGetLoaiHangFinishedListener() {
            @Override
            public void onSuccess(LoaiHangInfo itemResult) {
                view.onGetLoaiHangSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetLoaiHangError(error);
            }
        });
    }


//    public void DeleteProduct(final ProductInfo productInfo) {
//
//        if (productModel == null || view == null) return;
//        view.showProgressDialog();
//        productModel.
//
//    }
}
