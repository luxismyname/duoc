package com.bongmai.tiha.ui.danhmuc.employee.bophannhanvien;

import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.network.bophan.BoPhanModel;
import com.bongmai.tiha.data.network.bophan.IBoPhanModel;

import java.util.List;

public class ListBoPhanPresenter implements  ListBoPhanContract.Presenter {

    ListBoPhanContract.View view;
    BoPhanModel boPhanModel;

    public ListBoPhanPresenter(ListBoPhanContract.View view) {
        this.view = view;
        this.boPhanModel = new BoPhanModel();
    }

    @Override
    public void GetListBoPhan() {
        boPhanModel.GetListBoPhan(new IBoPhanModel.IOnGetListBoPhanFinishedListener() {
            @Override
            public void onGetListBoPhanSuccess(List<BoPhanInfo> listResult) {
                view.onGetListBoPhanSuccess(listResult);
            }

            @Override
            public void onGetListBoPhanError(String error) {
                view.onGetListBoPhanError(error);
            }
        });

    }
}
