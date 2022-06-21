package com.bongmai.tiha.ui.baocao.banhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DoanhSoChiNhanhInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class DoanhSoBanHangTheoChiNhanhRecyclerViewAdapter extends RecyclerView.Adapter<DoanhSoBanHangTheoChiNhanhRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DoanhSoChiNhanhInfo doanhSoChiNhanhInfo, View view, int position);

        void onItemLongClick(DoanhSoChiNhanhInfo doanhSoChiNhanhInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<DoanhSoChiNhanhInfo> listAllData;
    List<DoanhSoChiNhanhInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DoanhSoChiNhanhInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public DoanhSoBanHangTheoChiNhanhRecyclerViewAdapter(Context context, List<DoanhSoChiNhanhInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
    }

    public void DataSourceChanged(DataSourceChangedListener listener) {
        this.dataSourceChangedListener = listener;
        listener.onDataSourceChanged();
    }

    public void ItemClick(ItemClickListener listener) {
        this.itemClickListener = listener;
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

    public List<DoanhSoChiNhanhInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    ItemRowHolder itemRowHolder;

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doanhsobanhangtheochinhanh, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvChiNhanh;
        private TextView tvSoLuong;
        private TextView tvThanhTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvChiNhanh = (TextView) view.findViewById(R.id.colChiNhanh);
            this.tvSoLuong = (TextView) view.findViewById(R.id.colSoLuong);
            this.tvThanhTien = (TextView) view.findViewById(R.id.tvThanhTien);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(listSearchData.get(getAdapterPosition()), v, getAdapterPosition());
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemLongClick(listSearchData.get(getAdapterPosition()), v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        itemData = listSearchData.get(position);
        itemRowHolder.tvChiNhanh.setText(itemData.getTenChiNhanh());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(itemData.getSoLuong()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(itemData.getThanhTien()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DoanhSoChiNhanhInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (DoanhSoChiNhanhInfo item : listAllData) {
                timKiem = item.getTenChiNhanh() + " " + String.valueOf(item.getSoLuong()) + " " + String.valueOf(item.getThanhTien());
                if (!timKiem.isEmpty()) {
                    if (timKiem.toUpperCase().contains(filterString)) {
                        resultList.add(item);
                    }
                }
            }
            filterResults.values = resultList;
            filterResults.count = resultList.size();
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listSearchData = (ArrayList<DoanhSoChiNhanhInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}