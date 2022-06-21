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
import com.bongmai.tiha.data.entities.DoanhSoNhanVienInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class DoanhSoBanHangTheoNhanVienRecyclerViewAdapter extends RecyclerView.Adapter<DoanhSoBanHangTheoNhanVienRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DoanhSoNhanVienInfo doanhSoNhanVienInfo, View view, int position);

        void onItemLongClick(DoanhSoNhanVienInfo doanhSoNhanVienInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<DoanhSoNhanVienInfo> listAllData;
    List<DoanhSoNhanVienInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DoanhSoNhanVienInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public DoanhSoBanHangTheoNhanVienRecyclerViewAdapter(Context context, List<DoanhSoNhanVienInfo> listAllData) {
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

    public List<DoanhSoNhanVienInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doanhsobanhangtheonhanvien, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvNhanVien;
        private TextView tvSoLuong;
        private TextView tvThanhTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvNhanVien = (TextView) view.findViewById(R.id.tvNhanVien);
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
        itemRowHolder.tvNhanVien.setText(itemData.getEmployeeName());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(itemData.getSoLuong()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(itemData.getThanhTien()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DoanhSoNhanVienInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (DoanhSoNhanVienInfo item : listAllData) {
                timKiem = item.getEmployeeName() + " " + String.valueOf(item.getSoLuong()) + " " + String.valueOf(item.getThanhTien());
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
            listSearchData = (ArrayList<DoanhSoNhanVienInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}