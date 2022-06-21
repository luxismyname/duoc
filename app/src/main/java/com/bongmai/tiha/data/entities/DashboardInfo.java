package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DashboardInfo {

    //region Dashboard doanh thu
    private DashboardDoanhThuInfo ItemDoanhThu;
    private List<DashboardDoanhThuInfo> ListDoanhThu;
    private List<HangBanChayInfo> ListHangBanChay;
    private List<HangCoXuatInfo> ListHangCoXuat;

    public DashboardDoanhThuInfo getItemDoanhThu() {
        return ItemDoanhThu;
    }

    public void setItemDoanhThu(DashboardDoanhThuInfo itemDoanhThu) {
        ItemDoanhThu = itemDoanhThu;
    }

    public List<DashboardDoanhThuInfo> getListDoanhThu() {
        return ListDoanhThu;
    }

    public void setListDoanhThu(List<DashboardDoanhThuInfo> listDoanhThu) {
        ListDoanhThu = listDoanhThu;
    }

    public List<HangBanChayInfo> getListHangBanChay() {
        return ListHangBanChay;
    }

    public void setListHangBanChay(List<HangBanChayInfo> listHangBanChay) {
        ListHangBanChay = listHangBanChay;
    }

    public List<HangCoXuatInfo> getListHangCoXuat() {
        return ListHangCoXuat;
    }

    public void setListHangCoXuat(List<HangCoXuatInfo> listHangCoXuat) {
        ListHangCoXuat = listHangCoXuat;
    }
    //endregion

    private DashboardCuocGoiInfo ItemCuocGoi;
    private List<DashboardCuocGoiInfo> ListCuocGoi;

    public DashboardCuocGoiInfo getItemCuocGoi() {
        return ItemCuocGoi;
    }

    public void setItemCuocGoi(DashboardCuocGoiInfo itemCuocGoi) {
        ItemCuocGoi = itemCuocGoi;
    }

    public List<DashboardCuocGoiInfo> getListCuocGoi() {
        return ListCuocGoi;
    }

    public void setListCuocGoi(List<DashboardCuocGoiInfo> listCuocGoi) {
        ListCuocGoi = listCuocGoi;
    }

    public DashboardInfo getDashboard(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DashboardInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DashboardInfo> getListDashboard(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DashboardInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
