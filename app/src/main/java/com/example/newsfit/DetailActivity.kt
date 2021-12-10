package com.example.newsfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val url=intent.getStringExtra("URL")
        if(url!=null){
            //Bind WebView and if there is any javascript in page set true
            detailWebView.settings.javaScriptEnabled=true

            //Set the webView for mobile version
            detailWebView.settings.userAgentString="Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            //Load Page Event
            detailWebView.webViewClient=object:WebViewClient(){

                //whenever our page load, the below function executes
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility=View.GONE   //When page load we need to hide the Progressbar and show WebView
                    detailWebView.visibility=View.VISIBLE
                }
            }
            detailWebView.loadUrl(url)
        }
    }
}