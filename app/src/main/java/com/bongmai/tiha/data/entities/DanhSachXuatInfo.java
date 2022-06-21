package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

public class DanhSachXuatInfo implements Serializable {
    private Boolean Chon ;
    private java.util.Date Ngay ;
    private String SoCt ;
    private String SoHD ;
    private String SoPhieuVietTay ;
    private String SoCT2 ;
    private String Ghichu ;
    private String Cty ;
    private String DiaChi ;
    private String MSTHUE ;
    private String KHHD ;
    private String ThueSuat ;
    private String MSDKTT ;
    private double SL ;
    private double Thung ;
    private double Vien ;
    private double TienThue ;
    private double TienHang ;
    private String Nguoigo ;
    private double TT ;
    private String MaKH ;
    private String Tennguoimua ;
    private String TenDDnguoimua ;
    private String Tenhang ;
    private double PhieuDT ;
    private String MaHD ;
    private String KHMauHD ;
    private String MSK ;
    private String Tenkho ;
    private String ChiNhanhID ;
    private String TenChiNhanh ;
    private String DDBH ;
    private String TenDDBH ;
    private String Nhom ;
    private String Lydo ;
    private String Nhombanhang ;
    private String NHOMLYDO ;
    private String MSPTTT ;
    private String PTTT ;
    private String TenTam ;
    private String TenTrangThai ;
    private Date NgayHD ;
    private Date Giove ;
    private int SoHinhAnh ;
    private double ChietKhau ;
    private String KhuVuc ;
    private String SHTKN ;
    private String SHTKC ;
    private double HueHong ;
    private String DienThoai ;
    private String TrangThaiEInvoice ;
    private int Solanin ;
    private String SoXe ;
    private String TaiXe ;
    private String NVGiao1 ;
    private String NVGiao2 ;
    private String GhiChuEInvoice ;
    private String EMailPx ;
    private Date Ngaygio ;
    private String LyDoEInvoice ;
    private String VanBanThoaThuan ;
    private java.util.Date NgayThoaThuan ;
    private String GhiChuHoaDon ;
    private String MaGiaoDichHDDT ;
    private String MaSoTraCuuHDDT ;
    private String TrangThai ;


    public Boolean getChon() {
        return Chon;
    }

    public void setChon(Boolean chon) {
        Chon = chon;
    }

    public java.util.Date getNgay() {
        return Ngay;
    }

    public void setNgay(java.util.Date ngay) {
        Ngay = ngay;
    }

    public String getSoCt() {
        return SoCt;
    }

    public void setSoCt(String soCt) {
        SoCt = soCt;
    }

    public String getSoHD() {
        return SoHD;
    }

    public void setSoHD(String soHD) {
        SoHD = soHD;
    }

    public String getSoPhieuVietTay() {
        return SoPhieuVietTay;
    }

    public void setSoPhieuVietTay(String soPhieuVietTay) {
        SoPhieuVietTay = soPhieuVietTay;
    }

    public String getSoCT2() {
        return SoCT2;
    }

