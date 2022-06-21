package com.bongmai.tiha.ui.chungtu.phieuxuat.list;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.PhieuXuatCondition;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiActivity;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiAdapter;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.PhieuBanSiDetailActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked.DanhSachKhoCheckedActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PhieuBanSiListFragment extends Fragment implements BaseFragment, View.OnClickListener, PhieuBanSiListContract.View {

    FloatingActionButton fabThem;
    PhieuBanSiListPresenter phieuBanSiListPresenter;
    PhieuBanSiListAdapter adapterData;

    PhieuBanSiAdapter adapter;
    RecyclerView rvData;
    View errorLayout;
    Button btnRetry;
    TextView txtError;
    TextView
            tvTongTien,
            tvTongSL;
    ProgressBar progressBar;
    ConstraintLayout ctlMain;
    ConstraintLayout ctlErrorLayout;
    SwipeRefreshLayout swipe_refresh;
    EditText etTimKiem;
    ImageButton btnClear;
    Button btnFilter;
    PhieuXuatCondition condition;
    DateDialogAdapter adapterDateDialog;
    VattuxuatInfo vattuxuatInfo;
    //Dialog filter
    EditText etFromDate;
    EditText etToDate;
    EditText etKho;
    NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo;

    TelephonyManager tm;
    String imeiID = "";

    private boolean isShowProgressBar;
    private static final int FILTER_KHO_REQUESTCODE = 0;
    private static final int REQUEST_PHIEUBANSI = 1;
    private static final int REQUEST_SANPHAM = 2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieubansilist, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        onInit(view);
        onLoadData();
        return view;
    }

    @Override
    public void onInit(View view) {
        fabThem = view.findViewById(R.id.fabThem);
        rvData = view.findViewById(R.id.rvData);
        errorLayout = view.findViewById(R.id.ctlErrorLayout);
        btnRetry = view.findViewById(R.id.error_btn_retry);
        txtError = view.findViewById(R.id.error_txt_cause);
        progressBar = view.findViewById(R.id.progressBar);
        ctlMain = view.findViewById(R.id.ctlMain);
        ctlErrorLayout = view.findViewById(R.id.ctlErrorLayout);
        swipe_refresh = view.findViewById(R.id.swipe_refresh);
        etTimKiem = view.findViewById(R.id.etTimKiem);
        btnClear = view.findViewById(R.id.btnClear);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        tvTongSL = view.findViewById(R.id.tvTongSL);
        btnFilter = view.findViewById(R.id.btnFilter);

        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());
