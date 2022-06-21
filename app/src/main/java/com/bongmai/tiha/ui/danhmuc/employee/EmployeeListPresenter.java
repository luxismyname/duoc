package com.bongmai.tiha.ui.danhmuc.employee;

import com.bongmai.tiha.data.entities.EmployeeCondition;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;

import java.util.List;

public class EmployeeListPresenter implements EmployeeListContract.Presenter {
    EmployeeListContract.View view;
    EmployeeModel employeeModel;

    public EmployeeListPresenter(EmployeeListContract.View view) {
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
