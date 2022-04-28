package com.example.customBarcodeScanner.barcodeScanner.captureActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.customBarcodeScanner.R;
import com.example.customBarcodeScanner.barcodeScanner.shared.ScanConstants;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScanCapture extends CaptureActivity {

    private static final int DELAY = 3000;
    private final Logger LOGGER = LoggerFactory.getLogger(ScanCapture.class);

    private final BarcodeCallback continuousScanCallback = new BarcodeCallback() {
        private long lastTimestamp = 0;

        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                if (System.currentTimeMillis() - lastTimestamp < DELAY) {
                    // Too soon after the last barcode - ignore.
                    return;
                }
                LOGGER.info("the scanned qrcode is  : {}", result.getText());
                beepSound();
                lastTimestamp = System.currentTimeMillis();
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    private DecoratedBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcodeScannerView = initializeContent();
        CaptureManager capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        Button cancel = findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        boolean isContinuous = getIntent().getBooleanExtra(ScanConstants.IS_SCAN_CONTINUOUS, false);
        if (isContinuous) {
            DecodeContinuous();
        } else {
            capture.decode();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void beepSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DecodeContinuous() {
        barcodeScannerView.decodeContinuous(continuousScanCallback);
    }

    @Override
    public DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.scan_capture_layout);
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeScannerView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }
}
