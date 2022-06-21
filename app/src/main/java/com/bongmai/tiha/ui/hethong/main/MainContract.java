package com.bongmai.tiha.ui.hethong.main;

public interface MainContract {
    interface View {
        void onGetHinhAnhByEmployeeIDSuccess(String stringResult);
        void onGetHinhAnhByEmployeeIDError(String error);
    }

    interface Presenter{
        void GetHinhAnhByEmployeeID(String employeeID);
    }
}
