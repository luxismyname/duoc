package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class SupplierInfo implements Serializable {
    private String Supplier_ID;

    private String Company_Name;

    private String Company_NameE;

    private String Contact_Name;

    private String Phone;

    private String Fax;

    private String So;

    private String Duong;

    private String Quan;

    private String Tinh;

    private double Gioihanno;

    private String PTTT;

    private String TenNhomKH;

    private int Cotgia;

    private double DunoMo;

    private double PHAITHU;

    private double PHAITRA;

    private Date NgayMo;

    private double Duno;

    private Date Ngay;

    private String DDBH;

    private String TenDDBH;

    private int CS;

    private String EMail;

    private String MSTHUE;

    private String GID;

    private int Loai;

    private Timestamp Ngaygio;

    private int Songayno;

    private String DiachighiHD;

    private int Ngunghoatdong;

    private String Dacdiem;

    private String MSDKTT;

    private Date NgayHoi;

    private String GC;

    private int HuongGiavon;

    private int Khongtinhno;

    private String Nguoithu;

    private String Lichchino;

    private int CotrongBangkethutien;

    private int Nopphieuthusau;

    private int Songayduocpheptrehen;

    private String Diachigiaohang2;

    private String Diachigiaohang3;

    private String Diachigiaohang4;

    private String Nguoigo;

    private int VIP;

    private double PhanTramLaiNo;

    private String TaikhoanNH;

    private String TenNganHang;

    private String DiaChiNH;

    private String SoCMND;

    private String NgayCapCMND;

    private String NoiCap;

    private Date NgaySinh;

    private int GioiTinh;

    private String NgheNghiep;

    private String ModifiedBy;

    private Timestamp ModifiedDate;

    private String FileAnh;

    private int ChuKyLayHang;

    private double Gia;

    private double GiaLon;

    private String ChiNhanh;

    private String TenChiNhanh;

    private double PhanTramGiamGia;

    private String DanhXung;

    private String Phuong;

    private String DTDD;

    private String MaSoBangGia;

    private String LoaiHinhThanhToan;

    private String NhomKhachHang;

    private String MaGiamSat;

    private String TKKH1;

    private String TKKH2;

    private String TKKH3;

    private String TKKH4;

    private double TiGia;

    private String TKNO;

    private String TKCO;

    private int SoLeDonGia;

    private String MaSP;

    private String SPSuDung;

    private String X;

    private String Y;

    private String LoaiHinhDN;

    private String SoDT1;

    private String SoDT2;

    private String SoKm;

    private String MaTK;

    private String GiamDoc;

    private String DTDDGD;

    private String EmailGD;

    private String ChucVuNLH;

    private String EmailNLH;

    private String WebSite;

    private String KhuVuc;

    private String FaceBook;

    private String TheVip;

    private String KhuPho;

    private int HuongKMBT;

    private int TatCaChiNhanh;

    private String MaKHKT;

    private String TimKiem;

    private String NguoiDuyet;

    private String ThuocCacNhom;

    private String MaNguon;

    private String GhiChu;

    private String PhanAnhKH;

    private String MaNVQuanLy;

    private String DiaChiGH;

    private int SoLanGoi;

    private String NhanVienGoi;

    private Timestamp NgayGioGoi;

    private String BenhVien;

    private String Line;

    private String ActionId;

    private String UniqueId;

    private String LineBam;

    private String TrangThaiCuocGoi;

    private String NguoiGioiThieu;

    private String CVID;

    private Date NgayDangKy;

    private String NguoiBaoTro;

    private int ChiSoNhanhCon;

    private String MaQuocGia;

    private double MucShop;

    private String ContactNameE;

    private String ChucVuNLHE;

    private String MauSac;

    private Date NgayHetHan;

    private String TrangThai;

    private int DoiTuongID;

    private int NguonID;

    private int KieuKhachHang;

    private boolean GoiThanhCong;

    private String NhanVienHoTro;

    private String MSPhong;

    private int TinhTrangHonNhan;

    private int QuanLyTrucTiepID;

    private String TenQuanLyTrucTiep;

    private int TrangThaiID;

    private int DanhGia;

    private String AnhCaNhan;

    private String AnhCMND;

    private String AnhMatTruocThe;

    private int NhanVienKinhDoanhID;

    private String TenNhanVienKinhDoanh;

    private String ChucVu;

    private String ListTenSanPham;

    private String ListMaSanPham;

    private String QuanHe;

    private int LoaiTheID;

    private int TongGhiChu;

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        Supplier_ID = supplier_ID;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getCompany_NameE() {
        return Company_NameE;
    }

    public void setCompany_NameE(String company_NameE) {
        Company_NameE = company_NameE;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getSo() {
        return So;
    }

    public void setSo(String so) {
        So = so;
    }

    public String getDuong() {
        return Duong;
    }

    public void setDuong(String duong) {
        Duong = duong;
    }

    public String getQuan() {
        return Quan;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

    public String getTinh() {
        return Tinh;
    }

    public void setTinh(String tinh) {
        Tinh = tinh;
    }

    public double getGioihanno() {
        return Gioihanno;
    }

    public void setGioihanno(double gioihanno) {
        Gioihanno = gioihanno;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public int getCotgia() {
        return Cotgia;
    }

    public void setCotgia(int cotgia) {
        Cotgia = cotgia;
    }

    public double getDunoMo() {
        return DunoMo;
    }

    public void setDunoMo(double dunoMo) {
        DunoMo = dunoMo;
    }

    public double getPHAITHU() {
        return PHAITHU;
    }

    public void setPHAITHU(double PHAITHU) {
        this.PHAITHU = PHAITHU;
    }

    public double getPHAITRA() {
        return PHAITRA;
    }

    public void setPHAITRA(double PHAITRA) {
        this.PHAITRA = PHAITRA;
    }

    public Date getNgayMo() {
        return NgayMo;
    }

    public void setNgayMo(Date ngayMo) {
        NgayMo = ngayMo;
    }

    public double getDuno() {
        return Duno;
    }

    public void setDuno(double duno) {
        Duno = duno;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public int getCS() {
        return CS;
    }

    public void setCS(int CS) {
        this.CS = CS;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getMSTHUE() {
        return MSTHUE;
    }

    public void setMSTHUE(String MSTHUE) {
        this.MSTHUE = MSTHUE;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public int getLoai() {
        return Loai;
    }

    public void setLoai(int loai) {
        Loai = loai;
    }

    public Timestamp getNgaygio() {
        return Ngaygio;
    }

    public void setNgaygio(Timestamp ngaygio) {
        Ngaygio = ngaygio;
    }

    public int getSongayno() {
        return Songayno;
    }

    public void setSongayno(int songayno) {
        Songayno = songayno;
    }

    public String getDiachighiHD() {
        return DiachighiHD;
    }

    public void setDiachighiHD(String diachighiHD) {
        DiachighiHD = diachighiHD;
    }

    public int getNgunghoatdong() {
        return Ngunghoatdong;
    }

    public void setNgunghoatdong(int ngunghoatdong) {
        Ngunghoatdong = ngunghoatdong;
    }

    public String getDacdiem() {
        return Dacdiem;
    }

    public void setDacdiem(String dacdiem) {
        Dacdiem = dacdiem;
    }

    public String getMSDKTT() {
        return MSDKTT;
    }

    public void setMSDKTT(String MSDKTT) {
        this.MSDKTT = MSDKTT;
    }

    public Date getNgayHoi() {
        return NgayHoi;
    }

    public void setNgayHoi(Date ngayHoi) {
        NgayHoi = ngayHoi;
    }

    public String getGC() {
        return GC;
    }

    public void setGC(String GC) {
        this.GC = GC;
    }

    public int getHuongGiavon() {
        return HuongGiavon;
    }

    public void setHuongGiavon(int huongGiavon) {
        HuongGiavon = huongGiavon;
    }

    public int getKhongtinhno() {
        return Khongtinhno;
    }

    public void setKhongtinhno(int khongtinhno) {
        Khongtinhno = khongtinhno;
    }

    public String getNguoithu() {
        return Nguoithu;
    }

    public void setNguoithu(String nguoithu) {
        Nguoithu = nguoithu;
    }

    public String getLichchino() {
        return Lichchino;
    }

    public void setLichchino(String lichchino) {
        Lichchino = lichchino;
    }

    public int getCotrongBangkethutien() {
        return CotrongBangkethutien;
    }

    public void setCotrongBangkethutien(int cotrongBangkethutien) {
        CotrongBangkethutien = cotrongBangkethutien;
    }

    public int getNopphieuthusau() {
        return Nopphieuthusau;
    }

    public void setNopphieuthusau(int nopphieuthusau) {
        Nopphieuthusau = nopphieuthusau;
    }

    public int getSongayduocpheptrehen() {
        return Songayduocpheptrehen;
    }

    public void setSongayduocpheptrehen(int songayduocpheptrehen) {
        Songayduocpheptrehen = songayduocpheptrehen;
    }

    public String getDiachigiaohang2() {
        return Diachigiaohang2;
    }

    public void setDiachigiaohang2(String diachigiaohang2) {
        Diachigiaohang2 = diachigiaohang2;
    }

    public String getDiachigiaohang3() {
        return Diachigiaohang3;
    }

    public void setDiachigiaohang3(String diachigiaohang3) {
        Diachigiaohang3 = diachigiaohang3;
    }

    public String getDiachigiaohang4() {
        return Diachigiaohang4;
    }

    public void setDiachigiaohang4(String diachigiaohang4) {
        Diachigiaohang4 = diachigiaohang4;
    }

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public int getVIP() {
        return VIP;
    }

    public void setVIP(int VIP) {
        this.VIP = VIP;
    }

    public double getPhanTramLaiNo() {
        return PhanTramLaiNo;
    }

    public void setPhanTramLaiNo(double phanTramLaiNo) {
        PhanTramLaiNo = phanTramLaiNo;
    }

    public String getTaikhoanNH() {
        return TaikhoanNH;
    }

    public void setTaikhoanNH(String taikhoanNH) {
        TaikhoanNH = taikhoanNH;
    }

    public String getTenNganHang() {
        return TenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        TenNganHang = tenNganHang;
    }

    public String getDiaChiNH() {
        return DiaChiNH;
    }

    public void setDiaChiNH(String diaChiNH) {
        DiaChiNH = diaChiNH;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getNgayCapCMND() {
        return NgayCapCMND;
    }

    public void setNgayCapCMND(String ngayCapCMND) {
        NgayCapCMND = ngayCapCMND;
    }

    public String getNoiCap() {
        return NoiCap;
    }

    public void setNoiCap(String noiCap) {
        NoiCap = noiCap;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgheNghiep() {
        return NgheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        NgheNghiep = ngheNghiep;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getFileAnh() {
        return FileAnh;
    }

    public void setFileAnh(String fileAnh) {
        FileAnh = fileAnh;
    }

    public int getChuKyLayHang() {
        return ChuKyLayHang;
    }

    public void setChuKyLayHang(int chuKyLayHang) {
        ChuKyLayHang = chuKyLayHang;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public double getGiaLon() {
        return GiaLon;
    }

    public void setGiaLon(double giaLon) {
        GiaLon = giaLon;
    }

    public String getChiNhanh() {
        return ChiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        ChiNhanh = chiNhanh;
    }

    public double getPhanTramGiamGia() {
        return PhanTramGiamGia;
    }

    public void setPhanTramGiamGia(double phanTramGiamGia) {
        PhanTramGiamGia = phanTramGiamGia;
    }

    public String getDanhXung() {
        return DanhXung;
    }

    public void setDanhXung(String danhXung) {
        DanhXung = danhXung;
    }

    public String getPhuong() {
        return Phuong;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public String getDTDD() {
        return DTDD;
    }

    public void setDTDD(String DTDD) {
        this.DTDD = DTDD;
    }

    public String getMaSoBangGia() {
        return MaSoBangGia;
    }

    public void setMaSoBangGia(String maSoBangGia) {
        MaSoBangGia = maSoBangGia;
    }

    public String getLoaiHinhThanhToan() {
        return LoaiHinhThanhToan;
    }

    public void setLoaiHinhThanhToan(String loaiHinhThanhToan) {
        LoaiHinhThanhToan = loaiHinhThanhToan;
    }

    public String getNhomKhachHang() {
        return NhomKhachHang;
    }

    public void setNhomKhachHang(String nhomKhachHang) {
        NhomKhachHang = nhomKhachHang;
    }

    public String getMaGiamSat() {
        return MaGiamSat;
    }

    public void setMaGiamSat(String maGiamSat) {
        MaGiamSat = maGiamSat;
    }

    public String getTKKH1() {
        return TKKH1;
    }

    public void setTKKH1(String TKKH1) {
        this.TKKH1 = TKKH1;
    }

    public String getTKKH2() {
        return TKKH2;
    }

    public void setTKKH2(String TKKH2) {
        this.TKKH2 = TKKH2;
    }

    public String getTKKH3() {
        return TKKH3;
    }

    public void setTKKH3(String TKKH3) {
        this.TKKH3 = TKKH3;
    }

    public String getTKKH4() {
        return TKKH4;
    }

    public void setTKKH4(String TKKH4) {
        this.TKKH4 = TKKH4;
    }

    public double getTiGia() {
        return TiGia;
    }

    public void setTiGia(double tiGia) {
        TiGia = tiGia;
    }

    public String getTKNO() {
        return TKNO;
    }

    public void setTKNO(String TKNO) {
        this.TKNO = TKNO;
    }

    public String getTKCO() {
        return TKCO;
    }

    public void setTKCO(String TKCO) {
        this.TKCO = TKCO;
    }

    public int getSoLeDonGia() {
        return SoLeDonGia;
    }

    public void setSoLeDonGia(int soLeDonGia) {
        SoLeDonGia = soLeDonGia;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getSPSuDung() {
        return SPSuDung;
    }

    public void setSPSuDung(String SPSuDung) {
        this.SPSuDung = SPSuDung;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public String getLoaiHinhDN() {
        return LoaiHinhDN;
    }

    public void setLoaiHinhDN(String loaiHinhDN) {
        LoaiHinhDN = loaiHinhDN;
    }

    public String getSoDT1() {
        return SoDT1;
    }

    public void setSoDT1(String soDT1) {
        SoDT1 = soDT1;
    }

    public String getSoDT2() {
        return SoDT2;
    }

    public void setSoDT2(String soDT2) {
        SoDT2 = soDT2;
    }

    public String getSoKm() {
        return SoKm;
    }

    public void setSoKm(String soKm) {
        SoKm = soKm;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String maTK) {
        MaTK = maTK;
    }

    public String getGiamDoc() {
        return GiamDoc;
    }

    public void setGiamDoc(String giamDoc) {
        GiamDoc = giamDoc;
    }

    public String getDTDDGD() {
        return DTDDGD;
    }

    public void setDTDDGD(String DTDDGD) {
        this.DTDDGD = DTDDGD;
    }

    public String getEmailGD() {
        return EmailGD;
    }

    public void setEmailGD(String emailGD) {
        EmailGD = emailGD;
    }

    public String getChucVuNLH() {
        return ChucVuNLH;
    }

    public void setChucVuNLH(String chucVuNLH) {
        ChucVuNLH = chucVuNLH;
    }

    public String getEmailNLH() {
        return EmailNLH;
    }

    public void setEmailNLH(String emailNLH) {
        EmailNLH = emailNLH;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        KhuVuc = khuVuc;
    }

    public String getFaceBook() {
        return FaceBook;
    }

    public void setFaceBook(String faceBook) {
        FaceBook = faceBook;
    }

    public String getTheVip() {
        return TheVip;
    }

    public void setTheVip(String theVip) {
        TheVip = theVip;
    }

    public String getKhuPho() {
        return KhuPho;
    }

    public void setKhuPho(String khuPho) {
        KhuPho = khuPho;
    }

    public int getHuongKMBT() {
        return HuongKMBT;
    }

    public void setHuongKMBT(int huongKMBT) {
        HuongKMBT = huongKMBT;
    }

    public int getTatCaChiNhanh() {
        return TatCaChiNhanh;
    }

    public void setTatCaChiNhanh(int tatCaChiNhanh) {
        TatCaChiNhanh = tatCaChiNhanh;
    }

    public String getMaKHKT() {
        return MaKHKT;
    }

    public void setMaKHKT(String maKHKT) {
        MaKHKT = maKHKT;
    }

    public String getTimKiem() {
        return TimKiem;
    }

    public void setTimKiem(String timKiem) {
        TimKiem = timKiem;
    }

    public String getNguoiDuyet() {
        return NguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        NguoiDuyet = nguoiDuyet;
    }

    public String getThuocCacNhom() {
        return ThuocCacNhom;
    }

    public void setThuocCacNhom(String thuocCacNhom) {
        ThuocCacNhom = thuocCacNhom;
    }

    public String getMaNguon() {
        return MaNguon;
    }

    public void setMaNguon(String maNguon) {
        MaNguon = maNguon;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getPhanAnhKH() {
        return PhanAnhKH;
    }

    public void setPhanAnhKH(String phanAnhKH) {
        PhanAnhKH = phanAnhKH;
    }

    public String getMaNVQuanLy() {
        return MaNVQuanLy;
    }

    public void setMaNVQuanLy(String maNVQuanLy) {
        MaNVQuanLy = maNVQuanLy;
    }

    public String getDiaChiGH() {
        return DiaChiGH;
    }

    public void setDiaChiGH(String diaChiGH) {
        DiaChiGH = diaChiGH;
    }

    public int getSoLanGoi() {
        return SoLanGoi;
    }

    public void setSoLanGoi(int soLanGoi) {
        SoLanGoi = soLanGoi;
    }

    public String getNhanVienGoi() {
        return NhanVienGoi;
    }

    public void setNhanVienGoi(String nhanVienGoi) {
        NhanVienGoi = nhanVienGoi;
    }

    public Timestamp getNgayGioGoi() {
        return NgayGioGoi;
    }

    public void setNgayGioGoi(Timestamp ngayGioGoi) {
        NgayGioGoi = ngayGioGoi;
    }

    public String getBenhVien() {
        return BenhVien;
    }

    public void setBenhVien(String benhVien) {
        BenhVien = benhVien;
    }

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public String getActionId() {
        return ActionId;
    }

    public void setActionId(String actionId) {
        ActionId = actionId;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getLineBam() {
        return LineBam;
    }

    public void setLineBam(String lineBam) {
        LineBam = lineBam;
    }

    public String getTrangThaiCuocGoi() {
        return TrangThaiCuocGoi;
    }

    public void setTrangThaiCuocGoi(String trangThaiCuocGoi) {
        TrangThaiCuocGoi = trangThaiCuocGoi;
    }

    public String getNguoiGioiThieu() {
        return NguoiGioiThieu;
    }

    public void setNguoiGioiThieu(String nguoiGioiThieu) {
        NguoiGioiThieu = nguoiGioiThieu;
    }

    public String getCVID() {
        return CVID;
    }

    public void setCVID(String CVID) {
        this.CVID = CVID;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    public String getNguoiBaoTro() {
        return NguoiBaoTro;
    }

    public void setNguoiBaoTro(String nguoiBaoTro) {
        NguoiBaoTro = nguoiBaoTro;
    }

    public int getChiSoNhanhCon() {
        return ChiSoNhanhCon;
    }

    public void setChiSoNhanhCon(int chiSoNhanhCon) {
        ChiSoNhanhCon = chiSoNhanhCon;
    }

    public String getMaQuocGia() {
        return MaQuocGia;
    }

    public void setMaQuocGia(String maQuocGia) {
        MaQuocGia = maQuocGia;
    }

    public double getMucShop() {
        return MucShop;
    }

    public void setMucShop(double mucShop) {
        MucShop = mucShop;
    }

    public String getContactNameE() {
        return ContactNameE;
    }

    public void setContactNameE(String contactNameE) {
        ContactNameE = contactNameE;
    }

    public String getChucVuNLHE() {
        return ChucVuNLHE;
    }

    public void setChucVuNLHE(String chucVuNLHE) {
        ChucVuNLHE = chucVuNLHE;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String mauSac) {
        MauSac = mauSac;
    }

    public Date getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        NgayHetHan = ngayHetHan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getDoiTuongID() {
        return DoiTuongID;
    }

    public void setDoiTuongID(int doiTuongID) {
        DoiTuongID = doiTuongID;
    }

    public int getNguonID() {
        return NguonID;
    }

    public void setNguonID(int nguonID) {
        NguonID = nguonID;
    }

    public int getKieuKhachHang() {
        return KieuKhachHang;
    }

    public void setKieuKhachHang(int kieuKhachHang) {
        KieuKhachHang = kieuKhachHang;
    }

    public boolean isGoiThanhCong() {
        return GoiThanhCong;
    }

    public void setGoiThanhCong(boolean goiThanhCong) {
        GoiThanhCong = goiThanhCong;
    }

    public String getNhanVienHoTro() {
        return NhanVienHoTro;
    }

    public void setNhanVienHoTro(String nhanVienHoTro) {
        NhanVienHoTro = nhanVienHoTro;
    }

    public String getMSPhong() {
        return MSPhong;
    }

    public void setMSPhong(String MSPhong) {
        this.MSPhong = MSPhong;
    }

    public int getTinhTrangHonNhan() {
        return TinhTrangHonNhan;
    }

    public void setTinhTrangHonNhan(int tinhTrangHonNhan) {
        TinhTrangHonNhan = tinhTrangHonNhan;
    }

    public int getQuanLyTrucTiepID() {
        return QuanLyTrucTiepID;
    }

    public void setQuanLyTrucTiepID(int quanLyTrucTiepID) {
        QuanLyTrucTiepID = quanLyTrucTiepID;
    }

    public String getTenQuanLyTrucTiep() {
        return TenQuanLyTrucTiep;
    }

    public void setTenQuanLyTrucTiep(String tenQuanLyTrucTiep) {
        TenQuanLyTrucTiep = tenQuanLyTrucTiep;
    }

    public int getTrangThaiID() {
        return TrangThaiID;
    }

    public void setTrangThaiID(int trangThaiID) {
        TrangThaiID = trangThaiID;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
    }

    public String getAnhCaNhan() {
        return AnhCaNhan;
    }

    public void setAnhCaNhan(String anhCaNhan) {
        AnhCaNhan = anhCaNhan;
    }

    public String getAnhCMND() {
        return AnhCMND;
    }

    public void setAnhCMND(String anhCMND) {
        AnhCMND = anhCMND;
    }

    public String getAnhMatTruocThe() {
        return AnhMatTruocThe;
    }

    public void setAnhMatTruocThe(String anhMatTruocThe) {
        AnhMatTruocThe = anhMatTruocThe;
    }

    public int getNhanVienKinhDoanhID() {
        return NhanVienKinhDoanhID;
    }

    public void setNhanVienKinhDoanhID(int nhanVienKinhDoanhID) {
        NhanVienKinhDoanhID = nhanVienKinhDoanhID;
    }

    public String getTenNhanVienKinhDoanh() {
        return TenNhanVienKinhDoanh;
    }

    public void setTenNhanVienKinhDoanh(String tenNhanVienKinhDoanh) {
        TenNhanVienKinhDoanh = tenNhanVienKinhDoanh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getListTenSanPham() {
        return ListTenSanPham;
    }

    public void setListTenSanPham(String listTenSanPham) {
        ListTenSanPham = listTenSanPham;
    }

    public String getListMaSanPham() {
        return ListMaSanPham;
    }

    public void setListMaSanPham(String listMaSanPham) {
        ListMaSanPham = listMaSanPham;
    }

    public String getQuanHe() {
        return QuanHe;
    }

    public void setQuanHe(String quanHe) {
        QuanHe = quanHe;
    }

    public int getLoaiTheID() {
        return LoaiTheID;
    }

    public void setLoaiTheID(int loaiTheID) {
        LoaiTheID = loaiTheID;
    }

    public int getTongGhiChu() {
        return TongGhiChu;
    }

    public void setTongGhiChu(int tongGhiChu) {
        TongGhiChu = tongGhiChu;
    }

    public String getTenNhomKH() {
        return TenNhomKH;
    }

    public void setTenNhomKH(String tenNhomKH) {
        TenNhomKH = tenNhomKH;
    }

    public String getTenDDBH() {
        return TenDDBH;
    }

    public void setTenDDBH(String tenDDBH) {
        TenDDBH = tenDDBH;
    }

    public String getTenChiNhanh() {
        return TenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        TenChiNhanh = tenChiNhanh;
    }


    public SupplierInfo getSupplier(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<SupplierInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<SupplierInfo> getListSupplier(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<SupplierInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
