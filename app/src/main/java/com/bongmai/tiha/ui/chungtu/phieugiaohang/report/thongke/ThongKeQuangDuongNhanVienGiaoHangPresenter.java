package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;
import com.bongmai.tiha.data.network.reportgiaohang.IReportGiaoHangModel;
import com.bongmai.tiha.data.network.reportgiaohang.ReportGiaoHangModel;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.QuangDuongNhanVienGiaoHangContract;

import java.util.List;

public class ThongKeQuangDuongNhanVienGiaoHangPresenter implements ThongKeQuangDuongNhanVienGiaoHangContract.Presenter {

    ThongKeQuangDuongNhanVienGiaoHangContract.View view;
    ReportGiaoHangModel reportGiaoHangModel;

    public ThongKeQuangDuongNhanVienGiaoHangPresenter(ThongKeQuangDuongNhanVienGiaoHangContract.View view) {
        this.view = view;
        this.reportGiaoHangModel = new ReportGiaoHangModel();
    }


    @Override
    public void GetListThongKeKMNVGH(String ngayBD, String ngayKT) {
        reportGiaoHangModel.GetListThongKeKMNVGH(ngayBD, ngayKT, new IReportGiaoHangModel.IOnGetListThongKeKMNVGHFinishedListener() {
            @Override
            public void onGetListThongKeKMNVGHSuccess(List<ThongKeKMNVGHInfo> listResult) {
                view.onGetListThongKeKMNVGHSuccess(listResult);
            }

            @Override
            public void onGetListThongKeKMNVGHError(String error) {
                view.onGetListThongKeKMNVGHError(error);
            }
        });
    }

    @Override
    public void GetListThongKeKMNVGHCuuLong(String ngayBD, String ngayKT) {
        reportGiaoHangModel.GetListThongKeKMNVGHCuuLong(ngayBD, ngayKT, new IReportGiaoHangModel.IOnGetListThongKeKMNVGHCuuLongFinishedListener() {
            @Override
            public void onGetListThongKeKMNVGHCuuLongSuccess(List<ThongKeKMNVGHInfo> listResult) {
                view.onGetListThongKeKMNVGHCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListThongKeKMNVGHCuuLongError(String error) {
                view.onGetListThongKeKMNVGHError(error);
            }
        });
    }
}
