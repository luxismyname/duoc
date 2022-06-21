package com.bongmai.tiha.ui.danhmuc.diachi.duong;

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
import com.bongmai.tiha.ui.danhmuc.diachi.DiaChiListActivity;

public class ThemMoiDuongDialog {



    public interface DialogClickInterface{
            void onClickPositiveButton(String tenduongviettat, String chitiettenduong);
            void onClickNegativeButton();
    }

    TextView tvTitle;
//    EditText etTDVT, etCTTD, etTinhID, etQuanID;
    EditText etTDVT, etCTTD;
    public static ThemMoiDuongDialog themMoiDuongDialog;
    private Context mContext;

    public static ThemMoiDuongDialog getInstance(){
        if(themMoiDuongDialog == null)
            themMoiDuongDialog = new ThemMoiDuongDialog();
            return themMoiDuongDialog;

    }

    public void showConfirmDialog(String titlle, final String tenduongviettat, final String chitiettenduong, Context pContext, final  DialogClickInterface dialogClickInterface){

        mContext = pContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_themmoiduong, null);
        tvTitle = view.findViewById(R.id.tvTitle);
        etTDVT = view.findViewById(R.id.etTDVT);
        etCTTD = view.findViewById(R.id.etCTTD);
//        etTinhID = view.findViewById(R.id.etMaTinh);
//        etQuanID = view.findViewById(R.id.etMaQuan);

        tvTitle.setText(titlle);
        etTDVT.setText(tenduongviettat);
        etCTTD.setText(chitiettenduong);
//        etTinhID.setText(String.valueOf(TinhID));
//        etQuanID.setText(String.valueOf(QuanID));



        builder.setView(view).setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogClickInterface.onClickPositiveButton(etTDVT.getText().toString(), etCTTD.getText().toString());
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
        etTDVT.addTextChangedListener(new TextWatcher() {
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

        etCTTD.addTextChangedListener(new TextWatcher() {
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

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(tenduongviettat)));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(chitiettenduong)));

    }


}
