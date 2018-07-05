package com.example.wangtao.day7_jingdong.mvp.homeshou.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouUserBean;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouZhongUserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangtao on 2018/6/20.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyGangRecyclerAdapter extends RecyclerView.Adapter<MyGangRecyclerAdapter.MyViewHolder>{

    private  List<ShouZhongUserBean.DataBean> data1;

    public MyGangRecyclerAdapter(List<ShouZhongUserBean.DataBean> data1) {
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.shou_recyclers_gang, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String images = data1.get(position).getIcon();
        holder.textView.setText(data1.get(position).getName());
        ImageLoader.getInstance().displayImage(images,holder.imageView, MyApp.getOption());
        //holder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return data1 == null?0:data1.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.shou_recyclers_gang_image);
            textView =itemView.findViewById(R.id.shou_recyclers_gang_text);
        }
    }
}
