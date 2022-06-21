package com.bongmai.tiha.data.prefs;

import android.content.Context;

public class SplashModel {
    Context context;

    public SplashModel(Context context) {
        this.context = context;
    }

    public boolean CheckStatusLogin() {
        AppPreference appPreference = new AppPreference(context);
        return appPreference.isLogin();
    }
}
