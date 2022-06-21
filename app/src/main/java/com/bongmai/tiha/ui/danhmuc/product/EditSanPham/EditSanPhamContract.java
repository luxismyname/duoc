package com.bongmai.tiha.ui.danhmuc.product.EditSanPham;

import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.utils.PublicVariables;

public interface EditSanPhamContract {


    interface View{

        void onGetProductSuccess(ProductInfo productInfo);
        void onGetProductError(String error);

        void onDeleteSuccess();

        void onDeleteError(String error);

    }

    interface Presenter{
        void getProduct(String productID);
        void DeleteProduct( String supplierID);
    }

}
