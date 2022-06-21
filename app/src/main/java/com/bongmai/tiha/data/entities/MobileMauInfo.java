package com.bongmai.tiha.data.entities;

import com.bongmai.tiha.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MobileMauInfo {
    private String MaMau;
    private String TenMau;
    private int Xem;
    private int ThuTu;

    public String getMaMau() {
        return MaMau;
    }

    public void setMaMau(String maMau) {
        MaMau = maMau;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String tenMau) {
        TenMau = tenMau;
    }

    public int getXem() {
        return Xem;
    }

    public void setXem(int xem) {
        Xem = xem;
    }

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int thuTu) {
        ThuTu = thuTu;
    }

    public MobileMauInfo getMobileMau(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<MobileMauInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<MobileMauInfo> getListMobileMau(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<MobileMauInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public int getIDIcon() {
        int result;
        switch (MaMau) {
            case "TONGQUAN":
                result = R.drawable.ic_dashboard;
                break;
            case "CUOCGOI":
                result = R.drawable.ic_thongkecuocgoi;
                break;
            case "BANHANG":
                result = R.drawable.ic_thongkebanhang;
                break;
            case "CONGNO":
                result = R.drawable.ic_thongkecongno;
                break;
            case "TONKHO":
                result = R.drawable.ic_thongkekhohang;
                break;
            case "TIENMAT":
                result = R.drawable.ic_thongketienmat;
                break;
            default:
                result = R.drawable.ic_thongkebanhang;
                break;
        }
        return result;
    }
}
