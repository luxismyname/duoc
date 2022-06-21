package com.bongmai.tiha.ui.baocao.chitietbaocao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.MobileDetailMauInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;

import java.util.List;


public class ChiTietBaoCaoAdapter extends RecyclerView.Adapter<ChiTietBaoCaoAdapter.ItemRowHolder>  {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<MobileDetailMauInfo> listAllData;
    Context mContext;

    public ChiTietBaoCaoAdapter(Context context, List<MobileDetailMauInfo> listAllData) {
        this.listAllData = listAllData;
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
        return (null != listAllData ? listAllData.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public MobileDetailMauInfo getItem(int position) {
        return listAllData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<MobileDetailMauInfo> getListAllData() {
        return listAllData;
    }

    public void removeItem(int position) {
        listAllData.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    ItemRowHolder itemRowHolder;

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chitietmaubaocao, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvTenBaoCao;

        public ItemRowHolder(View view) {
            super(view);
            this.tvTenBaoCao = view.findViewById(R.id.tvTenBaoCao);

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
        MobileDetailMauInfo reportInfo = listAllData.get(position);
        itemRowHolder.tvTenBaoCao.setText(reportInfo.getTenDetailMau());
    }
}