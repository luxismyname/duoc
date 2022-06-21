package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DKNhatXuatNuocALLVoRongInfo {
    private String ProductID;

    private String Product_Name;

    private double SLDK_V;

    private double Nhap_V;

    private double CDen_V;

    private double Xuat_V;

    private double Cdi_V;

    private double Ton_V;

    private double SLDK_N;

    private double Nhap_N;

    private double CDen_N;

    private double Xuat_N;

    private double Cdi_N;

    private double Ton_N;

    private String MaNuoc;

    private String TenNuoc;

    private String LoaiHang;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public double getSLDK_V() {
        return SLDK_V;
    }

    public void setSLDK_V(double SLDK_V) {
        this.SLDK_V = SLDK_V;
    }

    public double getNhap_V() {
        return Nhap_V;
    }

    public void setNhap_V(double nhap_V) {
        Nhap_V = nhap_V;
    }

    public double getCDen_V() {
        return CDen_V;
    }

    public void setCDen_V(double CDen_V) {
        this.CDen_V = CDen_V;
    }

    public double getXuat_V() {
        return Xuat_V;
    }

    public void setXuat_V(double xuat_V) {
        Xuat_V = xuat_V;
    }

    public double getCdi_V() {
        return Cdi_V;
    }

    public void setCdi_V(double cdi_V) {
        Cdi_V = cdi_V;
    }

    public double getTon_V() {
        return Ton_V;
    }

    public void setTon_V(double ton_V) {
        Ton_V = ton_V;
    }

    public double getSLDK_N() {
        return SLDK_N;
    }

    public void setSLDK_N(double SLDK_N) {
        this.SLDK_N = SLDK_N;
    }

    public double getNhap_N() {
        return Nhap_N;
    }

    public void setNhap_N(double nhap_N) {
        Nhap_N = nhap_N;
    }

    public double getCDen_N() {
        return CDen_N;
    }

    public void setCDen_N(double CDen_N) {
        this.CDen_N = CDen_N;
    }

    public double getXuat_N() {
        return Xuat_N;
    }

    public void setXuat_N(double xuat_N) {
        Xuat_N = xuat_N;
    }

    public double getCdi_N() {
        return Cdi_N;
    }

    public void setCdi_N(double cdi_N) {
        Cdi_N = cdi_N;
    }

    public double getTon_N() {
        return Ton_N;
    }

    public void setTon_N(double ton_N) {
        Ton_N = ton_N;
    }

    public String getMaNuoc() {
        return MaNuoc;
    }

    public void setMaNuoc(String maNuoc) {
        MaNuoc = maNuoc;
    }

    public String getTenNuoc() {
        return TenNuoc;
    }

    public void setTenNuoc(String tenNuoc) {
        TenNuoc = tenNuoc;
    }

    public String getLoaiHang() {
        return LoaiHang;
    }

    public void setLoaiHang(String loaiHang) {
        LoaiHang = loaiHang;
    }

    public DKNhatXuatNuocALLVoRongInfo getDKNhatXuatNuocALLVoRong(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DKNhatXuatNuocALLVoRongInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DKNhatXuatNuocALLVoRongInfo> getListDKNhatXuatNuocALLVoRong(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DKNhatXuatNuocALLVoRongInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
