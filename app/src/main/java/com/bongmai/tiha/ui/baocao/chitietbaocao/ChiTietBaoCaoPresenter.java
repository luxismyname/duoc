package com.bongmai.tiha.ui.baocao.chitietbaocao;




import com.bongmai.tiha.data.entities.MobileDetailMauInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

import java.util.List;

public class ChiTietBaoCaoPresenter implements ChiTietBaoCaoContract.Presenter {
    ChiTietBaoCaoContract.View view;
    BaoCaoModel baoCaoModel;
    public ChiTietBaoCaoPresenter(ChiTietBaoCaoContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
    }


    @Override
    public void GetListNhomBaoCaoChiTiet(String maNhomBaoCao) {
        baoCaoModel.GetListNhomBaoCaoChiTiet(maNhomBaoCao, new IBaoCaoModel.IOnGetListNhomBaoCaoChiTietFinishedListener() {
            @Override
            public void onSuccess(List<MobileDetailMauInfo> listNhomBaoCaoChiTiet) {
                view.onGetListNhomBaoCaoChiTietSuccess(listNhomBaoCaoChiTiet);
            }

            @Override
            public void onError(String error) {
                view.onGetListNhomBaoCaoChiTietError(error);
            }
        });
    }
}
