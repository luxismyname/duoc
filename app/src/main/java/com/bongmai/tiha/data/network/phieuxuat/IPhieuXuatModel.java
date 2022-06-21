package com.bongmai.tiha.data.network.phieuxuat;

import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;

import java.util.List;


public interface IPhieuXuatModel {
    void InsertPhieuXuat(PhieuXuatInfo PhieuXuatInfo, List<VattuxuatInfo> listVattuxuat, IOnInsertPhieuXuatFinishedListener listener);

    interface IOnInsertPhieuXuatFinishedListener {
        void onSuccess(PhieuXuatInfo itemResult);

        void onError(String error);
    }

    void UpdatePhieuXuat(PhieuXuatInfo PhieuXuatInfo, List<VattuxuatInfo> listVattuxuat, IOnUpdatePhieuXuatFinishedListener listener);

    interface IOnUpdatePhieuXuatFinishedListener {
        void onSuccess(PhieuXuatInfo itemResult);

        void onError(String error);
    }

    void GetListPhieuXuat(PhieuXuatCondition condition, IOnGetListPhieuXuatFinishedListener listener);

    interface IOnGetListPhieuXuatFinishedListener {
        void onSuccess(List<DanhSachXuatInfo> listResult);

        void onError(String error);
    }

    void GetPhieuXuat(String soPhieu, IOnGetPhieuXuatFinishedListener listener);

    interface IOnGetPhieuXuatFinishedListener {
        void onSuccess(PhieuXuatInfo phieuXuatResult);

        void onError(String error);
    }


    void GetPhieuXuatCuuLong(String soPhieu, IOnGetPhieuXuatCuuLongFinishedListener listener);

    interface IOnGetPhieuXuatCuuLongFinishedListener {
        void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatCuuLongError(String error);
    }



    void GetPhieuXuatChiTiet(String soPhieu, IOnGetPhieuXuatChiTietFinishedListener listener);

    interface IOnGetPhieuXuatChiTietFinishedListener {
        void onGetVattuSuccess(List<VattuxuatInfo> list);

        void onVattuError(String error);
    }


    void DeletePhieuXuat(String soPhieu, IOnDeletePhieuXuatFinishedListener listener);

    interface IOnDeletePhieuXuatFinishedListener {
        void onSuccess();

        void onError(String error);
    }

    void InQuaInternet(String soPhieu, String computerName, IOnInQuaInternetFinishedListener listener);

    interface IOnInQuaInternetFinishedListener {
        void onSuccess();

        void onError(String error);
    }

}
