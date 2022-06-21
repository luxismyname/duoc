package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.thongke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.ThongKeKMNVGHInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.danhmuc.employee.themnhanvien.ThemNhanVienPresenter;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter extends RecyclerView.Adapter<ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter.ItemRowHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;

    public interface ItemClickListener {
        void onItemClick(ThongKeKMNVGHInfo employeeGiaoHangInfo, View view, int position);

        void onItemLongClick(ThongKeKMNVGHInfo employeeGiaoHangInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<ThongKeKMNVGHInfo> listAllData;
    List<ThongKeKMNVGHInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    ThongKeKMNVGHInfo itemData;
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;

    ThemNhanVienPresenter employeeListPresenter;

    public ThongKeQuangDuongNhanVienGiaoHangRecycleViewAdapter(Context context, List<ThongKeKMNVGHInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.employeeListPresenter = new ThemNhanVienPresenter(null);
    }

    public void DataSourceChanged(DataSourceChangedListener listener) {
        this.dataSourceChangedListener = listener;
        listener.onDataSourceChanged();
    }

    public void setOnClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void ItemClick(ItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public ThongKeKMNVGHInfo getItem(int position) {
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

    public List<ThongKeKMNVGHInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }


    public void addAll(List<ThongKeKMNVGHInfo> serviceReportResults) {
        for (ThongKeKMNVGHInfo result : serviceReportResults) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_thongkekmnvgh, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }



    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvTenNV;
        private TextView tvTongChuyenDi;
        private TextView tvQuangDuong;

        public ItemRowHolder(View view) {
            super(view);
            this.tvTenNV = (TextView) view.findViewById(R.id.tvTenNV);
            this.tvTongChuyenDi = (TextView) view.findViewById(R.id.tvTongChuyenDi);
            this.tvQuangDuong = (TextView) view.findViewById(R.id.tvQuangDuong);
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
        final ThongKeKMNVGHInfo employeeInfos = listSearchData.get(position);
        itemData = listSearchData.get(position);


        itemRowHolder.tvTenNV.setText(itemData.getEmployeeID());
        itemRowHolder.tvTongChuyenDi.setText(AppUtils.formatNumber("N0").format(itemData.getSoChuyenDi()));


        itemRowHolder.tvQuangDuong.setText(AppUtils.formatNumber("N1").format(itemData.getTongKM() ) +"km");
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<ThongKeKMNVGHInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (ThongKeKMNVGHInfo item : listAllData) {
                timKiem = item.getEmployeeID() + " " + String.valueOf(item.getTongKM());
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
            listSearchData = (ArrayList<ThongKeKMNVGHInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

}
