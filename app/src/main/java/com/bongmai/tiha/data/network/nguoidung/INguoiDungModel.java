package com.bongmai.tiha.data.network.nguoidung;

import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;


public interface INguoiDungModel {
    void CheckLogin(String userName, String passWord, IOnCheckLoginFinishedListener listener);

    interface IOnCheckLoginFinishedListener {
        void onLoginSuccess(NguoiDungInfo nguoiDungInfo);

        void onLoginError(String error);
    }

    void CheckLoginCuuLong(String userName, String passWord, IOnCheckLoginCuuLongFinishedListener listener);

    interface IOnCheckLoginCuuLongFinishedListener {
        void onCheckLoginCuuLongSuccess(NguoiDungCuuLongInfo nguoiDungCuuLongInfo);

        void onCheckLoginCuuLongError(String error);
    }
}
