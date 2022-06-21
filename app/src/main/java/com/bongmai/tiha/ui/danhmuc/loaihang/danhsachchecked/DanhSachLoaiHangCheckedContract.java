package com.bongmai.tiha.ui.danhmuc.loaihang.danhsachchecked;

import com.bongmai.tiha.data.entities.LoaiHangInfo;

import java.util.List;

public interface DanhSachLoaiHangCheckedContract {
    interface View{
        void onGetListLoaiHangByUserSuccess(List<LoaiHangInfo> listResult);

        void onGetListLoaiHangByUserError(String error);
    }
    interface Presenter{
        void GetListLoaiHangByUser(String userName);
    }
}
