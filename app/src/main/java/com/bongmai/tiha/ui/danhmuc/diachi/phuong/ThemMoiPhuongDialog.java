package com.bongmai.tiha.ui.danhmuc.diachi.phuong;

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


public class ThemMoiPhuongDialog {

    public interface DialogClickInterface{
        void onClickPositiveButton(String tenphuongviettat, String chitiettenphuong);
        void onClickNegativeButton();
    }

    TextView tvTitle;
    EditText etTPVT, etCTTP;
    public static ThemMoiPhuongDialog themMoiPhuongDialog;
    private Context mContext;

    public static ThemMoiPhuongDialog getInstance(){
        if(themMoiPhuongDialog == null)
            themMoiPhuongDialog = new ThemMoiPhuongDialog();
        return themMoiPhuongDialog;

    }

    public void showConfirmDialog(String titlle, final String tenphuongviettat, final String chitiettenphuong, Context pContext, final DialogClickInterface dialogClickInterface){

        mContext = pContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_themmoiphuong, null);
        tvTitle = view.findViewById(R.id.tvTitle);
        etTPVT = view.findViewById(R.id.etTPVT);
        etCTTP = view.findViewById(R.id.etCTTP);
//        etTinhID = view.findViewById(R.id.etMaTinh);
//        etQuanID = view.findViewById(R.id.etMaQuan);

        tvTitle.setText(titlle);
        etTPVT.setText(tenphuongviettat);
        etCTTP.setText(chitiettenphuong);
//        etTinhID.setText(String.valueOf(TinhID));
//        etQuanID.setText(String.valueOf(QuanID));

        builder.setView(view).setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogClickInterface.onClickPositiveButton(etTPVT.getText().toString(), etCTTP.getText().toString());
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

//        etTinhID.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        etQuanID.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        etTPVT.addTextChangedListener(new TextWatcher() {
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

        etCTTP.addTextChangedListener(new TextWatcher() {
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

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(tenphuongviettat)));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(chitiettenphuong)));

    }
}
