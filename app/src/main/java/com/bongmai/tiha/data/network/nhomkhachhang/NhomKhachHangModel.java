package com.bongmai.tiha.data.network.nhomkhachhang;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.PhuongThucTTInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.text.MessageFormat;


public class NhomKhachHangModel implements INhomKhachHangModel {

    APIService service;

    @Override
    public void GetListNhomKhachHang(IOnGetListNhomKhachHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetListNhomKhachHang, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetListNhomKhachHangSuccess(new PhuongThucTTInfo().getListPhuongThucTT(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListNhomKhachHangError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
