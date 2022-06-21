package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

public class CongNoTongHopPhaiThuInfo {
    private String MaNhomKH;
    private String PTTT;
    private String Supplier_ID;
    private String Company_Name;
    private String DiaChi;
    private double PhaiThuDK;
    private double Nhap;
    private double Xuat;
    private double Thu;
    private double Chi;
    private double PhaiThuCK;
    private Date NgayThuGN;

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

    public String getDiaChi() {
        return (DiaChi == null) ? "" : DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
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

    public double getPhaiThuCK() {
        return PhaiThuCK;
    }

    public void setPhaiThuCK(double phaiThuCK) {
        PhaiThuCK = phaiThuCK;
    }

    public Date getNgayThuGN() {
        return NgayThuGN;
    }

    public void setNgayThuGN(Date ngayThuGN) {
        NgayThuGN = ngayThuGN;
    }

    public CongNoTongHopPhaiThuInfo() {
        Supplier_ID = "";
        Company_Name = "";
        MaNhomKH = "";
        PTTT = "";
        DiaChi = "";
        PhaiThuDK = 0;
        Nhap = 0;
        Xuat = 0;
        Thu = 0;
        Chi = 0;
        PhaiThuCK = 0;
        NgayThuGN = null;
    }

    public CongNoTongHopPhaiThuInfo getCongNoTongHopPhaiThu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<CongNoTongHopPhaiThuInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<CongNoTongHopPhaiThuInfo> getListCongNoTongHopPhaiThu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<CongNoTongHopPhaiThuInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
