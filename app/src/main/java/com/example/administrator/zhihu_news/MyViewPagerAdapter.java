package com.example.administrator.zhihu_news;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MyViewPagerAdapter extends PagerAdapter{
    private List<View> views;
    private Context context;
    private List<TopJsonBean> jsonBeanList = new ArrayList<>();

    public MyViewPagerAdapter(List<View>views,List<TopJsonBean> jsonBeanList,Context context){
        super();
        this.views = views;
        this.jsonBeanList = jsonBeanList;
        this.context = context;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView(views.get(position));
    }
    @Override
    public Object instantiateItem(final ViewGroup container, final int position){
        container.addView(views.get(position));
        View view = views.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = jsonBeanList.get(position).topNewsId;
                Intent intent = new Intent(context,NewsContent.class);
                intent.putExtra("id",data);
                context.startActivity(intent);
            }
        });
        return views.get(position);
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
