package com.bongmai.tiha.ui.test;

import com.bongmai.tiha.data.entities.ChangeLogInfo;

public interface TestContract {

    interface View {
        void onCheckChangeLogSuccess(ChangeLogInfo changeLogInfo);
        void onCheckChangeLogError(String error);
    }

    interface Presenter {
        void CheckChangeLog();
    }

}
