package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BangGiaGroupInfo {
    private String MaSoBangGia ;
    private String TenBangGia ;

    public String getMaSoBangGia() {
        return MaSoBangGia;
    }

    public void setMaSoBangGia(String maSoBangGia) {
        MaSoBangGia = maSoBangGia;
    }

    public String getTenBangGia() {
        return TenBangGia;
    }

    public void setTenBangGia(String tenBangGia) {
        TenBangGia = tenBangGia;
    }

    public BangGiaGroupInfo getBangGiaGroup(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<BangGiaGroupInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<BangGiaGroupInfo> getListBangGiaGroup(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<BangGiaGroupInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
