package com.bongmai.tiha.data.network.map;

import com.bongmai.tiha.data.entities.UserLocationInfo;

public interface IUserLocationModel {


    void InsertUserLocation(UserLocationInfo userLocationInfo, IUserLocationModel.IOnInsertUserLocationFinishedListener listener);

    interface IOnInsertUserLocationFinishedListener {
        void onInsertUserLocationSuccess(UserLocationInfo itemResult);

        void onInsertUserLocationError(String error);
    }

    void InsertUserLocationCuuLong(UserLocationInfo userLocationInfo, IUserLocationModel.IOnInsertUserLocationCuuLongFinishedListener listener);

    interface IOnInsertUserLocationCuuLongFinishedListener {
        void onInsertUserLocationCuuLongSuccess(UserLocationInfo itemResult);

        void onInsertUserLocationCuuLongError(String error);
    }

}
