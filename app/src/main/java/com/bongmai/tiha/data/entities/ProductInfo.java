package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInfo implements Serializable {

    private boolean Chon;

    private String Product_ID;

    private String Product_Name;

    private String SIZE1;

    private String SIZE2;

    private String Mau;

    private String Description;

    private String DonVitinh;

    private double GiaMua;

    private double GiaBanLe;

    private double GiaBanDLT;

    private double GiaBanDLTP;

    private String Category_ID;

    private String Supplier_ID;

    private int PHANTRAM;

    private double Gioihan;

    private int Action;

    private double Donggoi;

    private String dvt2;

    private int BH;

    private int SoNgayBH;

    private double ThueSuat;

    private Date Ngaygio;

    private double MaxDisc;

    private String NuocSX;

    private double HuehongNSX;

    private Date Ngaythaydoigia;

    private String Loai;

    private int SoNgaycohang;

    private int Cotinhhangton;

    private int Dathangduban;

    private String TeninHD;

    private String MaTK;

    private int Tinhtonkho;

    private double GIATAM;

    private String dvt3;

    private double dai;

    private double rong;

    private double Giacothue;

    private String Diachifileanh;

    private int Gioihantren;

    private int Cothongbaohangve;

    private int YCSerial;

    private Timestamp ModifiedDate;

    private String ModifiedBy;

    private String Nguoigo;

    private String Loaitien;

    private String Donvitien;

    private String Vitri;

    private int SLNhieu;

    private int InHD;

    private String DonvitinhDongia;

    private int TheoDoiVo;

    private double DonGiaDVT2;

    private double TrongLuong;

    private String KyHieu;

    private double HeSoCong;

    private double KLTinh;

    private double BaoBi;

    private String DonviBaoBi;

    private String MaVachID;

    private String MaTK1;

    private String MaTK2;

    private String MaTK3;

    private String MaTK4;

    private double Donggoi2;

    private String DVT4;

    private String ProductIDWeb;

    private String NhaPhanPhoi;

    private double Tinh;

    private String MaKet;

    private int TinhGiaVon;

    private String MaVo;

    private String KieuTheoDoi;

    private String SetMau;

    private String TimKiem;

    private String NhaSanXuat;

    private int Duyet;

    private String NguoiDuyet;

    private String MaThuongHieu;

    private String TKVT;

    private String TKGV;

    private String TKDT;

    private String TKHBTL;

    private String TKCKHB;

    private double HeSoVanChuyen;

    private double DongGoi3;

    private double DongGoi4;

    private double GiaC21;

    private double GiaC22;

    private double GiaC23;

    private double GiaC31;

    private double GiaC32;

    private double GiaC33;

    private double BienDoGiamGia;

    private double SoLuongYeuCau;

    private String LoaiThue;

    private double ChiPhiVanChuyen;

    private String DvtYeuCau;

    private int IDMauSac;

    private int IDPhieuNhanMau;

    private String MaHangNCC;

    private Boolean HangMau;

    private String NoS;

    private double Von_Cost2;

    private String HinhAnh;

    private List<ProductInfo> listProduct;

    public List<ProductInfo> getListProduct() {
        return listProduct;
    }

    private double SoLuong;

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public void setListProduct(List<ProductInfo> listProduct) {
        this.listProduct = listProduct;
    }

    public boolean isChon() {
        return Chon;
    }

    public void setChon(boolean chon) {
        Chon = chon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
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

    public String getSIZE1() {
        return SIZE1;
    }

    public void setSIZE1(String SIZE1) {
        this.SIZE1 = SIZE1;
    }

    public String getSIZE2() {
        return SIZE2;
    }

    public void setSIZE2(String SIZE2) {
        this.SIZE2 = SIZE2;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String mau) {
        Mau = mau;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDonVitinh() {
        return DonVitinh;
    }

    public void setDonVitinh(String donVitinh) {
        DonVitinh = donVitinh;
    }

    public double getGiaMua() {
        return GiaMua;
    }

    public void setGiaMua(double giaMua) {
        GiaMua = giaMua;
    }

    public double getGiaBanLe() {
        return GiaBanLe;
    }

    public void setGiaBanLe(double giaBanLe) {
        GiaBanLe = giaBanLe;
    }

    public double getGiaBanDLT() {
        return GiaBanDLT;
    }

    public void setGiaBanDLT(double giaBanDLT) {
        GiaBanDLT = giaBanDLT;
    }

    public double getGiaBanDLTP() {
        return GiaBanDLTP;
    }

    public void setGiaBanDLTP(double giaBanDLTP) {
        GiaBanDLTP = giaBanDLTP;
    }

    public String getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(String category_ID) {
        Category_ID = category_ID;
    }

    public String getSupplier_ID() {
        return Supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        Supplier_ID = supplier_ID;
    }

    public int getPHANTRAM() {
        return PHANTRAM;
    }

    public void setPHANTRAM(int PHANTRAM) {
        this.PHANTRAM = PHANTRAM;
    }

    public double getGioihan() {
        return Gioihan;
    }

    public void setGioihan(double gioihan) {
        Gioihan = gioihan;
    }

    public int getAction() {
        return Action;
    }

    public void setAction(int action) {
        Action = action;
    }

    public double getDonggoi() {
        return Donggoi;
    }

    public void setDonggoi(double donggoi) {
        Donggoi = donggoi;
    }

    public String getDvt2() {
        return dvt2;
    }

    public void setDvt2(String dvt2) {
        this.dvt2 = dvt2;
    }

    public int getBH() {
        return BH;
    }

    public void setBH(int BH) {
        this.BH = BH;
    }

    public int getSoNgayBH() {
        return SoNgayBH;
    }

    public void setSoNgayBH(int soNgayBH) {
        SoNgayBH = soNgayBH;
    }

    public double getThueSuat() {
        return ThueSuat;
    }

    public void setThueSuat(double thueSuat) {
        ThueSuat = thueSuat;
    }

    public Date getNgaygio() {
        return Ngaygio;
    }

    public void setNgaygio(Date ngaygio) {
        Ngaygio = ngaygio;
    }

    public double getMaxDisc() {
        return MaxDisc;
    }

    public void setMaxDisc(double maxDisc) {
        MaxDisc = maxDisc;
    }

    public String getNuocSX() {
        return NuocSX;
    }

    public void setNuocSX(String nuocSX) {
        NuocSX = nuocSX;
    }

    public double getHuehongNSX() {
        return HuehongNSX;
    }

    public void setHuehongNSX(double huehongNSX) {
        HuehongNSX = huehongNSX;
    }

    public Date getNgaythaydoigia() {
        return Ngaythaydoigia;
    }

    public void setNgaythaydoigia(Date ngaythaydoigia) {
        Ngaythaydoigia = ngaythaydoigia;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public int getSoNgaycohang() {
        return SoNgaycohang;
    }

    public void setSoNgaycohang(int soNgaycohang) {
        SoNgaycohang = soNgaycohang;
    }

    public int getCotinhhangton() {
        return Cotinhhangton;
    }

    public void setCotinhhangton(int cotinhhangton) {
        Cotinhhangton = cotinhhangton;
    }

    public int getDathangduban() {
        return Dathangduban;
    }

    public void setDathangduban(int dathangduban) {
        Dathangduban = dathangduban;
    }

    public String getTeninHD() {
        return TeninHD;
    }

    public void setTeninHD(String teninHD) {
        TeninHD = teninHD;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String maTK) {
        MaTK = maTK;
    }

    public int getTinhtonkho() {
        return Tinhtonkho;
    }

    public void setTinhtonkho(int tinhtonkho) {
        Tinhtonkho = tinhtonkho;
    }

    public double getGIATAM() {
        return GIATAM;
    }

    public void setGIATAM(double GIATAM) {
        this.GIATAM = GIATAM;
    }

    public String getDvt3() {
        return dvt3;
    }

    public void setDvt3(String dvt3) {
        this.dvt3 = dvt3;
    }

    public double getDai() {
        return dai;
    }

    public void setDai(double dai) {
        this.dai = dai;
    }

    public double getRong() {
        return rong;
    }

    public void setRong(double rong) {
        this.rong = rong;
    }

    public double getGiacothue() {
        return Giacothue;
    }

    public void setGiacothue(double giacothue) {
        Giacothue = giacothue;
    }

    public String getDiachifileanh() {
        return Diachifileanh;
    }

    public void setDiachifileanh(String diachifileanh) {
        Diachifileanh = diachifileanh;
    }

    public int getGioihantren() {
        return Gioihantren;
    }

    public void setGioihantren(int gioihantren) {
        Gioihantren = gioihantren;
    }

    public int getCothongbaohangve() {
        return Cothongbaohangve;
    }

    public void setCothongbaohangve(int cothongbaohangve) {
        Cothongbaohangve = cothongbaohangve;
    }

    public int getYCSerial() {
        return YCSerial;
    }

    public void setYCSerial(int YCSerial) {
        this.YCSerial = YCSerial;
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

    public String getNguoigo() {
        return Nguoigo;
    }

    public void setNguoigo(String nguoigo) {
        Nguoigo = nguoigo;
    }

    public String getLoaitien() {
        return Loaitien;
    }

    public void setLoaitien(String loaitien) {
        Loaitien = loaitien;
    }

    public String getDonvitien() {
        return Donvitien;
    }

    public void setDonvitien(String donvitien) {
        Donvitien = donvitien;
    }

    public String getVitri() {
        return Vitri;
    }

    public void setVitri(String vitri) {
        Vitri = vitri;
    }

    public int getSLNhieu() {
        return SLNhieu;
    }

    public void setSLNhieu(int SLNhieu) {
        this.SLNhieu = SLNhieu;
    }

    public int getInHD() {
        return InHD;
    }

    public void setInHD(int inHD) {
        InHD = inHD;
    }

    public String getDonvitinhDongia() {
        return DonvitinhDongia;
    }

    public void setDonvitinhDongia(String donvitinhDongia) {
        DonvitinhDongia = donvitinhDongia;
    }

    public int getTheoDoiVo() {
        return TheoDoiVo;
    }

    public void setTheoDoiVo(int theoDoiVo) {
        TheoDoiVo = theoDoiVo;
    }

    public double getDonGiaDVT2() {
        return DonGiaDVT2;
    }

    public void setDonGiaDVT2(double donGiaDVT2) {
        DonGiaDVT2 = donGiaDVT2;
    }

    public double getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(double trongLuong) {
        TrongLuong = trongLuong;
    }

    public String getKyHieu() {
        return KyHieu;
    }

    public void setKyHieu(String kyHieu) {
        KyHieu = kyHieu;
    }

    public double getHeSoCong() {
        return HeSoCong;
    }

    public void setHeSoCong(double heSoCong) {
        HeSoCong = heSoCong;
    }

    public double getKLTinh() {
        return KLTinh;
    }

    public void setKLTinh(double KLTinh) {
        this.KLTinh = KLTinh;
    }

    public double getBaoBi() {
        return BaoBi;
    }

    public void setBaoBi(double baoBi) {
        BaoBi = baoBi;
    }

    public String getDonviBaoBi() {
        return DonviBaoBi;
    }

    public void setDonviBaoBi(String donviBaoBi) {
        DonviBaoBi = donviBaoBi;
    }

    public String getMaVachID() {
        return MaVachID;
    }

    public void setMaVachID(String maVachID) {
        MaVachID = maVachID;
    }

    public String getMaTK1() {
        return MaTK1;
    }

    public void setMaTK1(String maTK1) {
        MaTK1 = maTK1;
    }

    public String getMaTK2() {
        return MaTK2;
    }

    public void setMaTK2(String maTK2) {
        MaTK2 = maTK2;
    }

    public String getMaTK3() {
        return MaTK3;
    }

    public void setMaTK3(String maTK3) {
        MaTK3 = maTK3;
    }

    public String getMaTK4() {
        return MaTK4;
    }

    public void setMaTK4(String maTK4) {
        MaTK4 = maTK4;
    }

    public double getDonggoi2() {
        return Donggoi2;
    }

    public void setDonggoi2(double donggoi2) {
        Donggoi2 = donggoi2;
    }

    public String getDVT4() {
        return DVT4;
    }

    public void setDVT4(String DVT4) {
        this.DVT4 = DVT4;
    }

    public String getProductIDWeb() {
        return ProductIDWeb;
    }

    public void setProductIDWeb(String productIDWeb) {
        ProductIDWeb = productIDWeb;
    }

    public String getNhaPhanPhoi() {
        return NhaPhanPhoi;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        NhaPhanPhoi = nhaPhanPhoi;
    }

    public double getTinh() {
        return Tinh;
    }

    public void setTinh(double tinh) {
        Tinh = tinh;
    }

    public String getMaKet() {
        return MaKet;
    }

    public void setMaKet(String maKet) {
        MaKet = maKet;
    }

    public int getTinhGiaVon() {
        return TinhGiaVon;
    }

    public void setTinhGiaVon(int tinhGiaVon) {
        TinhGiaVon = tinhGiaVon;
    }

    public String getMaVo() {
        return MaVo;
    }

    public void setMaVo(String maVo) {
        MaVo = maVo;
    }

    public String getKieuTheoDoi() {
        return KieuTheoDoi;
    }

    public void setKieuTheoDoi(String kieuTheoDoi) {
        KieuTheoDoi = kieuTheoDoi;
    }

    public String getSetMau() {
        return SetMau;
    }

    public void setSetMau(String setMau) {
        SetMau = setMau;
    }

    public String getTimKiem() {
        return TimKiem;
    }

    public void setTimKiem(String timKiem) {
        TimKiem = timKiem;
    }

    public String getNhaSanXuat() {
        return NhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        NhaSanXuat = nhaSanXuat;
    }

    public int getDuyet() {
        return Duyet;
    }

    public void setDuyet(int duyet) {
        Duyet = duyet;
    }

    public String getNguoiDuyet() {
        return NguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        NguoiDuyet = nguoiDuyet;
    }

    public String getMaThuongHieu() {
        return MaThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        MaThuongHieu = maThuongHieu;
    }

    public String getTKVT() {
        return TKVT;
    }

    public void setTKVT(String TKVT) {
        this.TKVT = TKVT;
    }

    public String getTKGV() {
        return TKGV;
    }

    public void setTKGV(String TKGV) {
        this.TKGV = TKGV;
    }

    public String getTKDT() {
        return TKDT;
    }

    public void setTKDT(String TKDT) {
        this.TKDT = TKDT;
    }

    public String getTKHBTL() {
        return TKHBTL;
    }

    public void setTKHBTL(String TKHBTL) {
        this.TKHBTL = TKHBTL;
    }

    public String getTKCKHB() {
        return TKCKHB;
    }

    public void setTKCKHB(String TKCKHB) {
        this.TKCKHB = TKCKHB;
    }

    public double getHeSoVanChuyen() {
        return HeSoVanChuyen;
    }

    public void setHeSoVanChuyen(double heSoVanChuyen) {
        HeSoVanChuyen = heSoVanChuyen;
    }

    public double getDongGoi3() {
        return DongGoi3;
    }

    public void setDongGoi3(double dongGoi3) {
        DongGoi3 = dongGoi3;
    }

    public double getDongGoi4() {
        return DongGoi4;
    }

    public void setDongGoi4(double dongGoi4) {
        DongGoi4 = dongGoi4;
    }

    public double getGiaC21() {
        return GiaC21;
    }

    public void setGiaC21(double giaC21) {
        GiaC21 = giaC21;
    }

    public double getGiaC22() {
        return GiaC22;
    }

    public void setGiaC22(double giaC22) {
        GiaC22 = giaC22;
    }

    public double getGiaC23() {
        return GiaC23;
    }

    public void setGiaC23(double giaC23) {
        GiaC23 = giaC23;
    }

    public double getGiaC31() {
        return GiaC31;
    }

    public void setGiaC31(double giaC31) {
        GiaC31 = giaC31;
    }

    public double getGiaC32() {
        return GiaC32;
    }

    public void setGiaC32(double giaC32) {
        GiaC32 = giaC32;
    }

    public double getGiaC33() {
        return GiaC33;
    }

    public void setGiaC33(double giaC33) {
        GiaC33 = giaC33;
    }

    public double getBienDoGiamGia() {
        return BienDoGiamGia;
    }

    public void setBienDoGiamGia(double bienDoGiamGia) {
        BienDoGiamGia = bienDoGiamGia;
    }

    public double getSoLuongYeuCau() {
        return SoLuongYeuCau;
    }

    public void setSoLuongYeuCau(double soLuongYeuCau) {
        SoLuongYeuCau = soLuongYeuCau;
    }

    public String getLoaiThue() {
        return LoaiThue;
    }

    public void setLoaiThue(String loaiThue) {
        LoaiThue = loaiThue;
    }

    public double getChiPhiVanChuyen() {
        return ChiPhiVanChuyen;
    }

    public void setChiPhiVanChuyen(double chiPhiVanChuyen) {
        ChiPhiVanChuyen = chiPhiVanChuyen;
    }

    public String getDvtYeuCau() {
        return DvtYeuCau;
    }

    public void setDvtYeuCau(String dvtYeuCau) {
        DvtYeuCau = dvtYeuCau;
    }

    public int getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(int IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public int getIDPhieuNhanMau() {
        return IDPhieuNhanMau;
    }

    public void setIDPhieuNhanMau(int IDPhieuNhanMau) {
        this.IDPhieuNhanMau = IDPhieuNhanMau;
    }

    public String getMaHangNCC() {
        return MaHangNCC;
    }

    public void setMaHangNCC(String maHangNCC) {
        MaHangNCC = maHangNCC;
    }

    public Boolean getHangMau() {
        return HangMau;
    }

    public void setHangMau(Boolean hangMau) {
        HangMau = hangMau;
    }

    public String getNoS() {
        return NoS;
    }

    public void setNoS(String noS) {
        NoS = noS;
    }

    public double getVon_Cost2() {
        return Von_Cost2;
    }

    public void setVon_Cost2(double von_Cost2) {
        Von_Cost2 = von_Cost2;
    }

    public ProductInfo getProduct(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ProductInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ProductInfo> getListProduct(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ProductInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }



}
