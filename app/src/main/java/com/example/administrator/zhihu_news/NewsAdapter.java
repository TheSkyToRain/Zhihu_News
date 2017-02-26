package com.example.administrator.zhihu_news;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private View mHeaderView;
    private View mFooterView;
    public View getHeaderView(){
        return mHeaderView;
    }
    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView(){
        return mFooterView;
    }
    public void setFooterView(View footerView){
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }
    @Override
    public int getItemViewType(int position){
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            return TYPE_HEADER;
        }
        if (position == getItemCount()-1){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    private Context mContext;
    private AsyncImageLoader loader = new AsyncImageLoader();
    private List<JsonBean> NewsList ;
    private List<TopJsonBean> TopNewsList;
    private int m;
    private List<View> views = new ArrayList<>();
    private MyViewPagerAdapter pagerAdapter;
    ViewPager viewPager;
    NewsAdapter(Context context,List<JsonBean> newsList,List<TopJsonBean> topNewsList,int m){
        this.mContext = context;
        this.NewsList = newsList;
        this.TopNewsList = topNewsList;
        this.m = m;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER){
            return new MyHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER){
            return new MyHolder(mFooterView);
        }
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            holder.textView.setText(NewsList.get(position-1).newsTitle);
            holder.imageView.setImageDrawable(loader.loadDrawable(NewsList.get(position-1).newsImgURL,new CallbackImpl(holder.imageView)));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("id",NewsList.get(position-1).newsId);
                    intent.setClass(mContext,NewsContent.class);
                    mContext.startActivity(intent);
                }
            });
        }
        else if(getItemViewType(position) == TYPE_HEADER){
            setPagerView(TopNewsList,m);
        }else {
            return;
        }
    }
    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null){
            return NewsList.size();
        }else if(mFooterView == null && mHeaderView != null){
            return NewsList.size()+1;
        }else if(mHeaderView == null && mFooterView != null){
            return NewsList.size()+1;
        }else{
            return NewsList.size()+2;
        }
    }
    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView){
                viewPager = (ViewPager) itemView.findViewById(R.id.viewpager);
            }
            if(itemView == mFooterView){
                return;
            }
            textView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public void setPagerView(List<TopJsonBean> topNews,int m){
        if(m == 0) {
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView1 = (ImageView) view1.findViewById(R.id.top_stories_img);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView1 = (TextView) view1.findViewById(R.id.top_stories_title);
            imageView1.setImageDrawable(loader.loadDrawable(topNews.get(0).topNewsImgURL, new CallbackImpl(imageView1)));
            textView1.setText(topNews.get(0).topNewsTitle);
            views.add(view1);

            View view2 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView2 = (ImageView) view2.findViewById(R.id.top_stories_img);
            imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView2 = (TextView) view2.findViewById(R.id.top_stories_title);
            imageView2.setImageDrawable(loader.loadDrawable(topNews.get(1).topNewsImgURL, new CallbackImpl(imageView2)));
            textView2.setText(topNews.get(1).topNewsTitle);
            views.add(view2);

            View view3 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView3 = (ImageView) view3.findViewById(R.id.top_stories_img);
            imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView3 = (TextView) view3.findViewById(R.id.top_stories_title);
            imageView3.setImageDrawable(loader.loadDrawable(topNews.get(2).topNewsImgURL, new CallbackImpl(imageView3)));
            textView3.setText(topNews.get(2).topNewsTitle);
            views.add(view3);

            View view4 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView4 = (ImageView) view4.findViewById(R.id.top_stories_img);
            imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView4 = (TextView) view4.findViewById(R.id.top_stories_title);
            imageView4.setImageDrawable(loader.loadDrawable(topNews.get(3).topNewsImgURL, new CallbackImpl(imageView4)));
            textView4.setText(topNews.get(3).topNewsTitle);
            views.add(view4);

            View view5 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView5 = (ImageView) view5.findViewById(R.id.top_stories_img);
            imageView5.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView5 = (TextView) view5.findViewById(R.id.top_stories_title);
            imageView5.setImageDrawable(loader.loadDrawable(topNews.get(4).topNewsImgURL, new CallbackImpl(imageView5)));
            textView5.setText(topNews.get(4).topNewsTitle);
            views.add(view5);
        }
        if(m == 1 ){
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.header_pager_item, null);
            ImageView imageView1 = (ImageView) view1.findViewById(R.id.top_stories_img);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView1 = (TextView) view1.findViewById(R.id.top_stories_title);
            imageView1.setImageDrawable(loader.loadDrawable(topNews.get(0).topNewsImgURL, new CallbackImpl(imageView1)));
            textView1.setText(topNews.get(0).topNewsTitle);
            views.add(view1);
        }
        pagerAdapter = new MyViewPagerAdapter(views,topNews,mContext);
        viewPager.setAdapter(pagerAdapter);
    }
}
