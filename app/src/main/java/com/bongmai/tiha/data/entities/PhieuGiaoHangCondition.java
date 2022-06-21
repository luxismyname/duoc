package com.bongmai.tiha.data.entities;

import java.util.HashMap;
import java.util.Map;

public class PhieuGiaoHangCondition {

    private String UserName;
    private String TextSearch;
    private String ListKho;
    private String NgayBD;
    private String NgayKT;
    private String ListTenKho;
    private String TrangThai;

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

    public String getListKho() {
        return ListKho;
    }

    public void setListKho(String listKho) {
        ListKho = listKho;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String ngayBD) {
        NgayBD = ngayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String ngayKT) {
        NgayKT = ngayKT;
    }

    public String getListTenKho() {
        return ListTenKho;
    }

    public void setListTenKho(String listTenKho) {
        ListTenKho = listTenKho;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public PhieuGiaoHangCondition() {
        UserName = "";
        TextSearch = "";
        ListKho = "";
        NgayBD = "";
        NgayKT = "";
        TrangThai = "";
        ListTenKho = "";
    }

    public PhieuGiaoHangCondition(PhieuGiaoHangCondition item) {
        UserName = item.getUserName();
        TextSearch = item.getTextSearch();
        ListKho = item.getListKho();
        NgayBD = String.valueOf(item.getNgayBD());
        NgayKT = String.valueOf(item.getNgayKT());
        TrangThai = item.getTrangThai();
        //ListTenKho = item.getListTenKho();
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("UserName", UserName);
        params.put("TextSearch", TextSearch);
        params.put("ListKho", ListKho);
        params.put("NgayBD", NgayBD);
        params.put("NgayKT", NgayKT);
        params.put("TrangThai", TrangThai);
        return params;
    }

}
