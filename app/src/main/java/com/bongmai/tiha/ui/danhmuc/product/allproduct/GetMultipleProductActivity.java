package com.bongmai.tiha.ui.danhmuc.product.allproduct;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiActivity;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiAdapter;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GetMultipleProductActivity extends AppCompatActivity implements BaseActivity, GetMultipleProductActivityContract.View, View.OnClickListener {

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
    View ctlNoResultLayout;
    TextView tvSLMon, tvTongTien;
    CardView cvBottom;
    PhieuBanSiAdapter adapterPhieuBanSi;
    ProductInfo productInfo;
    ImageView btnMinus, btnPlus;
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
    int positionHandle = -1;
    GetMultipleProductAdapter adapterData;
    GetMultipleProductActivityPresenter productListPresenter;
    NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo;
    PhieuXuatInfo phieuXuatInfo;
    VattuxuatInfo vattuxuatInfo;
    List<VattuxuatInfo> listVattuxuat;
    private boolean isCombobox;

    private static final int REQUEST_NHAPSOLUONG = 1;
    private static final int REQUEST_MAVACH = 2;
    private static final int REQUEST_THEMSANPHAM = 3;
    private static final int ZXING_CAMERA_PERMISSION_REQUEST = 999;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_multiple_product);
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
        tvSLMon = findViewById(R.id.textViewSoMon);
        tvTongTien = findViewById(R.id.tvTongTienTT);
        cvBottom = findViewById(R.id.cvBottom);
        btnBarcode = findViewById(R.id.btnBarcode);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

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
//        rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(
                () -> {
                    isShowProgressBar = false;
                    loadFirstPage();
                });


        listData = new ArrayList<>();
        onBindData();

        btnClear.setOnClickListener(this);
        btnRetry.setOnClickListener(this);

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
        productListPresenter = new GetMultipleProductActivityPresenter(this);
        showProgressBar();
        productInfo = new ProductInfo();
        vattuxuatInfo = new VattuxuatInfo();
        condition = new ProductCondition();
        condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        listVattuxuat = new ArrayList<>();
        adapterPhieuBanSi = new PhieuBanSiAdapter(this, listVattuxuat);
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
        adapterData = new GetMultipleProductAdapter(GetMultipleProductActivity.this, listData);
//        adapterData.setOnClickListener((view, positionItem) -> {
//            if (!isCombobox) return;
//            Intent intent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
//            intent.putExtras(bundle);
//            setResult(RESULT_OK, intent);
//            finish();
//
//        });


