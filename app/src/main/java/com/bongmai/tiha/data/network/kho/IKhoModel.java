package com.bongmai.tiha.data.network.kho;


import com.bongmai.tiha.data.entities.KhoInfo;

import java.util.List;


public interface IKhoModel {
    void GetListKhoByUser(String userName, IOnGetListKhoByUserFinishedListener listener);

    interface IOnGetListKhoByUserFinishedListener {
        void onSuccess(List<KhoInfo> listResult);

        void onError(String error);
    }

    void GetKho(String maKho, IOnGetKhoFinishedListener listener);

    interface IOnGetKhoFinishedListener {
        void onSuccess(KhoInfo itemResult);
        void onError(String error);
    }

    void GetListKhoCuuLongByUser(String userName, IOnGetListKhoCuuLongByUserFinishedListener listener);

    interface IOnGetListKhoCuuLongByUserFinishedListener {
        void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult);

        void onGetListKhoCuuLongByUserError(String error);
    }

    void GetKhoCuuLong(String maKho, IOnGetKhoCuuLongFinishedListener listener);

    interface IOnGetKhoCuuLongFinishedListener {
        void onGetKhoCuuLongSuccess(KhoInfo itemResult);
        void onGetKhoCuuLongError(String error);
    }

}
