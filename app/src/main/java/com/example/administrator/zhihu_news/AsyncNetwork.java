package com.example.administrator.zhihu_news;

import android.os.Handler;

/**
 * Created by Administrator on 2017/2/17.
 */

public class AsyncNetwork {
    public static void get(final String url, final Callback callback) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = Network.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    public interface Callback {
        void onResponse(String response);
    }
}
