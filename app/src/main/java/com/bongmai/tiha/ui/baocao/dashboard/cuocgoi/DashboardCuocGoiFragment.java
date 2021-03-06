package com.bongmai.tiha.ui.baocao.dashboard.cuocgoi;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DashboardCuocGoiInfo;
import com.bongmai.tiha.data.entities.DashboardInfo;
import com.bongmai.tiha.data.entities.DateReportInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;
import com.bongmai.tiha.data.entities.HangBanChayInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.dashboard.HangBanChayAdapter;
import com.bongmai.tiha.ui.base.BaseFragment;

import com.bongmai.tiha.ui.customcontrol.NhapThoiGianBaoCaoDialog;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;

public class DashboardCuocGoiFragment extends Fragment implements BaseFragment, DashboardCuocGoiContract.View, View.OnClickListener {

    SwipeRefreshLayout swipe_refresh;

    List<HangBanChayInfo> listData;


    HangBanChayAdapter adapterListData;


    RecyclerView rvHangBanChay;


    boolean isShowProgressBar;

    private ColumnChartView chartTongCuocGoi;
    private ColumnChartData data;
    BaseService service;
    DieuKienLocInfo dieuKienLoc;

    TextView
            tvThoiGianXem,
            tvXemTuNgayDenNgay,
            tvTongCuocGoi,
            tvTongDoanhThu,
            tvTongTienThu,
            tvTongTienNo,
            tvSLBinhXuat,
            tvSLVoVe,
            tvTongCuocGoiDen,
            tvTongCuocGoiDi,
            tvTongCuocGoiNhapTay,
            tvTongCuocGoiNho,
            tvTongCuocGoiCoBan,
            tvTongCuocGoiKhongBan;
    View cvNgayXem;

    List<String> listThoiGianXem;

    DashboardCuocGoiPresenter dashboardPresenter;

