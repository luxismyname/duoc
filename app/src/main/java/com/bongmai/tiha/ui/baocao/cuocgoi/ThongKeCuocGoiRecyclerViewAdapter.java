package com.bongmai.tiha.ui.baocao.cuocgoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class ThongKeCuocGoiRecyclerViewAdapter extends RecyclerView.Adapter<ThongKeCuocGoiRecyclerViewAdapter.ItemRowHolder> implements Filterable {

    public interface ItemClickListener {
        void onItemClick(DanhSachCuocGoiInfo danhSachCuocGoiInfo, View view, int position);

        void onItemLongClick(DanhSachCuocGoiInfo danhSachCuocGoiInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<DanhSachCuocGoiInfo> listAllData;
    List<DanhSachCuocGoiInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    DanhSachCuocGoiInfo danhSachCuocGoiInfo;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    public ThongKeCuocGoiRecyclerViewAdapter(Context context, List<DanhSachCuocGoiInfo> listAllData) {
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

    public List<DanhSachCuocGoiInfo> getListAllData() {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_thongkecuocgoi, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        //        private TextView tvID;
//        private TextView tvLine;
//        private TextView tvSoID;
        private TextView tvSoDienThoai;
        private TextView tvTienHang;
        //        private TextView tvMaKH;
        private TextView tvTenKhachHang;
        private TextView tvDiaChi;
        private TextView tvTGNhan;
        private TextView tvSanPhamSD;
        private TextView tvSoLuong;
        private TextView tvTienThu;

        public ItemRowHolder(View view) {
            super(view);
//            this.tvID = (TextView) view.findViewById(R.id.tvID);
//            this.tvLine = (TextView) view.findViewById(R.id.tvLine);
//            this.tvSoID = (TextView) view.findViewById(R.id.tvSoID);
            this.tvSoDienThoai = (TextView) view.findViewById(R.id.tvSoDienThoai);
            this.tvTienHang = (TextView) view.findViewById(R.id.tvTienHang);
//            this.tvMaKH = (TextView) view.findViewById(R.id.tvMaKH);
            this.tvTenKhachHang = (TextView) view.findViewById(R.id.tvTenKhachHang);
            this.tvDiaChi = (TextView) view.findViewById(R.id.tvDiaChi);
            this.tvTGNhan = (TextView) view.findViewById(R.id.tvTGNhan);
            this.tvSanPhamSD = (TextView) view.findViewById(R.id.tvSanPhamSD);
            this.tvSoLuong = (TextView) view.findViewById(R.id.colSoLuong);
            this.tvTienThu = (TextView) view.findViewById(R.id.tvTienThu);

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
        danhSachCuocGoiInfo = listSearchData.get(position);
        double tienBan = danhSachCuocGoiInfo.getTienBan();
        double tienThu = 0;
        try {
            tienThu = danhSachCuocGoiInfo.getTienThu();
        } catch (Exception e) {

        }
        String SoCTX = danhSachCuocGoiInfo.getSoPhieu();
        String SoCTT = "";
        try {
            SoCTT = danhSachCuocGoiInfo.getSoCTThu();
        } catch (Exception e) {
        }

//        itemRowHolder.tvID.setText(String.valueOf(danhSachCuocGoiInfo.getIDHinhAnh()));
//        itemRowHolder.tvLine.setText(danhSachCuocGoiInfo.getLine());
//        itemRowHolder.tvSoID.setText(String.valueOf(danhSachCuocGoiInfo.getSoID()));
        itemRowHolder.tvSoDienThoai.setText(danhSachCuocGoiInfo.getPhone());
        if (tienBan > 0) {
            itemRowHolder.tvTienHang.setText(AppUtils.formatNumber("N0").format(tienBan));
        } else {
            itemRowHolder.tvTienHang.setText("");
        }
        if (tienThu > 0) {
            itemRowHolder.tvTienThu.setText(AppUtils.formatNumber("N0").format(tienThu));
        } else {
            itemRowHolder.tvTienThu.setText("");
        }
//        itemRowHolder.tvMaKH.setText(danhSachCuocGoiInfo.getSupplier_ID());
        itemRowHolder.tvTenKhachHang.setText(danhSachCuocGoiInfo.getTenKhachHang());
        itemRowHolder.tvDiaChi.setText(danhSachCuocGoiInfo.getDiaChi());
        String ThoiGianNhan = null;
        try {

            ThoiGianNhan = AppUtils.formatDateToString(danhSachCuocGoiInfo.getNgay(), "HH:mm");
        } catch (Exception e) {

        }
        itemRowHolder.tvTGNhan.setText(ThoiGianNhan);
        itemRowHolder.tvSanPhamSD.setText(danhSachCuocGoiInfo.getSanPhamMua());
        if (danhSachCuocGoiInfo.getSL() > 0)
            itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(danhSachCuocGoiInfo.getSL()));
        else
            itemRowHolder.tvSoLuong.setText("");
        if (!SoCTX.isEmpty() && !SoCTT.isEmpty()) {
            if (SoCTT == "NO") {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_light));
            } else if ((tienBan - tienThu) > 0) {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_light));
            } else {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
            }
        } else {
            if (!SoCTX.isEmpty() && tienBan > 0) {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(R.color.Bisque));
            } else if (!SoCTX.isEmpty() && tienBan == 0) {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
            } else {
                itemRowHolder.tvSoDienThoai.setTextColor(mContext.getResources().getColor(android.R.color.black));
            }
        }

    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DanhSachCuocGoiInfo> resultList = new ArrayList<>();
            for (DanhSachCuocGoiInfo item : listAllData) {
                if (item.getTimKiem() != null) {
                    if (item.getTimKiem().toUpperCase().contains(filterString)) {
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
            listSearchData = (ArrayList<DanhSachCuocGoiInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}