package com.bongmai.tiha.data.network.reportgiaohang;


import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangCondition;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ReportGiaoHangModel  implements IReportGiaoHangModel {

    APIService service;

    APIServiceDuocCuuLong clService;

    @Override
    public void GetListQuangDuongNhanVienGiaoHang(EmployeeGiaoHangCondition condition, IOnGetListQuangDuongNhanVienGiaoHangFinishedListener listener) {
        EmployeeGiaoHangCondition conditionFinal = new EmployeeGiaoHangCondition(condition);
        Map<String, String> params = conditionFinal.GetParameter();
        service = new APIService(AppConstants.URL_GetListQuangDuongNhanVienGiaoHang);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("listquangduong", response);
                listener.onSuccess(new EmployeeGiaoHangInfo().getListEmployeeGiaoHangInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangCuuLong(EmployeeGiaoHangCondition condition, IOnGetListQuangDuongNhanVienGiaoHangCuuLongFinishedListener listener) {
        EmployeeGiaoHangCondition conditionFinal = new EmployeeGiaoHangCondition(condition);
        Map<String, String> params = conditionFinal.GetParameter();
        clService = new APIServiceDuocCuuLong(AppConstants.URL_GetListQuangDuongNhanVienGiaoHang);
        clService.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListQuangDuongNhanVienGiaoHangCuuLongSuccess(new EmployeeGiaoHangInfo().getListEmployeeGiaoHangInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListQuangDuongNhanVienGiaoHangCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        },params);
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangChiTiet(EmployeeGiaoHangCondition condition, IOnGetListQuangDuongNhanVienGiaoHangChiTietFinishedListener listener) {
        EmployeeGiaoHangCondition conditionFinal = new EmployeeGiaoHangCondition(condition);
        Map<String, String> params = conditionFinal.GetParameter();
        service = new APIService(AppConstants.URL_GetListQuangDuongNhanVienGiaoHangChiTiet);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("quangduongchitiet", response);
                listener.onSuccess(new EmployeeGiaoHangInfo().getListEmployeeGiaoHangInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListQuangDuongNhanVienGiaoHangChiTietCuuLong(EmployeeGiaoHangCondition condition, IOnGetListQuangDuongNhanVienGiaoHangChiTietCuuLongFinishedListener listener) {
        EmployeeGiaoHangCondition conditionFinal = new EmployeeGiaoHangCondition(condition);
        Map<String, String> params = conditionFinal.GetParameter();
        clService = new APIServiceDuocCuuLong(AppConstants.URL_GetListQuangDuongNhanVienGiaoHangChiTiet);
        clService.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongSuccess(new EmployeeGiaoHangInfo().getListEmployeeGiaoHangInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListQuangDuongNhanVienGiaoHangChiTietCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        },params);
    }

    @Override
    public void GetListThongKeKMNVGH(String ngayBD, String ngayKT, IOnGetListThongKeKMNVGHFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        String URL = MessageFormat.format(AppConstants.URL_GetListThongKeKMNVGH, ngayBD, ngayKT);
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("thongkeLoi", response);
               listener.onGetListThongKeKMNVGHSuccess(new ThongKeKMNVGHInfo().getListThongKeKMNVGH(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListThongKeKMNVGHError(AppUtils.getMessageVolleyError(error));
            }
        },params);
    }

    @Override
    public void GetListThongKeKMNVGHCuuLong(String ngayBD, String ngayKT, IOnGetListThongKeKMNVGHCuuLongFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        String URL = MessageFormat.format(AppConstants.URL_GetListThongKeKMNVGH, ngayBD, ngayKT);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListThongKeKMNVGHCuuLongSuccess(new ThongKeKMNVGHInfo().getListThongKeKMNVGH(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListThongKeKMNVGHCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        },params);
    }

    @Override
    public void GetListLuongNVGHTheoKM(String ngayBD, String ngayKT, String maNV, IOnGetListLuongNVGHTheoKMFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        String URL = MessageFormat.format(AppConstants.URL_GetListLuongNVGHTheoKM, ngayBD, ngayKT, maNV);
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListLuongNVGHTheoKMSuccess(new LuongNVGHTheoKMInfo().getListLuongNVGHTheoKM(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListLuongNVGHTheoKMError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListLuongNVGHTheoKMCuuLong(String ngayBD, String ngayKT, String maNV, IOnGetListLuongNVGHTheoKMCuuLongFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        String URL = MessageFormat.format(AppConstants.URL_GetListLuongNVGHTheoKM, ngayBD, ngayKT, maNV);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListLuongNVGHTheoKMCuuLongSuccess(new LuongNVGHTheoKMInfo().getListLuongNVGHTheoKM(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListLuongNVGHTheoKMCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }
}
