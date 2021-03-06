package com.bongmai.tiha.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import com.bongmai.tiha.R;

import static android.content.Context.LOCATION_SERVICE;


public class CommonUtils {
    public static void configToolbar(Context mContext, ActionBar actionBar, String tieuDeForm, int indexImage) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(tieuDeForm);
        switch (indexImage) {
            case 0:
                actionBar.setHomeAsUpIndicator(mContext.getResources().getDrawable(R.drawable.ic_arrow_back));
                break;
            case 1:
                actionBar.setHomeAsUpIndicator(mContext.getResources().getDrawable(R.drawable.ic_close_form));
                break;
            default:
                break;
        }

    }

    public static void showMessageError(Context context, String error) {
        error = error.isEmpty() ? context.getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(context)) {
            error = context.getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.title_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showMessage(Context context, String error) {
        error = error.isEmpty() ? context.getResources().getString(R.string.error_msg_unknown) : error;
        if (!NetworkUtils.isNetworkConnected(context)) {
            error = context.getResources().getString(R.string.error_msg_no_internet);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.title_not_error_msg))
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean checkLocation(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
