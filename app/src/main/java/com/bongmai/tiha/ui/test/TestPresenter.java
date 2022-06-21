package com.bongmai.tiha.ui.test;

import android.content.Context;

import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.network.changelog.ChangeLogModel;
import com.bongmai.tiha.data.network.changelog.IChangeLogModel;

public class TestPresenter implements TestContract.Presenter{
    ChangeLogModel changeLogModel;
    TestContract.View view;

    public TestPresenter(Context context, TestContract.View view) {
        this.view = view;
        this.changeLogModel = new ChangeLogModel();
    }
    @Override
    public void CheckChangeLog() {

        changeLogModel.GetListChangeLog(new IChangeLogModel.IOnGetListChangeLogFinishedListener() {
            @Override
            public void onGetListChangeLogSuccess(ChangeLogInfo changeLogInfo) {
                view.onCheckChangeLogSuccess(changeLogInfo);
            }

            @Override
            public void onGetListChangeLogError(String error) {
                view.onCheckChangeLogError(error);
            }
        });

    }
}
