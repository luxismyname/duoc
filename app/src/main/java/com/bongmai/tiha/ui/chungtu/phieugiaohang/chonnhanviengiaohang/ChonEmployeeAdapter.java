package com.bongmai.tiha.ui.chungtu.phieugiaohang.chonnhanviengiaohang;

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
import com.bongmai.tiha.data.entities.EmployeeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChonEmployeeAdapter extends RecyclerView.Adapter<ChonEmployeeAdapter.ItemRowHolder> implements Filterable {
    private List<EmployeeInfo> listAllData;
    private List<EmployeeInfo> listSearchData;
    private Context mContext;
    private CustomFilterListData customFilterListData = null;
    private List<EmployeeInfo> listEmployeeChon;

    public ChonEmployeeAdapter(Context context, List<EmployeeInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.listEmployeeChon = new ArrayList<>();

    }

    public void checkAllData(boolean isChecked) {
        listEmployeeChon = new ArrayList<>();
        for (EmployeeInfo item : listAllData) {
            item.setChon(isChecked);
            if (isChecked)
                listEmployeeChon.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_employeecheck, viewGroup, false);
        ItemRowHolder itemRowHolder = new ChonEmployeeAdapter.ItemRowHolder(v);
        return itemRowHolder;
    }

    EmployeeInfo employeeInfo;

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        employeeInfo = listSearchData.get(position);
        try {
            itemRowHolder.chkEmployee.setText(employeeInfo.getEmployeeName());
            itemRowHolder.chkEmployee.setChecked(employeeInfo.isChon());
        } catch (Exception e) {
        }
        itemRowHolder.chkEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                EmployeeInfo itemKho = listSearchData.get(position);
                int index = listEmployeeChon.indexOf(itemKho);
                if (index < 0 && isChecked) {
                    itemKho.setChon(true);
                    listEmployeeChon.add(itemKho);
                } else if (index >= 0 && !isChecked) {
                    listEmployeeChon.remove(itemKho);
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

    public Map<String, String> getMapKhoChon() {
        Map<String, String> mapListKhoChon = new HashMap<String, String>();
        String listMaNV = "", listTenNV = "";
        for (EmployeeInfo item : listEmployeeChon) {
            listMaNV += item.getEmployeeID() + ",";
            listTenNV += item.getEmployeeName() + ", ";
        }
        listMaNV = !listMaNV.isEmpty() ? listMaNV.substring(0, listMaNV.length() - 1) : listMaNV;
        listTenNV = !listTenNV.isEmpty() ? listTenNV.substring(0, listTenNV.length() - 2) : listTenNV;
        mapListKhoChon.put("MaNV", listMaNV);
        mapListKhoChon.put("TenNV", listTenNV);
        return mapListKhoChon;
    }

    public void setListEmployeeChon(List<EmployeeInfo> list) {
        listEmployeeChon = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        CheckBox chkEmployee;

        public ItemRowHolder(View view) {
            super(view);
            this.chkEmployee = (CheckBox) view.findViewById(R.id.chkEmployee);
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
            final List<EmployeeInfo> resultList = new ArrayList<>();
            String value;
            EmployeeInfo item;
            for (int i = 0; i < listAllData.size(); i++) {
                value = listAllData.get(i).getEmployeeID().toUpperCase() + " "
                        + listAllData.get(i).getEmployeeName().toUpperCase() + " ";
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
            listSearchData = (ArrayList<EmployeeInfo>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }
}