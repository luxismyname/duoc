package com.bongmai.tiha.data.network.common;

import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.BangGiaGroupInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.text.MessageFormat;
import java.util.Date;


public class CommonModel implements ICommonModel {

    APIService service;


    @Override
    public void GetListBangGiaGroup(IOnGetListBangGiaGroupFinishedListener listener) {
        String URL = AppConstants.URL_GetListBangGiaGroup;
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(new BangGiaGroupInfo().getListBangGiaGroup(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void KiemTraDuocXuatHang(String soPhieu, String maKho, String productID, Date ngay, double soLuong, IOnKiemTraDuocXuatHangFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_KiemTraDuocXuatHang, maKho, productID,
                AppUtils.formatDateToString(ngay, "dd/MM/yyyy"), soPhieu, String.valueOf(soLuong), PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }
}
