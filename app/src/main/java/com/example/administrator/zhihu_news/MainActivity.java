package com.example.administrator.zhihu_news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String urlPath = "http://news-at.zhihu.com/api/4/news/latest";
    RecyclerView recyclerView;
    NewsAdapter adapter;
    ViewPager viewPager;
    public List<JsonBean> news;
    public List<TopJsonBean> topNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header,null);
        viewPager = (ViewPager) headerView.findViewById(R.id.viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        AsyncNetwork.get(urlPath, new AsyncNetwork.Callback() {
            @Override
            public void onResponse(String response) {
                news = GetJsonData.getDataFromJsonObject(response);
                topNews = GetJsonData.getDataFromJsonObject1(response);
                adapter = new NewsAdapter(MainActivity.this,news,topNews,0);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);
                setHeaderView(recyclerView);
                setFooterView(recyclerView);
            }
        });
    }
    public void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header,view,false);
        adapter.setHeaderView(header);
    }
    public void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer,view,false);
        adapter.setFooterView(footer);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this,"对不起，暂无此功能。",Toast.LENGTH_LONG).show();
            return true;
        }else if(id == R.id.action_night){
            Toast.makeText(this,"请将亮度调到最低，即开启夜间模式",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.first) {
            urlPath = "http://news-at.zhihu.com/api/4/news/latest";
            AsyncNetwork.get(urlPath, new AsyncNetwork.Callback() {
                @Override
                public void onResponse(String response) {
                    news = GetJsonData.getDataFromJsonObject(response);
                    topNews = GetJsonData.getDataFromJsonObject1(response);
                    //Log.d("topNews",topNews.get(0).topNewsImgURL);
                    adapter = new NewsAdapter(MainActivity.this,news,topNews,0);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);
                    setHeaderView(recyclerView);
                    setFooterView(recyclerView);
                }
            });
        } else if (id == R.id.game) {
            urlPath = "http://news-at.zhihu.com/api/4/theme/2";
            setUpUI(urlPath);
        } else if (id == R.id.notBoring) {
            urlPath = "http://news-at.zhihu.com/api/4/themes/11";
            setUpUI(urlPath);
        } else if (id == R.id.music) {
            urlPath = "http://news-at.zhihu.com/api/4/theme/7";
            setUpUI(urlPath);
        } else if (id == R.id.sports) {
            urlPath = "http://news-at.zhihu.com/api/4/theme/8";
            setUpUI(urlPath);
        } else if (id == R.id.share) {
            Toast.makeText(this,"请联系开发者，谢谢。",Toast.LENGTH_LONG).show();
        }else if(id == R.id.about){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,OthersTips.class);
            startActivity(intent);
        }else if(id == R.id.money){
            urlPath = "http://news-at.zhihu.com/api/4/theme/6";
            setUpUI(urlPath);
        }else if(id == R.id.suggest){
            urlPath = "http://news-at.zhihu.com/api/4/theme/12";
            setUpUI(urlPath);
        }else if(id == R.id.comic){
            urlPath = "http://news-at.zhihu.com/api/4/theme/9";
            setUpUI(urlPath);
        }else if(id == R.id.internet){
            urlPath = "http://news-at.zhihu.com/api/4/theme/10";
            setUpUI(urlPath);
        }else if(id == R.id.psychology){
            urlPath = "http://news-at.zhihu.com/api/4/theme/13";
            setUpUI(urlPath);
        }else if(id == R.id.film){
            urlPath = "http://news-at.zhihu.com/api/4/theme/3";
            setUpUI(urlPath);
        }else if(id == R.id.design){
            urlPath = "http://news-at.zhihu.com/api/4/theme/4";
            setUpUI(urlPath);
        }else if(id == R.id.company){
            urlPath = "http://news-at.zhihu.com/api/4/theme/5";
            setUpUI(urlPath);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setUpUI(String urlPath){
        AsyncNetwork.get(urlPath, new AsyncNetwork.Callback() {
            @Override
            public void onResponse(String response) {
                news = GetJsonData.getDataFromJsonObject2(response);
                topNews = GetJsonData.getDataFromJsonObject3(response);
                //Log.d("topNews",topNews.get(0).topNewsImgURL);
                adapter = new NewsAdapter(MainActivity.this,news,topNews,1);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);
                setHeaderView(recyclerView);
                setFooterView(recyclerView);
            }
        });
    }
}
