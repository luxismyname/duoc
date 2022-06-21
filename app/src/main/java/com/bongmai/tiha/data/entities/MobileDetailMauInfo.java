package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MobileDetailMauInfo {
    private String MaDetailMau;
    private String TenDetailMau;
    private String MaMau;
    private boolean IsKho;
    private boolean IsLoaiHang;
    private boolean IsNhanVien;
    private int Xem;
    private int ThuTu;

    public String getMaDetailMau() {
        return MaDetailMau;
    }

    public void setMaDetailMau(String maDetailMau) {
        MaDetailMau = maDetailMau;
    }

    public String getTenDetailMau() {
        return TenDetailMau;
    }

    public void setTenDetailMau(String tenDetailMau) {
        TenDetailMau = tenDetailMau;
    }

    public String getMaMau() {
        return MaMau;
    }

    public void setMaMau(String maMau) {
        MaMau = maMau;
    }

    public boolean isKho() {
        return IsKho;
    }

    public void setKho(boolean kho) {
        IsKho = kho;
    }

    public boolean isLoaiHang() {
        return IsLoaiHang;
    }

    public void setLoaiHang(boolean loaiHang) {
        IsLoaiHang = loaiHang;
    }

    public boolean isNhanVien() {
        return IsNhanVien;
    }

    public void setNhanVien(boolean nhanVien) {
        IsNhanVien = nhanVien;
    }

    public int getXem() {
        return Xem;
    }

    public void setXem(int xem) {
        Xem = xem;
    }

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int thuTu) {
        ThuTu = thuTu;
    }

    public MobileDetailMauInfo getMobileDetailMau(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<MobileDetailMauInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<MobileDetailMauInfo> getListMobileDetailMau(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<MobileDetailMauInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
