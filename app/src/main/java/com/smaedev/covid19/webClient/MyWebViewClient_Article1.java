package com.smaedev.covid19.webClient;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient_Article1 extends WebViewClient {

    private Activity activity = null;

    public MyWebViewClient_Article1(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        /*view.loadUrl("javascript:(function() { " +
                "var head = document.getElementsByClassName('header')[0].style.display='none'; " +
                "})()");*/
    }
}
