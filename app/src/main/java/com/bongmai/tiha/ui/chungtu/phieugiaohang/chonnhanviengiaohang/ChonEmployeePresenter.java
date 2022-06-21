package com.bongmai.tiha.ui.chungtu.phieugiaohang.chonnhanviengiaohang;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;

import java.util.List;

public class ChonEmployeePresenter implements ChonEmployeeContract.Presenter {
    ChonEmployeeContract.View view;
    EmployeeModel employeeModel;

    public ChonEmployeePresenter(ChonEmployeeContract.View view) {
        this.view = view;
        this.employeeModel = new EmployeeModel();
    }

    @Override
    public void GetListEmployee() {
        employeeModel.GetListEmployee(new IEmployeeModel.IOnGetListEmployeeFinishedListener() {
            @Override
            public void onGetListEmployeeSuccess(List<EmployeeInfo> listResult) {
                view.onGetListEmployeeSuccess(listResult);
            }

            @Override
            public void onGetListEmployeeError(String error) {
                view.onGetListEmployeeError(error);
            }
        });
    }
}