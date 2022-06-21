package com.bongmai.tiha.ui.baocao.tonkho;



import com.bongmai.tiha.data.entities.ChiTietNhapHangInfo;
import com.bongmai.tiha.data.entities.DKNhapXuatTonVoRongListKhoKTG;
import com.bongmai.tiha.data.entities.DKNhatXuatNuocALLVoRongInfo;
import com.bongmai.tiha.data.entities.DanhSachNhapInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

import java.util.List;

public class BaoCaoTonKhoPresenter implements BaoCaoTonKhoContract.Presenter {
    BaoCaoModel baoCaoModel;

    public BaoCaoTonKhoPresenter() {
        baoCaoModel = new BaoCaoModel();
    }


    @Override
    public void GetListNhatKyNhapHang(DieuKienLocInfo dieuKienLocInfo, final BaoCaoTonKhoContract.IOnGetListNhatKyNhapHangFinishedListener listener) {
        baoCaoModel.GetListNhatKyNhapHang(dieuKienLocInfo, new IBaoCaoModel.IOnGetListNhatKyNhapHangFinishedListener() {
            @Override
            public void onSuccess(List<DanhSachNhapInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListNhatKyNhapHangChiTiet(DieuKienLocInfo dieuKienLocInfo, final BaoCaoTonKhoContract.IOnGetListNhatKyNhapHangChiTietFinishedListener listener) {
        baoCaoModel.GetListNhatKyNhapHangChiTiet(dieuKienLocInfo, new IBaoCaoModel.IOnGetListNhatKyNhapHangChiTietFinishedListener() {
            @Override
            public void onSuccess(List<ChiTietNhapHangInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListThongKeTonNuocVoRong(DieuKienLocInfo dieuKienLocInfo, final BaoCaoTonKhoContract.IOnGetListThongKeTonNuocVoRongFinishedListener listener) {
        baoCaoModel.GetListThongKeTonNuocVoRong(dieuKienLocInfo, new IBaoCaoModel.IOnGetListThongKeTonNuocVoRongFinishedListener() {
            @Override
            public void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListThongKeTonVoRong(DieuKienLocInfo dieuKienLocInfo, final BaoCaoTonKhoContract.IOnGetListThongKeTonVoRongFinishedListener listener) {
        baoCaoModel.GetListThongKeTonVoRong(dieuKienLocInfo, new IBaoCaoModel.IOnGetListThongKeTonVoRongFinishedListener() {
            @Override
            public void onSuccess(List<DKNhapXuatTonVoRongListKhoKTG> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void GetListThongKeTonNuoc(DieuKienLocInfo dieuKienLocInfo,final BaoCaoTonKhoContract.IOnGetListThongKeTonNuocFinishedListener listener) {
        baoCaoModel.GetListThongKeTonNuoc(dieuKienLocInfo, new IBaoCaoModel.IOnGetListThongKeTonNuocFinishedListener() {
            @Override
            public void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult) {
                listener.onSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }
}
