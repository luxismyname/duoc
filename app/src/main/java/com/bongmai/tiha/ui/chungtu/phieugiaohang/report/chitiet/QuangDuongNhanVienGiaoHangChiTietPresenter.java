package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.network.reportgiaohang.IReportGiaoHangModel;
import com.bongmai.tiha.data.network.reportgiaohang.ReportGiaoHangModel;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.QuangDuongNhanVienGiaoHangContract;

import java.util.List;

public class QuangDuongNhanVienGiaoHangChiTietPresenter  implements QuangDuongNhanVienGiaoHangChiTietContract.Presenter {

    QuangDuongNhanVienGiaoHangChiTietContract.View view;
    ReportGiaoHangModel reportGiaoHangModel;

    public QuangDuongNhanVienGiaoHangChiTietPresenter(QuangDuongNhanVienGiaoHangChiTietContract.View view) {
        this.view = view;
        this.reportGiaoHangModel = new ReportGiaoHangModel();
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangChiTiet(EmployeeGiaoHangCondition condition) {
        reportGiaoHangModel.GetListQuangDuongNhanVienGiaoHangChiTiet(condition, new IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangChiTietFinishedListener() {
            @Override
            public void onSuccess(List<EmployeeGiaoHangInfo> listResult) {
                view.onGetListQuangDuongNhanVienGiaoHangChiTietSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListQuangDuongNhanVienGiaoHangChiTietError(error);
            }
        });
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(EmployeeGiaoHangCondition condition) {
        reportGiaoHangModel.GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(condition, new IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangChiTietCuuLongFinishedListener() {
            @Override
            public void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult) {
                view.onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietSuccess(listResult);
            }

            @Override
            public void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongError(String error) {
                view.onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietError(error);
            }
        });
    }
}
