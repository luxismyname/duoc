package com.bongmai.tiha.ui.chungtu.phieuxuat.list;

import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiContract;

import java.util.List;

public class PhieuBanSiListPresenter implements PhieuBanSiListContract.Presenter {

    PhieuBanSiListContract.View view;
    PhieuXuatModel phieuXuatModel;


    public PhieuBanSiListPresenter(PhieuBanSiListContract.View view) {
        this.view = view;
        this.phieuXuatModel = new PhieuXuatModel();

    }

    @Override
    public void GetListPhieuXuat(PhieuXuatCondition condition) {
        phieuXuatModel.GetListPhieuXuat(condition, new IPhieuXuatModel.IOnGetListPhieuXuatFinishedListener() {
            @Override
            public void onSuccess(List<DanhSachXuatInfo> listResult) {
                view.onGetListPhieuXuatSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListPhieuXuatError(error);
            }
        });
    }

    @Override
    public void DeletePhieuXuat(String soPhieu, final int position) {
        phieuXuatModel.DeletePhieuXuat(soPhieu, new IPhieuXuatModel.IOnDeletePhieuXuatFinishedListener() {
            @Override
            public void onSuccess() {
                view.onDeletePhieuXuatSuccess(position);
            }

            @Override
            public void onError(String error) {
                view.onDeletePhieuXuatError(error);
            }
        });
    }

    @Override
    public void InQuaInternet(String soPhieu, String computerName) {
        phieuXuatModel.InQuaInternet(soPhieu, computerName, new IPhieuXuatModel.IOnInQuaInternetFinishedListener() {
            @Override
            public void onSuccess() {
                view.onInQuaInternetSuccess();
            }

            @Override
            public void onError(String error) {
                view.onInQuaInternetError(error);
            }
        });
    }


}
