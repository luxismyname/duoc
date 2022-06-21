package com.bongmai.tiha.ui.baocao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.MobileMauInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;

import java.util.List;


public class BaoCaoListAdapter extends RecyclerView.Adapter<BaoCaoListAdapter.ItemRowHolder>  {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;


    List<MobileMauInfo> listAllData;
    Context mContext;

    public BaoCaoListAdapter(Context context, List<MobileMauInfo> listAllData)
    {
        this.mContext = context;
        this.listAllData = listAllData;
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public MobileMauInfo getItem(int position) {
        return listAllData.get(position);
    }

    public List<MobileMauInfo> getListAllData() {
        return listAllData;
    }

    public void addItem(MobileMauInfo item) {
        listAllData.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        listAllData.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_baocaolist, viewGroup, false);
        ItemRowHolder itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgHinhAnh;


        public ItemRowHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.imgHinhAnh = (ImageView) view.findViewById(R.id.imgHinhAnh);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        clickListener.onClick( v, position);
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
        MobileMauInfo reportInfo = listAllData.get(position);
        itemRowHolder.tvName.setText(reportInfo.getTenMau());
        itemRowHolder.imgHinhAnh.setImageResource(reportInfo.getIDIcon());
    }



}