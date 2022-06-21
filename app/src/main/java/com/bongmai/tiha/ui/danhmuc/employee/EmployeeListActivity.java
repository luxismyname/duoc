package com.bongmai.tiha.ui.danhmuc.employee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.bongmai.tiha.data.entities.EmployeeCondition;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.PaginationScrollListener;
import com.bongmai.tiha.ui.danhmuc.employee.themnhanvien.ThemNhanVienActivity;
import com.bongmai.tiha.ui.danhmuc.employee.updatethongtinnhanvien.UpdateThongTinNhanVienActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.ThemKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class EmployeeListActivity extends AppCompatActivity implements BaseActivity, EmployeeListContract.View, View.OnClickListener {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    EmployeeListAdapter adapterData;
    List<EmployeeInfo> list;
    EmployeeInfo employeeInfo;
    //EmployeeCondition condition;

    EditText etTimKiem;
    ImageButton btnClear;
    FloatingActionButton fabThem;
    EmployeeListPresenter employeePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelist);
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
        btnClear = (ImageButton) findViewById(R.id.btnClear);
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
        list = new ArrayList<>();

        fabThem.setOnClickListener(this);

    }


    public void showProgressBar(final boolean isShow) {

        int shortAnimTime = AppConstants.config_shortAnimTime;

        rvData.setVisibility(isShow ? View.GONE : View.VISIBLE);
        rvData.animate().setDuration(shortAnimTime).alpha(
                isShow ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rvData.setVisibility(isShow ? View.GONE : View.VISIBLE);
            }
        });
    }


    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.employeelist_tieudeform);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onLoadData() {
        employeePresenter = new EmployeeListPresenter(this);
        showProgressBar();
        onBindData();
        loadEmployee();
    }

    private void loadEmployee() {
        employeePresenter.GetListEmployee();
    }

    private void onBindData() {
        adapterData = new EmployeeListAdapter(EmployeeListActivity.this, list);
;

        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("EmployeeInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                //startActivity(intent);
                finish();
            }
        });

        adapterData.setOnLongClickListener(new BaseRecyclerViewEvent.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(EmployeeListActivity.this, UpdateThongTinNhanVienActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("EmployeeInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
//                setResult(RESULT_OK, intent);
                startActivity(intent);
                finish();
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    @Override
    public void onGetListEmployeeSuccess(List<EmployeeInfo> listResult) {
        list = listResult == null ? new ArrayList<>() : listResult;

        onBindData();

    }

    @Override
    public void onGetListEmployeeError(String error) {
        PublicVariables.listEmployee = new ArrayList<>();
        onBindData();
        Toast.makeText(EmployeeListActivity.this, "Lấy danh sách nhân viên thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabThem:
                Intent intent = new Intent(EmployeeListActivity.this, ThemNhanVienActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
