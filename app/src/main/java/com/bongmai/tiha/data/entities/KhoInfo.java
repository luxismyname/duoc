package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class KhoInfo implements Serializable {
    private boolean Chon;
    private String MSK ;
    private String Tenkho ;
    private String diachi ;
    private String Tel ;
    private String THUKHO ;
    private int Coxemhangton ;
    private String ChiNhanhID ;
    private String ThuQuy ;
    private String X ;
    private String Y ;
    private String Unit ;
    private String KhuVuc ;

    public boolean isChon() {
        return Chon;
    }

    public void setChon(boolean chon) {
        Chon = chon;
    }

    public String getMSK() {
        return MSK;
    }

    public void setMSK(String MSK) {
        this.MSK = MSK;
    }

    public String getTenkho() {
        return Tenkho;
    }

    public void setTenkho(String tenkho) {
        Tenkho = tenkho;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTHUKHO() {
        return THUKHO;
    }

    public void setTHUKHO(String THUKHO) {
        this.THUKHO = THUKHO;
    }

    public int getCoxemhangton() {
        return Coxemhangton;
    }

    public void setCoxemhangton(int coxemhangton) {
        Coxemhangton = coxemhangton;
    }

    public String getChiNhanhID() {
        return ChiNhanhID;
    }

    public void setChiNhanhID(String chiNhanhID) {
        ChiNhanhID = chiNhanhID;
    }

    public String getThuQuy() {
        return ThuQuy;
    }

    public void setThuQuy(String thuQuy) {
        ThuQuy = thuQuy;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        KhuVuc = khuVuc;
    }

    public KhoInfo() {
        MSK = "";
        Tenkho = "";
        diachi = "";
        Tel = "";
        THUKHO = "";
        Coxemhangton = 0;
        ChiNhanhID = "";
        ThuQuy = "";
        X = "";
        Y = "";
        Unit = "";
        KhuVuc = "";
    }

    public KhoInfo getKho(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<KhoInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<KhoInfo> getListKho(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<KhoInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
