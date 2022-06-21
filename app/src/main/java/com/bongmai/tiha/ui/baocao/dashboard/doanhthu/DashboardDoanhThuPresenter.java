package com.bongmai.tiha.ui.baocao.dashboard.doanhthu;


import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

public class DashboardDoanhThuPresenter implements DashboardDoanhThuContract.Presenter {
    DashboardDoanhThuContract.View view;
    BaoCaoModel baoCaoModel;

    public DashboardDoanhThuPresenter(DashboardDoanhThuContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
    }


    @Override
    public void GetDashboardDoanhThu(DieuKienLocInfo dieuKienLocInfo) {
        baoCaoModel.GetDashboardDoanhThu(dieuKienLocInfo, new IBaoCaoModel.IOnGetDashboardDoanhThuFinishedListener() {
            @Override
            public void onSuccess(DashboardInfo dashboardInfo) {
                view.onGetDashboardDoanhThuSuccess(dashboardInfo);
            }

            @Override
            public void onError(String error) {
                view.onGetDashboardDoanhThuError(error);
            }
        });
    }
}
