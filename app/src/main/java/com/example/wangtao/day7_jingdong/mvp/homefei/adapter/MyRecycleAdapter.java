package com.example.wangtao.day7_jingdong.mvp.homefei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiRightBean;

import java.util.List;

/**
 * Created by wangtao on 2018/6/16.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<FeiRightBean.DataBean> lists;

    public MyRecycleAdapter(Context context, List<FeiRightBean.DataBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.fei_right_text,null);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_recycle_title.setText(lists.get(position).getName());
        List<FeiRightBean.DataBean.ListBean> list_child = lists.get(position).getList();
        MyRecyclechildAdapter adapter_child = new MyRecyclechildAdapter(context,list_child);
        holder1.recyclerView.setAdapter(adapter_child);
        holder1.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        Log.d("data",lists.toString()+"");
//        holder1.recyclerView.setAdapter();


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private TextView tv_recycle_title;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycle);
            tv_recycle_title = itemView.findViewById(R.id.tv_recycle_titles);

        }
    }
}
