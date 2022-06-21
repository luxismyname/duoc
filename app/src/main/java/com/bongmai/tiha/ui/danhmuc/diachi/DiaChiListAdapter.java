package com.bongmai.tiha.ui.danhmuc.diachi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;

import java.util.ArrayList;
import java.util.List;


public class DiaChiListAdapter extends RecyclerView.Adapter<DiaChiListAdapter.ItemRowHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<DiaChiInfo> listAllData;
    List<DiaChiInfo> listSearchData;
    Context mContext;
    CustomFilterListData customFilterListData;

    public DiaChiListAdapter(Context context, List<DiaChiInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
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

    public DiaChiInfo getItem(int position) {
        return listSearchData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public int getItemPosition(int  parentID) {
        if (listAllData == null) return -1;

        for (int i = 0; i < listAllData.size(); i++) {
            if (listAllData.get(i).getParentID() == parentID)
                return i;
        }
        return -1;
    }
    public void addItem(DiaChiInfo item) {
        listAllData = (listAllData == null) ? new ArrayList<>() : listAllData;
        listAllData.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int index, DiaChiInfo item) {
        listAllData.add(index, item);
        notifyDataSetChanged();
    }
    public List<DiaChiInfo> getListAllData() {
        return listSearchData;
    }

//    public void removeItem(int position) {
//        listSearchData.remove(position);
//        notifyDataSetChanged();
//    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    ItemRowHolder itemRowHolder;

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_diachilist, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvMaLoai;
        private TextView tvTenLoai;
        public ItemRowHolder(View view) {
            super(view);
            this.tvMaLoai = (TextView) view.findViewById(R.id.tvMaLoai);
            this.tvTenLoai = (TextView) view.findViewById(R.id.tvTenLoai);
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
            final List<DiaChiInfo> resultList = new ArrayList<>();
            String timKiem = "";
            for (DiaChiInfo item : listAllData) {
                timKiem = item.getTenDiaChi();
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
            listSearchData = (ArrayList<DiaChiInfo>) results.values;
            notifyDataSetChanged();
        }
    }

    public void setListAllData(List<DiaChiInfo> listAllData) {
        this.listAllData = listAllData;
        notifyDataSetChanged();
    }

    public List<DiaChiInfo> getListSearchData() {
        return listSearchData;
    }

    public void setListSearchData(List<DiaChiInfo> listSearchData) {
        this.listSearchData = listSearchData;
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        final DiaChiInfo diaChiInfo = listSearchData.get(position);
        itemRowHolder.tvMaLoai.setText(diaChiInfo.getMaDiaChi());
        itemRowHolder.tvTenLoai.setText(diaChiInfo.getTenDiaChi());
    }
}