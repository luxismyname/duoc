package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoanhSoNhanVienInfo {
    private String EmployeeID;
    private String EmployeeName;
    private double SoLuong;
    private double ThanhTien;

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

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }

    public DoanhSoNhanVienInfo() {
        EmployeeID = "";
        EmployeeName = "";
        SoLuong = 0;
        ThanhTien = 0;
    }

    public DoanhSoNhanVienInfo getDoanhSoNhanVien(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DoanhSoNhanVienInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DoanhSoNhanVienInfo> getListDoanhSoNhanVien(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DoanhSoNhanVienInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
