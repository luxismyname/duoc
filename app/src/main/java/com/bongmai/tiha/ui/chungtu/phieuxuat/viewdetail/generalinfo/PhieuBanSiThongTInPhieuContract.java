package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.generalinfo;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;

public interface PhieuBanSiThongTInPhieuContract {
    interface View{
        void onGetEmployeeSuccess(EmployeeInfo itemResult, String loai);

        void onGetEmployeeError(String error, String loai);

        void onGetKhoSuccess(KhoInfo itemResult);

        void onGetKhoError(String error);
    }

    interface Presenter{
        void GetEmployee(String employeeID, String loai);

        void GetKho(String maKho);
    }
}
