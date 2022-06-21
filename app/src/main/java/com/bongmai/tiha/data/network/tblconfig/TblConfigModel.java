package com.bongmai.tiha.data.network.tblconfig;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.TblConfigInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

import java.text.MessageFormat;

public class TblConfigModel implements ITblConfigModel {

    APIService service;
    APIServiceDuocCuuLong clService;


    @Override
    public void GetTblConfig(String userName, IOnGetTblConfigFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetTblConfig, userName);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new TblConfigInfo().getTblConfig(response));

            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetTblConfigCuuLong(String userName, IOnGetTblConfigCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetTblConfig, userName);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new TblConfigInfo().getTblConfig(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
