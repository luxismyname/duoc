package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.utils.NetworkUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhieuBanSiChiTietPhieuXuatFragment extends Fragment implements BaseFragment, PhieuBanSiChiTietPhieuXuatContract.View {

    List<VattuxuatInfo> listChiTietXuat;
    PhieuBanSiChiTietPhieuXuatPresenter presenter;
    PhieuBanSiChiTietPhieuXuatAdapter adapter;
    RecyclerView rvData;
    VattuxuatInfo vattuxuatInfo;


    public static PhieuBanSiChiTietPhieuXuatFragment newInstance(List<VattuxuatInfo> listData) { //List<VattuxuatInfo> listData
        PhieuBanSiChiTietPhieuXuatFragment fragment = new PhieuBanSiChiTietPhieuXuatFragment();
        Bundle args = new Bundle();
        args.putSerializable("listVattuxuat", (Serializable) listData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listChiTietXuat = (getArguments() != null) ? (List<VattuxuatInfo>) getArguments().getSerializable("listVattuxuat") : new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieubansi_detail_chitietphieuxuat, container, false);
        onInit(view);
        onLoadData();
        return view;
    }

    @Override
    public void onInit(View view) {
        rvData = view.findViewById(R.id.rvData);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(llm);
        rvData.setHasFixedSize(true);

        adapter = new PhieuBanSiChiTietPhieuXuatAdapter(getContext(), listChiTietXuat); //cần cái qq này mới show ra được, đume tau muốn chửi thề!!!
        rvData.setItemAnimator(new DefaultItemAnimator());
        rvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onLoadData() {


        presenter = new PhieuBanSiChiTietPhieuXuatPresenter(this);
        adapter = new PhieuBanSiChiTietPhieuXuatAdapter(getContext(), new ArrayList<>());

    }

    @Override
    public void onGetListVattuXuatSuccess(List<VattuxuatInfo> list) {
         this.listChiTietXuat = list;
        adapter.setListAllData(list == null ? new ArrayList<VattuxuatInfo>() : list);

    }

    @Override
    public void onGetListVattuXuatError(String error) {
        error = (error == null || error.isEmpty()) ? getContext().getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(getContext()))
            error = getContext().getResources().getString(R.string.error_msg_no_internet);

    }

    @Override
    public void onGetProductSuccess(ProductInfo itemResult) {

    }

    @Override
    public void onGetProductError(String error) {

    }
}




