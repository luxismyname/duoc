package com.bongmai.tiha.data.entities;

import java.util.ArrayList;
import java.util.List;

public class HuongCotGiaInfo {
    private int MaCotGia;
    private String TenCotGia;

    public int getMaCotGia() {
        return MaCotGia;
    }

    public void setMaCotGia(int maCotGia) {
        MaCotGia = maCotGia;
    }

    public String getTenCotGia() {
        return TenCotGia;
    }

    public void setTenCotGia(String tenCotGia) {
        TenCotGia = tenCotGia;
    }

    public HuongCotGiaInfo(int maCotGia, String tenCotGia) {
        MaCotGia = maCotGia;
        TenCotGia = tenCotGia;
    }

    public static List<HuongCotGiaInfo> GetListHuongCotGia() {
        List<HuongCotGiaInfo> listResult = new ArrayList<>();
        listResult.add(new HuongCotGiaInfo(1, "Bán lẻ C1"));
        listResult.add(new HuongCotGiaInfo(2, "ĐL C2"));
        listResult.add(new HuongCotGiaInfo(3, "Hội viên C3"));
        listResult.add(new HuongCotGiaInfo(4, "Nhập"));
        return listResult;
    }
}
