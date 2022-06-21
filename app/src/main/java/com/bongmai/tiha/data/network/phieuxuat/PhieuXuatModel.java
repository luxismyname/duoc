package com.bongmai.tiha.data.network.phieuxuat;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.POST;


public class PhieuXuatModel implements IPhieuXuatModel {

    APIService service;
    APIServiceDuocCuuLong clService;


    @Override
    public void InsertPhieuXuat(PhieuXuatInfo PhieuXuatInfo, List<VattuxuatInfo> listVattuxuat, final IOnInsertPhieuXuatFinishedListener listener) {
        Map<String, String> params = new HashMap();
        params.put("ItemPhieuXuat", new Gson().toJson(PhieuXuatInfo));
        params.put("ListVattuxuat", new Gson().toJson(listVattuxuat));
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(AppConstants.URL_InsertPhieuBanSiWithSerialX);
        service.Update(POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("PX", response);
                listener.onSuccess(new PhieuXuatInfo().getPhieuXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void UpdatePhieuXuat(PhieuXuatInfo PhieuXuatInfo, List<VattuxuatInfo> listVattuxuat, IOnUpdatePhieuXuatFinishedListener listener) {
        Map<String, String> params = new HashMap();
        params.put("ItemPhieuXuat", new Gson().toJson(PhieuXuatInfo));
        params.put("ListVattuxuat", new Gson().toJson(listVattuxuat));
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(AppConstants.URL_UpdatePhieuBanSiWithSerialX);
        service.Update(POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new PhieuXuatInfo().getPhieuXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void GetListPhieuXuat(PhieuXuatCondition condition, final IOnGetListPhieuXuatFinishedListener listener) {
        PhieuXuatCondition conditionFinal = new PhieuXuatCondition(condition);
        if (TextUtils.isEmpty(conditionFinal.getListKho())) {
            conditionFinal.setListKho(PublicVariables.nguoiDungInfo.getListKhoStr());
        }
        Map<String, String> params = conditionFinal.GetParameter();
        service = new APIService(AppConstants.URL_GetListPhieuXuat);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new DanhSachXuatInfo().getListDanhSachXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetPhieuXuat(String soPhieu, IOnGetPhieuXuatFinishedListener listener) {
        service = new APIService(MessageFormat.format(AppConstants.URL_GetPhieuXuat, soPhieu));
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("getPhieuxuat", response);
                listener.onSuccess(new PhieuXuatInfo().getPhieuXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetPhieuXuatCuuLong(String soPhieu, IOnGetPhieuXuatCuuLongFinishedListener listener) {
        clService = new APIServiceDuocCuuLong(MessageFormat.format(AppConstants.URL_GetPhieuXuat, soPhieu));
        clService.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("getPhieuxuat", response);
                listener.onGetPhieuXuatCuuLongSuccess(new PhieuXuatInfo().getPhieuXuat(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetPhieuXuatCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        });
    }


    @Override
    public void GetPhieuXuatChiTiet(String soPhieu, IOnGetPhieuXuatChiTietFinishedListener listener) {
        service = new APIService(MessageFormat.format(AppConstants.URL_GetChiTietPhieuXuat, soPhieu));
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {

                    listener.onGetVattuSuccess(new VattuxuatInfo().getListVattuxuat(response));

            }

            @Override
            public void onError(VolleyError error) {
                listener.onVattuError(AppUtils.getMessageVolleyError(error));
            }
        });
    }


    @Override
    public void DeletePhieuXuat(String soPhieu, final IOnDeletePhieuXuatFinishedListener listener) {
        service = new APIService(MessageFormat.format(AppConstants.URL_DeletePhieuXuat, soPhieu, PublicVariables.nguoiDungInfo.getUserName()));
        service.Delete(Request.Method.DELETE, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("Response", response);
                listener.onSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void InQuaInternet(String soPhieu, String computerName, IOnInQuaInternetFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_InQuaInternet, soPhieu, computerName);
        service = new APIService(URL);
        service.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("In", response);
                listener.onSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }
}
