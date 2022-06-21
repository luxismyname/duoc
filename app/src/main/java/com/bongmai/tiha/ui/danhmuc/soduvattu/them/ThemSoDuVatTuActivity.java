package com.bongmai.tiha.ui.danhmuc.soduvattu.them;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.kho.list.KhoListActivity;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.NetworkUtils;
import com.bongmai.tiha.utils.NumberTextWatcher;
import com.bongmai.tiha.utils.PublicVariables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThemSoDuVatTuActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener, ThemSoDuVatTuActivityContract.View{
    Toolbar toolbar;

    AutoCompleteTextView
            etTimKiem;

    SoDuVatTuDauInfo soDuVatTuCurent;

    ArrayList<String> listDonViTinh;

    DateDialogAdapter adapterDateDialog;

    SoDuVatTuAdapter adapterData;

    String soDuVatTuDauID;

    ProductInfo productInfo;

    ThemSoDuVatTuActivityPresenter presenter;

    List<ProductInfo> listProduct;
    private static final int REQUEST_SANPHAM = 1;
    private static final int REQUEST_KHO = 2;
    Spinner etDVT;
    boolean isAdd = false, isEdit = false;
    ConstraintLayout cltID;
    EditText
            etID,
            etKho,
            etNgay,
            etSanPham,
            etLoaiHang,
            etDonggoi, etDonggoi3, etDonggoi4,
            etSLThuc,
            etDonGiaThuc,
            etThanhTien;
//    etDG, etSL,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_so_du_vat_tu);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = findViewById(R.id.toolbar);
        cltID = findViewById(R.id.ctlID);
        etID = findViewById(R.id.etID);
        etTimKiem = findViewById(R.id.etTimKiem);
        etKho = findViewById(R.id.etKho);
        etNgay = findViewById(R.id.etNgay);
        etSanPham = findViewById(R.id.etSanPham);
        etLoaiHang = findViewById(R.id.etLoaiHang);
        etDVT = findViewById(R.id.etDVT);
        etDonggoi = findViewById(R.id.etDongGoi);
        etDonggoi3 = findViewById(R.id.etDongGoi3);
        etDonggoi4 = findViewById(R.id.etDonggoi4);
        etSLThuc = findViewById(R.id.etSoluongThuc);
        etDonGiaThuc = findViewById(R.id.etDongiaThuc);
        etThanhTien = findViewById(R.id.etThanhTien);

        etKho.setOnClickListener(this);
        etSanPham.setOnClickListener(this);
        etNgay.setOnClickListener(this);

        etSLThuc.addTextChangedListener(new NumberTextWatcher(etSLThuc, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double soLuongThuc = 0;
                try {
                    soLuongThuc = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }

                soDuVatTuCurent.setSoLuongThuc(soLuongThuc);
//                etSL.setText(AppUtils.formatNumber("N0").format(soLuongThuc));


                productInfo = new ProductInfo();
                if(etDVT.getSelectedItemPosition() == 0) {
                    float donggoi = Float.parseFloat(etDonggoi.getText().toString());
                    soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                } else if(etDVT.getSelectedItemPosition() == 1){
                    float donggoi = Float.parseFloat(etDonggoi.getText().toString());
                    soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                } else if(etDVT.getSelectedItemPosition() == 2){
                    float donggoi3 = Float.parseFloat(etDonggoi3.getText().toString());
                    soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi3);
                } else if(etDVT.getSelectedItemPosition() == 3){
                    float donggoi4 = Float.parseFloat(etDonggoi4.getText().toString());
                    soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi4);
                }
                soluong2();
                if(!TextUtils.isEmpty(etDonGiaThuc.getText().toString())){
                    soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                    etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                }

            }
        }));

