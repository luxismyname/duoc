package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.luongtheonvgh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LuongNVGHTheoKMInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter  extends RecyclerView.Adapter<ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter.ItemRowHolder> implements Filterable {


    private BaseRecyclerViewEvent.OnClickListener clickListener;

    public interface ItemClickListener {
        void onItemClick(LuongNVGHTheoKMInfo luongNVGHTheoKMInfo, View view, int position);

        void onItemLongClick(LuongNVGHTheoKMInfo luongNVGHTheoKMInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<LuongNVGHTheoKMInfo> listAllData;
    List<LuongNVGHTheoKMInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    LuongNVGHTheoKMInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;


    public ThongKeLuongNhanVienGiaoHangTheoQuangDuongAdapter(Context context, List<LuongNVGHTheoKMInfo> listAllData) {
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


    public void setOnClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public LuongNVGHTheoKMInfo getItem(int position) {
        return listSearchData.get(position);
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

    public List<LuongNVGHTheoKMInfo> getListAllData() {
        return listSearchData;
    }



    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }


    public void addAll(List<LuongNVGHTheoKMInfo> serviceReportResults) {
        for (LuongNVGHTheoKMInfo result : serviceReportResults) {
            listSearchData.add(result);
        }
        notifyDataSetChanged();
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    ItemRowHolder itemRowHolder;

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_luongnhanviengiaohangtheokm, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvSLD;
        private TextView tvNgayGiao;
        private TextView tvDCBD;
        private TextView tvDCGH;
        private TextView tvQuangDuong;
        private TextView tvSoCT;
        private TextView tvTongTien;

        public ItemRowHolder(View view) {
            super(view);
            this.tvSLD = (TextView) view.findViewById(R.id.tvSLD);
            this.tvNgayGiao = (TextView) view.findViewById(R.id.tvNgayGiao);
            this.tvDCBD  = view.findViewById(R.id.tvDCBD);
            this.tvDCGH = view.findViewById(R.id.tvDCGH);
            this.tvQuangDuong = view.findViewById(R.id.tvQuangDuong);
            this.tvSoCT = view.findViewById(R.id.tvSoCT);
            this.tvTongTien = view.findViewById(R.id.tvTongTien);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        clickListener.onClick( v, position);
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

        itemRowHolder.tvSoCT.setText(itemData.getMSChuyenDi());
        itemRowHolder.tvSLD.setText(String.valueOf(itemData.getLanDiSo()));
        itemRowHolder.tvNgayGiao.setText(AppUtils.formatDateToString(itemData.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvDCBD.setText(itemData.getDCBD());
        itemRowHolder.tvDCGH.setText(itemData.getDCGH());
        itemRowHolder.tvQuangDuong.setText(AppUtils.formatNumber("N1").format(itemData.getTongQuangDuong() ) +"km");
        itemRowHolder.tvTongTien.setText(AppUtils.formatNumber("N0").format(itemData.getSotiennhap()));


    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<LuongNVGHTheoKMInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (LuongNVGHTheoKMInfo item : listAllData) {
                timKiem = item.getMSChuyenDi() + " " + item.getTenNVGH();
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
            listSearchData = (ArrayList<LuongNVGHTheoKMInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

}
