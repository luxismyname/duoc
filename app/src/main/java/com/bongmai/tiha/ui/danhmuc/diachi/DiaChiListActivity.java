package com.bongmai.tiha.ui.danhmuc.diachi;

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
import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.ui.base.BaseActivity;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.danhmuc.diachi.duong.ThemMoiDuongDialog;
import com.bongmai.tiha.ui.danhmuc.diachi.phuong.ThemMoiPhuongDialog;
import com.bongmai.tiha.ui.danhmuc.diachi.quan.ThemMoiQuanDialog;
import com.bongmai.tiha.utils.CommonUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DiaChiListActivity extends AppCompatActivity implements BaseActivity, DiaChiListContract.View {

    //region Khai bao bien
    Toolbar toolbar;
    RecyclerView rvData;
    DiaChiListAdapter adapterData;
    List<DiaChiInfo> listData;
    EditText etTimKiem;
    ImageButton btnClear;
    FloatingActionButton fab;
    DiaChiInfo diaChiInfo;
    DiaChiListPresenter diachiPresenter;
    String loaiDiaChi = "";
    int parentID;
    int parentName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachilist);
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
        fab = findViewById(R.id.fabThem);

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
        String tieuDeForm = getResources().getString(R.string.diachi_tieudeform);
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
        diachiPresenter = new DiaChiListPresenter(this);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            diaChiInfo = new DiaChiInfo();

            loaiDiaChi = bundle.getString("LoaiDiaChi");
            parentID = bundle.getInt("ParentID");


            switch (loaiDiaChi) {
                case DiaChiInfo.DUONG:
                    getSupportActionBar().setTitle(getResources().getString(R.string.duong_tieudeform));
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ThemMoiDuongDialog.getInstance().showConfirmDialog(getApplicationContext().getResources().getString(R.string.popup_them_moi_duong),
                                    "", "", DiaChiListActivity.this,
                                    new ThemMoiDuongDialog.DialogClickInterface() {
                                        @Override
                                        public void onClickPositiveButton(String tenduongviettat, String chitiettenduong) {

                                            diaChiInfo.setMaDiaChi(tenduongviettat);
                                            diaChiInfo.setTenDiaChi(chitiettenduong);
                                            diaChiInfo.setTinhID(55);
                                            diaChiInfo.setQuanID(5514);
                                            diaChiInfo.setLoaiDiaChi("DUONG");
                                            diachiPresenter.InsertDiaChi(diaChiInfo);
                                        }

                                @Override
                                public void onClickNegativeButton() {

                                }
                            });
                        }
                    });
                    break;
                case DiaChiInfo.PHUONG:
                    getSupportActionBar().setTitle(getResources().getString(R.string.phuong_tieudeform));
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ThemMoiPhuongDialog.getInstance().showConfirmDialog(getApplicationContext().getResources().getString(R.string.popup_them_moi_phuong), "", "",
                                    DiaChiListActivity.this, new ThemMoiPhuongDialog.DialogClickInterface() {
                                @Override
                                public void onClickPositiveButton(String tenphuongviettat, String chitiettenphuong) {
                                    diaChiInfo.setTinhID(55);
                                    diaChiInfo.setQuanID(5526);
                                    diaChiInfo.setMaDiaChi(tenphuongviettat);
                                    diaChiInfo.setTenDiaChi(chitiettenphuong);
                                    diaChiInfo.setLoaiDiaChi("PHUONG");
                                    diachiPresenter.InsertDiaChi(diaChiInfo);
                                }

                                @Override
                                public void onClickNegativeButton() {

                                }
                            });
                        }
                    });
                    break;
                case DiaChiInfo.QUAN:
                    getSupportActionBar().setTitle(getResources().getString(R.string.quan_tieudeform));
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ThemMoiQuanDialog.getInstance().showConfirmDiaglog(getApplicationContext().getResources().getString(R.string.popup_them_moi_quan), "", "",
                                    DiaChiListActivity.this, new ThemMoiQuanDialog.DialogClickInterface() {
                                @Override
                                public void onClickPositiveButton(String tenquanviettat, String chitiettenquan) {
                                    diaChiInfo.setTinhID(55);
                                    diaChiInfo.setQuanID(0);
                                    diaChiInfo.setMaDiaChi(tenquanviettat);
                                    diaChiInfo.setTenDiaChi(chitiettenquan);
                                    diaChiInfo.setLoaiDiaChi("QUAN");
                                    diachiPresenter.InsertDiaChi(diaChiInfo);
                                }

                                @Override
                                public void onClickNegativeButton() {

                                }
                            });
                        }
                    });
                    break;
                case DiaChiInfo.TINH:
                    getSupportActionBar().setTitle(getResources().getString(R.string.tinh_tieudeform));
//                    fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ThemMoiTinhThanhPhoDialog.getInstance().showConfirmDialog(getApplicationContext().getResources().getString(R.string.popup_them_moi_tinh), "", "",
//                                    DiaChiListActivity.this, new ThemMoiTinhThanhPhoDialog.DialogClickInterface() {
//                                @Override
//                                public void onClickPositiveButton(String tentinhviettat, String chitiettentinh) {
////                                    diaChiInfo.setMaDiaChi(tentinhviettat);
////                                    diaChiInfo.setTenDiaChi(chitiettentinh);
////                                    diachiPresenter.InsertDiaChi(diaChiInfo);
//                                }
//
//                                @Override
//                                public void onClickNegativeButton() {
//
//                                }
//                            });
//                        }
//                    });

                    break;
                default:
                    break;
            }
           loadDiaChi();
        }


    }

    private void loadDiaChi() {
        diachiPresenter.GetListDiaChi(loaiDiaChi, parentID, parentName);
    }

    private void onBindData() {

        adapterData = new DiaChiListAdapter(DiaChiListActivity.this, listData);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DiaChiInfo", adapterData.getItem(position));
                bundle.putString("LoaiDiaChi", loaiDiaChi);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }

    @Override
    public void onGetListDiaChiSuccess(List<DiaChiInfo> listResult) {
        this.listData = listResult;
        adapterData.setListAllData(listResult == null ? new ArrayList<>() : listResult);
        onBindData();
    }

    @Override
    public void onGetListDiaChiError(String error) {
        listData = new ArrayList<>();
        onBindData();
        Toast.makeText(DiaChiListActivity.this, "Lấy danh sách địa chỉ thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInsertDiaChiSuccess(DiaChiInfo itemResult) {
        this.diaChiInfo = itemResult;
        int position = adapterData.getItemPosition(itemResult.getParentID());
        if(position >= 0){
            adapterData.addItem(position, itemResult);
        } else {
            adapterData.addItem(itemResult);
        }
        Toast.makeText(DiaChiListActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
    }


//    @Override
//    public void onInsertDiaChiSuccess(DiaChiInfo itemResult) {
//        this.diaChiInfo = itemResult;
//        int position = adapterData.getItemPosition(itemResult.getParentID());
//        if(position >= 0){
//            adapterData.addItem(position, itemResult);
//        } else {
//            adapterData.addItem(itemResult);
//        }
//
//        Toast.makeText(DiaChiListActivity.this, "Thêm địa chỉ thành công", Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onInsertDiaChiError(String error) {
        CommonUtils.showMessageError(this, error);
    }


}