//        etSL.addTextChangedListener(new NumberTextWatcher(etSL, new NumberTextWatcher.TextChangedListener() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                double soluong = 0;
//                try {
//                    soluong = Double.parseDouble(s.toString().replace(".", ""));
//                } catch (NumberFormatException e) {
//                }
//                soDuVatTuCurent.setSODUDAU(soluong);
//                if(!TextUtils.isEmpty(etDG.getText().toString())){
//                    double dongia = soDuVatTuCurent.getDONGIA();
//                    double thanhtien = dongia * soluong;
//                    etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
//                }
//
//            }
//        }));

        etDonggoi.addTextChangedListener(new NumberTextWatcher(etDonggoi, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double donggoi = 0;
                try {
                    donggoi = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }
            }
        }));

        etDonGiaThuc.addTextChangedListener(new NumberTextWatcher(etDonGiaThuc, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double donGiaThuc = 0;
                try {
                    donGiaThuc = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                }
                soDuVatTuCurent.setDonGiaThuc(donGiaThuc);
                if(etDVT.getSelectedItemPosition() == 0) {
                    float donggoi = Float.parseFloat(etDonggoi.getText().toString());
                    soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                    soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                    etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                } else if(etDVT.getSelectedItemPosition() == 1){
                    float donggoi = Float.parseFloat(etDonggoi.getText().toString());
                    soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                    soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                    etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                } else if(etDVT.getSelectedItemPosition() == 2){
                    float donggoi3 = Float.parseFloat(etDonggoi3.getText().toString());
                    soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi3);
                    soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                    etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                } else if(etDVT.getSelectedItemPosition() == 3){
                    float donggoi4 = Float.parseFloat(etDonggoi4.getText().toString());
                    soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi4);
                    soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                    etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                }
//                productInfo = new ProductInfo();
//                float donggoi = Float.parseFloat(etDonggoi.getText().toString());
//                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
//                etDG.setText(AppUtils.formatNumber("N0").format(donGiaThuc));
                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getDONGIA() * soDuVatTuCurent.getSODUDAU());
                etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
                soluong2();

            }
        }));

//        etDG.addTextChangedListener(new NumberTextWatcher(etDG, new NumberTextWatcher.TextChangedListener() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                double dongia = 0;
//                try {
//                    dongia = Double.parseDouble(s.toString().replace(".", ""));
//                } catch (NumberFormatException e) {
//                }
//                soDuVatTuCurent.setDONGIA(dongia);
//                double soluong = soDuVatTuCurent.getSODUDAU();
//                double thanhtien = soluong * dongia;
//                etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
//
//            }
//        }));

        etThanhTien.addTextChangedListener(new NumberTextWatcher(etThanhTien, new NumberTextWatcher.TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double thanhtien = 0;
                try {
                    thanhtien = Double.parseDouble(s.toString().replace(".", ""));
                } catch (NumberFormatException e) {
                    soDuVatTuCurent.setThanhTien(thanhtien);
                }
            }
        }));

    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        String tieuDeForm = getResources().getString(R.string.soduvattu_them_title);
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

        presenter = new ThemSoDuVatTuActivityPresenter(this);
        productInfo = new ProductInfo();

        listProduct = new ArrayList<>();
        adapterData = new SoDuVatTuAdapter(this, listProduct);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
