package com.bongmai.tiha.data.entities;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DieuKienLocInfo implements Serializable {
    private String UserName;
    private String TuNgay;
    private String DenNgay;
    private String TuNgayTuChon;
    private String DenNgayTuChon;
    private String TenMauBaoCao;
    private String ThoiGianXemBaoCao;
    private String ListKho;
    private String ListLoaiHang;
    private String ListNhanVien;
    private String ListChiNhanh;
    private String ListNhomKH;
    private int TrangThai;
    private int LoaiNhanVien;
    private String TimKiem;
    private String MaMauBaoCao;
    private String MaHang;

    public String getTuNgay() {
        return TuNgay;
    }

    public void setTuNgay(String tuNgay) {
        TuNgay = tuNgay;
    }

    public String getDenNgay() {
        return DenNgay;
    }

    public void setDenNgay(String denNgay) {
        DenNgay = denNgay;
    }

    public String getTuNgayTuChon() {
        return TuNgayTuChon;
    }

    public void setTuNgayTuChon(String tuNgayTuChon) {
        TuNgayTuChon = tuNgayTuChon;
    }

    public String getDenNgayTuChon() {
        return DenNgayTuChon;
    }

    public void setDenNgayTuChon(String denNgayTuChon) {
        DenNgayTuChon = denNgayTuChon;
    }

    public String getTenMauBaoCao() {
        return TenMauBaoCao;
    }

    public void setTenMauBaoCao(String tenMauBaoCao) {
        TenMauBaoCao = tenMauBaoCao;
    }

    public String getThoiGianXemBaoCao() {
        return ThoiGianXemBaoCao;
    }

    public void setThoiGianXemBaoCao(String thoiGianXemBaoCao) {
        ThoiGianXemBaoCao = thoiGianXemBaoCao;
    }

    public String getListKho() {
        return ListKho;
    }

    public void setListKho(String listKho) {
        ListKho = listKho;
    }

    public String getListLoaiHang() {
        return ListLoaiHang;
    }

    public void setListLoaiHang(String listLoaiHang) {
        ListLoaiHang = listLoaiHang;
    }

    public String getListNhanVien() {
        return ListNhanVien;
    }

    public void setListNhanVien(String listNhanVien) {
        ListNhanVien = listNhanVien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getLoaiNhanVien() {
        return LoaiNhanVien;
    }

    public void setLoaiNhanVien(int loaiNhanVien) {
        LoaiNhanVien = loaiNhanVien;
    }

    public String getTimKiem() {
        return TimKiem;
    }

    public void setTimKiem(String timKiem) {
        TimKiem = timKiem;
    }

    public String getMaMauBaoCao() {
        return MaMauBaoCao;
    }

    public void setMaMauBaoCao(String maMauBaoCao) {
        MaMauBaoCao = maMauBaoCao;
    }

    public String getListChiNhanh() {
        return ListChiNhanh;
    }

    public void setListChiNhanh(String listChiNhanh) {
        ListChiNhanh = listChiNhanh;
    }

    public String getListNhomKH() {
        return ListNhomKH;
    }

    public void setListNhomKH(String listNhomKH) {
        ListNhomKH = listNhomKH;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String maHang) {
        MaHang = maHang;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public DieuKienLocInfo() {
        TuNgay = "";
        DenNgay = "";
        TuNgayTuChon = null;
        DenNgayTuChon = null;
        TenMauBaoCao = "";
        ThoiGianXemBaoCao = "";
        ListKho = "";
        ListLoaiHang = "";
        ListNhanVien = "";
        TrangThai = 0;
        LoaiNhanVien = 0;
        TimKiem = "";
        MaMauBaoCao = "";
        ListChiNhanh = "";
        ListNhomKH = "";
        UserName = "";
    }

    //sửa param theo này để set mặc định là all kho và all loại hàng
    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("TuNgay", TuNgay);
        params.put("DenNgay", DenNgay);
        params.put("ListKho", TextUtils.isEmpty(ListKho) ? "" : ListKho );
        params.put("ListLoaiHang", TextUtils.isEmpty(ListLoaiHang) ? "" : ListLoaiHang);
        params.put("ListNhanVien", TextUtils.isEmpty(ListNhanVien) ? "" : ListNhanVien);
        params.put("TrangThai", String.valueOf(TrangThai));
        params.put("ListChiNhanh", TextUtils.isEmpty(ListChiNhanh) ? "" : ListChiNhanh);
        params.put("ListNhomKH", TextUtils.isEmpty(ListNhomKH) ? "" : ListNhomKH);
        params.put("UserName", UserName);
        return params;
    }
}
