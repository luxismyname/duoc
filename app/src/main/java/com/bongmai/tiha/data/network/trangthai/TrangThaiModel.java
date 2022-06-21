package com.bongmai.tiha.data.network.trangthai;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.data.network.phieugiaohang.IPhieuGiaoHangModel;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class TrangThaiModel implements ITrangThaiModel {

    APIService service;

    @Override
    public void GetListTrangThai(IOnGetListTrangThaiFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        service = new APIService(AppConstants.URL_GetListTrangThai);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("trangthaiinfo",response);
               listener.onGetListTrangThaiSuccess(new TrangThaiInfo().getListTrangThai(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListTrangThaiError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListTrangThaiByLoaiPhieuByUser(String loaiPhieu, String userName, IOnGetListTrangThaiByLoaiPhieuByUserFinishedListener listener) {
        Map<String, String> params = new HashMap<>();
        String URL = MessageFormat.format(AppConstants.URL_GetListTrangThaiByLoaiPhieuByUser, loaiPhieu, userName);
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("heyloaiphieu", response);
                listener.onGetListTrangThaiByLoaiPhieuByUserSuccess(new TrangThaiLoaiPhieuInfo().getListTrangThaiLoaiPhieu(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListTrangThaiByLoaiPhieuByUserError(AppUtils.getMessageVolleyError(error));
            }
        },params);
    }

}
