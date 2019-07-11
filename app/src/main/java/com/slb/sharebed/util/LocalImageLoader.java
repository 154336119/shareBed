package com.slb.sharebed.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luck.picture.lib.tools.ScreenUtils;
import com.lzy.imagepicker.loader.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LocalImageLoader extends com.youth.banner.loader.ImageLoader implements ImageLoader{
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(path)   //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(com.slb.frame.R.mipmap.default_image)           //设置错误图片
                .placeholder(com.slb.frame.R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(path)   //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(com.slb.frame.R.mipmap.default_image)           //设置错误图片
                .placeholder(com.slb.frame.R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }


    @Override
    public ImageView createImageView(Context context) {
        RoundedImageView roundedImg = new RoundedImageView(context, null);
        roundedImg.setCornerRadius(ScreenUtils.dip2px(context, 10));
        return roundedImg;
    }
}
