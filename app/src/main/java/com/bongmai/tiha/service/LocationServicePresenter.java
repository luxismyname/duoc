package com.bongmai.tiha.service;

import com.bongmai.tiha.data.entities.UserLocationInfo;
import com.bongmai.tiha.data.network.map.IUserLocationModel;
import com.bongmai.tiha.data.network.map.UserLocationModel;

public class LocationServicePresenter implements LocationServiceContract.Presenter {

    UserLocationModel userLocationModel;
    LocationServiceContract.View view;

    public LocationServicePresenter(LocationServiceContract.View view) {
        this.view = view;
        this.userLocationModel = new UserLocationModel();
    }

    @Override
    public void InsertUserLocation(UserLocationInfo userLocationInfo) {
        userLocationModel.InsertUserLocation(userLocationInfo, new IUserLocationModel.IOnInsertUserLocationFinishedListener() {
            @Override
            public void onInsertUserLocationSuccess(UserLocationInfo itemResult) {
                view.onInsertUserLocationSuccess(userLocationInfo);
            }

            @Override
            public void onInsertUserLocationError(String error) {
                view.onInsertUserLocationError(error);
            }
        });
    }

    @Override
    public void InsertUserLocationCuuLong(UserLocationInfo userLocationInfo) {
        userLocationModel.InsertUserLocationCuuLong(userLocationInfo, new IUserLocationModel.IOnInsertUserLocationCuuLongFinishedListener() {
            @Override
            public void onInsertUserLocationCuuLongSuccess(UserLocationInfo itemResult) {
                view.onInsertUserLocationCuuLongSuccess(userLocationInfo);
            }

            @Override
            public void onInsertUserLocationCuuLongError(String error) {
                view.onInsertUserLocationCuuLongError(error);
            }
        });
    }

}
