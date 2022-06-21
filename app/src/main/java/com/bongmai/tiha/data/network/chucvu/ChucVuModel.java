package com.bongmai.tiha.data.network.chucvu;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.ChucVuInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

public class ChucVuModel implements IChucVuModel {

    APIService service;

    @Override
    public void GetListChucVu(IOnGetListChucVuFinishedListener listener) {
        String URL = AppConstants.URL_GetListChucVu;
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemChucVu", response);
                listener.onGetListChucVuSuccess(new ChucVuInfo().getListChucVu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListChucVuError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
