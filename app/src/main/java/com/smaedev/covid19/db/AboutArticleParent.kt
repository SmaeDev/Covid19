package com.smaedev.covid19.db

import android.app.Activity
import android.webkit.WebView
import com.smaedev.covid19.ui.about.ExpandableRecyclerViewAdapter
import com.smaedev.covid19.webClient.MyWebViewClient_Article1

data class AboutArticleParent(val name: String) : ExpandableRecyclerViewAdapter.ExpandableGroup<AboutArticleChild>() {

    private var webView: WebView? = null


    var webViewClient: MyWebViewClient_Article1 = MyWebViewClient_Article1(Activity())
    //webView.setWebViewClient(webViewClient)

    override fun getExpandingItems(): WebView{

        return webView!!
    }


}
