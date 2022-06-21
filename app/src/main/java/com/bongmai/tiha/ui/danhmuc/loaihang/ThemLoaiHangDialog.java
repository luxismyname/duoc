package com.bongmai.tiha.ui.danhmuc.loaihang;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bongmai.tiha.R;
import com.bongmai.tiha.ui.customcontrol.DateDialogAdapter;


public class ThemLoaiHangDialog {
    DateDialogAdapter adapterDateDialog;

    public interface DialogClickInterface {
        void onClickPositiveButton(String maLoai, String tenLoai);

        void onClickNegativeButton();
    }

    TextView tvTitle;
    EditText etMaLoai, etTenLoai;
    public static ThemLoaiHangDialog mDialog;
    private Context mContext;
    AlertDialog dialog;

    public static ThemLoaiHangDialog getInstance() {

        if (mDialog == null)
            mDialog = new ThemLoaiHangDialog();
        return mDialog;
    }

    public void showConfirmDialog(final String maLoai, final String tenLoai, Context pContext, final DialogClickInterface dialogClickInterface) {
        mContext = pContext;

        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_themloaihang, null);
        tvTitle = view.findViewById(R.id.tvTitle);
        etMaLoai = view.findViewById(R.id.etMaLoai);
        etTenLoai = view.findViewById(R.id.etTenLoai);

        tvTitle.setText(mContext.getString(R.string.dialog_themloaihang_tieudeform));
        etMaLoai.setText(maLoai);
        etTenLoai.setText(tenLoai);

        builder.setView(view)
                .setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogClickInterface.onClickPositiveButton(etMaLoai.getText().toString(), etTenLoai.getText().toString());
                    }
                })
                .setNegativeButton(mContext.getResources().getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogClickInterface.onClickNegativeButton();
                    }
                });
        etMaLoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SetEnable(charSequence.toString(), etTenLoai.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etTenLoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SetEnable(etMaLoai.getText().toString(), charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        SetEnable(maLoai, tenLoai);
    }

    private void SetEnable(String maLoai, String tenLoai) {
        if (!TextUtils.isEmpty(maLoai) && !TextUtils.isEmpty(tenLoai)) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        } else {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }

}
