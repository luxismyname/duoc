package com.bongmai.tiha.ui.danhmuc.loaihang.list;

import com.bongmai.tiha.data.entities.LoaiHangInfo;

import java.util.List;

public interface LoaiHangListContract {
    interface View {
        void onGetListLoaiHangSuccess(List<LoaiHangInfo> listResult);

        void onGetListLoaiHangError(String error);

        void onInsertLoaiHangSuccess(LoaiHangInfo itemResult);

        void onInsertLoaiHangError(String error, LoaiHangInfo loaiHangInfo);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListLoaiHang();
        void InsertLoaiHang(LoaiHangInfo loaiHangInfo);
    }
}
