package com.greatplan.myapplication.Utils;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greatplan.myapplication.Contract.BaseActivity;
import com.greatplan.myapplication.R;
import com.just.agentweb.AgentWeb;

public class AgentWebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentweb);
        webView = findViewById(R.id.web);

        WebSettings webSettings = webView.getSettings();
        String path = getIntent().getStringExtra("path");
        webView.getSettings().setJavaScriptEnabled(true);//支持js
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.canGoBack();//可以后退
//        webView.goBack();//后退网页
//        webView.canGoForward();//可以前进
//        webView.goForward();//前进
        //清除网页访问留下的缓存  针对整个应用程序
        webView.clearCache(true);
        //清除webview访问历史记录
        webView.clearHistory();
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true);//将图片设置到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕大大小
        //缩放操作
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);//设置内置的缩放控件 若为false  则webview不可缩放
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        //其他细节
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中缓存
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持同伙js打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式



//        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(path);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
//        linearLayout = findViewById(R.id.ll);
//
////        AgentWeb agentWeb = AgentWeb.with(AgentWebActivity.this)
////                .setAgentWebParent(linearLayout,new LinearLayout.LayoutParams(-1,-1))
////                .
//        AgentWeb agentWeb = AgentWeb.with(this)//传入Activity
//                .setAgentWebParent( linearLayout,new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams .useDefaultIndicator()//使用默认进度条
//                .useDefaultIndicator()//使用默认进度条
//                .createAgentWeb()
//                .ready()//重试
//                .go("https://www.baidu.com");
    }


}


