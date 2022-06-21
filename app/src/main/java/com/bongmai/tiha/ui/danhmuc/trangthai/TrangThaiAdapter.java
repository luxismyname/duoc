package com.bongmai.tiha.ui.danhmuc.trangthai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.TrangThaiLoaiPhieuInfo;

import java.util.List;

public class TrangThaiAdapter  extends BaseAdapter {
    Context context;
    List<TrangThaiLoaiPhieuInfo> listAllData;

    public TrangThaiAdapter(Context applicationContext, List<TrangThaiLoaiPhieuInfo> listAllData) {
        this.context = applicationContext;
        this.listAllData = listAllData;
    }

    @Override
    public int getCount() {
        return listAllData.size();
    }

    @Override
    public Object getItem(int i) {
        return listAllData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_spinner, parent, false);
        TextView label = row.findViewById(R.id.tvName);
        label.setText(listAllData.get(position).getTenTrangThai());
        switch (listAllData.get(position).getMaTrangThai()) {
            case "ALL":
                //label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_bandau));
                break;

            case "6":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_chodonggoi_6));
                break;
            case "7":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_dadonggoi_7));
                break;
            case "8":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_chodangkygiao_8));
                break;
            case "9":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_chogiaonhanlayhang_9));
                break;
            case "10":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_danggiao_10));
                break;
            case "11":
                label.setBackgroundColor(context.getResources().getColor(R.color.danhsachcuocgoi_trangthai_dagiao_11));
                break;
        }
        return row;
    }
}
