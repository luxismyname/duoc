package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuanInfo implements Serializable {

    private String TenQuanVietTat;
    private String ChiTietTenQuan;
    private String MaTinh;


    public String getTenQuanVietTat() {
        return TenQuanVietTat;
    }

    public void setTenQuanVietTat(String tenQuanVietTat) {
        TenQuanVietTat = tenQuanVietTat;
    }

    public String getChiTietTenQuan() {
        return ChiTietTenQuan;
    }

    public void setChiTietTenQuan(String chiTietTenQuan) {
        ChiTietTenQuan = chiTietTenQuan;
    }

    public String getMaTinh() {
        return MaTinh;
    }

    public void setMaTinh(String maTinh) {
        MaTinh = maTinh;
    }


}
