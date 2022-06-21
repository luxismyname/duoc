package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail;

import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;

import java.util.List;

public class PhieuBanSiDetailPresenter implements PhieuBanSiDetailContract.Presenter {

    PhieuBanSiDetailContract.View view;
    PhieuXuatModel phieuXuatModel;


    public PhieuBanSiDetailPresenter(PhieuBanSiDetailContract.View view) {
        this.view = view;
        this.phieuXuatModel = new PhieuXuatModel();
    }


    @Override
    public void GetPhieuXuat(String soCT) {
        phieuXuatModel.GetPhieuXuat(soCT, new IPhieuXuatModel.IOnGetPhieuXuatFinishedListener() {
            @Override
            public void onSuccess(PhieuXuatInfo phieuXuatResult) {
                view.onGetPhieuXuatSuccess(phieuXuatResult);
            }

            @Override
            public void onError(String error) {
                view.onGetPhieuXuatError(error);
            }
        });
    }

    @Override
    public void GetPhieuXuatChitiet(String soCT) {
        phieuXuatModel.GetPhieuXuatChiTiet(soCT, new IPhieuXuatModel.IOnGetPhieuXuatChiTietFinishedListener() {
            @Override
            public void onGetVattuSuccess(List<VattuxuatInfo> list) {
                view.onGetPhieuXuatChiTietSuccess(list);
            }

            @Override
            public void onVattuError(String error) {
                view.onGetPhieuXuatChiTietError(error);
            }
        });


    }


}
