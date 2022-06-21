package com.bongmai.tiha.ui.base;

import android.view.View;

public class BaseRecyclerViewEvent {
    public interface OnClickListener {
        void onClick(View view, int position);
    }

    public interface OnLongClickListener {
        void onLongClick(View view, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(View view, int position);
    }

    public interface OnDataSetChangedListener {
        void onDataSetChanged();
    }
}
