package com.bongmai.tiha.ui.chungtu.phieuxuat.list;

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
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiPresenter;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class PhieuBanSiListAdapter extends RecyclerView.Adapter<PhieuBanSiListAdapter.ItemRowHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<DanhSachXuatInfo> listAllData;
    List<DanhSachXuatInfo> listSearchData;


    Context mContext;
    CustomFilterListData customFilterListData;
    PhieuBanSiPresenter phieuBanSiPresenter;

    public PhieuBanSiListAdapter(Context context, List<DanhSachXuatInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.phieuBanSiPresenter = new PhieuBanSiPresenter(null);
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

    public DanhSachXuatInfo getItem(int position) {
        return listSearchData.get(position);
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<DanhSachXuatInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }

    public void add(DanhSachXuatInfo itemAdd) {
        listSearchData.add(itemAdd);
        notifyItemInserted(listSearchData.size() - 1);
    }

    public void addAll(List<DanhSachXuatInfo> serviceReportResults) {
        for (DanhSachXuatInfo result : serviceReportResults) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phieubansilist, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvSoCT,
                tvTongTien,
                tvTenKH,
                tvTongSL,
                tvDiaChi,
                tvSoDienThoai,
                tvNgay,
                tvDDBH;

        View cltPrint;

        public ItemRowHolder(View view) {
            super(view);
            this.tvSoCT = view.findViewById(R.id.tvSoCT);
            this.tvTongTien = view.findViewById(R.id.tvTongTien);
            this.tvTenKH = view.findViewById(R.id.tvTenKH);
            this.tvTongSL = view.findViewById(R.id.tvTongSL);
            this.tvDiaChi = view.findViewById(R.id.tvDiaChi);
            this.tvSoDienThoai = view.findViewById(R.id.tvSoDienThoai);
            this.tvNgay = view.findViewById(R.id.tvNgay);
            this.tvDDBH = view.findViewById(R.id.tvDDBH);
            this.cltPrint = view.findViewById(R.id.cltPrint);
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

            cltPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (buttonClickListener != null) {
                        int position = getAdapterPosition();
                        buttonClickListener.onButtonClick(view, position);
                    }
                }
            });
        }
    }

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<DanhSachXuatInfo> resultList = new ArrayList<>();
            String timKiem;
            for (DanhSachXuatInfo item : listAllData) {
                timKiem = item.getCty() + " " + item.getSoCt() + " " + item.getTT();
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
            listSearchData = (ArrayList<DanhSachXuatInfo>) results.values;
            listSearchData = listSearchData == null ? new ArrayList<>() : listSearchData;
            if (dataSetChangedListener != null)
                dataSetChangedListener.onDataSetChanged();
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, final int position) {
        DanhSachXuatInfo danhSachXuatInfo = listSearchData.get(position);
        itemRowHolder.tvSoCT.setText(danhSachXuatInfo.getSoCt());
        itemRowHolder.tvTongSL.setText(String.valueOf(danhSachXuatInfo.getSL() + " món"));
        itemRowHolder.tvTongTien.setText(AppUtils.formatNumber("N0").format(danhSachXuatInfo.getTT()));
        itemRowHolder.tvTenKH.setText(danhSachXuatInfo.getTenDDnguoimua());
        itemRowHolder.tvDiaChi.setText(danhSachXuatInfo.getDiaChi());
        itemRowHolder.tvSoDienThoai.setText(danhSachXuatInfo.getDienThoai());
        itemRowHolder.tvNgay.setText(AppUtils.formatDateToString(danhSachXuatInfo.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvDDBH.setText("Người bán: " + danhSachXuatInfo.getTenDDBH());
    }
}