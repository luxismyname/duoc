package com.bongmai.tiha.data.entities;

public class HangBanChayInfo {
    private String STT;
    private String TenSanPham;
    private double GiaTri;

    public HangBanChayInfo(String STT, String tenSanPham, double giaTri) {
        this.STT = STT;
        TenSanPham = tenSanPham;
        GiaTri = giaTri;
    }

    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public double getGiaTri() {
        return GiaTri;
    }

    public void setGiaTri(double giaTri) {
        GiaTri = giaTri;
    }
}
