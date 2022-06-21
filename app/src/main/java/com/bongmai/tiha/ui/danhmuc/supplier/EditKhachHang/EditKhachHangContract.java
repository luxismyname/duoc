package com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang;

import com.bongmai.tiha.data.entities.SupplierInfo;

public interface EditKhachHangContract {
     interface View{

         void onGetSupplierSuccess(SupplierInfo supplierInfo);
         void onGetSupplierError(String error);
         void onDeleteSuccess();

         void onDeleteError(String error);


     }

     interface Presenter{
         void getSupplier(String supplierID);
         void DeleteSupplier(SupplierInfo supplierInfo);
     }

}
