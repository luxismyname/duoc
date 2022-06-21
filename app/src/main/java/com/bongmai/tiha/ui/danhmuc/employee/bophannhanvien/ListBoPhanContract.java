package com.bongmai.tiha.ui.danhmuc.employee.bophannhanvien;

import com.bongmai.tiha.data.entities.BoPhanInfo;

import java.util.List;

public interface ListBoPhanContract {
    interface View {
        void onGetListBoPhanSuccess(List<BoPhanInfo> listResult);

        void onGetListBoPhanError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetListBoPhan();
    }
}