//            soDuVatTuDauID =  bundle.getString("soDuVatTuDauID");
            SoDuVatTuDauInfo soDuVatTuDauInfo = (SoDuVatTuDauInfo) bundle.getSerializable("SoDuVatTuDauInfo");
            soDuVatTuDauID = soDuVatTuDauInfo == null ? "" : String.valueOf(soDuVatTuDauInfo.getID());

        }

        if (soDuVatTuDauID == null || soDuVatTuDauID.isEmpty())//Edit
        {
            AddNew();
        } else//Add new
        {
            Edit();
        }
    }

    private void AddNew() {
        isAdd = true;
        isEdit = false;
        soDuVatTuCurent = new SoDuVatTuDauInfo();
        SetSoDuVatTu(soDuVatTuCurent);
    }

    private void Edit() {
        isAdd = false;
        isEdit = true;
        String tieuDeForm = "Cập nhật số dư vật tư đầu";
        CommonUtils.configToolbar(this, getSupportActionBar(), tieuDeForm, 0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        presenter = new ThemSoDuVatTuActivityPresenter(this);
//        presenter.GetSoDuVatTuDau(this.getIntent().getExtras().getString("soDuVatTuDauID"));
        presenter.GetSoDuVatTuDau(soDuVatTuDauID);

    }

    private void Save() {
        SoDuVatTuDauInfo sodusave = GetSoduvattu();
        if (isAdd)
            presenter.InsertSoDuVatTuDau(sodusave);
        else
            presenter.UpdateSoDuVatTuDau(sodusave);
    }

    private void SetSoDuVatTu(SoDuVatTuDauInfo soDuVatTuDauInfo){

        etID.setText(String.valueOf(soDuVatTuDauInfo.getID()));
        etKho.setText(soDuVatTuDauInfo.getTenKho());
        if(isAdd){
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            etNgay.setText(dateFormat.format(cal.getTime()));
        }
        if(isEdit) {
            etNgay.setText(AppUtils.formatDateToDateRequest(soDuVatTuDauInfo.getNgay(), "MM/dd/yyyy"));
        }

        double donggoi = Double.parseDouble(TextUtils.isEmpty(etDonggoi.getText().toString()) ? "0" : etDonggoi.getText().toString());
        etDonggoi.setText(AppUtils.formatNumber("N0").format(donggoi));
        etSanPham.setText(soDuVatTuDauInfo.getProductName());
        presenter.GetProduct(soDuVatTuCurent.getProduct_id());
        double slthuc = Double.parseDouble(TextUtils.isEmpty(etSLThuc.getText().toString()) ? "0" : etSLThuc.getText().toString());
        double dgthuc = Double.parseDouble(TextUtils.isEmpty(etDonGiaThuc.getText().toString()) ? "0" : etDonGiaThuc.getText().toString());
        double tt = Double.parseDouble(TextUtils.isEmpty(etThanhTien.getText().toString()) ? "0" : etThanhTien.getText().toString());
        etSLThuc.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getSoLuongThuc()));
        etDonGiaThuc.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getDonGiaThuc()));
        etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuDauInfo.getThanhTien()));

    }

    private SoDuVatTuDauInfo GetSoduvattu(){
        soDuVatTuCurent.setID(Integer.parseInt(etID.getText().toString()));
        soDuVatTuCurent.setTenKho(etKho.getText().toString());
        soDuVatTuCurent.setNgay(etNgay.getText().toString());

        soDuVatTuCurent.setProduct_Name(etSanPham.getText().toString());
        soDuVatTuCurent.setLO("");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        soDuVatTuCurent.setNgaygio(currentDateandTime);
        soDuVatTuCurent.setModifiedDate(currentDateandTime);
        soDuVatTuCurent.setModifiedBy(PublicVariables.nguoiDungInfo.getUserName());
        soDuVatTuCurent.setTienThue(0);
        soDuVatTuCurent.setDonGiaNguyen(0);
        soDuVatTuCurent.setProduct_Name(soDuVatTuCurent.getProduct_Name());
        soluong2();
        productInfo = new ProductInfo();
        double donggoi = 0;
        try {
            donggoi = Double.parseDouble(etDonggoi.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        productInfo.setDonggoi(donggoi);

        soDuVatTuCurent.setSize("");
        soDuVatTuCurent.setHanSuDung(currentDateandTime);
        soDuVatTuCurent.setTaiKhoan(PublicVariables.nguoiDungInfo.getUserName());
        soDuVatTuCurent.setSLDVT2(0);
        soDuVatTuCurent.setSLDVT3(0);
        soDuVatTuCurent.setSLNguyen(0);
        soDuVatTuCurent.setNguoigo(PublicVariables.nguoiDungInfo.getUserName());

        if(etDVT.getSelectedItemPosition() < 0){
            Toast.makeText(getApplicationContext(), "Chưa chọn ĐVT", Toast.LENGTH_SHORT).cancel();
        } else if (etDVT.getSelectedItemPosition() >= 0){
            String textDVT = etDVT.getSelectedItem().toString();
            soDuVatTuCurent.setDVT(textDVT);
        }
        double slthuc = 0;
        try {
            slthuc = Double.parseDouble(etSLThuc.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        soDuVatTuCurent.setSoLuongThuc(slthuc);


        double dongiathuc = 0;
        try {
            dongiathuc = Double.parseDouble(etDonGiaThuc.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        soDuVatTuCurent.setDonGiaThuc(dongiathuc);

        double thanhtien = 0;
        try {
            thanhtien = Double.parseDouble(etThanhTien.getText().toString().replace(".", ""));
        } catch (NumberFormatException e) {
        }
        soDuVatTuCurent.setThanhTien(thanhtien);

        return soDuVatTuCurent;
    }


    private void LoadDonViTinh() {
        listDonViTinh = new ArrayList<>();
        if (productInfo.getDonVitinh() != null &&  !productInfo.getDonVitinh().isEmpty() && productInfo.getDonvitinhDongia().equals(productInfo.getDonVitinh().toUpperCase().trim())) {
            listDonViTinh.add(productInfo.getDonVitinh());

        }
        if (productInfo.getDvt2() != null && !productInfo.getDvt2().isEmpty() && productInfo.getDonvitinhDongia().equals(productInfo.getDvt2().toUpperCase().trim())) {
            listDonViTinh.add(productInfo.getDvt2());

        }
        if (productInfo.getDvt3() != null && !productInfo.getDvt3().isEmpty() && productInfo.getDonvitinhDongia().equals(productInfo.getDvt3().toUpperCase().trim())) {
            listDonViTinh.add(productInfo.getDvt3());

        }
        if (productInfo.getDVT4() != null && !productInfo.getDVT4().isEmpty() && productInfo.getDonvitinhDongia().equals(productInfo.getDVT4().toUpperCase().trim())) {
            listDonViTinh.add(productInfo.getDVT4());

        }
        ArrayAdapter<String> adapterDVT = new ArrayAdapter<>(this, R.layout.item_spinner, listDonViTinh);
        etDVT.setAdapter(adapterDVT);
        soluong2();
    }


   private void  soluong2(){

        for(ProductInfo prd : adapterData.getListAllData()){
            soDuVatTuCurent.setProduct_id(prd.getProduct_ID());
            float donggoi = Float.parseFloat(etDonggoi.getText().toString());
            float donggoi3 = Float.parseFloat(etDonggoi3.getText().toString());
            float donggoi4 = Float.parseFloat(etDonggoi4.getText().toString());
            etDVT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            if(listDonViTinh.get(position).equals(prd.getDonVitinh())){
                                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                            }
                                etDVT.setSelection(position);

                            break;
                        case 1:
                            if(listDonViTinh.get(position).equals(prd.getDvt2())){
                                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                            }
                                etDVT.setSelection(position);

                            break;
                        case 2:
                            if(listDonViTinh.get(position).equals(prd.getDvt3())){
                                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi3);
                                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi3);
                                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                            }
                                etDVT.setSelection(position);

                            break;
                        case 3:
                            if(listDonViTinh.get(position).equals(prd.getDVT4())){
                                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi4);
                                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi4);
                                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                            }
                                etDVT.setSelection(position);

                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

   }



    private void setsldongia(){

//        etSL.setText("0");
        etSLThuc.setText("0");
//        etDG.setText("0");
        etDonGiaThuc.setText("0");
        etThanhTien.setText("0");

    }

    @Override
    public void onGetSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {
        this.soDuVatTuCurent = itemResult;
        SetSoDuVatTu(itemResult);
        soluong2();


    }

    @Override
    public void onGetSoDuVatTuDauError(String error) {
        error = error.isEmpty() ? this.getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(this)) {
            error = this.getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getResources().getString(R.string.title_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(this.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onInsertSoDuVatTuSuccess(SoDuVatTuDauInfo itemResult) {
        Toast.makeText(this, "Lưu số dư thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SoDuVatTuDauInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onInsertSoDuVatTuError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onUpdateSoDuVatTuDauSuccess(SoDuVatTuDauInfo itemResult) {

        Toast.makeText(this, "Cập nhật số dư thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SoDuVatTuDauInfo", itemResult);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onUpdateSoDuVatTuDauError(String error) {
        CommonUtils.showMessageError(this, error);
    }

    @Override
    public void onGetProductSuccess(ProductInfo itemResult) {
            productInfo = itemResult;
            itemResult.setProduct_ID(soDuVatTuCurent.getProduct_id());
            etLoaiHang.setText(itemResult.getCategory_ID());
            itemResult.setDonggoi(productInfo.getDonggoi());
            etDonggoi.setText(AppUtils.formatNumber("N0").format(productInfo.getDonggoi()));
            itemResult.setDongGoi3(productInfo.getDongGoi3());
            etDonggoi3.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi3()));
            itemResult.setDongGoi4(productInfo.getDongGoi4());
            etDonggoi4.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi4()));
            itemResult.setDonvitinhDongia(productInfo.getDonvitinhDongia());
            LoadDonViTinh();
            float donggoi = Float.parseFloat(etDonggoi.getText().toString());
            float donggoi3 = Float.parseFloat(etDonggoi3.getText().toString());
            float donggoi4 = Float.parseFloat(etDonggoi4.getText().toString());

            if(soDuVatTuCurent.getDVT().equals(productInfo.getDonVitinh())){
                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
            } else if(soDuVatTuCurent.getDVT().equals(productInfo.getDvt2())){
                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi);
                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi);
                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
            } else if(soDuVatTuCurent.getDVT().equals(productInfo.getDvt3())){
            soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi3);
            soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi3);
            soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
            } else if(soDuVatTuCurent.getDVT().equals(productInfo.getDVT4())){
                soDuVatTuCurent.setSODUDAU(soDuVatTuCurent.getSoLuongThuc() * donggoi4);
                soDuVatTuCurent.setDONGIA(soDuVatTuCurent.getDonGiaThuc() / donggoi4);
                soDuVatTuCurent.setThanhTien(soDuVatTuCurent.getSODUDAU() * soDuVatTuCurent.getDONGIA());
                etThanhTien.setText(AppUtils.formatNumber("N0").format(soDuVatTuCurent.getThanhTien()));
            }

        etDVT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).toString();
                float soluong = (float) 0.0000000001, dongia = (float) 0.0000000001, thanhtien = (float) 0.0000000001;
                switch (position) {

                    case 0:
                        if (listDonViTinh.get(position).equals(itemResult.getDonVitinh()) && itemResult.getDonggoi() > 0 ) {
                            soluong = (float) (soDuVatTuCurent.getSoLuongThuc() * itemResult.getDonggoi());
                            soDuVatTuCurent.setSODUDAU(soluong);

//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) (soDuVatTuCurent.getDonGiaThuc() / itemResult.getDonggoi());
                            soDuVatTuCurent.setDONGIA(dongia);
//                                etDG.setText(AppUtils.formatNumber("N0").format(dongia));
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        } else if(listDonViTinh.get(position).equals(itemResult.getDonVitinh())&& itemResult.getDonggoi() == 0){
                            soluong = (float) soDuVatTuCurent.getSoLuongThuc();
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) soDuVatTuCurent.getDonGiaThuc();
                            soDuVatTuCurent.setDONGIA(dongia);
//                                etDG.setText(AppUtils.formatNumber("N0").format(dongia));
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        }
                        etDVT.setSelection(position);
                        break;
                    case 1:
                        if (listDonViTinh.get(position).equals(itemResult.getDvt2()) && itemResult.getDonggoi() > 0) {
                            soluong = (float) (soDuVatTuCurent.getSoLuongThuc() * itemResult.getDonggoi());
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) (soDuVatTuCurent.getDonGiaThuc()/ itemResult.getDonggoi());
                            soDuVatTuCurent.setDONGIA(dongia);
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        } else if(listDonViTinh.get(position).equals(itemResult.getDvt2()) && itemResult.getDonggoi() == 0){
                            soluong = (float) soDuVatTuCurent.getSoLuongThuc();
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) soDuVatTuCurent.getDonGiaThuc();
                            soDuVatTuCurent.setDONGIA(dongia);
