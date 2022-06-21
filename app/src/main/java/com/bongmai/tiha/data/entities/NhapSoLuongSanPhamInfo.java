package com.bongmai.tiha.data.entities;

import java.io.Serializable;
import java.util.Date;

public class NhapSoLuongSanPhamInfo implements Serializable {
    private String SoCT;

    private Date Ngay;

    private String Product_ID;

    private String Product_Name;

    private String MaKho;

    private String SupplierID;

    private Double dongia;

    private Double Thue;

    private String Ghichu;

    private Double SL;

    private Double Giaban;

    private int LoaiDiscount;

    private double GiatriDiscount;

    private Double Thanh_Tien;

    private String PromotionID;

    private String Dvt;

    private double TienThue;

    private Double SoLuongThuc;

    private Double DonGiaThuc;

    public String getSoCT() {
        return SoCT;
    }

    public void setSoCT(String soCT) {
        SoCT = soCT;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public Double getThue() {
        return Thue;
    }

    public void setThue(Double thue) {
        Thue = thue;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public Double getSL() {
        return SL;
    }

    public void setSL(Double SL) {
        this.SL = SL;
    }

    public Double getGiaban() {
        return Giaban;
    }

    public void setGiaban(Double giaban) {
        Giaban = giaban;
    }

    public int getLoaiDiscount() {
        return LoaiDiscount;
    }

    public void setLoaiDiscount(int loaiDiscount) {
        LoaiDiscount = loaiDiscount;
    }

    public double getGiatriDiscount() {
        return GiatriDiscount;
    }

    public void setGiatriDiscount(double giatriDiscount) {
        GiatriDiscount = giatriDiscount;
    }

    public Double getThanh_Tien() {
        return Thanh_Tien;
    }

    public void setThanh_Tien(Double thanh_Tien) {
        Thanh_Tien = thanh_Tien;
    }

    public String getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(String promotionID) {
        PromotionID = promotionID;
    }

    public String getDvt() {
        return Dvt;
    }

    public void setDvt(String dvt) {
        Dvt = dvt;
    }

    public double getTienThue() {
        return TienThue;
    }

    public void setTienThue(double tienThue) {
        TienThue = tienThue;
    }

    public Double getSoLuongThuc() {
        return SoLuongThuc;
    }

    public void setSoLuongThuc(Double soLuongThuc) {
        SoLuongThuc = soLuongThuc;
    }

    public Double getDonGiaThuc() {
        return DonGiaThuc;
    }

    public void setDonGiaThuc(Double donGiaThuc) {
        DonGiaThuc = donGiaThuc;
    }

    public NhapSoLuongSanPhamInfo() {
        SoCT = "";
        Ngay = null;
        Product_ID = "";
        Product_Name = "";
        MaKho = "";
        SupplierID = "";
        dongia = 0.0;
        Thue = 0.0;
        Ghichu = "";
        this.SL = 0.0;
        Giaban = 0.0;
        LoaiDiscount = 0;
        GiatriDiscount = 0;
        Thanh_Tien = 0.0;
        PromotionID = "";
        Dvt = "";
        TienThue = 0;
        SoLuongThuc = 0.0;
        DonGiaThuc = 0.0;
    }

    public NhapSoLuongSanPhamInfo(PhieuXuatInfo phieuXuatInfo, VattuxuatInfo vattuxuatInfo) {
        SoCT = phieuXuatInfo.getSoCt();
        Ngay = phieuXuatInfo.getNgay();
        Product_ID = vattuxuatInfo.getProduct_ID();
        Product_Name = vattuxuatInfo.getProduct_Name();
        MaKho = phieuXuatInfo.getMSK();
        SupplierID = phieuXuatInfo.getSupplier_ID();
        dongia = vattuxuatInfo.getDongia();
        Thue = vattuxuatInfo.getThue();
        Ghichu = vattuxuatInfo.getGhichu();
        SL = vattuxuatInfo.getSL();
        Giaban = vattuxuatInfo.getGiaban();
        LoaiDiscount = vattuxuatInfo.getLoaiDiscount();
        GiatriDiscount = vattuxuatInfo.getGiatriDiscount();
        Thanh_Tien = vattuxuatInfo.getThanh_Tien();
        PromotionID = vattuxuatInfo.getPromotionID();
        Dvt = vattuxuatInfo.getDvt();
        TienThue = vattuxuatInfo.getTienThue();
        SoLuongThuc = vattuxuatInfo.getSoLuongThuc();
        DonGiaThuc = vattuxuatInfo.getDonGiaThuc();

    }
}
