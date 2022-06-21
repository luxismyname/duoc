package com.bongmai.tiha.ui.baocao.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.HangBanChayInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.LetterTileProvider;

import java.util.List;


public class HangBanChayAdapter extends RecyclerView.Adapter<HangBanChayAdapter.ItemRowHolder>  {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<HangBanChayInfo> listAllData;
    List<HangBanChayInfo> listSearchData;
    Context mContext;
    private LetterTileProvider mLetterTileProvider;

    public HangBanChayAdapter(Context context, List<HangBanChayInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;

        try {
            this.mLetterTileProvider = new LetterTileProvider(context);
        } catch (Exception e) {

        }
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

    public HangBanChayInfo getItem(int position) {
        return listSearchData.get(position);
    }

    public List<HangBanChayInfo> getListAllData() {
        return listSearchData;
    }

    public void addItem(HangBanChayInfo item) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hangbanchay, viewGroup, false);
        ItemRowHolder itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvTenSanPham,
                tvGiaTri;
        ImageView imgSTT;


        public ItemRowHolder(View view) {
            super(view);

            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvGiaTri = (TextView) view.findViewById(R.id.tvGiaTri);
            this.imgSTT = (ImageView) view.findViewById(R.id.imgSTT);
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
        HangBanChayInfo hangBanChayInfo = listSearchData.get(position);
        itemRowHolder.tvTenSanPham.setText(hangBanChayInfo.getTenSanPham());
        itemRowHolder.tvGiaTri.setText(AppUtils.formatNumber("N0").format(hangBanChayInfo.getGiaTri()));
        try {
            itemRowHolder.imgSTT.setImageBitmap(mLetterTileProvider.getLetterTileSTTHangBanChay(hangBanChayInfo.getSTT()));
        } catch (Exception e) {

        }
    }



}