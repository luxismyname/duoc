package com.bongmai.tiha.data.entities;

import java.util.HashMap;
import java.util.Map;

public class ProductCondition {

    private long Begin;
    private long End;
    private String NhomLoaiHang;
    private String UserName;
    private String TextSearch;

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

    public String getNhomLoaiHang() {
        return NhomLoaiHang;
    }

    public void setNhomLoaiHang(String nhomLoaiHang) {
        NhomLoaiHang = nhomLoaiHang;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTextSearch() {
        return TextSearch;
    }

    public void setTextSearch(String textSearch) {
        TextSearch = textSearch;
    }

    public ProductCondition() {
        Begin = 0;
        End = 0;
        NhomLoaiHang = "";
        UserName = "";
        TextSearch = "";
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("Begin", String.valueOf(Begin));
        params.put("End", String.valueOf(End));
        params.put("NhomLoaiHang", NhomLoaiHang);
        params.put("UserName", UserName);
        params.put("TextSearch", TextSearch);
        return params;
    }

}
