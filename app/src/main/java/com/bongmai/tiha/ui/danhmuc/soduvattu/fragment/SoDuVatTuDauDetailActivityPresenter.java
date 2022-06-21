package com.bongmai.tiha.ui.danhmuc.soduvattu.fragment;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.soduvattudau.ISoDuVatTuDauModel;
import com.bongmai.tiha.data.network.soduvattudau.SoDuVatTuDauModel;

public class SoDuVatTuDauDetailActivityPresenter implements SoDuVatTuDauDetailActivityContact.Presenter {

    SoDuVatTuDauDetailActivityContact.View view;
    SoDuVatTuDauModel soDuVatTuDauModel;

    public SoDuVatTuDauDetailActivityPresenter(SoDuVatTuDauDetailActivityContact.View view) {
        this.view = view;
        this.soDuVatTuDauModel = new SoDuVatTuDauModel();
    }

    @Override
    public void GetSoDuVatTuDau(String soduvattudauID) {
        soDuVatTuDauModel.GetSoDuVatTuDau(soduvattudauID, new ISoDuVatTuDauModel.IOnGetSoDuVatTuDauFinishedListener() {
            @Override
            public void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {
                view.onGetSoDuVatTuDauSuccess(itemResult);
            }

            @Override
            public void onGetSoDuVatTuDauError(String error) {
                view.onGetSoDuVatTuDauError(error);
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
