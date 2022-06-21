package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TblConfigInfo {
    String Khomacdinh;

    public String getKhomacdinh() {
        return Khomacdinh;
    }

    public void setKhomacdinh(String khomacdinh) {
        Khomacdinh = khomacdinh;
    }

    public TblConfigInfo getTblConfig(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<TblConfigInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<TblConfigInfo> getListTblConfig(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<TblConfigInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
