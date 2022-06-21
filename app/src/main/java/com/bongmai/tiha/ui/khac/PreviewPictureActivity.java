package com.bongmai.tiha.ui.khac;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bongmai.tiha.R;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;


public class PreviewPictureActivity extends AppCompatActivity implements BaseActivity {
    Toolbar toolbar;
    ImageView imgHinhAnhKH;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewpicture);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);
        imgHinhAnhKH = findViewById(R.id.imgHinhAnh);
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.app_tieudeform) +" " + getResources().getString(R.string.previewpicture_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLoadData() {
        imgHinhAnhKH.setImageBitmap(PublicVariables.BMPicture);
    }
}
