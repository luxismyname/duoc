package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class UserLocationInfo {


    public int ID;
    public String UserName;
    public String Phone;
    public String Latitude;
    public String Longitude;
    public String OS;
    public Date CreateDate;
    public String EventName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public UserLocationInfo() {

        UserName = Phone = Latitude = Longitude = OS = EventName;

    }

    public UserLocationInfo(UserLocationInfo ob){

        ID = ob.ID;
        UserName = ob.UserName;
        Phone = ob.Phone;
        Latitude = ob.Latitude;
        Longitude = ob.Longitude;
        OS = ob.OS;
        CreateDate = ob.CreateDate;
        EventName = ob.EventName;

    }


    public UserLocationInfo getUserLocationInfo(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<UserLocationInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<UserLocationInfo> getListUserLocationInfo(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<UserLocationInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }


}
