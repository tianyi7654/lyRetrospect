package com.inca.lyretrospect.activity;

import android.os.Bundle;

import com.inca.lyretrospect.R;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.Size;

/**
 * Created by Administrator on 2017/12/19.
 */

public class ScanActivity extends CaptureActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.zxing_mycapture);
        DecoratedBarcodeView decoratedBarcodeView=(DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
//        decoratedBarcodeView.setStatusText("wwwwwwwwwww");
//        BarcodeView barcodeView =new BarcodeView(this);
//        barcodeView.setMarginFraction(0.222);
//        barcodeView.setFramingRectSize(new Size(240,61));
//        Size ss =barcodeView.getFramingRectSize();
//        decoratedBarcodeView.getViewFinder().setCameraPreview(barcodeView);
//        decoratedBarcodeView.getViewFinder().drawViewfinder();
//        decoratedBarcodeView.setTorchOn();//开灯
        return decoratedBarcodeView;
    }
}