package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaChiInfo implements Serializable {
    public static final String DUONG = "DUONG";
    public static final String PHUONG = "PHUONG";
    public static final String QUAN = "QUAN";
    public static final String TINH = "TINH";

    private int ID;
    private String MaDiaChi;
    private String TenDiaChi;
    private int ParentID;
    private String LoaiDiaChi;
    private List<DiaChiInfo> ListDiaChi;
    public int QuanID;
    public int TinhID;

    public int getQuanID() {
        return QuanID;
    }

    public void setQuanID(int quanID) {
        QuanID = quanID;
    }

    public int getTinhID() {
        return TinhID;
    }

    public void setTinhID(int tinhID) {
        TinhID = tinhID;
    }

    public List<DiaChiInfo> getListDiaChi() {
        return ListDiaChi;
    }

    public void setListDiaChi(List<DiaChiInfo> listDiaChi) {
        ListDiaChi = listDiaChi;
    }

    public String getLoaiDiaChi() {
        return LoaiDiaChi;
    }

    public void setLoaiDiaChi(String loaiDiaChi) {
        LoaiDiaChi = loaiDiaChi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaDiaChi() {
        return MaDiaChi;
    }

    public void setMaDiaChi(String maDiaChi) {
        MaDiaChi = maDiaChi;
    }

    public String getTenDiaChi() {
        return TenDiaChi;
    }

    public void setTenDiaChi(String tenDiaChi) {
        TenDiaChi = tenDiaChi;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public DiaChiInfo getDiaChi(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DiaChiInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DiaChiInfo> getListDiaChi(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DiaChiInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }



}
