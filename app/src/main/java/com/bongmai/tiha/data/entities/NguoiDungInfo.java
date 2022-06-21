package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NguoiDungInfo {
    private String UserName;
    private String GroupName;
    private String Password;
    private String MaSoBangGia;
    private String EmployeeID;
    private String EmployeeName;
    private String DTNoio;
    private String ChiNhanh;
    private String Picture;
    private String Khomacdinh;

    private List<ChiNhanhInfo> ListChiNhanh;
    private List<DonViTinhInfo> ListDVT;

    private List<TrangThaiInfo> ListTrangThai;
    private List<EmployeeInfo> listEmployee;
    private String ListNhanVienStr;
    private String ListChiNhanhStr;
    private List<KhoInfo> ListKho;
    private String ListKhoStr;
    private int BatSoDienThoai;
    private String SecurityNo;
    private String Noiohiennay;
    private Date BirthDate;
    private String BoPhan;
    private String ChucVu;
    private String ListTrangThaiStr;



    private List<QuanInfo> listQuan;
    private List<TinhThanhPhoInfo> listTinh;


    public List<EmployeeInfo> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeInfo> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public String getListNhanVienStr() {
        return ListNhanVienStr;
    }

    public void setListNhanVienStr(String listNhanVienStr) {
        ListNhanVienStr = listNhanVienStr;
    }

    public List<QuanInfo> getListQuan() {
        return listQuan;
    }

    public void setListQuan(List<QuanInfo> listQuan) {
        this.listQuan = listQuan;
    }

    public List<TinhThanhPhoInfo> getListTinh() {
        return listTinh;
    }

    public void setListTinh(List<TinhThanhPhoInfo> listTinh) {
        this.listTinh = listTinh;
    }

    public String getBoPhan() {
        return BoPhan;
    }

    public void setBoPhan(String boPhan) {
        BoPhan = boPhan;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public List<TrangThaiInfo> getListTrangThai() {
        return ListTrangThai;
    }

    public void setListTrangThai(List<TrangThaiInfo> listTrangThai) {
        ListTrangThai = listTrangThai;
    }

    public String getListTrangThaiStr() {
        return ListTrangThaiStr;
    }

    public void setListTrangThaiStr(String listTrangThaiStr) {
        ListTrangThaiStr = listTrangThaiStr;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public List<DonViTinhInfo> getListDVT() {
        return ListDVT;
    }

    public void setListDVT(List<DonViTinhInfo> listDVT) {
        ListDVT = listDVT;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMaSoBangGia() {
        return MaSoBangGia;
    }

    public void setMaSoBangGia(String maSoBangGia) {
        MaSoBangGia = maSoBangGia;
    }

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

    public String getDTNoio() {
        return DTNoio;
    }

    public void setDTNoio(String DTNoio) {
        this.DTNoio = DTNoio;
    }

    public String getChiNhanh() {
        return ChiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        ChiNhanh = chiNhanh;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getKhomacdinh() {
        return Khomacdinh;
    }

    public void setKhomacdinh(String khomacdinh) {
        Khomacdinh = khomacdinh;
    }

    public List<ChiNhanhInfo> getListChiNhanh() {
        return ListChiNhanh;
    }

    public void setListChiNhanh(List<ChiNhanhInfo> listChiNhanh) {
        ListChiNhanh = listChiNhanh;
    }

    public String getListChiNhanhStr() {
        return ListChiNhanhStr;
    }

    public void setListChiNhanhStr(String listChiNhanhStr) {
        ListChiNhanhStr = listChiNhanhStr;
    }

    public List<KhoInfo> getListKho() {
        return ListKho;
    }

    public void setListKho(List<KhoInfo> listKho) {
        ListKho = listKho;
    }

    public String getListKhoStr() {
        return ListKhoStr;
    }

    public void setListKhoStr(String listKhoStr) {
        ListKhoStr = listKhoStr;
    }

    public int getBatSoDienThoai() {
        return BatSoDienThoai;
    }

    public void setBatSoDienThoai(int batSoDienThoai) {
        BatSoDienThoai = batSoDienThoai;
    }

    public String getSecurityNo() {
        return SecurityNo;
    }

    public void setSecurityNo(String securityNo) {
        SecurityNo = securityNo;
    }

    public String getNoiohiennay() {
        return Noiohiennay;
    }

    public void setNoiohiennay(String noiohiennay) {
        Noiohiennay = noiohiennay;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public NguoiDungInfo() {
        UserName = "";
        GroupName = "";
        Password = "";
        MaSoBangGia = "";
        EmployeeID = "";
        EmployeeName = "";
        this.DTNoio = "";
        ChiNhanh = "";
        Picture = "";
        Khomacdinh = "";
        ListChiNhanhStr = "";
        ListKhoStr ="";
        BatSoDienThoai = 0;
        SecurityNo = "";
        Noiohiennay = "";
        BoPhan = "";
        ChucVu = "";
        ListTrangThaiStr = "";
    }

    public NguoiDungInfo getNguoiDung(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<NguoiDungInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<NguoiDungInfo> getListNguoiDung(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<NguoiDungInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
