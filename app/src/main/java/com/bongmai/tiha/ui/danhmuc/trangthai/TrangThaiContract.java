package com.bongmai.tiha.ui.danhmuc.trangthai;

import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;

import java.util.List;

public interface TrangThaiContract {
    interface View {
        void onGetListTrangThaiSuccess(List<TrangThaiLoaiPhieuInfo> listResult);

        void onGetListTrangThaiError(String error);
    }

    interface Presenter{

        void GetListTrangThai(String loaiPhieu, String userName);

    }
}
