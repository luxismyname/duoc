package com.bongmai.tiha.ui.danhmuc.product;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DonViTinhInfo;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.LoaiSanPhamInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.danhmuc.loaihang.list.LoaiHangListActivity;
import com.bongmai.tiha.ui.danhmuc.supplier.list.SupplierListActivity;
import com.bongmai.tiha.ui.khac.PreviewPictureActivity;
import com.bongmai.tiha.ui.khac.QuetMaVachActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.ImageFilePath;
import com.bongmai.tiha.utils.ImageUtils;
import com.bongmai.tiha.utils.NumberTextWatcher;
import com.bongmai.tiha.utils.PublicVariables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class ThemSanPhamActivity extends AppCompatActivity implements BaseActivity, ThemSanPhamContract.View, View.OnClickListener {
    private static final String TAG = ThemSanPhamActivity.class.getName();
    Toolbar toolbar;
    EditText
            etMaSanPham,
            etMaVach,
            etTenSanPham,
            etDongGoi,
            etDongGoi2,
            etGiaNhap,
            etGiaBanLe,
            etGiaBanSi,
            etGiaBanNo,
            etNhomSanPham,
            etLoaiSanPham,
            etDienGiai,
            etSLToiDa,
            etSLToiThieu,
            etThueSuat,
            etNhaCungCap;
    AutoCompleteTextView
            etDonViTinh,
            etDonViTinh2;
    ImageButton
            btnQuetMaVach,
            btnShowPopupDonViTinh,
            btnShowPopupDonViTinh2;
    Button btnMore;
    ConstraintLayout ctlMoreInfo, ctlMore;
    View btnThemHinhAnh;
    ImageView imgHinhAnh;
    ImageButton btnXoaHinhAnh;
    Bitmap bmHinhAnh;
    Dialog progressDialog;
    ThemSanPhanPresenter themSanPhanPresenter;
    ProductInfo productCurrent;
    Boolean isAdd, isEdit;
    Boolean isCapNhatSanPham;

    private static final int REQUEST_NHOMSANPHAM = 0;
    private static final int REQUEST_MAVACH = 1;
    private static final int REQUEST_LOAIHANG = 2;
    private static final int REQUEST_CAMERA = 3;
    private static final int REQUEST_GALLERY = 4;
    private static final int REQUEST_NHACUNGCAP = 5;
    private static final int REQUEST_CAMERA_AND_GALLERY_PERMISSION = 90;
    private static final int ZXING_CAMERA_PERMISSION_REQUEST = 999;
    private static final int REQUEST_MULTIPLE_PERMISSIONS = 100;

    DonViTinhAdapter donViTinhAdapter;

    //Quyen camera va bo suu tap
    String[] cameraAndGalleryPermissionsRequired = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsanpham);
        if (ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, cameraAndGalleryPermissionsRequired, REQUEST_MULTIPLE_PERMISSIONS);
            //return;
        }

        onInit();
        onConfigToolbar();
        onLoadData();
        //onBindData();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);

        etMaSanPham = findViewById(R.id.etMaSanPham);
        etMaVach = findViewById(R.id.etMaVach);
        etTenSanPham = findViewById(R.id.etTenSanPham);
        etDongGoi = findViewById(R.id.etDongGoi);
        etDongGoi2 = findViewById(R.id.etDongGoi2);
        etGiaNhap = findViewById(R.id.etGiaNhap);
        etGiaBanLe = findViewById(R.id.etGiaBanLe);
        etGiaBanSi = findViewById(R.id.etGiaBanSi);
        etGiaBanNo = findViewById(R.id.etGiaBanNo);
        etDonViTinh = findViewById(R.id.etDonViTinh);
        etDonViTinh2 = findViewById(R.id.etDonViTinh2);
        etNhomSanPham = findViewById(R.id.etNhomSanPham);
        etLoaiSanPham = findViewById(R.id.etLoaiSanPham);
        etDienGiai = findViewById(R.id.etDienGiai);
        etSLToiDa = findViewById(R.id.etSLToiDa);
        etSLToiThieu = findViewById(R.id.etSLToiThieu);
        etThueSuat = findViewById(R.id.etThueSuat);
        etNhaCungCap = findViewById(R.id.etNhaCungCap);

        btnQuetMaVach = findViewById(R.id.btnQuetMaVach);
        btnShowPopupDonViTinh = findViewById(R.id.btnShowPopupDonViTinh);
        btnShowPopupDonViTinh2 = findViewById(R.id.btnShowPopupDonViTinh2);
        btnMore = findViewById(R.id.btnMore);

        ctlMoreInfo = findViewById(R.id.ctlMoreInfo);
        ctlMore = findViewById(R.id.ctlMore);

        ctlMoreInfo.setVisibility(View.GONE);

        //Hinh anh
        btnThemHinhAnh = findViewById(R.id.btnThemHinhAnh);
        imgHinhAnh = findViewById(R.id.imgHinhAnh);
        btnXoaHinhAnh = findViewById(R.id.btnXoaHinhAnh);

        btnMore.setOnClickListener(this);
        btnQuetMaVach.setOnClickListener(this);
        btnShowPopupDonViTinh.setOnClickListener(this);
        btnShowPopupDonViTinh2.setOnClickListener(this);
        etNhomSanPham.setOnClickListener(this);
        etLoaiSanPham.setOnClickListener(this);
        btnThemHinhAnh.setOnClickListener(this);
        btnXoaHinhAnh.setOnClickListener(this);
        etNhaCungCap.setOnClickListener(this);


        etDongGoi.addTextChangedListener(new NumberTextWatcher(etDongGoi, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etDongGoi2.addTextChangedListener(new NumberTextWatcher(etDongGoi2, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etGiaNhap.addTextChangedListener(new NumberTextWatcher(etGiaNhap, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etGiaBanLe.addTextChangedListener(new NumberTextWatcher(etGiaBanLe, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etGiaBanNo.addTextChangedListener(new NumberTextWatcher(etGiaBanNo, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etGiaBanSi.addTextChangedListener(new NumberTextWatcher(etGiaBanSi, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));

        etDienGiai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                return false;
            }

        });


        //Upper character
//        etDonViTinh.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
//        etDonViTinh2.setFilters(new  InputFilter[]{new InputFilter.AllCaps()});

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.app_tieudeform)
                + " " + getResources().getString(R.string.themsanpham_tieudeform);
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLoadData() {
        themSanPhanPresenter = new ThemSanPhanPresenter(this);
        loadDonViTinh();
        //onBindData();
        String productID = null;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            productID = bundle.getString("ProductID");
            isCapNhatSanPham = bundle.getBoolean("isCapNhatSanPham");
        }
        if (TextUtils.isEmpty(productID))//Edit
        {
            AddNew();
        } else//Add new
        {
            Edit();
        }
    }

    private void loadDonViTinh() {

        donViTinhAdapter = new DonViTinhAdapter(this, DonViTinhInfo.loadListDonViTinhMau());
        etDonViTinh.setAdapter(donViTinhAdapter);

        etDonViTinh2.setAdapter(donViTinhAdapter);
    }

    private void loadLoaiSanPham() {
        ArrayList<String> listLoaiSanPhamStr = new ArrayList<>();
        final List<LoaiSanPhamInfo> listLoaiSanPham = LoaiSanPhamInfo.loadLoaiSanPham();
        for (LoaiSanPhamInfo item : listLoaiSanPham) {
            listLoaiSanPhamStr.add(item.getTenLoaiSanPham());
        }
        final CharSequence[] items = listLoaiSanPhamStr.toArray(new CharSequence[listLoaiSanPham.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("CHỌN BẢNG GIÁ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                LoaiSanPhamInfo loaiSanPhamInfo = listLoaiSanPham.get(position);
                productCurrent.setLoai(loaiSanPhamInfo.getMaLoaiSanPham());
                etLoaiSanPham.setText(loaiSanPhamInfo.getTenLoaiSanPham());
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void SetHinhAnh(Bitmap bitmapHinhAnh) {
        if (bitmapHinhAnh == null) {
            bmHinhAnh = null;
            imgHinhAnh.setImageDrawable(getResources().getDrawable(R.drawable.ic_product));
            btnXoaHinhAnh.setVisibility(View.GONE);
        } else {
            imgHinhAnh.setImageBitmap(bitmapHinhAnh);
            bmHinhAnh = bitmapHinhAnh;
            btnXoaHinhAnh.setVisibility(View.VISIBLE);
        }
    }

    private void SetProduct(ProductInfo productInfo) {

        etMaSanPham.setText(productInfo.getProduct_ID());
        etMaVach.setText(productInfo.getMaVachID());
        etTenSanPham.setText(productInfo.getProduct_Name());
        etDonViTinh.setText(productInfo.getDonVitinh());
        etDongGoi.setText(AppUtils.formatNumber("N0").format(productInfo.getDonggoi()));
        etDonViTinh2.setText(productInfo.getDvt2());
        etDongGoi2.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi3()));
        etGiaNhap.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaMua()));
        etGiaBanLe.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanLe()));
        etGiaBanNo.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanDLTP()));
        etGiaBanSi.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanDLT()));
        if (!TextUtils.isEmpty(productInfo.getCategory_ID()))
            themSanPhanPresenter.GetLoaiHang(productInfo.getCategory_ID());
        for (LoaiSanPhamInfo itemLoaiSanPham : LoaiSanPhamInfo.loadLoaiSanPham()) {
            if (itemLoaiSanPham.getMaLoaiSanPham().equals(productInfo.getLoai())) {
                etLoaiSanPham.setText(itemLoaiSanPham.getTenLoaiSanPham());
                break;
            }
        }

        etDienGiai.setText(productInfo.getDescription());

    }

    private ProductInfo GetProduct() {

        if (bmHinhAnh != null) {
            productCurrent.setHinhAnh(AppUtils.formatBitMapToString(bmHinhAnh));
        }
        productCurrent.setProduct_ID(etMaSanPham.getText().toString());
        productCurrent.setMaVachID(etMaVach.getText().toString());
        productCurrent.setProduct_Name(etTenSanPham.getText().toString());
        productCurrent.setDonVitinh(etDonViTinh.getText().toString());
        productCurrent.setDvt2(etDonViTinh2.getText().toString());
        float dongGoi = 0;
        try {
            dongGoi = Float.parseFloat(etDongGoi.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        productCurrent.setDonggoi(dongGoi);
        try {
            dongGoi = Float.parseFloat(etDongGoi.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        productCurrent.setDongGoi3(dongGoi);
        productCurrent.setGiaMua(AppUtils.convertTextNumericToDouble(etGiaNhap));
        productCurrent.setGiaBanLe(AppUtils.convertTextNumericToDouble(etGiaBanLe));
        productCurrent.setGiaBanDLT(AppUtils.convertTextNumericToDouble(etGiaBanSi));
        productCurrent.setGiaBanDLTP(AppUtils.convertTextNumericToDouble(etGiaBanNo));
        productCurrent.setDescription(etDienGiai.getText().toString());
        double thueSuat = Double.parseDouble(TextUtils.isEmpty(etThueSuat.getText().toString()) ? "0" : etThueSuat.getText().toString());
        productCurrent.setThueSuat(thueSuat > 1 ? thueSuat / 100 : thueSuat);
        productCurrent.setGioihantren(Integer.parseInt(TextUtils.isEmpty(etSLToiDa.getText().toString()) ? "0" : etSLToiDa.getText().toString()));
        productCurrent.setGioihan(Integer.parseInt(TextUtils.isEmpty(etSLToiThieu.getText().toString()) ? "0" : etSLToiThieu.getText().toString()));

        SupplierInfo supplierInfo = new SupplierInfo();
        productCurrent.setSupplier_ID(supplierInfo.getSupplier_ID());

        return productCurrent;
    }

    private void AddNew() {
        isAdd = true;
        isEdit = false;
        isEdit = false;
        productCurrent = new ProductInfo();
        productCurrent.setDonggoi(1);
        productCurrent.setLoai("SP");
        SetHinhAnh(null);
        SetProduct(productCurrent);
    }

    private void Edit() {
        isAdd = false;
        isEdit = true;
        etMaSanPham.setEnabled(false);
        themSanPhanPresenter.GetProduct(this.getIntent().getExtras().getString("ProductID"));

    }

    private void Save() {
        showProgressDialog(true);
        ProductInfo productInfo = GetProduct();
        if (isAdd)
            themSanPhanPresenter.InsertProduct(productInfo);
        else
            themSanPhanPresenter.UpdateProduct(productInfo);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnMore:
                ctlMoreInfo.setVisibility(View.VISIBLE);
                ctlMore.setVisibility(View.GONE);
                break;
            case R.id.btnQuetMaVach:
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
            case R.id.btnShowPopupDonViTinh:
                etDonViTinh.showDropDown();
                break;
            case R.id.btnShowPopupDonViTinh2:
                etDonViTinh2.showDropDown();
                break;
            case R.id.etNhomSanPham:
                intent = new Intent(ThemSanPhamActivity.this, LoaiHangListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_LOAIHANG);
                break;
            case R.id.etLoaiSanPham:
                loadLoaiSanPham();
                break;
            case R.id.btnThemHinhAnh:
                ChupHoacChonHinhAnh();
                break;
            case R.id.btnXoaHinhAnh:
                SetHinhAnh(null);
                break;
            case R.id.etNhaCungCap:
                intent = new Intent(this, SupplierListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("isCombobox", true);
                intent.putExtras(bundle1);
                startActivityForResult(intent, REQUEST_NHACUNGCAP);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_MAVACH:
                    bundle = intent.getExtras();
                    String maVach = bundle.getString("Code");
                    if (!TextUtils.isEmpty(maVach)) {
                        etMaVach.setText(maVach);
                    }
                    break;
                case REQUEST_LOAIHANG:
                    bundle = intent.getExtras();
                    LoaiHangInfo loaiHangInfo = (LoaiHangInfo) bundle.getSerializable("LoaiHangInfo");
                    productCurrent.setCategory_ID(loaiHangInfo.getCategory_ID());
                    etNhomSanPham.setText(loaiHangInfo.getLoaihang1());
                    break;
                case REQUEST_GALLERY:
                    onSelectFromGalleryResult(intent);
                    break;
                case REQUEST_CAMERA:
                    ImageUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                    Bitmap bitmapHinhAnh = ImageUtils.optimizeBitmap(8, imageStoragePath, this);
                    SetHinhAnh(bitmapHinhAnh);
                    break;
                case REQUEST_NHACUNGCAP:
                    bundle = intent.getExtras();
                    SupplierInfo supplierInfo = (SupplierInfo) bundle.getSerializable("SupplierInfo");
                    if (supplierInfo == null) return;
                    productCurrent.setSupplier_ID(supplierInfo.getSupplier_ID());
                    etNhaCungCap.setText(supplierInfo.getCompany_Name());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onGetProductSuccess(ProductInfo itemResult) {
        productCurrent = itemResult;
        SetProduct(itemResult);

//        int position = -1;
//        List<ProductInfo> listProduct = adapter.getListAllData();
//        for(int i = 0; i< listProduct.size(); i++){
//            if (listProduct.get(i).getProduct_ID().equals(itemResult.getProduct_ID())){
//                position = i;
//                break;
//            }
//        }
//
//        if (position == -1){
//
//            Intent intent = new Intent(this, EditSanPhamActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            ProductInfo productInfo = new ProductInfo();
//
//            productInfo.setProduct_ID(productCurrent.getProduct_ID());
//            productInfo.setProduct_Name(productCurrent.getProduct_Name());
//            productInfo.setMaVachID(productCurrent.getMaVachID());
//            productInfo.setDonggoi(productCurrent.getDonggoi());
//            productInfo.setDonggoi2(productCurrent.getDonggoi2());
//            productInfo.setGiaMua(productCurrent.getGiaMua());
//            productInfo.setGiaBanLe(productCurrent.getGiaBanLe());
//            productInfo.setGiaBanDLT(productCurrent.getGiaBanDLT());
//            productInfo.setGiaBanDLTP(productCurrent.getGiaBanDLTP());
//            productInfo.setDonVitinh(productCurrent.getDonVitinh());
//            productInfo.setDvt2(productCurrent.getDvt2());
//            productInfo.setCategory_ID(productInfo.getCategory_ID());
//            productInfo.setSLNhieu(productCurrent.getSLNhieu());
//            productInfo.setLoai(productCurrent.getLoai());
//
//
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("ProductInfo", productInfo);
//            bundle.putSerializable("ProductInfo", (Serializable) adapter.getListAllData());
//            intent.putExtras(bundle);
//            startActivityForResult(intent, REQUEST_SANPHAM);
//        } else {
//            VattuxuatInfo vattuxuatInfo = listVattuxuat.get(position);
//            phieuXuatPresenter.KiemTraDuocXuatHang(phieuXuatCurrent.getSoCt(), phieuXuatCurrent.getMSK(), vattuxuatInfo.getProduct_ID(),
//                    phieuXuatCurrent.getNgay(), vattuxuatInfo.getSL() + 1);
//            positionHandle = position;
//        }

    }

    @Override
    public void onGetProductError(String error) {

    }

    @Override
    public void onInsertProductSuccess(ProductInfo itemResult) {
        showProgressDialog(false);
        productCurrent.setProduct_ID(itemResult.getProduct_ID());
        etMaSanPham.setText(itemResult.getProduct_ID());
        Toast.makeText(this, "Lưu sản phẩm thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ProductInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();


    }

    @Override
    public void onInsertProductError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onUpdateProductSuccess(ProductInfo itemResult) {
        showProgressDialog(false);
        productCurrent.setProduct_ID(itemResult.getProduct_ID());
//        etMaSanPham.setText(itemResult.getProduct_ID());
        Toast.makeText(this, "Lưu sản phẩm thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ProductInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onUpdateProductError(String error) {
        showProgressDialog(false);
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onGetLoaiHangSuccess(LoaiHangInfo itemResult) {
        etNhomSanPham.setText(itemResult.getLoaihang1());
    }

    @Override
    public void onGetLoaiHangError(String error) {
        Toast.makeText(this, "Lấy thông tin loại hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }



    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void hideProgressDialog() {
        showProgressDialog(false);
    }

    private void showProgressDialog(boolean isShow) {
        if (isShow) {
            progressDialog = new Dialog(this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.getWindow().setBackgroundDrawableResource(R.color.colorTransparent);
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.dialog_progressbar_waiting);
            progressDialog.show();

        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themkhachhang, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:
                Save();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ChupHoacChonHinhAnh() {
        final List<String> listMenuHinhAnh = new ArrayList<>();
        if (bmHinhAnh != null)
            listMenuHinhAnh.add("Xem lại ảnh");
        listMenuHinhAnh.add("Chụp ảnh");
        listMenuHinhAnh.add("Chọn từ album");
        final CharSequence[] items = listMenuHinhAnh.toArray(new CharSequence[listMenuHinhAnh.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(ThemSanPhamActivity.this);
        builder.setTitle("Thêm hình ảnh");
        builder.setPositiveButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (listMenuHinhAnh.get(position).equals("Chụp ảnh")) {
                    if (!checkPermissions(REQUEST_CAMERA_AND_GALLERY_PERMISSION)) return;
                    cameraIntent();
                } else if (listMenuHinhAnh.get(position).equals("Chọn từ album")) {
                    if (!checkPermissions(REQUEST_CAMERA_AND_GALLERY_PERMISSION)) return;
                    galleryIntent();
                } else if (listMenuHinhAnh.get(position).equals("Xem lại ảnh")) {
                    Intent intent = new Intent(ThemSanPhamActivity.this, PreviewPictureActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PublicVariables.BMPicture = bmHinhAnh;//((BitmapDrawable) imgAnhChuKy.getDrawable()).getBitmap();
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }

    //region Get Hinh anh khach hang
    String imageStoragePath = "";

    //Mo camera
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = ImageUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public static Uri getOutputMediaFileUri(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        //return FileProvider.getUriForFile(context, context.getPackageName() + ".share", file);
    }


    //Mo bo suu tap
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_GALLERY);
    }


    /*
     - Neu data.getClipData() != null => Chon nhieu hinh anh
     - Nguoc lai chon 1 hinh anh
     */
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        if (data == null) return;
        if (data.getClipData() != null) {
            ClipData mClipData = data.getClipData();
            for (int i = 0; i < mClipData.getItemCount(); i++) {
                ClipData.Item item = mClipData.getItemAt(i);
                InsertPictureFromGallery(item.getUri());
            }
        } else if (data.getData() != null) {
            InsertPictureFromGallery(data.getData());
        }
    }

    private void InsertPictureFromGallery(Uri uriPicture) {
        Bitmap bitmapHinhAnh = null;
        String filePath = ImageFilePath.getPath(ThemSanPhamActivity.this, uriPicture);
        File file = new File(filePath);
        bitmapHinhAnh = ImageUtils.optimizeBitmap(8, filePath, this);
        SetHinhAnh(bitmapHinhAnh);
    }
    //endregion

    //region Kiem tra quyen
    public boolean checkPermissions(int request) {
        switch (request) {
            case REQUEST_CAMERA_AND_GALLERY_PERMISSION:
                if (ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, cameraAndGalleryPermissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, cameraAndGalleryPermissionsRequired, REQUEST_CAMERA_AND_GALLERY_PERMISSION);
                    return false;
                }
                break;
//            case REQUEST_GALLERY_PERMISSION:
//                if (ActivityCompat.checkSelfPermission(this, galleryPermissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, galleryPermissionsRequired, REQUEST_GALLERY_PERMISSION);
//                    return false;
//                }
//                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Hien thi thong bao ly do tai sao phai can cap nhung quyen nay cho ung dung
     *
     * @param requestCode
     * @param isLoad
     */
    private void showMessagePermissions(int requestCode, final Boolean isLoad) {
        switch (requestCode) {
            case REQUEST_CAMERA_AND_GALLERY_PERMISSION:
                new AlertDialog.Builder(this)
                        .setTitle("QUYỀN ỨNG DỤNG")
                        .setMessage("Ứng dụng cần được cấp nhiều quyền hơn.")
                        .setPositiveButton("CẤP QUYỀN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ActivityCompat.requestPermissions(ThemSanPhamActivity.this, cameraAndGalleryPermissionsRequired, REQUEST_CAMERA_AND_GALLERY_PERMISSION);
                            }
                        })
                        .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if (isLoad)
                                    finish();
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            default:
                break;
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allgranted = false;
        //Kiem tra tat ca quyen can cap
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                allgranted = true;
            } else {
                allgranted = false;
                break;
            }
        }
        switch (requestCode) {
            case REQUEST_CAMERA_AND_GALLERY_PERMISSION:
                if (!allgranted && (ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[2]))) {
                    showMessagePermissions(REQUEST_CAMERA_AND_GALLERY_PERMISSION, false);
                } else {
                    cameraIntent();
                }
                break;
            case REQUEST_MULTIPLE_PERMISSIONS:
                if (!allgranted && (ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[1])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, cameraAndGalleryPermissionsRequired[2]))) {
                    showMessagePermissions(REQUEST_CAMERA_AND_GALLERY_PERMISSION, true);
                } else {
                    if (isEdit) {
                        //DownloadFileFromFTP();
                    }
                }
            default:
                break;
        }

    }



//    private void onBindData()
//    {
//        ProductListAdapter adapterData;
//        List<ProductInfo> list = new ArrayList<>();
//        adapterData = new ProductListAdapter(ThemSanPhamActivity.this, list);
//
//        adapterData.setOnLongClickListener((view, position) -> {
//            ArrayList<String> listLongClick = new ArrayList<>();
//            listLongClick.add("Cập nhật");
//            listLongClick.add("Xóa");
//            listLongClick.add("Xóa tất cả");
//
//            final CharSequence[] items = listLongClick.toArray(new CharSequence[listLongClick.size()]);
//            AlertDialog.Builder builder = new AlertDialog.Builder(ThemSanPhamActivity.this);
//            builder.setItems(items, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int positionItem) {
//
//                    switch (position) {
//                        case 0:
//                            ProductInfo product = adapterData.getItem(position);
//
//                            Intent intent = new Intent(ThemSanPhamActivity.this, EditSanPhamActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("ProductID", product.getProduct_ID());
//                            intent.putExtras(bundle);
//                            startActivityForResult(intent, RESULT_OK);
//                            break;
//                        case 1:
//                            final AlertDialog.Builder builderDelete = new AlertDialog.Builder(ThemSanPhamActivity.this);
//                            builderDelete.setTitle("XÓA SẢN PHẨM")
//                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
//                                    .setCancelable(false)
//                                    .setPositiveButton(ThemSanPhamActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//                                            adapterData.removeItem(position);
//                                            dialog.cancel();
//                                        }
//                                    })
//                                    .setNegativeButton(ThemSanPhamActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//                                            dialog.cancel();
//                                        }
//                                    });
//                            final AlertDialog alert = builderDelete.create();
//                            alert.show();
//                            break;
//                        case 2:
//                            final AlertDialog.Builder builderDeleteAll = new AlertDialog.Builder(ThemSanPhamActivity.this);
//                            builderDeleteAll.setTitle("XÓA SẢN PHẨM")
//                                    .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
//                                    .setCancelable(false)
//                                    .setPositiveButton(ThemSanPhamActivity.this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
//                                        public void onClick(final DialogInterface dialog, final int id) {
//
//                                            adapterData.setData(new ArrayList<>());
//                                            dialog.cancel();
//                                        }
//                                    })
//                                    .setNegativeButton(ThemSanPhamActivity.this.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
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
//    }


    //endregion
}
