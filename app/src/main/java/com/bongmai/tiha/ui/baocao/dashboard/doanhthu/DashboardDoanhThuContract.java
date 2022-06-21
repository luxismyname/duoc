package com.bongmai.tiha.ui.baocao.dashboard.doanhthu;


import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

public interface DashboardDoanhThuContract {
    interface View {
        void onGetDashboardDoanhThuSuccess(DashboardInfo itemResult);

        void onGetDashboardDoanhThuError(String error);
    }

    interface Presenter {
        void GetDashboardDoanhThu(DieuKienLocInfo dieuKienLocInfo);
    }
}
