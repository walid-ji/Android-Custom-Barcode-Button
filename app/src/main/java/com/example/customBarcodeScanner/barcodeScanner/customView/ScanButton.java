package com.example.customBarcodeScanner.barcodeScanner.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customBarcodeScanner.R;
import com.example.customBarcodeScanner.barcodeScanner.captureActivity.ScanCapture;
import com.example.customBarcodeScanner.barcodeScanner.shared.ScanConstants;
import com.google.zxing.integration.android.IntentIntegrator;

public class ScanButton extends LinearLayout {

    private Scan scan;

    public ScanButton(Context context) {
        super(context);
        init();
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScanButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ScanButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.scan_button, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Button scan = findViewById(R.id.scan_button);
        scan.setOnClickListener(v -> onScanClick());
    }

    private void onScanClick() {
        if (scan == null) {
            throw new UnsupportedOperationException("Please implement Scan interface");
        }
        IntentIntegrator result = IntentIntegrator.forSupportFragment(scan.getFragment());
        result.addExtra(ScanConstants.IS_SCAN_CONTINUOUS, scan.isScanContinuous());
        result.setPrompt("Start scanning")
                .setOrientationLocked(true)
                .setBeepEnabled(false)
                .setBarcodeImageEnabled(false)
                .setCaptureActivity(ScanCapture.class)
                .initiateScan();
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public interface Scan {

        Fragment getFragment();

        boolean isScanContinuous();
    }

}
