package com.bongmai.tiha.ui.danhmuc.loaihang.list;


import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.network.loaihang.ILoaiHangModel;
import com.bongmai.tiha.data.network.loaihang.LoaiHangModel;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.List;

public class LoaiHangListPresenter implements LoaiHangListContract.Presenter {
    LoaiHangListContract.View view;
    LoaiHangModel loaiHangModel;

    public LoaiHangListPresenter(LoaiHangListContract.View view) {
        this.view = view;
        this.loaiHangModel = new LoaiHangModel();
    }

    @Override
    public void GetListLoaiHang() {
        loaiHangModel.GetListLoaiHangByUser(PublicVariables.nguoiDungInfo.getUserName(), new ILoaiHangModel.IOnGetListLoaiHangByUserFinishedListener() {
            @Override
            public void onSuccess(List<LoaiHangInfo> listResult) {
                view.onGetListLoaiHangSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListLoaiHangError(error);
            }
        });
    }

    @Override
    public void InsertLoaiHang(final LoaiHangInfo loaiHangInfo) {
        loaiHangModel.InsertLoaiHang(loaiHangInfo, new ILoaiHangModel.IOnInsertLoaiHangFinishedListener() {
            @Override
            public void onSuccess(LoaiHangInfo itemResult) {
                view.onInsertLoaiHangSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onInsertLoaiHangError(error, loaiHangInfo);
            }
        });
    }
}
