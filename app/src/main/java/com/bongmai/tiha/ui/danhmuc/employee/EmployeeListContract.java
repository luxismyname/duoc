package com.bongmai.tiha.ui.danhmuc.employee;


import com.bongmai.tiha.data.entities.EmployeeInfo;

import java.util.List;

public interface EmployeeListContract {
    interface View {
        void onGetListEmployeeSuccess(List<EmployeeInfo> listResult);

        void onGetListEmployeeError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListEmployee();
    }
}
