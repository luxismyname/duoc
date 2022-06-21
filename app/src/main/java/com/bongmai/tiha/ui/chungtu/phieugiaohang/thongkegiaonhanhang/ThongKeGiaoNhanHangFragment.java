package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bongmai.tiha.BuildConfig;
import com.bongmai.tiha.MainActivity;
import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuDatHangInfo;
import com.bongmai.tiha.data.entities.PhieuGiaoHangCondition;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.UserKhacActivity;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieugiaohang.ghichudialog.CapNhatGhiChuPhieuGiaoHangDialog;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.PhieuBanSiDetailActivity;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;
import com.bongmai.tiha.ui.danhmuc.kho.danhsachchecked.DanhSachKhoCheckedActivity;
import com.bongmai.tiha.ui.danhmuc.trangthai.TrangThaiAdapter;
import com.bongmai.tiha.ui.map.GPSTracker;
import com.bongmai.tiha.ui.map.MapActivity;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class ThongKeGiaoNhanHangFragment extends Fragment implements BaseFragment, View.OnClickListener, ThongKeGiaoNhanHangContract.View {
    ThongKeGiaoNhanHangPresenter thongKeGiaoNhanHangPresenter;
    ThongKeGiaoNhanHangApdapter adapterData;
    TrangThaiAdapter adapterTrangThai;
    RecyclerView rvData;
    View errorLayout;
    Button btnRetry;
    TextView txtError;
    TextView tvKho1, tvKho2;
    ProgressBar progressBar;
    ConstraintLayout ctlMain;
    ConstraintLayout ctlErrorLayout;
    SwipeRefreshLayout swipe_refresh;
    Button btnFilter;
    PhieuGiaoHangCondition condition;
    DateDialogAdapter adapterDateDialog;
    TextView tvVersion;
    String listkho1 = "", listkho2 = "";

    List<ThongKeGiaoNhanHangInfo> thongkeList;

    List<KhoInfo> kholist;

    ChangeLogInfo logInfo;

    FloatingActionButton fabThem;
    List<String> listTrangThai;
    String versionInfo = "";

    EditText  tvMaTrangThai;
    Spinner spinnerTimKiem;
    ThongKeGiaoNhanHangInfo thongke;
    PhieuDatHangInfo phieuDatHangInfo;
    List<TrangThaiLoaiPhieuInfo> menusTrangThai;
    String kho1 = "", kho2 = "";
    TextView
            tvTongTien,
            tvTongSL;

    //Dialog filter
    EditText etFromDate;
    EditText etToDate;
    EditText etKho;
    ImageButton btnClear;
    EditText etTimKiem;

    ConstraintLayout ctlTotal;

    private boolean isShowProgressBar;
    private static final int FILTER_KHO_REQUESTCODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke_giao_nhan_hang, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            ((MainActivity) getActivity()).hideFloatingActionButton();
        } else {

            ((UserKhacActivity) getActivity()).hideFloatingActionButton();
        }
        onInit(view);
        onLoadData();
        return view;
    }

  @Override
    public void onInit(View view) {
        fabThem = view.findViewById(R.id.fabThem);
        rvData = view.findViewById(R.id.rvData);
        errorLayout = view.findViewById(R.id.error_layout);
        btnRetry = view.findViewById(R.id.error_btn_retry);
        txtError = view.findViewById(R.id.error_txt_cause);
        progressBar = view.findViewById(R.id.progressBar);
        ctlMain = view.findViewById(R.id.ctlMain);
        ctlErrorLayout = view.findViewById(R.id.ctlErrorLayout);
        swipe_refresh = view.findViewById(R.id.swipe_refresh);
        etTimKiem = view.findViewById(R.id.etTimKiem);

        tvVersion = view.findViewById(R.id.tvVersion);
        btnFilter = view.findViewById(R.id.btnFilter);
        btnClear = view.findViewById(R.id.btnClear);
        spinnerTimKiem = view.findViewById(R.id.spinnerTimKiem);
        tvMaTrangThai = view.findViewById(R.id.tvMaTrangThai);
        tvKho1 = view.findViewById(R.id.tvKho1);
        tvKho2 = view.findViewById(R.id.tvKho2);

        tvTongTien = view.findViewById(R.id.tvTongTien);
        tvTongSL = view.findViewById(R.id.tvTongSL);

        ctlTotal = view.findViewById(R.id.ctlTotal);
      Context context = getContext();
      PackageManager packageManager = context.getPackageManager();
      String packageName = context.getPackageName();

      try {
          versionInfo = packageManager.getPackageInfo(packageName, 0).versionName;
      } catch (PackageManager.NameNotFoundException e) {
          e.printStackTrace();
      }

       tvVersion.setText(versionInfo);
       fabThem.hide();

        rvData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(llm);
        rvData.setItemAnimator(new DefaultItemAnimator());
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

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(
                () -> {
                    isShowProgressBar = false;
                    loadListPhieuGiaoHang();
                });

        btnRetry.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onLoadData() {

        thongKeGiaoNhanHangPresenter = new ThongKeGiaoNhanHangPresenter(this);
        thongke = new ThongKeGiaoNhanHangInfo();
        phieuDatHangInfo = new PhieuDatHangInfo();
        logInfo = new ChangeLogInfo();
        thongKeGiaoNhanHangPresenter.GetListTrangThai("PHIEUXUAT", PublicVariables.nguoiDungInfo.getUserName());
        isShowProgressBar = true;
        kholist = new ArrayList<>();
        thongkeList = new ArrayList<>();
        listTrangThai = new ArrayList<>();
        menusTrangThai = new ArrayList<>();
        onBindData();
        loadListPhieuGiaoHang();

    }

    private void loadListPhieuGiaoHang() {
        hideErrorView();

            adapterData.getListAllData().clear();
            if (condition == null) {
                condition = new PhieuGiaoHangCondition();

                condition.setNgayBD(PublicVariables.NgayLamViec);
                condition.setNgayKT(PublicVariables.NgayLamViec);
                condition.setUserName(PublicVariables.nguoiDungInfo.getUserName());
                condition.setListKho("");
                if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
                    condition.setTrangThai("");
                } else {
                    condition.setTrangThai("9");
                }

                if(condition.getTrangThai().equals("9")) {
                    condition.setTextSearch("");
                } else if(!condition.getTrangThai().equals("9") &&  !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
                    condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
                } else if(!condition.getTrangThai().equals("9") && PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
                    condition.setTextSearch("");
                }

            }
            thongKeGiaoNhanHangPresenter.GetListThongKeGiaoNhanHang(condition);

    }


    private void onBindData() {
        thongKeGiaoNhanHangPresenter.CheckChangeLog();
        adapterData = new ThongKeGiaoNhanHangApdapter(getContext(), thongkeList);

        adapterData.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {

                ThongKeGiaoNhanHangInfo itemThongKe = adapterData.getItem(position);
                thongke = itemThongKe;
                switch (itemThongKe.getTenTaiXe()){
                     case "TIHALOC":
                        capnhattrangthai();
                        break;
                    case "TIHADUOCQ10":
                        capnhattrangthaiCuuLong();
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

     adapterData.setOnButtonClickListener(new BaseRecyclerViewEvent.OnButtonClickListener() {
            @Override
            public void onButtonClick(View view, int position) {
                ThongKeGiaoNhanHangInfo itemThongKe = adapterData.getItem(position);
                thongke = itemThongKe;

                switch (view.getId()) {

                    case R.id.tvSoCT:
                        ThongKeGiaoNhanHangInfo danhSachXuatInfo = adapterData.getItem(position);
                        List<VattuxuatInfo> list = new ArrayList<>();
                        if (danhSachXuatInfo == null) return;
                        else {
                            Intent intent = new Intent(getContext(), PhieuBanSiDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("SoCT", danhSachXuatInfo.getSoCt());
                            bundle.putSerializable("listVattuxuat", (Serializable) list);
                            intent.putExtras(bundle);
                            //startActivity(intent);
                            startActivity(intent);
                        }
                        break;
                    case R.id.ctlDiaChi:
                        if (!CommonUtils.checkLocation(getActivity())) {
                            buildAlertMessageNoGps();
                        }

                        if (!CommonUtils.checkLocation(getActivity())) {
                            GPSTracker tracker = new GPSTracker(getContext());
                            if (!tracker.canGetLocation()) {
                                tracker.showSettingsAlert();
                            }
                        }

                        else {

                            switch (itemThongKe.getTenTaiXe()){
                                case "TIHALOC":
                                    thongKeGiaoNhanHangPresenter.GetSupplier(thongke.getSupplierID());
                                    break;
                                case "TIHADUOCQ10":
                                    thongKeGiaoNhanHangPresenter.GetSupplierCuuLong(thongke.getSupplierID());
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;
                    case R.id.btnMore:
                        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                        popupMenu.inflate(R.menu.menu_popup);
                        popupMenu.show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                public boolean onMenuItemClick(MenuItem item) {

                                    switch (item.getItemId()){
                                        case R.id.menu_capnhattrangthai:
                                            if(itemThongKe.getTenTaiXe().equals("TIHALOC")){
                                                capnhattrangthai();
                                            } else if(itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")){
                                                capnhattrangthaiCuuLong();
                                            }
                                           // capnhattrangthai();
                                            break;
                                        case R.id.menu_xembando:
                                            if (!CommonUtils.checkLocation(getActivity())) {
                                                buildAlertMessageNoGps();
                                            }
                                            if(itemThongKe.getMaTrangThai().equals("6")){
                                                CommonUtils.showMessage(getContext(), "Bạn không thể xem bản đồ khi ở trạng thái này!");
                                            } else if(itemThongKe.getMaTrangThai().equals("7")){
                                                CommonUtils.showMessage(getContext(), "Bạn không thể xem bản đồ khi ở trạng thái này!");
                                            } else if(itemThongKe.getMaTrangThai().equals("8")){
                                                CommonUtils.showMessage(getContext(), "Bạn không thể xem bản đồ khi ở trạng thái này!");
                                            } else if(itemThongKe.getMaTrangThai().equals("9")){
                                                CommonUtils.showMessage(getContext(), "Bạn không thể xem bản đồ khi ở trạng thái này!");
                                            } else if(itemThongKe.getMaTrangThai().equals("10") && itemThongKe.getTenTaiXe().equals("TIHALOC")){
                                                thongKeGiaoNhanHangPresenter.GetSupplier(thongke.getSupplierID());
                                            } else if(itemThongKe.getMaTrangThai().equals("10") && itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")) {
                                                thongKeGiaoNhanHangPresenter.GetSupplierCuuLong(thongke.getSupplierID());
                                            }
                                            else if(itemThongKe.getMaTrangThai().equals("11") && itemThongKe.getTenTaiXe().equals("TIHALOC")){
                                                thongKeGiaoNhanHangPresenter.GetSupplier(thongke.getSupplierID());
                                            }
                                            else if(itemThongKe.getMaTrangThai().equals("11") && itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")){
                                                thongKeGiaoNhanHangPresenter.GetSupplierCuuLong(thongke.getSupplierID());
                                            }
//                                            } else {
//                                                thongKeGiaoNhanHangPresenter.GetSupplier(thongke.getSupplierID());
//                                            }
                                            break;

                                        case R.id.menu_chitietphieu:

                                            if(!PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator") && itemThongKe.getMaTrangThai().equals("11")){
                                                CommonUtils.showMessage(getContext(), "Đơn hàng đã giao rồi không thể xem chi tiết phiếu");
                                            } else {

                                                ThongKeGiaoNhanHangInfo danhSachXuatInfo = adapterData.getItem(position);
                                                List<VattuxuatInfo> list = new ArrayList<>();
                                                Intent intent = new Intent(getContext(), PhieuBanSiDetailActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("SoCT", danhSachXuatInfo.getSoCt());
                                                bundle.putSerializable("listVattuxuat", (Serializable) list);
                                                intent.putExtras(bundle);
                                                //startActivity(intent);
                                                startActivity(intent);
                                            }
                                            break;

                                        case R.id.menu_trahang:

                                            if(itemThongKe.getMaTrangThai().equals("11")){
                                                CommonUtils.showMessage(getContext(), "Phiếu đã giao không thể trả hàng");
                                            } else

                                            if(itemThongKe.getMaTrangThai().equals("10")){
                                                CommonUtils.showMessage(getContext(), "Bạn không thể trả hàng từ trạng thái này!");
                                            } else {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setMessage("Bạn đã lấy đơn hàng '" + itemThongKe.getSoCt() + "' rồi. Bạn có muốn trả lại?")
                                                        .setTitle("Thông báo")
                                                        .setCancelable(false)
                                                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                                            public void onClick(final DialogInterface dialog, final int id) {
                                                                if(itemThongKe.getTenTaiXe().equals("TIHALOC")) {
                                                                    CapNhatGhiChuPhieuGiaoHang(itemThongKe.getSoCt(), "");
                                                                    condition.setNgayBD(PublicVariables.NgayLamViec);
                                                                    condition.setNgayKT(PublicVariables.NgayLamViec);
                                                                    if (PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")) {
                                                                        condition.setTrangThai("");
                                                                    } else {
                                                                        condition.setTrangThai("9");
                                                                    }

                                                                    loadListPhieuGiaoHang();
                                                                } else if(itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")){
                                                                    CapNhatGhiChuPhieuGiaoHangCuuLong(itemThongKe.getSoCt(), "");
                                                                    condition.setNgayBD(PublicVariables.NgayLamViec);
                                                                    condition.setNgayKT(PublicVariables.NgayLamViec);
                                                                    if (PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")) {
                                                                        condition.setTrangThai("");
                                                                    } else {
                                                                        condition.setTrangThai("9");
                                                                    }

                                                                    loadListPhieuGiaoHang();
                                                                }
                                                            }
                                                        })
                                                        .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                                            public void onClick(final DialogInterface dialog, final int id) {
                                                                dialog.cancel();

                                                            }
                                                        });
                                                final AlertDialog alert = builder.create();
                                                alert.show();
                                            }

                                            break;


                                        case R.id.menu_huydonhang:

                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                            builder1.setMessage("Bạn có muốn hủy đơn hàng '" + itemThongKe.getSoCt() + "' không?" )
                                                    .setTitle("Thông báo")
                                                    .setCancelable(false)
                                                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                                        public void onClick(final DialogInterface dialog, final int id) {
                                                            if(itemThongKe.getTenTaiXe().equals("TIHALOC")) {
                                                                HuyDonHang(itemThongKe.getSoCt(), "");

                                                                loadListPhieuGiaoHang();
                                                            } else if(itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")){
                                                                HuyDonHangCuuLong(itemThongKe.getSoCt(), "");
                                                                loadListPhieuGiaoHang();
                                                            }
                                                        }
                                                    })
                                                    .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                                        public void onClick(final DialogInterface dialog, final int id) {
                                                            dialog.cancel();

                                                        }
                                                    });
                                            final AlertDialog alert1 = builder1.create();
                                            alert1.show();
                                            break;

                                        case R.id.menu_capnhatthongtingiaohang:
                                            if(itemThongKe.getMaTrangThai().equals("11")){
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setMessage("Đơn hàng '" + itemThongKe.getSoCt() + "' đã giao rồi. Bạn có muốn cập nhật lại thông tin giao hàng??")
                                                        .setTitle("Thông báo")
                                                        .setCancelable(false)
                                                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                                            public void onClick(final DialogInterface dialog, final int id) {
                                                                if(itemThongKe.getTenTaiXe().equals("TIHALOC")) {
                                                                    thongKeGiaoNhanHangPresenter.CapNhatThongTinPhieuGiaoHangSai(itemThongKe.getSoCt());
                                                                    condition.setNgayBD(PublicVariables.NgayLamViec);
                                                                    condition.setNgayKT(PublicVariables.NgayLamViec);
                                                                    if (PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")) {
                                                                        condition.setTrangThai("");
                                                                    } else {
                                                                        condition.setTrangThai("10");
                                                                    }

                                                                    loadListPhieuGiaoHang();
                                                                } else if(itemThongKe.getTenTaiXe().equals("TIHADUOCQ10")){
                                                                    thongKeGiaoNhanHangPresenter.CapNhatThongTinPhieuGiaoHangSaiCuuLong(itemThongKe.getSoCt());
                                                                    condition.setNgayBD(PublicVariables.NgayLamViec);
                                                                    condition.setNgayKT(PublicVariables.NgayLamViec);
                                                                    if (PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")) {
                                                                        condition.setTrangThai("");
                                                                    } else {
                                                                        condition.setTrangThai("10");
                                                                    }

                                                                    loadListPhieuGiaoHang();
                                                                }
                                                            }
                                                        })
                                                        .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                                            public void onClick(final DialogInterface dialog, final int id) {
                                                                dialog.cancel();

                                                            }
                                                        });
                                                final AlertDialog alert = builder.create();
                                                alert.show();
                                            } else
                                            {
                                                CommonUtils.showMessage(getContext(), "Đơn hàng chưa giao xong không thể cập nhật thông tin bị sai");
                                            }
                                            break;

                                        default:
                                            break;

                                    }
                                    return false;
                                }
                            });
                        }
                        break;
                    default:
                        break;

                }
            }
        });


        rvData.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();
    }

    private void capnhattrangthai(){

        if(TextUtils.isEmpty(thongke.getMaTrangThai())){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt()+ " sang trạng thái 'CHỜ ĐÓNG GÓI' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("6");
                    thongke.setTenTrangThai("Chờ đóng gói");
                    thongke.setMaTrangThaiCu(null);
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                    
                    loadListPhieuGiaoHang();

                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();

            return;

        } else

        if(thongke.getMaTrangThai().equals("6")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐÃ ĐÓNG GÓI' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("7");
                    thongke.setTenTrangThai("Đã đóng gói");
                    thongke.setMaTrangThaiCu("6");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                    
                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("7")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'CHỜ ĐĂNG KÝ GIAO' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("8");
                    thongke.setTenTrangThai("Chờ đăng ký giao");
                    thongke.setMaTrangThaiCu("7");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                    
                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("8")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> CHỜ GIAO NHẬN LẤY HÀNG' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("9");
                    thongke.setTenTrangThai("Chờ giao nhận lấy hàng");
                    thongke.setMaTrangThaiCu("8");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                    
                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("9")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐANG GIAO' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("10");
                    thongke.setTenTrangThai("Đang giao");
                    thongke.setMaTrangThaiCu("9");
                    thongKeGiaoNhanHangPresenter.CapNhatNhanVienGiaoHang(thongke.getSoCt(), PublicVariables.nguoiDungInfo.getEmployeeID());
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                    if(!TextUtils.isEmpty(thongke.getTaiXe())) {
                        phieuDatHangInfo.setSoCT(thongke.getTaiXe());
                        phieuDatHangInfo.setTrangThai("3");
                        phieuDatHangInfo.setTenTrangThai("Đang giao hàng");
                        phieuDatHangInfo.setMaTrangThaiCu("2");
                        thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuDatHang(phieuDatHangInfo);
                    }
                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        }
        else

        if(thongke.getMaTrangThai().equals("10")){
//            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
//            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
//                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐÃ GIAO' không?").
//                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    thongke.setMaTrangThai("11");
//                    thongke.setTenTrangThai("Đã giao");
//                    thongke.setMaTrangThaiCu("10");
//                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
//                    loadListPhieuGiaoHang();
//                    hideProgressBar();
//                    hideErrorView();
//                    dialog.dismiss();
//                }
//            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            final AlertDialog alert2 = builder2.create();
//            alert2.show();
//
//            return;

            CommonUtils.showMessage(getActivity(), "Không thể cập nhật trạng thái");

        } else if(thongke.getMaTrangThai().equals("11")){
            CommonUtils.showMessage(getActivity(), "Đơn hàng đã giao không thể tiếp tục cập nhật trạng thái!");
        }

    }


    private void capnhattrangthaiCuuLong(){

        if(TextUtils.isEmpty(thongke.getMaTrangThai())){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt()+ " sang trạng thái 'CHỜ ĐÓNG GÓI' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("6");
                    thongke.setTenTrangThai("Chờ đóng gói");
                    thongke.setMaTrangThaiCu(null);
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                    loadListPhieuGiaoHang();

                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();

            return;

        } else

        if(thongke.getMaTrangThai().equals("6")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐÃ ĐÓNG GÓI' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("7");
                    thongke.setTenTrangThai("Đã đóng gói");
                    thongke.setMaTrangThaiCu("6");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("7")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'CHỜ ĐĂNG KÝ GIAO' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("8");
                    thongke.setTenTrangThai("Chờ đăng ký giao");
                    thongke.setMaTrangThaiCu("7");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("8")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> CHỜ GIAO NHẬN LẤY HÀNG' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("9");
                    thongke.setTenTrangThai("Chờ giao nhận lấy hàng");
                    thongke.setMaTrangThaiCu("8");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        } else

        if(thongke.getMaTrangThai().equals("9")){
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐANG GIAO' không?").
                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thongke.setMaTrangThai("10");
                    thongke.setTenTrangThai("Đang giao");
                    thongke.setMaTrangThaiCu("9");
                    thongKeGiaoNhanHangPresenter.CapNhatNhanVienGiaoHangCuuLong(thongke.getSoCt(), PublicVariables.nguoiDungInfo.getEmployeeID());
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);
                    if(!TextUtils.isEmpty(thongke.getTaiXe())){
                        phieuDatHangInfo.setSoCT(thongke.getTaiXe());
                        phieuDatHangInfo.setTrangThai("3");
                        phieuDatHangInfo.setTenTrangThai("Đang giao hàng");
                        phieuDatHangInfo.setMaTrangThaiCu("2");
                        thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuDatHangCuuLong(phieuDatHangInfo);
                    }
                    loadListPhieuGiaoHang();
                    hideProgressBar();
                    hideErrorView();
                    dialog.dismiss();
                }
            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert2 = builder2.create();
            alert2.show();
            return;

        }
        else

        if(thongke.getMaTrangThai().equals("10")){
//            final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
//            builder2.setMessage("Bạn có muốn cập nhật trạng thái của phiếu xuất " + thongke.getSoCt() + " từ trạng thái "
//                    +"'"+ thongke.getTenTrangThai().toUpperCase()+"'" + " --> 'ĐÃ GIAO' không?").
//                    setCancelable(false).setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    thongke.setMaTrangThai("11");
//                    thongke.setTenTrangThai("Đã giao");
//                    thongke.setMaTrangThaiCu("10");
//                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
//                    loadListPhieuGiaoHang();
//                    hideProgressBar();
//                    hideErrorView();
//                    dialog.dismiss();
//                }
//            }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            final AlertDialog alert2 = builder2.create();
//            alert2.show();
//
//            return;

            CommonUtils.showMessage(getActivity(), "Không thể cập nhật trạng thái");

        } else if(thongke.getMaTrangThai().equals("11")){
            CommonUtils.showMessage(getActivity(), "Đơn hàng đã giao không thể tiếp tục cập nhật trạng thái!");
        }

    }


    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {


        spinnerTimKiem.setSelection(position);
        Adapter adapter = adapterView.getAdapter();
        TrangThaiLoaiPhieuInfo trangThaiLoaiPhieuInfo = (TrangThaiLoaiPhieuInfo) adapter.getItem(position);

        tvMaTrangThai.setText(trangThaiLoaiPhieuInfo.getMaTrangThai());

//        if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("9")) {
//            condition.setTextSearch("");
//        } else if(!trangThaiLoaiPhieuInfo.getMaTrangThai().equals("9") &&  !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
//            condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
//        } else if(!trangThaiLoaiPhieuInfo.getMaTrangThai().equals("9") && PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
//            condition.setTextSearch("");
//        }
        if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("6")){
            condition.setTextSearch("");
        } else if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("7")){
            condition.setTextSearch("");
        } else if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("8")){
            condition.setTextSearch("");
        } else
        if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("9")){
            condition.setTextSearch("");
        } else if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("10") && !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")|| trangThaiLoaiPhieuInfo.getMaTrangThai().equals("11") && !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
        }  else if(trangThaiLoaiPhieuInfo.getMaTrangThai().equals("10") && PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator") || trangThaiLoaiPhieuInfo.getMaTrangThai().equals("11") && PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            condition.setTextSearch("");
        }

        if(spinnerTimKiem.getSelectedItemPosition() == 0){

            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        } else if(spinnerTimKiem.getSelectedItemPosition() == 1){
            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        } else if(spinnerTimKiem.getSelectedItemPosition() == 2) {
            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        } else  if(spinnerTimKiem.getSelectedItemPosition() == 3) {
            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        } else  if(spinnerTimKiem.getSelectedItemPosition() == 4) {
            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        } else  if(spinnerTimKiem.getSelectedItemPosition() == 5) {
            condition.setTrangThai(tvMaTrangThai.getText().toString());
            loadListPhieuGiaoHang();
        }

        thongKeGiaoNhanHangPresenter.GetListThongKeGiaoNhanHang(condition);

        }

    private void CapNhatGhiChuPhieuGiaoHang(String soPhieu, String ghiChu){

        CapNhatGhiChuPhieuGiaoHangDialog.getInstance().showConfirmDialog("Lý do trả hàng", soPhieu, ghiChu, getActivity(), new CapNhatGhiChuPhieuGiaoHangDialog.DialogClickInterface() {
            @Override
            public void onClickPositiveButton(String soPhieu, String ghiChu) {
                thongKeGiaoNhanHangPresenter.CapNhatGhiChu(soPhieu, ghiChu);
                thongKeGiaoNhanHangPresenter.GetPhieuXuat(soPhieu);

                if(thongke.getMaTrangThai().equals("10")) {
                    thongke.setMaTrangThaiCu("10");
                    thongke.setMaTrangThai("9");
                    thongke.setTenTrangThai("Chờ giao nhận lấy hàng");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);

                } else
                if(thongke.getMaTrangThai().equals("9")) {
                    thongke.setMaTrangThaiCu("9");
                    thongke.setMaTrangThai("8");
                    thongke.setTenTrangThai("Chờ đăng ký giao");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);

                } else

                if(thongke.getMaTrangThai().equals("8")){
                    thongke.setMaTrangThaiCu("8");
                    thongke.setMaTrangThai("7");
                    thongke.setTenTrangThai("Đã đóng gói");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                } else

                if(thongke.getMaTrangThai().equals("7")){
                    thongke.setMaTrangThaiCu("7");
                    thongke.setMaTrangThai("6");
                    thongke.setTenTrangThai("Chờ đóng gói");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuat(thongke);
                }

            }

            @Override
            public void onClickNegativeButton() {

            }
        });

    }

    private void CapNhatGhiChuPhieuGiaoHangCuuLong(String soPhieu, String ghiChu){

        CapNhatGhiChuPhieuGiaoHangDialog.getInstance().showConfirmDialog("Lý do trả hàng", soPhieu, ghiChu, getActivity(), new CapNhatGhiChuPhieuGiaoHangDialog.DialogClickInterface() {
            @Override
            public void onClickPositiveButton(String soPhieu, String ghiChu) {
                thongKeGiaoNhanHangPresenter.CapNhatGhiChuCuuLong(soPhieu, ghiChu);
                thongKeGiaoNhanHangPresenter.GetPhieuXuatCuuLong(soPhieu);

                if(thongke.getMaTrangThai().equals("10")) {
                    thongke.setMaTrangThaiCu("10");
                    thongke.setMaTrangThai("9");
                    thongke.setTenTrangThai("Chờ giao nhận lấy hàng");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                } else
                if(thongke.getMaTrangThai().equals("9")) {
                    thongke.setMaTrangThaiCu("9");
                    thongke.setMaTrangThai("8");
                    thongke.setTenTrangThai("Chờ đăng ký giao");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);

                } else

                if(thongke.getMaTrangThai().equals("8")){
                    thongke.setMaTrangThaiCu("8");
                    thongke.setMaTrangThai("7");
                    thongke.setTenTrangThai("Đã đóng gói");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);
                } else

                if(thongke.getMaTrangThai().equals("7")){
                    thongke.setMaTrangThaiCu("7");
                    thongke.setMaTrangThai("6");
                    thongke.setTenTrangThai("Chờ đóng gói");
                    thongKeGiaoNhanHangPresenter.UpdateTrangThaiPhieuXuatCuuLong(thongke);
                }

            }

            @Override
            public void onClickNegativeButton() {

            }
        });

    }

    private void HuyDonHang(String soPhieu, String ghiChu){

        CapNhatGhiChuPhieuGiaoHangDialog.getInstance().showConfirmDialog("Lý do hủy đơn hàng", soPhieu, ghiChu, getActivity(), new CapNhatGhiChuPhieuGiaoHangDialog.DialogClickInterface() {
            @Override
            public void onClickPositiveButton(String soPhieu, String ghiChu) {
                thongKeGiaoNhanHangPresenter.HuyDonHang(soPhieu, ghiChu, PublicVariables.nguoiDungInfo.getUserName());

            }

            @Override
            public void onClickNegativeButton() {

            }
        });

    }

    private void HuyDonHangCuuLong(String soPhieu, String ghiChu){

        CapNhatGhiChuPhieuGiaoHangDialog.getInstance().showConfirmDialog("Lý do hủy đơn hàng", soPhieu, ghiChu, getActivity(), new CapNhatGhiChuPhieuGiaoHangDialog.DialogClickInterface() {
            @Override
            public void onClickPositiveButton(String soPhieu, String ghiChu) {
                thongKeGiaoNhanHangPresenter.HuyDonHangCuuLong(soPhieu, ghiChu, PublicVariables.nguoiDungInfo.getUserName());

            }

            @Override
            public void onClickNegativeButton() {

            }
        });

    }


    private void SumTongTien() {
        double tongTien = 0;
        for (ThongKeGiaoNhanHangInfo item : adapterData.getListAllData()) {
            tongTien += item.getThanhTien();
        }
        tvTongTien.setText(AppUtils.formatNumber("N0").format(tongTien));
        tvTongSL.setText(AppUtils.formatNumber("N0").format(adapterData.getListAllData().size()));

        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){

            ctlTotal.setVisibility(View.VISIBLE);

        } else {

            ctlTotal.setVisibility(View.GONE);

        }

    }



    private void loadTrangthai() {
        thongKeGiaoNhanHangPresenter.GetListTrangThai("PHIEUXUAT", PublicVariables.nguoiDungInfo.getUserName());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spinnerTimKiem:
                loadTrangthai();
                break;
            case R.id.error_btn_retry:
                loadListPhieuGiaoHang();
                break;

            case R.id.btnClear:
                etTimKiem.setText("");
                break;


            case R.id.btnFilter:
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.dialog_bottom_filter_phieugiaohangsilist, null);
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
                        condition.setTrangThai(tvMaTrangThai.getText().toString());

                       // tvMaTrangThai.setText(trangThaiLoaiPhieuInfo.getMaTrangThai());
                        if(tvMaTrangThai.getText().toString().equals("6")){
                            condition.setTrangThai("6");
                            loadListPhieuGiaoHang();
                        } else if(tvMaTrangThai.getText().toString().equals("7")){
                            condition.setTrangThai("7");

                                condition.setTextSearch("");

                            loadListPhieuGiaoHang();
                        } else if(tvMaTrangThai.getText().toString().equals("8")) {
                            condition.setTrangThai("8");

                                condition.setTextSearch("");

                            loadListPhieuGiaoHang();
                        } else if(tvMaTrangThai.getText().toString().equals("9")) {

                            condition.setTrangThai("9");

                                condition.setTextSearch("");

                            loadListPhieuGiaoHang();
                        } else if(tvMaTrangThai.getText().toString().equals("10")) {
                            condition.setTrangThai("10");
                            if(!PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
                                condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
                            } else {
                                condition.setTextSearch("");
                            }
                            loadListPhieuGiaoHang();
                        } else if(tvMaTrangThai.getText().toString().equals("11")) {
                            if(!PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator") && !condition.getTrangThai().equals("6")){
                                condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
                            } else {
                                condition.setTextSearch("");
                            }
                            condition.setTrangThai("11");
                            loadListPhieuGiaoHang();
                        }

