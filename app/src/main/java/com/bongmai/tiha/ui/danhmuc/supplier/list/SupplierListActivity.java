package com.bongmai.tiha.ui.danhmuc.supplier.list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.SupplierCondition;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.PaginationAdapterCallback;
import com.bongmai.tiha.ui.customcontrol.PaginationScrollListener;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.ThemKhachHangActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SupplierListActivity extends AppCompatActivity implements BaseActivity, SupplierListContract.View, View.OnClickListener {

    //region Khai bao bien
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

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private static final int PAGE_START = 1;
    private static final int PAGE_RECORD = 50;
    private int currentPage = PAGE_START;
    Boolean isShowProgressBar = false;
    List<SupplierInfo> listData;
    SupplierCondition condition;
    private Timer timer;
    SupplierListAdapter adapterData;
    SupplierListPresenter supplierPresenter;
    private boolean isCombobox = false;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplierlist);
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);
        rvData = findViewById(R.id.rvData);
        etTimKiem = findViewById(R.id.etTimKiem);
        btnClear = findViewById(R.id.btnClear);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        errorLayout = findViewById(R.id.ctlErrorLayout);
        btnRetry = findViewById(R.id.error_btn_retry);
        txtError = findViewById(R.id.error_txt_cause);
        progressBar = findViewById(R.id.progressBar);
        ctlMain = findViewById(R.id.ctlMain);
        ctlErrorLayout = findViewById(R.id.ctlErrorLayout);
        ctlNoResultLayout = findViewById(R.id.ctlNoResultLayout);
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
                if (timer != null)
                    timer.cancel();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SupplierListActivity.this.runOnUiThread(() -> loadFirstPage());
                        }

                    }, AppConstants.DELAY_FIND_DATA);
                }
            }
        });


        btnClear.setOnClickListener(view -> {
            etTimKiem.setText("");
            loadFirstPage();
        });

        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());
//        rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(
                () -> {
                    isShowProgressBar = false;
                    loadFirstPage();
                });
        rvData.addOnScrollListener(new PaginationScrollListener(llm) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFirstPage();
            }
        });



//        ctlErrorLayout.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
        fabThem.setOnClickListener(this);

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.supplierlist_tieudeform);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onLoadData() {
        supplierPresenter = new SupplierListPresenter(this);
        showProgressBar();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            isCombobox = bundle.getBoolean("isCombobox");
        }
        condition = new SupplierCondition();
        condition.setDDBH(PublicVariables.nguoiDungInfo.getEmployeeID());
        condition.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
        condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        condition.setTextSearch("");
        isShowProgressBar = true;
        loadFirstPage();
        onBindData();
    }

    private void onBindData() {
        adapterData = new SupplierListAdapter(SupplierListActivity.this, listData);

        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });


        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (!isCombobox) return;
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SupplierInfo", adapterData.getItem(position));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
//        adapterData.setOnClickListener((view, position) -> {
//            if (!isCombobox) return;
//
//        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        adapterData.setLoadingCallback(new PaginationAdapterCallback() {
            @Override
            public void retryPageLoad() {
                /**
                 * Reset begin, end load record if load data error
                 */
                condition.setBegin(condition.getBegin() - 1);
                condition.setEnd(condition.getEnd() - PAGE_RECORD);
                loadNextPage();
            }
        });

        adapterData.setOnLongClickListener(new BaseRecyclerViewEvent.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                SupplierInfo supplierInfo = adapterData.getItem(position);
                if (supplierInfo == null)return;
                else {
                    Intent intent = new Intent(SupplierListActivity.this, EditKhachHangActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("SupplierInfo", adapterData.getItem(position));
                    intent.putExtras(bundle);
//                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    //finish();
                }
            }
        });

    }

    private void loadFirstPage() {
        hideErrorView();
        hideNoResultView();
        isLastPage = false;
        adapterData = new SupplierListAdapter(SupplierListActivity.this, listData);
        //adapterData.clear();
        condition.setTextSearch(etTimKiem.getText().toString());
        condition.setBegin(1);
        condition.setEnd(PAGE_RECORD);
        supplierPresenter.GetListSupplier(condition);
    }

    private void loadNextPage() {
        condition.setBegin(condition.getEnd() + 1);
        condition.setEnd(condition.getEnd() + PAGE_RECORD);
        supplierPresenter.GetListSupplier(condition);
    }

    @Override
    public void onGetListSupplierSuccess(List<SupplierInfo> listResult, long total) {
        listData = listResult == null ? new ArrayList<>() : listResult;


        if (condition.getBegin() == 1) {
            adapterData.clear();
            hideErrorView();
            if (listResult.size() == 0) {
                showNoResultView();
            }
        } else {
            adapterData.removeLoadingFooter();
            isLoading = false;
        }

        double phanDu = Double.parseDouble(String.valueOf(total)) % PAGE_RECORD;
        int phanNguyen = (Integer.parseInt(String.valueOf(total)) / PAGE_RECORD);
        TOTAL_PAGES = (phanDu > 0) ? phanNguyen + 1 : phanNguyen;
        progressBar.setVisibility(View.GONE);
        adapterData.addAll(listData);

        if (currentPage < TOTAL_PAGES) adapterData.addLoadingFooter();
        else isLastPage = true;
        hideProgressBar();
    }

    @Override
    public void onGetListSupplierError(String error) {
        error = error.isEmpty() ? getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(this)) {
            error = getResources().getString(R.string.error_msg_no_internet);
        }
        if (condition.getBegin() == 1) {
            showErrorView(error);
        } else {
            adapterData.showRetry(true, error);
        }
        hideProgressBar();
        hideNoResultView();
    }


    private void showErrorView(String error) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(error);
        }
    }

    private void hideErrorView() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    private void showNoResultView() {
        if (ctlNoResultLayout.getVisibility() == View.GONE) {
            ctlNoResultLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
        }
    }

    private void hideNoResultView() {
        if (ctlNoResultLayout.getVisibility() == View.VISIBLE) {
            ctlNoResultLayout.setVisibility(View.GONE);
        }
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

    public void showProgressBar(final boolean isShow) {

        int shortAnimTime = AppConstants.config_shortAnimTime;
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
        progressBar.animate().setDuration(shortAnimTime).alpha(
                isShow ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
            }
        });
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabThem:
                Intent intent = new Intent(SupplierListActivity.this, ThemKhachHangActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
