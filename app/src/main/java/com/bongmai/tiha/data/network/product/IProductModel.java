package com.bongmai.tiha.data.network.product;

import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;

import java.util.Date;
import java.util.List;


public interface IProductModel {
    void InsertProduct(ProductInfo ProductInfo, IOnInsertProductFinishedListener listener);

    interface IOnInsertProductFinishedListener {
        void onInsertProductSuccess(ProductInfo itemResult);

        void onInsertProductError(String error);
    }

    void UpdateProduct(ProductInfo ProductInfo, IOnUpdateProductFinishedListener listener);

    interface IOnUpdateProductFinishedListener {
        void onUpdateProductSuccess(ProductInfo itemResult);

        void onUpdateProductError(String error);
    }

    void GetListProduct(ProductCondition condition, IOnGetListProductFinishedListener listener);

    interface IOnGetListProductFinishedListener {
        void onGetListProductSuccess(List<ProductInfo> listProduct, long total);

        void onGetListProductError(String error);
    }

    void GetProduct(String ProductID, IOnGetProductFinishedListener listener);

    interface IOnGetProductFinishedListener {
        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);
    }

    void GetProductTonKho(String maKho, String productID, Date ngay, IOnGetProductTonKhoFinishedListener listener);

    interface IOnGetProductTonKhoFinishedListener {
        void onGetProductTonKhoSuccess(double valueResult);

        void onGetProductTonKhoError(String error);
    }

    void GetProductDonGia(String loaiPhieu, String supplierID, String productID, Date ngay, double soLuong, String donViTinh,
                          IOnGetProductDonGiaFinishedListener listener);

    interface IOnGetProductDonGiaFinishedListener {
        void onGetProductDonGiaSuccess(double valueResult);

        void onGetProductDonGiaError(String error);
    }

    void GetHinhAnhByProductID(String ProductID, IOnGetHinhAnhByProductIDFinishedListener listener);

    interface IOnGetHinhAnhByProductIDFinishedListener {
        void onSuccess(String stringResult);

        void onError(String error);
    }

    void DeleteProduct(String supplierID, IOnDeleteProductFinishedListener listener);

    interface IOnDeleteProductFinishedListener{
        void onDeleteProductSuccess();
        void onDeleteProductError(String error);
    }

    void GetListAllProduct(ProductCondition condition, IOnGetListAllProductFinishedListener listener);

    interface IOnGetListAllProductFinishedListener {
        void onGetListAllProductSuccess(List<ProductInfo> listProduct);

        void onGetListAllProductError(String error);
    }

}
