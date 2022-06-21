package com.bongmai.tiha.ui.customcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bongmai.tiha.R;


public class NhapThoiGianBaoCaoDialog {
    DateDialogAdapter adapterDateDialog;

    public interface DialogClickInterface {
        void onClickPositiveButton(String tuNgay, String denNgay);

        void onClickNegativeButton();
    }

    TextView tvTitle;
    EditText etTuNgay, etDenNgay;
    public static NhapThoiGianBaoCaoDialog mDialog;
    private Context mContext;

    public static NhapThoiGianBaoCaoDialog getInstance() {

        if (mDialog == null)
            mDialog = new NhapThoiGianBaoCaoDialog();
        return mDialog;
    }

    public void showConfirmDialog(final String tuNgay, final String denNgay, Context pContext, final DialogClickInterface dialogClickInterface) {
        mContext = pContext;

        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_nhapthoigianbaocao, null);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        etTuNgay = (EditText) view.findViewById(R.id.etTuNgay);
        etDenNgay = (EditText) view.findViewById(R.id.etDenNgay);

        tvTitle.setText(mContext.getString(R.string.dialog_nhapthoigianbaocao));
        etTuNgay.setText(tuNgay);
        etDenNgay.setText(denNgay);

        builder.setView(view)
                .setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogClickInterface.onClickPositiveButton(etTuNgay.getText().toString(), etDenNgay.getText().toString());
                    }
                })
                .setNegativeButton(mContext.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogClickInterface.onClickNegativeButton();
                    }
                });
        etTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adapterDateDialog = new DateDialogAdapter(v, etTuNgay.getText().toString());
                    android.app.FragmentTransaction ft = ((Activity) mContext).getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        etDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adapterDateDialog = new DateDialogAdapter(v, etDenNgay.getText().toString());
                    android.app.FragmentTransaction ft = ((Activity) mContext).getFragmentManager().beginTransaction();
                    adapterDateDialog.show(ft, "DatePicker");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

}
