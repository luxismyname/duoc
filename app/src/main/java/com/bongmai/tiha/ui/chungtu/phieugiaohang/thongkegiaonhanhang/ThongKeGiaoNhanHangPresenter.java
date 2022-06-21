package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang;

import android.text.TextUtils;

import com.bongmai.tiha.data.entities.BMConfigInfo;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuDatHangInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.network.changelog.ChangeLogModel;
import com.bongmai.tiha.data.network.changelog.IChangeLogModel;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.data.network.phieugiaohang.IPhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieugiaohang.PhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.data.network.supplier.SupplierModel;
import com.bongmai.tiha.data.network.trangthai.ITrangThaiModel;
import com.bongmai.tiha.data.network.trangthai.TrangThaiModel;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class ThongKeGiaoNhanHangPresenter implements ThongKeGiaoNhanHangContract.Presenter {
    List<KhoInfo> kholist = new ArrayList<>();
    ThongKeGiaoNhanHangContract.View view;
    PhieuGiaoHangModel phieuGiaoHangModel;
    PhieuXuatModel phieuXuatModel;
    TrangThaiModel trangThaiModel;
    SupplierModel supplierModel;
    ChangeLogModel changeLogModel;
    KhoModel khoModel;
    BMConfigInfo bmConfigInfo = new BMConfigInfo();

    public ThongKeGiaoNhanHangPresenter(ThongKeGiaoNhanHangContract.View view) {
        this.view = view;
        this.phieuGiaoHangModel = new PhieuGiaoHangModel();
        this.phieuXuatModel = new PhieuXuatModel();
        this.trangThaiModel = new TrangThaiModel();
        this.supplierModel = new SupplierModel();
        this.changeLogModel = new ChangeLogModel();
        this.khoModel = new KhoModel();
    }

    @Override
    public void CheckChangeLog() {

        changeLogModel.GetListChangeLog(new IChangeLogModel.IOnGetListChangeLogFinishedListener() {
            @Override
            public void onGetListChangeLogSuccess(ChangeLogInfo changeLogInfo) {
                view.onCheckChangeLogSuccess(changeLogInfo);
            }

            @Override
            public void onGetListChangeLogError(String error) {
                view.onCheckChangeLogError(error);
            }
        });

    }



    @Override
    public void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition) {

        if (TextUtils.isEmpty(condition.getListKho())) {
            condition.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr() + ",101,LIEN");
        }



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

//        if(condition.getListKho().equals("")){
//                khoModel.GetListKhoByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoByUserFinishedListener() {
//                    @Override
//                    public void onSuccess(List<KhoInfo> listResult) {
//                        String kho ="";
//                        for (KhoInfo itemkho : listResult){
//                            kho += itemkho.getMSK() + ",";
//                        }
//                        kho1 = kho;
//                        String finalKho = kho;
//                        khoModel.GetListKhoCuuLongByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoCuuLongByUserFinishedListener() {
//                            @Override
//                            public void onGetListKhoCuuLongByUserSuccess(List<KhoInfo> listResult) {
//                                String kho2 = "";
//                                //kho1 = finalKho;
//                                for(KhoInfo itemkho : listResult){
//                                    kho2 += itemkho.getMSK() + ",";
//                                }
//                                kho2new = kho2;
//                                String finalkho2 = kho2;
//
//                                //view.onGetAllKhoSuccess(kho1, kho2);
//                                condition.setListKho(finalKho+finalkho2);
//
//                            }
//
//                            @Override
//                            public void onGetListKhoCuuLongByUserError(String error) {
//                                view.onGetListKhoCuuLongError(error);
//                            }
//                        });
//
//
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        view.onGetListKhoError(error);
//                    }
//                });

          //  condition.setListKho(kho1 +kho2new);
