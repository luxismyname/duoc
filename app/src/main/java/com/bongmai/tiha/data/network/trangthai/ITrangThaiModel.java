package com.bongmai.tiha.data.network.trangthai;

import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;

import java.util.List;

public interface ITrangThaiModel {

    void  GetListTrangThai(IOnGetListTrangThaiFinishedListener listener);

    interface IOnGetListTrangThaiFinishedListener {
        void onGetListTrangThaiSuccess(List<TrangThaiInfo> listTrangthai);

        void onGetListTrangThaiError(String error);
    }

    void  GetListTrangThaiByLoaiPhieuByUser(String loaiPhieu, String userName, IOnGetListTrangThaiByLoaiPhieuByUserFinishedListener listener);

    interface IOnGetListTrangThaiByLoaiPhieuByUserFinishedListener {
        void onGetListTrangThaiByLoaiPhieuByUserSuccess(List<TrangThaiLoaiPhieuInfo> list);

        void onGetListTrangThaiByLoaiPhieuByUserError(String error);
    }
}
