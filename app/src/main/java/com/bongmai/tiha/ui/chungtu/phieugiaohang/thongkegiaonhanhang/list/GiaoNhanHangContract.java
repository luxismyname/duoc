package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.list;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;

import java.util.List;

public interface GiaoNhanHangContract {

    interface View{
        void onGetListThongKeGiaoNhanHangSuccess(List<ThongKeGiaoNhanHangInfo> listResult);

        void onGetListThongKeGiaoNhanHangError(String error);

        void onGetSupplierSuccess(SupplierInfo itemResult);

        void onGetSupplierError(String error);

        void onGetSupplierCuuLongSuccess(SupplierInfo itemResult);

        void onGetSupplierCuuLongError(String error);

        void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult);

        void onGetListKhoCuuLongError(String error);

        void onGetListKhoSuccess(List<KhoInfo> listResult);

        void onGetListKhoError(String error);


    }


    interface Presenter {

        void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition);

        void GetSupplier(String supplierID);

        void GetSupplierCuuLong(String supplierID);

        void GetListKho();

        void GetListKhoCuuLong();

    }
}
