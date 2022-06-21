package com.bongmai.tiha.ui.chungtu.phieugiaohang.report;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.ui.danhmuc.employee.themnhanvien.ThemNhanVienPresenter;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class QuangDuongNhanVienGiaoHangRecycleViewAdapter  extends RecyclerView.Adapter<QuangDuongNhanVienGiaoHangRecycleViewAdapter.ItemRowHolder> implements Filterable  {

    public interface ItemClickListener {
        void onItemClick(EmployeeGiaoHangInfo employeeGiaoHangInfo, View view, int position);

        void onItemLongClick(EmployeeGiaoHangInfo employeeGiaoHangInfo, View view, int position);
    }

    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }

    List<EmployeeGiaoHangInfo> listAllData;
    List<EmployeeGiaoHangInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;
    EmployeeGiaoHangInfo itemData;
    QuangDuongNhanVienGiaoHangRecycleViewAdapter.DataSourceChangedListener dataSourceChangedListener;
    QuangDuongNhanVienGiaoHangRecycleViewAdapter.ItemClickListener itemClickListener;

    ThemNhanVienPresenter employeeListPresenter;

    public QuangDuongNhanVienGiaoHangRecycleViewAdapter(Context context, List<EmployeeGiaoHangInfo> listAllData) {
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

    public List<EmployeeGiaoHangInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }


    public void addAll(List<EmployeeGiaoHangInfo> serviceReportResults) {
        for (EmployeeGiaoHangInfo result : serviceReportResults) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quangduongnhanviengiaohang, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvMaNV;
        private TextView tvTenNV;
        private TextView tvQuangDuong;

        public ItemRowHolder(View view) {
            super(view);
            this.tvMaNV = (TextView) view.findViewById(R.id.tvMaNV);
            this.tvTenNV = (TextView) view.findViewById(R.id.tvTenNhanVien);
            this.tvQuangDuong = (TextView) view.findViewById(R.id.tvQuangDuong);
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
        final EmployeeGiaoHangInfo employeeInfos = listSearchData.get(position);
        itemData = listSearchData.get(position);


        itemRowHolder.tvMaNV.setText(itemData.getEmployeeID());
        itemRowHolder.tvTenNV.setText(itemData.getEmployeeName());

//        itemRowHolder.tvTenNV.setText(PublicVariables.nguoiDungInfo.getEmployeeName());

        itemRowHolder.tvQuangDuong.setText(AppUtils.formatNumber("N1").format(itemData.getQuangDuong() ) +"km");
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<EmployeeGiaoHangInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (EmployeeGiaoHangInfo item : listAllData) {
                timKiem = item.getEmployeeID() + " " + String.valueOf(item.getQuangDuong());
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
            listSearchData = (ArrayList<EmployeeGiaoHangInfo>) results.values;
            dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
    
}