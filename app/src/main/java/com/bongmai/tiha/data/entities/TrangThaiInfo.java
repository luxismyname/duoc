package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TrangThaiInfo implements Serializable {

    public int ID ;
    public String MaTrangThai ;
    public String TenTrangThai ;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaTrangThai() {
        return MaTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        MaTrangThai = maTrangThai;
    }

    public String getTenTrangThai() {
        return TenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        TenTrangThai = tenTrangThai;
    }

    public TrangThaiInfo()
    {
        MaTrangThai = TenTrangThai = "";
    }



    public TrangThaiInfo(int id, String maTrangThai, String tenTrangThai)
    {
        ID = id;
        MaTrangThai = maTrangThai;
        TenTrangThai = tenTrangThai;
    }

    public static List<TrangThaiInfo> loadTrangThai() {

            List<TrangThaiInfo> listTrangThai = new ArrayList<>();
            TrangThaiInfo trangThaiCuocGoiInfo;

            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("ALL");
            trangThaiCuocGoiInfo.setTenTrangThai("Tất cả");
            listTrangThai.add(trangThaiCuocGoiInfo);

            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("6");
            trangThaiCuocGoiInfo.setTenTrangThai("Chờ Đóng Gói");
            listTrangThai.add(trangThaiCuocGoiInfo);

            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("7");
            trangThaiCuocGoiInfo.setTenTrangThai("Đã đóng gói");
            listTrangThai.add(trangThaiCuocGoiInfo);

            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("8");
            trangThaiCuocGoiInfo.setTenTrangThai("Chờ đăng ký giao");
            listTrangThai.add(trangThaiCuocGoiInfo);


            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("9");
            trangThaiCuocGoiInfo.setTenTrangThai("Chờ giao hàng");
            listTrangThai.add(trangThaiCuocGoiInfo);


            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("10");
            trangThaiCuocGoiInfo.setTenTrangThai("Đang giao");
            listTrangThai.add(trangThaiCuocGoiInfo);


            trangThaiCuocGoiInfo = new TrangThaiInfo();
            trangThaiCuocGoiInfo.setMaTrangThai("11");
            trangThaiCuocGoiInfo.setTenTrangThai("Đã giao");
            listTrangThai.add(trangThaiCuocGoiInfo);

            return listTrangThai;
        }


//        List<TrangThaiInfo> listResult = new ArrayList<>();
//        listResult.add(new TrangThaiInfo(0, "", "Tất cả trạng thái"));
//        listResult.add(new TrangThaiInfo(12, "6", "Chờ đóng gói"));
//        listResult.add(new TrangThaiInfo(13,"7", "Đã đóng gói"));
//        listResult.add(new TrangThaiInfo(14,"8", "Chờ đăng ký giao"));
//        listResult.add(new TrangThaiInfo(15,"9", "Chờ giao hàng"));
//        listResult.add(new TrangThaiInfo(16,"10", "Đang giao"));
//        listResult.add(new TrangThaiInfo(17,"11", "Đã giao"));
//        return listResult;


    public static List<TrangThaiInfo> loadTrangThaiNVGH() {
        List<TrangThaiInfo> listResult = new ArrayList<>();
        listResult.add(new TrangThaiInfo(14,"8", "Chờ đăng ký giao"));
        listResult.add(new TrangThaiInfo(13,"9", "Chờ giao hàng"));
        listResult.add(new TrangThaiInfo(16,"10", "Đang giao"));
        listResult.add(new TrangThaiInfo(17,"11", "Đã giao"));
        return listResult;
    }



    public TrangThaiInfo getTrangThai(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<TrangThaiInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<TrangThaiInfo> getListTrangThai(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<TrangThaiInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
