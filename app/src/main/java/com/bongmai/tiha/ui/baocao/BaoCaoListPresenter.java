package com.bongmai.tiha.ui.baocao;




import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.MobileMauInfo;
import com.bongmai.tiha.data.network.report.BaoCaoModel;
import com.bongmai.tiha.data.network.report.IBaoCaoModel;

import java.util.List;

public class BaoCaoListPresenter implements BaoCaoListContract.Presenter {
    BaoCaoListContract.View view;
    BaoCaoModel baoCaoModel;

    public BaoCaoListPresenter(BaoCaoListContract.View view) {
        this.view = view;
        this.baoCaoModel = new BaoCaoModel();
    }

    @Override
    public void GetListNhomBaoCao() {
        baoCaoModel.GetListNhomBaoCao(new IBaoCaoModel.IOnGetListNhomBaoCaoFinishedListener() {
            @Override
            public void onSuccess(List<MobileMauInfo> listNhomBaoCao) {
                view.onGetListNhomBaoCaoSuccess(listNhomBaoCao);
            }

            @Override
            public void onError(String error) {
                view.onGetListNhomBaoCaoError(error);
            }
        });
    }

    @Override
    public void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo, BaoCaoListContract.IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener) {

    }
}
