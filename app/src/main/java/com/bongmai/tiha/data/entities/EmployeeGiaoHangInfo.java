package com.bongmai.tiha.data.entities;

import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeGiaoHangInfo {

    public String SoCT;

    public String EmployeeID;
    public String EmployeeName;
    public Date NgayGiaoHang;
    public String Diachigiaohang;
    public double QuangDuong;
    public String Diachibatdau;
    public int Solandi;

    public String getSoCT() {
        return SoCT;
    }

    public void setSoCT(String soCT) {
        SoCT = soCT;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public Date getNgayGiaoHang() {
        return NgayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        NgayGiaoHang = ngayGiaoHang;
    }

    public String getDiachigiaohang() {
        return Diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        Diachigiaohang = diachigiaohang;
    }

    public double getQuangDuong() {
        return QuangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        QuangDuong = quangDuong;
    }


    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getDiachibatdau() {
        return Diachibatdau;
    }

    public void setDiachibatdau(String diachibatdau) {
        Diachibatdau = diachibatdau;
    }

    public int getSolandi() {
        return Solandi;
    }

    public void setSolandi(int solandi) {
        Solandi = solandi;
    }

    public EmployeeGiaoHangInfo() {
        SoCT = "";
        EmployeeID = "";
        NgayGiaoHang = AppUtils.formatStringToDateSQL(PublicVariables.NgayLamViec, "dd/MM/yyyy");
        Diachigiaohang = "";
        QuangDuong = 0.0;
        Diachibatdau = "";
        Solandi = 0;
    }


    public EmployeeGiaoHangInfo(EmployeeGiaoHangInfo item) {
        SoCT = item.getSoCT();
        EmployeeID = PublicVariables.nguoiDungInfo.getEmployeeID();
        NgayGiaoHang = AppUtils.formatStringToDateSQL(PublicVariables.NgayLamViec, "dd/MM/yyyy");
        Diachigiaohang = item.getDiachigiaohang();
        QuangDuong = item.getQuangDuong();
        Diachibatdau = item.getDiachibatdau();
        Solandi = item.getSolandi();
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("SoCT", SoCT);
        params.put("EmployeeID", EmployeeID);
        params.put("NgayGiaoHang", PublicVariables.NgayLamViec);
        params.put("Diachigiaohang", Diachigiaohang);
        params.put("QuangDuong", String.valueOf(QuangDuong));
        params.put("Diachibatdau", Diachibatdau);
        params.put("Solandi", String.valueOf(Solandi));

        return params;
    }

    public EmployeeGiaoHangInfo getEmployeeGiaoHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<EmployeeGiaoHangInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<EmployeeGiaoHangInfo> getListEmployeeGiaoHangInfo(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<EmployeeGiaoHangInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }


}
