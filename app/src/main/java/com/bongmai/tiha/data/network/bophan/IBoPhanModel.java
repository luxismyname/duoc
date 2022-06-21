package com.bongmai.tiha.data.network.bophan;

import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.data.network.nhomkhachhang.INhomKhachHangModel;

import java.util.List;

public interface IBoPhanModel {

    void GetListBoPhan(IBoPhanModel.IOnGetListBoPhanFinishedListener listener);

    interface IOnGetListBoPhanFinishedListener {
        void onGetListBoPhanSuccess(List<BoPhanInfo> listResult);

        void onGetListBoPhanError(String error);
    }
}
