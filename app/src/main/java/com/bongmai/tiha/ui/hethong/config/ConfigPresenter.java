package com.bongmai.tiha.ui.hethong.config;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.List;

public class ConfigPresenter implements ConfigContract.Presenter {

    ConfigContract.View view;
    SupplierModel supplierModel;
    KhoModel khoModel;

    public ConfigPresenter(ConfigContract.View view) {
        this.view = view;
        this.supplierModel = new SupplierModel();
        this.khoModel = new KhoModel();
    }


    @Override
    public void GetSupplier(String supplierID) {
        supplierModel.GetSupplier(supplierID, new ISupplierModel.IOnGetSupplierFinishedListener() {
            @Override
            public void onGetSupplierSuccess(SupplierInfo itemResult) {
                view.onGetSupplierSuccess(itemResult);
            }

            @Override
            public void onGetSupplierError(String error) {
                view.onGetSupplierError(error);
            }
        });
    }

    @Override
    public void GetListKhoCuuLong() {
        khoModel.GetListKhoCuuLongByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoCuuLongByUserFinishedListener() {
            @Override
            public void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult) {
                view.onGetListKhoCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListKhoCuuLongByUserError(String error) {
                view.onGetListKhoCuuLongError(error);
            }
        });
    }


}
