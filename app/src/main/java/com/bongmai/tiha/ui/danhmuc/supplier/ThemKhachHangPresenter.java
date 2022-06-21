package com.bongmai.tiha.ui.danhmuc.supplier;

import android.text.TextUtils;

import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.common.CommonModel;
import com.bongmai.tiha.data.network.common.ICommonModel;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;

import java.util.List;

public class ThemKhachHangPresenter implements ThemKhachHangContract.Presenter {
    ThemKhachHangContract.View view;
    SupplierModel supplierModel;
    CommonModel commonModel;


    public ThemKhachHangPresenter(ThemKhachHangContract.View view) {
        this.view = view;
        this.supplierModel = new SupplierModel();
        this.commonModel = new CommonModel();

    }

    @Override
    public void GetSupplier(String supplierID) {
        supplierModel.GetSupplier(supplierID, new ISupplierModel.IOnGetSupplierFinishedListener() {
            @Override
            public void onGetSupplierSuccess(SupplierInfo itemResult) {
                view.onGetSupplierSuccess(itemResult);
            }

            @Override
            public void onGetSupplierError(String error) {
                view.onGetSupplierError(error);
            }
        });
    }

    @Override
    public void InsertSupplier(SupplierInfo supplierInfo) {
        if (TextUtils.isEmpty(supplierInfo.getPhone()) && TextUtils.isEmpty(supplierInfo.getDTDD())
                && TextUtils.isEmpty(supplierInfo.getSoDT1()) && TextUtils.isEmpty(supplierInfo.getSoDT2())) {
            view.onInsertSupplierError("Bạn chưa nhập số điện thoại!");
            return;
        }
        if (TextUtils.isEmpty(supplierInfo.getCompany_Name())) {
            view.onInsertSupplierError("Bạn chưa nhập tên khách hàng!");
            return;
        }
        if (TextUtils.isEmpty(supplierInfo.getPTTT())) {
            view.onInsertSupplierError("Bạn chưa chọn nhóm khách hàng!");
            return;
        }
        supplierModel.InsertSupplier(supplierInfo, new ISupplierModel.IOnInsertSupplierFinishedListener() {
            @Override
            public void onInsertSupplierSuccess(SupplierInfo itemResult) {
                view.onInsertSupplierSuccess(itemResult);
            }

            @Override
            public void onInsertSupplierError(String error) {
                view.onInsertSupplierError(error);
            }
        });
    }

    @Override
    public void UpdateSupplier(SupplierInfo supplierInfo) {
        if (TextUtils.isEmpty(supplierInfo.getPhone()) && TextUtils.isEmpty(supplierInfo.getDTDD())
                && TextUtils.isEmpty(supplierInfo.getSoDT1()) && TextUtils.isEmpty(supplierInfo.getSoDT2())) {
            view.onInsertSupplierError("Bạn chưa nhập số điện thoại!");
            return;
        }
        if (TextUtils.isEmpty(supplierInfo.getCompany_Name())) {
            view.onInsertSupplierError("Bạn chưa nhập tên khách hàng!");
            return;
        }
        if (TextUtils.isEmpty(supplierInfo.getPTTT())) {
            view.onInsertSupplierError("Bạn chưa chọn nhóm khách hàng!");
            return;
        }
        supplierModel.UpdateSupplier(supplierInfo, new ISupplierModel.IOnUpdateSupplierFinishedListener() {
            @Override
            public void onUpdateSupplierSuccess(SupplierInfo itemResult) {
                view.onUpdateSupplierSuccess(itemResult);
            }

            @Override
            public void onUpdateSupplierError(String error) {
                view.onUpdateSupplierError(error);
            }
        });
    }

    @Override
    public void GetListBangGia() {
        commonModel.GetListBangGiaGroup(new ICommonModel.IOnGetListBangGiaGroupFinishedListener() {
            @Override
            public void onSuccess(List<BangGiaGroupInfo> listResult) {
                view.onGetListBangGiaSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListBangGiaError(error);
            }
        });
    }




}
