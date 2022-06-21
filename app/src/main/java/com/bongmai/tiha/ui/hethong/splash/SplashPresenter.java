package com.bongmai.tiha.ui.hethong.splash;

import android.content.Context;

import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.network.nguoidung.INguoiDungModel;
import com.bongmai.tiha.data.network.nguoidung.NguoiDungModel;
import com.bongmai.tiha.data.prefs.SplashModel;


public class SplashPresenter implements SplashContract.Presenter {
    SplashContract.View splashView;
    Context context;
    SplashModel splashModel;
    NguoiDungModel nguoiDungModel;

    public SplashPresenter(Context context, SplashContract.View splashView) {
        this.splashView = splashView;
        this.context = context;
        this.splashModel = new SplashModel(context);
        this.nguoiDungModel = new NguoiDungModel();
    }

    @Override
    public void CheckStatusLogin() {
        boolean isLogin = splashModel.CheckStatusLogin();
        splashView.onCheckStatusLoginSuccess(isLogin);
    }

    @Override
    public void CheckLogin(String username, String password) {
        nguoiDungModel.CheckLogin(username, password, new INguoiDungModel.IOnCheckLoginFinishedListener() {
            @Override
            public void onLoginSuccess(NguoiDungInfo nguoiDungInfo) {
                splashView.onLoginSuccess(nguoiDungInfo);
            }

            @Override
            public void onLoginError(String error) {
                splashView.onLoginError(error);
            }
        });
    }

}
