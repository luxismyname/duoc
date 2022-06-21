package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PhieuXuatInfo implements Serializable {
    private String SoCt;

    private Date Ngay;

    private String MSK;

    private String Supplier_ID;

    private double TriGia;

    private double Thanhtoan;

    private String PTTT;

    private String DDBH;

    private String Ghichu;

    private short DuocSua;

    private int TT;

    private String Lydo;

    private String Tygia;

    private String Ngaygiogiao;

    private String Nguoigo;

    private String Tennguoimua;

    private String Diachi;

    private String MaSoThue;

    private String TenDDnguoimua;

    private String Diengiai;

    private String ThueSuat;

    private Timestamp Ngaygio;

    private int LoaiDiscount;

    private double GiatriDiscount;

    private String LydoDiscount;

    private int Solanin;

    private String SOCT2;

    private String MSDKTT;

    private String MSNguoigiao;

    private String Diachigiaohang;

    private String LPhieu;

    private String Chinhanh;

    private double Khachdua;

    private Timestamp ModifiedDate;

    private String ModifiedBy;

    private String TrangThai;

    private String Tuyen;

    private String DTDD;

    private String LoaiHinhThanhToan;

    private String GhiChuHoaDon;

    private String SoPhieuVietTay;

    private String CAID;

    private String EMailPx;

    private String Chanh;

    private String NhanVienGiaoHang;

    private String TaiXe;

    private String LoaiPhieu;

    public String getSoCt() {
        return SoCt;
    }

    public void setSoCt(String soCt) {
        SoCt = soCt;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getMSK() {
        return MSK;
    }

    public void setMSK(String MSK) {
        this.MSK = MSK;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        Supplier_ID = supplier_ID;
    }

    public double getTriGia() {
        return TriGia;
    }

    public void setTriGia(double triGia) {
        TriGia = triGia;
    }

    public double getThanhtoan() {
        return Thanhtoan;
    }

    public void setThanhtoan(double thanhtoan) {
        Thanhtoan = thanhtoan;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public short getDuocSua() {
        return DuocSua;
    }

    public void setDuocSua(short duocSua) {
        DuocSua = duocSua;
    }

    public int getTT() {
        return TT;
    }

    public void setTT(int TT) {
        this.TT = TT;
    }

    public String getLydo() {
        return Lydo;
    }

    public void setLydo(String lydo) {
        Lydo = lydo;
    }

    public String getTygia() {
        return Tygia;
    }

    public void setTygia(String tygia) {
        Tygia = tygia;
    }

    public String getNgaygiogiao() {
        return Ngaygiogiao;
    }

    public void setNgaygiogiao(String ngaygiogiao) {
        Ngaygiogiao = ngaygiogiao;
    }

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public String getTennguoimua() {
        return Tennguoimua;
    }

    public void setTennguoimua(String tennguoimua) {
        Tennguoimua = tennguoimua;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        MaSoThue = maSoThue;
    }

    public String getTenDDnguoimua() {
        return TenDDnguoimua;
    }

    public void setTenDDnguoimua(String tenDDnguoimua) {
        TenDDnguoimua = tenDDnguoimua;
    }

    public String getDiengiai() {
        return Diengiai;
    }

    public void setDiengiai(String diengiai) {
        Diengiai = diengiai;
    }

    public String getThueSuat() {
        return ThueSuat;
    }

    public void setThueSuat(String thueSuat) {
        ThueSuat = thueSuat;
    }

    public Timestamp getNgaygio() {
        return Ngaygio;
    }

    public void setNgaygio(Timestamp ngaygio) {
        Ngaygio = ngaygio;
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

    public int getSolanin() {
        return Solanin;
    }

    public void setSolanin(int solanin) {
        Solanin = solanin;
    }

    public String getSOCT2() {
        return SOCT2;
    }

    public void setSOCT2(String SOCT2) {
        this.SOCT2 = SOCT2;
    }

    public String getMSDKTT() {
        return MSDKTT;
    }

    public void setMSDKTT(String MSDKTT) {
        this.MSDKTT = MSDKTT;
    }

    public String getMSNguoigiao() {
        return MSNguoigiao;
    }

    public void setMSNguoigiao(String MSNguoigiao) {
        this.MSNguoigiao = MSNguoigiao;
    }

    public String getDiachigiaohang() {
        return Diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        Diachigiaohang = diachigiaohang;
    }

    public String getLPhieu() {
        return LPhieu;
    }

    public void setLPhieu(String LPhieu) {
        this.LPhieu = LPhieu;
    }

    public String getChinhanh() {
        return Chinhanh;
    }

    public void setChinhanh(String chinhanh) {
        Chinhanh = chinhanh;
    }

    public double getKhachdua() {
        return Khachdua;
    }

    public void setKhachdua(double khachdua) {
        Khachdua = khachdua;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTuyen() {
        return Tuyen;
    }

    public void setTuyen(String tuyen) {
        Tuyen = tuyen;
    }

    public String getDTDD() {
        return DTDD;
    }

    public void setDTDD(String DTDD) {
        this.DTDD = DTDD;
    }

    public String getLoaiHinhThanhToan() {
        return LoaiHinhThanhToan;
    }

    public void setLoaiHinhThanhToan(String loaiHinhThanhToan) {
        LoaiHinhThanhToan = loaiHinhThanhToan;
    }

    public String getGhiChuHoaDon() {
        return GhiChuHoaDon;
    }

    public void setGhiChuHoaDon(String ghiChuHoaDon) {
        GhiChuHoaDon = ghiChuHoaDon;
    }

    public String getSoPhieuVietTay() {
        return SoPhieuVietTay;
    }

    public void setSoPhieuVietTay(String soPhieuVietTay) {
        SoPhieuVietTay = soPhieuVietTay;
    }

    public String getCAID() {
        return CAID;
    }

    public void setCAID(String CAID) {
        this.CAID = CAID;
    }

    public String getEMailPx() {
        return EMailPx;
    }

    public void setEMailPx(String EMailPx) {
        this.EMailPx = EMailPx;
    }

    public String getChanh() {
        return Chanh;
    }

    public void setChanh(String chanh) {
        Chanh = chanh;
    }

    public String getNhanVienGiaoHang() {
        return NhanVienGiaoHang;
    }

    public void setNhanVienGiaoHang(String nhanVienGiaoHang) {
        NhanVienGiaoHang = nhanVienGiaoHang;
    }

    public String getTaiXe() {
        return TaiXe;
    }

    public void setTaiXe(String taiXe) {
        TaiXe = taiXe;
    }

    public String getLoaiPhieu() {
        return LoaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        LoaiPhieu = loaiPhieu;
    }

    public PhieuXuatInfo() {
        SoCt = "";
        Ngay = null;
        this.MSK = "";
        Supplier_ID = "";
        TriGia = 0;
        Thanhtoan = 0;
        this.PTTT = "";
        this.DDBH = "";
        Ghichu = "";
        DuocSua = -1;
        this.TT = 0;
        Lydo = "";
        Tygia = "";
        Ngaygiogiao = null;
        Nguoigo = "";
        Tennguoimua = "";
        Diachi = "";
        MaSoThue = "";
        TenDDnguoimua = "";
        Diengiai = "";
        ThueSuat = "";
        Ngaygio = null;
        LoaiDiscount = 0;
        GiatriDiscount = 0;
        LydoDiscount = "";
        Solanin = 0;
        this.SOCT2 = "";
        this.MSDKTT = "";
        this.MSNguoigiao = "";
        Diachigiaohang = "";
        this.LPhieu = "";
        Chinhanh = "";
        Khachdua = 0;
        ModifiedDate = null;
        ModifiedBy = "";
        TrangThai = "";
        Tuyen = "";
        this.DTDD = "";
        LoaiHinhThanhToan = "";
        GhiChuHoaDon = "";
        SoPhieuVietTay = "";
        this.CAID = "";
        this.EMailPx = "";
        Chanh = "";
        NhanVienGiaoHang = "";
        TaiXe = "";
        LoaiPhieu = "";
    }

    public PhieuXuatInfo getPhieuXuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<PhieuXuatInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<PhieuXuatInfo> getListPhieuXuat(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<PhieuXuatInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
