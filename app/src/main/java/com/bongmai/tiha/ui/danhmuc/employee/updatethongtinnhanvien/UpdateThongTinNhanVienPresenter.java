package com.bongmai.tiha.ui.danhmuc.employee.updatethongtinnhanvien;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;

public class UpdateThongTinNhanVienPresenter implements UpdateThongTinNhanVienContract.Presenter{

    EmployeeModel employeeModel;
    UpdateThongTinNhanVienContract.View view;

    public UpdateThongTinNhanVienPresenter(UpdateThongTinNhanVienContract.View view) {
        this.view = view;
        this.employeeModel = new EmployeeModel();
    }

    @Override
    public void getEmployee(String employeeID) {
        employeeModel.GetEmployee(employeeID, new IEmployeeModel.IOnGetEmployeeFinishedListener() {
            @Override
            public void onSuccess(EmployeeInfo itemResult) {
                view.onGetEmployeeSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetEmployeeError(error);
            }
        });
    }

    @Override
    public void DeleteEmployee(EmployeeInfo employeeInfo) {

        employeeModel.DeleteEmployee(employeeInfo.getEmployeeID(), new IEmployeeModel.IOnDeleteEmployeeFinishedListener() {
            @Override
            public void onDeleteEmployeeSuccess() {
                view.onDeleteEmployeeSuccess();
            }

            @Override
            public void onDeleteEmployeeError(String error) {
                view.onGetEmployeeError(error);
            }
        });

    }
}
