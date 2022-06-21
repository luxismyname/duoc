package com.bongmai.tiha.ui.danhmuc.loaihang.danhsachchecked;

import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.network.loaihang.ILoaiHangModel;
import com.bongmai.tiha.data.network.loaihang.LoaiHangModel;

import java.util.List;

public class DanhSachLoaiHangCheckedPresenter implements DanhSachLoaiHangCheckedContract.Presenter {
    DanhSachLoaiHangCheckedContract.View view;
    LoaiHangModel loaiHangModel;

    public DanhSachLoaiHangCheckedPresenter(DanhSachLoaiHangCheckedContract.View view) {
        this.view = view;
        this.loaiHangModel = new LoaiHangModel();
    }

    @Override
    public void GetListLoaiHangByUser(String userName) {
        loaiHangModel.GetListLoaiHangByUser(userName, new ILoaiHangModel.IOnGetListLoaiHangByUserFinishedListener() {
            @Override
            public void onSuccess(List<LoaiHangInfo> listResult) {
                view.onGetListLoaiHangByUserSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListLoaiHangByUserError(error);
            }
        });
    }
}
