# Custom-Barcode-Button
## Benefits
<ul>
  <li>Can scan both barcodes and QR codes</li>
  <li>Uses ZXing ("zebra crossing"), an open-source, multi-format 1D/2D barcode image processing library implemented in Java</li>
</ul> 

## Description
this projet is a simple implementation of zxing library for scannig qr code & barcodes.
[ScanButton ](https://github.com/Walid-Ji/Custom-Barcode-Button/blob/main/app/src/main/java/com/example/customBarcodeScanner/barcodeScanner/customView/ScanButton.java) is a custom view that 
was implemented to avoid duplicationg code whenerver we need to implement a scan feature.
[ScanButton ](https://github.com/Walid-Ji/Custom-Barcode-Button/blob/main/app/src/main/java/com/example/customBarcodeScanner/barcodeScanner/customView/ScanButton.java) handles both scan scenarios :
<ul>
  <li>Scan once</li>
  <li>Scan continuously</li>
</ul> 

## Implementation 
to use this custom view , first you need to import custom view to your layout :

      <com.example.customBarcodeScanner.barcodeScanner.customView.ScanButton
        android:id="@+id/scan_button_custom"
        android:layout_width="match_parent"
        android:gravity="center_vertical"
        android:layout_height="wrap_content" />
        
then implement the scan interface within your hosting fragment :

    public class ScanFragment extends Fragment implements ScanButton.Scan 
    
you can specify whether you want to scan continuously or only once using the isScanContinuous method provided by ScanButton.scan.

        
