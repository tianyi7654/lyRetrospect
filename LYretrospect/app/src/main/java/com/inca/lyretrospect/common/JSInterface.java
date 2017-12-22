package com.inca.lyretrospect.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.webkit.JavascriptInterface;

import com.google.zxing.integration.android.IntentIntegrator;
import com.inca.administrator.opensourcelibrary.utils.ToastUtils;
import com.inca.lyretrospect.activity.PersonActivity;
import com.inca.lyretrospect.activity.ScanActivity;
import com.inca.lyretrospect.activity.WebViewActivity;

/**
 * Created by Administrator on 2017/9/6.
 */

public class JSInterface {
    WebViewActivity webViewActivity=(WebViewActivity) WebViewActivity.instance;

    @JavascriptInterface
    public void goBack() {
        webViewActivity .finish();
    }

    @JavascriptInterface
    public void goPerson() {
        webViewActivity.startActivity(new Intent(webViewActivity, PersonActivity.class));
    }
    @JavascriptInterface
    public void scan(){
        if (ContextCompat.checkSelfPermission(webViewActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showToast(webViewActivity,"没有添加相机权限！请前往手机设置");
        }else {
            IntentIntegrator integrator = new IntentIntegrator(webViewActivity);
// 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
            integrator.setCaptureActivity(ScanActivity.class);
            integrator.setPrompt("请扫描"); //底部的提示文字，设为""可以置空
            integrator.setCameraId(0); //前置或者后置摄像头
            integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        }
    }
}
