package com.bongmai.tiha.ui.danhmuc.product.list;



import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;

import java.util.List;

public interface ProductListContract {
    interface View {
        void onGetListProductSuccess(List<ProductInfo> listResult, long total);

        void onGetListProductError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListProduct(ProductCondition condition);

        void GetHinhAnhByProductID(String productID, IOnGetHinhAnhByProductIDFinishedListener listener);

        interface IOnGetHinhAnhByProductIDFinishedListener {
            void onSuccess(String stringResult);

            void onError(String error);
        }
    }
}
