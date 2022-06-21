package com.bongmai.tiha.ui.baocao.dashboard.doanhthu;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DashboardDoanhThuInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.HangBanChayInfo;
import com.bongmai.tiha.data.entities.HangCoXuatInfo;
import com.bongmai.tiha.data.entities.TonKhoInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.dashboard.HangBanChayAdapter;
import com.bongmai.tiha.ui.baocao.dashboard.HangCoXuatAdapter;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;


public class DashboardDoanhThuFragment extends Fragment implements BaseFragment, DashboardDoanhThuContract.View, View.OnClickListener {

    SwipeRefreshLayout swipe_refresh;

    List<HangBanChayInfo> listData;
    List<TonKhoInfo> listHangCoXuat;

    HangBanChayAdapter adapterListData;

    HangCoXuatAdapter adapterHangCoXuat;
    ProgressBar progressBar;

    RecyclerView rvHangBanChay;
    RecyclerView rvHangCoXuat;

    boolean isShowProgressBar;

    private ColumnChartView chart;
    private ColumnChartData data;
    BaseService service;
    DieuKienLocInfo dieuKienLoc;
    CardView cvDoanhthu, cvHangbanchay;
    ConstraintLayout ctlBieuDoDoanhThu, ctlHangbanchay;
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

    List<String> listThoiGianXem;

    DashboardDoanhThuPresenter dashboardPresenter;


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
        ctlBieuDoDoanhThu = view.findViewById(R.id.cltBieuDoDoanhThu);
        ctlHangbanchay = view.findViewById(R.id.ctlHangBanChay);
        rvHangBanChay = (RecyclerView) view.findViewById(R.id.rvHangBanChay);
        rvHangCoXuat = (RecyclerView) view.findViewById(R.id.rvHangCoXuat);
        cvDoanhthu = view.findViewById(R.id.cvBieuDoDoanhThu);
        cvHangbanchay = view.findViewById(R.id.cvHangBanChay);
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        chart = (ColumnChartView) view.findViewById(R.id.chart);

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

        cvNgayXem.setOnClickListener(this);
        ctlBieuDoDoanhThu.setVisibility(View.GONE);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isShowProgressBar = false;
                loadDashboard();
                hideSwipeRefresh();
            }
        });
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
        dashboardPresenter = new DashboardDoanhThuPresenter(this);

        listThoiGianXem = DateReportInfo.GetListDateReport();
        dieuKienLoc = new DieuKienLocInfo();
        DateReportInfo dateReportInfo = DateReportInfo.GetDateReport(DateReportInfo.HomNay);
        dieuKienLoc.setTuNgay(dateReportInfo.StartDate);
        dieuKienLoc.setDenNgay(dateReportInfo.EndDate);
        dieuKienLoc.setListChiNhanh(PublicVariables.listChiNhanhByUserStr);
        tvThoiGianXem.setText(dateReportInfo.Name);
        tvXemTuNgayDenNgay.setText(dateReportInfo.StartDate + " - " + dateReportInfo.EndDate);

            loadDashboard();

    }

    private void loadDashboard() {

        dashboardPresenter.GetDashboardDoanhThu(dieuKienLoc);
    }

    @Override
    public void onGetDashboardDoanhThuSuccess(DashboardInfo itemResult) {
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
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    private void hideSwipeRefresh() {
        try {
            swipe_refresh.setRefreshing(false);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvNgayXem:
                showMenuThoiGianXem(v);


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

                if (position == DateReportInfo.LuaChonKhac) {
                    dieuKienLoc.setTuNgay((dieuKienLoc.getTuNgayTuChon() == null) ? PublicVariables.NgayLamViec : dieuKienLoc.getTuNgayTuChon());
                    dieuKienLoc.setDenNgay((dieuKienLoc.getDenNgayTuChon() == null) ? PublicVariables.NgayLamViec : dieuKienLoc.getDenNgayTuChon());
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), getContext(),
                            new NhapThoiGianBaoCaoDialog.DialogClickInterface() {
                        @Override
                        public void onClickPositiveButton(String tuNgay, String denNgay) {
                            dieuKienLoc.setTuNgay(tuNgay);
                            dieuKienLoc.setDenNgay(denNgay);
                            dieuKienLoc.setTuNgayTuChon(tuNgay);
                            dieuKienLoc.setDenNgayTuChon(denNgay);
                            tvThoiGianXem.setText(DateReportInfo.GetListDateReport().get(DateReportInfo.LuaChonKhac));
                            tvXemTuNgayDenNgay.setText(tuNgay + " - " + denNgay);

                            loadDashboard();

                        }

                        @Override
                        public void onClickNegativeButton() {

                        }
                    });
                } else {
                    DateReportInfo dateReport = DateReportInfo.GetDateReport(position);
                    dieuKienLoc.setTuNgay(dateReport.StartDate);
                    dieuKienLoc.setDenNgay(dateReport.EndDate);
                    tvThoiGianXem.setText(dateReport.Name);
                    tvXemTuNgayDenNgay.setText(dateReport.StartDate + " - " + dateReport.EndDate);
                    loadDashboard();


                }
                dialog.dismiss();

            }
        });
        builder.show();

    }

}
