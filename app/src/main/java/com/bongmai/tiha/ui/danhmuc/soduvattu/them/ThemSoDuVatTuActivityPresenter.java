package com.bongmai.tiha.ui.danhmuc.soduvattu.them;

import android.text.TextUtils;
import android.widget.Spinner;

import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.loaihang.ILoaiHangModel;
import com.bongmai.tiha.data.network.loaihang.LoaiHangModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;
import com.bongmai.tiha.data.network.soduvattudau.ISoDuVatTuDauModel;
import com.bongmai.tiha.data.network.soduvattudau.SoDuVatTuDauModel;

public class ThemSoDuVatTuActivityPresenter implements ThemSoDuVatTuActivityContract.Presenter {

    ThemSoDuVatTuActivityContract.View view;
    SoDuVatTuDauModel soDuVatTuDauModel;
    ProductModel productModel;
    LoaiHangModel loaiHangModel;

    public ThemSoDuVatTuActivityPresenter(ThemSoDuVatTuActivityContract.View view) {
        this.view = view;
        this.soDuVatTuDauModel = new SoDuVatTuDauModel();
        this.productModel = new ProductModel();
        this.loaiHangModel = new LoaiHangModel();
    }


    @Override
    public void GetSoDuVatTuDau(String soduvattudauID) {
        soDuVatTuDauModel.GetSoDuVatTuDau(soduvattudauID, new ISoDuVatTuDauModel.IOnGetSoDuVatTuDauFinishedListener() {
            @Override
            public void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {
                view.onGetSoDuVatTuDauSuccess(itemResult);
            }

            @Override
            public void onGetSoDuVatTuDauError(String error) {
                view.onGetSoDuVatTuDauError(error);
            }
        });
    }

    @Override
    public void InsertSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo) {


        if(TextUtils.isEmpty(soDuVatTuDauInfo.getTenKho())){
            view.onInsertSoDuVatTuError("Chưa chọn kho");
            return;
        }

        if(TextUtils.isEmpty(soDuVatTuDauInfo.getProduct_Name())){
            view.onInsertSoDuVatTuError("Chưa chon sản phẩm!");
            return;
        }

        if(soDuVatTuDauInfo.getSoLuongThuc() == 0){
            view.onInsertSoDuVatTuError("Số lượng phải lớn hơn 0!");
            return;
        }

        if(soDuVatTuDauInfo.getDonGiaThuc() == 0){
            view.onInsertSoDuVatTuError("Đơn giá phải lớn hơn 0!");
            return;
        }

        soDuVatTuDauModel.InsertSoDuVatTuDau(soDuVatTuDauInfo, new ISoDuVatTuDauModel.IOnInsertSoDuVatTuDauFinishedListener() {
            @Override
            public void onInsertSuccess(SoDuVatTuDauInfo itemResult) {
                view.onInsertSoDuVatTuSuccess(itemResult);
            }

            @Override
            public void onInsertError(String error) {
                view.onInsertSoDuVatTuError(error);
            }
        });
    }

    @Override
    public void GetProduct(String productID, final ThemSoDuVatTuActivityContract.Presenter.IOnGetProductFinishedListener listener) {
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

    @Override
    public void UpdateSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo) {
        soDuVatTuDauModel.UpdateSoDuVatTuDau(soDuVatTuDauInfo, new ISoDuVatTuDauModel.IOnUpdateSoDuVatTuDauFinishedListener() {
            @Override
            public void onUpdateSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {
                view.onUpdateSoDuVatTuDauSuccess(itemResult);
            }

            @Override
            public void onUpdateSoDuVatTuDauError(String error) {
                view.onUpdateSoDuVatTuDauError(error);
            }
        });
    }

    @Override
    public void GetProduct(String ProductID) {
        productModel.GetProduct(ProductID, new IProductModel.IOnGetProductFinishedListener() {
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


}
