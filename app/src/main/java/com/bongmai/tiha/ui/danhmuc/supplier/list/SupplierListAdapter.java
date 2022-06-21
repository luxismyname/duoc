package com.bongmai.tiha.ui.danhmuc.supplier.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.PaginationAdapterCallback;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class SupplierListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;


    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;
    private PaginationAdapterCallback mCallback;

    List<SupplierInfo> listAllData;
    List<SupplierInfo> listSearchData;
    Context mContext;

    public SupplierListAdapter(Context context, List<SupplierInfo> listAllData) {
        this.listAllData = new ArrayList<>();//listAllData;
        this.listSearchData = new ArrayList<>();// listAllData;
        this.mContext = context;

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

    public void setLoadingCallback(PaginationAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getItemCount() {
        return listSearchData == null ? 0 : listSearchData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == listSearchData.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public SupplierInfo getItem(int position) {
        if (listSearchData == null) return null;
        return listSearchData.get(position);
    }

    public List<SupplierInfo> getListAllData() {
        return listSearchData;
    }

    public void addItem(SupplierInfo item) {
        listSearchData.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new SupplierInfo());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = listSearchData.size() - 1;
        if (position == -1) return;
        SupplierInfo result = getItem(position);

        if (result != null) {
            listSearchData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(SupplierInfo itemAdd) {
        listSearchData.add(itemAdd);
        notifyItemInserted(listSearchData.size() - 1);
    }

    public void addAll(List<SupplierInfo> serviceReportResults) {
        for (SupplierInfo result : serviceReportResults) {
            listSearchData.add(result);
        }
        notifyDataSetChanged();
    }

    public void remove(SupplierInfo itemRemove) {
        int position = listSearchData.indexOf(itemRemove);
        if (position > -1) {
            listSearchData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */
    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(listSearchData.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.item_supplierlist, viewGroup, false);
                viewHolder = new ItemRowHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress_loadmore, viewGroup, false);
                viewHolder = new LoadingRowHolder(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SupplierInfo supplierInfo = listSearchData.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                final ItemRowHolder itemRowHolder = (ItemRowHolder) holder;
                //set data
                itemRowHolder.tvMaKhachHang.setText(supplierInfo.getSupplier_ID());
                if (TextUtils.isEmpty(supplierInfo.getCompany_Name())) {
                    itemRowHolder.tvTenKhachHang.setText(supplierInfo.getPhone());
                } else {
                    itemRowHolder.tvTenKhachHang.setText(supplierInfo.getCompany_Name());
                }
                itemRowHolder.tvTenKhachHang.setText(supplierInfo.getCompany_Name());
                itemRowHolder.tvDiaChi.setText(AppUtils.GetDiaChi(supplierInfo.getSo(), supplierInfo.getDuong(), supplierInfo.getKhuPho(),
                        supplierInfo.getPhuong(), supplierInfo.getQuan(), supplierInfo.getTinh()));
                itemRowHolder.tvSoDienThoai.setText(GetSoDienThoai(supplierInfo));
                break;
            case LOADING:
                LoadingRowHolder loadingVH = (LoadingRowHolder) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    mContext.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private String GetSoDienThoai(SupplierInfo supplierInfo) {
        String result = "";
        if (!TextUtils.isEmpty(supplierInfo.getPhone()))
            result += supplierInfo.getPhone() + ", ";
        if (!TextUtils.isEmpty(supplierInfo.getDTDD()))
            result += supplierInfo.getDTDD() + ", ";
        if (!TextUtils.isEmpty(supplierInfo.getSoDT1()))
            result += supplierInfo.getPhone() + ", ";
        if (!TextUtils.isEmpty(supplierInfo.getSoDT2()))
            result += supplierInfo.getPhone() + ", ";
        if(!TextUtils.isEmpty(result))
            result = result.substring(0, result.length() - 2);
        return result;
    }


    /**
     * Header ViewHolder
     */
    public class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView
                tvMaKhachHang,
                tvTenKhachHang,
                tvMaSoThue,
                tvDiaChi,
                tvSoDienThoai;
        ImageView imgHinhAnh;

        public ItemRowHolder(View view) {
            super(view);

            this.tvMaKhachHang = view.findViewById(R.id.tvMaKhachHang);
            this.tvTenKhachHang = view.findViewById(R.id.tvTenKhachHang);
            this.tvMaSoThue = view.findViewById(R.id.tvMaSoThue);
            this.tvDiaChi = view.findViewById(R.id.tvDiaChi);
            this.tvSoDienThoai = view.findViewById(R.id.tvSoDienThoai);
            this.imgHinhAnh = view.findViewById(R.id.imgHinhAnh);

            view.setOnClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    clickListener.onClick(v, position);
                }
            });

            view.setOnLongClickListener(v -> {
                if (longClickListener != null) {
                    int position = getAdapterPosition();
                    longClickListener.onLongClick(v, position);
                }
                return true;
            });
        }
    }

    protected class LoadingRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingRowHolder(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:

                    showRetry(false, null);
                    mCallback.retryPageLoad();

                    break;
            }
        }
    }

}