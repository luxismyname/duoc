package com.bongmai.tiha.ui.danhmuc.kho.list;

import com.bongmai.tiha.data.entities.KhoInfo;

import java.util.List;

public interface KhoListContract {
    interface View {
        void onGetListKhoSuccess(List<KhoInfo> listResult);

        void onGetListKhoError(String error);

        void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult);

        void onGetListKhoCuuLongError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListKho();

        void GetListKhoCuuLong();
    }
}
