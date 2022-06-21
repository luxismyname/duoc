package com.bongmai.tiha.ui.baocao.banhang;

import com.bongmai.tiha.data.entities.ChiTietXuatHangInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.data.entities.DoanhSoNhanVienInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;


import java.util.List;

public class BaoCaoBanHangPresenter implements BaoCaoBanHangContract.Presenter {
    BaoCaoModel baoCaoModel;

    public BaoCaoBanHangPresenter() {
        baoCaoModel = new BaoCaoModel();
    }

    @Override
    public void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo,
                                                  final BaoCaoBanHangContract.IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener) {
        baoCaoModel.GetListDoanhSoBanHangTheoChiNhanh(dieuKienLocInfo, new IBaoCaoModel.IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener() {
            @Override
            public void onSuccess(List<DoanhSoChiNhanhInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListDoanhSoBanHangTheoKhachHang(DieuKienLocInfo dieuKienLocInfo,
                                                   final IBaoCaoModel.IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener listener) {
        baoCaoModel.GetListDoanhSoBanHangTheoKhachHang(dieuKienLocInfo, new IBaoCaoModel.IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener() {
            @Override
            public void onSuccess(List<DoanhSoKhachHangInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListDoanhSoBanHangTheoNhanVien(DieuKienLocInfo dieuKienLocInfo,
                                                  final IBaoCaoModel.IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener listener) {
        baoCaoModel.GetListDoanhSoBanHangTheoNhanVien(dieuKienLocInfo, new IBaoCaoModel.IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener() {
            @Override
            public void onSuccess(List<DoanhSoNhanVienInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListNhatKyBanHang(DieuKienLocInfo dieuKienLocInfo,
                                     final IBaoCaoModel.IOnGetListNhatKyBanHangFinishedListener listener) {
        baoCaoModel.GetListNhatKyBanHang(dieuKienLocInfo, new IBaoCaoModel.IOnGetListNhatKyBanHangFinishedListener() {
            @Override
            public void onSuccess(List<DanhSachXuatInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListNhatKyBanHangChiTiet(DieuKienLocInfo dieuKienLocInfo,
                                            final IBaoCaoModel.IOnGetListNhatKyBanHangChiTietFinishedListener listener) {
        baoCaoModel.GetListNhatKyBanHangChiTiet(dieuKienLocInfo, new IBaoCaoModel.IOnGetListNhatKyBanHangChiTietFinishedListener() {
            @Override
            public void onSuccess(List<ChiTietXuatHangInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHang(DieuKienLocInfo dieuKienLocInfo, IBaoCaoModel.IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener) {
        baoCaoModel.GetListQuangDuongNhanVienGiaoHang(dieuKienLocInfo, new IBaoCaoModel.IOnGetListQuangDuongNhanVienGiaoHangFinishedListener() {
            @Override
            public void onSuccess(List<EmployeeGiaoHangInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }
}
