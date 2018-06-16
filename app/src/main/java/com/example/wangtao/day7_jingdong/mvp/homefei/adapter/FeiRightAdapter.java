package com.example.wangtao.day7_jingdong.mvp.homefei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiLeftBean;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiRightBean;

import java.util.List;

/**
 * Created by wangtao on 2018/6/16.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class FeiRightAdapter extends BaseAdapter{
    private List<FeiRightBean.DataBean.ListBean> list;
    private Context context;

    public FeiRightAdapter(List<FeiRightBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder viewHolder=null;
        if (convertView == null){
            viewHolder=new  ViewHolder();
            //获取子布局
            convertView =View.inflate(context, R.layout.fei_left_text,null);
            viewHolder.textView =convertView.findViewById(R.id.fei_left_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());
        return  convertView;
    }
    class ViewHolder{
        TextView textView;

    }
}
