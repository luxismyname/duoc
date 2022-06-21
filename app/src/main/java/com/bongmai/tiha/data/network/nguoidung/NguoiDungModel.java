package com.bongmai.tiha.data.network.nguoidung;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.ChiNhanhInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.NetworkUtils;

import java.util.HashMap;
import java.util.Map;


public class NguoiDungModel implements INguoiDungModel {

    APIService service;

    APIServiceDuocCuuLong clService;

    @Override
    public void CheckLogin(String userName, String passWord, final IOnCheckLoginFinishedListener listener) {
        if (!NetworkUtils.pingGoogle()) {
            listener.onLoginError(AppConstants.Error_KhongCoInternet);
            return;
        }
        String URL = AppConstants.URL_CheckLogin;
        Map<String, String> params = new HashMap<>();
        params.put("UserName", userName);
        params.put("Password", passWord);
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                NguoiDungInfo nguoiDungInfo = new NguoiDungInfo().getNguoiDung(response);
                if (nguoiDungInfo != null && nguoiDungInfo.getListChiNhanh() != null) {
                    String listChiNhanhStr = "";
                    for (ChiNhanhInfo itemChiNhanh : nguoiDungInfo.getListChiNhanh()) {
                        listChiNhanhStr += itemChiNhanh.getChiNhanhID() + ",";
                    }
                    nguoiDungInfo.setListChiNhanhStr(listChiNhanhStr);
                }
                if (nguoiDungInfo != null && nguoiDungInfo.getListKho() != null) {
                    String listKhoStr = "";
                    for (KhoInfo itemKho : nguoiDungInfo.getListKho()) {
                        listKhoStr += itemKho.getMSK() + ",";
                    }
                    nguoiDungInfo.setListKhoStr(listKhoStr);
                }
                listener.onLoginSuccess(nguoiDungInfo);
            }

            @Override
            public void onError(VolleyError error) {
                listener.onLoginError(AppUtils.getMessageVolleyError(error));

            }
        }, params);



    }

    @Override
    public void CheckLoginCuuLong(String userName, String passWord, IOnCheckLoginCuuLongFinishedListener listener) {
        if (!NetworkUtils.pingGoogle()) {
            listener.onCheckLoginCuuLongError(AppConstants.Error_KhongCoInternet);
            return;
        }
        String URL = AppConstants.URL_CheckLogin;
        Map<String, String> params = new HashMap<>();
        params.put("UserName", userName);
        params.put("Password", passWord);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                NguoiDungCuuLongInfo nguoiDungInfo = new NguoiDungCuuLongInfo().getNguoiDungCL(response);
                if (nguoiDungInfo != null && nguoiDungInfo.getListChiNhanh() != null) {
                    String listChiNhanhStr = "";
                    for (ChiNhanhInfo itemChiNhanh : nguoiDungInfo.getListChiNhanh()) {
                        listChiNhanhStr += itemChiNhanh.getChiNhanhID() + ",";
                    }
                    nguoiDungInfo.setListChiNhanhStr(listChiNhanhStr);
                }
                if (nguoiDungInfo != null && nguoiDungInfo.getListKho() != null) {
                    String listKhoStr = "";
                    for (KhoInfo itemKho : nguoiDungInfo.getListKho()) {
                        listKhoStr += itemKho.getMSK() + ",";
                    }
                    nguoiDungInfo.setListKhoStr(listKhoStr);
                }
                Log.d("ngdCL", response);
                listener.onCheckLoginCuuLongSuccess(nguoiDungInfo);
            }

            @Override
            public void onError(VolleyError error) {
                listener.onCheckLoginCuuLongError(AppUtils.getMessageVolleyError(error));

            }
        }, params);
    }


}
