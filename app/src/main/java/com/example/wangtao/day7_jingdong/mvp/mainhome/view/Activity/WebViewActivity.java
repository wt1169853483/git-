package com.example.wangtao.day7_jingdong.mvp.mainhome.view.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.wangtao.day7_jingdong.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView =findViewById(R.id.web_webview);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(webSetting.getMixedContentMode());
        }*/

        webView.loadUrl(url);
    }
}
