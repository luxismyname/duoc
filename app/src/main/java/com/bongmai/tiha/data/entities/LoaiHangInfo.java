package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoaiHangInfo implements Serializable {
    private boolean Chon;
    private String Category_ID;
    private String Loaihang1;
    private String ParentID;
    private int Level;
    List<LoaiHangInfo> ListChild;

    public boolean isChon() {
        return Chon;
    }

    public void setChon(boolean chon) {
        Chon = chon;
    }

    public String getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(String category_ID) {
        Category_ID = category_ID;
    }

    public String getLoaihang1() {
        return Loaihang1;
    }

    public void setLoaihang1(String loaihang1) {
        Loaihang1 = loaihang1;
    }

    public String getParentID() {
        return ParentID == null ? "" : ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public List<LoaiHangInfo> getListChild() {
        return ListChild;
    }

    public void setListChild(List<LoaiHangInfo> listChild) {
        ListChild = listChild;
    }


    public LoaiHangInfo getLoaiHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<LoaiHangInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<LoaiHangInfo> getListLoaiHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<LoaiHangInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
