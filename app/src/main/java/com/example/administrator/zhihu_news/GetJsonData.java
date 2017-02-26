package com.example.administrator.zhihu_news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class GetJsonData {
    public static List<JsonBean> getDataFromJsonObject(String response){
        List<JsonBean> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            for(int i=0;i<jsonArray.length();i++){
                JsonBean jsonBean = new JsonBean();
                jsonObject = jsonArray.getJSONObject(i);
                jsonBean.newsTitle = jsonObject.getString("title");
                jsonBean.newsImgURL = jsonObject.getString("images");

                jsonBean.newsImgURL = jsonBean.newsImgURL.replace("[", "");
                jsonBean.newsImgURL = jsonBean.newsImgURL.replace("]", "");
                jsonBean.newsImgURL = jsonBean.newsImgURL.replace("\\", "");
                jsonBean.newsImgURL = jsonBean.newsImgURL.replace("\"", "");

                jsonBean.newsId = jsonObject.getString("id");
                newsList.add(jsonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public static List<TopJsonBean> getDataFromJsonObject1(String response){
        List<TopJsonBean> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("top_stories");
            for(int i=0;i<jsonArray.length();i++){
                TopJsonBean jsonBean = new TopJsonBean();
                jsonObject = jsonArray.getJSONObject(i);
                jsonBean.topNewsTitle = jsonObject.getString("title");
                jsonBean.topNewsImgURL = jsonObject.getString("image");
                //Log.d("json解析",jsonBean.topNewsImgURL);
                jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("[", "");
                jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("]", "");
                jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("\\", "");
                jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("\"", "");

                jsonBean.topNewsId = jsonObject.getString("id");
                newsList.add(jsonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public static List<JsonBean> getDataFromJsonObject2(String response){
        List<JsonBean> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            for(int i=0;i<jsonArray.length();i++){
                JsonBean jsonBean = new JsonBean();
                jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.has("images")) {
                    jsonBean.newsImgURL = jsonObject.getString("images");

                    jsonBean.newsImgURL = jsonBean.newsImgURL.replace("[", "");
                    jsonBean.newsImgURL = jsonBean.newsImgURL.replace("]", "");
                    jsonBean.newsImgURL = jsonBean.newsImgURL.replace("\\", "");
                    jsonBean.newsImgURL = jsonBean.newsImgURL.replace("\"", "");
                }else {
                    jsonBean.newsImgURL ="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1487484708&di=3856c574cb15b1936202697983177913&src=http://wenwen.soso.com/p/20091014/20091014191854-1887029992.jpg";
                }
                jsonBean.newsTitle = jsonObject.getString("title");
                jsonBean.newsId = jsonObject.getString("id");
                newsList.add(jsonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public static List<TopJsonBean> getDataFromJsonObject3(String response){
        List<TopJsonBean> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            TopJsonBean jsonBean = new TopJsonBean();
            jsonBean.topNewsTitle = jsonObject.getString("description");

            jsonBean.topNewsImgURL = jsonObject.getString("background");
            jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("[", "");
            jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("]", "");
            jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("\\", "");
            jsonBean.topNewsImgURL = jsonBean.topNewsImgURL.replace("\"", "");

            jsonBean.topNewsId = "0";
            newsList.add(jsonBean);
            } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return newsList;
    }
}
