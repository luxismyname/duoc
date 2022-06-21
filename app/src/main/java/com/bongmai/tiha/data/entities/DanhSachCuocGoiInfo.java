package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DanhSachCuocGoiInfo implements Serializable {
    private int ID;
    private int SoID;
    private String Line;
    private String DiaChi;
    private String TenKhachHang;
    private String SanPham;
    private String Phone;
    private Timestamp Ngay;
    private String SupplierID;
    private String MaSP;
    private String SoPhieu;
    private String GhiChu;
    private Timestamp GioIn;
    private double TienBan;
    private String SoCTThu;
    private double TienThu;
    private String NguoiGiao;
    private Boolean Chon;
    private String TrangThai;
    private Timestamp GioThu;
    private Timestamp GioGiao;
    private String SoKM;
    private String Serial;
    private String DacDiemKH;
    private String ChiNhanh;
    private Boolean GiaoKetHop;
    private String TT;
    private String MoiCu;
    private String MSK;
    private String NguoiGo;
    private String SanPhamMua;
    private String SoCTNhap;
    private String VoVe;
    private double SL;
    private double SLTra;
    private String NhomKH;
    private String MayIn;
    private int TTX;
    private String MoTaLine;
    private String SanPhamKM;
    private Boolean LaCuocGoiToi;
    private String SoXe;
    private String TaiXe;
    private String Product_Name;
    private String FileGhiAm;
    private String TimKiem;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSoID() {
        return SoID;
    }

    public void setSoID(int soID) {
        SoID = soID;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public String getDiaChi() {
        return DiaChi == null ? "" : DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getTenKhachHang() {
        return TenKhachHang == null ? "" : TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getSanPham() {
        return SanPham;
    }

    public void setSanPham(String sanPham) {
        SanPham = sanPham;
    }

    public String getPhone() {
        return Phone == null ? "" : Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Timestamp getNgay() {
        return Ngay;
    }

    public void setNgay(Timestamp ngay) {
        Ngay = ngay;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getSoPhieu() {
        return SoPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        SoPhieu = soPhieu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public Timestamp getGioIn() {
        return GioIn;
    }

    public void setGioIn(Timestamp gioIn) {
        GioIn = gioIn;
    }

    public double getTienBan() {
        return TienBan;
    }

    public void setTienBan(double tienBan) {
        TienBan = tienBan;
    }

    public String getSoCTThu() {
        return SoCTThu;
    }

    public void setSoCTThu(String soCTThu) {
        SoCTThu = soCTThu;
    }

    public double getTienThu() {
        return TienThu;
    }

    public void setTienThu(double tienThu) {
        TienThu = tienThu;
    }

    public String getNguoiGiao() {
        return NguoiGiao;
    }

    public void setNguoiGiao(String nguoiGiao) {
        NguoiGiao = nguoiGiao;
    }

    public Boolean getChon() {
        return Chon;
    }

    public void setChon(Boolean chon) {
        Chon = chon;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public Timestamp getGioThu() {
        return GioThu;
    }

    public void setGioThu(Timestamp gioThu) {
        GioThu = gioThu;
    }

    public Timestamp getGioGiao() {
        return GioGiao;
    }

    public void setGioGiao(Timestamp gioGiao) {
        GioGiao = gioGiao;
    }

    public String getSoKM() {
        return SoKM;
    }

    public void setSoKM(String soKM) {
        SoKM = soKM;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public String getDacDiemKH() {
        return DacDiemKH;
    }

    public void setDacDiemKH(String dacDiemKH) {
        DacDiemKH = dacDiemKH;
    }

    public String getChiNhanh() {
        return ChiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        ChiNhanh = chiNhanh;
    }

    public Boolean getGiaoKetHop() {
        return GiaoKetHop;
    }

    public void setGiaoKetHop(Boolean giaoKetHop) {
        GiaoKetHop = giaoKetHop;
    }

    public String getTT() {
        return TT;
    }

    public void setTT(String TT) {
        this.TT = TT;
    }

    public String getMoiCu() {
        return MoiCu;
    }

    public void setMoiCu(String moiCu) {
        MoiCu = moiCu;
    }

    public String getMSK() {
        return MSK;
    }

    public void setMSK(String MSK) {
        this.MSK = MSK;
    }

    public String getNguoiGo() {
        return NguoiGo;
    }

    public void setNguoiGo(String nguoiGo) {
        NguoiGo = nguoiGo;
    }

    public String getSanPhamMua() {
        return SanPhamMua == null ? "" : SanPhamMua;
    }

    public void setSanPhamMua(String sanPhamMua) {
        SanPhamMua = sanPhamMua;
    }

    public String getSoCTNhap() {
        return SoCTNhap;
    }

    public void setSoCTNhap(String soCTNhap) {
        SoCTNhap = soCTNhap;
    }

    public String getVoVe() {
        return VoVe;
    }

    public void setVoVe(String voVe) {
        VoVe = voVe;
    }

    public double getSL() {
        return SL;
    }

    public void setSL(double SL) {
        this.SL = SL;
    }

    public double getSLTra() {
        return SLTra;
    }

    public void setSLTra(double SLTra) {
        this.SLTra = SLTra;
    }

    public String getNhomKH() {
        return NhomKH;
    }

    public void setNhomKH(String nhomKH) {
        NhomKH = nhomKH;
    }

    public String getMayIn() {
        return MayIn;
    }

    public void setMayIn(String mayIn) {
        MayIn = mayIn;
    }

    public int getTTX() {
        return TTX;
    }

    public void setTTX(int TTX) {
        this.TTX = TTX;
    }

    public String getMoTaLine() {
        return MoTaLine;
    }

    public void setMoTaLine(String moTaLine) {
        MoTaLine = moTaLine;
    }

    public String getSanPhamKM() {
        return SanPhamKM;
    }

    public void setSanPhamKM(String sanPhamKM) {
        SanPhamKM = sanPhamKM;
    }

    public Boolean getLaCuocGoiToi() {
        return LaCuocGoiToi;
    }

    public void setLaCuocGoiToi(Boolean laCuocGoiToi) {
        LaCuocGoiToi = laCuocGoiToi;
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

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getTimKiem() {
        return TimKiem;
    }

    public void setTimKiem(String timKiem) {
        TimKiem = timKiem;
    }

    public DanhSachCuocGoiInfo() {
        ID = 0;
        SoID = 0;
        Line = "";
        DiaChi = "";
        TenKhachHang = "";
        SanPham = "";
        Phone = "";
        Ngay = null;
        SupplierID = "";
        MaSP = "";
        SoPhieu = "";
        GhiChu = "";
        GioIn = null;
        TienBan = 0;
        SoCTThu = "";
        TienThu = 0;
        NguoiGiao = "";
        Chon = false;
        TrangThai = "";
        GioThu = null;
        GioGiao = null;
        SoKM = "";
        Serial = "";
        DacDiemKH = "";
        ChiNhanh = "";
        GiaoKetHop = false;
        TT = "";
        MoiCu = "";
        MSK = "";
        NguoiGo = "";
        SanPhamMua = "";
        SoCTNhap = "";
        VoVe = "";
        SL = 0;
        SLTra = 0;
        NhomKH = "";
        MayIn = "";
        TTX = 0;
        MoTaLine = "";
        SanPhamKM = "";
        LaCuocGoiToi = false;
        SoXe = "";
        TaiXe = "";
        Product_Name = "";
        TimKiem = "";
    }

    public DanhSachCuocGoiInfo getCuocGoi(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DanhSachCuocGoiInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DanhSachCuocGoiInfo> getListCuocGoi(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DanhSachCuocGoiInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
