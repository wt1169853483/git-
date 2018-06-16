package com.example.wangtao.day7_jingdong.mvp.homeshou.adapter;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyPageAdapter extends PagerAdapter{
    private List<ImageView> list;
    private static final String TAG = "MyPageAdapter";
    public MyPageAdapter(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public void registerDataSetObserver(@NonNull DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        if (list != null){
            return Integer.MAX_VALUE;
        }
       return  0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView =list.get(position%list.size());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
          container.removeView((View) object);
    }
}
