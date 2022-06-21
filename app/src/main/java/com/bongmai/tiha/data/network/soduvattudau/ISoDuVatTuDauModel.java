package com.bongmai.tiha.data.network.soduvattudau;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;

import java.util.List;

public interface ISoDuVatTuDauModel {

    void GetListSoDuVatTuDau(IOnGetListSoDuVaTuDauFinishedListener listener);

    interface IOnGetListSoDuVaTuDauFinishedListener{

        void onGetListSoDuVatTuDauSuccess(List<SoDuVatTuDauInfo> listResult);
        void onGetListSoDuVatTuDauError(String Error);

    }

    void GetSoDuVatTuDau(String soDuVatTuDauID, IOnGetSoDuVatTuDauFinishedListener listener);

    interface IOnGetSoDuVatTuDauFinishedListener{

        void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult);
        void onGetSoDuVatTuDauError(String error);

    }

    void InsertSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo, ISoDuVatTuDauModel.IOnInsertSoDuVatTuDauFinishedListener listener);

    interface IOnInsertSoDuVatTuDauFinishedListener {
        void onInsertSuccess(SoDuVatTuDauInfo itemResult);

        void onInsertError(String error);
    }

    void UpdateSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo, ISoDuVatTuDauModel.IOnUpdateSoDuVatTuDauFinishedListener listener);

    interface IOnUpdateSoDuVatTuDauFinishedListener {
        void onUpdateSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult);

        void onUpdateSoDuVatTuDauError(String error);
    }

    void DeleteSoDuVatTuDau(String soDuVatTuDauID, ISoDuVatTuDauModel.IOnDeleteSoDuVatTuDauFinishedListener listener);

    interface  IOnDeleteSoDuVatTuDauFinishedListener{
        void onDeleteSoDuVatTuDauSuccess();
        void onDeleteSoDuVatTuDauError(String error);
    }

}
