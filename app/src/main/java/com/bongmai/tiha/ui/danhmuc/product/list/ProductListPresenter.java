package com.bongmai.tiha.ui.danhmuc.product.list;

import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter {
    ProductListContract.View view;
    ProductModel productModel;

    public ProductListPresenter(ProductListContract.View view) {
        this.view = view;
        this.productModel = new ProductModel();
    }

    @Override
    public void GetListProduct(ProductCondition condition) {
        productModel.GetListProduct(condition, new IProductModel.IOnGetListProductFinishedListener() {
            @Override
            public void onGetListProductSuccess(List<ProductInfo> listProduct, long total) {
                view.onGetListProductSuccess(listProduct, total);
            }

            @Override
            public void onGetListProductError(String error) {
                view.onGetListProductError(error);
            }
        });
    }

    @Override
    public void GetHinhAnhByProductID(String productID, final IOnGetHinhAnhByProductIDFinishedListener listener) {
        productModel.GetHinhAnhByProductID(productID, new IProductModel.IOnGetHinhAnhByProductIDFinishedListener() {
            @Override
            public void onSuccess(String stringResult) {
                listener.onSuccess(stringResult);
            }

            @Override
            public void onError(String error) {
                listener.onError(error);
            }
        });
    }


}
