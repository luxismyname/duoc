package com.bongmai.tiha.data.entities;

public class ChiNhanhInfo {
    private String ChiNhanhID;
    private String TenChiNhanh;
    private String Diachi;
    private String DienThoai;

    public String getChiNhanhID() {
        return ChiNhanhID;
    }

    public void setChiNhanhID(String chiNhanhID) {
        ChiNhanhID = chiNhanhID;
    }

    public String getTenChiNhanh() {
        return TenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        TenChiNhanh = tenChiNhanh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public ChiNhanhInfo() {
        ChiNhanhID = "";
        TenChiNhanh = "";
        Diachi = "";
        DienThoai = "";
    }
}
