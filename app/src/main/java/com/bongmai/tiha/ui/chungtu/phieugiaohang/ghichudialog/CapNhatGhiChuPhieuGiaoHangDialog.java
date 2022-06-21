package com.bongmai.tiha.ui.chungtu.phieugiaohang.ghichudialog;

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

public class CapNhatGhiChuPhieuGiaoHangDialog {


    public interface DialogClickInterface{
        void onClickPositiveButton(String soPhieu, String ghiChu);
        void onClickNegativeButton();
    }

    TextView tvTitle;
    EditText etSoCT, etGhiChu;
    public static CapNhatGhiChuPhieuGiaoHangDialog capNhatGhiChuPhieuGiaoHangDialog;
    private Context mContext;

    public static CapNhatGhiChuPhieuGiaoHangDialog getInstance(){
        if(capNhatGhiChuPhieuGiaoHangDialog == null)
            capNhatGhiChuPhieuGiaoHangDialog = new CapNhatGhiChuPhieuGiaoHangDialog();
        return capNhatGhiChuPhieuGiaoHangDialog;

    }

    public void showConfirmDialog(String titlle, final String soPhieu, final String ghiChu, Context pContext, final CapNhatGhiChuPhieuGiaoHangDialog.DialogClickInterface dialogClickInterface){

        mContext = pContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_capnhatghichuphieugiaohang, null);
        tvTitle = view.findViewById(R.id.tvTitle);
        etSoCT = view.findViewById(R.id.etSoCT);
        etGhiChu = view.findViewById(R.id.etGhiChu);

        tvTitle.setText(titlle);
        etSoCT.setText(soPhieu);
        etGhiChu.setText(ghiChu);



        builder.setView(view).setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogClickInterface.onClickPositiveButton(etSoCT.getText().toString(), etGhiChu.getText().toString());
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

        final AlertDialog dialog = builder.create();

        etSoCT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etGhiChu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        builder.setCancelable(false);

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(soPhieu)));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(ghiChu)));

    }


}
