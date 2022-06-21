package com.bongmai.tiha.data.entities;

import java.util.HashMap;
import java.util.Map;

public class SupplierCondition {
    private long Begin;
    private long End;
    private String NhomKhachHang;
    private String UserName;
    private String DDBH;
    private String ListChiNhanh;
    private String TextSearch;
    private String CompanyName;
    private String So;
    private String Duong;
    private String Phuong;
    private String Quan;
    private String Tinh;
    private String Phone;

    public long getBegin() {
        return Begin;
    }

    public void setBegin(long begin) {
        Begin = begin;
    }

    public long getEnd() {
        return End;
    }

    public void setEnd(long end) {
        End = end;
    }

    public String getNhomKhachHang() {
        return NhomKhachHang;
    }

    public void setNhomKhachHang(String nhomKhachHang) {
        NhomKhachHang = nhomKhachHang;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getListChiNhanh() {
        return ListChiNhanh;
    }

    public void setListChiNhanh(String listChiNhanh) {
        ListChiNhanh = listChiNhanh;
    }

    public String getTextSearch() {
        return TextSearch;
    }

    public void setTextSearch(String textSearch) {
        TextSearch = textSearch;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
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

    public SupplierCondition() {
        Begin = 0;
        End = 0;
        NhomKhachHang = "";
        UserName = "";
        this.DDBH = "";
        ListChiNhanh = "";
        TextSearch = "";
        CompanyName = "";
        So = "";
        Duong = "";
        Phuong = "";
        Quan = "";
        Tinh = "";
        Phone = "";
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("Begin", String.valueOf(Begin));
        params.put("End", String.valueOf(End));
        params.put("NhomKhachHang", NhomKhachHang);
        params.put("UserName", UserName);
        params.put("ListChiNhanh", ListChiNhanh);
        params.put("TextSearch", TextSearch);
        params.put("CompanyName", CompanyName);
        params.put("So", So);
        params.put("Duong", Duong);
        params.put("Phuong", Phuong);
        params.put("Quan", Quan);
        params.put("Tinh", Tinh);
        params.put("Phone", Tinh);
        return params;
    }
}
