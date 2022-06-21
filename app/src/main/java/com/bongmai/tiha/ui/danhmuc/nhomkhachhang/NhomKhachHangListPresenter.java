package com.bongmai.tiha.ui.danhmuc.nhomkhachhang;


import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.data.network.nhomkhachhang.INhomKhachHangModel;
import com.bongmai.tiha.data.network.nhomkhachhang.NhomKhachHangModel;

import java.util.List;

public class NhomKhachHangListPresenter implements NhomKhachHangListContract.Presenter {
    NhomKhachHangListContract.View view;
    NhomKhachHangModel nhomKhachHangModel;

    public NhomKhachHangListPresenter(NhomKhachHangListContract.View view) {
        this.view = view;
        this.nhomKhachHangModel = new NhomKhachHangModel();
    }

    @Override
    public void GetListNhomKhachHang() {
        nhomKhachHangModel.GetListNhomKhachHang(new INhomKhachHangModel.IOnGetListNhomKhachHangFinishedListener() {
            @Override
            public void onGetListNhomKhachHangSuccess(List<PhuongThucTTInfo> listResult) {
                view.onGetListNhomKhachHangSuccess(listResult);
            }

            @Override
            public void onGetListNhomKhachHangError(String error) {
                view.onGetListNhomKhachHangError(error);
            }
        });
    }
}
