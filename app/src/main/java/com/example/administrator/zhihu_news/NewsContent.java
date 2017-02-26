package com.example.administrator.zhihu_news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/17.
 */

public class NewsContent extends AppCompatActivity{
    private static String CONTENT_URL = "http://news-at.zhihu.com/api/4/news/";
    String body,id,news,images,css;
    private WebView webView;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        news = CONTENT_URL+id;
        AsyncNetwork.get(news, new AsyncNetwork.Callback() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    body = jsonObject.getString("body");
                    images = jsonObject.getString("images");
                    images = images.replace("[", "");
                    images = images.replace("]", "");
                    images = images.replace("\\", "");
                    images = images.replace("\"", "");
                    body = body.replace("<div class=\"img-place-holder\"></div>","<div class=\"img-place-holder\"><img src=\""+images+"\"></div>");
                    css = jsonObject.getString("css");
                    css = css.replace("[", "");
                    css = css.replace("]", "");
                    css = css.replace("\\", "");
                    css = css.replace("\"", "");
                    Log.d("cssContent=",css);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            css = Network.get(css);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    webView.loadDataWithBaseURL("","<style>"+css+"</style>"+body,"text/html","UTF-8","");
                                }
                            });
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
