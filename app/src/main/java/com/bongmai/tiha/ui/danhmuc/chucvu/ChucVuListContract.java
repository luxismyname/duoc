package com.bongmai.tiha.ui.danhmuc.chucvu;

import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.ChucVuInfo;

import java.util.List;

public interface ChucVuListContract {

    interface View {
        void onGetChucVuListSuccess(List<ChucVuInfo> listResult);

        void onGetChucVuListError(String error);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {
        void GetChucVuList();
    }
}
