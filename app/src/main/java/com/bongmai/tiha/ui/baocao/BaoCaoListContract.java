package com.bongmai.tiha.ui.baocao;

import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.data.entities.MobileMauInfo;

import java.util.List;


public interface BaoCaoListContract {
    interface View {
        void onGetListNhomBaoCaoSuccess(List<MobileMauInfo> listResult);

        void onGetListNhomBaoCaoError(String error);
    }

    interface IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener{
        void onSuccess(List<DoanhSoChiNhanhInfo> listResult);

        void onError(String error);
    }

    interface Presenter {
        void GetListNhomBaoCao();
        void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener);
    }
}
