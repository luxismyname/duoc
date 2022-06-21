package com.bongmai.tiha.ui.khac;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import com.bongmai.tiha.R;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QuetMaVachActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, View.OnClickListener {
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    //0 la camera sau, 1 la camera truoc
    private int mCameraId = 0;
    ViewGroup contentFrame;
    Toolbar toolbar;
    ImageButton btnCameraID, btnFlash, btnScanPicture;
    private static final int ZXING_GALLERY_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quetmavach);
        mFlash = false;
        mAutoFocus = true;
        mSelectedIndices = null;
        findViewById();
        setupToolbar();
        setupScannerView();
    }

    private void findViewById() {
        contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnCameraID = (ImageButton) findViewById(R.id.btnCameraID);
        btnFlash = (ImageButton) findViewById(R.id.btnFlash);
        btnScanPicture = (ImageButton) findViewById(R.id.btnScanPicture);

        btnCameraID.setOnClickListener(this);
        btnFlash.setOnClickListener(this);
        btnScanPicture.setOnClickListener(this);
    }

    private void setupScannerView() {
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Quét mã vạch");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        //chuyen doi camera truoc va sau
        if (view.getId() == btnCameraID.getId()) {
            onPause();
            mFlash = false;
            btnFlash.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
            mCameraId = mCameraId == 0 ? 1 : 0;
            onResume();

        }
        //bat tat den flash
        else if (view.getId() == btnFlash.getId()) {
            mFlash = !mFlash;
            if (mFlash) {
                btnFlash.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_on));
                Toast.makeText(this, getResources().getString(R.string.title_flash_on), Toast.LENGTH_LONG).show();
            } else {
                btnFlash.setImageDrawable(getResources().getDrawable(R.drawable.ic_flash_off));
                Toast.makeText(this, getResources().getString(R.string.title_flash_off), Toast.LENGTH_LONG).show();
            }
            mScannerView.setFlash(mFlash);
        }
        //quet ma vach tu hinh anh co san
        else if (view.getId() == btnScanPicture.getId()) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ZXING_GALLERY_PERMISSION);
                    return;
                }
            }

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
//            scanQRImage(BitmapFactory.decodeResource(getResources(), R.drawable.qrphuocthien));



//            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//            photoPickerIntent.setType("image/*");
//            startActivityForResult(photoPickerIntent, 0);

//            InputStream is = new BufferedInputStream(new FileInputStream(file));
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            String decoded = scanQRImage(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                //String decoded = scanQRImage(selectedImage);

//                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context)
//                        .build();
//                if(barcode.isOperational()){
//                    SparseArray<Barcode> sparseArray = barcodeDetector.detect(frame);
//                    if(sparseArray != null && sparseArray.size() > 0){
//                        for (int i = 0; i < sparseArray.size(); i++){
//                            Log.d(LOG_TAG, "Value: " + sparseArray.valueAt(i).rawValue + "----" + sparseArray.valueAt(i).displayValue);
//                            Toast.makeText(LOG_TAG, sparseArray.valueAt(i).rawValue, Toast.LENGTH_SHORT).show();
//
//                        }
//                    }else {
//                        Log.e(LOG_TAG,"SparseArray null or empty");
//                    }
//
//                }else{
//                    Log.e(LOG_TAG, "Detector dependencies are not yet downloaded");
//                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public static String scanQRImage(Bitmap bMap) {
        String contents = null;

        int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
        //copy pixel data from the Bitmap into the 'intArray' array
        bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());

        LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Reader reader = new MultiFormatReader();
        try {
            Result result = reader.decode(bitmap);
            contents = result.getText();
        } catch (Exception e) {
            Log.e("QrTest", "Error decoding barcode", e);
        }

        return contents;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(mCameraId);
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        try {

//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.);
//            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//            r.play();
            MediaPlayer mp = MediaPlayer.create(this, R.raw.audio_barcode);
            mp.start();
            Intent intent = new Intent();
            intent.putExtra("Code", result.getText());
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Contents = " + result.getText() + ", Format = " + result.getBarcodeFormat().toString())
//                .setTitle("Scan Results");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//
//            }
//        });
//        final AlertDialog alert = builder.create();
//        alert.show();
    }


}
