package com.bongmai.tiha.ui.chungtu.phieugiaohang.report;

import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;

import java.util.List;

public interface QuangDuongNhanVienGiaoHangContract {

    interface View{

        void onGetListQuangDuongNhanVienGiaoHangSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangError(String error);

        void onGetListQuangDuongNhanVienGiaoHangCuuLongSuccess(List<EmployeeGiaoHangInfo> listResult);

        void onGetListQuangDuongNhanVienGiaoHangCuuLongError(String error);

    }

    interface Presenter {

        void GetListQuangDuongNhanVienGiaoHang(EmployeeGiaoHangCondition condition);

        void GetListQuangDuongNhanVienGiaoHangCuuLong(EmployeeGiaoHangCondition condition);

    }
}
