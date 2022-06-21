package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TrangThaiLoaiPhieuInfo {

    public int ID;
    public String LoaiPhieu;
    public String MaTrangThai;
    public String TenTrangThai;
    public int STT;
    public int DuocIn;
    public int DuocXemIn;
    public Boolean KhoaPhieu;
    public int DuocXuatHoaDonDienTu;
    public Boolean PhaiNhapGhiChu;
    public Boolean DuyetPhieu;
    public int Mau;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoaiPhieu() {
        return LoaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        LoaiPhieu = loaiPhieu;
    }

    public String getMaTrangThai() {
        return MaTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        MaTrangThai = maTrangThai;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getDuocIn() {
        return DuocIn;
    }

    public void setDuocIn(int duocIn) {
        DuocIn = duocIn;
    }

    public int getDuocXemIn() {
        return DuocXemIn;
    }

    public void setDuocXemIn(int duocXemIn) {
        DuocXemIn = duocXemIn;
    }

    public Boolean getKhoaPhieu() {
        return KhoaPhieu;
    }

    public void setKhoaPhieu(Boolean khoaPhieu) {
        KhoaPhieu = khoaPhieu;
    }

    public int getDuocXuatHoaDonDienTu() {
        return DuocXuatHoaDonDienTu;
    }

    public void setDuocXuatHoaDonDienTu(int duocXuatHoaDonDienTu) {
        DuocXuatHoaDonDienTu = duocXuatHoaDonDienTu;
    }

    public Boolean getPhaiNhapGhiChu() {
        return PhaiNhapGhiChu;
    }

    public void setPhaiNhapGhiChu(Boolean phaiNhapGhiChu) {
        PhaiNhapGhiChu = phaiNhapGhiChu;
    }

    public Boolean getDuyetPhieu() {
        return DuyetPhieu;
    }

    public void setDuyetPhieu(Boolean duyetPhieu) {
        DuyetPhieu = duyetPhieu;
    }

    public int getMau() {
        return Mau;
    }

    public void setMau(int mau) {
        Mau = mau;
    }

    public TrangThaiLoaiPhieuInfo()
    {
        LoaiPhieu = MaTrangThai = "";
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    public TrangThaiLoaiPhieuInfo(TrangThaiLoaiPhieuInfo ob)
    {
        ID = ob.ID;
        LoaiPhieu = ob.LoaiPhieu;
        MaTrangThai = ob.MaTrangThai;
        TenTrangThai = ob.TenTrangThai;
        STT = ob.STT;
        DuocIn = ob.DuocIn;
        DuocXemIn = ob.DuocXemIn;
        KhoaPhieu = ob.KhoaPhieu;
        DuocXuatHoaDonDienTu = ob.DuocXuatHoaDonDienTu;
        PhaiNhapGhiChu = ob.PhaiNhapGhiChu;
        DuyetPhieu = ob.DuyetPhieu;
        Mau = ob.Mau;
    }


    public static List<TrangThaiLoaiPhieuInfo> loadTrangThai() {

        List<TrangThaiLoaiPhieuInfo> listTrangThai = new ArrayList<>();
        TrangThaiLoaiPhieuInfo trangThaiCuocGoiInfo;

        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("ALL");
        trangThaiCuocGoiInfo.setTenTrangThai("Tất cả");
        listTrangThai.add(trangThaiCuocGoiInfo);

        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("6");
        trangThaiCuocGoiInfo.setTenTrangThai("Chờ Đóng Gói");
        listTrangThai.add(trangThaiCuocGoiInfo);

        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("7");
        trangThaiCuocGoiInfo.setTenTrangThai("Đã đóng gói");
        listTrangThai.add(trangThaiCuocGoiInfo);

        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("8");
        trangThaiCuocGoiInfo.setTenTrangThai("Chờ đăng ký giao");
        listTrangThai.add(trangThaiCuocGoiInfo);


        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("9");
        trangThaiCuocGoiInfo.setTenTrangThai("Chờ giao hàng");
        listTrangThai.add(trangThaiCuocGoiInfo);


        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("10");
        trangThaiCuocGoiInfo.setTenTrangThai("Đang giao");
        listTrangThai.add(trangThaiCuocGoiInfo);


        trangThaiCuocGoiInfo = new TrangThaiLoaiPhieuInfo();
        trangThaiCuocGoiInfo.setMaTrangThai("11");
        trangThaiCuocGoiInfo.setTenTrangThai("Đã giao");
        listTrangThai.add(trangThaiCuocGoiInfo);

        return listTrangThai;
    }


    public TrangThaiLoaiPhieuInfo getTrangThaiLoaiPhieu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<TrangThaiLoaiPhieuInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<TrangThaiLoaiPhieuInfo> getListTrangThaiLoaiPhieu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<TrangThaiLoaiPhieuInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
