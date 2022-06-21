package com.bongmai.tiha.data.network.bophan;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

import java.text.MessageFormat;

public class BoPhanModel implements IBoPhanModel {

    APIService service;

    @Override
    public void GetListBoPhan(IOnGetListBoPhanFinishedListener listener) {
        String URL = AppConstants.URL_GetListBoPhan;
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemBoPhan", response);
                listener.onGetListBoPhanSuccess(new BoPhanInfo().getListBoPhan(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListBoPhanError(AppUtils.getMessageVolleyError(error));
            }
        });


    }
}
