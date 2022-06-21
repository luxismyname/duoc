package com.bongmai.tiha.ui.danhmuc.diachi.tinh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.ui.danhmuc.diachi.quan.ThemMoiQuanDialog;

public class ThemMoiTinhThanhPhoDialog {

    public interface DialogClickInterface{
        void onClickPositiveButton(String tentinhviettat, String chitiettentinh);
        void onClickNegativeButton();
    }

    TextView tvTitle;
    EditText etTenTinhVietTat, etChiTietTenTinh;

    public static ThemMoiTinhThanhPhoDialog themMoiTinhThanhPhoDialog;
    private Context mContext;

    public static  ThemMoiTinhThanhPhoDialog getInstance(){

        if(themMoiTinhThanhPhoDialog == null)
            themMoiTinhThanhPhoDialog = new ThemMoiTinhThanhPhoDialog();

        return themMoiTinhThanhPhoDialog;
    }

    public void showConfirmDialog(String title, final String tenTinhVietTat, final String chiTietTenTinh,  Context pContext, DialogClickInterface dialogClickInterface){

        mContext = pContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_themmoitinh, null);

        tvTitle = view.findViewById(R.id.tvTitle);
        etTenTinhVietTat = view.findViewById(R.id.etTenTinhVietTat);
        etChiTietTenTinh = view.findViewById(R.id.etTinhThanhPhoChiTiet);

        tvTitle.setText(title);
        etTenTinhVietTat.setText(tenTinhVietTat);
        etChiTietTenTinh.setText(chiTietTenTinh);

        builder.setView(view).setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogClickInterface.onClickPositiveButton(etTenTinhVietTat.getText().toString(), etChiTietTenTinh.getText().toString());
            }
        });

        builder.setView(view).setNegativeButton(mContext.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogClickInterface.onClickNegativeButton();
            }
        });

        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();

        etTenTinhVietTat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etChiTietTenTinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        builder.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(tenTinhVietTat)));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(chiTietTenTinh)));

    }
}
