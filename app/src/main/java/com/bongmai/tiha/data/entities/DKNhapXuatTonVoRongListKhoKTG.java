package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DKNhapXuatTonVoRongListKhoKTG {
    private String Category_ID;

    private String MaSP;

    private String Product_Name;

    private String DonVitinh;

    private double DauKy;

    private double NhapTuNCC;

    private double NhapTuKH;

    private double TraTheChan;

    private double TraVoMuon;

    private double MuaVo;

    private double CDen;

    private double NhapKhac;

    private double TongNhap;

    private double TraNCC;

    private double XuatMuon;

    private double XuatTheChan;

    private double XuatKhac;

    private double CDi;

    private double TongXuat;

    private double DieuChinh;

    private double TonCuoi;

    private double ThucTeXuat;

    private double Lech;

    private double Nhap;

    private double Xuat;

    public String getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(String category_ID) {
        Category_ID = category_ID;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getDonVitinh() {
        return DonVitinh;
    }

    public void setDonVitinh(String donVitinh) {
        DonVitinh = donVitinh;
    }

    public double getDauKy() {
        return DauKy;
    }

    public void setDauKy(double dauKy) {
        DauKy = dauKy;
    }

    public double getNhapTuNCC() {
        return NhapTuNCC;
    }

    public void setNhapTuNCC(double nhapTuNCC) {
        NhapTuNCC = nhapTuNCC;
    }

    public double getNhapTuKH() {
        return NhapTuKH;
    }

    public void setNhapTuKH(double nhapTuKH) {
        NhapTuKH = nhapTuKH;
    }

    public double getTraTheChan() {
        return TraTheChan;
    }

    public void setTraTheChan(double traTheChan) {
        TraTheChan = traTheChan;
    }

    public double getTraVoMuon() {
        return TraVoMuon;
    }

    public void setTraVoMuon(double traVoMuon) {
        TraVoMuon = traVoMuon;
    }

    public double getMuaVo() {
        return MuaVo;
    }

    public void setMuaVo(double muaVo) {
        MuaVo = muaVo;
    }

    public double getCDen() {
        return CDen;
    }

    public void setCDen(double CDen) {
        this.CDen = CDen;
    }

    public double getNhapKhac() {
        return NhapKhac;
    }

    public void setNhapKhac(double nhapKhac) {
        NhapKhac = nhapKhac;
    }

    public double getTongNhap() {
        return TongNhap;
    }

    public void setTongNhap(double tongNhap) {
        TongNhap = tongNhap;
    }

    public double getTraNCC() {
        return TraNCC;
    }

    public void setTraNCC(double traNCC) {
        TraNCC = traNCC;
    }

    public double getXuatMuon() {
        return XuatMuon;
    }

    public void setXuatMuon(double xuatMuon) {
        XuatMuon = xuatMuon;
    }

    public double getXuatTheChan() {
        return XuatTheChan;
    }

    public void setXuatTheChan(double xuatTheChan) {
        XuatTheChan = xuatTheChan;
    }

    public double getXuatKhac() {
        return XuatKhac;
    }

    public void setXuatKhac(double xuatKhac) {
        XuatKhac = xuatKhac;
    }

    public double getCDi() {
        return CDi;
    }

    public void setCDi(double CDi) {
        this.CDi = CDi;
    }

    public double getTongXuat() {
        return TongXuat;
    }

    public void setTongXuat(double tongXuat) {
        TongXuat = tongXuat;
    }

    public double getDieuChinh() {
        return DieuChinh;
    }

    public void setDieuChinh(double dieuChinh) {
        DieuChinh = dieuChinh;
    }

    public double getTonCuoi() {
        return TonCuoi;
    }

    public void setTonCuoi(double tonCuoi) {
        TonCuoi = tonCuoi;
    }

    public double getThucTeXuat() {
        return ThucTeXuat;
    }

    public void setThucTeXuat(double thucTeXuat) {
        ThucTeXuat = thucTeXuat;
    }

    public double getLech() {
        return Lech;
    }

    public void setLech(double lech) {
        Lech = lech;
    }

    public double getNhap() {
        return Nhap;
    }

    public void setNhap(double nhap) {
        Nhap = nhap;
    }

    public double getXuat() {
        return Xuat;
    }

    public void setXuat(double xuat) {
        Xuat = xuat;
    }

    public DKNhapXuatTonVoRongListKhoKTG getDKNhapXuatTonVoRongListKhoKTG(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DKNhapXuatTonVoRongListKhoKTG>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DKNhapXuatTonVoRongListKhoKTG> getListDKNhapXuatTonVoRongListKhoKTG(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DKNhapXuatTonVoRongListKhoKTG>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
