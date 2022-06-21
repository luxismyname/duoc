package com.bongmai.tiha.data.entities;

public class KhachHangSearchInternetInfo {
    private String Name;
    private String Address;
    private String Latitude;
    private String Longitude;
    private String Photos;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPhotos() {
        return Photos;
    }

    public void setPhotos(String photos) {
        Photos = photos;
    }

    public KhachHangSearchInternetInfo() {
        Name = "";
        Address = "";
        Latitude = "";
        Longitude = "";
        Photos = "";
    }
}
