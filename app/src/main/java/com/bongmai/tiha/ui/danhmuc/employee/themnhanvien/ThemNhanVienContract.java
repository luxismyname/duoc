package com.bongmai.tiha.ui.danhmuc.employee.themnhanvien;

import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiContract;

import java.util.List;

public interface ThemNhanVienContract {
    interface View {
        void onGetEmployeeSuccess(EmployeeInfo itemResult);

        void onGetEmployeeError(String error);

        void onInsertEmployeeSuccess(EmployeeInfo itemResult);

        void onInsertEmployeeError(String error);

        void onUpdateEmployeeSuccess(EmployeeInfo itemResult);

        void onUpdateEmployeeError(String error);

//        void onGetListBoPhanSuccess(EmployeeInfo itemResult);
//        void onGetListBoPhanError(String error);


        void showProgressBar();

        void hideProgressBar();

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void GetEmployee(String EmployeeID);

        void InsertEmployee(EmployeeInfo employeeInfo);

        void UpdateEmployee(EmployeeInfo employeeInfo);

//        void GetListBoPhan(String DepartmentID);



    }
}
