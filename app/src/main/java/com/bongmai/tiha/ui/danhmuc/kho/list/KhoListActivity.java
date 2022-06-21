package com.bongmai.tiha.ui.danhmuc.kho.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
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
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class KhoListActivity extends AppCompatActivity implements BaseActivity, KhoListContract.View {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    BaseService service;
    KhoListAdapter adapterData;
    List<KhoInfo> listData;
    EditText etTimKiem;
    ImageButton btnClear;
    FloatingActionButton fabThem;

    KhoListPresenter KhoPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_kholist);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.kholist_tieudeform);
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
        KhoPresenter = new KhoListPresenter(this);

        loadKho();

    }

    private void loadKho() {
        KhoPresenter.GetListKho();
//        KhoPresenter.GetListKhoCuuLong();
    }

    private void onBindData() {
        adapterData = new KhoListAdapter(KhoListActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("KhoInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    @Override
    public void onGetListKhoSuccess(List<KhoInfo> listResult) {
        listData = listResult;
        KhoPresenter.GetListKhoCuuLong();
        onBindData();

    }

    @Override
    public void onGetListKhoError(String error) {
        listData = new ArrayList<>();
       onBindData();
        Toast.makeText(KhoListActivity.this, "Lấy danh sách loại hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult) {
        listData = listResult;
        adapterData.addAll(listResult);
    }

    @Override
    public void onGetListKhoCuuLongError(String error) {
        listData = new ArrayList<>();
        onBindData();
        Toast.makeText(KhoListActivity.this, "Lấy danh sách loại hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();

    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
