package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat;

import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiContract;

import java.util.Date;
import java.util.List;

public interface PhieuBanSiChiTietPhieuXuatContract {

    interface View {
        void onGetListVattuXuatSuccess(List<VattuxuatInfo> vattuxuatInfos);

        void onGetListVattuXuatError(String error);
        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);

    }



    interface Presenter {
        void onGetListVattuXuat(String soCT);
        void GetProduct(String productID);

        void GetProduct(String productID, PhieuBanSiContract.Presenter.IOnGetProductFinishedListener listener);

        interface IOnGetProductFinishedListener {
            void onSuccess(ProductInfo itemResult);

            void onError(String error);
        }


    }

}
