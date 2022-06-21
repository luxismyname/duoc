package com.bongmai.tiha.ui.baocao.dashboard.cuocgoi;


import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

public interface DashboardCuocGoiContract {
    interface View {
        void onGetDashboardCuocGoiSuccess(DashboardInfo itemResult);

        void onGetDashboardCuocGoiError(String error);
    }

    interface Presenter {
        void GetDashboardCuocGoi(DieuKienLocInfo dieuKienLocInfo);
    }
}
