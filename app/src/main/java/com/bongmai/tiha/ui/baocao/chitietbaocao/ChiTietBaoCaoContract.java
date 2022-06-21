package com.bongmai.tiha.ui.baocao.chitietbaocao;



import com.bongmai.tiha.data.entities.MobileDetailMauInfo;

import java.util.List;


public interface ChiTietBaoCaoContract {
    interface View {
        void onGetListNhomBaoCaoChiTietSuccess(List<MobileDetailMauInfo> listResult);

        void onGetListNhomBaoCaoChiTietError(String error);
    }

    interface Presenter {

        void GetListNhomBaoCaoChiTiet(String maNhomBaoCao);
    }
}
