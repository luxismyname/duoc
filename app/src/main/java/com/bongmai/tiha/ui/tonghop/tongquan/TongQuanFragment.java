package com.bongmai.tiha.ui.tonghop.tongquan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bongmai.tiha.BuildConfig;
import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.data.entities.DashboardDoanhThuInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.HangBanChayInfo;
import com.bongmai.tiha.data.entities.HangCoXuatInfo;
import com.bongmai.tiha.data.entities.TonKhoInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.banhang.DoanhSoBanHangTheoKhachHangActivity;
import com.bongmai.tiha.ui.baocao.banhang.NhatKyBanHangActivity;
import com.bongmai.tiha.ui.baocao.dashboard.HangBanChayAdapter;
import com.bongmai.tiha.ui.baocao.dashboard.HangCoXuatAdapter;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;


public class TongQuanFragment extends Fragment implements BaseFragment, TongQuanContract.View, View.OnClickListener {

    SwipeRefreshLayout swipe_refresh;
    TextView tvVersion;
    String versionInfo = "";
    List<HangBanChayInfo> listData;
    List<TonKhoInfo> listHangCoXuat;
    CardView cvBieuDoDoanhThu;
    HangBanChayAdapter adapterListData;

    HangCoXuatAdapter adapterHangCoXuat;
    ProgressBar progressBar;

    ChangeLogInfo logInfo;
    RecyclerView rvHangBanChay;
    RecyclerView rvHangCoXuat;
    ConstraintLayout cltHangbanchay;
    boolean isShowProgressBar;
    String maNhomBaoCao = "";
    private ColumnChartView chart;
    private ColumnChartData data;
    BaseService service;
    DieuKienLocInfo dieuKienLoc;
    TextView
            tvTongDoanhThu,
            tvTongDonHang,
            tvTongLoiNhuan,
            tvTongKhachHang,
            tvTongTienMat,
            tvTongTienNganHang,
            tvSlTonKho,
            tvGiaTriTonKho,
            tvThoiGianXem,
            tvXemTuNgayDenNgay,
            tvTongCuoiKyHangCoXuat,
            tvTongPSXuatHangCoXuat,
            tvTongSoDongHangCoXuat;
    View cvNgayXem;
    CardView cvDoanhThuTheoNgay,
            cvTongDonHangTheoNgay,
            cvLaiGopTheoNgay,
            cvLuotKhachTheoNgay,
            cvQuyTienMatTheoNgay,
            cvQuyNganHangTheoNgay;

    List<String> listThoiGianXem;

    TongQuanPresenter dashboardPresenter;


