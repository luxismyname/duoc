package com.bongmai.tiha.ui.danhmuc.product.list;

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
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseRecyclerViewEvent;
import com.bongmai.tiha.ui.customcontrol.PaginationAdapterCallback;
import com.bongmai.tiha.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    List<ProductInfo> listAllData;
    List<ProductInfo> listSearchData;
    Context mContext;

    ProductListPresenter productListPresenter;

    public ProductListAdapter(Context context, List<ProductInfo> listAllData) {
        this.listAllData = new ArrayList<>();//listAllData;
        this.listSearchData = new ArrayList<>();// listAllData;
        this.mContext = context;
        this.productListPresenter = new ProductListPresenter(null);

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

    public void removeItem(int position) {
        listSearchData.remove(position);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ProductInfo());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = listSearchData.size() - 1;
        if (position == -1) return;
        ProductInfo result = getItem(position);

        if (result != null) {
            listSearchData.remove(position);
            notifyItemRemoved(position);
        }
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
                View viewItem = inflater.inflate(R.layout.item_productlist, viewGroup, false);
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


    /**
     * Header ViewHolder
     */
    public class ItemRowHolder extends RecyclerView.ViewHolder {
        private TextView tvMaSanPham;
        private TextView tvTenSanPham;
        private TextView tvLoaiHang;
        private TextView tvGiaBanLe;
//        private ImageView imgHinhAnh;
        private View ctlProgress;

        public ItemRowHolder(View view) {
            super(view);

            this.tvMaSanPham = view.findViewById(R.id.tvMaSanPham);
            this.tvTenSanPham = view.findViewById(R.id.tvTenSanPham);
            this.tvLoaiHang = view.findViewById(R.id.tvLoaiHang);
            this.tvGiaBanLe = view.findViewById(R.id.tvGiaBanLe);
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

}