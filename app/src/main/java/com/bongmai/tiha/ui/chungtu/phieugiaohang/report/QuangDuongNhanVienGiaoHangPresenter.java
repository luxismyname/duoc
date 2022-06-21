package com.bongmai.tiha.ui.chungtu.phieugiaohang.report;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.network.reportgiaohang.IReportGiaoHangModel;
import com.bongmai.tiha.data.network.reportgiaohang.ReportGiaoHangModel;

import java.util.List;

public class QuangDuongNhanVienGiaoHangPresenter implements QuangDuongNhanVienGiaoHangContract.Presenter{

    QuangDuongNhanVienGiaoHangContract.View view;
    ReportGiaoHangModel reportGiaoHangModel;

    public QuangDuongNhanVienGiaoHangPresenter(QuangDuongNhanVienGiaoHangContract.View view) {
        this.view = view;
        this.reportGiaoHangModel = new ReportGiaoHangModel();
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHang(EmployeeGiaoHangCondition condition) {
        reportGiaoHangModel.GetListQuangDuongNhanVienGiaoHang(condition, new IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangFinishedListener() {
            @Override
            public void onSuccess(List<EmployeeGiaoHangInfo> listResult) {
                view.onGetListQuangDuongNhanVienGiaoHangSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListQuangDuongNhanVienGiaoHangError(error);
            }
        });
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangCuuLong(EmployeeGiaoHangCondition condition) {
        reportGiaoHangModel.GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(condition, new IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangChiTietCuuLongFinishedListener() {
            @Override
            public void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult) {
                view.onGetListQuangDuongNhanVienGiaoHangCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongError(String error) {
                view.onGetListQuangDuongNhanVienGiaoHangCuuLongError(error);
            }
        });
    }
}
