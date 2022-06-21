package com.bongmai.tiha.ui.hethong.config;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;

import java.util.List;

public interface ConfigContract {
    interface View {

        void onGetSupplierSuccess(SupplierInfo itemResult);
        void onGetSupplierError(String error);

        void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult);
        void onGetListKhoCuuLongError(String error);
    }

    interface Presenter {
        void GetSupplier(String supplierID);

        void GetListKhoCuuLong();
    }
}