//                        if(condition.getTrangThai().equals("") && PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
//                            condition.setTextSearch("");
//                        } else if(condition.getTrangThai().equals("") && !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
//                            condition.setTextSearch("");
//                        } else if(!condition.getTrangThai().equals("") && !PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
//                            condition.setTextSearch(PublicVariables.nguoiDungInfo.getEmployeeID());
//                        }
//                        thongKeGiaoNhanHangPresenter.GetListThongKeGiaoNhanHang(condition);

                        //loadListPhieuGiaoHang();
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
                        condition.setTrangThai("");
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
    public void onHuyDonHangSuccess() {
        Toast.makeText(getActivity(), "Đơn hàng đã được hủy thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHuyDonHangError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onHuyDonHangCuuLongSuccess() {
        Toast.makeText(getActivity(), "Đơn hàng đã được hủy thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHuyDonHangCuuLongError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onCapNhatGhiChuSuccess() {
        Toast.makeText(getActivity(), "Cập nhật ghi chú thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatGhiChuError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onCapNhatGhiChuCuuLongSuccess() {
        Toast.makeText(getActivity(), "Cập nhật ghi chú thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatGhiChuCuuLongError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onCapNhatNhanVienGiaoHangSuccess() {
        Toast.makeText(getActivity(), "Lấy đơn hàng thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onCapNhatNhanVienGiaoHangCuuLongSuccess() {
        Toast.makeText(getActivity(), "Lấy đơn hàng thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatNhanVienGiaoHangCuuLongError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onGetListThongKeGiaoNhanHangSuccess(List<ThongKeGiaoNhanHangInfo> listResultThongKe) {
        this.thongkeList = listResultThongKe;
        //condition.setListKho(tvKho1.getText().toString() + tvKho2.getText().toString());
        adapterData.getListAllData().clear();
        adapterData.addAll(thongkeList);

        SumTongTien();
        hideProgressBar();
        hideErrorView();
    }

    @Override
    public void onGetListThongKeGiaoNhanHangError(String error) {
        showErrorView(error);
        hideProgressBar();
    }



    @Override
    public void onGetListKhoCuuLongSuccess(List<KhoInfo> listResult) {
        kholist = listResult;

        for (KhoInfo itemKho : kholist) {
            listkho2 += itemKho.getMSK() + ",";
        }
        tvKho2.setText(listkho2);
        kho2 = tvKho2.getText().toString();


    }

    @Override
    public void onGetListKhoCuuLongError(String error) {

    }

    @Override
    public void onGetListKhoSuccess(List<KhoInfo> listResult) {
        kholist = listResult;
        String str ="";
        for (KhoInfo itemKho : kholist) {
            listkho1 += itemKho.getMSK() + ",";
        }
        tvKho1.setText(listkho1);
//        kho1 = tvKho1.getText().toString();
//        thongKeGiaoNhanHangPresenter.GetListKhoCuuLong();
//        condition.setListKho(tvKho1.getText().toString() + tvKho2.getText().toString());
    }

    @Override
    public void onGetListKhoError(String error) {

    }

    @Override
    public void onUpdateTrangThaiPhieuXuatSuccess(ThongKeGiaoNhanHangInfo itemResult) {
        thongke = itemResult;
        if(itemResult.getSoCt().isEmpty()){
            thongKeGiaoNhanHangPresenter.GetPhieuXuat(thongke.getSoCt());
            itemResult.setTenTrangThai(thongke.getTenTrangThai());
            itemResult.setMaTrangThai(thongke.getMaTrangThai());
            itemResult.setMaTrangThaiCu(thongke.getMaTrangThaiCu());
        }
        condition.setTrangThai(thongke.getMaTrangThaiCu());
        loadListPhieuGiaoHang();
        hideProgressBar();
        hideErrorView();
        Toast.makeText(getContext(), "Cập nhật trạng thái " + thongke.getTenTrangThai() + " thành công!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpdateTrangThaiPhieuXuatError(String error) {
       CommonUtils.showMessageError(getActivity(), "Đã xảy ra lỗi không mong muốn");
    }

    @Override
    public void onUpdateTrangThaiPhieuDatHangSuccess(PhieuDatHangInfo itemResult) {
        phieuDatHangInfo = itemResult;
        if(itemResult.getSoCT().isEmpty()){
            thongKeGiaoNhanHangPresenter.GetPhieuXuat(thongke.getSoCt());
            itemResult.setTenTrangThai(thongke.getTenTrangThai());
            itemResult.setTrangThai(thongke.getMaTrangThai());
            itemResult.setMaTrangThaiCu(thongke.getMaTrangThaiCu());
        }
//        condition.setTrangThai(thongke.getMaTrangThaiCu());
//        loadListPhieuGiaoHang();
//        hideProgressBar();
//        hideErrorView();
        Toast.makeText(getContext(), "Cập nhật trạng thái " + thongke.getTenTrangThai() + " thành công!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpdateTrangThaiPhieuDatHangError(String error) {

    }

    @Override
    public void onUpdateTrangThaiPhieuXuatCuuLongSuccess(ThongKeGiaoNhanHangInfo itemResult) {
        thongke = itemResult;
//        if(itemResult.getSoCt().isEmpty()){
//            thongKeGiaoNhanHangPresenter.GetPhieuXuat(thongke.getSoCt());
//            itemResult.setTenTrangThai(thongke.getTenTrangThai());
//            itemResult.setMaTrangThai(thongke.getMaTrangThai());
//            itemResult.setMaTrangThaiCu(thongke.getMaTrangThaiCu());
//        }
//        condition.setTrangThai(thongke.getMaTrangThaiCu());
//        loadListPhieuGiaoHang();
//        hideProgressBar();
//        hideErrorView();
        Toast.makeText(getContext(), "Cập nhật trạng thái " + thongke.getTenTrangThai() + " thành công!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpdateTrangThaiPhieuXuatCuuLongError(String error) {
        CommonUtils.showMessageError(getActivity(), "Đã xảy ra lỗi không mong muốn");
    }

    @Override
    public void onUpdateTrangThaiPhieuDatHangCuuLongSuccess(PhieuDatHangInfo itemResult) {

    }

    @Override
    public void onUpdateTrangThaiPhieuDatHangCuuLongError(String error) {

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Để tiếp tục, vui lòng bật chức năng xác định vị trí.")
                .setCancelable(false)
                .setTitle("VỊ TRÍ")
                .setPositiveButton("CÀI ĐẶT", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onGetPhieuXuatSuccess(PhieuXuatInfo phieuXuatResult) {
        phieuXuatResult.setSoCt(thongke.getSoCt());
    }

    @Override
    public void onGetPhieuXuatError(String error) {

    }

    @Override
    public void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult) {
        phieuXuatResult.setSoCt(thongke.getSoCt());
    }

    @Override
    public void onGetPhieuXuatCuuLongError(String error) {

    }

    @Override
    public void onGetListTrangThaiSuccess(List<TrangThaiLoaiPhieuInfo> listResult) {
        menusTrangThai= listResult;
        //onBindData();

        adapterTrangThai = new TrangThaiAdapter(getContext(), menusTrangThai);

        spinnerTimKiem.setAdapter(adapterTrangThai);
        // spinnerTimKiem.setSelection(5);
        adapterTrangThai.notifyDataSetChanged();
        spinnerTimKiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // spinnerTimKiem.setSelection(position);

                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //loadListPhieuGiaoHang();
            }
        });
    }

    @Override
    public void onGetListTrangThaiError(String error) {

    }


    @Override
    public void onCapNhatToaDoKhachHangSuccess() {
        Toast.makeText(getContext(), "Lấy tọa độ thành công!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCapNhatToaDoKhachHangError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onGetSupplierSuccess(SupplierInfo itemResult) {
        Intent intent = new Intent(getActivity(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("SoCT", thongke.getSoCt());
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        bundle.putString("MaTrangThai", thongke.getMaTrangThai());
        bundle.putString("Solandi", "1");
        bundle.putString("database", thongke.getTenTaiXe());
        bundle.putBoolean("isView", false);
        if (itemResult != null) {
            bundle.putString("ViDo", itemResult.getX());
            bundle.putString("KinhDo", itemResult.getY());
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetSupplierError(String error) {
        Intent intent = new Intent(getActivity(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetSupplierCuuLongSuccess(SupplierInfo itemResult) {
        Intent intent = new Intent(getActivity(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("SoCT", thongke.getSoCt());
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        bundle.putString("MaTrangThai", thongke.getMaTrangThai());
        bundle.putString("Solandi", "1");
        bundle.putString("database", thongke.getTenTaiXe());
        bundle.putBoolean("isView", false);
        if (itemResult != null) {
            bundle.putString("ViDo", itemResult.getX());
            bundle.putString("KinhDo", itemResult.getY());
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onGetSupplierCuuLongError(String error) {
        Intent intent = new Intent(getActivity(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("DiaChi", thongke.getDiachigiaohang());
        intent.putExtras(bundle);
        startActivity(intent);
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


    @Override
    public void onCheckChangeLogSuccess(ChangeLogInfo changeLogInfo) {
        logInfo = changeLogInfo;
        logInfo.setLatestVersion(changeLogInfo.getLatestVersion());
        logInfo.setLatestVersionCode(changeLogInfo.getLatestVersionCode());
        logInfo.setUrl(changeLogInfo.getUrl());

        double vsName = Double.parseDouble(tvVersion.getText().toString());

        if(logInfo.getLatestVersion() > vsName){

            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("CẢNH BÁO")
                    .setMessage("Phiên bản phần mềm đã cũ, vui lòng cập nhật phiên bản mới để sử dụng")
                    .setCancelable(false)
                    .setPositiveButton("CẬP NHẬT", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            URL url = null;
                            try {
                                url = new URL(AppConstants.URL_UPDATE_FILE_APK);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            DownloadFileUpdateAplicationTask downloadFileUpdateAplicationTask = new DownloadFileUpdateAplicationTask(url);
                            downloadFileUpdateAplicationTask.execute();

                        }
                    })
                    .setCancelable(false);
            final AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void onCheckChangeLogError(String error) {

    }

    @Override
    public void onCapNhatThongTinPhieuGiaoHangSaiCuuLongSuccess() {
        Toast.makeText(getContext(), "Vui lòng bấm xem bản đồ để tiến hành cập nhật thông tin bị sai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCapNhatThongTinPhieuGiaoHangSaiCuuLongError(String error) {
        CommonUtils.showMessageError(getContext(), error);
    }

    @Override
    public void onCapNhatThongTinPhieuGiaoHangSaiSuccess() {
        Toast.makeText(getContext(), "Vui lòng bấm xem bản đồ để tiến hành cập nhật thông tin bị sai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCapNhatThongTinPhieuGiaoHangSaiError(String error) {
        CommonUtils.showMessageError(getContext(), error);
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

                default:
                    break;
            }
        }
    }

    private class DownloadFileUpdateAplicationTask extends AsyncTask<String, String, String> {

        URL url;

        public DownloadFileUpdateAplicationTask(URL url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                String newfileName = "TihaLoc.apk";
                URLConnection conn = url.openConnection();
                conn.connect();
                // getting file length
                int lenghtOfFile = conn.getContentLength();

                FileOutputStream fos;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    File newAPKFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), newfileName);
                    fos = new FileOutputStream(newAPKFile);
                }
                else {
//                    fos = getActivity().openFileOutput(newfileName, MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
                    fos = getActivity().openFileOutput(newfileName,Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                }
                long total = 0;
                // Download the new APK file
                InputStream is = conn.getInputStream();//httpConn.getInputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                return e.getMessage();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int progress = Integer.parseInt(values[0]);
            progressBarDownload.setProgress(progress);
            tvProgress.setText(String.valueOf(progress) + "%");
            tvTotalProgress.setText(String.valueOf(progress) + "/100");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            showProgressDialog(false);
            if (result == null || result.isEmpty())
                installApk();
        }


    }

    private void installApk() {
        try {
            String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
            String fileName = "TihaLoc.apk";
            filePath += fileName;
            File file = new File(filePath);
            if (file.exists()) {
                String[] fileNameArray = file.getName().split(Pattern.quote("."));
                if (fileNameArray[fileNameArray.length - 1].equals("apk")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri downloaded_apk = getFileUri(file);
                        Intent intent = new Intent(Intent.ACTION_VIEW).setDataAndType(downloaded_apk,
                                "application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file),
                                "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Uri getFileUri(File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    ProgressBar progressBarDownload;
    TextView tvProgress;
    TextView tvTotalProgress;
    AlertDialog alertDialog;


    private void showProgressDialog(boolean isShow) {
        if (isShow) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_progressbar_download, null);
            progressBarDownload = view.findViewById(R.id.progressBar);
            tvProgress = view.findViewById(R.id.tvProgress);
            tvTotalProgress = view.findViewById(R.id.tvTotalProgress);
            alertDialog = new AlertDialog.Builder(getContext())
                    .setTitle("Please Wait...")
                    .setCancelable(false)
                    .setView(view)
                    .create();
            alertDialog.show();

        } else {
            alertDialog.dismiss();
        }
    }

}