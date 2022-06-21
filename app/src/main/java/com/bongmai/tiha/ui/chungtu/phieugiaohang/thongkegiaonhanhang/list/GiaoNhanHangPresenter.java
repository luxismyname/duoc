package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.list;

import android.text.TextUtils;

import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.data.network.phieugiaohang.IPhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieugiaohang.PhieuGiaoHangModel;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang.ThongKeGiaoNhanHangPresenter;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.List;

public class GiaoNhanHangPresenter implements GiaoNhanHangContract.Presenter {
    SupplierModel supplierModel;
    GiaoNhanHangContract.View view;
    PhieuGiaoHangModel phieuGiaoHangModel;
    KhoModel khoModel;
    ThongKeGiaoNhanHangPresenter presenter;
    String kho1 = "", kho2 = "";

    public GiaoNhanHangPresenter(GiaoNhanHangContract.View view) {
        this.view = view;
        this.phieuGiaoHangModel = new PhieuGiaoHangModel();
        this.supplierModel = new SupplierModel();
        this.khoModel = new KhoModel();

    }

    @Override
    public void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition) {
//        if(TextUtils.isEmpty(condition.getListKho())){
//            khoModel.GetListKhoCuuLongByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoCuuLongByUserFinishedListener() {
//                @Override
//                public void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult) {
//                    String kho = "";
//                    for (KhoInfo itemKho : listResult){
//                        kho += itemKho.getMSK() + ",";
//                    }
//                    condition.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr() + kho);
//                }
//
//                @Override
//                public void onGetListKhoCuuLongByUserError(String error) {
//                    view.onGetListKhoError(error);
//                }
//            });
//        }
        if (TextUtils.isEmpty(condition.getListKho())) {
            condition.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr() + ",101,LIEN");
        }
        phieuGiaoHangModel.GetListThongKeGiaoNhanHang(condition, new IPhieuGiaoHangModel.IOnGetListThongKeGiaoNhanHangFinishedListener() {
            @Override
            public void onSuccess(List<ThongKeGiaoNhanHangInfo> listResult) {
                view.onGetListThongKeGiaoNhanHangSuccess(listResult);
            }

            @Override
            public void onError(String error) {
                view.onGetListThongKeGiaoNhanHangError(error);
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
    public void GetSupplierCuuLong(String supplierID) {
        supplierModel.GetSupplierDuocCuuLong(supplierID, new ISupplierModel.IOnGetSupplierDuocCuuLongFinishedListener() {
            @Override
            public void onGetSupplierDuocCuuLongSuccess(SupplierInfo itemResult) {
                view.onGetSupplierCuuLongSuccess(itemResult);
            }

            @Override
            public void onGetSupplierDuocCuuLongError(String error) {
                view.onGetSupplierCuuLongError(error);
            }
        });
    }

    @Override
    public void GetListKho() {
        khoModel.GetListKhoByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoByUserFinishedListener() {
            @Override
            public void onSuccess(List<KhoInfo> listResult) {
                String listkho1 = "";
                for(KhoInfo item : listResult){
                    listkho1 += item.getMSK() + ",";
                }
                kho1 = listkho1;

                view.onGetListKhoSuccess(listResult);


            }

            @Override
            public void onError(String error) {
                view.onGetListKhoError(error);
            }
        });
    }

    @Override
    public void GetListKhoCuuLong() {
        khoModel.GetListKhoCuuLongByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoCuuLongByUserFinishedListener() {
            @Override
            public void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult) {
                view.onGetListKhoCuuLongSuccess(listResult);
            }

            @Override
            public void onGetListKhoCuuLongByUserError(String error) {
                view.onGetListKhoCuuLongError(error);
            }
        });
    }
}
