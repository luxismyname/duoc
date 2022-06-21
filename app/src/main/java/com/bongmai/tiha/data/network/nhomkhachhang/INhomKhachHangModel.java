package com.bongmai.tiha.data.network.nhomkhachhang;

import com.bongmai.tiha.data.entities.PhuongThucTTInfo;

import java.util.List;


public interface INhomKhachHangModel {

    void GetListNhomKhachHang(IOnGetListNhomKhachHangFinishedListener listener);

    interface IOnGetListNhomKhachHangFinishedListener {
        void onGetListNhomKhachHangSuccess(List<PhuongThucTTInfo> listResult);

        void onGetListNhomKhachHangError(String error);
    }
}
