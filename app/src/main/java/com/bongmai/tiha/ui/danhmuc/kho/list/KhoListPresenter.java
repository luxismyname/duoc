package com.bongmai.tiha.ui.danhmuc.kho.list;


import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.List;

public class KhoListPresenter implements KhoListContract.Presenter {
    KhoListContract.View view;
    KhoModel khoModel;

    public KhoListPresenter(KhoListContract.View view) {
        this.view = view;
        this.khoModel = new KhoModel();
    }

    @Override
    public void GetListKho() {
        khoModel.GetListKhoByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoByUserFinishedListener() {
            @Override
            public void onSuccess(List<KhoInfo> listResult) {
                view.onGetListKhoSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListKhoError(error);
            }
        });

    }

    @Override
    public void GetListKhoCuuLong() {
        khoModel.GetListKhoCuuLongByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoCuuLongByUserFinishedListener() {
            @Override
            public void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult) {
                view.onGetListKhoCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListKhoCuuLongByUserError(String error) {
                 view.onGetListKhoCuuLongError(error);
            }
        });
    }


}