//                                etDG.setText(AppUtils.formatNumber("N0").format(dongia));
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        }
                        etDVT.setSelection(position);
                        break;
                    case 2:
                        if (listDonViTinh.get(position).equals(itemResult.getDvt3()) && itemResult.getDongGoi3() > 0) {
                            soluong = (float) (soDuVatTuCurent.getSoLuongThuc() * itemResult.getDongGoi3());
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) (soDuVatTuCurent.getDonGiaThuc()/ itemResult.getDongGoi3());
                            soDuVatTuCurent.setDONGIA(dongia);
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        } else if(listDonViTinh.get(position).equals(itemResult.getDvt3()) && itemResult.getDongGoi3() == 0){
                            soluong = (float) soDuVatTuCurent.getSoLuongThuc();
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) soDuVatTuCurent.getDonGiaThuc();
                            soDuVatTuCurent.setDONGIA(dongia);
//                                etDG.setText(AppUtils.formatNumber("N0").format(dongia));
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        }
                        etDVT.setSelection(position);
                        break;
                    case 3:
                        if (listDonViTinh.get(position).equals(itemResult.getDVT4()) && itemResult.getDongGoi4() > 0) {
                            soluong = (float) (soDuVatTuCurent.getSoLuongThuc() * itemResult.getDongGoi4());
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) (soDuVatTuCurent.getDonGiaThuc() / itemResult.getDongGoi4());
                            soDuVatTuCurent.setDONGIA(dongia);
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        } else if(listDonViTinh.get(position).equals(itemResult.getDVT4()) && itemResult.getDongGoi4() == 0){
                            soluong = (float) soDuVatTuCurent.getSoLuongThuc();
                            soDuVatTuCurent.setSODUDAU(soluong);
//                                etSL.setText(AppUtils.formatNumber("N0").format(soluong));
                            dongia = (float) soDuVatTuCurent.getDonGiaThuc();
                            soDuVatTuCurent.setDONGIA(dongia);
//                                etDG.setText(AppUtils.formatNumber("N0").format(dongia));
                            thanhtien = soluong * dongia;
                            etThanhTien.setText(AppUtils.formatNumber("N0").format(thanhtien));
                        }
                        etDVT.setSelection(position);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @Override
    public void onGetProductError(String error) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themkhachhang, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luu:

                Save();
                break;
            case R.id.action_edit:
                Edit();

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle bundle;
        switch (v.getId()){
            case R.id.etKho:
                intent = new Intent(this, KhoListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.getSerializable("KhoInfo");
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_KHO);
                break;
            case R.id.etSanPham:
                intent = new Intent(this, ProductListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.getSerializable("ProductInfo");
                bundle.putBoolean("isCombobox", true);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_SANPHAM);
                setsldongia();
                break;
            case R.id.etNgay:
                try {
                    adapterDateDialog = new DateDialogAdapter(v, etNgay.getText().toString());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Bundle bundle;
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_KHO:
                    bundle = intent.getExtras();
                    KhoInfo khoInfo = (KhoInfo) bundle.getSerializable("KhoInfo");
                    soDuVatTuCurent.setTenKho(khoInfo.getTenkho());
                    soDuVatTuCurent.setMSk(khoInfo.getMSK());
                    etKho.setText(khoInfo.getTenkho());

                    break;
                case REQUEST_SANPHAM:
                    bundle = intent.getExtras();
                    ProductInfo productInfo = (ProductInfo) bundle.getSerializable("ProductInfo");
                    etSanPham.setText(productInfo.getProduct_Name());
                    if(soDuVatTuCurent == null) return;
                    soDuVatTuCurent.setProductName(productInfo.getProduct_Name());
                    etSanPham.setText(productInfo.getProduct_Name());
                    etLoaiHang.setText(productInfo.getCategory_ID());
                    etDonggoi.setText(AppUtils.formatNumber("N0").format(productInfo.getDonggoi()));
                    etDonggoi3.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi3()));
                    etDonggoi4.setText(AppUtils.formatNumber("N0").format(productInfo.getDongGoi4()));
                    int position = -1;
                    listProduct = new ArrayList<>();
                    adapterData =  new SoDuVatTuAdapter(this, listProduct);
                    List<ProductInfo> listProduct = adapterData.getListAllData();
                    for (int i = 0; i < listProduct.size(); i++) {
                        if (listProduct.get(i).getProduct_ID().equals(productInfo.getProduct_ID())) {
                            position = i;
                            break;
                        }
                    }
                    if (position == -1) {
                        adapterData.addItem(productInfo);
                    } else {
                        adapterData.removeItem(position);
                        adapterData.addItem(position, productInfo);
                    }

                    listDonViTinh = new ArrayList<>();
                    if (productInfo.getDonVitinh() != null && !productInfo.getDonVitinh().isEmpty()) {
                        listDonViTinh.add(productInfo.getDonVitinh());
                    }
                    if (productInfo.getDvt2() != null && !productInfo.getDvt2().isEmpty()) {
                        listDonViTinh.add(productInfo.getDvt2());
                    }
                    if (productInfo.getDvt3() != null && !productInfo.getDvt3().isEmpty()) {
                        listDonViTinh.add(productInfo.getDvt3());
                    }
                    if (productInfo.getDVT4() != null && !productInfo.getDVT4().isEmpty()) {
                        listDonViTinh.add(productInfo.getDVT4());
                    }
                    ArrayAdapter<String> adapterDVT = new ArrayAdapter<>(this, R.layout.item_spinner, listDonViTinh);
                    etDVT.setAdapter(adapterDVT);
                    etDVT.setSelection(position);


                    break;
            }
        }
    }
}