package com.example.wangtao.day7_jingdong.mvp.homegou.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.mvp.homegou.model.bean.GouUtilBean;
import com.example.wangtao.day7_jingdong.mvp.homegou.view.fragmnets.MyAddStuds;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangtao on 2018/6/27.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class gouExpandAdapter extends BaseExpandableListAdapter {
    private List<GouUtilBean.DataBean> data;
    private Context context;
    private boolean allProductsSelected;

    public gouExpandAdapter(List<GouUtilBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList().size() == 0 ? 0 : data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup  viewHolderGroup=null;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.fragment_gou_item_group, null);
            viewHolderGroup=new ViewHolderGroup(convertView);
            convertView.setTag(viewHolderGroup);
        }else{
                viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }
        viewHolderGroup.gouGroupTextSeller.setText(data.get(groupPosition).getSellerName());

        boolean onParamGourpSelledSelected = isOnParamGourpSelledSelected(groupPosition);
        viewHolderGroup.gouGroupCheckBox.setChecked(onParamGourpSelledSelected);
        viewHolderGroup.gouGroupCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (shopingListener != null){
                    shopingListener.getListGroupParamChanged(groupPosition);
                  }
            }
        });


        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GouUtilBean.DataBean.ListBean listBean = data.get(groupPosition).getList().get(childPosition);
        ViewHolderChild viewHolderChild=null;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.fragment_gou_item_child, null);
            viewHolderChild=new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        }else{
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        viewHolderChild.gouItemChildTitleTv.setText(listBean.getTitle());
        viewHolderChild.gouItemChildPriceTv.setText(listBean.getPrice()+"");
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0],viewHolderChild.imageView, MyApp.getOption());
        viewHolderChild.gouItemChildCheckBox.setChecked(listBean.getSelected() == 1);
        viewHolderChild.gouItemChildCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (shopingListener != null){
                        shopingListener.getListchildParamChanged(groupPosition,childPosition);
                  }
            }
        });

        //设置数量
        viewHolderChild.grouItemChildMyAddStuds.setNumber(listBean.getNum());
        viewHolderChild.grouItemChildMyAddStuds.setonNumberBachListener(new MyAddStuds.OnNumberBachListener() {
            @Override
            public void getNumberSeuccess(int num) {
                   if (shopingListener != null){
                        shopingListener.getListchildNumberChanged(groupPosition,childPosition,num);
                   }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean isOnParamGourpSelledSelected(int groupPosition) {
        GouUtilBean.DataBean dataBean = data.get(groupPosition);
        List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
        for (GouUtilBean.DataBean.ListBean listBean:list) {
              if (listBean.getSelected() ==0){
                     return false;
              }
        }
        return true;
    }

    public void ChangedParamGrourpSelledSelected(int groupPoition, boolean b) {
        GouUtilBean.DataBean dataBean = data.get(groupPoition);
        List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            GouUtilBean.DataBean.ListBean listBean = list.get(i);
            listBean.setSelected(b ?1:0);
        }
    }

    public void ChangedParamChildSelected(int groupPoition, int childPoition) {
        GouUtilBean.DataBean dataBean = data.get(groupPoition);
        List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
        GouUtilBean.DataBean.ListBean listBean = list.get(childPoition);
        listBean.setSelected(listBean.getSelected()==0 ?1:0);
    }

    public boolean isAllProductsSelected() {
        for (int i = 0; i <data.size() ; i++) {
            List<GouUtilBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                GouUtilBean.DataBean.ListBean listBean = list.get(j);
                if (listBean.getSelected() == 0){
                          return false;
                   }
            }
        }
        return true;
    }

    public void changeAllProductsStatus(boolean b) {
        for (int i = 0; i <data.size() ; i++) {
            GouUtilBean.DataBean dataBean = data.get(i);
            List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j <list.size() ; j++) {
                GouUtilBean.DataBean.ListBean listBean = list.get(j);
                listBean.setSelected(b ?1:0);
            }
        }
    }

    public void changedParamChildNumber(int groupPoition, int childPoition, int number) {
        GouUtilBean.DataBean dataBean = data.get(groupPoition);
        List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
        GouUtilBean.DataBean.ListBean listBean = list.get(childPoition);
        listBean.setNum(number);
    }

    public double changedAllPrices() {
        double tatoSumPrice=0;
        for (int i = 0; i <data.size() ; i++) {
            GouUtilBean.DataBean dataBean = data.get(i);
            List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j <list.size() ; j++) {
                GouUtilBean.DataBean.ListBean listBean = list.get(j);
                if (listBean.getSelected() ==1){
                    int num = listBean.getNum();
                    double price = listBean.getPrice();
                    tatoSumPrice+=num*price;
                }
            }
        }
        return tatoSumPrice;
    }

    public int changedAllNumber() {
           int tatoSumNub=0;
        for (int i = 0; i <data.size() ; i++) {
            GouUtilBean.DataBean dataBean = data.get(i);
            List<GouUtilBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j <list.size() ; j++) {
                GouUtilBean.DataBean.ListBean listBean = list.get(j);
                if (listBean.getSelected() ==1){
                    int num = listBean.getNum();
                    tatoSumNub +=num;
                }
            }
        }
        return tatoSumNub;
    }


    static class ViewHolderGroup {
        @BindView(R.id.gou_group_checkBox)
        CheckBox gouGroupCheckBox;
        @BindView(R.id.gou_group_textSeller)
        TextView gouGroupTextSeller;

        ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

   static class ViewHolderChild {
        @BindView(R.id.gou_item_child_checkBox)
        CheckBox gouItemChildCheckBox;
        @BindView(R.id.gou_item_child_titleTv)
        TextView gouItemChildTitleTv;
        @BindView(R.id.gou_item_child_PriceTv)
        TextView gouItemChildPriceTv;
        @BindView(R.id.child_lay)
        LinearLayout childLay;
        @BindView(R.id.grou_item_child_myAddStuds)
        MyAddStuds grouItemChildMyAddStuds;
       @BindView(R.id.gou_item_child_imageVIew)
       ImageView imageView;
       ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
   //接口回调
    private OnShopingListener shopingListener;
    public void setOnShopingListener(OnShopingListener onShopingListener){
            this.shopingListener =onShopingListener;
    }
    public interface OnShopingListener{
         void  getListGroupParamChanged(int groupPoition);
         void  getListchildParamChanged(int groupPoition,int childPoition);
         void  getListchildNumberChanged(int groupPoition,int childPoition,int number);
    }
}