    public static final int COLOR_CHART = Color.parseColor("#5f8b95");
    public static final int COLOR_TITLE_CHART = Color.parseColor("#000000");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_doanhthu, container, false);
        onInit(view);
        onLoadData();
        return view;
    }

    public void onInit(View view) {
        cvNgayXem = (View) view.findViewById(R.id.cvNgayXem);

        rvHangBanChay = (RecyclerView) view.findViewById(R.id.rvHangBanChay);
        rvHangCoXuat = (RecyclerView) view.findViewById(R.id.rvHangCoXuat);
        cltHangbanchay = view.findViewById(R.id.ctlHangBanChay);
        cvBieuDoDoanhThu = view.findViewById(R.id.cvBieuDoDoanhThu);

        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        chart = (ColumnChartView) view.findViewById(R.id.chart);

        tvVersion = view.findViewById(R.id.tvVersion);
        tvTongDoanhThu = (TextView) view.findViewById(R.id.tvTongDoanhThu);
        tvTongDonHang = (TextView) view.findViewById(R.id.tvTongDonHang);
        tvTongLoiNhuan = (TextView) view.findViewById(R.id.tvTongLoiNhuan);
        tvTongKhachHang = (TextView) view.findViewById(R.id.tvTongKhachHang);
        tvTongTienMat = (TextView) view.findViewById(R.id.tvTongTienMat);
        tvTongTienNganHang = (TextView) view.findViewById(R.id.tvTongTienNganHang);
        tvSlTonKho = (TextView) view.findViewById(R.id.tvSlTonKho);
        tvGiaTriTonKho = (TextView) view.findViewById(R.id.tvGiaTriTonKho);
        tvThoiGianXem = (TextView) view.findViewById(R.id.tvThoiGianXem);
        tvXemTuNgayDenNgay = (TextView) view.findViewById(R.id.tvXemTuNgayDenNgay);
        tvTongCuoiKyHangCoXuat = (TextView) view.findViewById(R.id.tvTongCuoiKyHangCoXuat);
        tvTongPSXuatHangCoXuat = (TextView) view.findViewById(R.id.tvTongPSXuatHangCoXuat);
        tvTongSoDongHangCoXuat = (TextView) view.findViewById(R.id.tvTongSoDongHangCoXuat);

        cvDoanhThuTheoNgay = view.findViewById(R.id.cvTongDoanhThu);
        cvTongDonHangTheoNgay = view.findViewById(R.id.cvTongDonHang);
        cvLaiGopTheoNgay = view.findViewById(R.id.cvTongLoiNhuan);
        cvLuotKhachTheoNgay = view.findViewById(R.id.cvTongKhachHang);
        cvQuyTienMatTheoNgay = view.findViewById(R.id.cvTongTienMat);
        cvQuyNganHangTheoNgay = view.findViewById(R.id.cvTongTienNganHang);

        cvDoanhThuTheoNgay.setOnClickListener(this);
        cvTongDonHangTheoNgay.setOnClickListener(this);
        cvLaiGopTheoNgay.setOnClickListener(this);
        cvLuotKhachTheoNgay.setOnClickListener(this);
        cvQuyTienMatTheoNgay.setOnClickListener(this);
        cvQuyNganHangTheoNgay.setOnClickListener(this);

        cvNgayXem.setOnClickListener(this);

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isShowProgressBar = false;
                loadDashboard();
                hideSwipeRefresh();
            }
        });
        cvBieuDoDoanhThu.setVisibility(View.GONE);
        cltHangbanchay.setVisibility(View.GONE);



        Context context = getContext();
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

        try {
            versionInfo = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvVersion.setText(versionInfo);


        rvHangBanChay.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvHangBanChay.setLayoutManager(llm);
        rvHangBanChay.setItemAnimator(new DefaultItemAnimator());
        listData = new ArrayList<>();


        rvHangCoXuat.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(getContext());
        rvHangCoXuat.setLayoutManager(llm1);
        rvHangCoXuat.setItemAnimator(new DefaultItemAnimator());
        listHangCoXuat = new ArrayList<>();






    }

    public void onLoadData() {
        service = new BaseService(getContext());
        dashboardPresenter = new TongQuanPresenter(this);
        logInfo = new ChangeLogInfo();
        dashboardPresenter.CheckChangeLog();

        listThoiGianXem = DateReportInfo.GetListDateReport();
        dieuKienLoc = new DieuKienLocInfo();
        DateReportInfo dateReportInfo = DateReportInfo.GetDateReport(DateReportInfo.HomNay);
        dieuKienLoc.setTuNgay(dateReportInfo.StartDate);
        dieuKienLoc.setDenNgay(dateReportInfo.EndDate);
        dieuKienLoc.setListChiNhanh(PublicVariables.listChiNhanhByUserStr);
        tvThoiGianXem.setText(dateReportInfo.Name);
        tvXemTuNgayDenNgay.setText(dateReportInfo.StartDate + " - " + dateReportInfo.EndDate);


//        cvBieuDoDoanhThu.setVisibility(View.GONE);

        loadDashboard();


    }

    private void loadDashboard() {
        dashboardPresenter.GetDashboardDoanhThu(dieuKienLoc);
    }

    @Override
    public void onGetDashboardDoanhThuSuccess(DashboardInfo itemResult) {
        if (getContext() == null) return;
        //region Tong quan
        DashboardDoanhThuInfo doanhThuInfo = itemResult.getItemDoanhThu();
        tvTongDoanhThu.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getDoanhthu()));
        tvTongDonHang.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getSoDonHang()));
        tvTongLoiNhuan.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getLaiGop()));
        tvTongKhachHang.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getLuotKhach()));
        tvTongTienMat.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getQuyTienMat()));
        tvTongTienNganHang.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getQuyNganHang()));
        tvSlTonKho.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getSoLuongTonKho()));
        tvGiaTriTonKho.setText(AppUtils.formatNumber("N0").format(doanhThuInfo.getGiaTriTonKho()));
        //endregion

        //region Bieu do doanh thu
        int numSubcolumns = 1;
        int numColumns = itemResult.getListDoanhThu().size();
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        List<AxisValue> axisValuesY = new ArrayList<AxisValue>();


        SubcolumnValue subcolumnValue;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                subcolumnValue = new SubcolumnValue((float) itemResult.getListDoanhThu().get(i).getDoanhthu() / 1000000, COLOR_CHART);
                subcolumnValue.setLabel(AppUtils.formatNumber("N0").format(itemResult.getListDoanhThu().get(i).getDoanhthu()));
                values.add(subcolumnValue);

            }

            axisValuesX.add(new AxisValue(i)
                    .setLabel(itemResult.getListDoanhThu().get(i).getNgayStr()));

            axisValuesY.add(new AxisValue(i)
                    .setLabel(AppUtils.formatNumber("N0").format(itemResult.getListDoanhThu().get(i).getDoanhthu() / 1000000)));
