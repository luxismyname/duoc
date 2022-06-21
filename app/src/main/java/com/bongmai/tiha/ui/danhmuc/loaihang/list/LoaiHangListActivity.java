package com.bongmai.tiha.ui.danhmuc.loaihang.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.danhmuc.loaihang.ThemLoaiHangDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class LoaiHangListActivity extends AppCompatActivity implements BaseActivity, LoaiHangListContract.View {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    BaseService service;
    LoaiHangListAdapter adapterData;
    List<LoaiHangInfo> listData;
    EditText etTimKiem;
    ImageButton btnClear;
    FloatingActionButton fabThem;

    LoaiHangListPresenter loaiHangPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_loaihanglist);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvData = (RecyclerView) findViewById(R.id.rvData);
        etTimKiem = (EditText) findViewById(R.id.etTimKiem);
        btnClear = findViewById(R.id.btnClear);
        fabThem = findViewById(R.id.fabThem);

        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    btnClear.setVisibility(View.VISIBLE);
                else
                    btnClear.setVisibility(View.GONE);
                adapterData.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTimKiem.setText("");
            }
        });

        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());
        rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        listData = new ArrayList<>();
        onBindData();

        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiHangDialog.getInstance().showConfirmDialog("", "", LoaiHangListActivity.this, new ThemLoaiHangDialog.DialogClickInterface() {
                    @Override
                    public void onClickPositiveButton(String maLoai, String tenLoai) {
                        LoaiHangInfo loaiHangInfo = new LoaiHangInfo();
                        loaiHangInfo.setCategory_ID(maLoai);
                        loaiHangInfo.setLoaihang1(tenLoai);
                        loaiHangPresenter.InsertLoaiHang(loaiHangInfo);
                    }

                    @Override
                    public void onClickNegativeButton() {

                    }
                });
            }
        });
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.loaihanglist_tieudeform);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLoadData() {
        loaiHangPresenter = new LoaiHangListPresenter(this);

        loadLoaiHang();

    }

    private void loadLoaiHang() {
        loaiHangPresenter.GetListLoaiHang();
    }

    private void onBindData() {
        adapterData = new LoaiHangListAdapter(LoaiHangListActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHangInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    @Override
    public void onGetListLoaiHangSuccess(List<LoaiHangInfo> listResult) {
        listData = listResult;
        onBindData();
    }

    @Override
    public void onGetListLoaiHangError(String error) {
        listData = new ArrayList<>();
        onBindData();
        Toast.makeText(LoaiHangListActivity.this, "Lấy danh sách loại hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInsertLoaiHangSuccess(LoaiHangInfo itemResult) {
        adapterData.addItem(itemResult, 0);
        Toast.makeText(LoaiHangListActivity.this, "Lưu loại hàng thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInsertLoaiHangError(String error, LoaiHangInfo loaiHangInfo) {
        Toast.makeText(LoaiHangListActivity.this, "Lưu loại hàng thất bại. Vui lòng thử lại!\nChi tiết: "+ error, Toast.LENGTH_LONG).show();
        ThemLoaiHangDialog.getInstance().showConfirmDialog(loaiHangInfo.getCategory_ID(), loaiHangInfo.getLoaihang1(), LoaiHangListActivity.this, new ThemLoaiHangDialog.DialogClickInterface() {
            @Override
            public void onClickPositiveButton(String maLoai, String tenLoai) {
                LoaiHangInfo loaiHangInfo = new LoaiHangInfo();
                loaiHangInfo.setCategory_ID(maLoai);
                loaiHangInfo.setLoaihang1(tenLoai);
                loaiHangPresenter.InsertLoaiHang(loaiHangInfo);
            }

            @Override
            public void onClickNegativeButton() {

            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
