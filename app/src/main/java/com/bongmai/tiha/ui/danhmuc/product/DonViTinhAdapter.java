package com.bongmai.tiha.ui.danhmuc.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.DonViTinhInfo;

import java.util.ArrayList;
import java.util.List;




public class DonViTinhAdapter extends ArrayAdapter<DonViTinhInfo> implements Filterable {
    private CustomFilterListData customFilterListData = new CustomFilterListData();
    Context context;
    List<DonViTinhInfo> listAllData;

    public DonViTinhAdapter(Context context, List<DonViTinhInfo> listAllData) {
        super(context, 0, listAllData);
        this.context = context;
        this.listAllData = new ArrayList<>(listAllData);
    }

    private class ViewHolder {
        public TextView tvName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_donvitinh, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tvDonViTinh);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DonViTinhInfo donViTinhInfo = getItem(position);
        if (donViTinhInfo != null) {
            holder.tvName.setText(String.valueOf(donViTinhInfo.getTenDonViTinh()));
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

    private class CustomFilterListData extends Filter {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((DonViTinhInfo) resultValue).getTenDonViTinh();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            final List<DonViTinhInfo> resultList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                resultList.addAll(listAllData);
            } else {
                String filterString = charSequence.toString().toUpperCase();
                String timKiem;
                for (DonViTinhInfo item : listAllData) {
                    timKiem = item.getTenDonViTinh();
                    if (!timKiem.isEmpty()) {
                        if (timKiem.toUpperCase().contains(filterString)) {
                            resultList.add(item);
                        }
                    }
                }
            }
            filterResults.values = resultList;
            filterResults.count = resultList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            listSearchData = (ArrayList<SerialInfo>) results.values;
//            notifyDataSetChanged();
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }
    }

}
