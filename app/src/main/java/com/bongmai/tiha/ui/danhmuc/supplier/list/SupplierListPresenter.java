package com.bongmai.tiha.ui.danhmuc.supplier.list;

import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;

import java.util.List;

public class SupplierListPresenter implements SupplierListContract.Presenter {
    SupplierListContract.View view;
    SupplierModel supplierModel;

    public SupplierListPresenter(SupplierListContract.View view) {
        this.view = view;
        this.supplierModel = new SupplierModel();
    }

    @Override
    public void GetListSupplier(SupplierCondition condition) {
        supplierModel.GetListSupplier(condition, new ISupplierModel.IOnGetListSupplierFinishedListener() {
            @Override
            public void onGetListSupplierSuccess(List<SupplierInfo> listSupplier, long total) {
                view.onGetListSupplierSuccess(listSupplier, total);
            }

            @Override
            public void onGetListSupplierError(String error) {
                view.onGetListSupplierError(error);
            }
        });
    }
}
