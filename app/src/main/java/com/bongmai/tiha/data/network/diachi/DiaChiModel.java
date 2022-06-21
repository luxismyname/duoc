package com.bongmai.tiha.data.network.diachi;

import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.google.gson.Gson;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class DiaChiModel implements IDiaChiModel {

    APIService service;

    @Override
    public void GetListDiaChi(String loaiDiaChi, int parentID,int parentName, final IOnGetListDiaChiFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListDiaChi, loaiDiaChi, String.valueOf(parentID), String.valueOf(parentName));
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("listdiachi", response);
                listener.onGetListDiaChiSuccess(new DiaChiInfo().getListDiaChi(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListDiaChiError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void InsertDiaChi(DiaChiInfo diaChiInfo, IOnInsertDiaChiFinishedListener listener) {
        String URL = AppConstants.URL_InsertAddress;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        params.put("MaDiaChi", diaChiInfo.getMaDiaChi());
        params.put("TenDiaChi", diaChiInfo.getTenDiaChi());
        params.put("QuanID", String.valueOf(diaChiInfo.getQuanID()));
        params.put("TinhID", String.valueOf(diaChiInfo.getTinhID()));
        params.put("LoaiDiaChi", diaChiInfo.getLoaiDiaChi());
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("DiaChi", response);
                listener.onInsertDiaChiSuccess(new DiaChiInfo().getDiaChi(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

//    @Override
//    public void InsertDiaChi(AddressInfo addressInfo,String MaDiaChi, String TenDiaChi, int QuanID, int TinhID, String LoaiDiaChi, IOnInsertDiaChiFinishedListener listener) {
//        String URL = MessageFormat.format(AppConstants.URL_InsertAddress, MaDiaChi, TenDiaChi, String.valueOf(QuanID), String.valueOf(TinhID), LoaiDiaChi);
//        service = new APIService(URL);
//        Map<String, String> params = new HashMap<>();
//        params.put("AddressInfo", new Gson().toJson(addressInfo));
//        service.Insert(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("DiaChi", response);
//                listener.onInsertDiaChiSuccess(new AddressInfo().getAddress(response));
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                listener.onInsertDiaChiError(AppUtils.getMessageVolleyError(error));
//            }
//        }, params);
//    }


//    @Override
//    public void InsertDiaChi(DiaChiInfo diaChiInfo, final IOnInsertDiaChiFinishedListener listener) {
//
//        String URL = AppConstants.URL_InsertAddress;
//        service = new APIService(URL);
//        Map<String, String> params = new HashMap<>();
//        params.put("item", new Gson().toJson(diaChiInfo));
//        service.Insert(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("Diachi", response);
//                listener.onInsertDiaChiSuccess(new DiaChiInfo().getDiaChi(response));
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                listener.onInsertDiaChiError(AppUtils.getMessageVolleyError(error));
//            }
//        }, params);
//
//    }



}
