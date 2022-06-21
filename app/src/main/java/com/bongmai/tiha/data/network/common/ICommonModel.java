package com.bongmai.tiha.data.network.common;

import com.bongmai.tiha.data.entities.BangGiaGroupInfo;

import java.util.Date;
import java.util.List;


public interface ICommonModel {
    void GetListBangGiaGroup(IOnGetListBangGiaGroupFinishedListener listener);

    interface IOnGetListBangGiaGroupFinishedListener {
        void onSuccess(List<BangGiaGroupInfo> listResult);

        void onError(String error);
    }

    void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong, IOnKiemTraDuocXuatHangFinishedListener listener);

    interface IOnKiemTraDuocXuatHangFinishedListener {
        void onSuccess();

        void onError(String error);
    }
}
