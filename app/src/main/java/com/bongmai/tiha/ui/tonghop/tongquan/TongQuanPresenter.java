package com.bongmai.tiha.ui.tonghop.tongquan;


import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.changelog.ChangeLogModel;
import com.bongmai.tiha.data.network.changelog.IChangeLogModel;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

public class TongQuanPresenter implements TongQuanContract.Presenter {
    TongQuanContract.View view;
    BaoCaoModel baoCaoModel;
    ChangeLogModel changeLogModel;

    public TongQuanPresenter(TongQuanContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
        this.changeLogModel = new ChangeLogModel();
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

    @Override
    public void CheckChangeLog() {

        changeLogModel.GetListChangeLog(new IChangeLogModel.IOnGetListChangeLogFinishedListener() {
            @Override
            public void onGetListChangeLogSuccess(ChangeLogInfo changeLogInfo) {
                view.onCheckChangeLogSuccess(changeLogInfo);
            }

            @Override
            public void onGetListChangeLogError(String error) {
                view.onCheckChangeLogError(error);
            }
        });

    }

}
