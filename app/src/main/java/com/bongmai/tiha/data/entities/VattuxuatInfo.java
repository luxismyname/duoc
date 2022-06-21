package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VattuxuatInfo implements Serializable {

    private static int stt = 0;

    private String tangSTT;

    @Override
    public String toString() {
        return stt +" " + Product_Name;
    }

    private String SoCT;

    private String Product_ID;

    private String Product_Name;

    private String MaKho;

    private String SupplierID;

    private Double dongia;

    private Double Thue;

    private String Ghichu;

    private String PO;

    private int BH;

    private Double SL;

    private double Von_Cost;

    private String ContractID;

    private Timestamp Thutu;

    private Double Giaban;

    private int LoaiDiscount;

    private double GiatriDiscount;

    private String LydoDiscount;

    private Double Thanh_Tien;

    private String Loaitien;

    private double TG;

    private String PromotionID;

    private String LO;

    private String Dvt;

    private Timestamp ModifiedDate;

    private String ModifiedBy;

    private float GiatriDiscountP;

    private double DongiaThue;

    private double DongiaCoThue;

    private double SLNguyen;

    private double Hesochuyendoi;

    private double DongiaNguyen;

    private String EmployeeID;

    private String MSKP;

    private String MaTK;

    private double TienThue;

    private double Diem;

    private String VoGasTraVe;

    private String MayBo;

    private String VoNhanVe;

    private double TongChietKhau;

    private String DiaChiKH;

    private String DDBH;

    private Double SoLuongThuc;

    private Double DonGiaThuc;

    private double SLTra;

    private double ThanhTienChietKhau;

    private double Chiphivanchuyen;

    private double Chiphikhac;

    private double SLLe;

    private long IDVatTuXuat;

    private String QuyDoi;

    private double Dai;

    private double Rong;

    private List<VattuxuatInfo> listVattuxuat;

    public List<VattuxuatInfo> getListVattuxuat() {
        return listVattuxuat;
    }

    public void setListVattuxuat(List<VattuxuatInfo> listVattuxuat) {
        this.listVattuxuat = listVattuxuat;
    }

    public String getSoCT() {
        return SoCT;
    }

    public void setSoCT(String soCT) {
        SoCT = soCT;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public String getProduct_Name() {
        return  Product_Name;
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
        return dongia == null ? 0 : dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public Double getThue() {
        return Thue == null ? 0 : Thue;
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

    public String getPO() {
        return PO;
    }

    public void setPO(String PO) {
        this.PO = PO;
    }

    public int getBH() {
        return BH;
    }

    public void setBH(int BH) {
        this.BH = BH;
    }

    public Double getSL() {
        return SL == null ? 0 : SL;
    }

    public void setSL(Double SL) {
        this.SL = SL;
    }

    public double getVon_Cost() {
        return Von_Cost;
    }

    public void setVon_Cost(double von_Cost) {
        Von_Cost = von_Cost;
    }

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String contractID) {
        ContractID = contractID;
    }

    public Timestamp getThutu() {
        return Thutu;
    }

    public void setThutu(Timestamp thutu) {
        Thutu = thutu;
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

    public String getLydoDiscount() {
        return LydoDiscount;
    }

    public void setLydoDiscount(String lydoDiscount) {
        LydoDiscount = lydoDiscount;
    }

    public Double getThanh_Tien() {
        return Thanh_Tien == null ? 0 : Thanh_Tien;
    }

    public void setThanh_Tien(Double thanh_Tien) {
        Thanh_Tien = thanh_Tien;
    }

    public String getLoaitien() {
        return Loaitien;
    }

    public void setLoaitien(String loaitien) {
        Loaitien = loaitien;
    }

    public double getTG() {
        return TG;
    }

    public void setTG(double TG) {
        this.TG = TG;
    }

    public String getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(String promotionID) {
        PromotionID = promotionID;
    }

    public String getLO() {
        return LO;
    }

    public void setLO(String LO) {
        this.LO = LO;
    }

    public String getDvt() {
        return Dvt;
    }

    public void setDvt(String dvt) {
        Dvt = dvt;
    }

    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public float getGiatriDiscountP() {
        return GiatriDiscountP;
    }

    public void setGiatriDiscountP(float giatriDiscountP) {
        GiatriDiscountP = giatriDiscountP;
    }

    public double getDongiaThue() {
        return DongiaThue;
    }

    public void setDongiaThue(double dongiaThue) {
        DongiaThue = dongiaThue;
    }

    public double getDongiaCoThue() {
        return DongiaCoThue;
    }

    public void setDongiaCoThue(double dongiaCoThue) {
        DongiaCoThue = dongiaCoThue;
    }

    public double getSLNguyen() {
        return SLNguyen;
    }

    public void setSLNguyen(double SLNguyen) {
        this.SLNguyen = SLNguyen;
    }

    public double getHesochuyendoi() {
        return Hesochuyendoi;
    }

    public void setHesochuyendoi(double hesochuyendoi) {
        Hesochuyendoi = hesochuyendoi;
    }

    public double getDongiaNguyen() {
        return DongiaNguyen;
    }

    public void setDongiaNguyen(double dongiaNguyen) {
        DongiaNguyen = dongiaNguyen;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getMSKP() {
        return MSKP;
    }

    public void setMSKP(String MSKP) {
        this.MSKP = MSKP;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String maTK) {
        MaTK = maTK;
    }

    public double getTienThue() {
        return TienThue;
    }

    public void setTienThue(double tienThue) {
        TienThue = tienThue;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }

    public String getVoGasTraVe() {
        return VoGasTraVe;
    }

    public void setVoGasTraVe(String voGasTraVe) {
        VoGasTraVe = voGasTraVe;
    }

    public String getMayBo() {
        return MayBo;
    }

    public void setMayBo(String mayBo) {
        MayBo = mayBo;
    }

    public String getVoNhanVe() {
        return VoNhanVe;
    }

    public void setVoNhanVe(String voNhanVe) {
        VoNhanVe = voNhanVe;
    }

    public double getTongChietKhau() {
        return TongChietKhau;
    }

    public void setTongChietKhau(double tongChietKhau) {
        TongChietKhau = tongChietKhau;
    }

    public String getDiaChiKH() {
        return DiaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        DiaChiKH = diaChiKH;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public Double getSoLuongThuc() {
        return SoLuongThuc == null ? 0 : SoLuongThuc;
    }

    public void setSoLuongThuc(Double soLuongThuc) {
        SoLuongThuc = soLuongThuc;
    }

    public Double getDonGiaThuc() {
        return DonGiaThuc == null ? 0 : DonGiaThuc;
    }

    public void setDonGiaThuc(Double donGiaThuc) {
        DonGiaThuc = donGiaThuc;
    }

    public double getSLTra() {
        return SLTra;
    }

    public void setSLTra(double SLTra) {
        this.SLTra = SLTra;
    }

    public double getThanhTienChietKhau() {
        return ThanhTienChietKhau;
    }

    public void setThanhTienChietKhau(double thanhTienChietKhau) {
        ThanhTienChietKhau = thanhTienChietKhau;
    }

    public double getChiphivanchuyen() {
        return Chiphivanchuyen;
    }

    public void setChiphivanchuyen(double chiphivanchuyen) {
        Chiphivanchuyen = chiphivanchuyen;
    }

    public double getChiphikhac() {
        return Chiphikhac;
    }

    public void setChiphikhac(double chiphikhac) {
        Chiphikhac = chiphikhac;
    }

    public double getSLLe() {
        return SLLe;
    }

    public void setSLLe(double SLLe) {
        this.SLLe = SLLe;
    }

    public long getIDVatTuXuat() {
        return IDVatTuXuat;
    }

    public void setIDVatTuXuat(long IDVatTuXuat) {
        this.IDVatTuXuat = IDVatTuXuat;
    }

    public String getQuyDoi() {
        return QuyDoi;
    }

    public void setQuyDoi(String quyDoi) {
        QuyDoi = quyDoi;
    }

    public double getDai() {
        return Dai;
    }

    public void setDai(double dai) {
        Dai = dai;
    }

    public double getRong() {
        return Rong;
    }

    public void setRong(double rong) {
        Rong = rong;
    }

    public VattuxuatInfo() {
        stt++;
        SoCT = "";
        Product_ID = "";
        MaKho = "";
        SupplierID = "";
        this.dongia = 0.0;
        Thue = 0.0;
        Ghichu = "";
        this.PO = "";
        this.BH = 12;
        this.SL = 0.0;
        Von_Cost = 0;
        ContractID = "";
        Thutu = null;
        Giaban = 0.0;
        LoaiDiscount = 0;
        GiatriDiscount = 0;
        LydoDiscount = "";
        Thanh_Tien = 0.0;
        Loaitien = "";
        this.TG = 0;
        PromotionID = "";
        this.LO = "";
        Dvt = "";
        ModifiedDate = null;
        ModifiedBy = "";
        GiatriDiscountP = 0;
        DongiaThue = 0;
        DongiaCoThue = 0;
        this.SLNguyen = 0;
        Hesochuyendoi = 0;
        DongiaNguyen = 0;
        EmployeeID = "";
        this.MSKP = "";
        MaTK = "";
        TienThue = 0;
        Diem = 0;
        VoGasTraVe = "";
        MayBo = "";
        VoNhanVe = "";
        TongChietKhau = 0;
        DiaChiKH = "";
        this.DDBH = "";
        SoLuongThuc = 0.0;
        DonGiaThuc = 0.0;
        this.SLTra = 0.0;
        ThanhTienChietKhau = 0;
        Chiphivanchuyen = 0;
        Chiphikhac = 0;
        this.SLLe = 0;
        this.IDVatTuXuat = 0;
        QuyDoi = "";
        Dai = 0;
        Rong = 0;
    }

    public void nhap(Scanner sc){
        Product_Name = sc.nextLine();
    }


    public VattuxuatInfo getVattuxuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<VattuxuatInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<VattuxuatInfo> getListVattuxuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<VattuxuatInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public VattuxuatInfo getVattuxuatByNhapSL(NhapSoLuongSanPhamInfo itemNhapSoLuong) {
        VattuxuatInfo vtx = new VattuxuatInfo();
        vtx.setProduct_ID(itemNhapSoLuong.getProduct_ID());
        vtx.setProduct_Name(itemNhapSoLuong.getProduct_Name());
        vtx.setDvt(itemNhapSoLuong.getDvt());
        vtx.setSL(itemNhapSoLuong.getSL());
        vtx.setSoLuongThuc(itemNhapSoLuong.getSoLuongThuc());
        vtx.setGiaban(itemNhapSoLuong.getGiaban());
        vtx.setDongia(itemNhapSoLuong.getDongia());
        vtx.setDonGiaThuc(itemNhapSoLuong.getDonGiaThuc());
        vtx.setLoaiDiscount(itemNhapSoLuong.getLoaiDiscount());
        vtx.setGiatriDiscount(itemNhapSoLuong.getGiatriDiscount());
        vtx.setThue(itemNhapSoLuong.getThue());
        vtx.setThanh_Tien(itemNhapSoLuong.getThanh_Tien());
        vtx.setGhichu(itemNhapSoLuong.getGhichu());
        return vtx;
    }

    public VattuxuatInfo getVattuxuatByProduct(ProductInfo productInfo){
        VattuxuatInfo vtx = new VattuxuatInfo();
        vtx.setProduct_ID(productInfo.getProduct_ID());
        vtx.setProduct_Name(productInfo.getProduct_Name());
        vtx.setDvt(productInfo.getDonVitinh());
        vtx.setSL(productInfo.getSoLuong());
        vtx.setSoLuongThuc(productInfo.getSoLuong());
        vtx.setGiaban(productInfo.getGiaBanLe());
        vtx.setDongia(productInfo.getGiaBanLe());
        vtx.setLoaiDiscount(2);
        vtx.setGiatriDiscount(0);
        vtx.setThue(productInfo.getThueSuat());
        vtx.setThanh_Tien(productInfo.getSoLuong() * productInfo.getGiaBanLe());

        return vtx;
    }



}
