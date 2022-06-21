package com.bongmai.tiha.ui.danhmuc.product.EditSanPham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.danhmuc.product.ThemSanPhamActivity;
import com.bongmai.tiha.ui.danhmuc.product.list.fragment.BlankFragmentProduct;
import com.bongmai.tiha.ui.danhmuc.product.list.fragment.EditSanPhamFragment;
import com.bongmai.tiha.ui.danhmuc.product.list.fragment.ThemSanPhamPageAdapter;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.EditKhachHang.EditKhachHangPresenter;
import com.bongmai.tiha.ui.danhmuc.supplier.ThemKhachHangActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.frame.BlankFragment;
import com.bongmai.tiha.ui.danhmuc.supplier.frame.ThongtinKhachHangFragment;
import com.bongmai.tiha.ui.danhmuc.supplier.frame.ThongtinKhachHangPagerAdapter;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.google.android.material.tabs.TabLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class EditSanPhamActivity extends AppCompatActivity implements EditSanPhamContract.View {

    Toolbar toolbar;
    ThemSanPhamPageAdapter adapter;
    ProductInfo productInfo;
    ViewPager viewPager;
    TabLayout tabLayout;
    String productID = "";
    String supplierID = "";
    EditSanPhamPresenter presenter;
    BlankFragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_san_pham);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    private void onConfigToolbar() {

        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.update_Product_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void onInit() {

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

    }

    private void onLoadData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            ProductInfo productInfo= (ProductInfo) bundle.getSerializable("ProductInfo");
            productID = productInfo == null ? "" : productInfo.getProduct_ID();

        }
        presenter = new EditSanPhamPresenter(this);
        presenter.getProduct(productID);

    }



    @Override
    public void onGetProductSuccess(ProductInfo productInfo) {

        this.productInfo = productInfo;
        setUpViewPageBar();

    }

    private void setUpViewPageBar() {

        int tabCount = 2;
        // tabLayout = new TabLayout(this);
        adapter = new ThemSanPhamPageAdapter(getSupportFragmentManager(),EditSanPhamActivity.this, productInfo, tabCount );
        viewPager.setOffscreenPageLimit(tabCount);
        adapter.addFragment(new EditSanPhamFragment().newInstance(productInfo), getResources().getString(R.string.fragment_Thongtinsanpham_detail),0);
       // adapter.addFragment(new EditSanPhamFragment().newInstance(productInfo), "",0);

        //adapter.addFragment(new BlankFragmentProduct().newInstance(productInfo),"test", 1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    public void onGetProductError(String error) {

        error = error.isEmpty() ? getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(this)) {
            error = getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        if (!this.isFinishing()) {
            alert.show();
        }

    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(this,"Sản phẩm đã được xóa!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDeleteError(String error) {
        CommonUtils.showMessageError(this, error);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_edit_supplier, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:

                if (productInfo == null) break;
                Intent intent = new Intent(EditSanPhamActivity.this, ThemSanPhamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProductID", productInfo.getProduct_ID());
                intent.putExtras(bundle);
                startActivity(intent);

                break;


            case R.id.action_delete:
                SupplierInfo supplierInfo = new SupplierInfo();
                if (productInfo == null) break;
//                if (baoCaoDichVuInfo.getTrangThai() == BaoCaoDichVuInfo.TRANGTHAI_HOANTHANH) {
//                    Toast.makeText(ServiceReportDetailActivity.this, "Service report đã " + getResources().getString(R.string.trangthai_servicereport_hoanthanh)
//                            + " bạn không được phép xóa. Vui lòng liên hệ Admin để được hỗ trợ!", Toast.LENGTH_LONG).show();
//                    break;
//                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(EditSanPhamActivity.this);
                builder.setTitle("XÓA SẢN PHẨM")
                        .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                presenter.DeleteProduct(productInfo.getProduct_ID());

                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                //navigationView.setCheckedItem(itemIdLast);
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}