package com.bongmai.tiha.data.network.report;

import com.bongmai.tiha.data.entities.ChiTietNhapHangInfo;
import com.bongmai.tiha.data.entities.ChiTietXuatHangInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiThuInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiTraInfo;
import com.bongmai.tiha.data.entities.DKNhapXuatTonVoRongListKhoKTG;
import com.bongmai.tiha.data.entities.DKNhatXuatNuocALLVoRongInfo;
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DanhSachNhapInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.data.entities.DoanhSoNhanVienInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.MobileDetailMauInfo;
import com.bongmai.tiha.data.entities.MobileMauInfo;

import java.util.List;


public interface IBaoCaoModel {
    void GetListNhomBaoCao(IOnGetListNhomBaoCaoFinishedListener listener);

    interface IOnGetListNhomBaoCaoFinishedListener {
        void onSuccess(List<MobileMauInfo> listNhomBaoCao);

        void onError(String error);
    }

    void GetListNhomBaoCaoChiTiet(String maNhomBaoCao, IOnGetListNhomBaoCaoChiTietFinishedListener listener);

    interface IOnGetListNhomBaoCaoChiTietFinishedListener {
        void onSuccess(List<MobileDetailMauInfo> listNhomBaoCaoChiTiet);

        void onError(String error);
    }

    void GetDashboardDoanhThu(DieuKienLocInfo dieuKienLocInfo, IOnGetDashboardDoanhThuFinishedListener listener);

    interface IOnGetDashboardDoanhThuFinishedListener {
        void onSuccess(DashboardInfo dashboardInfo);

        void onError(String error);
    }

    void GetDashboardCuocGoi(DieuKienLocInfo dieuKienLocInfo, IOnGetDashboardCuocGoiFinishedListener listener);

    interface IOnGetDashboardCuocGoiFinishedListener {
        void onSuccess(DashboardInfo dashboardInfo);

        void onError(String error);
    }

    void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener);

    interface IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener {
        void onSuccess(List<DoanhSoChiNhanhInfo> listResult);

        void onError(String error);
    }

    void GetListDoanhSoBanHangTheoKhachHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener listener);

    interface IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener {
        void onSuccess(List<DoanhSoKhachHangInfo> listResult);

        void onError(String error);
    }

    void GetListDoanhSoBanHangTheoNhanVien(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener listener);

    interface IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener {
        void onSuccess(List<DoanhSoNhanVienInfo> listResult);

        void onError(String error);
    }

    void GetListNhatKyBanHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyBanHangFinishedListener listener);

    interface IOnGetListNhatKyBanHangFinishedListener {
        void onSuccess(List<DanhSachXuatInfo> listResult);

        void onError(String error);
    }

    void GetListNhatKyBanHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyBanHangChiTietFinishedListener listener);

    interface IOnGetListNhatKyBanHangChiTietFinishedListener {
        void onSuccess(List<ChiTietXuatHangInfo> listResult);

        void onError(String error);
    }

    void GetListThongKeCuocGoi(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeCuocGoiFinishedListener listener);

    interface IOnGetListThongKeCuocGoiFinishedListener {
        void onSuccess(List<DanhSachCuocGoiInfo> listResult);

        void onError(String error);
    }

    void GetListCongNoTongHop(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopFinishedListener listener);

    interface IOnGetListCongNoTongHopFinishedListener {
        void onSuccess(List<CongNoTongHopInfo> listResult);

        void onError(String error);
    }

    void GetListCongNoTongHopPhaiThu(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiThuFinishedListener listener);

    interface IOnGetListCongNoTongHopPhaiThuFinishedListener {
        void onSuccess(List<CongNoTongHopPhaiThuInfo> listResult);

        void onError(String error);
    }

    void GetListCongNoTongHopPhaiTra(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiTraFinishedListener listener);

    interface IOnGetListCongNoTongHopPhaiTraFinishedListener {
        void onSuccess(List<CongNoTongHopPhaiTraInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListNhatKyNhapHangFinishedListener {
        void onSuccess(List<DanhSachNhapInfo> listResult);

        void onError(String error);
    }

    void GetListNhatKyNhapHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangFinishedListener listener);

    interface IOnGetListNhatKyNhapHangChiTietFinishedListener {
        void onSuccess(List<ChiTietNhapHangInfo> listResult);

        void onError(String error);
    }

    void GetListNhatKyNhapHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangChiTietFinishedListener listener);

    interface IOnGetListThongKeTonNuocVoRongFinishedListener {
        void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult);

        void onError(String error);
    }

    void GetListThongKeTonNuocVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocVoRongFinishedListener listener);

    interface IOnGetListThongKeTonVoRongFinishedListener {
        void onSuccess(List<DKNhapXuatTonVoRongListKhoKTG> listResult);

        void onError(String error);
    }

    void GetListThongKeTonVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonVoRongFinishedListener listener);

    interface IOnGetListThongKeTonNuocFinishedListener {
        void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult);

        void onError(String error);
    }

    void GetListThongKeTonNuoc(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocFinishedListener listener);


    void GetListQuangDuongNhanVienGiaoHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener);

    interface IOnGetListQuangDuongNhanVienGiaoHangFinishedListener {
        void onSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onError(String error);
    }

}
