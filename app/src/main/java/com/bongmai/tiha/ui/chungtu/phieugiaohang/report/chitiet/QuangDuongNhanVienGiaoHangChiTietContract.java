package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;

import java.util.List;

public interface QuangDuongNhanVienGiaoHangChiTietContract {

    interface View{

        void onGetListQuangDuongNhanVienGiaoHangChiTietSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangChiTietError(String error);

        void onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangCuuLongChiTietError(String error);

    }

    interface Presenter {

        void GetListQuangDuongNhanVienGiaoHangChiTiet(EmployeeGiaoHangCondition condition);

        void GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(EmployeeGiaoHangCondition condition);

    }
}

