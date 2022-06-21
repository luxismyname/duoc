package com.bongmai.tiha.ui.danhmuc.soduvattu.them;

import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;

public interface ThemSoDuVatTuActivityContract {

    interface View{
        void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult);

        void onGetSoDuVatTuDauError(String error);

        void onInsertSoDuVatTuSuccess(SoDuVatTuDauInfo itemResult);

        void onInsertSoDuVatTuError(String error);

        void onUpdateSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult);

        void onUpdateSoDuVatTuDauError(String error);

        void onGetProductSuccess(ProductInfo itemResult);

        void onGetProductError(String error);



    }

    interface Presenter {

        void GetSoDuVatTuDau(String soduvattudauID);

        void InsertSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo);
        void GetProduct(String productID, ThemSoDuVatTuActivityContract.Presenter.IOnGetProductFinishedListener listener);

        interface IOnGetProductFinishedListener {
            void onSuccess(ProductInfo itemResult);

            void onError(String error);
        }

        void UpdateSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo);

        void GetProduct(String ProductID);

    }
}