//                            .setValue((float) listDoanhThu.get(i).getDoanhthu()/1000000));

            Column column = new Column(values);
//                    column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }

        data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis(axisValuesX).setHasLines(true).setHasTiltedLabels(true));

        Axis axisY = new Axis();
        axisY.setHasLines(false);
        SimpleAxisValueFormatter formatter = new SimpleAxisValueFormatter();
        formatter.setDecimalSeparator('.');
        formatter.setAppendedText(" tr".toCharArray());
        axisY.setFormatter(formatter);
        axisY.setMaxLabelChars(6);
        //Nghieng 1 goc 45 do
        axisY.setHasTiltedLabels(false);
        data.setAxisYLeft(axisY);

//                data.getAxisYLeft().setTextSize(8);
        data.getAxisYLeft().setTextColor(COLOR_TITLE_CHART);

//                data.getAxisXBottom().setTextSize(8);
        data.getAxisXBottom().setTextColor(COLOR_TITLE_CHART);

        chart.setColumnChartData(data);
//
//        chart.setZoomEnabled(true);
//
//        chart.setZoomType(ZoomType.HORIZONTAL);


//                Viewport v = new Viewport(chart.getMaximumViewport());
//                v.left = -0.5f;
//                v.right = 3.5f;
//                chart.setCurrentViewport(v);
        chart.setZoomEnabled(true);
        chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        chart.setZoomLevel(0, 0, 0);


//                Viewport v = chart.getMaximumViewport();
//                v.set(v.left, 0, v.right, 0);
//                chart.setMaximumViewport(v);
//                chart.setCurrentViewport(v);


//                Viewport v = new Viewport(chart.getMaximumViewport());
//                v.top = 100;
//                v.bottom = 0;
//                chart.setMaximumViewport(v);
//                chart.setCurrentViewport(v);
//                chart.setViewportCalculationEnabled(false);

