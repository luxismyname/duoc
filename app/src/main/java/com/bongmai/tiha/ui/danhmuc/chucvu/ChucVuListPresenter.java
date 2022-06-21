package com.bongmai.tiha.ui.danhmuc.chucvu;

import com.bongmai.tiha.data.entities.ChucVuInfo;
import com.bongmai.tiha.data.network.chucvu.ChucVuModel;
import com.bongmai.tiha.data.network.chucvu.IChucVuModel;

import java.util.List;

public class ChucVuListPresenter implements ChucVuListContract.Presenter {

    ChucVuListContract.View view;
    ChucVuModel chucVuModel;

    public ChucVuListPresenter(ChucVuListContract.View view) {
        this.view = view;
        this.chucVuModel = new ChucVuModel();
    }

    @Override
    public void GetChucVuList() {
        chucVuModel.GetListChucVu(new IChucVuModel.IOnGetListChucVuFinishedListener() {
            @Override
            public void onGetListChucVuSuccess(List<ChucVuInfo> listResult) {
                view.onGetChucVuListSuccess(listResult);
            }

            @Override
            public void onGetListChucVuError(String error) {
                view.onGetChucVuListError(error);
            }
        });

    }
}
