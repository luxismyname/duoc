package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoanhSoKhachHangInfo {
    private String SupplierID;
    private String CompanyName;
    private String DiaChi;
    private String DienThoai;
    private double SoLuong;
    private double ThanhTien;

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public DoanhSoKhachHangInfo() {
        SupplierID = "";
        CompanyName = "";
        DiaChi = "";
        DienThoai = "";
        SoLuong = 0;
        ThanhTien = 0;
    }

    public DoanhSoKhachHangInfo getDoanhSoKhachHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DoanhSoKhachHangInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DoanhSoKhachHangInfo> getListDoanhSoKhachHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DoanhSoKhachHangInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
