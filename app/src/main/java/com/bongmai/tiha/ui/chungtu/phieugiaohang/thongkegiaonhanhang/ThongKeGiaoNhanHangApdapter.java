package com.bongmai.tiha.ui.chungtu.phieugiaohang.thongkegiaonhanhang;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;

public class ThongKeGiaoNhanHangApdapter extends RecyclerView.Adapter<ThongKeGiaoNhanHangApdapter.ItemRowHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;

    List<ThongKeGiaoNhanHangInfo> listAllData;
    List<ThongKeGiaoNhanHangInfo> listSearchData;


    Context mContext;
    CustomFilterListData customFilterListData;
    ThongKeGiaoNhanHangPresenter thongKeGiaoNhanHangPresenter;

    public ThongKeGiaoNhanHangApdapter(Context context, List<ThongKeGiaoNhanHangInfo> listAllData) {
        this.listAllData = listAllData;
        this.listSearchData = listAllData;
        this.mContext = context;
        this.customFilterListData = new CustomFilterListData();
        this.thongKeGiaoNhanHangPresenter = new ThongKeGiaoNhanHangPresenter(null);
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

    public ThongKeGiaoNhanHangInfo getItem(int position) {
        return listSearchData.get(position);
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<ThongKeGiaoNhanHangInfo> getListAllData() {
        return listSearchData;
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }

    public void add(ThongKeGiaoNhanHangInfo itemAdd) {
        listSearchData.add(itemAdd);
        notifyItemInserted(listSearchData.size() - 1);
    }

    public void addAll(List<ThongKeGiaoNhanHangInfo> serviceReportResults) {
        for (ThongKeGiaoNhanHangInfo result : serviceReportResults) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_giaonhan_list, viewGroup, false);
        itemRowHolder = new ItemRowHolder(v);
        return itemRowHolder;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        CardView cvMain;
        ConstraintLayout ctlMain, ctlSoCT, ctlDiaChi;
        TextView
                tvTrangThai,
                tvSoCT,
                tvNgay,
                tvTenKH,
                tvDiachigiaohang,
                tvNhapLieu,
                tvNVGH,
                tvTaiXe,
                tvSoXe,
                tvNgayGioGiao,
                tvGhiChu,
                tvSoLuong,
                tvThanhTien,
                tvTrongLuong,
                tvTheTich;

        ImageButton btnMore;


        ConstraintLayout ctlGiaoHang, ctlTaiXe, ctlNgayGioGiao, ctlNotes;

        public ItemRowHolder(View view) {
            super(view);
            this.tvTrangThai = view.findViewById(R.id.tvTrangthai);
            this.tvSoCT = view.findViewById(R.id.tvSoCT);
            this.tvNgay = view.findViewById(R.id.tvNgay);
            this.tvTenKH = view.findViewById(R.id.tvTenKH);
            this.tvDiachigiaohang = view.findViewById(R.id.tvDiaChi);
            this.tvNhapLieu = view.findViewById(R.id.tvEmployee);
            this.tvNVGH = view.findViewById(R.id.tvNVGH);
            this.tvTaiXe = view.findViewById(R.id.tvTaixe);
            this.tvSoXe = view.findViewById(R.id.tvSoXe);
            this.tvNgayGioGiao = view.findViewById(R.id.tvNgayGioGiao);
            this.tvGhiChu = view.findViewById(R.id.tvGhiChu);
            this.ctlMain = view.findViewById(R.id.ctlMain);
            this.btnMore = view.findViewById(R.id.btnMore);
            this.cvMain= view.findViewById(R.id.cvMain);
            this.ctlGiaoHang = view.findViewById(R.id.ctlNhanVienGiaoHang);
            this.ctlTaiXe = view.findViewById(R.id.ctlXe);
            this.ctlNgayGioGiao = view.findViewById(R.id.ctlNgayGioGiao);
            this.ctlNotes = view.findViewById(R.id.ctlGhiChu);
            this.tvSoLuong = view.findViewById(R.id.tvSoLuong);
            this.tvThanhTien = view.findViewById(R.id.tvThanhTien);
            this.tvTrongLuong = view.findViewById(R.id.tvTrongLuong);
            this.tvTheTich = view.findViewById(R.id.tvTheTich);


            this.ctlSoCT = view.findViewById(R.id.ctlSoCT);
            this.ctlDiaChi = view.findViewById(R.id.ctlDiaChi);


//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (clickListener != null) {
//                        clickListener.onClick(v, getAdapterPosition());
//                    }
//                }
//            });
//
//            view.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (longClickListener != null) {
//                        longClickListener.onLongClick(v, getAdapterPosition());
//                    }
//                    return true;
//                }
//            });

            ctlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        clickListener.onClick(view, position);
                    }
                }
            });


            ctlMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        longClickListener.onLongClick(view, position);
                    }
                    return true;
                }
            });

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (buttonClickListener != null) {
                        int position = getAdapterPosition();
                        buttonClickListener.onButtonClick(view, position);
                    }
                }
            });


            ctlDiaChi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (buttonClickListener != null) {
                        int position = getAdapterPosition();
                        buttonClickListener.onButtonClick(view, position);
                    }
                }
            });

            tvSoCT.setOnClickListener(new View.OnClickListener() {
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
            final List<ThongKeGiaoNhanHangInfo> resultList = new ArrayList<>();
            String timKiem;
            for (ThongKeGiaoNhanHangInfo item : listAllData) {
                timKiem = item.getCompanyName() + " " + item.getSoCt() + " " + item.getDiachigiaohang() + " " + item.getDiaChi() + " "
                        + item.getDienThoai() + " " + item.getTenTrangThai() + " " + item.getMaTrangThai() + " " + item.getNgay() + " " + item.getTenTaiXe();
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
            listSearchData = (ArrayList<ThongKeGiaoNhanHangInfo>) results.values;
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
        ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo = listSearchData.get(position);

        itemRowHolder.tvTrangThai.setText(thongKeGiaoNhanHangInfo.getMaTrangThai());

        if (itemRowHolder.tvTrangThai.getText().equals("6")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#d2f0cc"));
        }

        if (itemRowHolder.tvTrangThai.getText().equals("7")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#bbe3b3"));
        }

        if (itemRowHolder.tvTrangThai.getText().equals("8")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#ffe4c4"));
        }

        if (itemRowHolder.tvTrangThai.getText().equals("9")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#ffd900"));
        }

        if (itemRowHolder.tvTrangThai.getText().equals("10")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#d98832"));
        }

        if (itemRowHolder.tvTrangThai.getText().equals("11")){
            itemRowHolder.cvMain.setCardBackgroundColor(Color.parseColor("#7a9e0d"));
        }

        itemRowHolder.tvSoCT.setText(thongKeGiaoNhanHangInfo.getSoCt());
        itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N1").format(thongKeGiaoNhanHangInfo.getSoLuong()));
        itemRowHolder.tvThanhTien.setText(AppUtils.formatNumber("N0").format(thongKeGiaoNhanHangInfo.getThanhTien()));

        if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrator")){
            itemRowHolder.tvThanhTien.setVisibility(View.VISIBLE);
        } else {
            itemRowHolder.tvThanhTien.setVisibility(View.GONE);
        }

        itemRowHolder.tvNgay.setText(AppUtils.formatDateToString(thongKeGiaoNhanHangInfo.getNgay(), "dd/MM/yyyy"));
        itemRowHolder.tvTenKH.setText(thongKeGiaoNhanHangInfo.getCompanyName());
        itemRowHolder.tvDiachigiaohang.setText(thongKeGiaoNhanHangInfo.getDiachigiaohang());
        itemRowHolder.tvNhapLieu.setText(thongKeGiaoNhanHangInfo.getNguoigo());
        itemRowHolder.tvNVGH.setText(thongKeGiaoNhanHangInfo.getTenNguoigiao());
        itemRowHolder.tvNVGH.setTextColor(Color.parseColor("#0324fc"));
        itemRowHolder.tvTrongLuong.setText(AppUtils.formatNumber("N0").format(thongKeGiaoNhanHangInfo.getTrongLuong()) + " kg");
        itemRowHolder.tvTheTich.setText(AppUtils.formatNumber("N0").format(thongKeGiaoNhanHangInfo.getTheTich()) +"m3");

        if(TextUtils.isEmpty(itemRowHolder.tvNVGH.getText())){
            itemRowHolder.tvNVGH.setText("Chưa có NVGH");
            itemRowHolder.tvNVGH.setTextColor(Color.parseColor("#000000"));
        }

        itemRowHolder.tvSoXe.setText(thongKeGiaoNhanHangInfo.getSoXe());
        itemRowHolder.tvTaiXe.setText(thongKeGiaoNhanHangInfo.getTenTaiXe());
        if (TextUtils.isEmpty(itemRowHolder.tvTaiXe.getText()) && TextUtils.isEmpty(itemRowHolder.tvSoXe.getText())){
            itemRowHolder.ctlTaiXe.setVisibility(View.GONE);
        }

        itemRowHolder.tvNgayGioGiao.setText(thongKeGiaoNhanHangInfo.getNgaygiogiao());
        if (TextUtils.isEmpty(itemRowHolder.tvNgayGioGiao.getText())){
            itemRowHolder.ctlNgayGioGiao.setVisibility(View.GONE);
        }

        itemRowHolder.tvGhiChu.setText(thongKeGiaoNhanHangInfo.getGhichu());

        if (TextUtils.isEmpty(itemRowHolder.tvGhiChu.getText())){
            itemRowHolder.ctlNotes.setVisibility(View.GONE);
        }

    }
}
