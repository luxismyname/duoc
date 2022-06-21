package com.bongmai.tiha.data.network.map;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.UserLocationInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserLocationModel implements  IUserLocationModel  {

    APIService service;
    APIServiceDuocCuuLong clService;

    @Override
    public void InsertUserLocation(UserLocationInfo userLocationInfo, IOnInsertUserLocationFinishedListener listener) {
        String URL = AppConstants.URL_InsertUserLocation;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(userLocationInfo);
        params.put("itemUserLocation", jsonItemReport);
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemKMNVGH", response);
                listener.onInsertUserLocationSuccess(new UserLocationInfo().getUserLocationInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertUserLocationError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void InsertUserLocationCuuLong(UserLocationInfo userLocationInfo, IOnInsertUserLocationCuuLongFinishedListener listener) {
        String URL = AppConstants.URL_InsertUserLocation;
        clService = new APIServiceDuocCuuLong(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(userLocationInfo);
        params.put("itemUserLocation", jsonItemReport);
        clService.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("itemKMNVGH", response);
                listener.onInsertUserLocationCuuLongSuccess(new UserLocationInfo().getUserLocationInfo(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertUserLocationCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

}
