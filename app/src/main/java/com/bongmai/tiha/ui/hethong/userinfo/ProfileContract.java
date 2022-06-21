package com.bongmai.tiha.ui.hethong.userinfo;

public interface ProfileContract {
    interface View{
        void onGetHinhAnhByEmployeeIDSuccess(String stringResult);
        void onGetHinhAnhByEmployeeIDError(String error);
    }

    interface Presenter{
        void GetHinhAnhByEmployeeID(String employeeID);
    }
}
