package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoanhSoChiNhanhInfo {
    private String ChiNhanhID;
    private String TenChiNhanh;
    private double SoLuong;
    private double ThanhTien;

    public String getChiNhanhID() {
        return ChiNhanhID;
    }

    public void setChiNhanhID(String chiNhanhID) {
        ChiNhanhID = chiNhanhID;
    }

    public String getTenChiNhanh() {
        return TenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        TenChiNhanh = tenChiNhanh;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public DoanhSoChiNhanhInfo() {
        ChiNhanhID = "";
        TenChiNhanh = "";
        SoLuong = 0;
        ThanhTien = 0;
    }

    public DoanhSoChiNhanhInfo getDoanhSoChiNhanh(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DoanhSoChiNhanhInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DoanhSoChiNhanhInfo> getListDoanhSoChiNhanh(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DoanhSoChiNhanhInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
