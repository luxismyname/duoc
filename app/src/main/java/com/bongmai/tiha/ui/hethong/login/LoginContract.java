package com.bongmai.tiha.ui.hethong.login;


import com.bongmai.tiha.data.entities.NguoiDungInfo;

public interface LoginContract {
     interface View {
        void onLoginSuccess(NguoiDungInfo nguoiDungInfo);
        void onLoginError(String error);
        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter {
        void CheckLogin(String username, String password);
    }
}
