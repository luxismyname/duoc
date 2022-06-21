package com.bongmai.tiha.ui.hethong.userinfo;


import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;

public class ProfilePresenter implements ProfileContract.Presenter {

    ProfileContract.View view;
    EmployeeModel employeeModel;


    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        this.employeeModel = new EmployeeModel();
    }

    @Override
    public void GetHinhAnhByEmployeeID(String employeeID) {
        employeeModel.GetHinhAnhByEmployeeID(employeeID, new IEmployeeModel.IOnGetHinhAnhByEmployeeIDFinishedListener() {
            @Override
            public void onSuccess(String stringResult) {
                view.onGetHinhAnhByEmployeeIDSuccess(stringResult);
            }

            @Override
            public void onError(String error) {
                view.onGetHinhAnhByEmployeeIDError(error);
            }
        });
    }
}
