package com.bongmai.tiha.data.network.changelog;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.network.api.APINoService;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;

public class ChangeLogModel implements IChangeLogModel {

    APINoService service;

    @Override
    public void GetListChangeLog(IOnGetListChangeLogFinishedListener listener) {

        String URL = AppConstants.URL_UPDATE_CHANGELOG;
        service = new APINoService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("changelog", response);
                listener.onGetListChangeLogSuccess(new ChangeLogInfo().getChangeLog(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListChangeLogError(AppUtils.getMessageVolleyError(error));
            }
        });

    }
}
