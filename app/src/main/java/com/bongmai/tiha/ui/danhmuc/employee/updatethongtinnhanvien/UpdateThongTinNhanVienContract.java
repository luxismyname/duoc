package com.bongmai.tiha.ui.danhmuc.employee.updatethongtinnhanvien;

import com.bongmai.tiha.data.entities.EmployeeInfo;

public interface UpdateThongTinNhanVienContract {
    interface View{

        void onGetEmployeeSuccess(EmployeeInfo employeeInfo);
        void onGetEmployeeError(String error);
        void onDeleteEmployeeSuccess();

        void onDeleteEmployeeError(String error);


    }

    interface Presenter{
        void getEmployee(String employeeID);
        void DeleteEmployee(EmployeeInfo employeeInfo);
    }
}
