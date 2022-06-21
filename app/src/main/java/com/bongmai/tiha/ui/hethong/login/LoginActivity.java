package com.bongmai.tiha.ui.hethong.login;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bongmai.tiha.MainActivity;
import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.prefs.AppPreference;
import com.bongmai.tiha.service.Constraints;
import com.bongmai.tiha.service.LocationService;
import com.bongmai.tiha.ui.UserKhacActivity;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.hethong.splash.SplashActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.PublicVariables;
import com.bongmai.tiha.utils.aes.AESUtils;


public class LoginActivity extends AppCompatActivity
        implements LoginContract.View, View.OnClickListener, BaseActivity {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    EditText etTenDangNhap, etMatKhau, etServerNameCTy;
    Button btnDangNhap;
    CheckBox chkGhiNho;
    ImageView imgLogo;
    LoginPresenter loginPresenter;
    AppPreference appPreference;
    Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_login);
        onInit();
        onLoadData();
    }

    @Override
    public void onInit() {
        etTenDangNhap = findViewById(R.id.etTenDangNhap);
        etMatKhau = findViewById(R.id.etMatKhau);
        chkGhiNho = findViewById(R.id.chkGhiNho);
        etServerNameCTy = findViewById(R.id.etServerNameCTy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        imgLogo = findViewById(R.id.imgLogo);
        btnDangNhap.setOnClickListener(this);
        imgLogo.setOnClickListener(this);
    }

    @Override
    public void onConfigToolbar() {

    }

    @Override
    public void onLoadData() {
        appPreference = new AppPreference(this);
        loginPresenter = new LoginPresenter(LoginActivity.this);
    }

    private NguoiDungInfo getNguoiDung() {
        NguoiDungInfo nguoiDungInfo = new NguoiDungInfo();
        nguoiDungInfo.setUserName(etTenDangNhap.getText().toString());
        nguoiDungInfo.setPassword(etMatKhau.getText().toString());
        return nguoiDungInfo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhap:
                NguoiDungInfo nguoiDungInfo = getNguoiDung();
//                AppConstants.SERVER_NAME = etServerNameCTy.getText().toString();
//                AppConstants.URL_SERVER = "http://" + AppConstants.SERVER_NAME;
                loginPresenter.CheckLogin(nguoiDungInfo.getUserName(), nguoiDungInfo.getPassword());
                break;
            case R.id.imgLogo:
                String url = "http://" + getResources().getString(R.string.app_website_customer);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess(NguoiDungInfo nguoiDungInfo) {
        PublicVariables.nguoiDungInfo = nguoiDungInfo;
        AESUtils aesUtils = new AESUtils();
        String userName = "";
        try {
            userName = aesUtils.encrypt(etTenDangNhap.getText().toString());
        } catch (Exception e) {
        }
        String passWord = "";
        try {
            passWord = aesUtils.encrypt(etMatKhau.getText().toString());
        } catch (Exception e) {
        }
        appPreference.setLogin(true);
        appPreference.setTenDangNhap(userName);
        appPreference.setMatKhau(passWord);
        if(nguoiDungInfo.getGroupName().equals("Administrator")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(!nguoiDungInfo.getGroupName().equals("Administrator")){
            Intent intentBH = new Intent(LoginActivity.this, UserKhacActivity.class);
            startActivity(intentBH);
            finish();
        }

    }

    @Override
    public void onLoginError(String error) {
        error = error.isEmpty() ? "Đăng nhập thất bại, hãy thử lại!" : error;
        showMessage("LỖI", error);
    }

    @Override
    public void showProgressBar() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgressBar() {
        showProgressDialog(false);
    }

    private void showProgressDialog(boolean isShow) {
        if (isShow) {
            progressDialog = new Dialog(this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.getWindow().setBackgroundDrawableResource(R.color.colorTransparent);
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.dialog_progressbar_waiting);
            progressDialog.show();

        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    public void showMessage(String title, String body) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //CommonUtils.configShowMessage(builder, title, body);
        builder.setTitle(title)
                .setMessage(body)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }



}
