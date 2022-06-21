package com.bongmai.tiha.data.entities;

import java.util.HashMap;
import java.util.Map;

public class EmployeeCondition {

    private long Begin;
    private long End;
    private String BoPhan;
    private String UserName;
    private String TextSearch;
    private String Vitri;

    public String getVitri() {
        return Vitri;
    }

    public void setVitri(String vitri) {
        Vitri = vitri;
    }

    public long getBegin() {
        return Begin;
    }

    public void setBegin(long begin) {
        Begin = begin;
    }

    public long getEnd() {
        return End;
    }

    public void setEnd(long end) {
        End = end;
    }

    public String getBoPhan() {
        return BoPhan;
    }

    public void setBoPhan(String boPhan) {
        BoPhan = boPhan;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTextSearch() {
        return TextSearch;
    }

    public void setTextSearch(String textSearch) {
        TextSearch = textSearch;
    }

    public EmployeeCondition() {
        Begin = 0;
        End = 0;
        BoPhan = "";
        UserName = "";
        TextSearch = "";
        Vitri = "";
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("Begin", String.valueOf(Begin));
        params.put("End", String.valueOf(End));
        params.put("BoPhan", BoPhan);
        params.put("UserName", UserName);
        params.put("TextSearch", TextSearch);
        params.put("Vitri", Vitri);
        return params;
    }
}
