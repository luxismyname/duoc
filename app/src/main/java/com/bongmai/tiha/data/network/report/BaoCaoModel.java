package com.bongmai.tiha.data.network.report;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.ChiTietNhapHangInfo;
import com.bongmai.tiha.data.entities.ChiTietXuatHangInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiThuInfo;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiTraInfo;
import com.bongmai.tiha.data.entities.DKNhapXuatTonVoRongListKhoKTG;
import com.bongmai.tiha.data.entities.DKNhatXuatNuocALLVoRongInfo;
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DanhSachNhapInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.data.entities.DoanhSoNhanVienInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.MobileDetailMauInfo;
import com.bongmai.tiha.data.entities.MobileMauInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.text.MessageFormat;
import java.util.Map;


public class BaoCaoModel implements IBaoCaoModel {
    APIService service;

    @Override
    public void GetListNhomBaoCao(final IOnGetListNhomBaoCaoFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListNhomBaoCao, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new MobileMauInfo().getListMobileMau(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetListNhomBaoCaoChiTiet(String maNhomBaoCao, IOnGetListNhomBaoCaoChiTietFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListNhomBaoCaoChiTiet, maNhomBaoCao, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new MobileDetailMauInfo().getListMobileDetailMau(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetDashboardDoanhThu(DieuKienLocInfo dieuKienLocInfo, IOnGetDashboardDoanhThuFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetDashboardDoanhThu;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DashboardInfo().getDashboard(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetDashboardCuocGoi(DieuKienLocInfo dieuKienLocInfo, IOnGetDashboardCuocGoiFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetDashboardCuocGoi;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DashboardInfo().getDashboard(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListDoanhSoBanHangTheoChiNhanh(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoChiNhanhFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListDoanhSoBanHangTheoChiNhanh;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DoanhSoChiNhanhInfo().getListDoanhSoChiNhanh(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListDoanhSoBanHangTheoKhachHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoKhachHangFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListDoanhSoBanHangTheoKhachHang;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DoanhSoKhachHangInfo().getListDoanhSoKhachHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListDoanhSoBanHangTheoNhanVien(DieuKienLocInfo dieuKienLocInfo, IOnGetListDoanhSoBanHangTheoNhanVienFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListDoanhSoBanHangTheoNhanVien;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DoanhSoNhanVienInfo().getListDoanhSoNhanVien(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListNhatKyBanHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyBanHangFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListNhatKyBanHang;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DanhSachXuatInfo().getListDanhSachXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListNhatKyBanHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyBanHangChiTietFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListNhatKyBanHangChiTiet;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new ChiTietXuatHangInfo().getListChiTietXuatHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListThongKeCuocGoi(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeCuocGoiFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListThongKeCuocGoi;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DanhSachCuocGoiInfo().getListCuocGoi(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListCongNoTongHop(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListCongNoTongHop;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new CongNoTongHopInfo().getListCongNoTongHop(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListCongNoTongHopPhaiThu(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiThuFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListCongNoTongHopPhaiThu;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new CongNoTongHopPhaiThuInfo().getListCongNoTongHopPhaiThu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListCongNoTongHopPhaiTra(DieuKienLocInfo dieuKienLocInfo, IOnGetListCongNoTongHopPhaiTraFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListCongNoTongHopPhaiTra;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new CongNoTongHopPhaiTraInfo().getListCongNoTongHopPhaiTra(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListNhatKyNhapHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListNhatKyNhapHang;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DanhSachNhapInfo().getListDanhSachNhap(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListNhatKyNhapHangChiTiet(DieuKienLocInfo dieuKienLocInfo, IOnGetListNhatKyNhapHangChiTietFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListNhatKyNhapHangChiTiet;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new ChiTietNhapHangInfo().getListChiTietNhapHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListThongKeTonNuocVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocVoRongFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListThongKeTonNuocVoRong;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DKNhatXuatNuocALLVoRongInfo().getListDKNhatXuatNuocALLVoRong(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListThongKeTonVoRong(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonVoRongFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListThongKeTonVoRong;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DKNhapXuatTonVoRongListKhoKTG().getListDKNhapXuatTonVoRongListKhoKTG(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListThongKeTonNuoc(DieuKienLocInfo dieuKienLocInfo, IOnGetListThongKeTonNuocFinishedListener listener) {
        if (dieuKienLocInfo.getListKho().isEmpty()) {
            dieuKienLocInfo.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        if (dieuKienLocInfo.getListChiNhanh().isEmpty()) {
            dieuKienLocInfo.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        }
        dieuKienLocInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        String URL = AppConstants.URL_GetListThongKeTonNuoc;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DKNhatXuatNuocALLVoRongInfo().getListDKNhatXuatNuocALLVoRong(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHang(DieuKienLocInfo dieuKienLocInfo, IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener) {
        String URL = AppConstants.URL_GetListQuangDuongNhanVienGiaoHang;
        Map<String, String> params = dieuKienLocInfo.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new EmployeeGiaoHangInfo().getListEmployeeGiaoHangInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }
}
