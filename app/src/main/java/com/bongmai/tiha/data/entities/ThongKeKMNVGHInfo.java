package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ThongKeKMNVGHInfo implements Serializable {
    
    public  double TongKM;

    public  double SoChuyenDi;

    public String EmployeeID;


    public double getTongKM() {
        return TongKM;
    }

    public void setTongKM(double tongKM) {
        TongKM = tongKM;
    }

    public double getSoChuyenDi() {
        return SoChuyenDi;
    }

    public void setSoChuyenDi(double soChuyenDi) {
        SoChuyenDi = soChuyenDi;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public ThongKeKMNVGHInfo() {
        TongKM = 0.0;
        SoChuyenDi = 0.0;
        EmployeeID = "";
    }

    public ThongKeKMNVGHInfo getThongKeKMNVGH(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ThongKeKMNVGHInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ThongKeKMNVGHInfo> getListThongKeKMNVGH(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ThongKeKMNVGHInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }


}
