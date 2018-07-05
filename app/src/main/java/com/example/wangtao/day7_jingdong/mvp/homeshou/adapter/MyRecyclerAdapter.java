package com.example.wangtao.day7_jingdong.mvp.homeshou.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouUserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangtao on 2018/6/20.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private List<ShouUserBean.MiaoshaBean.ListBeanX> list;

    public MyRecyclerAdapter(List<ShouUserBean.MiaoshaBean.ListBeanX> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.shou_recyclers, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.imageView, MyApp.getOption());
        //holder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.shou_recyclers_image);
        }
    }
}
