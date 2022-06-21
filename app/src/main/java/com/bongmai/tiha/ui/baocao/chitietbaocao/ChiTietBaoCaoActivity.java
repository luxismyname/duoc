package com.bongmai.tiha.ui.baocao.chitietbaocao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.MobileDetailMauInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.banhang.DoanhSoBanHangTheoChiNhanhActivity;
import com.bongmai.tiha.ui.baocao.banhang.DoanhSoBanHangTheoKhachHangActivity;
import com.bongmai.tiha.ui.baocao.banhang.DoanhSoBanHangTheoNhanVienActivity;
import com.bongmai.tiha.ui.baocao.banhang.NhatKyBanHangActivity;
import com.bongmai.tiha.ui.baocao.banhang.NhatKyBanHangChiTietActivity;
import com.bongmai.tiha.ui.baocao.congno.CongNoTongHopActivity;
import com.bongmai.tiha.ui.baocao.congno.CongNoTongHopPhaiThuActivity;
import com.bongmai.tiha.ui.baocao.congno.CongNoTongHopPhaiTraActivity;
import com.bongmai.tiha.ui.baocao.cuocgoi.ThongKeCuocGoiActivity;
import com.bongmai.tiha.ui.baocao.tonkho.NhatKyNhapHangActivity;
import com.bongmai.tiha.ui.baocao.tonkho.NhatKyNhapHangChiTietActivity;
import com.bongmai.tiha.ui.baocao.tonkho.ThongKeTonNuocActivity;
import com.bongmai.tiha.ui.baocao.tonkho.ThongKeTonNuocVoRongActivity;
import com.bongmai.tiha.ui.baocao.tonkho.ThongKeTonVoRongActivity;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked.DanhSachKhoCheckedActivity;
import com.bongmai.tiha.ui.danhmuc.loaihang.danhsachchecked.DanhSachLoaiHangCheckedActivity;
import com.bongmai.tiha.utils.PublicVariables;


import java.util.ArrayList;
import java.util.List;


