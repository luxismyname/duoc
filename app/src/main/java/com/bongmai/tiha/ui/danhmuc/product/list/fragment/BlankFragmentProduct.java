package com.bongmai.tiha.ui.danhmuc.product.list.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ProductInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentProduct extends Fragment {


    public BlankFragmentProduct() {
        // Required empty public constructor
    }

    public static BlankFragmentProduct newInstance(ProductInfo productInfo) {
        BlankFragmentProduct fragment = new BlankFragmentProduct();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }
}