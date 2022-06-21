package com.bongmai.tiha.ui.danhmuc.chucvu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.BoPhanInfo;
import com.bongmai.tiha.data.entities.ChucVuInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.danhmuc.employee.bophannhanvien.ListBoPhanAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChucVuListAdapter extends RecyclerView.Adapter<ChucVuListAdapter.ItemRowHolder> implements Filterable {
    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<ChucVuInfo> listAllData;
    List<ChucVuInfo> listSearchData;
    Context mContext;
    ChucVuListAdapter.CustomFilterListData customFilterListData;

    public ChucVuListAdapter(Context context, List<ChucVuInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new ChucVuListAdapter.CustomFilterListData();
    }

    public void setOnDataSetChangedListener(BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener) {
        this.dataSetChangedListener = dataSetChangedListener;
        dataSetChangedListener.onDataSetChanged();
    }

    public void setOnClickListener(BaseRecyclerViewEvent.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnLongClickListener(BaseRecyclerViewEvent.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnButtonClickListener(BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != listSearchData ? listSearchData.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ChucVuInfo getItem(int position) {
        return listSearchData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<ChucVuInfo> getListAllData() {
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

    ChucVuListAdapter.ItemRowHolder itemRowHolder;

    @Override
    public ChucVuListAdapter.ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chucvulist, viewGroup, false);
        itemRowHolder = new ChucVuListAdapter.ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        private TextView tvChucVuID, tvChucVu;
        public ItemRowHolder(View view) {
            super(view);
            this.tvChucVuID = (TextView) view.findViewById(R.id.tvChucVuID);
            this.tvChucVu = view.findViewById(R.id.tvChucVu);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onClick(v, getAdapterPosition());
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        longClickListener.onLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<ChucVuInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (ChucVuInfo item : listAllData) {
                timKiem = item.getCVName();
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
            listSearchData = (ArrayList<ChucVuInfo>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

    @Override
    public void onBindViewHolder(ChucVuListAdapter.ItemRowHolder itemRowHolder, final int position) {
        ChucVuInfo chucVuInfo = listSearchData.get(position);
        itemRowHolder.tvChucVuID.setText(chucVuInfo.getCVID());
        itemRowHolder.tvChucVu.setText(chucVuInfo.getCVName());
    }
}
