package com.bongmai.tiha.ui.chungtu.phieugiaohang.report.chitiet;

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
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class QuangDuongNhanVienGiaoHangChiTietAdapter extends RecyclerView.Adapter<QuangDuongNhanVienGiaoHangChiTietAdapter.ItemRowHolder> implements Filterable {


    private BaseRecyclerViewEvent.OnClickListener clickListener;

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
    DataSourceChangedListener dataSourceChangedListener;
    ItemClickListener itemClickListener;


    public QuangDuongNhanVienGiaoHangChiTietAdapter(Context context, List<EmployeeGiaoHangInfo> listAllData) {
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

    public EmployeeGiaoHangInfo getItem(int position) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quangduonggiaohangchitiet, viewGroup, false);
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

        public ItemRowHolder(View view) {
            super(view);
            this.tvSLD = (TextView) view.findViewById(R.id.tvSLD);
            this.tvNgayGiao = (TextView) view.findViewById(R.id.tvNgayGiao);
            this.tvDCBD  = view.findViewById(R.id.tvDCBD);
            this.tvDCGH = view.findViewById(R.id.tvDCGH);
            this.tvQuangDuong = view.findViewById(R.id.tvQuangDuong);
            this.tvSoCT = view.findViewById(R.id.tvSoCT);

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

            itemRowHolder.tvSoCT.setText(itemData.getSoCT());
            itemRowHolder.tvSLD.setText(String.valueOf(itemData.getSolandi()));
            itemRowHolder.tvNgayGiao.setText(AppUtils.formatDateToString(itemData.getNgayGiaoHang(), "dd/MM/yyyy"));
            itemRowHolder.tvDCBD.setText(itemData.getDiachibatdau());
            itemRowHolder.tvDCGH.setText(itemData.getDiachigiaohang());
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
