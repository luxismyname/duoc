package com.bongmai.tiha.data.entities;

import java.io.Serializable;

public class TinhThanhPhoInfo implements Serializable {

    private String MaTinh;
    private String TenTinhVietTat;
    private String ChiTietTenTinh;

    public String getMaTinh() {
        return MaTinh;
    }

    public void setMaTinh(String maTinh) {
        MaTinh = maTinh;
    }

    public String getTenTinhVietTat() {
        return TenTinhVietTat;
    }

    public void setTenTinhVietTat(String tenTinhVietTat) {
        TenTinhVietTat = tenTinhVietTat;
    }

    public String getChiTietTenTinh() {
        return ChiTietTenTinh;
    }

    public void setChiTietTenTinh(String chiTietTenTinh) {
        ChiTietTenTinh = chiTietTenTinh;
    }
}
