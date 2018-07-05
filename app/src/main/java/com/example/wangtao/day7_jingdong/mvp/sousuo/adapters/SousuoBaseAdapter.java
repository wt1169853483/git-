package com.example.wangtao.day7_jingdong.mvp.sousuo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouUserBean;
import com.example.wangtao.day7_jingdong.mvp.sousuo.model.bean.SousuoBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangtao on 2018/6/15.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class SousuoBaseAdapter extends BaseAdapter{
    private static final String TAG = "GridBaseAdapter";
    private  List<SousuoBean.DataBean> list;
    private Context context;

    public SousuoBaseAdapter( List<SousuoBean.DataBean>  list, Context context) {
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
            viewHolder=new ViewHolder();
            //获取子布局
            convertView =View.inflate(context,R.layout.shou_item,null);
            viewHolder.imageView =convertView.findViewById(R.id.shou_item_image);
            viewHolder.textView =convertView.findViewById(R.id.shou_item_text);
            viewHolder.textView1 =convertView.findViewById(R.id.shou_item_textPrice);
            convertView.setTag(viewHolder);
        }else{
              viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position).getTitle());
        viewHolder.textView1.setText("￥"+list.get(position).getPrice()+"0");
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        //Log.d(TAG, "getView:++++++++++++++++++++ "+split[0]);
        viewHolder.imageView.setPadding(0,0,5,10);
        ImageLoader.getInstance().displayImage(split[0],viewHolder.imageView, MyApp.getOption());
        return convertView;
    }
    class ViewHolder{
          ImageView imageView;
          TextView textView;
          TextView textView1;
    }
}
