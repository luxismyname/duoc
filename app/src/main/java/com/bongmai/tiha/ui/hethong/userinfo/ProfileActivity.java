package com.bongmai.tiha.ui.hethong.userinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.prefs.AppPreference;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.LetterTileProvider;
import com.bongmai.tiha.utils.PublicVariables;

public class ProfileActivity extends AppCompatActivity implements BaseActivity, ProfileContract.View {

    ImageView imageAvatar;
    TextView
            tvSoDienThoai,
            tvNgaySinh,
            tvCMND,
            tvDiaChi,
            tvEmployeeName;
    Button btnLogout;
    ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        imageAvatar = findViewById(R.id.imageAvatar);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        tvNgaySinh = findViewById(R.id.tvNgaySinh);
        tvCMND = findViewById(R.id.tvCMND);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvEmployeeName = findViewById(R.id.tvEmployeeName);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("ĐĂNG XUẤT")
                        .setMessage("Bạn có chắc muốn đăng xuất ứng dụng?")
                        .setCancelable(false)
                        .setPositiveButton("ĐĂNG XUẤT", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                PublicVariables.ClearData();
                                AppPreference appPreference = new AppPreference(ProfileActivity.this);
                                appPreference.setLogin(false);
                                appPreference.setTenDangNhap("");
                                appPreference.setMatKhau("");
                                appPreference.setNguoiDungID("");
                                Intent intent = getBaseContext().getPackageManager().
                                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
//                                moveTaskToBack(true);
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();

                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    @Override
    public void onConfigToolbar() {

    }

    @Override
    public void onLoadData() {
        profilePresenter = new ProfilePresenter(this);
        SetProfile();

    }

    private void SetProfile() {
        profilePresenter.GetHinhAnhByEmployeeID(PublicVariables.nguoiDungInfo.getEmployeeID());
        tvEmployeeName.setText(PublicVariables.nguoiDungInfo.getEmployeeName());
        tvSoDienThoai.setText(PublicVariables.nguoiDungInfo.getDTNoio());
        tvNgaySinh.setText(AppUtils.formatDateToString(PublicVariables.nguoiDungInfo.getBirthDate(), "dd/MM/yyyy"));
        tvCMND.setText(PublicVariables.nguoiDungInfo.getSecurityNo());
        tvDiaChi.setText(PublicVariables.nguoiDungInfo.getNoiohiennay());

    }

    @Override
    public void onGetHinhAnhByEmployeeIDSuccess(String stringResult) {
        imageAvatar.setImageBitmap(AppUtils.formatStringToBitMap(stringResult));
    }

    @Override
    public void onGetHinhAnhByEmployeeIDError(String error) {
        LetterTileProvider mLetterTileProvider = new LetterTileProvider(this);
        try {
            imageAvatar.setImageBitmap(mLetterTileProvider.getCircularLetterTile(PublicVariables.nguoiDungInfo.getEmployeeName()));
        } catch (Exception e) {
            Log.d("SetEmployeeInfo", e.getMessage());
        }
    }
}
