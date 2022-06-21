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
import com.bongmai.tiha.data.entities.ChiTietXuatHangInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class NhatKyBanHangChiTietRecyclerViewAdapter extends RecyclerView.Adapter<NhatKyBanHangChiTietRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(ChiTietXuatHangInfo chiTietXuatHangInfo, View view, int position);

        void onItemLongClick(ChiTietXuatHangInfo chiTietXuatHangInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<ChiTietXuatHangInfo> listAllData;
    List<ChiTietXuatHangInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    ChiTietXuatHangInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public NhatKyBanHangChiTietRecyclerViewAdapter(Context context, List<ChiTietXuatHangInfo> listAllData) {
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

    public List<ChiTietXuatHangInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nhatkybanhangchitiet, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvNgay;
        private TextView tvSoPhieu;
        private TextView tvTenKhachHang;
        private TextView tvDiaChi;
        private TextView tvDDBH;
        private TextView tvTenKho;
        private TextView tvTenSanPham;
        private TextView tvDVT;
        private TextView tvSoLuong;
        private TextView tvDonGia;
        private TextView tvThanhTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvNgay = (TextView) view.findViewById(R.id.tvNgay);
            this.tvSoPhieu = (TextView) view.findViewById(R.id.tvSoPhieu);
            this.tvTenKhachHang = (TextView) view.findViewById(R.id.tvTenKhachHang);
            this.tvDiaChi = (TextView) view.findViewById(R.id.tvDiaChi);
            this.tvDDBH = (TextView) view.findViewById(R.id.tvDDBH);
            this.tvTenKho = (TextView) view.findViewById(R.id.tvTenKho);
            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvDVT = (TextView) view.findViewById(R.id.tvDVT);
            this.tvSoLuong = (TextView) view.findViewById(R.id.colSoLuong);
            this.tvDonGia = (TextView) view.findViewById(R.id.tvDonGia);
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
        itemRowHolder.tvNgay.setText(AppUtils.formatDateToString(itemData.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvSoPhieu.setText(itemData.getSoCt());
        itemRowHolder.tvTenKhachHang.setText(itemData.getCompanyName());
        itemRowHolder.tvDiaChi.setText(itemData.getDiaChi());
        itemRowHolder.tvDDBH.setText(itemData.getEmployeeName());
        itemRowHolder.tvTenKho.setText(itemData.getTenkho());
        itemRowHolder.tvTenSanPham.setText(itemData.getProductName());
        itemRowHolder.tvDVT.setText(itemData.getDvt());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(itemData.getSL()));
        itemRowHolder.tvDonGia.setText(AppUtils.formatNumber("N0").format(itemData.getDongia()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(itemData.getThanh_Tien()));
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<ChiTietXuatHangInfo> resultList = new ArrayList<>();
            String timKiem;
            for (ChiTietXuatHangInfo item : listAllData) {
                timKiem = AppUtils.formatDateToString(item.getNgay(), "dd/MM/yyyy")
                        +" " + item.getSoCt()
                        +" " + item.getCompanyName()
                        +" " + item.getDiaChi()
                        +" " + item.getEmployeeName()
                        +" "+ item.getTenkho()
                        +" " + item.getProductName()
                        +" "+ item.getDvt()
                        +" "+ AppUtils.formatNumber("N0").format((itemData.getSL())
                        +" "+ AppUtils.formatNumber("N0").format(itemData.getDongia()))
                        +" "+ AppUtils.formatNumber("N0").format(itemData.getThanh_Tien());
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
            listSearchData = (ArrayList<ChiTietXuatHangInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}