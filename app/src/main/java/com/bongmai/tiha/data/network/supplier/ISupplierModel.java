package com.bongmai.tiha.data.network.supplier;

import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;

import java.util.List;


public interface ISupplierModel {
    void InsertSupplier(SupplierInfo SupplierInfo, IOnInsertSupplierFinishedListener listener);

    interface IOnInsertSupplierFinishedListener {
        void onInsertSupplierSuccess(SupplierInfo itemResult);

        void onInsertSupplierError(String error);
    }

    void UpdateSupplier(SupplierInfo SupplierInfo, IOnUpdateSupplierFinishedListener listener);

    interface IOnUpdateSupplierFinishedListener {
        void onUpdateSupplierSuccess(SupplierInfo itemResult);

        void onUpdateSupplierError(String error);
    }

    void GetListSupplier(SupplierCondition condition, IOnGetListSupplierFinishedListener listener);

    interface IOnGetListSupplierFinishedListener {
        void onGetListSupplierSuccess(List<SupplierInfo> listSupplier, long total);

        void onGetListSupplierError(String error);
    }

    void GetSupplier(String SupplierID, IOnGetSupplierFinishedListener listener);

    interface IOnGetSupplierFinishedListener {
        void onGetSupplierSuccess(SupplierInfo itemResult);

        void onGetSupplierError(String error);
    }

    void DeleteSupplier(String SupplierID, IOnDeleteSupplierFinishedListener listener);

    interface  IOnDeleteSupplierFinishedListener{
        void onDeleteSupplierSuccess();
        void onDeleteSupplierError(String error);
    }

    void CapNhatToaDoKhachHang(String supplierID, String viDo, String kinhDo, IOnCapNhatToaDoKhachHangFinishedListener listener);

    interface IOnCapNhatToaDoKhachHangFinishedListener {
        void onSuccess();

        void onError(String error);
    }

    void GetSupplierDuocCuuLong(String SupplierID, IOnGetSupplierDuocCuuLongFinishedListener listener);

    interface IOnGetSupplierDuocCuuLongFinishedListener {
        void onGetSupplierDuocCuuLongSuccess(SupplierInfo itemResult);

        void onGetSupplierDuocCuuLongError(String error);
    }

}
