package com.bongmai.tiha.service;

import com.bongmai.tiha.data.entities.UserLocationInfo;

public interface LocationServiceContract {
    public interface View {
        void onInsertUserLocationSuccess(UserLocationInfo itemResult);

        void onInsertUserLocationError(String error);

        void onInsertUserLocationCuuLongSuccess(UserLocationInfo itemResult);

        void onInsertUserLocationCuuLongError(String error);
    }

    public interface Presenter{
        void InsertUserLocation(UserLocationInfo userLocationInfo);

        void InsertUserLocationCuuLong(UserLocationInfo userLocationInfo);
    }
}
