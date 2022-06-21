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
import com.bongmai.tiha.data.entities.DanhSachNhapInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class NhatKyNhapHangRecyclerViewAdapter extends RecyclerView.Adapter<NhatKyNhapHangRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DanhSachNhapInfo danhSachCuocGoiInfo, View view, int position);

        void onItemLongClick(DanhSachNhapInfo danhSachCuocGoiInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<DanhSachNhapInfo> listAllData;
    List<DanhSachNhapInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DanhSachNhapInfo danhSachNhapInfo;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public NhatKyNhapHangRecyclerViewAdapter(Context context, List<DanhSachNhapInfo> listAllData) {
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

    public List<DanhSachNhapInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nhatkynhaphang, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvNgay;
        private TextView tvSoCT;
        private TextView tvTenKhachHang;
        private TextView tvTongTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvNgay = (TextView) view.findViewById(R.id.tvNgay);
            this.tvSoCT = (TextView) view.findViewById(R.id.tvSoCT);
            this.tvTenKhachHang = (TextView) view.findViewById(R.id.tvTenKhachHang);
            this.tvTongTien = (TextView) view.findViewById(R.id.tvTongTien);

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
        danhSachNhapInfo = listSearchData.get(position);

        itemRowHolder.tvNgay.setText(AppUtils.formatDateToString(danhSachNhapInfo.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvSoCT.setText(danhSachNhapInfo.getSoCT());
        itemRowHolder.tvTenKhachHang.setText(danhSachNhapInfo.getTennguoimua());
        itemRowHolder.tvTongTien.setText(AppUtils.formatNumber("N0").format(danhSachNhapInfo.getTT()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DanhSachNhapInfo> resultList = new ArrayList<>();
            String timKiem;
            for (DanhSachNhapInfo item : listAllData) {
                timKiem = AppUtils.formatDateToString(item.getNgay(), "dd/MM/yyyy") +" " + item.getSoCT() +" "+ item.getTennguoimua() +" "+ String.valueOf(item.getTT());
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
            listSearchData = (ArrayList<DanhSachNhapInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}