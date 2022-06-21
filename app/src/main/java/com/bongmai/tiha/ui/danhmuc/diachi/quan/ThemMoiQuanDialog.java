package com.bongmai.tiha.ui.danhmuc.diachi.quan;

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
import com.bongmai.tiha.utils.PublicVariables;

public class ThemMoiQuanDialog {

        public interface  DialogClickInterface{
            void onClickPositiveButton(String tenquanviettat, String chitiettenquan);
            void onClickNegativeButton();
        }


        TextView tvTitle;
        EditText etTQVT, etCTTQ;

        public static ThemMoiQuanDialog themMoiQuanDialog;
        private Context mContext;

        public static ThemMoiQuanDialog getInstance(){
            if(themMoiQuanDialog == null)
                themMoiQuanDialog = new ThemMoiQuanDialog();
            return themMoiQuanDialog;
        }

        public void showConfirmDiaglog(String title, final String tenquanviettat, final String chitiettenquan, Context pContext, final DialogClickInterface dialogClickInterface){

            mContext = pContext;
            AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
            LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_themmoiquan, null);
            tvTitle = view.findViewById(R.id.tvTitle);
            etTQVT = view.findViewById(R.id.etTQVT);
            etCTTQ = view.findViewById(R.id.etCTTQ);


            tvTitle.setText(title);
            etTQVT.setText(tenquanviettat);
            etCTTQ.setText(chitiettenquan);


            builder.setView(view).setPositiveButton(mContext.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    dialogClickInterface.onClickPositiveButton(etTQVT.getText().toString(), etCTTQ.getText().toString());
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

//            etMaTinh.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(s == null || s.toString().isEmpty()));
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
            etTQVT.addTextChangedListener(new TextWatcher() {
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

            etCTTQ.addTextChangedListener(new TextWatcher() {
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

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(tenquanviettat)));
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!(TextUtils.isEmpty(chitiettenquan)));


        }
}
