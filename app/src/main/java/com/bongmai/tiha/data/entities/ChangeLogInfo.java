package com.bongmai.tiha.data.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChangeLogInfo {

    private double latestVersion;
    private int latestVersionCode;
    private String url;

    public double getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(double latestVersion) {
        this.latestVersion = latestVersion;
    }

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public ChangeLogInfo getChangeLog(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ChangeLogInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ChangeLogInfo> getListChangeLog(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ChangeLogInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

}
