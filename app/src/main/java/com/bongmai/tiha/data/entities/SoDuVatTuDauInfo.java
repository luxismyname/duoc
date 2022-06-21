package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SoDuVatTuDauInfo implements Serializable {

    private int ID;
    private String MSk;
    private String TenKho;
    private String Product_id;
    private String Product_Name;
    private String Ngay;
    private String LO;
    private double DONGIA;
    private double SODUDAU;
    private String Ngaygio;
    private String ModifiedDate;
    private String ModifiedBy;
    private int TienThue;
    private double SLNguyen;
    private int DonGiaNguyen;
    private String DVT;
    private String ProductName;
    private double ThanhTien;
    private String Size;
    private String HanSuDung;
    private String TaiKhoan;
    private int SLDVT2;
    private int SLDVT3;
    private String Nguoigo;
    private double SoLuongThuc;
    private double DonGiaThuc;

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public double getDonGiaThuc() {
        return DonGiaThuc;
    }

    public double getSoLuongThuc() {
        return SoLuongThuc;
    }

    public void setSoLuongThuc(double soLuongThuc) {
        SoLuongThuc = soLuongThuc;
    }

    public void setDonGiaThuc(double donGiaThuc) {
        DonGiaThuc = donGiaThuc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMSk() {
        return MSk;
    }

    public void setMSk(String MSk) {
        this.MSk = MSk;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getLO() {
        return LO;
    }

    public void setLO(String LO) {
        this.LO = LO;
    }

    public double getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(double DONGIA) {
        this.DONGIA = DONGIA;
    }

    public double getSODUDAU() {
        return SODUDAU;
    }

    public void setSODUDAU(double SODUDAU) {
        this.SODUDAU = SODUDAU;
    }

    public String getNgaygio() {
        return Ngaygio;
    }

    public void setNgaygio(String ngaygio) {
        Ngaygio = ngaygio;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public int getTienThue() {
        return TienThue;
    }

    public void setTienThue(int tienThue) {
        TienThue = tienThue;
    }

    public double getSLNguyen() {
        return SLNguyen;
    }

    public void setSLNguyen(double SLNguyen) {
        this.SLNguyen = SLNguyen;
    }

    public int getDonGiaNguyen() {
        return DonGiaNguyen;
    }

    public void setDonGiaNguyen(int donGiaNguyen) {
        DonGiaNguyen = donGiaNguyen;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }



    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        HanSuDung = hanSuDung;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public int getSLDVT2() {
        return SLDVT2;
    }

    public void setSLDVT2(int SLDVT2) {
        this.SLDVT2 = SLDVT2;
    }

    public int getSLDVT3() {
        return SLDVT3;
    }

    public void setSLDVT3(int SLDVT3) {
        this.SLDVT3 = SLDVT3;
    }

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public SoDuVatTuDauInfo getSoDuVatTu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<SoDuVatTuDauInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<SoDuVatTuDauInfo> getListSoDuVatTu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<SoDuVatTuDauInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
