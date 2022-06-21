package com.bongmai.tiha.data.entities;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChucVuInfo implements Serializable {

    private String CVID;
    private String CVName;
//    private int HeSoLuong;
//    private String ModifiedBy ;
//    private Nullable ModifiedDate ;
//    private Nullable DoanhSo;
//    private int MaThuTu;
//    private String CVIDCaoNhatDaDat;
//    private String DuLieuTimKiem;

//    public int getHeSoLuong() {
//        return HeSoLuong;
//    }
//
//    public void setHeSoLuong(int heSoLuong) {
//        HeSoLuong = heSoLuong;
//    }
//
//    public String getModifiedBy() {
//        return ModifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        ModifiedBy = modifiedBy;
//    }
//
//    public Nullable getModifiedDate() {
//        return ModifiedDate;
//    }
//
//    public void setModifiedDate(Nullable modifiedDate) {
//        ModifiedDate = modifiedDate;
//    }
//
//    public Nullable getDoanhSo() {
//        return DoanhSo;
//    }
//
//    public void setDoanhSo(Nullable doanhSo) {
//        DoanhSo = doanhSo;
//    }
//
//    public int getMaThuTu() {
//        return MaThuTu;
//    }
//
//    public void setMaThuTu(int maThuTu) {
//        MaThuTu = maThuTu;
//    }
//
//    public String getCVIDCaoNhatDaDat() {
//        return CVIDCaoNhatDaDat;
//    }
//
//    public void setCVIDCaoNhatDaDat(String CVIDCaoNhatDaDat) {
//        this.CVIDCaoNhatDaDat = CVIDCaoNhatDaDat;
//    }
//
//    public String getDuLieuTimKiem() {
//        return DuLieuTimKiem;
//    }
//
//    public void setDuLieuTimKiem(String duLieuTimKiem) {
//        DuLieuTimKiem = duLieuTimKiem;
//    }

    public String getCVID() {
        return CVID;
    }

    public void setCVID(String CVID) {
        this.CVID = CVID;
    }

    public String getCVName() {
        return CVName;
    }

    public void setCVName(String CVName) {
        this.CVName = CVName;
    }

    public ChucVuInfo getChucVu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ChucVuInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ChucVuInfo> getListChucVu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ChucVuInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
