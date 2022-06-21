package com.bongmai.tiha.ui.baocao.cuocgoi;

import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

import java.util.List;

public class BaoCaoCuocGoiPresenter implements BaoCaoCuocGoiContract.Presenter {

    BaoCaoCuocGoiContract.View view;
    BaoCaoModel baoCaoModel;

    public BaoCaoCuocGoiPresenter(BaoCaoCuocGoiContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
    }

    @Override
    public void GetListThongKeCuocGoi(DieuKienLocInfo dieuKienLocInfo) {
        baoCaoModel.GetListThongKeCuocGoi(dieuKienLocInfo, new IBaoCaoModel.IOnGetListThongKeCuocGoiFinishedListener() {
            @Override
            public void onSuccess(List<DanhSachCuocGoiInfo> listResult) {
                view.onGetListThongKeCuocGoiSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListThongKeCuocGoiError(error);
            }
        });
    }
}
