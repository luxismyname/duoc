package com.bongmai.tiha.ui.chungtu.phieuxuat;

import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;


import java.util.Date;
import java.util.List;


public interface PhieuBanSiContract {
    interface View {

        void onInsertPhieuXuatSuccess(PhieuXuatInfo itemResult);

        void onInsertPhieuXuatError(String error);

        void onUpdatePhieuXuatSuccess(PhieuXuatInfo itemResult);

        void onUpdatePhieuXuatError(String error);

        void onKiemTraDuocXuatHangSuccess();

        void onKiemTraDuocXuatHangError(String error);

        void onGetSupplierSuccess(SupplierInfo itemResult);

        void onGetSupplierError(String error);

        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);

        void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult);

        void onGetPhieuXuatError(String error);

        void onGetListVattuXuatSuccess(List<VattuxuatInfo> vattuxuatInfos);

        void onGetListVattuXuatError(String error);


        void onGetKhoSuccess(KhoInfo itemResult);

        void onGetKhoError(String error);

        void onGetEmployeeSuccess(EmployeeInfo itemResult, String loai);

        void onGetEmployeeError(String error, String loai);


    }

    interface Presenter {

        void InsertPhieuXuat(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listVattuxuat);

        void UpdatePhieuXuat(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listVattuxuat);

        void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong);

        void GetSupplier(String supplierID);

        void GetProduct(String productID);

        void GetProduct(String productID, IOnGetProductFinishedListener listener);

        interface IOnGetProductFinishedListener {
            void onSuccess(ProductInfo itemResult);

            void onError(String error);
        }

        void GetPhieuXuat(String soCT);
        void GetListVattuXuat(String soCT);

        void GetKho(String maKho);

        void GetEmployee(String employeeID, String loai);


    }
}
