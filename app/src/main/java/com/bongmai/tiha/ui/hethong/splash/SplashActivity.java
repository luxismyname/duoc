package com.bongmai.tiha.ui.hethong.splash;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bongmai.tiha.MainActivity;
import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.prefs.AppPreference;
import com.bongmai.tiha.service.Constraints;
import com.bongmai.tiha.service.LocationService;
import com.bongmai.tiha.ui.UserKhacActivity;
import com.bongmai.tiha.ui.hethong.login.LoginActivity;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.bongmai.tiha.utils.aes.AESUtils;


public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    Thread thread;
    SplashPresenter presenterManHinhChao;
    AppPreference appPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appPreference = new AppPreference(this);
        presenterManHinhChao = new SplashPresenter(SplashActivity.this, SplashActivity.this);
        presenterManHinhChao.CheckStatusLogin();
    }

    @Override
    public void onCheckStatusLoginSuccess(boolean isLogin) {
        //Trang thai da login thi goi form Trang chu
        //Nguoc lai goi form dang nhap
        if (isLogin) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        String userName = "", passWord = "";
                        AESUtils aesUtils = new AESUtils();
                        try {
                            userName = aesUtils.decrypt(appPreference.getTenDangNhap());
                        } catch (Exception e) {
                        }
                        try {
                            passWord = aesUtils.decrypt(appPreference.getMatKhau());
                        } catch (Exception e) {
                        }
                        presenterManHinhChao.CheckLogin(userName, passWord);
                    } catch (Exception ex) {
                    } finally {
                    }
                }
            });
        } else {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception ex) {
                    } finally {
                    }
                }
            });

        }
        thread.start();
    }

    @Override
    public void onLoginSuccess(NguoiDungInfo nguoiDungInfo) {
        if (nguoiDungInfo != null && nguoiDungInfo.getGroupName().equals("Administrator")) {
            PublicVariables.nguoiDungInfo = nguoiDungInfo;
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);

            } else {
                startLocationService();
            }
        } else if(nguoiDungInfo != null && !nguoiDungInfo.getGroupName().equals("Administrator")){
            PublicVariables.nguoiDungInfo = nguoiDungInfo;
            Intent intent = new Intent(SplashActivity.this, UserKhacActivity.class);
            startActivity(intent);
            finish();

            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);

            } else {
                startLocationService();
            }
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();


        }
    }

    @Override
    public void onLoginError(String error) {
        error = error.isEmpty() ? getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(SplashActivity.this)) {
            error = getResources().getString(R.string.error_msg_no_internet);
        }
        if (alertDialog == null || !alertDialog.isShowing()) {
            Bundle bundle = new Bundle();
            bundle.putString("Error", "Đã có lỗi xảy ra. " + error);
            msg = new Message();
            msg.setData(bundle);
            msg.arg1 = 1;
            handler.sendMessage(msg);
        }
//        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }


    Message msg;
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            try {
                Bundle bundle = msg.getData();
                String error = bundle.getString("Error");
                if (msg.arg1 == 1) {
                    if (error != null) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                        builder.setTitle(getResources().getString(R.string.title_error_msg))
                                .setMessage(error)
                                .setCancelable(false)
                                .setNegativeButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                        dialog.cancel();
                                        moveTaskToBack(true);
                                        finish();
                                    }
                                });
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            } catch (Exception e) {

            }
            return false;
        }
    });
    AlertDialog alertDialog;


    private boolean isLocationServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if(activityManager != null){
            for(ActivityManager.RunningServiceInfo service :
                    activityManager.getRunningServices(Integer.MAX_VALUE)){
                if(LocationService.class.getName().equals(service.service.getClassName())){
                    if(service.foreground){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }


    private void startLocationService(){
        if(!isLocationServiceRunning()){
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constraints.ACTION_START_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            startLocationService();
        } else {
            Toast.makeText(this, "Fail sml", Toast.LENGTH_SHORT).show();
        }

    }


}
