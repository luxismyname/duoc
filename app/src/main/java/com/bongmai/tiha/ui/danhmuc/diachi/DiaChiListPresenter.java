package com.bongmai.tiha.ui.danhmuc.diachi;

import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.data.network.diachi.DiaChiModel;
import com.bongmai.tiha.data.network.diachi.IDiaChiModel;

import java.util.List;

public class DiaChiListPresenter implements DiaChiListContract.Presenter {
    DiaChiListContract.View view;
    DiaChiModel diaChiModel;

    public DiaChiListPresenter(DiaChiListContract.View view) {
        this.view = view;
        this.diaChiModel = new DiaChiModel();
    }

    @Override
    public void GetListDiaChi(String loaiDiaChi, int parentID, int parentName) {
        diaChiModel.GetListDiaChi(loaiDiaChi, parentID, parentName, new IDiaChiModel.IOnGetListDiaChiFinishedListener() {
            @Override
            public void onGetListDiaChiSuccess(List<DiaChiInfo> listResult) {
                view.onGetListDiaChiSuccess(listResult);
            }

            @Override
            public void onGetListDiaChiError(String error) {
                view.onGetListDiaChiError(error);
            }
        });
    }

    @Override
    public void InsertDiaChi(DiaChiInfo diaChiInfo) {
        diaChiModel.InsertDiaChi(diaChiInfo, new IDiaChiModel.IOnInsertDiaChiFinishedListener() {
            @Override
            public void onInsertDiaChiSuccess(DiaChiInfo itemResult) {
                view.onInsertDiaChiSuccess(itemResult);
            }

            @Override
            public void onInsertError(String error) {
                view.onInsertDiaChiError(error);
            }
        });
    }


//    @Override
//    public void InsertDiaChi(DiaChiInfo diaChiInfo) {
//        if(diaChiModel == null) return;
//        diaChiModel.InsertDiaChi(diaChiInfo, new IDiaChiModel.IOnInsertDiaChiFinishedListener() {
//            @Override
//            public void onInsertDiaChiSuccess(DiaChiInfo itemResult) {
//                view.onInsertDiaChiSuccess(itemResult);
//            }
//
//            @Override
//            public void onInsertDiaChiError(String error) {
//                view.onInsertDiaChiError(error);
//            }
//        });
//    }


}
