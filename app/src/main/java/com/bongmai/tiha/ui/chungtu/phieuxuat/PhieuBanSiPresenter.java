package com.bongmai.tiha.ui.chungtu.phieuxuat;


import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.common.CommonModel;
import com.bongmai.tiha.data.network.common.ICommonModel;
import com.bongmai.tiha.data.network.employee.EmployeeModel;
import com.bongmai.tiha.data.network.employee.IEmployeeModel;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;
import com.bongmai.tiha.data.network.product.IProductModel;
import com.bongmai.tiha.data.network.product.ProductModel;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;

import java.util.Date;
import java.util.List;

public class PhieuBanSiPresenter implements PhieuBanSiContract.Presenter {
    PhieuBanSiContract.View view;
    CommonModel commonModel;
    PhieuXuatModel phieuXuatModel;
    SupplierModel supplierModel;
    ProductModel productModel;
    KhoModel khoModel;
    EmployeeModel employeeModel;

    public PhieuBanSiPresenter(PhieuBanSiContract.View view) {
        this.view = view;
        this.commonModel = new CommonModel();
        this.phieuXuatModel = new PhieuXuatModel();
        this.supplierModel = new SupplierModel();
        this.productModel = new ProductModel();
        this.khoModel = new KhoModel();
        this.employeeModel = new EmployeeModel();

    }

    @Override
    public void InsertPhieuXuat(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listVattuxuat) {
        if (listVattuxuat.size() == 0) {
            view.onInsertPhieuXuatError("Bạn chưa chọn sản phẩm nào!");
            return;
        }
        phieuXuatModel.InsertPhieuXuat(phieuXuatInfo, listVattuxuat, new IPhieuXuatModel.IOnInsertPhieuXuatFinishedListener() {
            @Override
            public void onSuccess(PhieuXuatInfo itemResult) {
                view.onInsertPhieuXuatSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onInsertPhieuXuatError(error);
            }
        });
    }

    @Override
    public void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong) {
        commonModel.KiemTraDuocXuatHang(soPhieu, maKho, productID, ngay, soLuong, new ICommonModel.IOnKiemTraDuocXuatHangFinishedListener() {
            @Override
            public void onSuccess() {
                view.onKiemTraDuocXuatHangSuccess();
            }

            @Override
            public void onError(String error) {
                view.onKiemTraDuocXuatHangError(error);
            }
        });
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
    public void GetProduct(String productID, final IOnGetProductFinishedListener listener) {
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
    public void GetPhieuXuat(String soCT) {
        phieuXuatModel.GetPhieuXuat(soCT, new IPhieuXuatModel.IOnGetPhieuXuatFinishedListener() {
            @Override
            public void onSuccess(PhieuXuatInfo phieuXuatResult) {
                view.onGetPhieuXuatSuccess(phieuXuatResult);
            }

            @Override
            public void onError(String error) {
                view.onGetPhieuXuatError(error);
            }
        });




    }

    @Override
    public void GetListVattuXuat(String soCT) {
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
    public void GetKho(String maKho) {
        khoModel.GetKho(maKho, new IKhoModel.IOnGetKhoFinishedListener() {
            @Override
            public void onSuccess(KhoInfo itemResult) {
                view.onGetKhoSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetKhoError(error);
            }
        });
    }

    @Override
    public void GetEmployee(String employeeID, final String loai) {
        employeeModel.GetEmployee(employeeID, new IEmployeeModel.IOnGetEmployeeFinishedListener() {
            @Override
            public void onSuccess(EmployeeInfo itemResult) {
                view.onGetEmployeeSuccess(itemResult, loai);
            }

            @Override
            public void onError(String error) {
                view.onGetEmployeeError(error, loai);
            }
        });
    }

    @Override
    public void UpdatePhieuXuat(PhieuXuatInfo phieuXuatInfo, List<VattuxuatInfo> listVattuxuat) {
        phieuXuatModel.UpdatePhieuXuat(phieuXuatInfo, listVattuxuat, new IPhieuXuatModel.IOnUpdatePhieuXuatFinishedListener() {
            @Override
            public void onSuccess(PhieuXuatInfo itemResult) {
                view.onUpdatePhieuXuatSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onUpdatePhieuXuatError(error);
            }
        });
    }



}
