package com.bongmai.tiha.data.network.employee;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.EmployeeCondition;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.data.network.supplier.ISupplierModel;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class EmployeeModel implements IEmployeeModel
{

    APIService service;

    @Override
    public void InsertEmployee(EmployeeInfo EmployeeInfo, IOnInsertEmployeeFinishedListener listener) {
        String URL = AppConstants.URL_InsertEmployee;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(EmployeeInfo);
        params.put("itemEmployee", jsonItemReport);
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        Log.d("itemEmployee", jsonItemReport);
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onInsertEmployeeSuccess(new EmployeeInfo().getEmployee(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertEmployeeError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void UpdateEmployee(EmployeeInfo employeeInfo, IOnUpdateEmployeeFinishedListener listener) {
        String URL = AppConstants.URL_UpdateEmployee;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        String jsonItemReport = new Gson().toJson(employeeInfo);
        params.put("itemEmployee", jsonItemReport);
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        Log.d("itemEmployee", jsonItemReport);
        service.Update(Request.Method.POST, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onUpdateEmployeeSuccess(new EmployeeInfo().getEmployee(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onUpdateEmployeeError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

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

    @Override
    public void GetListChucVu(IOnGetListChucVuFinishedListener listener) {
//        String URL = MessageFormat.format(AppConstants.URL_GetListEmployee, PublicVariables.nguoiDungInfo.getUserName(),
//                PublicVariables.nguoiDungInfo.getListChiNhanh());
//        service = new APIService(URL);
//        service.DownloadJson(new VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//                listener.onGetListEmployeeSuccess(new EmployeeInfo().getListEmployee(response));
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                listener.onGetListEmployeeError(AppUtils.getMessageVolleyError(error));
//            }
//        });
    }

    @Override
    public void GetListEmployee( IOnGetListEmployeeFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListEmployee, PublicVariables.nguoiDungInfo.getUserName(),
                PublicVariables.nguoiDungInfo.getListChiNhanh());
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListEmployeeSuccess(new EmployeeInfo().getListEmployee(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListEmployeeError(AppUtils.getMessageVolleyError(error));
            }
        });
    }



    @Override
    public void GetHinhAnhByEmployeeID(String employeeID, IOnGetHinhAnhByEmployeeIDFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetHinhAnhByEmployeeID, employeeID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetEmployee(String employeeID, IOnGetEmployeeFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetEmployee, employeeID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new EmployeeInfo().getEmployee(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void DeleteEmployee(String EmployeeID, final IEmployeeModel.IOnDeleteEmployeeFinishedListener listener) {

        service = new APIService(MessageFormat.format(AppConstants.URL_DeleteEmployee, EmployeeID, PublicVariables.nguoiDungInfo.getUserName()));

        service.Delete(Request.Method.DELETE, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onDeleteEmployeeSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onDeleteEmployeeError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
