package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class LuongNVGHTheoKMInfo {

    public String MSChuyenDi;

    public Date Ngay;

    public String TenNVGH;

    public String DCGH;

    public String DCBD;

    public double QuangDuongThuc;

    public int LanDiSo;

    public double Bonus;

    public double TongQuangDuong;

    public double Sotiennhap;

    public String getMSChuyenDi() {
        return MSChuyenDi;
    }

    public void setMSChuyenDi(String MSChuyenDi) {
        this.MSChuyenDi = MSChuyenDi;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getTenNVGH() {
        return TenNVGH;
    }

    public void setTenNVGH(String tenNVGH) {
        TenNVGH = tenNVGH;
    }

    public String getDCGH() {
        return DCGH;
    }

    public void setDCGH(String DCGH) {
        this.DCGH = DCGH;
    }

    public String getDCBD() {
        return DCBD;
    }

    public void setDCBD(String DCBD) {
        this.DCBD = DCBD;
    }

    public double getQuangDuongThuc() {
        return QuangDuongThuc;
    }

    public void setQuangDuongThuc(double quangDuongThuc) {
        QuangDuongThuc = quangDuongThuc;
    }

    public int getLanDiSo() {
        return LanDiSo;
    }

    public void setLanDiSo(int lanDiSo) {
        LanDiSo = lanDiSo;
    }

    public double getBonus() {
        return Bonus;
    }

    public void setBonus(double bonus) {
        Bonus = bonus;
    }

    public double getTongQuangDuong() {
        return TongQuangDuong;
    }

    public void setTongQuangDuong(double tongQuangDuong) {
        TongQuangDuong = tongQuangDuong;
    }

    public double getSotiennhap() {
        return Sotiennhap;
    }

    public void setSotiennhap(double sotiennhap) {
        Sotiennhap = sotiennhap;
    }

    public LuongNVGHTheoKMInfo getLuongNVGHTheoKM(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<LuongNVGHTheoKMInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<LuongNVGHTheoKMInfo> getListLuongNVGHTheoKM(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<LuongNVGHTheoKMInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

}
