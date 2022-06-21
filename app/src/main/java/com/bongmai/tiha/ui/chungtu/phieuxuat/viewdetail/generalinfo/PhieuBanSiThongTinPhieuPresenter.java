package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.generalinfo;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;

public class PhieuBanSiThongTinPhieuPresenter implements PhieuBanSiThongTInPhieuContract.Presenter {

    PhieuBanSiThongTInPhieuContract.View view;
    EmployeeModel employeeModel;
    KhoModel khoModel;

    public PhieuBanSiThongTinPhieuPresenter(PhieuBanSiThongTInPhieuContract.View view) {
        this.view = view;
        this.employeeModel = new EmployeeModel();
        this.khoModel = new KhoModel();
    }

    @Override
    public void GetEmployee(String employeeID, final  String loai) {
        employeeModel.GetEmployee(employeeID, new IEmployeeModel.IOnGetEmployeeFinishedListener() {
            @Override
            public void onSuccess(EmployeeInfo itemResult) {
                view.onGetEmployeeSuccess(itemResult, loai);
            }

            @Override
            public void onError(String error) {
                view.onGetEmployeeError(error, loai);
            }
        });
    }

    @Override
    public void GetKho(String maKho) {
        khoModel.GetKho(maKho, new IKhoModel.IOnGetKhoFinishedListener() {
            @Override
            public void onSuccess(KhoInfo itemResult) {
                view.onGetKhoSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetKhoError(error);
            }
        });
    }
}
