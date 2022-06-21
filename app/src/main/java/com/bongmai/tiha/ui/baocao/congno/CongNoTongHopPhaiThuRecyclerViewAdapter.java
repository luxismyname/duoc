package com.bongmai.tiha.ui.baocao.congno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.CongNoTongHopPhaiThuInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class CongNoTongHopPhaiThuRecyclerViewAdapter extends RecyclerView.Adapter<CongNoTongHopPhaiThuRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(CongNoTongHopPhaiThuInfo congNoTongHopPhaiThuInfo, View view, int position);

        void onItemLongClick(CongNoTongHopPhaiThuInfo congNoTongHopPhaiThuInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<CongNoTongHopPhaiThuInfo> listAllData;
    List<CongNoTongHopPhaiThuInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    CongNoTongHopPhaiThuInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;
    boolean isCongNoCuoiKy;

    public CongNoTongHopPhaiThuRecyclerViewAdapter(Context context, List<CongNoTongHopPhaiThuInfo> listAllData, boolean isCongNoCuoiKy) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.isCongNoCuoiKy = isCongNoCuoiKy;
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

    public List<CongNoTongHopPhaiThuInfo> getListAllData() {
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
        View v;
        if (!isCongNoCuoiKy)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_congnotonghopphaithu, viewGroup, false);
        else
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_congnotonghopphaithucuoiky, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvNhomKH;
        private TextView tvMaKhachHang;
        private TextView tvTenKhachHang;
        private TextView tvDiaChi;
        private TextView tvPhaiThuDK;
        private TextView tvNhap;
        private TextView tvXuat;
        private TextView tvThu;
        private TextView tvChi;
        private TextView tvPhaiThuCK;

        public ItemRowHolder(View view) {
            super(view);
            this.tvNhomKH = (TextView) view.findViewById(R.id.tvNhomKH);
            this.tvMaKhachHang = (TextView) view.findViewById(R.id.tvMaKhachHang);
            this.tvTenKhachHang = (TextView) view.findViewById(R.id.tvTenKhachHang);
            this.tvDiaChi = (TextView) view.findViewById(R.id.tvDiaChi);
            this.tvPhaiThuDK = (TextView) view.findViewById(R.id.tvPhaiThuDK);
            this.tvNhap = (TextView) view.findViewById(R.id.tvNhap);
            this.tvXuat = (TextView) view.findViewById(R.id.tvXuat);
            this.tvThu = (TextView) view.findViewById(R.id.tvThu);
            this.tvChi = (TextView) view.findViewById(R.id.tvChi);
            this.tvPhaiThuCK = (TextView) view.findViewById(R.id.tvPhaiThuCK);;

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
        itemRowHolder.tvNhomKH.setText(itemData.getPTTT());
        itemRowHolder.tvMaKhachHang.setText(itemData.getSupplier_ID());
        itemRowHolder.tvTenKhachHang.setText(itemData.getCompany_Name());
        itemRowHolder.tvDiaChi.setText(itemData.getDiaChi());
        itemRowHolder.tvPhaiThuDK.setText(AppUtils.formatNumber("N0").format(itemData.getPhaiThuDK()));
        itemRowHolder.tvNhap.setText(AppUtils.formatNumber("N0").format(itemData.getNhap()));
        itemRowHolder.tvXuat.setText(AppUtils.formatNumber("N0").format(itemData.getXuat()));
        itemRowHolder.tvThu.setText(AppUtils.formatNumber("N0").format(itemData.getThu()));
        itemRowHolder.tvChi.setText(AppUtils.formatNumber("N0").format(itemData.getChi()));
        itemRowHolder.tvPhaiThuCK.setText(AppUtils.formatNumber("N0").format(itemData.getPhaiThuCK()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<CongNoTongHopPhaiThuInfo> resultList = new ArrayList<>();
            String timKiem;
            for (CongNoTongHopPhaiThuInfo item : listAllData) {
                timKiem = item.getPTTT() + " " + item.getSupplier_ID() + " " + item.getCompany_Name() + " "+ item.getDiaChi();
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
            listSearchData = (ArrayList<CongNoTongHopPhaiThuInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}