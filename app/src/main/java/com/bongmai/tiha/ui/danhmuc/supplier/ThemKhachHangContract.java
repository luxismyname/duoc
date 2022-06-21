package com.bongmai.tiha.ui.danhmuc.supplier;

import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;

import java.util.List;

public interface ThemKhachHangContract {
    interface View {
        void onGetSupplierSuccess(SupplierInfo itemResult);

        void onGetSupplierError(String error);

        void onInsertSupplierSuccess(SupplierInfo itemResult);

        void onInsertSupplierError(String error);

        void onUpdateSupplierSuccess(SupplierInfo itemResult);

        void onUpdateSupplierError(String error);

        void onGetListBangGiaSuccess(List<BangGiaGroupInfo> listResult);

        void onGetListBangGiaError(String error);


        void showProgressBar();

        void hideProgressBar();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void GetSupplier(String supplierID);

        void InsertSupplier(SupplierInfo supplierInfo);

        void UpdateSupplier(SupplierInfo supplierInfo);

        void GetListBangGia();


    }
}
