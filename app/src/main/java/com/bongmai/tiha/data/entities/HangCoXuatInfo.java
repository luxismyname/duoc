package com.bongmai.tiha.data.entities;

public class HangCoXuatInfo {
    private String Product_ID;
    private String Product_Name;
    private double Xuat;
    private double Ton;

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

    public double getXuat() {
        return Xuat;
    }

    public void setXuat(double xuat) {
        Xuat = xuat;
    }

    public double getTon() {
        return Ton;
    }

    public void setTon(double ton) {
        Ton = ton;
    }
}
