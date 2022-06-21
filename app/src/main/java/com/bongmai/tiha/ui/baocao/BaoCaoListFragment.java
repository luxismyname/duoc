package com.bongmai.tiha.ui.baocao;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.MobileMauInfo;
import com.bongmai.tiha.data.prefs.BaseService;
import com.bongmai.tiha.ui.baocao.chitietbaocao.ChiTietBaoCaoActivity;
import com.bongmai.tiha.ui.baocao.dashboard.DashboardActivity;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;

import java.util.ArrayList;
import java.util.List;

public class BaoCaoListFragment extends Fragment implements BaseFragment, BaoCaoListContract.View {
//    Toolbar toolbar;
    List<MobileMauInfo> listNhomBaoCao;
    BaseService service;
    RecyclerView rvReportList;
    BaoCaoListAdapter adapterReportList;

    BaoCaoListPresenter baoCaoPresenter;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new BaseService(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao, container, false);
        onInit(view);
        configToolbar();
        onLoadData();
        return view;
    }

    @Override
    public void onInit(View view) {
        rvReportList = (RecyclerView) view.findViewById(R.id.rvReportList);

        rvReportList.setHasFixedSize(true);
        int columns = 3;
        rvReportList.setLayoutManager(new GridLayoutManager(getContext(), columns));
        rvReportList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onLoadData() {
        baoCaoPresenter = new BaoCaoListPresenter(this);
        baoCaoPresenter.GetListNhomBaoCao();
    }



    private void configToolbar() {
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        String tieuDeForm = getActivity().getResources().getString(R.string.baocao_tieudeform);
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setTitle(tieuDeForm);
//        actionBar.setHomeAsUpIndicator(getActivity().getResources().getDrawable(R.drawable.ic_arrow_back));
    }


    @Override
    public void onGetListNhomBaoCaoSuccess(List<MobileMauInfo> listResult) {
        listNhomBaoCao = listResult;
        loadDataNhomBaoCao();
    }

    @Override
    public void onGetListNhomBaoCaoError(String error) {
        Toast.makeText(getContext(), "Lấy nhóm báo cáo thất bại. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
        listNhomBaoCao = new ArrayList<>();
        loadDataNhomBaoCao();
    }

    private void loadDataNhomBaoCao() {
        adapterReportList = new BaoCaoListAdapter(getContext(), listNhomBaoCao);
        adapterReportList.setOnClickListener(new BaseRecyclerViewEvent.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MobileMauInfo reportListInfo = adapterReportList.getItem(position);
                Intent intent;
                if (reportListInfo.getMaMau().equals("TONGQUAN")) {
                    intent = new Intent(getActivity(), DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), ChiTietBaoCaoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("MaNhomBaoCao", reportListInfo.getMaMau());
                    startActivity(intent);
                }
            }
        });
        rvReportList.setAdapter(adapterReportList);
    }


}
