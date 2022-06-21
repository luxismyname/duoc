package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

public class CongNoTongHopPhaiTraInfo {
    private String MaNhomKH;
    private String PTTT;
    private String Supplier_ID;
    private String Company_Name;
    private String DiaChi;
    private double PhaiTraDK;
    private double Nhap;
    private double Xuat;
    private double Thu;
    private double Chi;
    private double PhaiTraCK;
    private Date NgayTraGN;

    public String getMaNhomKH() {
        return MaNhomKH;
    }

    public void setMaNhomKH(String maNhomKH) {
        MaNhomKH = maNhomKH;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getDiaChi() {
        return (DiaChi == null) ? "" : DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        Supplier_ID = supplier_ID;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public double getPhaiTraDK() {
        return PhaiTraDK;
    }

    public void setPhaiTraDK(double phaiTraDK) {
        PhaiTraDK = phaiTraDK;
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

    public Date getNgayTraGN() {
        return NgayTraGN;
    }

    public void setNgayTraGN(Date ngayTraGN) {
        NgayTraGN = ngayTraGN;
    }

    public CongNoTongHopPhaiTraInfo() {
        Supplier_ID = "";
        Company_Name = "";
        MaNhomKH = "";
        PTTT = "";
        DiaChi = "";
        PhaiTraDK = 0;
        Nhap = 0;
        Xuat = 0;
        Thu = 0;
        Chi = 0;
        PhaiTraCK = 0;
        NgayTraGN = null;
    }

    public CongNoTongHopPhaiTraInfo getCongNoTongHopPhaiTra(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<CongNoTongHopPhaiTraInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<CongNoTongHopPhaiTraInfo> getListCongNoTongHopPhaiTra(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<CongNoTongHopPhaiTraInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
