package com.bongmai.tiha.ui.baocao.tonkho;

import com.bongmai.tiha.data.entities.ChiTietNhapHangInfo;
import com.bongmai.tiha.data.entities.DKNhapXuatTonVoRongListKhoKTG;
import com.bongmai.tiha.data.entities.DKNhatXuatNuocALLVoRongInfo;
import com.bongmai.tiha.data.entities.DanhSachNhapInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

import java.util.List;

public interface BaoCaoTonKhoContract {
    interface IOnGetListNhatKyNhapHangFinishedListener{
        void onSuccess(List<DanhSachNhapInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListNhatKyNhapHangChiTietFinishedListener{
        void onSuccess(List<ChiTietNhapHangInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListThongKeTonNuocVoRongFinishedListener{
        void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListThongKeTonVoRongFinishedListener{
        void onSuccess(List<DKNhapXuatTonVoRongListKhoKTG> listResult);

        void onError(String error);
    }

    interface IOnGetListThongKeTonNuocFinishedListener{
        void onSuccess(List<DKNhatXuatNuocALLVoRongInfo> listResult);

        void onError(String error);
    }

    interface Presenter {
        void GetListNhatKyNhapHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangFinishedListener listener);
        void GetListNhatKyNhapHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangChiTietFinishedListener listener);
        void GetListThongKeTonNuocVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocVoRongFinishedListener listener);
        void GetListThongKeTonVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonVoRongFinishedListener listener);
        void GetListThongKeTonNuoc(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocFinishedListener listener);
    }
}
