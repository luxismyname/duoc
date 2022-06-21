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


public class ThongKeTonNuocVoRongRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeTonNuocVoRongRecyclerViewAdapter.ItemRowHolder> implements Filterable {

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

    public ThongKeTonNuocVoRongRecyclerViewAdapter(Context context, List<DKNhatXuatNuocALLVoRongInfo> listAllData) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_thongketonnuocvorong, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView
                tvMaHang,
                tvTenHang,
                tvDauKyNuoc,
                tvDauKyVo,
                tvNhapNuoc,
                tvNhapVo,
                tvXuatNuoc,
                tvXuatVo,
                tvChuyenDenNuoc,
                tvChuyenDenVo,
                tvChuyenDiNuoc,
                tvChuyenDiVo,
                tvCuoiKyNuoc,
                tvCuoiKyVo;

        public ItemRowHolder(View view) {
            super(view);
            this.tvMaHang = (TextView) view.findViewById(R.id.tvLoaiPhieu);
            this.tvTenHang = (TextView) view.findViewById(R.id.tvSoPhieu);
            this.tvDauKyNuoc = (TextView) view.findViewById(R.id.tvDauKyNuoc);
            this.tvDauKyVo = (TextView) view.findViewById(R.id.tvDauKyVo);
            this.tvNhapNuoc = (TextView) view.findViewById(R.id.tvNhapNuoc);
            this.tvNhapVo = (TextView) view.findViewById(R.id.tvNhapVo);
            this.tvXuatNuoc = (TextView) view.findViewById(R.id.tvXuatNuoc);
            this.tvXuatVo = (TextView) view.findViewById(R.id.tvXuatVo);
            this.tvChuyenDenNuoc = (TextView) view.findViewById(R.id.tvChuyenDenNuoc);
            this.tvChuyenDenVo = (TextView) view.findViewById(R.id.tvChuyenDenVo);
            this.tvChuyenDiNuoc = (TextView) view.findViewById(R.id.tvChuyenDiNuoc);
            this.tvChuyenDiVo = (TextView) view.findViewById(R.id.tvChuyenDiVo);
            this.tvCuoiKyNuoc = (TextView) view.findViewById(R.id.tvCuoiKyNuoc);
            this.tvCuoiKyVo = (TextView) view.findViewById(R.id.tvCuoiKyVo);

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
        itemRowHolder.tvMaHang.setText(itemData.getProductID());
        itemRowHolder.tvTenHang.setText(itemData.getProduct_Name());
        itemRowHolder.tvDauKyNuoc.setText(itemData.getSLDK_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getSLDK_N()));
        itemRowHolder.tvDauKyVo.setText(itemData.getSLDK_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getSLDK_V()));
        itemRowHolder.tvNhapNuoc.setText(itemData.getNhap_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getNhap_N()));
        itemRowHolder.tvNhapVo.setText(itemData.getNhap_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getNhap_V()));
        itemRowHolder.tvXuatNuoc.setText(itemData.getXuat_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getXuat_N()));
        itemRowHolder.tvXuatVo.setText(itemData.getXuat_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getXuat_V()));
        itemRowHolder.tvChuyenDenNuoc.setText(itemData.getCDen_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getCDen_N()));
        itemRowHolder.tvChuyenDenVo.setText(itemData.getCDen_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getCDen_V()));
        itemRowHolder.tvChuyenDiNuoc.setText(itemData.getCdi_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getCdi_N()));
        itemRowHolder.tvChuyenDiVo.setText(itemData.getCdi_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getCdi_V()));
        itemRowHolder.tvCuoiKyNuoc.setText(itemData.getTon_N() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getTon_N()));
        itemRowHolder.tvCuoiKyVo.setText(itemData.getTon_V() == 0 ? "" : AppUtils.formatNumber("N0").format(itemData.getTon_V()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DKNhatXuatNuocALLVoRongInfo> resultList = new ArrayList<>();
            String timKiem;
            for (DKNhatXuatNuocALLVoRongInfo item : listAllData) {
                timKiem = item.getProductID()
                        + " " + item.getProduct_Name();

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