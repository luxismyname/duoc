package com.bongmai.tiha.ui.chungtu.phieugiaohang.detail;

import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;

public interface CapNhatTrangThaiContract {

    interface View {

        void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatError(String error);

        void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult);

        void onUpdateTrangThaiPhieuXuatError(String error);
    }
    interface Presenter {

        void GetPhieuXuat(String soCT);

        void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo);
    }
}