    public void setSoCT2(String soCT2) {
        SoCT2 = soCT2;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public String getCty() {
        return Cty;
    }

    public void setCty(String cty) {
        Cty = cty;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getMSTHUE() {
        return MSTHUE;
    }

    public void setMSTHUE(String MSTHUE) {
        this.MSTHUE = MSTHUE;
    }

    public String getKHHD() {
        return KHHD;
    }

    public void setKHHD(String KHHD) {
        this.KHHD = KHHD;
    }

    public String getThueSuat() {
        return ThueSuat;
    }

    public void setThueSuat(String thueSuat) {
        ThueSuat = thueSuat;
    }

    public String getMSDKTT() {
        return MSDKTT;
    }

    public void setMSDKTT(String MSDKTT) {
        this.MSDKTT = MSDKTT;
    }

    public double getSL() {
        return SL;
    }

    public void setSL(double SL) {
        this.SL = SL;
    }

    public double getThung() {
        return Thung;
    }

    public void setThung(double thung) {
        Thung = thung;
    }

    public double getVien() {
        return Vien;
    }

    public void setVien(double vien) {
        Vien = vien;
    }

    public double getTienThue() {
        return TienThue;
    }

    public void setTienThue(double tienThue) {
        TienThue = tienThue;
    }

    public double getTienHang() {
        return TienHang;
    }

    public void setTienHang(double tienHang) {
        TienHang = tienHang;
    }

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public double getTT() {
        return TT;
    }

    public void setTT(double TT) {
        this.TT = TT;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTennguoimua() {
        return Tennguoimua;
    }

    public void setTennguoimua(String tennguoimua) {
        Tennguoimua = tennguoimua;
    }

    public String getTenDDnguoimua() {
        return TenDDnguoimua;
    }

    public void setTenDDnguoimua(String tenDDnguoimua) {
        TenDDnguoimua = tenDDnguoimua;
    }

    public String getTenhang() {
        return Tenhang;
    }

    public void setTenhang(String tenhang) {
        Tenhang = tenhang;
    }

    public double getPhieuDT() {
        return PhieuDT;
    }

    public void setPhieuDT(double phieuDT) {
        PhieuDT = phieuDT;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getKHMauHD() {
        return KHMauHD;
    }

    public void setKHMauHD(String KHMauHD) {
        this.KHMauHD = KHMauHD;
    }

    public String getMSK() {
        return MSK;
    }

    public void setMSK(String MSK) {
        this.MSK = MSK;
    }

    public String getTenkho() {
        return Tenkho;
    }

    public void setTenkho(String tenkho) {
        Tenkho = tenkho;
    }

    public String getChiNhanhID() {
        return ChiNhanhID;
    }

    public void setChiNhanhID(String chiNhanhID) {
        ChiNhanhID = chiNhanhID;
    }

    public String getTenChiNhanh() {
        return TenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        TenChiNhanh = tenChiNhanh;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getTenDDBH() {
        return TenDDBH;
    }

    public void setTenDDBH(String tenDDBH) {
        TenDDBH = tenDDBH;
    }

    public String getNhom() {
        return Nhom;
    }

    public void setNhom(String nhom) {
        Nhom = nhom;
    }

    public String getLydo() {
        return Lydo;
    }

    public void setLydo(String lydo) {
        Lydo = lydo;
    }

    public String getNhombanhang() {
        return Nhombanhang;
    }

    public void setNhombanhang(String nhombanhang) {
        Nhombanhang = nhombanhang;
    }

    public String getNHOMLYDO() {
        return NHOMLYDO;
    }

    public void setNHOMLYDO(String NHOMLYDO) {
        this.NHOMLYDO = NHOMLYDO;
    }

    public String getMSPTTT() {
        return MSPTTT;
    }

    public void setMSPTTT(String MSPTTT) {
        this.MSPTTT = MSPTTT;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getTenTam() {
        return TenTam;
    }

    public void setTenTam(String tenTam) {
        TenTam = tenTam;
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    public Date getNgayHD() {
        return NgayHD;
    }

    public void setNgayHD(Date ngayHD) {
        NgayHD = ngayHD;
    }

    public Date getGiove() {
        return Giove;
    }

    public void setGiove(Date giove) {
        Giove = giove;
    }

    public int getSoHinhAnh() {
        return SoHinhAnh;
    }

    public void setSoHinhAnh(int soHinhAnh) {
        SoHinhAnh = soHinhAnh;
    }

    public double getChietKhau() {
        return ChietKhau;
    }

    public void setChietKhau(double chietKhau) {
        ChietKhau = chietKhau;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        KhuVuc = khuVuc;
    }

    public String getSHTKN() {
        return SHTKN;
    }

    public void setSHTKN(String SHTKN) {
        this.SHTKN = SHTKN;
    }

    public String getSHTKC() {
        return SHTKC;
    }

    public void setSHTKC(String SHTKC) {
        this.SHTKC = SHTKC;
    }

    public double getHueHong() {
        return HueHong;
    }

    public void setHueHong(double hueHong) {
        HueHong = hueHong;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getTrangThaiEInvoice() {
        return TrangThaiEInvoice;
    }

    public void setTrangThaiEInvoice(String trangThaiEInvoice) {
        TrangThaiEInvoice = trangThaiEInvoice;
    }

    public int getSolanin() {
        return Solanin;
    }

    public void setSolanin(int solanin) {
        Solanin = solanin;
    }

    public String getSoXe() {
        return SoXe;
    }

    public void setSoXe(String soXe) {
        SoXe = soXe;
    }

    public String getTaiXe() {
        return TaiXe;
    }

    public void setTaiXe(String taiXe) {
        TaiXe = taiXe;
    }

    public String getNVGiao1() {
        return NVGiao1;
    }

    public void setNVGiao1(String NVGiao1) {
        this.NVGiao1 = NVGiao1;
    }

    public String getNVGiao2() {
        return NVGiao2;
    }

    public void setNVGiao2(String NVGiao2) {
        this.NVGiao2 = NVGiao2;
    }

    public String getGhiChuEInvoice() {
        return GhiChuEInvoice;
    }

    public void setGhiChuEInvoice(String ghiChuEInvoice) {
        GhiChuEInvoice = ghiChuEInvoice;
    }

    public String getEMailPx() {
        return EMailPx;
    }

    public void setEMailPx(String EMailPx) {
        this.EMailPx = EMailPx;
    }

    public Date getNgaygio() {
        return Ngaygio;
    }

    public void setNgaygio(Date ngaygio) {
        Ngaygio = ngaygio;
    }

    public String getLyDoEInvoice() {
        return LyDoEInvoice;
    }

    public void setLyDoEInvoice(String lyDoEInvoice) {
        LyDoEInvoice = lyDoEInvoice;
    }

    public String getVanBanThoaThuan() {
        return VanBanThoaThuan;
    }

    public void setVanBanThoaThuan(String vanBanThoaThuan) {
        VanBanThoaThuan = vanBanThoaThuan;
    }

    public java.util.Date getNgayThoaThuan() {
        return NgayThoaThuan;
    }

    public void setNgayThoaThuan(java.util.Date ngayThoaThuan) {
        NgayThoaThuan = ngayThoaThuan;
    }

    public String getGhiChuHoaDon() {
        return GhiChuHoaDon;
    }

    public void setGhiChuHoaDon(String ghiChuHoaDon) {
        GhiChuHoaDon = ghiChuHoaDon;
    }

    public String getMaGiaoDichHDDT() {
        return MaGiaoDichHDDT;
    }

    public void setMaGiaoDichHDDT(String maGiaoDichHDDT) {
        MaGiaoDichHDDT = maGiaoDichHDDT;
    }

    public String getMaSoTraCuuHDDT() {
        return MaSoTraCuuHDDT;
    }

    public void setMaSoTraCuuHDDT(String maSoTraCuuHDDT) {
        MaSoTraCuuHDDT = maSoTraCuuHDDT;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public DanhSachXuatInfo() {
        Ngay = null;
        SoCt = "";
        Cty = "";
        TT = 0;
    }

    public DanhSachXuatInfo getDanhSachXuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DanhSachXuatInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DanhSachXuatInfo> getListDanhSachXuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DanhSachXuatInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
