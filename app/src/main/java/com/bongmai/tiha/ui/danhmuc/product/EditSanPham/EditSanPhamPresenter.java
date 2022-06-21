package com.bongmai.tiha.ui.danhmuc.product.EditSanPham;

import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;
import com.bongmai.tiha.utils.PublicVariables;

public class EditSanPhamPresenter implements EditSanPhamContract.Presenter {

    ProductModel productModel;
    EditSanPhamContract.View view;
    SupplierModel supplierModel;

    public EditSanPhamPresenter(EditSanPhamContract.View view) {
        this.productModel = new ProductModel();
        this.view = view;
        this.supplierModel = new SupplierModel();
    }

    @Override
    public void getProduct(String productID) {

        productModel.GetProduct(productID, new IProductModel.IOnGetProductFinishedListener() {
            @Override
            public void onGetProductSuccess(ProductInfo itemResult) {
                view.onGetProductSuccess(itemResult);
            }

            @Override
            public void onGetProductError(String error) {
                view.onGetProductError(error);
            }
        });

    }

    @Override
    public void DeleteProduct(String supplierID) {
        productModel.DeleteProduct( supplierID, new IProductModel.IOnDeleteProductFinishedListener() {
            @Override
            public void onDeleteProductSuccess() {
                view.onDeleteSuccess();
            }

            @Override
            public void onDeleteProductError(String error) {
                view.onDeleteError(error);
            }
        });
    }

//    @Override
//    public void DeleteProduct(final ProductInfo productInfo) {
//
//        if(productModel == null || view == null) return;
////        productModel.DeleteProduct(productInfo.getProduct_ID(), new IProductModel.IOnDeleteProductFinishedListener() {
////            @Override
////            public void onDeleteProductSuccess() {
////                view.onDeleteSuccess();
////            }
////
////            @Override
////            public void onDeleteProductError(String error) {
////                view.onDeleteError(error);
////            }
////        });
//
//
//
//    }
}
