package com.bongmai.tiha.data.network.diachi;

import com.bongmai.tiha.data.entities.DiaChiInfo;

import java.util.List;


public interface IDiaChiModel {
    void GetListDiaChi(String loaiDiaChi, int parentID, int parentName, IOnGetListDiaChiFinishedListener listener);

    interface IOnGetListDiaChiFinishedListener {
        void onGetListDiaChiSuccess(List<DiaChiInfo> listResult);

        void onGetListDiaChiError(String error);

    }


//    void InsertDiaChi(DiaChiInfo diaChiInfo, IOnInsertDiaChiFinishedListener listener);
//    interface IOnInsertDiaChiFinishedListener{
//        void onInsertDiaChiSuccess(DiaChiInfo itemResult);
//        void onInsertDiaChiError(String error);
//    }

    void InsertDiaChi(DiaChiInfo diaChiInfo, IOnInsertDiaChiFinishedListener listener);

    interface IOnInsertDiaChiFinishedListener{
        void onInsertDiaChiSuccess(DiaChiInfo itemResult);
        void onInsertError(String error);
    }

}
