package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.luongtheonvgh;

import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;
import com.bongmai.tiha.data.network.reportgiaohang.IReportGiaoHangModel;
import com.bongmai.tiha.data.network.reportgiaohang.ReportGiaoHangModel;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet.QuangDuongNhanVienGiaoHangChiTietContract;

import java.util.List;

public class ThongKeLuongNhanVienGiaoHangTheoQuangDuongPresenter implements  ThongKeLuongNhanVienGiaoHangTheoQuangDuongContract.Presenter{

    ThongKeLuongNhanVienGiaoHangTheoQuangDuongContract.View view;
    ReportGiaoHangModel reportGiaoHangModel;

    public ThongKeLuongNhanVienGiaoHangTheoQuangDuongPresenter(ThongKeLuongNhanVienGiaoHangTheoQuangDuongContract.View view) {
        this.view = view;
        this.reportGiaoHangModel = new ReportGiaoHangModel();
    }

    @Override
    public void ThongKeLuongNhanVienGiaoHangTheoQuangDuong(String ngayBD, String ngayKT, String maNV) {
        reportGiaoHangModel.GetListLuongNVGHTheoKM(ngayBD, ngayKT, maNV, new IReportGiaoHangModel.IOnGetListLuongNVGHTheoKMFinishedListener() {
            @Override
            public void onGetListLuongNVGHTheoKMSuccess(List<LuongNVGHTheoKMInfo> listResult) {
                view.onThongKeLuongNhanVienGiaoHangTheoQuangDuongSuccess(listResult);
            }

            @Override
            public void onGetListLuongNVGHTheoKMError(String error) {
                view.onThongKeLuongNhanVienGiaoHangTheoQuangDuongError(error);
            }
        });
    }

    @Override
    public void ThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLong(String ngayBD, String ngayKT, String maNV) {
        reportGiaoHangModel.GetListLuongNVGHTheoKMCuuLong(ngayBD, ngayKT, maNV, new IReportGiaoHangModel.IOnGetListLuongNVGHTheoKMCuuLongFinishedListener() {
            @Override
            public void onGetListLuongNVGHTheoKMCuuLongSuccess(List<LuongNVGHTheoKMInfo> listResult) {
                view.onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListLuongNVGHTheoKMCuuLongError(String error) {
                view.onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongError(error);
            }
        });
    }
}
