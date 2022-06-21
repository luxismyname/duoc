package com.bongmai.tiha.ui.danhmuc.product.allproduct;

import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;

import java.util.Date;
import java.util.List;

public interface GetMultipleProductActivityContract {

    interface View {
        void onGetListAllProductSuccess(List<ProductInfo> listResult);

        void onGetListAllProductError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListAllProduct(ProductCondition condition);
    }
}