    public static final int COLOR_CHART = Color.parseColor("#5f8b95");
    public static final int COLOR_TITLE_CHART = Color.parseColor("#000000");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_cuocgoi, container, false);
        onInit(view);
        onLoadData();
        return view;
    }

    public void onInit(View view) {
        cvNgayXem = (View) view.findViewById(R.id.cvNgayXem);

        rvHangBanChay = (RecyclerView) view.findViewById(R.id.rvHangBanChay);


        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);


        chartTongCuocGoi = (ColumnChartView) view.findViewById(R.id.chartTongCuocGoi);

        tvThoiGianXem = (TextView) view.findViewById(R.id.tvThoiGianXem);
        tvXemTuNgayDenNgay = (TextView) view.findViewById(R.id.tvXemTuNgayDenNgay);

        tvTongDoanhThu = (TextView) view.findViewById(R.id.tvTongDoanhThu);
        tvTongCuocGoi = (TextView) view.findViewById(R.id.tvTongCuocGoi);
        tvTongTienThu = (TextView) view.findViewById(R.id.tvTongTienThu);
        tvTongTienNo = (TextView) view.findViewById(R.id.tvTongTienNo);
        tvSLBinhXuat = (TextView) view.findViewById(R.id.tvSLBinhXuat);
        tvSLVoVe = (TextView) view.findViewById(R.id.tvSLVoVe);
        tvTongCuocGoiDen = (TextView) view.findViewById(R.id.tvTongCuocGoiDen);
        tvTongCuocGoiDi = (TextView) view.findViewById(R.id.tvTongCuocGoiDi);
        tvTongCuocGoiNhapTay = (TextView) view.findViewById(R.id.tvTongCuocGoiNhapTay);
        tvTongCuocGoiNho = (TextView) view.findViewById(R.id.tvTongCuocGoiNho);
        tvTongCuocGoiCoBan = (TextView) view.findViewById(R.id.tvTongCuocGoiCoBan);
        tvTongCuocGoiKhongBan = (TextView) view.findViewById(R.id.tvTongCuocGoiKhongBan);

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

        rvHangBanChay.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvHangBanChay.setLayoutManager(llm);
        rvHangBanChay.setItemAnimator(new DefaultItemAnimator());
        listData = new ArrayList<>();
    }

    public void onLoadData() {
        service = new BaseService(getContext());
        dashboardPresenter = new DashboardCuocGoiPresenter(this);


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
        dashboardPresenter.GetDashboardCuocGoi(dieuKienLoc);
    }

    @Override
    public void onGetDashboardCuocGoiSuccess(DashboardInfo itemResult) {
        //region Tong quan
        DashboardCuocGoiInfo cuocGoiInfo = itemResult.getItemCuocGoi();
        tvTongCuocGoi.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoi()));
        tvTongDoanhThu.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongDoanhThu()));
        tvTongTienThu.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongTienThu()));
        tvTongTienNo.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongTienNo()));
        tvSLBinhXuat.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getSLBinhXuat()));
        tvSLVoVe.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getSLVoVe()));
        tvTongCuocGoiDen.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiDen()));
        tvTongCuocGoiDi.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiDi()));
        tvTongCuocGoiNhapTay.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiNhapTay()));
        tvTongCuocGoiNho.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiNho()));
        tvTongCuocGoiCoBan.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiCoBan()));
        tvTongCuocGoiKhongBan.setText(AppUtils.formatNumber("N0").format(cuocGoiInfo.getTongCuocGoiKhongBan()));
        //endregion

        //region Bieu do tong cuoc goi
        int numSubcolumns = 1;
        int numColumns = itemResult.getListCuocGoi().size();

        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        List<AxisValue> axisValuesY = new ArrayList<AxisValue>();
        SubcolumnValue subcolumnValue;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                subcolumnValue = new SubcolumnValue((float) itemResult.getListCuocGoi().get(i).getTongCuocGoi(), COLOR_CHART);
                subcolumnValue.setLabel(AppUtils.formatNumber("N0").format(itemResult.getListCuocGoi().get(i).getTongCuocGoi()));
                values.add(subcolumnValue);

            }

            axisValuesX.add(new AxisValue(i)
                    .setLabel(itemResult.getListCuocGoi().get(i).getNgayStr()));

            axisValuesY.add(new AxisValue(i)
                    .setLabel(AppUtils.formatNumber("N0").format(itemResult.getListCuocGoi().get(i).getTongCuocGoi()))
                    .setValue((float) itemResult.getListCuocGoi().get(i).getTongCuocGoi()));

            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }

        data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis(axisValuesX).setHasLines(true));

        Axis axisY = new Axis();
        axisY.setHasLines(true);
        SimpleAxisValueFormatter formatter = new SimpleAxisValueFormatter();
        formatter.setDecimalSeparator('.');
        axisY.setFormatter(formatter);
        axisY.setMaxLabelChars(4);
        axisY.setHasTiltedLabels(false);
        data.setAxisYLeft(axisY);

//            data.getAxisYLeft().setTextSize(12);
        data.getAxisYLeft().setTextColor(COLOR_TITLE_CHART);

//            data.getAxisXBottom().setTextSize(12);
        data.getAxisXBottom().setTextColor(COLOR_TITLE_CHART);

        chartTongCuocGoi.setColumnChartData(data);
//
//        chart.setZoomEnabled(true);
//
//        chart.setZoomType(ZoomType.HORIZONTAL);


        Viewport v = new Viewport(chartTongCuocGoi.getMaximumViewport());
        v.left = -0.5f;
//                v.right = 5f;
        chartTongCuocGoi.setCurrentViewport(v);
        chartTongCuocGoi.setZoomEnabled(false);
        //endregion

        //region Hang ban chay
        adapterListData = new HangBanChayAdapter(getContext(), itemResult.getListHangBanChay());
        rvHangBanChay.setAdapter(adapterListData);
        adapterListData.notifyDataSetChanged();
        //endregion
    }

    @Override
    public void onGetDashboardCuocGoiError(String error) {
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
        builder.setTitle("TH???I GIAN XEM");
        builder.setPositiveButton("H???Y B???", new DialogInterface.OnClickListener() {
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
                    NhapThoiGianBaoCaoDialog.getInstance().showConfirmDialog(dieuKienLoc.getTuNgay(), dieuKienLoc.getDenNgay(), getContext(), new NhapThoiGianBaoCaoDialog.DialogClickInterface() {
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
