package com.anandniketan.anandniketanskool360shilajTeacher.Adapter;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by admsandroid on 10/26/2017.
 */

class AppWebViewClients extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);

    }

}
