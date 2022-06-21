package com.bongmai.tiha.ui.danhmuc.soduvattu.list;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiActivity;
import com.bongmai.tiha.ui.danhmuc.soduvattu.fragment.SoDuVatTuDetailActivity;
import com.bongmai.tiha.ui.danhmuc.soduvattu.them.ThemSoDuVatTuActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ListSoDuVatTuActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener, ListSoDuVatTuActivityContract.View {
    Toolbar toolbar;
    SwipeRefreshLayout swipe_refresh;
    RecyclerView rvData;
    EditText etTimKiem;
    ImageButton btnClear;
    View errorLayout;
    Button btnRetry;
    TextView txtError;
    ProgressBar progressBar;
    ConstraintLayout ctlMain;
    ConstraintLayout ctlErrorLayout;
    FloatingActionButton fabThem;
    View ctlNoResultLayout;
    List<SoDuVatTuDauInfo> listData;
    ListSoDuVatTuActivityAdapter adapter;
    ListSoDuVatTuActivityPresenter presenter;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private static final int PAGE_START = 1;
    private static final int PAGE_RECORD = 50;
    private int currentPage = PAGE_START;
    Boolean isShowProgressBar = false;

    private Timer timer;
    private boolean isCombobox = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_so_du_vat_tu);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvData = (RecyclerView) findViewById(R.id.rvData);
        etTimKiem = (EditText) findViewById(R.id.etTimKiem);
        btnClear = (ImageButton) findViewById(R.id.btnClear);
        fabThem = findViewById(R.id.fabThem);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        errorLayout = findViewById(R.id.ctlErrorLayout);
        progressBar = findViewById(R.id.progressBar);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(
                () -> {
                    isShowProgressBar = false;
                    loadListSoDuVatTuDau();
                });

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
                adapter.getFilter().filter(s);
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

        fabThem.setOnClickListener(this);

    }

    private void loadListSoDuVatTuDau() {
        hideErrorView();
        adapter.getListAllData().clear();
        presenter.GetListSoDuVatTuDauKy();
    }

    private void hideErrorView() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
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
        String tieuDeForm = getResources().getString(R.string.soduvattu_list_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());
    }
    @Override
    public void onLoadData() {
        presenter = new ListSoDuVatTuActivityPresenter(this);
        showProgressBar();
        onBindData();
        loadSoDuVatTu();
    }

    private void loadSoDuVatTu() {
        presenter.GetListSoDuVatTuDauKy();
    }

    private void onBindData() {
        adapter = new ListSoDuVatTuActivityAdapter(ListSoDuVatTuActivity.this, listData);
        rvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnLongClickListener(new BaseRecyclerViewEvent.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int positionItem) {
                ArrayList<String> listLongClick = new ArrayList<>();
                listLongClick.add("Cập nhật");
                listLongClick.add("Xóa");

                final CharSequence[] items = listLongClick.toArray(new CharSequence[listLongClick.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListSoDuVatTuActivity.this);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int position) {

                        final SoDuVatTuDauInfo soDuVatTuDauInfo = adapter.getItem(positionItem);
                        switch (position) {
                            case 0:

                                Intent intent = new Intent(ListSoDuVatTuActivity.this, ThemSoDuVatTuActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("SoDuVatTuDauInfo", soDuVatTuDauInfo);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case 1:
                                final AlertDialog.Builder builderDelete = new AlertDialog.Builder(ListSoDuVatTuActivity.this);
                                builderDelete.setTitle("XÓA VẬT TƯ")
                                        .setMessage("Bạn có chắc muốn xóa vật tư này?")
                                        .setCancelable(false)
                                        .setPositiveButton(ListSoDuVatTuActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {

                                                presenter.DeleteSoDuVatTuDau(soDuVatTuDauInfo);
                                                dialog.cancel();
                                            }
                                        })
                                        .setNegativeButton(ListSoDuVatTuActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builderDelete.create();
                                alert.show();
                                break;
                            default:
                                break;
                        }


                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fabThem:
                intent = new Intent(ListSoDuVatTuActivity.this, ThemSoDuVatTuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            default:
                break;
        }

    }

    @Override
    public void onGetListSoDuVatTuDauKySuccess(List<SoDuVatTuDauInfo> listResult) {
        listData = listResult == null ? new ArrayList<>() : listResult;
        hideProgressBar();
        hideErrorView();
        onBindData();
    }

    @Override
    public void onGetListSoDuVatTuDauKyError(String error) {

    }

    @Override
    public void onDeleteSuccess() {
        hideProgressBar();
        hideErrorView();
        Toast.makeText(this, "Xóa số dư vật tư đầu thành công!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDeleteError(String error) {

    }

    @Override
    public void showProgressBar() {
        if (!isShowProgressBar) return;
        showProgressBar(true);
    }

    @Override
    public void hideProgressBar() {
        hideSwipeRefresh();
        showProgressBar(false);
    }

    private void hideSwipeRefresh() {
        try {
            swipe_refresh.setRefreshing(false);
        } catch (Exception e) {

        }
    }
}