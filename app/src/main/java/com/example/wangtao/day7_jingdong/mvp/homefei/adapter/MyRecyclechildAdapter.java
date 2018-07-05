package com.example.wangtao.day7_jingdong.mvp.homefei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiRightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by wangtao on 2018/6/16.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyRecyclechildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FeiRightBean.DataBean.ListBean> list;

    public MyRecyclechildAdapter(Context context, List<FeiRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_recycle_child,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_name_child.setText(list.get(position).getName());
        holder1.draweeView.setImageURI(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView draweeView;
        private TextView tv_name_child;
        public ViewHolder(View itemView) {

            super(itemView);

            draweeView = itemView.findViewById(R.id.sdv);
            tv_name_child = itemView.findViewById(R.id.tv_name_child);
        }
    }
}
