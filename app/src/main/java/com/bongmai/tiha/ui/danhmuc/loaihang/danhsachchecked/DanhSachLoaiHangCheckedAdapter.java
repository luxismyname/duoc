package com.bongmai.tiha.ui.danhmuc.loaihang.danhsachchecked;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.LoaiHangInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DanhSachLoaiHangCheckedAdapter extends RecyclerView.Adapter<DanhSachLoaiHangCheckedAdapter.ItemRowHolder> implements Filterable {
    private List<LoaiHangInfo> listAllData;
    private List<LoaiHangInfo> listSearchData;
    private Context mContext;
    private CustomFilterListData customFilterListData = null;
    private List<LoaiHangInfo> listKhoChon;

    public DanhSachLoaiHangCheckedAdapter(Context context, List<LoaiHangInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.listKhoChon = new ArrayList<>();
    }

    public void checkAllData(boolean isChecked) {
        listKhoChon = new ArrayList<>();
        for (LoaiHangInfo item : listAllData) {
            item.setChon(isChecked);
            if (isChecked)
                listKhoChon.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_danhsachkhocheck, viewGroup, false);
        ItemRowHolder itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    LoaiHangInfo khoInfo;

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        khoInfo = listSearchData.get(position);
        try {
            itemRowHolder.chkKho.setText(khoInfo.getLoaihang1());
            itemRowHolder.chkKho.setChecked(khoInfo.isChon());
        } catch (Exception e) {
        }
        itemRowHolder.chkKho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                LoaiHangInfo itemKho = listSearchData.get(position);
                int index = listKhoChon.indexOf(itemKho);
                if (index < 0 && isChecked) {
                    itemKho.setChon(true);
                    listKhoChon.add(itemKho);
                } else if (index >= 0 && !isChecked) {
                    listKhoChon.remove(itemKho);
                    itemKho.setChon(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listSearchData != null ? listSearchData.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Map<String, String> getMapLoaiHangChon() {
        Map<String, String> mapListKhoChon = new HashMap<String, String>();
        String listMaKho = "", listTenKho = "";
        for (LoaiHangInfo item : listKhoChon) {
            listMaKho += item.getCategory_ID() + ",";
            listTenKho += item.getLoaihang1() + ", ";
        }
        listMaKho = !listMaKho.isEmpty() ? listMaKho.substring(0, listMaKho.length() - 1) : listMaKho;
        listTenKho = !listTenKho.isEmpty() ? listTenKho.substring(0, listTenKho.length() - 2) : listTenKho;
        mapListKhoChon.put("MaLoaiHang", listMaKho);
        mapListKhoChon.put("TenLoaiHang", listTenKho);
        return mapListKhoChon;
    }

    public void setListLoaiHangChon(List<LoaiHangInfo> list) {
        listKhoChon = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        CheckBox chkKho;

        public ItemRowHolder(View view) {
            super(view);
            this.chkKho = (CheckBox) view.findViewById(R.id.chkKho);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<LoaiHangInfo> resultList = new ArrayList<>();
            String value;
            LoaiHangInfo item;
            for (int i = 0; i < listAllData.size(); i++) {
                value = listAllData.get(i).getCategory_ID().toUpperCase() + " "
                        + listAllData.get(i).getLoaihang1().toUpperCase() + " ";
                if (value.contains(filterString)) {
                    item = listAllData.get(i);
                    resultList.add(item);
                }
            }
            filterResults.values = resultList;
            filterResults.count = resultList.size();
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listSearchData = (ArrayList<LoaiHangInfo>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}