package com.example.administrator.zhihu_news;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CallbackImpl implements AsyncImageLoader.ImageCallback{
    private ImageView imageView;
    public CallbackImpl(ImageView imageView){
        super();
        this.imageView = imageView;
    }
    @Override
    public void imageLoaded(Drawable imageDrawable) {
        imageView.setImageDrawable(imageDrawable);
    }
}
