package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang;

import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuDatHangInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;

import java.util.List;

public interface ThongKeGiaoNhanHangContract {

    interface View{

        void onHuyDonHangSuccess();

        void onHuyDonHangError(String error);

        void onHuyDonHangCuuLongSuccess();

        void onHuyDonHangCuuLongError(String error);

        void onCapNhatGhiChuSuccess();

        void onCapNhatGhiChuError(String error);

        void onCapNhatGhiChuCuuLongSuccess();

        void onCapNhatGhiChuCuuLongError(String error);

        void onCapNhatNhanVienGiaoHangSuccess();

        void onCapNhatNhanVienGiaoHangError(String error);

        void onCapNhatNhanVienGiaoHangCuuLongSuccess();

        void onCapNhatNhanVienGiaoHangCuuLongError(String error);

        void onGetListThongKeGiaoNhanHangSuccess(List<ThongKeGiaoNhanHangInfo> listResult);

        void onGetListThongKeGiaoNhanHangError(String error);

        void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult);

        void onGetListKhoCuuLongError(String error);

        void onGetListKhoSuccess(List<KhoInfo> listResult);

        void onGetListKhoError(String error);

        void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult);

        void onUpdateTrangThaiPhieuXuatError(String error);

        void onUpdateTrangThaiPhieuDatHangSuccess(PhieuDatHangInfo itemResult);

        void onUpdateTrangThaiPhieuDatHangError(String error);

        void onUpdateTrangThaiPhieuXuatCuuLongSuccess(ThongKeGiaoNhanHangInfo itemResult);

        void onUpdateTrangThaiPhieuXuatCuuLongError(String error);

        void onUpdateTrangThaiPhieuDatHangCuuLongSuccess(PhieuDatHangInfo itemResult);

        void onUpdateTrangThaiPhieuDatHangCuuLongError(String error);

        void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatError(String error);

        void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatCuuLongError(String error);

        void onGetListTrangThaiSuccess(List<TrangThaiLoaiPhieuInfo> listResult);

        void onGetListTrangThaiError(String error);

        void onCapNhatToaDoKhachHangSuccess();

        void onCapNhatToaDoKhachHangError(String error);

        void onGetSupplierSuccess(SupplierInfo itemResult);

        void onGetSupplierError(String error);

        void onGetSupplierCuuLongSuccess(SupplierInfo itemResult);

        void onGetSupplierCuuLongError(String error);

        void onCheckChangeLogSuccess(ChangeLogInfo changeLogInfo);

        void onCheckChangeLogError(String error);

        void onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess();

        void onCapNhatThongTinPhieuGiaoHangSaiCuuLongError(String error);

        void onCapNhatThongTinPhieuGiaoHangSaiSuccess();

        void onCapNhatThongTinPhieuGiaoHangSaiError(String error);

        void showProgressBar();

        void hideProgressBar();

//        void onGetAllKhoSuccess(String kho1, String kho2);
//
//        void onGetAllKhoError(String error);



    }

    interface Presenter{

        void GetListTrangThai(String loaiPhieu, String UserName);

        void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition);

        void GetListKho();

        void GetListKhoCuuLong();

        void GetPhieuXuat(String soCT);

        void GetPhieuXuatCuuLong(String soCT);

        void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo);

        void UpdateTrangThaiPhieuDatHang(PhieuDatHangInfo phieuDatHangInfo);

        void CapNhatToaDoKhachHang(String supplierID, String viDo, String kinhDo);

        void GetSupplier(String supplierID);

        void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID);

        void CapNhatGhiChu(String soPhieu, String ghiChu);

        void HuyDonHang(String soChungTu, String ghiChu, String userName);

        void CheckChangeLog();

        void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo);

        void UpdateTrangThaiPhieuDatHangCuuLong(PhieuDatHangInfo phieuDatHangInfo);

        void GetSupplierCuuLong(String supplierID);

        void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID);

        void CapNhatGhiChuCuuLong(String soPhieu, String ghiChu);

        void HuyDonHangCuuLong(String soChungTu, String ghiChu, String userName);

        void CapNhatThongTinPhieuGiaoHangSai(String soChungTu);

        void CapNhatThongTinPhieuGiaoHangSaiCuuLong(String soChungTu);

    }
}
