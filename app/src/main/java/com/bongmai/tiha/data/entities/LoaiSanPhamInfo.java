package com.bongmai.tiha.data.entities;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamInfo {
    private String MaLoaiSanPham;
    private String TenLoaiSanPham;

    public String getMaLoaiSanPham() {
        return MaLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        MaLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return TenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        TenLoaiSanPham = tenLoaiSanPham;
    }

    public LoaiSanPhamInfo(String maLoaiSanPham, String tenLoaiSanPham) {
        MaLoaiSanPham = maLoaiSanPham;
        TenLoaiSanPham = tenLoaiSanPham;
    }

    public static List<LoaiSanPhamInfo> loadLoaiSanPham() {
        List<LoaiSanPhamInfo> listResult = new ArrayList<>();
        listResult.add(new LoaiSanPhamInfo("NL", "Nguyên vật liệu"));
        listResult.add(new LoaiSanPhamInfo("DC", "Công cụ dụng cụ"));
        listResult.add(new LoaiSanPhamInfo("SP", "Hàng hóa"));
        listResult.add(new LoaiSanPhamInfo("LK", "Linh kiện"));
        listResult.add(new LoaiSanPhamInfo("TP", "Thành phẩm"));
        listResult.add(new LoaiSanPhamInfo("TS", "Tài sản cố định"));
        listResult.add(new LoaiSanPhamInfo("DV", "Dịch vụ"));
        listResult.add(new LoaiSanPhamInfo("NVLP", "Nguyên vật liệu phụ"));
        listResult.add(new LoaiSanPhamInfo("BTP", "Bán thành phẩm"));
        listResult.add(new LoaiSanPhamInfo("HHVH", "Hàng hóa vô hình"));
        return listResult;
    }
}
