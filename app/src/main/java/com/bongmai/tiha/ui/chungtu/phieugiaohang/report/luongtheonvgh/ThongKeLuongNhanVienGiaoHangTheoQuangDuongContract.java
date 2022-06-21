package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.luongtheonvgh;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;

import java.util.List;

public interface ThongKeLuongNhanVienGiaoHangTheoQuangDuongContract {

    interface View{

        void onThongKeLuongNhanVienGiaoHangTheoQuangDuongSuccess(List<LuongNVGHTheoKMInfo> listResult);

        void onThongKeLuongNhanVienGiaoHangTheoQuangDuongError(String error);

        void onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongSuccess(List<LuongNVGHTheoKMInfo> listResult);

        void onThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLongError(String error);

    }

    interface Presenter {

        void ThongKeLuongNhanVienGiaoHangTheoQuangDuong(String ngayBD, String ngayKT, String maNV);

        void ThongKeLuongNhanVienGiaoHangTheoQuangDuongCuuLong(String ngayBD, String ngayKT, String maNV);

    }
}
