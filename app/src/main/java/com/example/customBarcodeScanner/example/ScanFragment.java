package com.example.customBarcodeScanner.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customBarcodeScanner.barcodeScanner.customView.ScanButton;
import com.example.customBarcodeScanner.databinding.FragmentScanBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScanFragment extends Fragment implements ScanButton.Scan {

    private FragmentScanBinding binding;
    private final Logger LOGGER = LoggerFactory.getLogger(ScanFragment.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.scanButtonCustom.setScan(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        binding.textScanned.setText("the code scanned :"+ result.getContents());
        LOGGER.info("the scanned qrcode is  : {}", result.getContents());    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public boolean isScanContinuous() {
        return false;
    }
}