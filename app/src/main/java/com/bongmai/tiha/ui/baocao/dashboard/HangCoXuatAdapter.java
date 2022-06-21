package com.bongmai.tiha.ui.baocao.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.HangCoXuatInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.List;


public class HangCoXuatAdapter extends RecyclerView.Adapter<HangCoXuatAdapter.ItemRowHolder> {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<HangCoXuatInfo> listAllData;
    List<HangCoXuatInfo> listSearchData;
    Context mContext;


    public HangCoXuatAdapter(Context context, List<HangCoXuatInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;

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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public HangCoXuatInfo getItem(int position) {
        return listSearchData.get(position);
    }

    public List<HangCoXuatInfo> getListAllData() {
        return listSearchData;
    }

    public void addItem(HangCoXuatInfo item) {
        listSearchData.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hangcoxuat, viewGroup, false);
        ItemRowHolder itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvTenSanPham,
                tvXuat,
                tvCuoiKy;


        public ItemRowHolder(View view) {
            super(view);

            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvXuat = (TextView) view.findViewById(R.id.tvXuat);
            this.tvCuoiKy = (TextView) view.findViewById(R.id.tvCuoiKy);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        clickListener.onClick(v, position);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        longClickListener.onLongClick(v, position);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        HangCoXuatInfo tonKhoInfo = listSearchData.get(position);
        itemRowHolder.tvTenSanPham.setText(tonKhoInfo.getProduct_Name());
        itemRowHolder.tvXuat.setText(AppUtils.formatNumber("N0").format(tonKhoInfo.getXuat()));
        itemRowHolder.tvCuoiKy.setText(AppUtils.formatNumber("N0").format(tonKhoInfo.getTon()));
    }


}