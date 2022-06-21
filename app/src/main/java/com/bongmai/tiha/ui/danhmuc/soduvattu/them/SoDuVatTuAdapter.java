package com.bongmai.tiha.ui.danhmuc.soduvattu.them;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat.PhieuBanSiChiTietPhieuXuatPresenter;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.ui.danhmuc.soduvattu.them.ThemSoDuVatTuActivityContract;
import com.bongmai.tiha.ui.danhmuc.soduvattu.them.ThemSoDuVatTuActivityPresenter;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SoDuVatTuAdapter extends RecyclerView.Adapter<SoDuVatTuAdapter.ItemRowHolder> {


    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;



    List<ProductInfo> listAllData;
    List<ProductInfo> listSearchData;
    Context mContext;
    ThemSoDuVatTuActivityPresenter soDuVatTuPresenter;

    public SoDuVatTuAdapter(Context context, List<ProductInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.soDuVatTuPresenter = new ThemSoDuVatTuActivityPresenter(null);
    }

    public void setOnDataSetChangedListener(BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener) {
        this.dataSetChangedListener = dataSetChangedListener;
        dataSetChangedListener.onDataSetChanged();
    }

    public void setOnClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnLongClickListener(BaseRecyclerViewEvent.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnButtonClickListener(BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != listSearchData ? listSearchData.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ProductInfo getItem(int position) {
        return listSearchData.get(position);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<ProductInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        if (listSearchData.size() == 0) return;
        listSearchData.remove(position);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void addItem(ProductInfo ProductInfo) {
        listSearchData.add(ProductInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void addItem(int position, ProductInfo ProductInfo) {
        listSearchData.add(position, ProductInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void updateItem(int position, ProductInfo ProductInfo) {
        if (position < 0) return;
        listSearchData.remove(position);
        listSearchData.add(position, ProductInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void setData(List<ProductInfo> ProductInfos) {
        listSearchData = ProductInfos;
        listAllData = ProductInfos;
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    ItemRowHolder itemRowHolder;

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_soduvattudau, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvMaSanPham,
                tvTenSanPham,
                tvDVT,
                tvDVT2,
                tvDVT3,
                tvDVT4,
                tvDonggoi,
                tvDonggoi2,
                tvDonggoi3,
                tvDonggoi4,
                tvMSK,
                tvTenKho;
        private static final int REQUEST_SANPHAM = 1;
        public ItemRowHolder(View view) {
            super(view);
            this.tvMaSanPham = view.findViewById(R.id.tvMaSanPham);
            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvDVT = (TextView) view.findViewById(R.id.tvDVT);
            this.tvDVT2 = view.findViewById(R.id.tvDVT2);
            this.tvDVT3 = view.findViewById(R.id.tvDVT3);
            this.tvDonggoi = view.findViewById(R.id.tvDonggoi);
            this.tvDonggoi2 = view.findViewById(R.id.tvDonggoi2);
            this.tvDonggoi3 = view.findViewById(R.id.tvDonggoi3);
            this.tvDonggoi4 = view.findViewById(R.id.tvDonggoi4);
            this.tvMSK = view.findViewById(R.id.tvMSK);
            this.tvTenKho = view.findViewById(R.id.tvTenKho);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClick(v, getAdapterPosition());
                    }


                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        longClickListener.onLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        final ProductInfo productInfo = listSearchData.get(position);
        if (TextUtils.isEmpty(productInfo.getProduct_Name())) {
            soDuVatTuPresenter.GetProduct(productInfo.getProduct_ID(), new ThemSoDuVatTuActivityContract.Presenter.IOnGetProductFinishedListener() {
                @Override
                public void onSuccess(ProductInfo itemResult) {
                    itemRowHolder.tvTenSanPham.setText(itemResult.getProduct_Name());
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(mContext, "Lấy thông tin sản phẩm lỗi. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            itemRowHolder.tvTenSanPham.setText(productInfo.getProduct_Name());
        }

        itemRowHolder.tvDVT.setText(productInfo.getDonVitinh());
        itemRowHolder.tvDVT2.setText(productInfo.getDvt2());
        itemRowHolder.tvDVT3.setText(productInfo.getDvt3());
        itemRowHolder.tvDVT4.setText(productInfo.getDVT4());
        itemRowHolder.tvDonggoi.setText(String.valueOf(productInfo.getDonggoi()));
        itemRowHolder.tvDonggoi2.setText(String.valueOf(productInfo.getDonggoi2()));
        itemRowHolder.tvDonggoi3.setText(String.valueOf(productInfo.getDongGoi3()));
        itemRowHolder.tvDonggoi4.setText(String.valueOf(productInfo.getDongGoi4()));



    }



}