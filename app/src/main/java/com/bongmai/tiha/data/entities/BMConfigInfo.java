package com.bongmai.tiha.data.entities;

public class BMConfigInfo {
    private String ChiNhanh;
    private String MyPhone;
    private String KhachHangMacDinh;
    private String Company;
    private String Address;
    private String Tel;
    private String LuuY;

    public String getChiNhanh() {
        return ChiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        ChiNhanh = chiNhanh;
    }

    public String getMyPhone() {
        return MyPhone;
    }

    public void setMyPhone(String myPhone) {
        MyPhone = myPhone;
    }

    public String getKhachHangMacDinh() {
        return KhachHangMacDinh;
    }

    public void setKhachHangMacDinh(String khachHangMacDinh) {
        KhachHangMacDinh = khachHangMacDinh;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getLuuY() {
        return LuuY;
    }

    public void setLuuY(String luuY) {
        LuuY = luuY;
    }

    public BMConfigInfo() {
        ChiNhanh = "";
        MyPhone = "";
        KhachHangMacDinh = "";
        Company = "";
        Address = "";
        Tel = "";
        LuuY = "";
    }
}
