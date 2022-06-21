package com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang;

import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;

public class EditKhachHangPresenter implements EditKhachHangContract.Presenter {

    SupplierModel supplierModel;
    EditKhachHangContract.View view;

    public EditKhachHangPresenter( EditKhachHangContract.View view) {
        this.supplierModel = new SupplierModel();
        this.view = view;
    }

    @Override
    public void getSupplier(String supplierID) {
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
    public void DeleteSupplier(SupplierInfo supplierInfo) {
        supplierModel.DeleteSupplier(supplierInfo.getSupplier_ID(), new ISupplierModel.IOnDeleteSupplierFinishedListener() {
            @Override
            public void onDeleteSupplierSuccess() {
                view.onDeleteSuccess();
            }

            @Override
            public void onDeleteSupplierError(String error) {
                view.onDeleteError(error);
            }
        });
    }
}
