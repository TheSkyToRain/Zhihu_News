package com.example.administrator.zhihu_news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/17.
 */

public class OthersTips extends AppCompatActivity{
    TextView mTextView;
    ImageView mImageView;
    private AsyncImageLoader loader = new AsyncImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);

        mTextView = (TextView) findViewById(R.id.tips_);
        mImageView = (ImageView) findViewById(R.id.smile_img);
        mTextView.setText("你好，我是一只来自redrock的小菜鸟，联系方式：953853620@qq.com");
        mImageView.setImageDrawable(loader.loadDrawable("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2885481684,4239058697&fm=23&gp=0.jpg",new CallbackImpl(this.mImageView)));
    }
}