//                chart.setZoomEnabled(true);
//                chart.setZoomType(ZoomType.HORIZONTAL);
//                chart.setZoomLevel(0, 0, 0);
        //endregion

        //region Hang ban chay
        adapterListData = new HangBanChayAdapter(getContext(), itemResult.getListHangBanChay());
        rvHangBanChay.setAdapter(adapterListData);
        adapterListData.notifyDataSetChanged();
        //endregion

        //region Hang co xuat
        adapterHangCoXuat = new HangCoXuatAdapter(getContext(), itemResult.getListHangCoXuat());
        rvHangCoXuat.setAdapter(adapterHangCoXuat);
        adapterHangCoXuat.notifyDataSetChanged();

        double tongXuat = 0, tongTon = 0;
        for (HangCoXuatInfo item : adapterHangCoXuat.getListAllData()) {
            tongXuat += item.getXuat();
            tongTon += item.getTon();
        }
        tvTongPSXuatHangCoXuat.setText(AppUtils.formatNumber("N0").format(tongXuat));
        tvTongCuoiKyHangCoXuat.setText(AppUtils.formatNumber("N0").format(tongTon));
        tvTongSoDongHangCoXuat.setText(AppUtils.formatNumber("N0").format(adapterHangCoXuat.getListAllData() == null ? 0 : adapterHangCoXuat.getListAllData().size()));
        //endregion
    }

    @Override
    public void onGetDashboardDoanhThuError(String error) {
        if (getContext() == null) return;
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
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
                    .setNegativeButton(" ", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {

                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void onCheckChangeLogError(String error) {

    }


    private void hideSwipeRefresh() {
        try {
            swipe_refresh.setRefreshing(false);
            cvBieuDoDoanhThu.setVisibility(View.GONE);
            cltHangbanchay.setVisibility(View.GONE);
        } catch (Exception e) {

        }
    }


    @Override
    public void onClick(View v) {
        DateReportInfo dateReportInfo = new DateReportInfo();
        Intent intent;
        Bundle bundle = new Bundle();
        bundle.putSerializable("DieuKienLoc", dieuKienLoc);
        switch (v.getId()) {
            case R.id.cvNgayXem:
                showMenuThoiGianXem(v);
                break;
            case R.id.tvTongDoanhThu:
                break;
            case R.id.cvTongDonHang:
                dieuKienLoc.setTenMauBaoCao("Thống kê bán hàng");
                dieuKienLoc.setThoiGianXemBaoCao(tvThoiGianXem.getText().toString());
                intent = new Intent(getContext(), NhatKyBanHangActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.cvTongLoiNhuan:
                break;
            case R.id.cvTongKhachHang:
                dieuKienLoc.setTenMauBaoCao("Doanh số bán hàng theo khách hàng");
                dieuKienLoc.setThoiGianXemBaoCao(tvThoiGianXem.getText().toString());
                intent = new Intent(getContext(), DoanhSoBanHangTheoKhachHangActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.cvTongTienMat:
                break;
            case R.id.cvTongTienNganHang:
                break;
            default:
                break;
        }
    }

    public void showMenuThoiGianXem(View v) {

        final CharSequence[] items = listThoiGianXem.toArray(new CharSequence[listThoiGianXem.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("THỜI GIAN XEM");
        builder.setPositiveButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == DateReportInfo.LuaChonKhac ) {
                    dieuKienLoc.setTuNgay((dieuKienLoc.getTuNgayTuChon() == null) ? PublicVariables.NgayLamViec : dieuKienLoc.getTuNgayTuChon());
                    dieuKienLoc.setDenNgay((dieuKienLoc.getDenNgayTuChon() == null) ? PublicVariables.NgayLamViec : dieuKienLoc.getDenNgayTuChon());
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), getContext(), new NhapThoiGianBaoCaoDialog.DialogClickInterface() {
                        @Override
                        public void onClickPositiveButton(String tuNgay, String denNgay) {
                            dieuKienLoc.setTuNgay(tuNgay);
                            dieuKienLoc.setDenNgay(denNgay);
                            dieuKienLoc.setTuNgayTuChon(tuNgay);
                            dieuKienLoc.setDenNgayTuChon(denNgay);
                            tvThoiGianXem.setText(DateReportInfo.GetListDateReport().get(DateReportInfo.LuaChonKhac));
                            tvXemTuNgayDenNgay.setText(tuNgay + " - " + denNgay);
//                            loadDashboard();

                        }

                        @Override
                        public void onClickNegativeButton() {

                        }
                    });
                }
                else {
                    DateReportInfo dateReport = DateReportInfo.GetDateReport(position);
                    dieuKienLoc.setTuNgay(dateReport.StartDate);
                    dieuKienLoc.setDenNgay(dateReport.EndDate);
                    tvThoiGianXem.setText(dateReport.Name);

                    if(tvThoiGianXem.getText().toString().equals("Hôm nay")){
                        tvXemTuNgayDenNgay.setText(dateReport.StartDate + " - " + dateReport.EndDate);
                        loadDashboard();
                        cvBieuDoDoanhThu.setVisibility(View.GONE);
                        cltHangbanchay.setVisibility(View.GONE);
                    } else {
                        tvXemTuNgayDenNgay.setText(dateReport.StartDate + " - " + dateReport.EndDate);
                        loadDashboard();
                        cvBieuDoDoanhThu.setVisibility(View.VISIBLE);
                        cltHangbanchay.setVisibility(View.VISIBLE);
                    }

                }

//                else {
//                    DateReportInfo dateReport = DateReportInfo.GetDateReport(position);
//                    dieuKienLoc.setTuNgay(dateReport.StartDate);
//                    dieuKienLoc.setDenNgay(dateReport.EndDate);
//                    tvThoiGianXem.setText(dateReport.Name);
//                    tvXemTuNgayDenNgay.setText(dateReport.StartDate + " - " + dateReport.EndDate);
//                    loadDashboard();
//                }
                dialog.dismiss();
            }
        });
        builder.show();

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
