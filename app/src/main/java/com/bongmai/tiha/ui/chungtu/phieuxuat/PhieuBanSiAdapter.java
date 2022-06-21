package com.bongmai.tiha.ui.chungtu.phieuxuat;

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
import com.bongmai.tiha.data.entities.NhapSoLuongSanPhamInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.chitietphieuxuat.PhieuBanSiChiTietPhieuXuatPresenter;
import com.bongmai.tiha.ui.danhmuc.product.list.ProductListActivity;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PhieuBanSiAdapter extends RecyclerView.Adapter<PhieuBanSiAdapter.ItemRowHolder> {


    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;


    List<VattuxuatInfo> listAllData;
    List<VattuxuatInfo> listSearchData;
    Context mContext;
    PhieuBanSiChiTietPhieuXuatPresenter phieuBanSiPresenter;

    public PhieuBanSiAdapter(Context context, List<VattuxuatInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.phieuBanSiPresenter = new PhieuBanSiChiTietPhieuXuatPresenter(null);
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

    public VattuxuatInfo getItem(int position) {
        return listSearchData.get(position);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<VattuxuatInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        if (listSearchData.size() == 0) return;
        listSearchData.remove(position);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }



    public void addItem(VattuxuatInfo vattuxuatInfo) {
        listSearchData.add(vattuxuatInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void addItem(int position, VattuxuatInfo vattuxuatInfo) {
        listSearchData.add(position, vattuxuatInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }



    public void addAll(List<VattuxuatInfo> serviceReportResults) {
        for (VattuxuatInfo result : serviceReportResults) {
            listSearchData.add(result);
        }
        notifyDataSetChanged();
    }


    public void updateItem(int position, VattuxuatInfo vattuxuatInfo) {
        if (position < 0) return;
        listSearchData.remove(position);
        listSearchData.add(position, vattuxuatInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void setData(List<VattuxuatInfo> vattuxuatInfos) {
        listSearchData = vattuxuatInfos;
        listAllData = vattuxuatInfos;
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phieubansi_chitietphieuxuat, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvTenSanPham,
                tvDVT,
                tvSoLuong,
                tvGiaBan,
                tvDonGia,
                tvSoLuong1,
                tvThanhTien,
                tvGhiChu;
        ImageView btnMinus, btnPlus;
        private static final int REQUEST_SANPHAM = 1;
        public ItemRowHolder(View view) {
            super(view);
            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvDVT = (TextView) view.findViewById(R.id.tvDVT);
            this.tvSoLuong = (TextView) view.findViewById(R.id.tvSoLuong);
            this.tvGiaBan = (TextView) view.findViewById(R.id.tvGiaBan);
            this.tvDonGia = (TextView) view.findViewById(R.id.tvDonGia);
            this.tvSoLuong1 = (TextView) view.findViewById(R.id.tvSoLuong1);
            this.tvThanhTien = (TextView) view.findViewById(R.id.tvThanhTien);
            this.tvGhiChu = (TextView) view.findViewById(R.id.tvGhiChu);
            this.btnMinus = (ImageView) view.findViewById(R.id.btnMinus);
            this.btnPlus = (ImageView) view.findViewById(R.id.btnPlus);
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
        final VattuxuatInfo vattuxuatInfo = listSearchData.get(position);

        if (TextUtils.isEmpty(vattuxuatInfo.getProduct_Name())) {
            phieuBanSiPresenter.GetProduct(vattuxuatInfo.getProduct_ID(), new PhieuBanSiContract.Presenter.IOnGetProductFinishedListener() {
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
            itemRowHolder.tvTenSanPham.setText(vattuxuatInfo.getProduct_Name());
        }
        itemRowHolder.tvDVT.setText(vattuxuatInfo.getDvt());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL()));
        if (vattuxuatInfo.getGiatriDiscount() > 0) {
            itemRowHolder.tvDonGia.setVisibility(View.VISIBLE);
            itemRowHolder.tvGiaBan.setPaintFlags(itemRowHolder.tvGiaBan.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else
            itemRowHolder.tvDonGia.setVisibility(View.GONE);
        itemRowHolder.tvGiaBan.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getGiaban()) + " đ");
        itemRowHolder.tvDonGia.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getDongia()) + " đ");
        itemRowHolder.tvSoLuong1.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getSL()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(vattuxuatInfo.getThanh_Tien()) + " đ");
        if (TextUtils.isEmpty(vattuxuatInfo.getGhichu())) {
            itemRowHolder.tvGhiChu.setVisibility(View.GONE);
        } else {
            itemRowHolder.tvGhiChu.setText(vattuxuatInfo.getGhichu());
            itemRowHolder.tvGhiChu.setVisibility(View.VISIBLE);
        }




        itemRowHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClick(view, position);
                }
//                vattuxuatInfo.setSL(vattuxuatInfo.getSL() - 1);
//                if (vattuxuatInfo.getSL() == 0) {
//                    removeItem(position);
//                    return;
//                }
//                vattuxuatInfo.setThanh_Tien(vattuxuatInfo.getSL() * vattuxuatInfo.getDongia() * (1 + vattuxuatInfo.getThue()));
//                notifyDataSetChanged();
//                if (dataSetChangedListener != null)
//                    dataSetChangedListener.onDataSetChanged();

            }
        });

        itemRowHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClick(view, position);
                }
//                vattuxuatInfo.setSL(vattuxuatInfo.getSL() + 1);
//                vattuxuatInfo.setThanh_Tien(vattuxuatInfo.getSL() * vattuxuatInfo.getDongia() * (1 + vattuxuatInfo.getThue()));
//                notifyDataSetChanged();
//                if (dataSetChangedListener != null)
//                    dataSetChangedListener.onDataSetChanged();
            }
        });

    }



}