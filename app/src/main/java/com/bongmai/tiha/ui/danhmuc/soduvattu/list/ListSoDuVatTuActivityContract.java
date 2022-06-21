package com.bongmai.tiha.ui.danhmuc.soduvattu.list;

import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;

import java.util.List;

public interface ListSoDuVatTuActivityContract {

    interface View {
        void onGetListSoDuVatTuDauKySuccess(List<SoDuVatTuDauInfo> listResult);

        void onGetListSoDuVatTuDauKyError(String error);

        void onDeleteSuccess();

        void onDeleteError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListSoDuVatTuDauKy();
        
        void DeleteSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo);
    }

}
