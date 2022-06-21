package com.bongmai.tiha.ui.danhmuc.product.list;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.nhapsoluongsanpham.NhapSoLuongSanPhamActivity;
import com.bongmai.tiha.ui.customcontrol.PaginationAdapterCallback;
import com.bongmai.tiha.ui.customcontrol.PaginationScrollListener;
import com.bongmai.tiha.ui.danhmuc.product.EditSanPham.EditSanPhamActivity;
import com.bongmai.tiha.ui.danhmuc.product.ThemSanPhamActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.ui.khac.QuetMaVachActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ProductListActivity extends AppCompatActivity implements BaseActivity, ProductListContract.View, View.OnClickListener {

    //region Khai bao bien
    Toolbar toolbar;
    SwipeRefreshLayout swipe_refresh;
    RecyclerView rvData;
    EditText etTimKiem;
    ImageButton btnClear, btnBarcode;
    View errorLayout;
    Button btnRetry;
    TextView txtError;
    ProgressBar progressBar;
    ConstraintLayout ctlMain;
    FloatingActionButton fabThem;
    View ctlNoResultLayout;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private static final int PAGE_START = 1;
    private static final int PAGE_RECORD = 50;
    private int currentPage = PAGE_START;
    Boolean isShowProgressBar = false;
    List<ProductInfo> listData;
    ProductCondition condition;
    private Timer timer;
    ProductInfo productInfo;
    ProductListAdapter adapterData;
    ProductListPresenter productListPresenter;
    NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo;
    PhieuXuatInfo phieuXuatInfo;
    List<VattuxuatInfo> listVattuxuat;
    private boolean isCombobox;

    private static final int REQUEST_NHAPSOLUONG = 1;
    private static final int REQUEST_MAVACH = 2;
    private static final int REQUEST_THEMSANPHAM = 3;
    private static final int ZXING_CAMERA_PERMISSION_REQUEST = 999;

    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        ctlNoResultLayout = findViewById(R.id.ctlNoResultLayout);
        fabThem = findViewById(R.id.fabThem);
        btnBarcode = findViewById(R.id.btnBarcode);

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
                            ProductListActivity.this.runOnUiThread(() -> loadFirstPage());
                        }

                    }, AppConstants.DELAY_FIND_DATA);
                }
            }
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

        listData = new ArrayList<>();
        onBindData();

