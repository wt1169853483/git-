package com.example.wangtao.day7_jingdong.mvp.sousuo.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.mvp.homeshou.adapter.MyRecyclerAdapter;
import com.example.wangtao.day7_jingdong.mvp.sousuo.model.bean.SousuoBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangtao on 2018/6/27.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class SousuoBaseRecyclerAdapter extends RecyclerView.Adapter<SousuoBaseRecyclerAdapter.MyViewHolder> {
    List<SousuoBean.DataBean> data;
    private static final String TAG = "SousuoBa++++++adapter";
    public SousuoBaseRecyclerAdapter(List<SousuoBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sousuo_recycler_item, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String images = data.get(position).getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.imageView, MyApp.getOption());
        Log.d(TAG, "getItemCount: "+data.get(position).getTitle());
        holder.TitleTV.setText(data.get(position).getTitle());
        holder.PriceTv.setText("￥"+data.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:+++++++++++++++++++ "+data.size());
        return data.size() ==0 ? 0:data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       private TextView TitleTV,PriceTv;
       public MyViewHolder(View itemView) {
           super(itemView);
           imageView =itemView.findViewById(R.id.sousuo_recycle_imageview);
           TitleTV=itemView.findViewById(R.id.sousuo_recycle_textTitle);
           PriceTv=itemView.findViewById(R.id.sousuo_recycle_textPrice);
       }
   }
}
