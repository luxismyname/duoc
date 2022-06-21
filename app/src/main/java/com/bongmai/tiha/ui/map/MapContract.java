package com.bongmai.tiha.ui.map;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.RouteInfo;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.TblConfigInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;


import java.util.List;

public interface MapContract {
    interface View {

        void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult);

        void onUpdateTrangThaiPhieuXuatError(String error);

        void onUpdateTrangThaiPhieuXuatCuuLongSuccess(ThongKeGiaoNhanHangInfo itemResult);

        void onUpdateTrangThaiPhieuXuatCuuLongError(String error);

        void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatError(String error);

        void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatCuuLongError(String error);

        void onHoanThanhDonHangSuccess(EmployeeGiaoHangInfo itemResult);

        void onHoanThanhDonHangError(String error);

        void onHoanThanhDonHangCuuLongSuccess(EmployeeGiaoHangInfo itemResult);

        void onHoanThanhDonHangCuuLongError(String error);


        void onDirectionFinderStart();

        void onGetKhoSuccess(KhoInfo khoInfo);

        void onGetKhoError(String error);

        void onGetKhoCuuLongSuccess(KhoInfo khoInfo);

        void onGetKhoCuuLongError(String error);

        void onDowloadDataMapSuccess(List<RouteInfo> listRoute, String link);

        void onDowloadDataMapError(String error);

        void onCapNhatNhanVienGiaoHangSuccess();

        void onCapNhatNhanVienGiaoHangError(String error);

        void onCapNhatNhanVienGiaoHangCuuLongSuccess();

        void onCapNhatNhanVienGiaoHangCuuLongError(String error);

        void onGetTblConfigSuccess(TblConfigInfo itemResult);

        void onGetTblConfigError(String error);

        void onGetTblConfigCuuLongSuccess(TblConfigInfo itemResult);

        void onGetTblConfigCuuLongError(String error);
//
//        void onDowloadDataMapTurnbackSuccess(List<RouteInfo> listRoute, String link);
//
//        void onDowloadDataMapTurnbackError(String error);

    }

    interface Presenter {

        void GetPhieuXuat(String soCT);

        void GetPhieuXuatCuuLong(String soCT);

        void GetKho(String maKho);

        void GetKhoCuuLong(String maKho);

        void DowloadDataMap(String origin, String destination);

//        void DowloadDataMapTurnBack(String destination, String origin);

        void HoanThanhDonHang(EmployeeGiaoHangInfo employeeGiaoHangInfo);

        void HoanThanhDonHangCuuLong(EmployeeGiaoHangInfo employeeGiaoHangInfo);

        void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo);

        void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo);

        void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID);

        void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID);

        void GetTblConfig(String userName);

        void GetTblConfigCuuLong(String userName);

    }
}