//        ctlErrorLayout.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
        btnClear.setOnClickListener(this);
        btnRetry.setOnClickListener(this);
        fabThem.setOnClickListener(this);
        btnBarcode.setOnClickListener(this);

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.productlist_tieudeform);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onLoadData() {
        productListPresenter = new ProductListPresenter(this);
        showProgressBar();
        condition = new ProductCondition();
        condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        isShowProgressBar = true;
        loadFirstPage();

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            nhapSoLuongSanPhamInfo = (NhapSoLuongSanPhamInfo) bundle.getSerializable("NhapSoLuongSanPhamInfo");
            listVattuxuat = (List<VattuxuatInfo>) bundle.getSerializable("ListVattuxuat");
            phieuXuatInfo = (PhieuXuatInfo) bundle.getSerializable("PhieuXuatInfo");
            isCombobox = bundle.getBoolean("isCombobox");
        }
    }

    private void onBindData() {
        adapterData = new ProductListAdapter(ProductListActivity.this, listData);
        adapterData.setOnClickListener((view, positionItem) -> {
            if (!isCombobox) return;
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();


        });


        adapterData.setOnClickListener((view, positionItem) ->{

            if (nhapSoLuongSanPhamInfo != null) {

                ProductInfo productInfo = adapterData.getItem(positionItem);

                int position = -1;

                for (int i = 0; i < listVattuxuat.size(); i++) {
                    if (listVattuxuat.get(i).getProduct_ID().equals(productInfo.getProduct_ID())) {
                        position = i;
                        break;
                    }
                }
                if (position == -1) {
                    Intent intent = new Intent(ProductListActivity.this, NhapSoLuongSanPhamActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    nhapSoLuongSanPhamInfo.setProduct_ID(productInfo.getProduct_ID());
                    nhapSoLuongSanPhamInfo.setProduct_Name(productInfo.getProduct_Name());
                    nhapSoLuongSanPhamInfo.setLoaiDiscount(2);
                    nhapSoLuongSanPhamInfo.setGiatriDiscount(0);
                    nhapSoLuongSanPhamInfo.setSL(1.0);

                    bundle.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
                    bundle.putBoolean("isNew", true);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_NHAPSOLUONG);
                } else {
                    VattuxuatInfo vattuxuatInfo = listVattuxuat.get(position);
                    NhapSoLuongSanPhamInfo itemNhapSoLuongSP = new NhapSoLuongSanPhamInfo(phieuXuatInfo, vattuxuatInfo);
                    itemNhapSoLuongSP.setSL(itemNhapSoLuongSP.getSL() + 1);
                    Intent intent = new Intent(ProductListActivity.this, NhapSoLuongSanPhamActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NhapSoLuongSanPhamInfo", itemNhapSoLuongSP);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_NHAPSOLUONG);
                }
            } else if (isCombobox) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
            else {
                Intent intent = new Intent(ProductListActivity.this, EditSanPhamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                startActivity(intent);
                //finish();
            }
        });
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

//        adapterData.setOnLongClickListener((view, position) -> {
//            ArrayList<String> listLongClick = new ArrayList<>();
//            listLongClick.add("Cập nhật");
//            listLongClick.add("Xóa");
//            listLongClick.add("Xóa tất cả");
//
//            final CharSequence[] items = listLongClick.toArray(new CharSequence[listLongClick.size()]);
//            AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this);
//            builder.setItems(items, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int positionItem) {
//
//                    switch (position) {
//                        case 0:
//                            Intent intent = new Intent(ProductListActivity.this, EditSanPhamActivity.class);
//                            Bundle bundle = new Bundle();
////                            bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
//                            bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
//                            intent.putExtras(bundle);
//                            setResult(RESULT_OK, intent);
//                            startActivity(intent);
//
////                            if (productInfo == null) break;
////                            Intent intent = new Intent(ProductListActivity.this, EditSanPhamActivity.class);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            Bundle bundle = new Bundle();
////                            bundle.putSerializable("ProductInfo", productInfo);
////                            intent.putExtras(bundle);
////                            startActivity(intent);
//
//                            break;
//                        case 1:
//                            final AlertDialog.Builder builderDelete = new AlertDialog.Builder(ProductListActivity.this);
//                            builderDelete.setTitle("XÓA SẢN PHẨM")
//                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
//                                    .setCancelable(false)
//                                    .setPositiveButton(ProductListActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//                                            adapterData.removeItem(position);
//                                            dialog.cancel();
//                                        }
//                                    })
//                                    .setNegativeButton(ProductListActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//                                            dialog.cancel();
//                                        }
//                                    });
//                            final AlertDialog alert = builderDelete.create();
//                            alert.show();
//                            break;
//                        case 2:
//                            final AlertDialog.Builder builderDeleteAll = new AlertDialog.Builder(ProductListActivity.this);
//                            builderDeleteAll.setTitle("XÓA SẢN PHẨM")
//                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
//                                    .setCancelable(false)
//                                    .setPositiveButton(ProductListActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//
//                                            adapterData.setData(new ArrayList<>());
//                                            dialog.cancel();
//                                        }
//                                    })
//                                    .setNegativeButton(ProductListActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//                                            dialog.cancel();
//                                        }
//                                    });
//                            AlertDialog alertDeleteAll = builderDeleteAll.create();
//                            alertDeleteAll.show();
//                            break;
//                        default:
//                            break;
//                    }
//
//
//                    dialog.dismiss();
//                }
//            });
//            builder.show();
//
//
//        });

    }

    private void loadFirstPage() {
        hideErrorView();
        hideNoResultView();
        isLastPage = false;
        adapterData.clear();
        condition.setTextSearch(etTimKiem.getText().toString());
        condition.setBegin(1);
        condition.setEnd(PAGE_RECORD);
        productListPresenter.GetListProduct(condition);
    }

    private void loadNextPage() {
        condition.setBegin(condition.getEnd() + 1);
        condition.setEnd(condition.getEnd() + PAGE_RECORD);
        productListPresenter.GetListProduct(condition);
    }

    @Override
    public void onGetListProductSuccess(List<ProductInfo> listResult, long total) {
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
    public void onGetListProductError(String error) {
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundleResult;
        Intent intent;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_NHAPSOLUONG:
                    intent = new Intent();
                    bundleResult = data.getExtras();
                    NhapSoLuongSanPhamInfo nhapSLSanPhamInfo = (NhapSoLuongSanPhamInfo) bundleResult.getSerializable("NhapSoLuongSanPhamInfo");
                    bundleResult.putSerializable("NhapSoLuongSanPhamInfo", nhapSLSanPhamInfo);
                    intent.putExtras(bundleResult);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case REQUEST_MAVACH:
                    bundleResult = data.getExtras();
                    String maVach = bundleResult.getString("Code");
                    etTimKiem.setText(maVach);
                    break;
                case REQUEST_THEMSANPHAM:
                    if (nhapSoLuongSanPhamInfo != null) {
                        bundleResult = data.getExtras();
                        ProductInfo productInfo = (ProductInfo) bundleResult.getSerializable("ProductInfo");
                        intent = new Intent(ProductListActivity.this, NhapSoLuongSanPhamActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        nhapSoLuongSanPhamInfo.setProduct_ID(productInfo.getProduct_ID());
                        nhapSoLuongSanPhamInfo.setProduct_Name(productInfo.getProduct_Name());
                        nhapSoLuongSanPhamInfo.setLoaiDiscount(2);
                        nhapSoLuongSanPhamInfo.setGiatriDiscount(0);
                        nhapSoLuongSanPhamInfo.setSL(1.0);

                        bundle.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
                        bundle.putBoolean("isNew", true);
                        bundle.putSerializable("ListVattuxuat", (Serializable) adapterData.getListAllData());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, REQUEST_NHAPSOLUONG);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Display layout when error
     *
     * @param error
     */
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
            case R.id.btnClear:
                etTimKiem.setText("");
                loadFirstPage();
                break;
            case R.id.fabThem:
                Intent intent = new Intent(ProductListActivity.this, ThemSanPhamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_THEMSANPHAM);
                break;
            case R.id.error_btn_retry:
                loadFirstPage();
                break;
            case R.id.btnBarcode:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION_REQUEST);
                } else {
                    intent = new Intent(this, QuetMaVachActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent, REQUEST_MAVACH);
                }
                break;
            default:
                break;
        }
    }
}
