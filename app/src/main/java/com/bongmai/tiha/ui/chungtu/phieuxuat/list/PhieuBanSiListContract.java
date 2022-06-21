package com.bongmai.tiha.ui.chungtu.phieuxuat.list;

import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiContract;

import java.util.List;

public interface PhieuBanSiListContract {
    interface View{
        void onGetListPhieuXuatSuccess(List<DanhSachXuatInfo> listResult);

        void onGetListPhieuXuatError(String error);

        void onDeletePhieuXuatSuccess(int position);

        void onDeletePhieuXuatError(String error);

        void onInQuaInternetSuccess();

        void onInQuaInternetError(String error);

        void showProgressBar();

        void hideProgressBar();

    }

    interface Presenter{
        void GetListPhieuXuat(PhieuXuatCondition condition);

        void DeletePhieuXuat(String soPhieu, int position);

        void InQuaInternet(String soPhieu, String computerName);


    }
}
