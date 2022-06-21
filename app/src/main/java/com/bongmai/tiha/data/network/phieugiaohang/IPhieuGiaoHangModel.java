package com.bongmai.tiha.data.network.phieugiaohang;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.PhieuDatHangInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;

import java.util.List;

public interface IPhieuGiaoHangModel {

    void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition, IOnGetListThongKeGiaoNhanHangFinishedListener listener);

    interface IOnGetListThongKeGiaoNhanHangFinishedListener {
        void onSuccess(List<ThongKeGiaoNhanHangInfo> listResult);

        void onError(String error);
    }

    void GetListThongKeGiaoNhanHangCuuLong(PhieuGiaoHangCondition condition, IOnGetListThongKeGiaoNhanHangCuuLongFinishedListener listener);

    interface IOnGetListThongKeGiaoNhanHangCuuLongFinishedListener {
        void onGetListThongKeGiaoNhanHangCuuLongSuccess(List<ThongKeGiaoNhanHangInfo> listResult);

        void onGetListThongKeGiaoNhanHangCuuLongError(String error);
    }

    void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo, IOnUpdateTrangThaiPhieuXuatFinishedListener listener);

    interface IOnUpdateTrangThaiPhieuXuatFinishedListener {
        void onSuccess();

        void onError(String error);
    }

    void UpdateTrangThaiPhieuDatHang(PhieuDatHangInfo phieuDatHangInfo, IOnUpdateTrangThaiPhieuDatHangFinishedListener listener);

    interface IOnUpdateTrangThaiPhieuDatHangFinishedListener {
        void onSuccess();

        void onError(String error);
    }

    void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo, IOnUpdateTrangThaiPhieuXuatCuuLongFinishedListener listener);

    interface IOnUpdateTrangThaiPhieuXuatCuuLongFinishedListener {
        void onUpdateTrangThaiPhieuXuatCuuLongSuccess();

        void onUpdateTrangThaiPhieuXuatCuuLongError(String error);
    }

    void UpdateTrangThaiPhieuDatHangCuuLong(PhieuDatHangInfo phieuDatHangInfo, IOnUpdateTrangThaiPhieuDatHangCuuLongFinishedListener listener);

    interface IOnUpdateTrangThaiPhieuDatHangCuuLongFinishedListener {
        void onSuccess();

        void onError(String error);
    }
    void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID, IOnCapNhatNhanVienGiaoHangFinishedListener listener);

    interface IOnCapNhatNhanVienGiaoHangFinishedListener {
        void onCapNhatNhanVienGiaoHangSuccess();

        void  onCapNhatNhanVienGiaoHangError(String error);
    }

    void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID, IOnCapNhatNhanVienGiaoHangCuuLongFinishedListener listener);

    interface IOnCapNhatNhanVienGiaoHangCuuLongFinishedListener {
        void onCapNhatNhanVienGiaoHangCuuLongSuccess();

        void  onCapNhatNhanVienGiaoHangCuuLongError(String error);
    }

    void CapNhatGhiChu(String soPhieu, String ghiChu, IOnCapNhatGhiChuFinishedListener listener);

    interface IOnCapNhatGhiChuFinishedListener {
        void onCapNhatGhiChuSuccess();

        void  onCapNhatGhiChuError(String error);
    }

    void CapNhatGhiChuCuuLong(String soPhieu, String ghiChu, IOnCapNhatGhiChuCuuLongFinishedListener listener);

    interface IOnCapNhatGhiChuCuuLongFinishedListener {
        void onCapNhatGhiChuCuuLongSuccess();

        void  onCapNhatGhiChuCuuLongError(String error);
    }

    void HuyDonHang(String soChungTu, String ghiChu, String userName, IOnHuyDonHangFinishedListener listener);

    interface IOnHuyDonHangFinishedListener {
        void onHuyDonHangSuccess();

        void  onHuyDonHangError(String error);
    }

    void HuyDonHangCuuLong(String soChungTu, String ghiChu, String userName, IOnHuyDonHangCuuLongFinishedListener listener);

    interface IOnHuyDonHangCuuLongFinishedListener {
        void onHuyDonHangCuuLongSuccess();

        void  onHuyDonHangCuuLongError(String error);
    }




    void HoanThanhDonHang(EmployeeGiaoHangInfo employeeGiaoHangInfo, IOnHoanThanhDonHangFinishedListener listener);

    interface IOnHoanThanhDonHangFinishedListener {
        void onHoanThanhDonHangSuccess(EmployeeGiaoHangInfo itemResult);

        void onHoanThanhDonHangError(String error);
    }

    void HoanThanhDonHangCuuLong(EmployeeGiaoHangInfo employeeGiaoHangInfo, IOnHoanThanhDonHangCuuLongFinishedListener listener);

    interface IOnHoanThanhDonHangCuuLongFinishedListener {
        void onHoanThanhDonHangCuuLongSuccess(EmployeeGiaoHangInfo itemResult);

        void onHoanThanhDonHangCuuLongError(String error);
    }

    void CapNhatThongTinPhieuGiaoHangSai(String soChungTu, IOnCapNhatThongTinPhieuGiaoHangSaiFinishedListener listener);

    interface IOnCapNhatThongTinPhieuGiaoHangSaiFinishedListener {
        void onCapNhatThongTinPhieuGiaoHangSaiSuccess();

        void  onCapNhatThongTinPhieuGiaoHangSaiError(String error);
    }

    void CapNhatThongTinPhieuGiaoHangSaiCuuLong(String soChungTu, IOnCapNhatThongTinPhieuGiaoHangSaiCuuLongFinishedListener listener);

    interface IOnCapNhatThongTinPhieuGiaoHangSaiCuuLongFinishedListener {
        void onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess();

        void  onCapNhatThongTinPhieuGiaoHangSaiCuuLongError(String error);
    }
}
