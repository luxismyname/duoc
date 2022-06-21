package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;

import java.util.List;

public interface ThongKeQuangDuongNhanVienGiaoHangContract {
    interface View{

        void onGetListThongKeKMNVGHSuccess(List<ThongKeKMNVGHInfo> listResult);

        void onGetListThongKeKMNVGHError(String error);

        void onGetListThongKeKMNVGHCuuLongSuccess(List<ThongKeKMNVGHInfo> listResult);

        void onGetListThongKeKMNVGHCuuLongError(String error);

    }

    interface Presenter {

        void GetListThongKeKMNVGH(String ngayBD, String ngayKT);

        void GetListThongKeKMNVGHCuuLong(String ngayBD, String ngayKT);

    }
}
