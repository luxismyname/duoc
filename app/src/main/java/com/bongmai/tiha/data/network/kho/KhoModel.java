package com.bongmai.tiha.data.network.kho;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

import java.text.MessageFormat;


public class KhoModel implements IKhoModel {

    APIService service;

    APIServiceDuocCuuLong clService;

    @Override
    public void GetListKhoByUser(String userName, final IOnGetListKhoByUserFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListKhoByUser, userName);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new KhoInfo().getListKho(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetKho(String maKho, IOnGetKhoFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetKho, maKho);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new KhoInfo().getKho(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetListKhoCuuLongByUser(String userName, IOnGetListKhoCuuLongByUserFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListKhoByUser, userName);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListKhoCuuLongByUserSuccess(new KhoInfo().getListKho(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListKhoCuuLongByUserError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetKhoCuuLong(String maKho, IOnGetKhoCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetKho, maKho);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetKhoCuuLongSuccess(new KhoInfo().getKho(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetKhoCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
