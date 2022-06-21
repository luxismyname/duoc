package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CongNoTongHopInfo {
    private String PTTT;
    private String NhomKH;
    private String MaKH;
    private String TenKH;
    private String DiaChi;
    private double PhaiTraDK;
    private double PhaiThuDK;
    private double Nhap;
    private double Xuat;
    private double Thu;
    private double Chi;
    private double PhaiTraCK;
    private double PhaiThuCK;

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getNhomKH() {
        return NhomKH;
    }

    public void setNhomKH(String nhomKH) {
        NhomKH = nhomKH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public double getPhaiTraDK() {
        return PhaiTraDK;
    }

    public void setPhaiTraDK(double phaiTraDK) {
        PhaiTraDK = phaiTraDK;
    }

    public double getPhaiThuDK() {
        return PhaiThuDK;
    }

    public void setPhaiThuDK(double phaiThuDK) {
        PhaiThuDK = phaiThuDK;
    }

    public double getNhap() {
        return Nhap;
    }

    public void setNhap(double nhap) {
        Nhap = nhap;
    }

    public double getXuat() {
        return Xuat;
    }

    public void setXuat(double xuat) {
        Xuat = xuat;
    }

    public double getThu() {
        return Thu;
    }

    public void setThu(double thu) {
        Thu = thu;
    }

    public double getChi() {
        return Chi;
    }

    public void setChi(double chi) {
        Chi = chi;
    }

    public double getPhaiTraCK() {
        return PhaiTraCK;
    }

    public void setPhaiTraCK(double phaiTraCK) {
        PhaiTraCK = phaiTraCK;
    }

    public double getPhaiThuCK() {
        return PhaiThuCK;
    }

    public void setPhaiThuCK(double phaiThuCK) {
        PhaiThuCK = phaiThuCK;
    }

    public CongNoTongHopInfo() {
        PTTT = "";
        NhomKH = "";
        MaKH = "";
        TenKH = "";
        DiaChi = "";
        PhaiTraDK = 0;
        PhaiThuDK = 0;
        Nhap = 0;
        Xuat = 0;
        Thu = 0;
        Chi = 0;
        PhaiTraCK = 0;
        PhaiThuCK = 0;
    }

    public CongNoTongHopInfo getCongNoTongHop(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<CongNoTongHopInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<CongNoTongHopInfo> getListCongNoTongHop(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<CongNoTongHopInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
