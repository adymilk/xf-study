package com.lzy.headerviewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

public class webview1 extends AppCompatActivity {
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //设置沉浸的颜色
        tintManager.setStatusBarTintResource(R.color.colorPrimary);
        init("https://mp.weixin.qq.com/s?__biz=MzA4MTg4MjkzMw==&mid=406710215&idx=1&sn=32d43c2cc1a2e47f297b51101148f20d&scene=0&key=b28b03434249256bc258afbcb4cca01388b9482d6371eec6500fe0823ac90eba328f03a05a97eb0edea133b447401748&ascene=7&uin=MjQzMjgzMzgyNA%3D%3D&devicetype=android-19&version=26031031&nettype=WIFI&pass_ticket=9%2BOyU9tX1tdk4bwTetM1zp%2F0QROgYubRKxv0l1tuJI5%2BT0BikBiJ61koGWBe5G%2Bi");
    }

    public  void init(String url){
        android.webkit.WebView webView = (android.webkit.WebView) findViewById(R.id.webView1);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);

        //WebView加载web资源
        webView.loadUrl(url);
        Toast.makeText(getApplicationContext(),"正在加载...",Toast.LENGTH_SHORT).show();
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
