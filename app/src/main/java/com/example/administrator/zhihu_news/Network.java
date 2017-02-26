package com.example.administrator.zhihu_news;

import android.accounts.NetworkErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Network {
    public static String get(String url){
        HttpURLConnection connection = null;
        try{
            URL mURL = new URL(url);
            connection = (HttpURLConnection) mURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            connection.setReadTimeout(5*1000);
            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                InputStream is = connection.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            }else {
                throw new NetworkErrorException("response status is　" + responseCode);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return null;
    }
    private static String getStringFromInputStream(InputStream is)throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }
}
