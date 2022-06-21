package com.bongmai.tiha.ui.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bongmai.tiha.BuildConfig;
import com.bongmai.tiha.MainActivity;
import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.ChangeLogInfo;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity implements TestContract.View{

    TestContract.Presenter presenter;

    TextView version, code, url;

    Button btnUpdate;
    String versionName = "";
    int versionCode = 0;
    ChangeLogInfo logInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        onInit();
        onLoadData();

    }

    private void onLoadData() {
        logInfo = new ChangeLogInfo();
        presenter = new TestPresenter(getApplicationContext(), this);
        presenter.CheckChangeLog();
    }




    void onInit(){
        version = findViewById(R.id.tvVersion);
        code = findViewById(R.id.tvCode);
        url = findViewById(R.id.tvUrl);
        btnUpdate = findViewById(R.id.btnUpdate);

        Context context = getApplicationContext(); // or activity.getApplicationContext()
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

        try {
            versionName = packageManager.getPackageInfo(packageName, 0).versionName;
            versionCode = packageManager.getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        double vsName = Double.parseDouble(versionName);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(logInfo.getLatestVersion() > vsName){

                    final AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                    builder.setTitle("CẢNH BÁO")
                            .setMessage("Phiên bản phần mềm đã cũ, vui lòng cập nhật phiên bản mới để sử dụng")
                            .setCancelable(false)
                            .setPositiveButton("CẬP NHẬT", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int id) {
                                    URL url = null;
                                    try {
                                        url = new URL(AppConstants.URL_UPDATE_FILE_APK);
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }
                                    DownloadFileUpdateAplicationTask downloadFileUpdateAplicationTask = new DownloadFileUpdateAplicationTask(url);
                                    downloadFileUpdateAplicationTask.execute();

                                }
                            })
                            .setNegativeButton(" ", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int id) {

                                }
                            });
                    final AlertDialog alert = builder.create();
                    alert.show();
                }

                }

        });

    }

    @Override
    public void onCheckChangeLogSuccess(ChangeLogInfo changeLogInfo) {
        logInfo = changeLogInfo;
        version.setText(AppUtils.formatNumber("N0").format(changeLogInfo.getLatestVersion()));
        code.setText(String.valueOf(changeLogInfo.getLatestVersionCode()));
        url.setText(changeLogInfo.getUrl());
    }

    @Override
    public void onCheckChangeLogError(String error) {
        CommonUtils.showMessageError(this, error);
    }


    private class DownloadFileUpdateAplicationTask extends AsyncTask<String, String, String> {

        URL url;

        public DownloadFileUpdateAplicationTask(URL url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @SuppressLint("WorldReadableFiles")
        @Override
        protected String doInBackground(String... strings) {

            try {
                String newfileName = "TihaLoc.apk";
                URLConnection conn = url.openConnection();
                conn.connect();
                // getting file length
                int lenghtOfFile = conn.getContentLength();

                FileOutputStream fos;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    File newAPKFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), newfileName);
                    fos = new FileOutputStream(newAPKFile);
                } else {
                    fos = openFileOutput(newfileName, MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
                }
                long total = 0;
                // Download the new APK file
                InputStream is = conn.getInputStream();//httpConn.getInputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                return e.getMessage();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int progress = Integer.parseInt(values[0]);
            progressBarDownload.setProgress(progress);
            tvProgress.setText(String.valueOf(progress) + "%");
            tvTotalProgress.setText(String.valueOf(progress) + "/100");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            showProgressDialog(false);
            if (result == null || result.isEmpty())
                installApk();
        }


    }

    private void installApk() {
        try {
            String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
            String fileName = "TihaLoc.apk";
            filePath += fileName;
            File file = new File(filePath);
            if (file.exists()) {
                String[] fileNameArray = file.getName().split(Pattern.quote("."));
                if (fileNameArray[fileNameArray.length - 1].equals("apk")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri downloaded_apk = getFileUri(file);
                        Intent intent = new Intent(Intent.ACTION_VIEW).setDataAndType(downloaded_apk,
                                "application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file),
                                "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Uri getFileUri(File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    ProgressBar progressBarDownload;
    TextView tvProgress;
    TextView tvTotalProgress;
    AlertDialog alertDialog;


    private void showProgressDialog(boolean isShow) {
        if (isShow) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_progressbar_download, null);
            progressBarDownload = view.findViewById(R.id.progressBar);
            tvProgress = view.findViewById(R.id.tvProgress);
            tvTotalProgress = view.findViewById(R.id.tvTotalProgress);
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Please Wait...")
                    .setCancelable(false)
                    .setView(view)
                    .create();
            alertDialog.show();

        } else {
            alertDialog.dismiss();
        }
    }

}