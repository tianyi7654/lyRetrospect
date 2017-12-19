package com.inca.lyretrospect.common;

import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.inca.administrator.opensourcelibrary.utils.ToastUtils;
import com.inca.lyretrospect.activity.PersonActivity;
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

}
