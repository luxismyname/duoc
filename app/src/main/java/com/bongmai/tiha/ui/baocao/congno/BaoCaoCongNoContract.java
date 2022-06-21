package com.bongmai.tiha.ui.baocao.congno;



import com.bongmai.tiha.data.entities.CongNoTongHopInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiThuInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiTraInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

import java.util.List;

public interface BaoCaoCongNoContract {
    interface IOnGetListCongNoTongHopFinishedListener {
        void onSuccess(List<CongNoTongHopInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListCongNoTongHopPhaiThuFinishedListener {
        void onSuccess(List<CongNoTongHopPhaiThuInfo> listResult);

        void onError(String error);
    }

    interface IOnGetListCongNoTongHopPhaiTraFinishedListener {
        void onSuccess(List<CongNoTongHopPhaiTraInfo> listResult);

        void onError(String error);
    }

    interface Presenter {
        void GetListCongNoTongHop(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopFinishedListener listener);
        void GetListCongNoTongHopPhaiThu(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiThuFinishedListener listener);
        void GetListCongNoTongHopPhaiTra(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiTraFinishedListener listener);
    }
}
