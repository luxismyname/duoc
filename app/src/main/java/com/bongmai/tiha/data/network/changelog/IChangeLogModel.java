package com.bongmai.tiha.data.network.changelog;

import com.bongmai.tiha.data.entities.ChangeLogInfo;

import java.util.List;

public interface IChangeLogModel {


    void GetListChangeLog(IChangeLogModel.IOnGetListChangeLogFinishedListener listener);

    interface IOnGetListChangeLogFinishedListener {
        void onGetListChangeLogSuccess(ChangeLogInfo changeLogInfo);

        void onGetListChangeLogError(String error);
    }

}
