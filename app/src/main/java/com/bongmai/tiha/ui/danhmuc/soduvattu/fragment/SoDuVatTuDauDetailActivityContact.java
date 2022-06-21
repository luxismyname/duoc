package com.bongmai.tiha.ui.danhmuc.soduvattu.fragment;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;

public interface SoDuVatTuDauDetailActivityContact {
    interface View {
        void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult);

        void onGetSoDuVatTuDauError(String error);

        void onDeleteSuccess();

        void onDeleteError(String error);
    }

    interface Presenter {

        void GetSoDuVatTuDau(String soduvattudauID);

        void DeleteSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo);
    }
}
