package com.bongmai.tiha.service;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.UserLocationInfo;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LocationService  extends Service implements LocationServiceContract.View {

    public boolean isLoading = false;
    UserLocationInfo userLocationInfo = new UserLocationInfo();

    Context mContext;
    List<UserLocationInfo> listLocation;

    LocationServicePresenter userLocationPresenter = new LocationServicePresenter(this);

    public LocationService(Context mContext) {
        this.mContext = mContext;


    }

    public LocationService() {
    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult != null && locationResult.getLastLocation() != null) {
                double latitude = locationResult.getLastLocation().getLatitude();
                double longtitude = locationResult.getLastLocation().getLongitude();
                Log.d("LOCATION_UPDATE", latitude + "," + longtitude);

                userLocationInfo.setUserName(PublicVariables.nguoiDungInfo.getUserName());


                //userLocationInfo.setCreateDate(AppUtils.formatStringToDateSQL(PublicVariables.NgayLamViec, "dd/MM/yyyy'T'HH:mm:ss"));
                userLocationInfo.setCreateDate(AppUtils.formatStringToDateUtil(PublicVariables.NgayLamViec, "dd/MM/yyyy'T'HH:mm:ss"));
                userLocationInfo.setPhone(PublicVariables.nguoiDungInfo.getUserName());
                if(!NetworkUtils.pingGoogle()){
                    userLocationInfo.setEventName("OFFLINE");
                } else {
                    userLocationInfo.setEventName("ONLINE");
                }


                userLocationInfo.setOS(PublicVariables.nguoiDungInfo.getUserName());
                userLocationInfo.setLatitude(String.valueOf(latitude));
                userLocationInfo.setLongitude(String.valueOf(longtitude));

                //cái này đúng nha

                userLocationPresenter.InsertUserLocation(userLocationInfo);
                userLocationPresenter.InsertUserLocationCuuLong(userLocationInfo);
//                locationOfflineServices = new LocationOfflineServices(getApplicationContext());
//
//                locationOfflineServices.InsertLocationOffline(userLocationInfo);


            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private UserLocationInfo getUserLocationInfo(UserLocationInfo userLocationInfo){

        return new UserLocationInfo(userLocationInfo);
    }


    private void startLocationService() {
        String channelID = "location_notification_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("TihaService");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentText("Running");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null && notificationManager.getNotificationChannel(channelID) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(channelID, "TihaService", NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.setDescription("This channel is used by location service");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(4000);
        //set thời gian gửi tọa độ về server 60000ms = 1 phút
        locationRequest.setFastestInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        startForeground(Constraints.LOCATION_SERVICE_ID, builder.build());


    }

    private void stopLocationService(){
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
        stopForeground(true);
        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null){
            String action = intent.getAction();

            if (action != null){
                if(action.equals(Constraints.ACTION_START_LOCATION_SERVICE)){
                    startLocationService();
                } else if(action.equals(Constraints.ACTION_STOP_LOCATION_SERVICE)){
                    stopLocationService();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }





    @Override
    public void onInsertUserLocationSuccess(UserLocationInfo itemResult) {
        itemResult = userLocationInfo;
        isLoading = false;
//        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertUserLocationError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertUserLocationCuuLongSuccess(UserLocationInfo itemResult) {
        itemResult = userLocationInfo;
        isLoading = false;
//        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onInsertUserLocationCuuLongError(String error) {

    }

}
