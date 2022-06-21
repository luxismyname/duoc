package com.bongmai.tiha.ui.danhmuc.product.allproduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.PaginationAdapterCallback;
import com.bongmai.tiha.ui.danhmuc.loaihang.list.LoaiHangListAdapter;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class GetMultipleProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private BaseRecyclerViewEvent.OnClickListener clickListener;
    private BaseRecyclerViewEvent.OnLongClickListener longClickListener;
    private BaseRecyclerViewEvent.OnButtonClickListener buttonClickListener;
    private BaseRecyclerViewEvent.OnDataSetChangedListener dataSetChangedListener;



    public interface DataSourceChangedListener {
        void onDataSourceChanged();
    }


    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;

    DataSourceChangedListener dataSourceChangedListener;

    private PaginationAdapterCallback mCallback;

    List<ProductInfo> listAllData;
    List<ProductInfo> listSearchData;

    CustomFilterListData customFilterListData;

    private List<ProductInfo> listProductChon;
    Context mContext;

    GetMultipleProductActivityPresenter productListPresenter;

    public GetMultipleProductAdapter(Context context, List<ProductInfo> listAllData) {
        this.listAllData = listAllData;//listAllData;
        this.listSearchData = listAllData;// listAllData;

        this.mContext = context;
        this.productListPresenter = new GetMultipleProductActivityPresenter(null);
        this.listProductChon = new ArrayList<>();
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

    public void setLoadingCallback(PaginationAdapterCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public Filter getFilter() {
        return customFilterListData;
    }



    public List<ProductInfo> getListProductChon() {
        return listProductChon == null ? new ArrayList<>() : listProductChon;
    }

    public void updateListProductChon(ProductInfo productInfo) {
        int pos = -1;
        for (int i = 0; i < getListProductChon().size(); i++) {
            if (productInfo.getProduct_ID().equals(getListProductChon().get(i).getProduct_ID())) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            listProductChon.add(productInfo);
        } else {
            listProductChon.remove(pos);
            if (productInfo.getSoLuong() > 0) {
                listProductChon.add(pos, productInfo);
            }
        }
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

    public ProductInfo getItem(int position) {
        if (listSearchData == null) return null;
        return listSearchData.get(position);
    }

    public List<ProductInfo> getListAllData() {
        return listSearchData;
    }

    public void addItem(ProductInfo item) {
        listSearchData.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int position, ProductInfo productInfo) {
        listSearchData.add(position, productInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }


    public void add(ProductInfo itemAdd) {
        listSearchData.add(itemAdd);
        notifyItemInserted(listSearchData.size() - 1);
    }

    public void addAll(List<ProductInfo> serviceReportResults) {
        for (ProductInfo result : serviceReportResults) {
            listSearchData.add(result);
        }
        notifyDataSetChanged();
    }

    public void updateItem(int position, ProductInfo productInfo) {
        if (position < 0) return;
        listSearchData.remove(position);
        listSearchData.add(position, productInfo);
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
    }


    public void remove(ProductInfo itemRemove) {
        int position = listSearchData.indexOf(itemRemove);
        if (position > -1) {
            listSearchData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setData(List<ProductInfo> productInfos) {
        listSearchData = productInfos;
        listAllData = productInfos;
        notifyDataSetChanged();
        if (dataSetChangedListener != null)
            dataSetChangedListener.onDataSetChanged();
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
                View viewItem = inflater.inflate(R.layout.multi_item_productlist, viewGroup, false);
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
        ProductInfo productInfo = listSearchData.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                final ItemRowHolder itemRowHolder = (ItemRowHolder) holder;
                itemRowHolder.tvMaSanPham.setText(productInfo.getProduct_ID());
                itemRowHolder.tvTenSanPham.setText(productInfo.getProduct_Name());
                itemRowHolder.tvLoaiHang.setText("Loại hàng: " + productInfo.getCategory_ID());
                itemRowHolder.tvGiaBanLe.setText(AppUtils.formatNumber("N0").format(productInfo.getGiaBanLe()));
                itemRowHolder.tvSoLuong.setText(AppUtils.formatNumber("N0").format(productInfo.getSoLuong()));
                if (itemRowHolder.tvSoLuong.getText().toString().equals("0")) {
                    itemRowHolder.btnMinus.setEnabled(false);
                } else {
                    itemRowHolder.btnMinus.setEnabled(true);
                }


                itemRowHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (buttonClickListener != null) {
                            buttonClickListener.onButtonClick(view, position);

                        }

                    }
                });

                itemRowHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (buttonClickListener != null) {
                            buttonClickListener.onButtonClick(view, position);

                        }


                    }
                });
//                productListPresenter.GetHinhAnhByProductID(productInfo.getProduct_ID(), new ProductListContract.Presenter.IOnGetHinhAnhByProductIDFinishedListener() {
//                    @Override
//                    public void onSuccess(String stringResult) {
//                        itemRowHolder.ctlProgress.setVisibility(View.GONE);
//                        itemRowHolder.imgHinhAnh.setImageBitmap(AppUtils.formatStringToBitMap(stringResult));
//                        itemRowHolder.imgHinhAnh.setVisibility(View.VISIBLE);
//
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        itemRowHolder.ctlProgress.setVisibility(View.GONE);
//                        itemRowHolder.imgHinhAnh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_product));
//                        itemRowHolder.imgHinhAnh.setVisibility(View.VISIBLE);
//                    }
//                });
                break;
        }
    }




    /**
     * Header ViewHolder
     */
    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvMaSanPham;
        private TextView tvTenSanPham;
        private TextView tvLoaiHang;
        private TextView tvGiaBanLe;
        private ImageView btnMinus;
        private TextView tvSoLuong;
        private ImageView btnPlus;
        private View ctlProgress;

        public ItemRowHolder(View view) {
            super(view);

            this.tvMaSanPham = view.findViewById(R.id.tvMaSanPham);
            this.tvTenSanPham = view.findViewById(R.id.tvTenSanPham);
            this.tvLoaiHang = view.findViewById(R.id.tvLoaiHang);
            this.tvGiaBanLe = view.findViewById(R.id.tvGiaBanLe);
            this.btnMinus = view.findViewById(R.id.btnMinus);
            this.tvSoLuong = view.findViewById(R.id.tvSoLuong);
            this.btnPlus = view.findViewById(R.id.btnPlus);
//            this.imgHinhAnh = view.findViewById(R.id.imgHinhAnh);
            this.ctlProgress = itemView.findViewById(R.id.ctlProgress);

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

    private class CustomFilterListData extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filterString = charSequence.toString().toUpperCase();
            FilterResults filterResults = new FilterResults();
            final List<ProductInfo> resultList = new ArrayList<>();
            String timKiem;
            for (ProductInfo item : listAllData) {
                timKiem = item.getProduct_Name();
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
            listSearchData = (ArrayList<ProductInfo>) results.values;
            if (dataSourceChangedListener != null)
                dataSourceChangedListener.onDataSourceChanged();
            notifyDataSetChanged();
        }
    }

}
