package com.bongmai.tiha.ui.danhmuc.product;

import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;

import java.util.List;

public interface ThemSanPhamContract {
    interface View {
        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);

        void onInsertProductSuccess(ProductInfo itemResult);

        void onInsertProductError(String error);

        void onUpdateProductSuccess(ProductInfo itemResult);

        void onUpdateProductError(String error);


        void onGetLoaiHangSuccess(LoaiHangInfo itemResult);

        void onGetLoaiHangError(String error);

        void showProgressBar();

        void hideProgressBar();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void GetProduct(String ProductID);

        void InsertProduct(ProductInfo ProductInfo);

        void UpdateProduct(ProductInfo ProductInfo);

        void GetLoaiHang(String categoryID);


    }
}
