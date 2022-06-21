package com.bongmai.tiha.ui.baocao.banhang;

import com.bongmai.tiha.data.entities.ChiTietXuatHangInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.data.entities.DoanhSoNhanVienInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;


import java.util.List;

public interface BaoCaoBanHangContract {
    interface IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener{
        void onSuccess(List<DoanhSoChiNhanhInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener {
        void onSuccess(List<DoanhSoKhachHangInfo> listResult);
        void onError(String error);
    }

    interface IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener {
        void onSuccess(List<DoanhSoNhanVienInfo> listResult);
        void onError(String error);
    }

    interface IOnGetListNhatKyBanHangFinishedListener {
        void onSuccess(List<DanhSachXuatInfo> listResult);
        void onError(String error);
    }

    interface IOnGetListNhatKyBanHangChiTietFinishedListener {
        void onSuccess(List<ChiTietXuatHangInfo> listResult);
        void onError(String error);
    }
    interface IOnGetListQuangDuongNhanVienGiaoHangFinishedListener {
        void onSuccess(List<EmployeeGiaoHangInfo> listResult);
        void onError(String error);
    }

    interface Presenter {
        void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener);
        void GetListDoanhSoBanHangTheoKhachHang(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener listener);
        void GetListDoanhSoBanHangTheoNhanVien(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener listener);
        void GetListNhatKyBanHang(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListNhatKyBanHangFinishedListener listener);
        void GetListNhatKyBanHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListNhatKyBanHangChiTietFinishedListener listener);
        void GetListQuangDuongNhanVienGiaoHang(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener);
    }
}
