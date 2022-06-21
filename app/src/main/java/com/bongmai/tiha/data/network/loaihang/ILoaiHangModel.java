package com.bongmai.tiha.data.network.loaihang;




import com.bongmai.tiha.data.entities.LoaiHangInfo;

import java.util.List;


public interface ILoaiHangModel {
    void GetListLoaiHangByUser(String userName, IOnGetListLoaiHangByUserFinishedListener listener);

    interface IOnGetListLoaiHangByUserFinishedListener {
        void onSuccess(List<LoaiHangInfo> listResult);

        void onError(String error);
    }

    void GetLoaiHang(String categoryID, IOnGetLoaiHangFinishedListener listener);

    interface IOnGetLoaiHangFinishedListener {
        void onSuccess(LoaiHangInfo itemResult);

        void onError(String error);
    }

    void InsertLoaiHang(LoaiHangInfo loaiHangInfo, IOnInsertLoaiHangFinishedListener listener);

    interface IOnInsertLoaiHangFinishedListener {
        void onSuccess(LoaiHangInfo itemResult);

        void onError(String error);
    }
}
