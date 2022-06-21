package com.bongmai.tiha.ui.danhmuc.employee.themnhanvien;

import android.text.TextUtils;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;

public class ThemNhanVienPresenter implements ThemNhanVienContract.Presenter {

    ThemNhanVienContract.View view;
    EmployeeModel employeeModel;

    public ThemNhanVienPresenter(ThemNhanVienContract.View view) {
        this.view = view;
        this.employeeModel = new EmployeeModel();
    }

    @Override
    public void GetEmployee(String EmployeeID) {

        employeeModel.GetEmployee(EmployeeID, new IEmployeeModel.IOnGetEmployeeFinishedListener() {
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
    public void InsertEmployee(EmployeeInfo employeeInfo) {
        if (TextUtils.isEmpty(employeeInfo.getDepartmentID())){
            view.onInsertEmployeeError("Bạn chưa chọn bộ phận nhân viên!");
            return;
        }
        if (TextUtils.isEmpty(employeeInfo.getPosition())) {
            view.onInsertEmployeeError("Bạn chưa chọn chức vụ nhân viên!");
            return;
        }

        if (TextUtils.isEmpty(employeeInfo.getBasicSalary())) {
            view.onInsertEmployeeError("Bạn chưa nhập lương cơ bản!");
            return;
        }

        employeeModel.InsertEmployee(employeeInfo, new IEmployeeModel.IOnInsertEmployeeFinishedListener() {
            @Override
            public void onInsertEmployeeSuccess(EmployeeInfo itemResult) {
                view.onInsertEmployeeSuccess(itemResult);
            }

            @Override
            public void onInsertEmployeeError(String error) {
                view.onInsertEmployeeError(error);
            }
        });
    }

    @Override
    public void UpdateEmployee(EmployeeInfo employeeInfo) {
        employeeModel.UpdateEmployee(employeeInfo, new IEmployeeModel.IOnUpdateEmployeeFinishedListener() {
            @Override
            public void onUpdateEmployeeSuccess(EmployeeInfo itemResult) {
                view.onUpdateEmployeeSuccess(itemResult);
            }

            @Override
            public void onUpdateEmployeeError(String error) {
                view.onUpdateEmployeeError(error);
            }
        });
    }



//    @Override
//    public void GetListBoPhan(String DepartmentID) {
//        employeeModel.GetListBoPhan(DepartmentID, new IEmployeeModel.IOnGetListBoPhanFinishedListener() {
//            @Override
//            public void onGetListBoPhanSuccess(EmployeeInfo itemResult) {
//                view.onGetEmployeeSuccess(itemResult);
//            }
//
//            @Override
//            public void onGetListBoPhanError(String error) {
//                view.onUpdateEmployeeError(error);
//            }
//        });
//    }


}
