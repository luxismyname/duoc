package com.bongmai.tiha.data.network.supplier;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.APIServiceDuocCuuLong;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class SupplierModel implements ISupplierModel {

    APIService service;
    APIServiceDuocCuuLong clService;

    @Override
    public void InsertSupplier(SupplierInfo SupplierInfo, IOnInsertSupplierFinishedListener listener) {
        String URL = AppConstants.URL_InsertSupplier;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(SupplierInfo);
        params.put("itemSupplier", jsonItemReport);
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        Log.d("itemSupplier", jsonItemReport);
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onInsertSupplierSuccess(new SupplierInfo().getSupplier(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertSupplierError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void UpdateSupplier(SupplierInfo SupplierInfo, final IOnUpdateSupplierFinishedListener listener) {
        //test
        String URL = AppConstants.URL_UpdateSupplier;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(SupplierInfo);
        params.put("itemSupplier", jsonItemReport);
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        Log.d("itemSupplier", jsonItemReport);
        service.Update(Request.Method.POST,new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onUpdateSupplierSuccess(new SupplierInfo().getSupplier(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onUpdateSupplierError(AppUtils.getMessageVolleyError(error));
            }
        },params);

    }

    @Override
    public void GetListSupplier(SupplierCondition condition, final IOnGetListSupplierFinishedListener listener) {
        String URL = AppConstants.URL_GetListSupplier;
        Map<String, String> params = condition.GetParameter();
        service = new APIService(URL);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String json = jsonObject.getJSONArray("List").toString();
                    long total = Long.parseLong(jsonObject.get("Total").toString());
                    listener.onGetListSupplierSuccess(new SupplierInfo().getListSupplier(json), total);
                } catch (JSONException e) {
                    listener.onGetListSupplierError(e.getMessage());
                }
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListSupplierError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetSupplier(String supplierID, final IOnGetSupplierFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetSupplier, supplierID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetSupplierSuccess(new SupplierInfo().getSupplier(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetSupplierError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void DeleteSupplier(String SupplierID, IOnDeleteSupplierFinishedListener listener) {

        String URL = MessageFormat.format(AppConstants.URL_DeleteSupplier, SupplierID, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.Delete(Request.Method.DELETE, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onDeleteSupplierSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onDeleteSupplierError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void CapNhatToaDoKhachHang(String supplierID, String viDo, String kinhDo, IOnCapNhatToaDoKhachHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_CapNhatToaDoKhachHang, supplierID, viDo, kinhDo);
        service = new APIService(URL);
        service.Update(Request.Method.GET, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        }, new HashMap<>());
    }

    @Override
    public void GetSupplierDuocCuuLong(String supplierID, IOnGetSupplierDuocCuuLongFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetSupplier, supplierID);
        clService = new APIServiceDuocCuuLong(URL);
        clService.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetSupplierDuocCuuLongSuccess(new SupplierInfo().getSupplier(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetSupplierDuocCuuLongError(AppUtils.getMessageVolleyError(error));
            }
        });
    }


}
