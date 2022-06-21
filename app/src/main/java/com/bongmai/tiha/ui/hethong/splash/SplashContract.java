package com.bongmai.tiha.ui.hethong.splash;


import com.bongmai.tiha.data.entities.NguoiDungInfo;

public interface SplashContract {
    interface View {
        void onCheckStatusLoginSuccess(boolean isLogin);
        void onLoginSuccess(NguoiDungInfo nguoiDungInfo);
        void onLoginError(String error);
    }

    interface Presenter {
        void CheckStatusLogin();
        void CheckLogin(String username, String password);
    }

}
