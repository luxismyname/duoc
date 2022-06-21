package com.bongmai.tiha.data.network.reportgiaohang;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;

import java.util.List;

public interface IReportGiaoHangModel {

    void GetListQuangDuongNhanVienGiaoHang(EmployeeGiaoHangCondition condition, IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener);

    interface IOnGetListQuangDuongNhanVienGiaoHangFinishedListener {
        void onSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onError(String error);
    }

    void GetListQuangDuongNhanVienGiaoHangCuuLong(EmployeeGiaoHangCondition condition, IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangCuuLongFinishedListener listener);

    interface IOnGetListQuangDuongNhanVienGiaoHangCuuLongFinishedListener {
        void onGetListQuangDuongNhanVienGiaoHangCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangCuuLongError(String error);
    }

    void GetListQuangDuongNhanVienGiaoHangChiTiet(EmployeeGiaoHangCondition condition, IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangChiTietFinishedListener listener);

    interface IOnGetListQuangDuongNhanVienGiaoHangChiTietFinishedListener {
        void onSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onError(String error);
    }


    void GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(EmployeeGiaoHangCondition condition, IReportGiaoHangModel.IOnGetListQuangDuongNhanVienGiaoHangChiTietCuuLongFinishedListener listener);

    interface IOnGetListQuangDuongNhanVienGiaoHangChiTietCuuLongFinishedListener {
        void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongError(String error);
    }

    void GetListThongKeKMNVGH(String ngayBD, String ngayKT,  IOnGetListThongKeKMNVGHFinishedListener listener);

    interface IOnGetListThongKeKMNVGHFinishedListener {
        void onGetListThongKeKMNVGHSuccess(List<ThongKeKMNVGHInfo> listResult);

        void onGetListThongKeKMNVGHError(String error);
    }

    void GetListThongKeKMNVGHCuuLong(String ngayBD, String ngayKT,  IOnGetListThongKeKMNVGHCuuLongFinishedListener listener);

    interface IOnGetListThongKeKMNVGHCuuLongFinishedListener {
        void onGetListThongKeKMNVGHCuuLongSuccess(List<ThongKeKMNVGHInfo> listResult);

        void onGetListThongKeKMNVGHCuuLongError(String error);
    }

    void GetListLuongNVGHTheoKM(String ngayBD, String ngayKT, String maNV,  IOnGetListLuongNVGHTheoKMFinishedListener listener);

    interface IOnGetListLuongNVGHTheoKMFinishedListener {
        void onGetListLuongNVGHTheoKMSuccess(List<LuongNVGHTheoKMInfo> listResult);

        void onGetListLuongNVGHTheoKMError(String error);
    }


    void GetListLuongNVGHTheoKMCuuLong(String ngayBD, String ngayKT, String maNV,  IOnGetListLuongNVGHTheoKMCuuLongFinishedListener listener);

    interface IOnGetListLuongNVGHTheoKMCuuLongFinishedListener {
        void onGetListLuongNVGHTheoKMCuuLongSuccess(List<LuongNVGHTheoKMInfo> listResult);

        void onGetListLuongNVGHTheoKMCuuLongError(String error);
    }


}
