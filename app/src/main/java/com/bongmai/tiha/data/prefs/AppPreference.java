package com.bongmai.tiha.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    public static final String PREF_ServerName = "ServerName";
    public static final String PREF_IS_LOGGED_IN = "is_logged_in";
    public static final String PREF_TenDangNhap = "TenDangNhap";
    public static final String PREF_MatKhau = "MatKhau";
    public static final String PREF_NguoiDungID = "NguoiDungID";

    private SharedPreferences sharedPreferences;

    public AppPreference(Context mContext) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(PREF_IS_LOGGED_IN, false);
    }
    public void setLogin(boolean status) {
        sharedPreferences.edit().putBoolean(PREF_IS_LOGGED_IN, status).apply();
    }

    public String getTenDangNhap() {
        return sharedPreferences.getString(PREF_TenDangNhap, "");
    }

    public void setTenDangNhap(String tenDangNhap) {
        sharedPreferences.edit().putString(PREF_TenDangNhap, tenDangNhap).apply();
    }

    public String getMatKhau() {
        return sharedPreferences.getString(PREF_MatKhau, "");
    }

    public void setMatKhau(String matKhau) {
        sharedPreferences.edit().putString(PREF_MatKhau, matKhau).apply();
    }

    public String getNguoiDungID() {
        return sharedPreferences.getString(PREF_NguoiDungID, "0");
    }

    public void setNguoiDungID(String nguoiDungID) {
        sharedPreferences.edit().putString(PREF_NguoiDungID, nguoiDungID).apply();
    }

    public String getServerName() {
        return sharedPreferences.getString(PREF_ServerName, "");
    }

    public void setServerName(String ServerName) {
        sharedPreferences.edit().putString(PREF_ServerName, ServerName).apply();
    }
}
