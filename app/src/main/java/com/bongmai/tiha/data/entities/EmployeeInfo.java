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

public class EmployeeInfo implements Serializable {

    private boolean Chon;
    public static final int GIOITINH_NAM = -1;
    public static final int GIOITINH_NU = 0;
    private String EmployeeID ;
    private String EmployeeName ;
    private String FirstName ;
    private String MiddleName ;
    private String LastName ;
    private String PrefixName ;
    private String SecurityNo ;
    private String SocialInsuranceNo ;
    private String PassportNo ;
    private int Sex ;
    private Date BirthDate ;
    private boolean IsForeigner ;
    private String Nationality ;
    private String Address ;
    private String City ;
    private String State ;
    private String Zip ;
    private String Country ;
    private String Tel ;
    private String DepartmentID ;
    private String Position ;
    private String PositionName;
    private String LaborContractNo ;
//    //
//    private Date HiredDate ;
//    private Date LastRaise ;
//    private Date ReleasedDate ;
//    private byte[] Photo ;
//    private Nullable<int> Vacation ;
//    private Nullable<int> Sick ;
//    private String EmployeeClassID ;
//    private Nullable<float> BasicCoefficient ;
//    private Nullable<decimal> NetSalary ;
//    private Nullable<decimal> BasicSalary ;
      private String BasicSalary;

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    //    private Nullable<float> AllowanceCoefficient ;
//    private Nullable<decimal> Allowance ;
//    //
    private boolean IsSaleRep ;
    private String AdvanceAccount ;
    private boolean Inactive ;
    private boolean SelectedForPayroll ;
    private double PHAT ;
    private String GHICHU ;
    private String Vanhoa ;
    private String Ngoaingu ;
    private String Chuyenmon ;
    private String Email ;
    private int HINHTHUCTINHLUONG ;
    private String DiachiFileAnh ;
    private double Chithay ;
    private String Noiohiennay ;
    private String DTNoio ;
    private String Nguyenquan ;
    private String Hotencha ;
    private String Namsinhcha ;
    private String Nghenghiepcha ;
    private String Hotenme ;
    private String Namsinhme ;
    private String Nghenghiepme ;
    private String Anhchiem ;
    private String TruonghoccapI ;
    private String CapITunam ;
    private String TruonghoccapII ;
    private String CapIItunam ;
    private String TruonghoccapIII ;
    private String CapIIItunam ;
    private String Truongkhac ;
    private String Quatrinhlamviecbanthan ;
    private String Khanangdaco ;
    private String Dudinhvitrimoi ;
    private int Tongluong ;
    private String Nhomthuong ;
    private String NickName ;
    private int Mucgiaohang ;
    private String Giaohangchokho ;
    private String Nhom ;
    private String NickIM ;
    private String Nhombanhang ;
    private String SoTK ;
    private Timestamp ModifiedDate ;
    private String ModifiedBy ;
    private int Gioihanduocbanno ;
    private String CVID ;
    private String ChiNhanh ;
    private String MaTK1 ;
    private String MaTK2 ;
    private String MaTK3 ;
    private String MaTK4 ;
    private byte[] Picture ;
    private double latitude ;
    private double longitude ;

    public static int getGioitinhNam() {
        return GIOITINH_NAM;
    }

    public static int getGioitinhNu() {
        return GIOITINH_NU;
    }

    private boolean monitor ;
    private boolean getposition ;
    private String MaSap ;
    private boolean LaKhachHang ;
    private boolean LaNhaCungCap ;

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPrefixName() {
        return PrefixName;
    }

    public void setPrefixName(String prefixName) {
        PrefixName = prefixName;
    }

    public String getSecurityNo() {
        return SecurityNo;
    }

    public void setSecurityNo(String securityNo) {
        SecurityNo = securityNo;
    }

    public String getSocialInsuranceNo() {
        return SocialInsuranceNo;
    }

    public void setSocialInsuranceNo(String socialInsuranceNo) {
        SocialInsuranceNo = socialInsuranceNo;
    }

    public boolean isChon() {
        return Chon;
    }

    public void setChon(boolean chon) {
        Chon = chon;
    }

    public String getPassportNo() {
        return PassportNo;
    }

