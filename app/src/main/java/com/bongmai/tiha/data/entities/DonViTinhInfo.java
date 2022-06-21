package com.bongmai.tiha.data.entities;

import java.util.ArrayList;
import java.util.List;

public class DonViTinhInfo {
    private int DonViTinhID;
    private String MaDonViTinh;
    private String TenDonViTinh;



    public int getDonViTinhID() {
        return DonViTinhID;
    }

    public void setDonViTinhID(int donViTinhID) {
        DonViTinhID = donViTinhID;
    }

    public String getMaDonViTinh() {
        return MaDonViTinh;
    }

    public void setMaDonViTinh(String maDonViTinh) {
        MaDonViTinh = maDonViTinh;
    }

    public String getTenDonViTinh() {
        return TenDonViTinh;
    }

    public void setTenDonViTinh(String tenDonViTinh) {
        TenDonViTinh = tenDonViTinh;
    }

    public DonViTinhInfo(int donViTinhID, String tenDonViTinh) {
        DonViTinhID = donViTinhID;
        TenDonViTinh = tenDonViTinh;
    }

    public DonViTinhInfo() {
    }

    public static List<DonViTinhInfo> loadListDonViTinhMau() {
        List<DonViTinhInfo> listResult = new ArrayList<>();
        listResult.add(new DonViTinhInfo(1, "Bịch"));
        listResult.add(new DonViTinhInfo(2, "Bình"));
        listResult.add(new DonViTinhInfo(3, "Bộ"));
        listResult.add(new DonViTinhInfo(4, "Cái"));
        listResult.add(new DonViTinhInfo(5, "Cặp"));
        listResult.add(new DonViTinhInfo(6, "Cây"));
        listResult.add(new DonViTinhInfo(7, "Con"));
        listResult.add(new DonViTinhInfo(8, "Cục"));
        listResult.add(new DonViTinhInfo(9, "Cụm"));
        listResult.add(new DonViTinhInfo(10, "Cuộn"));
        listResult.add(new DonViTinhInfo(11, "Chai"));
        listResult.add(new DonViTinhInfo(12, "Chiếc"));
        listResult.add(new DonViTinhInfo(13, "Gói"));
        listResult.add(new DonViTinhInfo(14, "Gram"));
        listResult.add(new DonViTinhInfo(15, "Hộp"));
        listResult.add(new DonViTinhInfo(16, "Kg"));
        listResult.add(new DonViTinhInfo(17, "Khối"));
        listResult.add(new DonViTinhInfo(18, "Lần"));
        listResult.add(new DonViTinhInfo(19, "Lít"));
        listResult.add(new DonViTinhInfo(20, "Lọ"));
        listResult.add(new DonViTinhInfo(21, "Lon"));
        listResult.add(new DonViTinhInfo(22, "Lốc"));
        listResult.add(new DonViTinhInfo(23, "m2"));
        listResult.add(new DonViTinhInfo(24, "Máy"));
        listResult.add(new DonViTinhInfo(25, "Mét"));
        listResult.add(new DonViTinhInfo(26, "Ống"));
        listResult.add(new DonViTinhInfo(27, "Phần"));
        listResult.add(new DonViTinhInfo(28, "Sợi"));
        listResult.add(new DonViTinhInfo(29, "Tấm"));
        listResult.add(new DonViTinhInfo(30, "Týp"));
        listResult.add(new DonViTinhInfo(31, "Thanh"));
        listResult.add(new DonViTinhInfo(32, "Thẻ"));
        listResult.add(new DonViTinhInfo(33, "Thùng"));
        listResult.add(new DonViTinhInfo(34, "Viên"));
        return listResult;
    }
}
