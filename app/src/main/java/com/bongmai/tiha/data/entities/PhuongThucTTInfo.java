package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PhuongThucTTInfo implements Serializable {
    private String MSPTTT;
    private String PTTT;
    private String Loai;

    public String getMSPTTT() {
        return MSPTTT;
    }

    public void setMSPTTT(String MSPTTT) {
        this.MSPTTT = MSPTTT;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public PhuongThucTTInfo getPhuongThucTT(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<PhuongThucTTInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<PhuongThucTTInfo> getListPhuongThucTT(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<PhuongThucTTInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