    public void setPassportNo(String passportNo) {
        PassportNo = passportNo;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public boolean isForeigner() {
        return IsForeigner;
    }

    public void setForeigner(boolean foreigner) {
        IsForeigner = foreigner;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getLaborContractNo() {
        return LaborContractNo;
    }

    public void setLaborContractNo(String laborContractNo) {
        LaborContractNo = laborContractNo;
    }

    public boolean isSaleRep() {
        return IsSaleRep;
    }

    public void setSaleRep(boolean saleRep) {
        IsSaleRep = saleRep;
    }

    public String getAdvanceAccount() {
        return AdvanceAccount;
    }

    public void setAdvanceAccount(String advanceAccount) {
        AdvanceAccount = advanceAccount;
    }

    public boolean isInactive() {
        return Inactive;
    }

    public void setInactive(boolean inactive) {
        Inactive = inactive;
    }

    public boolean isSelectedForPayroll() {
        return SelectedForPayroll;
    }

    public void setSelectedForPayroll(boolean selectedForPayroll) {
        SelectedForPayroll = selectedForPayroll;
    }

    public double getPHAT() {
        return PHAT;
    }

    public void setPHAT(double PHAT) {
        this.PHAT = PHAT;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    public String getVanhoa() {
        return Vanhoa;
    }

    public void setVanhoa(String vanhoa) {
        Vanhoa = vanhoa;
    }

    public String getNgoaingu() {
        return Ngoaingu;
    }

    public void setNgoaingu(String ngoaingu) {
        Ngoaingu = ngoaingu;
    }

    public String getChuyenmon() {
        return Chuyenmon;
    }

    public void setChuyenmon(String chuyenmon) {
        Chuyenmon = chuyenmon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getHINHTHUCTINHLUONG() {
        return HINHTHUCTINHLUONG;
    }

    public void setHINHTHUCTINHLUONG(int HINHTHUCTINHLUONG) {
        this.HINHTHUCTINHLUONG = HINHTHUCTINHLUONG;
    }

    public String getDiachiFileAnh() {
        return DiachiFileAnh;
    }

    public void setDiachiFileAnh(String diachiFileAnh) {
        DiachiFileAnh = diachiFileAnh;
    }

    public double getChithay() {
        return Chithay;
    }

    public void setChithay(double chithay) {
        Chithay = chithay;
    }

    public String getNoiohiennay() {
        return Noiohiennay;
    }

    public void setNoiohiennay(String noiohiennay) {
        Noiohiennay = noiohiennay;
    }

    public String getDTNoio() {
        return DTNoio;
    }

    public void setDTNoio(String DTNoio) {
        this.DTNoio = DTNoio;
    }

    public String getNguyenquan() {
        return Nguyenquan;
    }

    public void setNguyenquan(String nguyenquan) {
        Nguyenquan = nguyenquan;
    }

    public String getHotencha() {
        return Hotencha;
    }

    public void setHotencha(String hotencha) {
        Hotencha = hotencha;
    }

    public String getNamsinhcha() {
        return Namsinhcha;
    }

    public void setNamsinhcha(String namsinhcha) {
        Namsinhcha = namsinhcha;
    }

    public String getNghenghiepcha() {
        return Nghenghiepcha;
    }

    public void setNghenghiepcha(String nghenghiepcha) {
        Nghenghiepcha = nghenghiepcha;
    }

    public String getHotenme() {
        return Hotenme;
    }

    public void setHotenme(String hotenme) {
        Hotenme = hotenme;
    }

    public String getNamsinhme() {
        return Namsinhme;
    }

    public void setNamsinhme(String namsinhme) {
        Namsinhme = namsinhme;
    }

    public String getNghenghiepme() {
        return Nghenghiepme;
    }

    public void setNghenghiepme(String nghenghiepme) {
        Nghenghiepme = nghenghiepme;
    }

    public String getAnhchiem() {
        return Anhchiem;
    }

    public void setAnhchiem(String anhchiem) {
        Anhchiem = anhchiem;
    }

    public String getTruonghoccapI() {
        return TruonghoccapI;
    }

    public void setTruonghoccapI(String truonghoccapI) {
        TruonghoccapI = truonghoccapI;
    }

    public String getCapITunam() {
        return CapITunam;
    }

    public void setCapITunam(String capITunam) {
        CapITunam = capITunam;
    }

    public String getTruonghoccapII() {
        return TruonghoccapII;
    }

    public void setTruonghoccapII(String truonghoccapII) {
        TruonghoccapII = truonghoccapII;
    }

    public String getCapIItunam() {
        return CapIItunam;
    }

    public void setCapIItunam(String capIItunam) {
        CapIItunam = capIItunam;
    }

    public String getTruonghoccapIII() {
        return TruonghoccapIII;
    }

    public void setTruonghoccapIII(String truonghoccapIII) {
        TruonghoccapIII = truonghoccapIII;
    }

    public String getCapIIItunam() {
        return CapIIItunam;
    }

    public void setCapIIItunam(String capIIItunam) {
        CapIIItunam = capIIItunam;
    }

    public String getTruongkhac() {
        return Truongkhac;
    }

    public void setTruongkhac(String truongkhac) {
        Truongkhac = truongkhac;
    }

    public String getQuatrinhlamviecbanthan() {
        return Quatrinhlamviecbanthan;
    }

    public void setQuatrinhlamviecbanthan(String quatrinhlamviecbanthan) {
        Quatrinhlamviecbanthan = quatrinhlamviecbanthan;
    }

    public String getKhanangdaco() {
        return Khanangdaco;
    }

    public void setKhanangdaco(String khanangdaco) {
        Khanangdaco = khanangdaco;
    }

    public String getDudinhvitrimoi() {
        return Dudinhvitrimoi;
    }

    public void setDudinhvitrimoi(String dudinhvitrimoi) {
        Dudinhvitrimoi = dudinhvitrimoi;
    }

    public int getTongluong() {
        return Tongluong;
    }

    public void setTongluong(int tongluong) {
        Tongluong = tongluong;
    }

    public String getNhomthuong() {
        return Nhomthuong;
    }

    public void setNhomthuong(String nhomthuong) {
        Nhomthuong = nhomthuong;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getMucgiaohang() {
        return Mucgiaohang;
    }

    public void setMucgiaohang(int mucgiaohang) {
        Mucgiaohang = mucgiaohang;
    }

    public String getGiaohangchokho() {
        return Giaohangchokho;
    }

    public void setGiaohangchokho(String giaohangchokho) {
        Giaohangchokho = giaohangchokho;
    }

    public String getNhom() {
        return Nhom;
    }

    public void setNhom(String nhom) {
        Nhom = nhom;
    }

    public String getNickIM() {
        return NickIM;
    }

    public void setNickIM(String nickIM) {
        NickIM = nickIM;
    }

    public String getNhombanhang() {
        return Nhombanhang;
    }

    public void setNhombanhang(String nhombanhang) {
        Nhombanhang = nhombanhang;
    }

    public String getSoTK() {
        return SoTK;
    }

    public void setSoTK(String soTK) {
        SoTK = soTK;
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

    public int getGioihanduocbanno() {
        return Gioihanduocbanno;
    }

    public void setGioihanduocbanno(int gioihanduocbanno) {
        Gioihanduocbanno = gioihanduocbanno;
    }

    public String getCVID() {
        return CVID;
    }

    public void setCVID(String CVID) {
        this.CVID = CVID;
    }

    public String getChiNhanh() {
        return ChiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        ChiNhanh = chiNhanh;
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

    public byte[] getPicture() {
        return Picture;
    }

    public void setPicture(byte[] picture) {
        Picture = picture;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    public boolean isGetposition() {
        return getposition;
    }

    public void setGetposition(boolean getposition) {
        this.getposition = getposition;
    }

    public String getMaSap() {
        return MaSap;
    }

    public void setMaSap(String maSap) {
        MaSap = maSap;
    }

    public boolean isLaKhachHang() {
        return LaKhachHang;
    }

    public void setLaKhachHang(boolean laKhachHang) {
        LaKhachHang = laKhachHang;
    }

    public boolean isLaNhaCungCap() {
        return LaNhaCungCap;
    }

    public void setLaNhaCungCap(boolean laNhaCungCap) {
        LaNhaCungCap = laNhaCungCap;
    }

    public EmployeeInfo getEmployee(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<EmployeeInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }


    public BoPhanInfo getBoPhan(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<EmployeeInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<EmployeeInfo> getListEmployee(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<EmployeeInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }


}
