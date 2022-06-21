package com.bongmai.tiha.ui.chungtu.phieugiaohang.chonnhanviengiaohang;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;

import java.util.List;

public interface ChonEmployeeContract {

    interface View {

        void onGetListEmployeeSuccess(List<EmployeeInfo> listResult);

        void onGetListEmployeeError(String error);
    }

    interface Presenter {
        void GetListEmployee();
    }

}
