package com.bongmai.tiha.ui.danhmuc.soduvattu.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.SoDuVatTuDauInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class ListSoDuVatTuActivityAdapter extends RecyclerView.Adapter<ListSoDuVatTuActivityAdapter.ItemRowHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<SoDuVatTuDauInfo> listAllData;
    List<SoDuVatTuDauInfo> listSearchData;
    Context mContext;
    ListSoDuVatTuActivityAdapter.CustomFilterListData customFilterListData;

    public ListSoDuVatTuActivityAdapter(Context context, List<SoDuVatTuDauInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new ListSoDuVatTuActivityAdapter.CustomFilterListData();
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

    public SoDuVatTuDauInfo getItem(int position) {
        return listSearchData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<SoDuVatTuDauInfo> getListAllData() {
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

    ListSoDuVatTuActivityAdapter.ItemRowHolder itemRowHolder;

    @Override
    public ListSoDuVatTuActivityAdapter.ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listsoduvattudau, viewGroup, false);
        itemRowHolder = new ListSoDuVatTuActivityAdapter.ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvVattuID,
                tvTenSanPham,
                tvMSK,
                tvSoluongThuc,
                tvDongia,
                tvNgay;

        public ItemRowHolder(View view) {
            super(view);
            this.tvVattuID = view.findViewById(R.id.tvVattuID);
            this.tvTenSanPham = (TextView) view.findViewById(R.id.tvTenSanPham);
            this.tvMSK = (TextView) view.findViewById(R.id.tvMaKho);
            this.tvSoluongThuc = (TextView) view.findViewById(R.id.tvSLThuc);
            this.tvDongia = view.findViewById(R.id.tvDonGia);
            this.tvNgay = view.findViewById(R.id.etNgay);
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
            final List<SoDuVatTuDauInfo> resultList = new ArrayList<>();
            String timKiem;
            for (SoDuVatTuDauInfo item : listAllData) {
                timKiem = item.getID() + " " + item.getProductName();
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
            listSearchData = (ArrayList<SoDuVatTuDauInfo>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

    @Override
    public void onBindViewHolder(ListSoDuVatTuActivityAdapter.ItemRowHolder itemRowHolder, final int position) {
        SoDuVatTuDauInfo SoDuVatTuDauInfo = listSearchData.get(position);
        itemRowHolder.tvVattuID.setText(String.valueOf(SoDuVatTuDauInfo.getID()));
        itemRowHolder.tvTenSanPham.setText(SoDuVatTuDauInfo.getProductName());
        itemRowHolder.tvMSK.setText(SoDuVatTuDauInfo.getTenKho());
        itemRowHolder.tvSoluongThuc.setText(String.valueOf(SoDuVatTuDauInfo.getSoLuongThuc()));
        itemRowHolder.tvDongia.setText(AppUtils.formatNumber("N0").format(SoDuVatTuDauInfo.getDonGiaThuc()));
        itemRowHolder.tvNgay.setText(AppUtils.formatDateToDateRequest(SoDuVatTuDauInfo.getNgay(), "dd/MM/yyyy"));
    }

}
