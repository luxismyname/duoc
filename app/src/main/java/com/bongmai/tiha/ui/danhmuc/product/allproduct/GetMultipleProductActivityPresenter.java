package com.bongmai.tiha.ui.danhmuc.product.allproduct;

import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;

import java.util.List;

public class GetMultipleProductActivityPresenter implements GetMultipleProductActivityContract.Presenter{

    GetMultipleProductActivityContract.View view;
    ProductModel productModel;

    public GetMultipleProductActivityPresenter(GetMultipleProductActivityContract.View view) {
        this.view = view;
        this.productModel = new ProductModel();
    }

    @Override
    public void GetListAllProduct(ProductCondition condition) {
        productModel.GetListAllProduct(condition, new IProductModel.IOnGetListAllProductFinishedListener() {
            @Override
            public void onGetListAllProductSuccess(List<ProductInfo> listProduct) {
                view.onGetListAllProductSuccess(listProduct);
            }

            @Override
            public void onGetListAllProductError(String error) {
                view.onGetListAllProductError(error);
            }
        });
    }


}
