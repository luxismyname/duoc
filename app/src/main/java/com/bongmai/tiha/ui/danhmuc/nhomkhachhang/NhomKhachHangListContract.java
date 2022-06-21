package com.bongmai.tiha.ui.danhmuc.nhomkhachhang;

import com.bongmai.tiha.data.entities.PhuongThucTTInfo;

import java.util.List;

public interface NhomKhachHangListContract {
    interface View {
        void onGetListNhomKhachHangSuccess(List<PhuongThucTTInfo> listResult);

        void onGetListNhomKhachHangError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListNhomKhachHang();
    }
}
