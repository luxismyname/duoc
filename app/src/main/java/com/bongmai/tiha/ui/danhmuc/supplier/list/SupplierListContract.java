package com.bongmai.tiha.ui.danhmuc.supplier.list;


import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;

import java.util.List;

public interface SupplierListContract {
    interface View {
        void onGetListSupplierSuccess(List<SupplierInfo> listResult, long total);

        void onGetListSupplierError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListSupplier(SupplierCondition condition);
    }
}