//        }

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
    public void GetListKho() {
        khoModel.GetListKhoByUser(PublicVariables.nguoiDungInfo.getUserName(), new IKhoModel.IOnGetListKhoByUserFinishedListener() {
            @Override
            public void onSuccess(List<KhoInfo> listResult) {
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
    public void GetPhieuXuatCuuLong(String soCT) {
        phieuXuatModel.GetPhieuXuatCuuLong(soCT, new IPhieuXuatModel.IOnGetPhieuXuatCuuLongFinishedListener() {
            @Override
            public void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult) {
                view.onGetPhieuXuatCuuLongSuccess(phieuXuatResult);
            }

            @Override
            public void onGetPhieuXuatCuuLongError(String error) {
                view.onGetPhieuXuatCuuLongError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuXuat(thongKeGiaoNhanHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuXuatFinishedListener() {
            @Override
            public void onSuccess() {
                view.onUpdateTrangThaiPhieuXuatSuccess(thongKeGiaoNhanHangInfo);
            }

            @Override
            public void onError(String error) {
                view.onUpdateTrangThaiPhieuXuatError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuDatHang(PhieuDatHangInfo phieuDatHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuDatHang(phieuDatHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuDatHangFinishedListener() {
            @Override
            public void onSuccess() {
                view.onUpdateTrangThaiPhieuDatHangSuccess(phieuDatHangInfo);
            }

            @Override
            public void onError(String error) {
                view.onUpdateTrangThaiPhieuDatHangError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuXuatCuuLong(thongKeGiaoNhanHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuXuatCuuLongFinishedListener() {
            @Override
            public void onUpdateTrangThaiPhieuXuatCuuLongSuccess() {
                view.onUpdateTrangThaiPhieuXuatCuuLongSuccess(thongKeGiaoNhanHangInfo);
            }

            @Override
            public void onUpdateTrangThaiPhieuXuatCuuLongError(String error) {
                view.onUpdateTrangThaiPhieuXuatCuuLongError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuDatHangCuuLong(PhieuDatHangInfo phieuDatHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuDatHangCuuLong(phieuDatHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuDatHangCuuLongFinishedListener() {
            @Override
            public void onSuccess() {
                view.onUpdateTrangThaiPhieuDatHangCuuLongSuccess(phieuDatHangInfo);
            }

            @Override
            public void onError(String error) {
                view.onUpdateTrangThaiPhieuDatHangCuuLongError(error);
            }
        });
    }

    @Override
    public void CapNhatToaDoKhachHang(String supplierID, String viDo, String kinhDo) {
        supplierModel.CapNhatToaDoKhachHang(supplierID, viDo, kinhDo, new ISupplierModel.IOnCapNhatToaDoKhachHangFinishedListener() {
            @Override
            public void onSuccess() {
                view.onCapNhatToaDoKhachHangSuccess();
            }

            @Override
            public void onError(String error) {
                view.onCapNhatToaDoKhachHangError(error);
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
    public void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID) {
        phieuGiaoHangModel.CapNhatNhanVienGiaoHang(soPhieu, employeeID, new IPhieuGiaoHangModel.IOnCapNhatNhanVienGiaoHangFinishedListener() {
            @Override
            public void onCapNhatNhanVienGiaoHangSuccess() {
                view.onCapNhatNhanVienGiaoHangSuccess();
            }

            @Override
            public void onCapNhatNhanVienGiaoHangError(String error) {
                view.onCapNhatNhanVienGiaoHangError(error);
            }
        });
    }


    @Override
    public void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID) {
            phieuGiaoHangModel.CapNhatNhanVienGiaoHangCuuLong(soPhieu, employeeID, new IPhieuGiaoHangModel.IOnCapNhatNhanVienGiaoHangCuuLongFinishedListener() {
                @Override
                public void onCapNhatNhanVienGiaoHangCuuLongSuccess() {
                    view.onCapNhatNhanVienGiaoHangCuuLongSuccess();
                }

                @Override
                public void onCapNhatNhanVienGiaoHangCuuLongError(String error) {
                    view.onCapNhatNhanVienGiaoHangCuuLongError(error);
                }
            });
    }

    @Override
    public void CapNhatGhiChu(String soPhieu, String ghiChu) {
        phieuGiaoHangModel.CapNhatGhiChu(soPhieu, ghiChu, new IPhieuGiaoHangModel.IOnCapNhatGhiChuFinishedListener() {
            @Override
            public void onCapNhatGhiChuSuccess() {
                view.onCapNhatGhiChuSuccess();
            }

            @Override
            public void onCapNhatGhiChuError(String error) {
                view.onCapNhatGhiChuError(error);
            }
        });
    }


    @Override
    public void CapNhatGhiChuCuuLong(String soPhieu, String ghiChu) {
        phieuGiaoHangModel.CapNhatGhiChuCuuLong(soPhieu, ghiChu, new IPhieuGiaoHangModel.IOnCapNhatGhiChuCuuLongFinishedListener() {
            @Override
            public void onCapNhatGhiChuCuuLongSuccess() {
                view.onCapNhatGhiChuCuuLongSuccess();
            }

            @Override
            public void onCapNhatGhiChuCuuLongError(String error) {
                view.onCapNhatGhiChuCuuLongError(error);
            }
        });
    }

    @Override
    public void HuyDonHang(String soChungTu, String ghiChu, String userName) {
        phieuGiaoHangModel.HuyDonHang(soChungTu, ghiChu, userName, new IPhieuGiaoHangModel.IOnHuyDonHangFinishedListener() {
            @Override
            public void onHuyDonHangSuccess() {
                view.onHuyDonHangSuccess();
            }

            @Override
            public void onHuyDonHangError(String error) {
                view.onHuyDonHangError(error);
            }
        });
    }


    @Override
    public void HuyDonHangCuuLong(String soChungTu, String ghiChu, String userName) {
        phieuGiaoHangModel.HuyDonHangCuuLong(soChungTu, ghiChu, userName, new IPhieuGiaoHangModel.IOnHuyDonHangCuuLongFinishedListener() {
            @Override
            public void onHuyDonHangCuuLongSuccess() {
                view.onHuyDonHangCuuLongSuccess();
            }

            @Override
            public void onHuyDonHangCuuLongError(String error) {
                view.onHuyDonHangCuuLongError(error);
            }
        });
    }

    @Override
    public void CapNhatThongTinPhieuGiaoHangSai(String soChungTu) {
        phieuGiaoHangModel.CapNhatThongTinPhieuGiaoHangSai(soChungTu, new IPhieuGiaoHangModel.IOnCapNhatThongTinPhieuGiaoHangSaiFinishedListener() {
            @Override
            public void onCapNhatThongTinPhieuGiaoHangSaiSuccess() {
                view.onCapNhatThongTinPhieuGiaoHangSaiSuccess();
            }

            @Override
            public void onCapNhatThongTinPhieuGiaoHangSaiError(String error) {
                view.onCapNhatThongTinPhieuGiaoHangSaiError(error);
            }
        });
    }

    @Override
    public void CapNhatThongTinPhieuGiaoHangSaiCuuLong(String soChungTu) {
        phieuGiaoHangModel.CapNhatThongTinPhieuGiaoHangSaiCuuLong(soChungTu, new IPhieuGiaoHangModel.IOnCapNhatThongTinPhieuGiaoHangSaiCuuLongFinishedListener() {
            @Override
            public void onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess() {
                view.onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess();
            }

            @Override
            public void onCapNhatThongTinPhieuGiaoHangSaiCuuLongError(String error) {
                view.onCapNhatThongTinPhieuGiaoHangSaiError(error);
            }
        });
    }


    @Override
    public void GetListTrangThai(String loaiPhieu, String userName) {
        trangThaiModel.GetListTrangThaiByLoaiPhieuByUser(loaiPhieu, userName, new ITrangThaiModel.IOnGetListTrangThaiByLoaiPhieuByUserFinishedListener() {
            @Override
            public void onGetListTrangThaiByLoaiPhieuByUserSuccess(List<TrangThaiLoaiPhieuInfo> list) {
                view.onGetListTrangThaiSuccess(list);
            }

            @Override
            public void onGetListTrangThaiByLoaiPhieuByUserError(String error) {
                view.onGetListTrangThaiError(error);
            }
        });
    }


//    @Override
//    public void GetListTrangThai() {
//        trangThaiModel.GetListTrangThai(new ITrangThaiModel.IOnGetListTrangThaiFinishedListener() {
//            @Override
//            public void onGetListTrangThaiSuccess(List<TrangThaiInfo> listTrangthai) {
//                view.onGetListTrangThaiSuccess(listTrangthai);
//            }
//
//            @Override
//            public void onGetListTrangThaiError(String error) {
//                view.onGetListTrangThaiError(error);
//            }
//        });
//    }

}
