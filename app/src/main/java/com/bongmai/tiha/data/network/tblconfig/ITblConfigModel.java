package com.bongmai.tiha.data.network.tblconfig;

import com.bongmai.tiha.data.entities.TblConfigInfo;

public interface ITblConfigModel {

    void GetTblConfig(String userName, ITblConfigModel.IOnGetTblConfigFinishedListener listener);

    interface IOnGetTblConfigFinishedListener {
        void onSuccess(TblConfigInfo itemResult);
        void onError(String error);
    }

    void GetTblConfigCuuLong(String userName, ITblConfigModel.IOnGetTblConfigCuuLongFinishedListener listener);

    interface IOnGetTblConfigCuuLongFinishedListener {
        void onSuccess(TblConfigInfo itemResult);
        void onError(String error);
    }
}
