package com.bongmai.tiha.data.network.phieugiaohang;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.PhieuDatHangInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PhieuGiaoHangModel implements IPhieuGiaoHangModel {

    APIService service;
    APIServiceDuocCuuLong cuuLongService;

    @Override
    public void GetListThongKeGiaoNhanHang(PhieuGiaoHangCondition condition, IOnGetListThongKeGiaoNhanHangFinishedListener listener) {
//        PhieuGiaoHangCondition conditionFinal = new PhieuGiaoHangCondition(condition);
//        if (TextUtils.isEmpty(conditionFinal.getListKho())) {
//            conditionFinal.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
//
//        }
//        Map<String, String> params = conditionFinal.GetParameter();
        Map<String, String> params = new HashMap<>();
        params.put("UserName", condition.getUserName());
        params.put("TextSearch", condition.getTextSearch());
        params.put("ListKho", condition.getListKho());
        params.put("NgayBD", condition.getNgayBD());
        params.put("NgayKT", condition.getNgayKT());
        params.put("TrangThai", condition.getTrangThai());

        service = new APIService(AppConstants.URL_GetListThongKeGiaoNhanHang);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("matrangthai", response);
                listener.onSuccess(new ThongKeGiaoNhanHangInfo().getListDanhSachGiaoNhan(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListThongKeGiaoNhanHangCuuLong(PhieuGiaoHangCondition condition, IOnGetListThongKeGiaoNhanHangCuuLongFinishedListener listener) {
//;       PhieuGiaoHangCondition conditionFinal = new PhieuGiaoHangCondition(condition);
//
//        Map<String, String> params = conditionFinal.GetParameter();
//        cuuLongService = new APIServiceDuocCuuLong(AppConstants.URL_GetListThongKeGiaoNhanHang);
//        cuuLongService.DownloadJsonPOST(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("cl2",response);
//                listener.onGetListThongKeGiaoNhanHangCuuLongSuccess(new ThongKeGiaoNhanHangInfo().getListDanhSachGiaoNhan(response));
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                listener.onGetListThongKeGiaoNhanHangCuuLongError(AppUtils.getMessageVolleyError(error));
//            }
//        }, params);
    }

    @Override
    public void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo, IOnUpdateTrangThaiPhieuXuatFinishedListener listener) {
        String URL = AppConstants.URL_UpdateTrangThaiPhieuXuat;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
//        String jsonItem = new Gson().toJson(thongKeGiaoNhanHangInfo);
        params.put("SoChungTu", thongKeGiaoNhanHangInfo.getSoCt());
        params.put("MaTrangThaiHienTai", thongKeGiaoNhanHangInfo.getMaTrangThai());
        params.put("TenTrangThaiHienTai", thongKeGiaoNhanHangInfo.getTenTrangThai());
        params.put("MaTrangThaiCu", thongKeGiaoNhanHangInfo.getMaTrangThaiCu());
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
//        Log.d("Capnhattrangthai", jsonItem);
        service.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("Capnhattrangthai", response);
                listener.onSuccess();

            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void UpdateTrangThaiPhieuDatHang(PhieuDatHangInfo phieuDatHangInfo, IOnUpdateTrangThaiPhieuDatHangFinishedListener listener) {
        String URL = AppConstants.URL_UpdateTrangThaiPhieuDatHang;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
//        String jsonItem = new Gson().toJson(thongKeGiaoNhanHangInfo);
        params.put("SoChungTu", phieuDatHangInfo.getSoCT());
        params.put("MaTrangThaiHienTai", phieuDatHangInfo.getTrangThai());
        params.put("TenTrangThaiHienTai", phieuDatHangInfo.getTenTrangThai());
        params.put("MaTrangThaiCu", phieuDatHangInfo.getMaTrangThaiCu());
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
//        Log.d("Capnhattrangthai", jsonItem);
        service.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("trangthaipdh", response);
                listener.onSuccess();

            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo, IOnUpdateTrangThaiPhieuXuatCuuLongFinishedListener listener) {
        String URL = AppConstants.URL_UpdateTrangThaiPhieuXuat;
        cuuLongService = new APIServiceDuocCuuLong(URL);
        Map<String, String> params = new HashMap<>();
//        String jsonItem = new Gson().toJson(thongKeGiaoNhanHangInfo);
        params.put("SoChungTu", thongKeGiaoNhanHangInfo.getSoCt());
        params.put("MaTrangThaiHienTai", thongKeGiaoNhanHangInfo.getMaTrangThai());
        params.put("TenTrangThaiHienTai", thongKeGiaoNhanHangInfo.getTenTrangThai());
        params.put("MaTrangThaiCu", thongKeGiaoNhanHangInfo.getMaTrangThaiCu());
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
//        Log.d("Capnhattrangthai", jsonItem);
        cuuLongService.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("trangthaicl", response);
                listener.onUpdateTrangThaiPhieuXuatCuuLongSuccess();

            }

            @Override
            public void onError(VolleyError error) {
                listener.onUpdateTrangThaiPhieuXuatCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void UpdateTrangThaiPhieuDatHangCuuLong(PhieuDatHangInfo phieuDatHangInfo, IOnUpdateTrangThaiPhieuDatHangCuuLongFinishedListener listener) {
        String URL = AppConstants.URL_UpdateTrangThaiPhieuDatHang;
        cuuLongService = new APIServiceDuocCuuLong(URL);
        Map<String, String> params = new HashMap<>();
//        String jsonItem = new Gson().toJson(thongKeGiaoNhanHangInfo);
        params.put("SoChungTu", phieuDatHangInfo.getSoCT());
        params.put("MaTrangThaiHienTai", phieuDatHangInfo.getTrangThai());
        params.put("TenTrangThaiHienTai", phieuDatHangInfo.getTenTrangThai());
        params.put("MaTrangThaiCu", phieuDatHangInfo.getMaTrangThaiCu());
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
//        Log.d("Capnhattrangthai", jsonItem);
        cuuLongService.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("trangthaipdhcl", response);
                listener.onSuccess();

            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID, IOnCapNhatNhanVienGiaoHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatNhanVienGiaoHang, soPhieu, employeeID);
        service = new APIService(URL);
        service.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatNhanVienGiaoHangSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatNhanVienGiaoHangError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID, IOnCapNhatNhanVienGiaoHangCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatNhanVienGiaoHang, soPhieu, employeeID);
        cuuLongService = new APIServiceDuocCuuLong(URL);
        cuuLongService.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatNhanVienGiaoHangCuuLongSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatNhanVienGiaoHangCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void CapNhatGhiChu(String soPhieu, String ghiChu, IOnCapNhatGhiChuFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatGhiChu, soPhieu, ghiChu);
        service = new APIService(URL);
        service.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatGhiChuSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatGhiChuError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void CapNhatGhiChuCuuLong(String soPhieu, String ghiChu, IOnCapNhatGhiChuCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatGhiChu, soPhieu, ghiChu);
        cuuLongService = new APIServiceDuocCuuLong(URL);
        cuuLongService.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatGhiChuCuuLongSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatGhiChuCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void HuyDonHang(String soChungTu, String ghiChu, String userName, IOnHuyDonHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_HuyDonHang, soChungTu, ghiChu, userName);
        service = new APIService(URL);
        service.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("huy", response);
                listener.onHuyDonHangSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onHuyDonHangError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void HuyDonHangCuuLong(String soChungTu, String ghiChu, String userName, IOnHuyDonHangCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_HuyDonHang, soChungTu, ghiChu, userName);
        cuuLongService = new APIServiceDuocCuuLong(URL);
        cuuLongService.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("huycl", response);
                listener.onHuyDonHangCuuLongSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onHuyDonHangCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void HoanThanhDonHang(EmployeeGiaoHangInfo employeeGiaoHangInfo, IOnHoanThanhDonHangFinishedListener listener) {
        String URL = AppConstants.URL_HoanThanhDonHang;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(employeeGiaoHangInfo);
        params.put("itemEmployeeGiaoHang", jsonItemReport);
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemKMNVGH", response);
                listener.onHoanThanhDonHangSuccess(new EmployeeGiaoHangInfo().getEmployeeGiaoHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onHoanThanhDonHangError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void HoanThanhDonHangCuuLong(EmployeeGiaoHangInfo employeeGiaoHangInfo, IOnHoanThanhDonHangCuuLongFinishedListener listener) {
        String URL = AppConstants.URL_HoanThanhDonHang;
        cuuLongService = new APIServiceDuocCuuLong(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(employeeGiaoHangInfo);
        params.put("itemEmployeeGiaoHang", jsonItemReport);
        cuuLongService.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemkmcl", response);
                listener.onHoanThanhDonHangCuuLongSuccess(new EmployeeGiaoHangInfo().getEmployeeGiaoHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onHoanThanhDonHangCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void CapNhatThongTinPhieuGiaoHangSai(String soChungTu, IOnCapNhatThongTinPhieuGiaoHangSaiFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatThongTinGiaoHangSai, soChungTu);
        service = new APIService(URL);
        service.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatThongTinPhieuGiaoHangSaiSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatThongTinPhieuGiaoHangSaiError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void CapNhatThongTinPhieuGiaoHangSaiCuuLong(String soChungTu, IOnCapNhatThongTinPhieuGiaoHangSaiCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatThongTinGiaoHangSai, soChungTu);
        cuuLongService = new APIServiceDuocCuuLong(URL);
        cuuLongService.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCapNhatThongTinPhieuGiaoHangSaiCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }


}
