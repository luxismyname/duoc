package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat;

import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiContract;

import java.util.Date;
import java.util.List;

public class PhieuBanSiChiTietPhieuXuatPresenter implements PhieuBanSiChiTietPhieuXuatContract.Presenter {

    PhieuBanSiChiTietPhieuXuatContract.View view;
    PhieuXuatModel phieuXuatModel;
    ProductModel productModel;


    public PhieuBanSiChiTietPhieuXuatPresenter(PhieuBanSiChiTietPhieuXuatContract.View view) {
        this.view = view;
        this.phieuXuatModel = new PhieuXuatModel();
        this.productModel = new ProductModel();
    }

    @Override
    public void onGetListVattuXuat(String soCT) {

        phieuXuatModel.GetPhieuXuatChiTiet(soCT, new IPhieuXuatModel.IOnGetPhieuXuatChiTietFinishedListener() {
            @Override
            public void onGetVattuSuccess(List<VattuxuatInfo> list) {
                view.onGetListVattuXuatSuccess(list);
            }

            @Override
            public void onVattuError(String error) {
                view.onGetListVattuXuatError(error);
            }
        });


    }

    @Override
    public void GetProduct(String productID) {
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
    public void GetProduct(String productID, final PhieuBanSiContract.Presenter.IOnGetProductFinishedListener listener) {
        productModel.GetProduct(productID, new IProductModel.IOnGetProductFinishedListener() {
            @Override
            public void onGetProductSuccess(ProductInfo itemResult) {
                listener.onSuccess(itemResult);
            }

            @Override
            public void onGetProductError(String error) {
                listener.onError(error);
            }
        });
    }


}
