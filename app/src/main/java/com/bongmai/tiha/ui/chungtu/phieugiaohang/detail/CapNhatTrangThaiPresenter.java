package com.bongmai.tiha.ui.chungtu.phieugiaohang.detail;

import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.network.phieugiaohang.IPhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieugiaohang.PhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;

public class CapNhatTrangThaiPresenter implements CapNhatTrangThaiContract.Presenter {

    CapNhatTrangThaiContract.View view;
    PhieuXuatModel phieuXuatModel;
    PhieuGiaoHangModel phieuGiaoHangModel;


    public CapNhatTrangThaiPresenter(CapNhatTrangThaiContract.View view) {
        this.view = view;
        this.phieuXuatModel = new PhieuXuatModel();
        this.phieuGiaoHangModel = new PhieuGiaoHangModel();
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
    public void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuXuat(thongKeGiaoNhanHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuXuatFinishedListener() {
            @Override
            public void onSuccess() {
                view.onUpdateTrangThaiPhieuXuatSuccess(thongKeGiaoNhanHangInfo);
            }

            @Override
            public void onError(String error) {
                view.onUpdateTrangThaiPhieuXuatError(error);
            }
        });
    }
}
