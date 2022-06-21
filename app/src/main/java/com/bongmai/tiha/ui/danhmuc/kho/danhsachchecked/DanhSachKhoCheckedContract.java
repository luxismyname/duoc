package com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked;

import com.bongmai.tiha.data.entities.KhoInfo;

import java.util.List;

public interface DanhSachKhoCheckedContract {
    interface View {

        void onGetListKhoByUserSuccess(List<KhoInfo> listResult);

        void onGetListKhoByUserError(String error);
    }

    interface Presenter {
        void GetListKhoByUser(String userName);
    }
}
