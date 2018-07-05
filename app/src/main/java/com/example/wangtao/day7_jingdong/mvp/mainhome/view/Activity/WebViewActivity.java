package com.example.wangtao.day7_jingdong.mvp.mainhome.view.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangtao.day7_jingdong.R;

public class WebViewActivity extends AppCompatActivity {

    private String mimetype;
    private String encoding;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView =findViewById(R.id.web_webview);

        mimetype = "text/html";
        encoding = "utf-8";
        webView.loadData("<a href ='http://blog.csdn.net/imyang2007?viewmode=contents'>Young's Blog</a>", mimetype, encoding);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        Intent intent=getIntent();
        url = intent.getStringExtra("url");
        webView.loadUrl(url);
              /*
        webView.loadData(url,mimetype,encoding);
              */
       /* webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return false;
            }
        });*/
       // webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

}