//        rvData.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));


        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(
                () -> {
                    isShowProgressBar = false;
                    loadListPhieuXuat();
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
                adapterData.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnClear.setOnClickListener(this);
        btnRetry.setOnClickListener(this);
        fabThem.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
    }

    @Override
    public void onLoadData() {
        phieuBanSiListPresenter = new PhieuBanSiListPresenter(this);
        isShowProgressBar = true;
        onBindData();
        loadListPhieuXuat();
    }

    private void loadListPhieuXuat() {
        hideErrorView();
        adapterData.getListAllData().clear();
        if (condition == null) {
            condition = new PhieuXuatCondition();
            condition.setListKho("");
            condition.setNgayBD(PublicVariables.NgayLamViec);
            condition.setNgayKT(PublicVariables.NgayLamViec);
            condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
        }
        phieuBanSiListPresenter.GetListPhieuXuat(condition);
    }

    private void onBindData() {
        adapterData = new PhieuBanSiListAdapter(getContext(), new ArrayList<>());
        tm = (TelephonyManager) this.getActivity().getSystemService(getActivity().TELEPHONY_SERVICE);
        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                DanhSachXuatInfo danhSachXuatInfo = adapterData.getItem(position);

                List<VattuxuatInfo> list = new ArrayList<>();
                if (danhSachXuatInfo == null) return;
                else {
                    Intent intent = new Intent(getContext(), PhieuBanSiDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("SoCT", danhSachXuatInfo.getSoCt());
                    bundle.putSerializable("listVattuxuat", (Serializable) list);
                    intent.putExtras(bundle);
                    //startActivity(intent);
                    startActivityForResult(intent, REQUEST_PHIEUBANSI);
                }
            }
        });

        adapterData.setOnLongClickListener(new BaseRecyclerViewEvent.OnLongClickListener() {
            @Override
            public void onLongClick(View view, int positionItem) {
                ArrayList<String> listLongClick = new ArrayList<>();
                listLongClick.add("Cập nhật");
                listLongClick.add("Xóa");
                listLongClick.add("In phiếu");

                final CharSequence[] items = listLongClick.toArray(new CharSequence[listLongClick.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int position) {

                        final DanhSachXuatInfo danhSachXuatInfo = adapterData.getItem(positionItem);
                        switch (position) {
                            case 0:

                                Intent intent = new Intent(getContext(), PhieuBanSiActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Bundle bundle = new Bundle();
                                bundle.putString("SoCT", danhSachXuatInfo.getSoCt());
                                bundle.putSerializable("NhapSoLuongSanPhamInfo", nhapSoLuongSanPhamInfo);
                                intent.putExtras(bundle);
                                //startActivity(intent);
                                startActivityForResult(intent, REQUEST_PHIEUBANSI);
                                break;
                            case 1:
                                final AlertDialog.Builder builderDelete = new AlertDialog.Builder(getContext());
                                builderDelete.setTitle("XÓA SẢN PHẨM")
                                        .setMessage("Bạn có chắc muốn xóa đơn hàng này?")
                                        .setCancelable(false)
                                        .setPositiveButton(getContext().getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {

                                                phieuBanSiListPresenter.DeletePhieuXuat(danhSachXuatInfo.getSoCt(), positionItem);
                                                dialog.cancel();
                                            }
                                        })
                                        .setNegativeButton(getContext().getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                                            public void onClick(final DialogInterface dialog, final int id) {
                                                dialog.cancel();
                                            }
                                        });
                                final AlertDialog alert = builderDelete.create();
                                alert.show();
                                break;

                            case 2:
//                                String imei = android.provider.Settings.Secure.getString(
//                                        getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                                String imei = "";
                                TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    if (getActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                                        if (telephonyManager != null) {
                                            try {
                                                imei = telephonyManager.getImei();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                imei = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                                            }
                                        }
                                    } else {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
                                    }
                                } else {
                                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                                        if (telephonyManager != null) {
                                            imei = telephonyManager.getDeviceId();
                                        }
                                    } else {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
                                    }
                                }
                                phieuBanSiListPresenter.InQuaInternet(danhSachXuatInfo.getSoCt(), imei);
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

        adapterData.setOnButtonClickListener(new BaseRecyclerViewEvent.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                DanhSachXuatInfo itemDSXuat = adapterData.getItem(position);
                switch (view.getId()){
                    case R.id.cltPrint:
                        String imei = "";
                        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (getActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                                if (telephonyManager != null) {
                                    try {
                                        imei = telephonyManager.getImei();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        imei = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                                    }
                                }
                            } else {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
                            }
                        } else {
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                                if (telephonyManager != null) {
                                    imei = telephonyManager.getDeviceId();
                                }
                            } else {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
                            }
                        }
                        phieuBanSiListPresenter.InQuaInternet(itemDSXuat.getSoCt(), imei);
                        break;
                    default:
                        break;
                }
            }
        });

        adapterData.setOnDataSetChangedListener(new BaseRecyclerViewEvent.OnDataSetChangedListener() {
            @Override
            public void onDataSetChanged() {
                SumTongTien();
            }
        });

        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabThem:
                Intent intent = new Intent(getContext(), PhieuBanSiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.error_btn_retry:
                loadListPhieuXuat();
                break;
            case R.id.btnClear:
                etTimKiem.setText("");
                break;
            case R.id.btnFilter:
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.dialog_bottom_filter_phieubansilist, null);
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                etFromDate = customView.findViewById(R.id.etFromDate);
                etToDate = customView.findViewById(R.id.etToDate);
                etKho = customView.findViewById(R.id.etKho);
                TextView tvDatLai = customView.findViewById(R.id.tvDatLai);
                Button btnApDung = customView.findViewById(R.id.btnApDung);

                btnApDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        condition.setNgayBD(etFromDate.getText().toString());
                        condition.setNgayKT(etToDate.getText().toString());
                        loadListPhieuXuat();
                        dialog.dismiss();
                    }
                });
                tvDatLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etFromDate.setText(PublicVariables.NgayLamViec);
                        etToDate.setText(PublicVariables.NgayLamViec);
                        etKho.setText("");
                        condition.setListKho("");
                        condition.setListTenKho("");
                    }
                });
                etFromDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            adapterDateDialog = new DateDialogAdapter(view, etFromDate.getText().toString());
                            android.app.FragmentTransaction ft = ((AppCompatActivity) getContext()).getFragmentManager().beginTransaction();
                            adapterDateDialog.show(ft, "DatePicker");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                etToDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            adapterDateDialog = new DateDialogAdapter(view, etToDate.getText().toString());
                            android.app.FragmentTransaction ft = ((AppCompatActivity) getContext()).getFragmentManager().beginTransaction();
                            adapterDateDialog.show(ft, "DatePicker");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                etKho.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), DanhSachKhoCheckedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        bundle.putString("DanhSachKho", condition.getListKho());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, FILTER_KHO_REQUESTCODE);
                    }
                });

                etFromDate.setText(condition.getNgayBD());
                etToDate.setText(condition.getNgayKT());
                etKho.setText(condition.getListTenKho());


                View bottomSheet = customView.findViewById(R.id.bottom_sheet);
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setPeekHeight(0);

                dialog.setContentView(customView);
                dialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onGetListPhieuXuatSuccess(List<DanhSachXuatInfo> listResult) {

        adapterData.addAll(listResult);
        SumTongTien();
        hideProgressBar();
        hideErrorView();
    }

    private void SumTongTien() {
        double tongTien = 0;
        for (DanhSachXuatInfo item : adapterData.getListAllData()) {
            tongTien += item.getTT();
        }
        tvTongTien.setText(AppUtils.formatNumber("N0").format(tongTien));
        tvTongSL.setText(AppUtils.formatNumber("N0").format(adapterData.getListAllData().size()));
    }




    @Override
    public void onGetListPhieuXuatError(String error) {
        showErrorView(error);
        hideProgressBar();
//        Toast.makeText(getContext(), "Lấy danh sách đơn hàng thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeletePhieuXuatSuccess(int position) {
        Toast.makeText(getContext(), "Xóa đơn hàng thành công!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDeletePhieuXuatError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onInQuaInternetSuccess() {
        Toast.makeText(getContext(), "In phiếu thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInQuaInternetError(String error) {
        Toast.makeText(getContext(), "In phiếu thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle bundle;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FILTER_KHO_REQUESTCODE:
                    bundle = intent.getExtras();
                    if (bundle == null) return;
                    condition.setListKho(bundle.getString("MaKho"));
                    condition.setListTenKho(bundle.getString("TenKho"));
                    etKho.setText(condition.getListTenKho());
                    break;
                case REQUEST_PHIEUBANSI:
                    bundle = intent.getExtras();
                    if(bundle == null) return;
                    vattuxuatInfo.setThanh_Tien(bundle.getDouble("VattuXuatInfo"));
                    tvTongTien.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL() * vattuxuatInfo.getGiaban()));
                    vattuxuatInfo.setSL(vattuxuatInfo.getSL());
                    vattuxuatInfo.setTongChietKhau(vattuxuatInfo.getTongChietKhau());

                    break;
                case REQUEST_SANPHAM:
                    bundle = intent.getExtras();
                    NhapSoLuongSanPhamInfo nhapSoLuongSanPhamInfo = (NhapSoLuongSanPhamInfo) bundle.getSerializable("NhapSoLuongSanPhamInfo");
                    VattuxuatInfo vattuxuatInfo = new VattuxuatInfo().getVattuxuatByNhapSL(nhapSoLuongSanPhamInfo);
                    int position = -1;
                    List<VattuxuatInfo> listVattuxuat = adapter.getListAllData();
                    for (int i = 0; i < listVattuxuat.size(); i++) {
                        if (listVattuxuat.get(i).getProduct_ID().equals(vattuxuatInfo.getProduct_ID())) {
                            position = i;
                            break;
                        }
                    }
                    if (position == -1) {
                        adapter.addItem(vattuxuatInfo);
                    } else {
                        adapter.removeItem(position);
                        adapter.addItem(position, vattuxuatInfo);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
