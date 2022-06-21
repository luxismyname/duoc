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
import com.bongmai.tiha.data.entities.DoanhSoKhachHangInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class DoanhSoBanHangTheoKhachHangRecyclerViewAdapter extends RecyclerView.Adapter<DoanhSoBanHangTheoKhachHangRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DoanhSoKhachHangInfo doanhSoKhachHangInfo, View view, int position);

        void onItemLongClick(DoanhSoKhachHangInfo doanhSoKhachHangInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    private BaseRecyclerViewEvent.OnClickListener clickListener;

    List<DoanhSoKhachHangInfo> listAllData;
    List<DoanhSoKhachHangInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DoanhSoKhachHangInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public DoanhSoBanHangTheoKhachHangRecyclerViewAdapter(Context context, List<DoanhSoKhachHangInfo> listAllData) {
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

    public BaseRecyclerViewEvent.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
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

    public List<DoanhSoKhachHangInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doanhsobanhangtheokhachhang, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvKhachHang;
        private TextView tvSoLuong;
        private TextView tvThanhTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvKhachHang = (TextView) view.findViewById(R.id.tvKhachHang);
            this.tvSoLuong = (TextView) view.findViewById(R.id.colSoLuong);
            this.tvThanhTien = (TextView) view.findViewById(R.id.tvThanhTien);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        clickListener.onClick( v, position);
                    }
                }
            });

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (itemClickListener != null) {
//                        itemClickListener.onItemClick(listSearchData.get(getAdapterPosition()), v, getAdapterPosition());
//                    }
//                }
//            });

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
        itemRowHolder.tvKhachHang.setText(itemData.getCompanyName());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(itemData.getSoLuong()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(itemData.getThanhTien()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DoanhSoKhachHangInfo> resultList = new ArrayList<>();
            String timKiem;
            for (DoanhSoKhachHangInfo item : listAllData) {
                timKiem = item.getCompanyName() + " " + String.valueOf(item.getSoLuong()) + " " + String.valueOf(item.getThanhTien());
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
            listSearchData = (ArrayList<DoanhSoKhachHangInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}