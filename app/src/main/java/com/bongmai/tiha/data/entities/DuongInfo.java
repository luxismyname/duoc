package com.bongmai.tiha.data.entities;

import java.io.Serializable;

public class DuongInfo implements Serializable {

    private String TenDuongVietTat;
    private String ChiTietTenDuong;
    private String MaTinh;

    public String getTenDuongVietTat() {
        return TenDuongVietTat;
    }

    public void setTenDuongVietTat(String tenDuongVietTat) {
        TenDuongVietTat = tenDuongVietTat;
    }

    public String getChiTietTenDuong() {
        return ChiTietTenDuong;
    }

    public void setChiTietTenDuong(String chiTietTenDuong) {
        ChiTietTenDuong = chiTietTenDuong;
    }

    public String getMaTinh() {
        return MaTinh;
    }

    public void setMaTinh(String maTinh) {
        MaTinh = maTinh;
    }
}
