package com.bongmai.tiha.ui.hethong.login;


import android.text.TextUtils;

import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.network.nguoidung.INguoiDungModel;
import com.bongmai.tiha.data.network.nguoidung.NguoiDungModel;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View loginView;
    NguoiDungModel nguoiDungModel;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.nguoiDungModel = new NguoiDungModel();
    }

    @Override
    public void CheckLogin(String userName, String passWord) {


        if (userName.isEmpty()) {
            loginView.onLoginError("Bạn chưa nhập tên đăng nhập!");
            return;
        }
        if (passWord.isEmpty()) {
            loginView.onLoginError("Bạn chưa nhập mật khẩu!");
            return;
        }
        loginView.showProgressBar();
        nguoiDungModel.CheckLogin(userName, passWord, new INguoiDungModel.IOnCheckLoginFinishedListener() {
            @Override
            public void onLoginSuccess(NguoiDungInfo nguoiDungInfo) {
                if (nguoiDungInfo == null) {
                    loginView.hideProgressBar();
                    loginView.onLoginError("Tên đăng nhập hoặc mật khẩu không đúng!");
                    return;
                }
                loginView.onLoginSuccess(nguoiDungInfo);
                loginView.hideProgressBar();
            }

            @Override
            public void onLoginError(String error) {
                loginView.hideProgressBar();
                loginView.onLoginError(error);
            }
        });
    }
}
