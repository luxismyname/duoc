package com.bongmai.tiha.data.network.loaihang;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class LoaiHangModel implements ILoaiHangModel {

    APIService service;

    @Override
    public void GetListLoaiHangByUser(String userName, IOnGetListLoaiHangByUserFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListLoaiHangByUser, userName);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new LoaiHangInfo().getListLoaiHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetLoaiHang(String categoryID, final IOnGetLoaiHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetLoaiHang, categoryID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new LoaiHangInfo().getLoaiHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void InsertLoaiHang(LoaiHangInfo loaiHangInfo, final IOnInsertLoaiHangFinishedListener listener) {
        String URL = AppConstants.URL_InsertLoaiHang;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        params.put("MaLoaiHang", loaiHangInfo.getCategory_ID());
        params.put("TenLoaiHang", loaiHangInfo.getLoaihang1());
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new LoaiHangInfo().getLoaiHang(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }
}
