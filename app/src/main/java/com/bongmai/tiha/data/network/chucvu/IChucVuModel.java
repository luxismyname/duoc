package com.bongmai.tiha.data.network.chucvu;

import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.ChucVuInfo;
import com.bongmai.tiha.data.network.bophan.IBoPhanModel;

import java.util.List;

public interface IChucVuModel {

    void GetListChucVu(IChucVuModel.IOnGetListChucVuFinishedListener listener);

    interface IOnGetListChucVuFinishedListener {
        void onGetListChucVuSuccess(List<ChucVuInfo> listResult);

        void onGetListChucVuError(String error);
    }
}
