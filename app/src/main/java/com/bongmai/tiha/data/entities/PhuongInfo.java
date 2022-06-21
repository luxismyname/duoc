package com.bongmai.tiha.data.entities;

import java.io.Serializable;

public class PhuongInfo implements Serializable {

    private String TenPhuongVietTat;
    private String ChiTietTenPhuong;
    private String MaQuan;

    public String getTenPhuongVietTat() {
        return TenPhuongVietTat;
    }

    public void setTenPhuongVietTat(String tenPhuongVietTat) {
        TenPhuongVietTat = tenPhuongVietTat;
    }

    public String getChiTietTenPhuong() {
        return ChiTietTenPhuong;
    }

    public void setChiTietTenPhuong(String chiTietTenPhuong) {
        ChiTietTenPhuong = chiTietTenPhuong;
    }

    public String getMaQuan() {
        return MaQuan;
    }

    public void setMaQuan(String maQuan) {
        MaQuan = maQuan;
    }
}
