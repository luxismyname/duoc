package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

public class DanhSachNhapInfo {
    private Date Ngay;
    private String SoCT;
    private String Tennguoimua;
    private double TT;

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getSoCT() {
        return SoCT;
    }

    public void setSoCT(String soCT) {
        SoCT = soCT;
    }

    public String getTennguoimua() {
        return Tennguoimua;
    }

    public void setTennguoimua(String tennguoimua) {
        Tennguoimua = tennguoimua;
    }

    public double getTT() {
        return TT;
    }

    public void setTT(double TT) {
        this.TT = TT;
    }

    public DanhSachNhapInfo() {
        Ngay = null;
        SoCT = "";
        Tennguoimua = "";
        TT = 0;
    }

    public DanhSachNhapInfo getDanhSachNhap(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DanhSachNhapInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DanhSachNhapInfo> getListDanhSachNhap(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DanhSachNhapInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
