package com.bongmai.tiha.ui.baocao.dashboard.cuocgoi;


import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

public class DashboardCuocGoiPresenter implements DashboardCuocGoiContract.Presenter {
    DashboardCuocGoiContract.View view;
    BaoCaoModel baoCaoModel;

    public DashboardCuocGoiPresenter(DashboardCuocGoiContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
    }

    @Override
    public void GetDashboardCuocGoi(DieuKienLocInfo dieuKienLocInfo) {
        baoCaoModel.GetDashboardCuocGoi(dieuKienLocInfo, new IBaoCaoModel.IOnGetDashboardCuocGoiFinishedListener() {
            @Override
            public void onSuccess(DashboardInfo dashboardInfo) {
                view.onGetDashboardCuocGoiSuccess(dashboardInfo);
            }

            @Override
            public void onError(String error) {
                view.onGetDashboardCuocGoiError(error);
            }
        });
    }
}
