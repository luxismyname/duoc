package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail;

import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;

import java.util.List;

public interface PhieuBanSiDetailContract {

    interface View{

        void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatError(String error);

        void onGetPhieuXuatChiTietSuccess(List<VattuxuatInfo> list);

        void onGetPhieuXuatChiTietError(String error);

    }

    interface Presenter{

        void GetPhieuXuat(String soCT);

        void GetPhieuXuatChitiet(String soCT);

    }


}
