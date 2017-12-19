package com.inca.lyretrospect.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inca.lyretrospect.R;
import com.inca.lyretrospect.common.JSInterface;
import com.inca.lyretrospect.global.TFBaseActivity;
import com.inca.lyretrospect.view.MyDialog;

/**
 * Created by Administrator on 2017/12/18.
 */

public class WebViewActivity extends TFBaseActivity{
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        super.setCustomerTitle(false,false,"");
    }

    @Override
    public void initSubViews() {
        webView=(WebView) findViewById(R.id.wv_main);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名,参数2：Java对象名
        webView.addJavascriptInterface(new JSInterface(), "JSInterface");//AndroidtoJS类对象映射到js的test对象
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://192.9.200.67:8080/release_2.0.01_drugtraceability/MDH00002/OP100001/index");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            doDialog();
        }

        return false;
    }

    //自定义的webViewClient
    class MyWebViewClient extends WebViewClient{
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError
                error) {
//                super.onReceivedError(view, request, error);
//            showErrorPage();//显示错误页面
        }

        //设置加载前的函数
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (!url.contains("MD000020/OP000001/selectByCutNo") && !isFinishing()) {
//                CustomProgress.show(WebViewActivity.this, "加载中", false);
            }
        }

        //设置结束加载函数
        @Override
        public void onPageFinished(WebView view, String url) {
//                endLoading.setText("结束加载了");
            if (!url.contains("MD000020/OP000001/selectByCutNo")) {
//                CustomProgress.cancle();
            }
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://baidu.com/")) {
//                ToastUtil.show(WebViewActivity.this, "您没用权限访问", 2000);
                return true;
            } else  if (url.startsWith("tel:")){
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return false;
        }
    }


}
