package com.bongmai.tiha.ui.danhmuc.employee.bophannhanvien;

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
import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;

import java.util.ArrayList;
import java.util.List;


public class ListBoPhanActivity extends AppCompatActivity implements BaseActivity, ListBoPhanContract.View {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    BaseService service;
    ListBoPhanAdapter adapterData;
    List<BoPhanInfo> listData;
    EditText etTimKiem;
    ImageButton btnClear;
    String departmentID ="";

    ListBoPhanPresenter presenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_list_bo_phan);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvData = (RecyclerView) findViewById(R.id.rvData);
        etTimKiem = (EditText) findViewById(R.id.etTimKiem);
        btnClear = (ImageButton) findViewById(R.id.btnClear);

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
        String tieuDeForm = getResources().getString(R.string.themnhanvien_txt_bophan);
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
        presenter = new ListBoPhanPresenter(this);

        loadBoPhan();

    }

    private void loadBoPhan() {
        presenter.GetListBoPhan();
    }

    private void onBindData() {
        adapterData = new ListBoPhanAdapter(ListBoPhanActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("BoPhanInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }


    @Override
    public void onGetListBoPhanSuccess(List<BoPhanInfo> listResult) {
        listData = listResult;
        Toast.makeText(ListBoPhanActivity.this, "Lấy danh sách bộ phận thành công", Toast.LENGTH_LONG).show();
        onBindData();
    }

    @Override
    public void onGetListBoPhanError(String error) {
        listData = new ArrayList<>();
        onBindData();
        Toast.makeText(ListBoPhanActivity.this, "Lấy danh sách bộ phận thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
