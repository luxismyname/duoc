package com.bongmai.tiha.ui.tonghop.tongquan;


import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

public interface TongQuanContract {
    interface View {
        void onGetDashboardDoanhThuSuccess(DashboardInfo itemResult);

        void onGetDashboardDoanhThuError(String error);

        void onCheckChangeLogSuccess(ChangeLogInfo changeLogInfo);

        void onCheckChangeLogError(String error);
    }

    interface Presenter {

        void GetDashboardDoanhThu(DieuKienLocInfo dieuKienLocInfo);

        void CheckChangeLog();
    }
}