public class ChiTietBaoCaoActivity extends AppCompatActivity implements BaseActivity, ChiTietBaoCaoContract.View, View.OnClickListener {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    BaseService service;
    EditText
            etTuNgay,
            etDenNgay,
            etKho,
            etNhanVien,
            etLoaiHang;
    ChiTietBaoCaoAdapter adapterData;
    List<MobileDetailMauInfo> listData;
    String maNhomBaoCao = "";
    DieuKienLocInfo dieuKienLoc;
    DateDialogAdapter adapterDateDialog;
    private static final int FILTER_KHO_REQUESTCODE = 0;
    int positionKyBaoCao = DateReportInfo.HomNay;
    String danhSachKhoStr = "", danhSachLoaiHangStr;
    private static final int FILTER_LOAIHANG_REQUESTCODE = 1;
    ChiTietBaoCaoPresenter chiTietBaoCaoPresenter;
    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(this);
        setContentView(R.layout.activity_chitietmaubaocao);
        onInit();
        onConfigToolbar();
        onLoadData();
    }

    @Override
    public void onInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvData = (RecyclerView) findViewById(R.id.rvData);

        etTuNgay = (EditText) findViewById(R.id.etTuNgay);
        etDenNgay = (EditText) findViewById(R.id.etDenNgay);
        etKho = (EditText) findViewById(R.id.etKho);
        etNhanVien = (EditText) findViewById(R.id.etNhanVien);
        etLoaiHang = (EditText) findViewById(R.id.etLoaiHang);

        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());


        etTuNgay.setOnClickListener(this);
        etDenNgay.setOnClickListener(this);
        etKho.setOnClickListener(this);
        etNhanVien.setOnClickListener(this);
        etLoaiHang.setOnClickListener(this);

        listData = new ArrayList<>();
        onBindData();
    }

    @Override
    public void onConfigToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        String tieuDeForm = getResources().getString(R.string.chitietbaocao_tieudeform);
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
        chiTietBaoCaoPresenter = new ChiTietBaoCaoPresenter(this);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            dieuKienLoc = new DieuKienLocInfo();
            positionKyBaoCao = DateReportInfo.HomNay;
            DateReportInfo dateReport = DateReportInfo.GetDateReport(positionKyBaoCao);
            dieuKienLoc.setTuNgay(dateReport.StartDate);
            dieuKienLoc.setDenNgay(dateReport.EndDate);

            etTuNgay.setText(dieuKienLoc.getTuNgay());
            etDenNgay.setText(dieuKienLoc.getDenNgay());

            dieuKienLoc.setTuNgayTuChon(positionKyBaoCao == DateReportInfo.LuaChonKhac ? dateReport.StartDate : null);
            dieuKienLoc.setDenNgayTuChon(positionKyBaoCao == DateReportInfo.LuaChonKhac ? dateReport.EndDate : null);
            dieuKienLoc.setThoiGianXemBaoCao(dateReport.Name);
            //Lay du lieu tu ham CheckLoginTask
            dieuKienLoc.setListChiNhanh(PublicVariables.listChiNhanhByUserStr);

            maNhomBaoCao = bundle.getString("MaNhomBaoCao");

            loadChiTietBaoCao();
        }
    }

    private void loadChiTietBaoCao() {
        chiTietBaoCaoPresenter.GetListNhomBaoCaoChiTiet(maNhomBaoCao);
    }

    private void onBindData() {
        adapterData = new ChiTietBaoCaoAdapter(ChiTietBaoCaoActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle;
                Intent intent;
                MobileDetailMauInfo itemDetailBaoCao = adapterData.getItem(position);
                DateReportInfo dateReport = new DateReportInfo();

//                if (!etTuNgay.getText().toString().equals(etDenNgay.getText().toString()))
//                    positionKyBaoCao = DateReportInfo.LuaChonKhac;
//                else
//                    positionKyBaoCao = DateReportInfo.HomNay;

                if (etTuNgay.getText().toString().equals(etDenNgay.getText().toString()) && etTuNgay.getText().toString().equals(PublicVariables.NgayLamViec))
                    positionKyBaoCao = DateReportInfo.HomNay;
                else
                    positionKyBaoCao = DateReportInfo.LuaChonKhac;

                if (positionKyBaoCao == DateReportInfo.LuaChonKhac) {
                    dateReport.StartDate = etTuNgay.getText().toString();
                    dateReport.EndDate = etDenNgay.getText().toString();
                    dateReport.Name = dateReport.StartDate + " - " + dateReport.EndDate;
                } else {
                    dateReport = DateReportInfo.GetDateReport(positionKyBaoCao);
                }

                dieuKienLoc.setMaMauBaoCao(itemDetailBaoCao.getMaDetailMau());
                dieuKienLoc.setTenMauBaoCao(itemDetailBaoCao.getTenDetailMau());
                dieuKienLoc.setTrangThai(itemDetailBaoCao.getThuTu());

                dieuKienLoc.setTuNgay(dateReport.StartDate);
                dieuKienLoc.setDenNgay(dateReport.EndDate);
                dieuKienLoc.setTuNgayTuChon(positionKyBaoCao == DateReportInfo.LuaChonKhac ? dateReport.StartDate : null);
                dieuKienLoc.setDenNgayTuChon(positionKyBaoCao == DateReportInfo.LuaChonKhac ? dateReport.EndDate : null);
                dieuKienLoc.setThoiGianXemBaoCao(dateReport.Name);
                //Lay du lieu tu ham CheckLoginTask
                dieuKienLoc.setListChiNhanh(PublicVariables.nguoiDungInfo.getListChiNhanhStr());
                dieuKienLoc.setListKho(danhSachKhoStr);
                dieuKienLoc.setListLoaiHang(danhSachLoaiHangStr);

                bundle = new Bundle();
                bundle.putSerializable("DieuKienLoc", dieuKienLoc);

                switch (itemDetailBaoCao.getMaDetailMau()) {
                    //region Bao cao ban hang
                    case "THONGKEBANHANG":
                        intent = new Intent(ChiTietBaoCaoActivity.this, NhatKyBanHangActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CHITIETXUAT":
                        intent = new Intent(ChiTietBaoCaoActivity.this, NhatKyBanHangChiTietActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "DOANHSOTHEONHANVIEN":
                        intent = new Intent(ChiTietBaoCaoActivity.this, DoanhSoBanHangTheoNhanVienActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "DOANHSOTHEONHANVIENGIAO":
                        intent = new Intent(ChiTietBaoCaoActivity.this, DoanhSoBanHangTheoNhanVienActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "DOANHSOTHEOKHACHHANG":
                        intent = new Intent(ChiTietBaoCaoActivity.this, DoanhSoBanHangTheoKhachHangActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "DOANHSOTHEOCHINHANH":
                        intent = new Intent(ChiTietBaoCaoActivity.this, DoanhSoBanHangTheoChiNhanhActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    //endregion
                    //region Bao cao cong no
                    case "CONGNOTONGHOP":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CONGNOTONGHOPCK":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopActivity.class);
                        bundle.putBoolean("isCongNoCuoiKy", true);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CONGNOTONGHOPPHAITHU":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopPhaiThuActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CONGNOTONGHOPPHAITHUCK":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopPhaiThuActivity.class);
                        bundle.putBoolean("isCongNoCuoiKy", true);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CONGNOTONGHOPPHAITRA":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopPhaiTraActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CONGNOTONGHOPPHAITRACK":
                        intent = new Intent(ChiTietBaoCaoActivity.this, CongNoTongHopPhaiTraActivity.class);
                        bundle.putBoolean("isCongNoCuoiKy", true);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    //endregion
                    //region Bao cao cuoc goi
                    case "TATCACUOCGOI":

                    case "CUOCGOICHUAGIAO":

                    case "CUOCGOICHUATHANHTOAN":

                    case "CUOCGOICHUAGIAOCHUATT":

                    case "CUOCGOIDABAN":

                    case "CUOCGOICHUABAN":
                        ViewDanhSachCuocGoi(bundle);
                        break;
                    //endregion
                    //region Bao cao tien
                    case "CHITIETCHI":
                        break;
                    case "CHITIETTHU":
                        break;
                    case "BAOCAOQUYTIENMAT":
                        break;
                    //endregion
                    //region Bao cao nhap hang
                    case "THONGKENHAPHANG":
                        intent = new Intent(ChiTietBaoCaoActivity.this, NhatKyNhapHangActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "CHITIETNHAP":
                        intent = new Intent(ChiTietBaoCaoActivity.this, NhatKyNhapHangChiTietActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    //endregion
                    //region Bao cao ton kho
                    case "THONGKETONNUOC":
                        intent = new Intent(ChiTietBaoCaoActivity.this, ThongKeTonNuocActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "THONGKETONVO":
                        intent = new Intent(ChiTietBaoCaoActivity.this, ThongKeTonVoRongActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "THONGKETONNUOCTONVO":
                        intent = new Intent(ChiTietBaoCaoActivity.this, ThongKeTonNuocVoRongActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "THEKHO":
                        break;
                    case "XEMHANGTON":
                        break;
                    case "HANGTONSOLUONGAM":
                        break;
                    //endregion
                    default:
                        break;
                }
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    @Override
    public void onGetListNhomBaoCaoChiTietSuccess(List<MobileDetailMauInfo> listResult) {
        listData = listResult;
        onBindData();
    }

    @Override
    public void onGetListNhomBaoCaoChiTietError(String error) {
        listData = new ArrayList<>();
        onBindData();
        Toast.makeText(ChiTietBaoCaoActivity.this, "Lấy danh sách phiếu xuất thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }


    private void ViewDanhSachCuocGoi(Bundle bundle) {
        Intent intent = new Intent(this, ThongKeCuocGoiActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        Bundle bundle;
        switch (view.getId()) {
            case R.id.etTuNgay:
                try {
                    adapterDateDialog = new DateDialogAdapter(view, etTuNgay.getText().toString());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.etDenNgay:
                try {
                    adapterDateDialog = new DateDialogAdapter(view, etDenNgay.getText().toString());
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.etKho:
                intent = new Intent(ChiTietBaoCaoActivity.this, DanhSachKhoCheckedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.putString("DanhSachKho", danhSachKhoStr);
                intent.putExtras(bundle);
                startActivityForResult(intent, FILTER_KHO_REQUESTCODE);
                break;
            case R.id.etNhanVien:
                break;
            case R.id.etLoaiHang:
                intent = new Intent(ChiTietBaoCaoActivity.this, DanhSachLoaiHangCheckedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                bundle = new Bundle();
                bundle.putString("DanhSachLoaiHang", danhSachLoaiHangStr);
                intent.putExtras(bundle);
                startActivityForResult(intent, FILTER_LOAIHANG_REQUESTCODE);
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
                case FILTER_KHO_REQUESTCODE:
                    bundle = intent.getExtras();
                    if (bundle == null) return;
                    danhSachKhoStr = bundle.getString("MaKho");
                    etKho.setText(bundle.getString("TenKho"));
                    break;

                case FILTER_LOAIHANG_REQUESTCODE:
                    bundle = intent.getExtras();
                    if (bundle == null) return;
                    danhSachLoaiHangStr = bundle.getString("MaLoaiHang");
                    etLoaiHang.setText(bundle.getString("TenLoaiHang"));
                    break;
                default:
                    break;
            }
        }
    }
}