//        adapterData.setOnClickListener((view, positionItem) ->{
//
//            if (nhapSoLuongSanPhamInfo != null) {
//
//                ProductInfo productInfo = adapterData.getItem(positionItem);
//
//                int position = -1;
//
//                for (int i = 0; i < listVattuxuat.size(); i++) {
//                    if (listVattuxuat.get(i).getProduct_ID().equals(productInfo.getProduct_ID())) {
//                        position = i;
//                        break;
//                    }
//                }
//                if (position == -1) {
//                    Intent intent = new Intent(GetMultipleProductActivity.this, NhapSoLuongSanPhamActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Bundle bundle = new Bundle();
//                    nhapSoLuongSanPhamInfo.setProduct_ID(productInfo.getProduct_ID());
//                    nhapSoLuongSanPhamInfo.setProduct_Name(productInfo.getProduct_Name());
//                    nhapSoLuongSanPhamInfo.setLoaiDiscount(2);
//                    nhapSoLuongSanPhamInfo.setGiatriDiscount(0);
//                    nhapSoLuongSanPhamInfo.setSL(1.0);
//
//                    bundle.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
//                    bundle.putBoolean("isNew", true);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, REQUEST_NHAPSOLUONG);
//                } else {
//                    VattuxuatInfo vattuxuatInfo = listVattuxuat.get(position);
//                    NhapSoLuongSanPhamInfo itemNhapSoLuongSP = new NhapSoLuongSanPhamInfo(phieuXuatInfo, vattuxuatInfo);
//                    itemNhapSoLuongSP.setSL(itemNhapSoLuongSP.getSL() + 1);
//                    Intent intent = new Intent(GetMultipleProductActivity.this, NhapSoLuongSanPhamActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("NhapSoLuongSanPhamInfo", itemNhapSoLuongSP);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, REQUEST_NHAPSOLUONG);
//                }
//            } else if (isCombobox) {
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
//                intent.putExtras(bundle);
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//            else {
//                Intent intent = new Intent(GetMultipleProductActivity.this, EditSanPhamActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ProductInfo", adapterData.getItem(positionItem));
//                intent.putExtras(bundle);
//                setResult(RESULT_OK, intent);
//                startActivity(intent);
//                //finish();
//            }
//        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        adapterData.setOnButtonClickListener(new BaseRecyclerViewEvent.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                positionHandle = position;
                ProductInfo prd = adapterData.getItem(position);
                double tongsl = 0;
                double giabanle = 0;
                switch (view.getId()) {
                    case R.id.btnMinus:
                        prd.setSoLuong(prd.getSoLuong() - 1);

                        adapterData.updateItem(position, prd);
                        adapterData.updateListProductChon(prd);
                        for (ProductInfo prdo : adapterData.getListProductChon()) {
                            tongsl += prdo.getSoLuong();
                            giabanle += prdo.getGiaBanLe() * prdo.getSoLuong();

                        }
                        tvSLMon.setText(AppUtils.formatNumber("N0").format(tongsl));
                        tvTongTien.setText(AppUtils.formatNumber("N0").format(giabanle));
                        if (tongsl == 0) {
                            cvBottom.setVisibility(View.INVISIBLE);
                        }

                        break;
                    case R.id.btnPlus:
                        prd.setSoLuong(prd.getSoLuong() + 1);

                        adapterData.updateItem(position, prd);
                        adapterData.updateListProductChon(prd);
                        for (ProductInfo prdo : adapterData.getListProductChon()) {
                            tongsl += prdo.getSoLuong();
                            giabanle += prdo.getGiaBanLe() * prdo.getSoLuong();

                        }
                        tvSLMon.setText(AppUtils.formatNumber("N0").format(tongsl));
                        tvTongTien.setText(AppUtils.formatNumber("N0").format(giabanle));
                        cvBottom.setVisibility(View.VISIBLE);

                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void loadFirstPage() {
        hideErrorView();
        hideNoResultView();
        isLastPage = false;
        adapterData.clear();
        condition.setTextSearch(etTimKiem.getText().toString());
        condition.setBegin(1);
        condition.setEnd(PAGE_RECORD);
        productListPresenter.GetListAllProduct(condition);
    }


    @Override
    public void onGetListAllProductSuccess(List<ProductInfo> listResult) {
        listData = listResult == null ? new ArrayList<>() : listResult;
        adapterData.addAll(listResult);
        hideProgressBar();
    }

    @Override
    public void onGetListAllProductError(String error) {
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
                        intent = new Intent(GetMultipleProductActivity.this, NhapSoLuongSanPhamActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        nhapSoLuongSanPhamInfo.setProduct_ID(productInfo.getProduct_ID());
                        nhapSoLuongSanPhamInfo.setProduct_Name(productInfo.getProduct_Name());
                        nhapSoLuongSanPhamInfo.setLoaiDiscount(2);
                        nhapSoLuongSanPhamInfo.setGiatriDiscount(0);
                        nhapSoLuongSanPhamInfo.setSL(productInfo.getSoLuong());

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
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:
                List<VattuxuatInfo> listVattuxuat = new ArrayList<>();
                VattuxuatInfo vattuxuatInfo;
                if (adapterData.getListProductChon().size() == 0) {
                    CommonUtils.showMessageError(this, "Bạn chưa nhập số lượng!");
                    break;
                } else {
                    for (ProductInfo prdo : adapterData.getListProductChon()) {
                        vattuxuatInfo = new VattuxuatInfo();
                        vattuxuatInfo.setProduct_Name(prdo.getProduct_Name());
                        vattuxuatInfo.setProduct_ID(prdo.getProduct_ID());
                        vattuxuatInfo.setSoLuongThuc(prdo.getSoLuong());
                        vattuxuatInfo.setSL(vattuxuatInfo.getSoLuongThuc());
                        vattuxuatInfo.setDongia(prdo.getGiaBanLe());
                        vattuxuatInfo.setDonGiaThuc(vattuxuatInfo.getDongia());
                        vattuxuatInfo.setDvt(prdo.getDonVitinh());
                        vattuxuatInfo.setGiaban(prdo.getGiaBanLe());
                        vattuxuatInfo.setThue(0.0);
                        vattuxuatInfo.setThanh_Tien(vattuxuatInfo.getSL() * vattuxuatInfo.getDongia() * (1 + vattuxuatInfo.getThue()));
                        listVattuxuat.add(vattuxuatInfo);
                    }
                }
                Intent intent = new Intent(this, PhieuBanSiActivity.class);
                Bundle bundle = new Bundle();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle.putSerializable("ListVattuxuat", (Serializable) listVattuxuat);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();


//                double soluong = 0;
//                int position = 0;
//                for (ProductInfo prd : adapterData.getListAllData()) {
//                    soluong += prd.getSoLuong();
//                }
//                if (soluong == 0) {
//                    CommonUtils.showMessageError(this, "Bạn chưa nhập số lượng!");
//                    break;
//                }
//
//                if (soluong > 0) {
//                    for (ProductInfo prdo : adapterData.getListAllData()) {
//                        soluong += prdo.getSoLuong();
//                            if(listData != null && listData.size() > 0 && prdo.getSoLuong() > 0){
//                                for(int i = 0; i < listData.size(); i++){
//                                    position++;
//                                    Intent intent = new Intent(this, PhieuBanSiActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    vattuxuatInfo.setProduct_Name(prdo.getProduct_Name());
//                                    vattuxuatInfo.setProduct_ID(prdo.getProduct_ID());
//                                    vattuxuatInfo.setSoLuongThuc(soluong);
//                                    vattuxuatInfo.setSL(soluong);
//                                    vattuxuatInfo.setDongia(prdo.getGiaBanLe());
//                                    vattuxuatInfo.setGiaban(prdo.getGiaBanLe());
//                                    vattuxuatInfo.setThanh_Tien(prdo.getSoLuong() * prdo.getGiaBanLe());
//                                    bundle.putSerializable("ProductInfo", prdo);
//                                    intent.putExtras(bundle);
//                                    setResult(RESULT_OK, intent);
//                                    finish();
//
//                                }
//                            }
////
//                    }
//                }

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
