package com.bongmai.tiha.ui.danhmuc.diachi;

import com.bongmai.tiha.data.entities.DiaChiInfo;

import java.util.List;

public interface DiaChiListContract {
    interface View {
        void onGetListDiaChiSuccess(List<DiaChiInfo> listResult);

        void onGetListDiaChiError(String error);

        void onInsertDiaChiSuccess(DiaChiInfo itemResult);
        void onInsertDiaChiError(String error);


    }

    interface Presenter {
        void GetListDiaChi(String loaiDiaChi, int parentID, int parentName);
        void InsertDiaChi(DiaChiInfo diaChiInfo);

    }
}
