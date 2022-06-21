package com.bongmai.tiha.ui.danhmuc.trangthai;

import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.network.trangthai.ITrangThaiModel;
import com.bongmai.tiha.data.network.trangthai.TrangThaiModel;

import java.util.List;

public class TrangThaiPresenter implements TrangThaiContract.Presenter{

    TrangThaiContract.View view;
    TrangThaiModel trangThaiModel;

    public TrangThaiPresenter(TrangThaiContract.View view) {
        this.view = view;
        this.trangThaiModel = new TrangThaiModel();
    }

    @Override
    public void GetListTrangThai(String loaiPhieu, String userName) {
        trangThaiModel.GetListTrangThaiByLoaiPhieuByUser(loaiPhieu, userName, new ITrangThaiModel.IOnGetListTrangThaiByLoaiPhieuByUserFinishedListener() {
            @Override
            public void onGetListTrangThaiByLoaiPhieuByUserSuccess(List<TrangThaiLoaiPhieuInfo> list) {
                view.onGetListTrangThaiSuccess(list);
            }

            @Override
            public void onGetListTrangThaiByLoaiPhieuByUserError(String error) {
                view.onGetListTrangThaiError(error);
            }
        });
    }

//    @Override
//    public void GetListTrangThai() {
//        trangThaiModel.GetListTrangThai(new ITrangThaiModel.IOnGetListTrangThaiFinishedListener() {
//            @Override
//            public void onGetListTrangThaiSuccess(List<TrangThaiInfo> listTrangthai) {
//                view.onGetListTrangThaiSuccess(listTrangthai);
//            }
//
//            @Override
//            public void onGetListTrangThaiError(String error) {
//                view.onGetListTrangThaiError(error);
//            }
//        });
//    }
}
