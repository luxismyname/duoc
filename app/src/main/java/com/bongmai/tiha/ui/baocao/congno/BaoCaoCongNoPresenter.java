package com.bongmai.tiha.ui.baocao.congno;


import com.bongmai.tiha.data.entities.CongNoTongHopInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiThuInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiTraInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

import java.util.List;

public class BaoCaoCongNoPresenter implements BaoCaoCongNoContract.Presenter {
    BaoCaoModel baoCaoModel;

    public BaoCaoCongNoPresenter() {
        baoCaoModel = new BaoCaoModel();
    }


    @Override
    public void GetListCongNoTongHop(DieuKienLocInfo dieuKienLocInfo, final BaoCaoCongNoContract.IOnGetListCongNoTongHopFinishedListener listener) {
        baoCaoModel.GetListCongNoTongHop(dieuKienLocInfo, new IBaoCaoModel.IOnGetListCongNoTongHopFinishedListener() {
            @Override
            public void onSuccess(List<CongNoTongHopInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListCongNoTongHopPhaiThu(DieuKienLocInfo dieuKienLocInfo, final BaoCaoCongNoContract.IOnGetListCongNoTongHopPhaiThuFinishedListener listener) {
        baoCaoModel.GetListCongNoTongHopPhaiThu(dieuKienLocInfo, new IBaoCaoModel.IOnGetListCongNoTongHopPhaiThuFinishedListener() {
            @Override
            public void onSuccess(List<CongNoTongHopPhaiThuInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListCongNoTongHopPhaiTra(DieuKienLocInfo dieuKienLocInfo, final BaoCaoCongNoContract.IOnGetListCongNoTongHopPhaiTraFinishedListener listener) {
        baoCaoModel.GetListCongNoTongHopPhaiTra(dieuKienLocInfo, new IBaoCaoModel.IOnGetListCongNoTongHopPhaiTraFinishedListener() {
            @Override
            public void onSuccess(List<CongNoTongHopPhaiTraInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }
}
