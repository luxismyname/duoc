package com.bongmai.tiha.data.entities;

public class KhachHangInfo {
    private String Supplier_ID;
    private String Company_Name;
    private String So;
    private String Duong;
    private String Phuong;
    private String Quan;
    private String Tinh;
    private String Address;
    private String Phone;
    private String MaSP;
    private String SPSuDung;
    private String X;
    private String Y;

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

    public String getSo() {
        return So;
    }

    public void setSo(String so) {
        So = so;
    }

    public String getDuong() {
        return Duong;
    }

    public void setDuong(String duong) {
        Duong = duong;
    }

    public String getPhuong() {
        return Phuong;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public String getQuan() {
        return Quan;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

    public String getTinh() {
        return Tinh;
    }

    public void setTinh(String tinh) {
        Tinh = tinh;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getSPSuDung() {
        return SPSuDung;
    }

    public void setSPSuDung(String SPSuDung) {
        this.SPSuDung = SPSuDung;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public KhachHangInfo() {
        Supplier_ID = "";
        Company_Name = "";
        So = "";
        Duong = "";
        Phuong = "";
        Quan = "";
        Tinh = "";
        Phone = "";
        MaSP = "";
        SPSuDung = "";
        Address = "";
    }
}
