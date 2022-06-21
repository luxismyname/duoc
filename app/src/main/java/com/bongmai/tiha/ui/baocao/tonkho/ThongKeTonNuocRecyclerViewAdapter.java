package com.bongmai.tiha.ui.baocao.tonkho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DKNhatXuatNuocALLVoRongInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class ThongKeTonNuocRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeTonNuocRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DKNhatXuatNuocALLVoRongInfo itemTonKho, View view, int position);

        void onItemLongClick(DKNhatXuatNuocALLVoRongInfo itemTonKho, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<DKNhatXuatNuocALLVoRongInfo> listAllData;
    List<DKNhatXuatNuocALLVoRongInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DKNhatXuatNuocALLVoRongInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public ThongKeTonNuocRecyclerViewAdapter(Context context, List<DKNhatXuatNuocALLVoRongInfo> listAllData) {
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

    public List<DKNhatXuatNuocALLVoRongInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_thongketonnuoc, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvMaHang;
        private TextView tvTenHang;
        private TextView tvDauKy;
        private TextView tvNhap;
        private TextView tvXuat;
        private TextView tvChuyenDen;
        private TextView tvChuyenDi;
        private TextView tvCuoiKy;

        public ItemRowHolder(View view) {
            super(view);
            this.tvMaHang = (TextView) view.findViewById(R.id.tvLoaiPhieu);
            this.tvTenHang = (TextView) view.findViewById(R.id.tvSoPhieu);
            this.tvDauKy = (TextView) view.findViewById(R.id.tvDauKy);
            this.tvNhap = (TextView) view.findViewById(R.id.tvNhap);
            this.tvXuat = (TextView) view.findViewById(R.id.tvXuat);
            this.tvChuyenDen = (TextView) view.findViewById(R.id.tvChuyenDen);
            this.tvChuyenDi = (TextView) view.findViewById(R.id.tvChuyenDi);
            this.tvCuoiKy = (TextView) view.findViewById(R.id.tvCuoiKy);

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
        itemRowHolder.tvMaHang.setText(itemData.getMaNuoc());
        itemRowHolder.tvTenHang.setText(itemData.getTenNuoc());
        itemRowHolder.tvDauKy.setText(AppUtils.formatNumber("N0").format(itemData.getSLDK_N()));
        itemRowHolder.tvNhap.setText(AppUtils.formatNumber("N0").format(itemData.getNhap_N()));
        itemRowHolder.tvXuat.setText(AppUtils.formatNumber("N0").format(itemData.getXuat_N()));
        itemRowHolder.tvChuyenDen.setText(AppUtils.formatNumber("N0").format(itemData.getCDen_N()));
        itemRowHolder.tvChuyenDi.setText(AppUtils.formatNumber("N0").format(itemData.getCdi_N()));
        itemRowHolder.tvCuoiKy.setText(AppUtils.formatNumber("N0").format(itemData.getTon_N()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DKNhatXuatNuocALLVoRongInfo> resultList = new ArrayList<>();
            String timKiem;
            for (DKNhatXuatNuocALLVoRongInfo item : listAllData) {
                timKiem = item.getMaNuoc()
                        +" " + item.getTenNuoc();

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
            listSearchData = (ArrayList<DKNhatXuatNuocALLVoRongInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}