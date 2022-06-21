package com.bongmai.tiha.data.network.soduvattudau;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class SoDuVatTuDauModel implements ISoDuVatTuDauModel {

    APIService service;

    @Override
    public void GetListSoDuVatTuDau(IOnGetListSoDuVaTuDauFinishedListener listener) {
        String URL = AppConstants.URL_GetListSoDuVatTuDau;
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("Soduvattu", response);
                listener.onGetListSoDuVatTuDauSuccess(new SoDuVatTuDauInfo().getListSoDuVatTu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListSoDuVatTuDauError(AppUtils.getMessageVolleyError(error));
            }
        });

    }

    @Override
    public void GetSoDuVatTuDau(String soDuVatTuDauID, IOnGetSoDuVatTuDauFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetSoDuVatTuDau, soDuVatTuDauID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("vtd", response);
                listener.onGetSoDuVatTuDauSuccess(new SoDuVatTuDauInfo().getSoDuVatTu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetSoDuVatTuDauError(AppUtils.getMessageVolleyError(error));
            }
        });

    }

    @Override
    public void InsertSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo, IOnInsertSoDuVatTuDauFinishedListener listener) {
        String URL = AppConstants.URL_InsertSoDuVatTuDau;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(soDuVatTuDauInfo);
        params.put("ID", String.valueOf(soDuVatTuDauInfo.getID()));
        params.put("MSK", soDuVatTuDauInfo.getMSk());
        params.put("TenKho", soDuVatTuDauInfo.getTenKho());
        params.put("Product_id", soDuVatTuDauInfo.getProduct_id());
        params.put("Product_Name", soDuVatTuDauInfo.getProduct_Name());
        params.put("Ngay", soDuVatTuDauInfo.getNgay());
        params.put("LO", soDuVatTuDauInfo.getLO());
        params.put("DONGIA", String.valueOf(soDuVatTuDauInfo.getDONGIA()));
        params.put("SODUDAU", String.valueOf(soDuVatTuDauInfo.getSODUDAU()));
        params.put("Ngaygio", soDuVatTuDauInfo.getNgaygio());
        params.put("ModifiedDate", soDuVatTuDauInfo.getModifiedDate());
        params.put("ModifiedBy", PublicVariables.nguoiDungInfo.getUserName());
        params.put("TienThue", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getTienThue()));
        params.put("SLNguyen", String.valueOf(soDuVatTuDauInfo.getSLNguyen()));
        params.put("DonGiaNguyen", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getDonGiaNguyen()));
        params.put("Size", soDuVatTuDauInfo.getSize());
        params.put("HanSuDung", soDuVatTuDauInfo.getHanSuDung());
        params.put("TaiKhoan", PublicVariables.nguoiDungInfo.getUserName());
        params.put("SLDVT2", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSLDVT2()));
        params.put("SLDVT3", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSLDVT3()));
        params.put("Nguoigo", PublicVariables.nguoiDungInfo.getUserName());
        params.put("DVT", soDuVatTuDauInfo.getDVT());
        params.put("ProductName", soDuVatTuDauInfo.getProductName());
        params.put("SoLuongThuc", String.valueOf(soDuVatTuDauInfo.getSoLuongThuc()));
        params.put("DonGiaThuc", String.valueOf(soDuVatTuDauInfo.getDonGiaThuc()));
        params.put("ThanhTien", String.valueOf(soDuVatTuDauInfo.getThanhTien()));
        Log.d("json", jsonItemReport);
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onInsertSuccess(new SoDuVatTuDauInfo().getSoDuVatTu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void UpdateSoDuVatTuDau(SoDuVatTuDauInfo soDuVatTuDauInfo, IOnUpdateSoDuVatTuDauFinishedListener listener) {
        String URL = AppConstants.URL_UpdateSoDuVatTuDau;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(soDuVatTuDauInfo);
        params.put("ID", String.valueOf(soDuVatTuDauInfo.getID()));
        params.put("MSK", soDuVatTuDauInfo.getMSk());
        params.put("TenKho", soDuVatTuDauInfo.getTenKho());
        params.put("Product_id", soDuVatTuDauInfo.getProduct_id());
        params.put("Product_Name", soDuVatTuDauInfo.getProduct_Name());
        params.put("Ngay", soDuVatTuDauInfo.getNgay());
        params.put("LO", soDuVatTuDauInfo.getLO());
        params.put("DONGIA", String.valueOf(soDuVatTuDauInfo.getDONGIA()));
        params.put("SODUDAU", String.valueOf(soDuVatTuDauInfo.getSODUDAU()));
        params.put("Ngaygio", soDuVatTuDauInfo.getNgaygio());
        params.put("ModifiedDate", soDuVatTuDauInfo.getModifiedDate());
        params.put("ModifiedBy", PublicVariables.nguoiDungInfo.getUserName());
        params.put("TienThue", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getTienThue()));
        params.put("SLNguyen", String.valueOf(soDuVatTuDauInfo.getSLNguyen()));
        params.put("DonGiaNguyen", String.valueOf(soDuVatTuDauInfo.getDonGiaNguyen()));
        params.put("Size", soDuVatTuDauInfo.getSize());
        params.put("HanSuDung", soDuVatTuDauInfo.getNgay().toString());
        params.put("TaiKhoan", PublicVariables.nguoiDungInfo.getUserName());
        params.put("SLDVT2", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSLDVT2()));
        params.put("SLDVT3", AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSLDVT3()));
        params.put("Nguoigo", PublicVariables.nguoiDungInfo.getUserName());
        params.put("DVT", soDuVatTuDauInfo.getDVT());
        params.put("ProductName", soDuVatTuDauInfo.getProductName());
        params.put("SoLuongThuc", String.valueOf(soDuVatTuDauInfo.getSoLuongThuc()));
        params.put("DonGiaThuc", String.valueOf(soDuVatTuDauInfo.getDonGiaThuc()));
        params.put("ThanhTien", String.valueOf(soDuVatTuDauInfo.getThanhTien()));
        Log.d("jsonUpdate", jsonItemReport);
        service.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onUpdateSoDuVatTuDauSuccess(new SoDuVatTuDauInfo().getSoDuVatTu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onUpdateSoDuVatTuDauError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void DeleteSoDuVatTuDau(String soDuVatTuDauID, IOnDeleteSoDuVatTuDauFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_DeleteSoDuVatTuDau, soDuVatTuDauID, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.Delete(Request.Method.DELETE, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onDeleteSoDuVatTuDauSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onDeleteSoDuVatTuDauError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
