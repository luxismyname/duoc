package com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;

import java.util.List;

public class DanhSachKhoCheckedPresenter implements DanhSachKhoCheckedContract.Presenter {

    DanhSachKhoCheckedContract.View view;
    KhoModel khoModel;

    public DanhSachKhoCheckedPresenter(DanhSachKhoCheckedContract.View view) {
        this.view = view;
        this.khoModel = new KhoModel();
    }

    @Override
    public void GetListKhoByUser(String userName) {
        khoModel.GetListKhoByUser(userName, new IKhoModel.IOnGetListKhoByUserFinishedListener() {
            @Override
            public void onSuccess(List<KhoInfo> listResult) {
                view.onGetListKhoByUserSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListKhoByUserError(error);
            }
        });
    }
}
