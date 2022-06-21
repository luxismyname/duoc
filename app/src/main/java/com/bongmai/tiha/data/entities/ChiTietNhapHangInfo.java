package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

public class ChiTietNhapHangInfo {
private Date Ngay;
private String SoCt;
private String SupplierID;
private String CompanyName;
private String DiaChi;
private String MSK;
private String Tenkho;
private String EmployeeID;
private String EmployeeName;
private String MSNguoiGiao;
private String TenNguoiGiao;
private String CategoryID;
private String TenLoaiHang;
private String ProductID;
private String ProductName;
private String Dvt;
private double SL;
private double Giaban;
private double dongia;
private int LoaiDiscount;
private double GiatriDiscount;
private double ChietKhau;
private double Thue;
private double Thanh_Tien;

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getSoCt() {
        return SoCt;
    }

    public void setSoCt(String soCt) {
        SoCt = soCt;
    }

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

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getMSNguoiGiao() {
        return MSNguoiGiao;
    }

    public void setMSNguoiGiao(String MSNguoiGiao) {
        this.MSNguoiGiao = MSNguoiGiao;
    }

    public String getTenNguoiGiao() {
        return TenNguoiGiao;
    }

    public void setTenNguoiGiao(String tenNguoiGiao) {
        TenNguoiGiao = tenNguoiGiao;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getTenLoaiHang() {
        return TenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        TenLoaiHang = tenLoaiHang;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDvt() {
        return Dvt;
    }

    public void setDvt(String dvt) {
        Dvt = dvt;
    }

    public double getSL() {
        return SL;
    }

    public void setSL(double SL) {
        this.SL = SL;
    }

    public double getGiaban() {
        return Giaban;
    }

    public void setGiaban(double giaban) {
        Giaban = giaban;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public int getLoaiDiscount() {
        return LoaiDiscount;
    }

    public void setLoaiDiscount(int loaiDiscount) {
        LoaiDiscount = loaiDiscount;
    }

    public double getGiatriDiscount() {
        return GiatriDiscount;
    }

    public void setGiatriDiscount(double giatriDiscount) {
        GiatriDiscount = giatriDiscount;
    }

    public double getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(double chietKhau) {
        ChietKhau = chietKhau;
    }

    public double getThue() {
        return Thue;
    }

    public void setThue(double thue) {
        Thue = thue;
    }

    public double getThanh_Tien() {
        return Thanh_Tien;
    }

    public void setThanh_Tien(double thanh_Tien) {
        Thanh_Tien = thanh_Tien;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public ChiTietNhapHangInfo() {
        Ngay = null;
        SoCt = "";
        SupplierID = "";
        CompanyName = "";
        MSK = "";
        Tenkho = "";
        EmployeeID = "";
        EmployeeName = "";
        MSNguoiGiao = "";
        TenNguoiGiao = "";
        CategoryID = "";
        TenLoaiHang = "";
        ProductID = "";
        ProductName = "";
        Dvt = "";
        SL = 0;
        Giaban = 0;
        dongia = 0;
        LoaiDiscount = 0;
        GiatriDiscount = 0;
        ChietKhau = 0;
        Thue = 0;
        Thanh_Tien = 0;
        DiaChi = "";
    }

    public ChiTietNhapHangInfo getChiTietNhapHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ChiTietNhapHangInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ChiTietNhapHangInfo> getListChiTietNhapHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ChiTietNhapHangInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
