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
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class NhatKyBanHangRecyclerViewAdapter extends RecyclerView.Adapter<NhatKyBanHangRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DanhSachXuatInfo danhSachCuocGoiInfo, View view, int position);

        void onItemLongClick(DanhSachXuatInfo danhSachCuocGoiInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    private BaseRecyclerViewEvent.OnClickListener clickListener;



    List<DanhSachXuatInfo> listAllData;
    List<DanhSachXuatInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DanhSachXuatInfo danhSachXuatInfo;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public NhatKyBanHangRecyclerViewAdapter(Context context, List<DanhSachXuatInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
    }

    public void DataSourceChanged(DataSourceChangedListener listener) {
        this.dataSourceChangedListener = listener;
        listener.onDataSourceChanged();
    }

    public BaseRecyclerViewEvent.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
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

    public DanhSachXuatInfo getItem(int position) {
        return listSearchData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<DanhSachXuatInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nhatkybanhang, viewGroup, false);
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
        danhSachXuatInfo = listSearchData.get(position);

        itemRowHolder.tvNgay.setText(AppUtils.formatDateToString(danhSachXuatInfo.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvSoCT.setText(danhSachXuatInfo.getSoCt());
        itemRowHolder.tvTenKhachHang.setText(danhSachXuatInfo.getCty());
        itemRowHolder.tvTongTien.setText(AppUtils.formatNumber("N0").format(danhSachXuatInfo.getTT()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DanhSachXuatInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (DanhSachXuatInfo item : listAllData) {
                timKiem = AppUtils.formatDateToString(item.getNgay(), "dd/MM/yyyy") +" " + item.getSoCt() +" "+ item.getCty() +" "+ String.valueOf(item.getTT());
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
            listSearchData = (ArrayList<DanhSachXuatInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}