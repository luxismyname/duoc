package com.bongmai.tiha.ui.danhmuc.soduvattu.list;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.soduvattudau.ISoDuVatTuDauModel;
import com.bongmai.tiha.data.network.soduvattudau.SoDuVatTuDauModel;

import java.util.List;

public class ListSoDuVatTuActivityPresenter implements ListSoDuVatTuActivityContract.Presenter {

    ListSoDuVatTuActivityContract.View view;
    SoDuVatTuDauModel soDuVatTuDauModel;

    public ListSoDuVatTuActivityPresenter(ListSoDuVatTuActivityContract.View view) {
        this.view = view;
        this.soDuVatTuDauModel = new SoDuVatTuDauModel();
    }


    @Override
    public void GetListSoDuVatTuDauKy() {
        soDuVatTuDauModel.GetListSoDuVatTuDau(new ISoDuVatTuDauModel.IOnGetListSoDuVaTuDauFinishedListener() {
            @Override
            public void onGetListSoDuVatTuDauSuccess(List<SoDuVatTuDauInfo> listResult) {
                view.onGetListSoDuVatTuDauKySuccess(listResult);
            }

            @Override
            public void onGetListSoDuVatTuDauError(String Error) {
                view.onGetListSoDuVatTuDauKyError(Error);
            }
        });
    }

    @Override
    public void DeleteSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo) {
        soDuVatTuDauModel.DeleteSoDuVatTuDau(String.valueOf(soDuVatTuDauInfo.getID()), new ISoDuVatTuDauModel.IOnDeleteSoDuVatTuDauFinishedListener() {
            @Override
            public void onDeleteSoDuVatTuDauSuccess() {
                view.onDeleteSuccess();
            }

            @Override
            public void onDeleteSoDuVatTuDauError(String error) {
                view.onDeleteError(error);
            }
        });
    }
}
