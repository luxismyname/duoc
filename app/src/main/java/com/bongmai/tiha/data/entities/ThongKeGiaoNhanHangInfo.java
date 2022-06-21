package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeGiaoNhanHangInfo implements Serializable {

    public String SoCt ;
    public Date Ngay ;
    public String SupplierID ;
    public String CompanyName ;
    public String DienThoai ;
    public String MSNguoigiao ;
    public String TenNguoigiao ;
    public String DDBH ;
    public String TenDDBH ;
    public String Ngaygiogiao ;
    public String DiaChi ;
    public String Diachigiaohang ;
    public String Nguoigo ;
    public String Ghichu ;
    public String TaiXe ;
    public String TenTaiXe ;
    public String SoXe ;
    public double SoLuong ;
    public double TienHang ;
    public double TienThue ;
    public double ThanhTien ;
    public String MaTrangThai ;
    public String TenTrangThai ;
    public String MaTrangThaiCu;
    public double TrongLuong;
    public double TheTich;


    public String getSoCt() {
        return SoCt;
    }

    public void setSoCt(String soCt) {
        SoCt = soCt;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
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

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getMSNguoigiao() {
        return MSNguoigiao;
    }

    public void setMSNguoigiao(String MSNguoigiao) {
        this.MSNguoigiao = MSNguoigiao;
    }

    public String getTenNguoigiao() {
        return TenNguoigiao;
    }

    public void setTenNguoigiao(String tenNguoigiao) {
        TenNguoigiao = tenNguoigiao;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getTenDDBH() {
        return TenDDBH;
    }

    public void setTenDDBH(String tenDDBH) {
        TenDDBH = tenDDBH;
    }

    public String getNgaygiogiao() {
        return Ngaygiogiao;
    }

    public void setNgaygiogiao(String ngaygiogiao) {
        Ngaygiogiao = ngaygiogiao;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDiachigiaohang() {
        return Diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        Diachigiaohang = diachigiaohang;
    }

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public String getTaiXe() {
        return TaiXe;
    }

    public void setTaiXe(String taiXe) {
        TaiXe = taiXe;
    }

    public String getTenTaiXe() {
        return TenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        TenTaiXe = tenTaiXe;
    }

    public String getSoXe() {
        return SoXe;
    }

    public void setSoXe(String soXe) {
        SoXe = soXe;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public double getTienHang() {
        return TienHang;
    }

    public void setTienHang(double tienHang) {
        TienHang = tienHang;
    }

    public double getTienThue() {
        return TienThue;
    }

    public void setTienThue(double tienThue) {
        TienThue = tienThue;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getMaTrangThai() {
        return MaTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        MaTrangThai = maTrangThai;
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    public String getMaTrangThaiCu() {
        return MaTrangThaiCu;
    }

    public void setMaTrangThaiCu(String maTrangThaiCu) {
        MaTrangThaiCu = maTrangThaiCu;
    }

    public double getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(double trongLuong) {
        TrongLuong = trongLuong;
    }

    public double getTheTich() {
        return TheTich;
    }

    public void setTheTich(double theTich) {
        TheTich = theTich;
    }

    public ThongKeGiaoNhanHangInfo() {
            SoCt = "";
            Ngay = null;
            SupplierID = "";
            CompanyName = "";
            DienThoai = "";
            MSNguoigiao = "";
            TenNguoigiao = "";
            DDBH = "";
            TenDDBH = "";
            Ngaygiogiao = "";
            DiaChi = "";
            Diachigiaohang = "";
            Nguoigo = "";
            Ghichu = "";
            TaiXe = "";
            TenTaiXe = "";
            SoXe = "";
            SoLuong = 0.0;
            TienHang = 0.0;
            TienThue = 0.0;
            ThanhTien = 0.0;
            MaTrangThai = "";
            TenTrangThai = "";
            TrongLuong = 0.0;
            TheTich = 0.0;
    }

    public ThongKeGiaoNhanHangInfo getDanhSachGiaoNhan(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ThongKeGiaoNhanHangInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ThongKeGiaoNhanHangInfo> getListDanhSachGiaoNhan(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ThongKeGiaoNhanHangInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

}
