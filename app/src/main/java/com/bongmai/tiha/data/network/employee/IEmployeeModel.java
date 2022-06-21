package com.bongmai.tiha.data.network.employee;

import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.EmployeeCondition;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;

import java.util.List;


public interface IEmployeeModel {

    void InsertEmployee(EmployeeInfo employeeInfo, IEmployeeModel.IOnInsertEmployeeFinishedListener listener);

    interface IOnInsertEmployeeFinishedListener {
        void onInsertEmployeeSuccess(EmployeeInfo itemResult);

        void onInsertEmployeeError(String error);
    }

    void UpdateEmployee(EmployeeInfo employeeInfo, IEmployeeModel.IOnUpdateEmployeeFinishedListener listener);

    interface IOnUpdateEmployeeFinishedListener {
        void onUpdateEmployeeSuccess(EmployeeInfo itemResult);

        void onUpdateEmployeeError(String error);
    }


    void GetListBoPhan(IOnGetListBoPhanFinishedListener listener);

    interface IOnGetListBoPhanFinishedListener {
//        void onGetListBoPhanSuccess(EmployeeInfo employeeInfo);
        void onGetListBoPhanSuccess(List<BoPhanInfo> listResult);
        void onGetListBoPhanError(String error);
    }

    void GetListChucVu( IOnGetListChucVuFinishedListener listener);

    interface IOnGetListChucVuFinishedListener {
        void onGetListChucVuSuccess(List<EmployeeInfo> listResult);

        void onGetListChucVuError(String error);
    }

    void GetListEmployee( IOnGetListEmployeeFinishedListener listener);

    interface IOnGetListEmployeeFinishedListener {
        void onGetListEmployeeSuccess(List<EmployeeInfo> listResult);

        void onGetListEmployeeError(String error);
    }

    void GetHinhAnhByEmployeeID(String EmployeeID, IOnGetHinhAnhByEmployeeIDFinishedListener listener);

    interface IOnGetHinhAnhByEmployeeIDFinishedListener {
        void onSuccess(String stringResult);

        void onError(String error);
    }

    void GetEmployee(String employeeID, IOnGetEmployeeFinishedListener listener);

    interface IOnGetEmployeeFinishedListener {
        void onSuccess(EmployeeInfo itemResult);

        void onError(String error);
    }

    void DeleteEmployee(String employeeID, IEmployeeModel.IOnDeleteEmployeeFinishedListener listener);

    interface  IOnDeleteEmployeeFinishedListener{
        void onDeleteEmployeeSuccess();
        void onDeleteEmployeeError(String error);
    }

}
