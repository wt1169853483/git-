package com.example.wangtao.day7_jingdong.mvp.mainhome.view.fragment;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.mainhome.model.bean.ShouUserBean;
import com.example.wangtao.day7_jingdong.mvp.mainhome.presenter.ShouFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.adapter.MyPageAdapter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 2018/6/11.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class ShouFragment extends BaseActivity<ShouFragmentPresenter> implements View.OnClickListener,IShouFragment{
    private MyHandle myHandle=new MyHandle();
    private ViewPager viewPager;
    private View view;
    private static final String TAG = "ShouFragment";
    private ImageButton imageButton;
    private ImageButton imageButton1;
    private List<ImageView> ImageList=new ArrayList<>();
    private List<ImageView> poins=new ArrayList<>();
    private LinearLayout linearLayout;
    private MyPageAdapter adapter;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shou_item,container,false);
        initview();
        return view;
    }
     //获取P
    @Override
    protected ShouFragmentPresenter probildPresenter() {
        return new ShouFragmentPresenter(this);
    }
    public void initview(){
        //获取组件
        viewPager = view.findViewById(R.id.shou_viewPager);
        imageButton = view.findViewById(R.id.shou_imageBtn);
        imageButton1 = view.findViewById(R.id.shou_imageBtnOne);
        linearLayout = view.findViewById(R.id.shou_layout);
         imageButton1.setOnClickListener(this);
         imageButton.setOnClickListener(this);
        presenter.shouPrement(Url_Http.shou_URl);
    }
    @Override
    protected void InitListener() {

    }

    //初始化界面
    @Override
    protected void InitData() {
     //获取网络数据

    }
    @Override
    public void getDataSuccess(List<ShouUserBean.DataBean> data) {
             //无限轮播
        for (int i = 0; i <data.size() ; i++) {
            String icon = data.get(i).getIcon();
            Log.d(TAG, "getDataSuccess: "+icon);
            ImageView imageView=new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(icon,imageView, MyApp.getOption());
            ImageList.add(imageView);
            ImageView poinImage=new ImageView(getActivity());
            poinImage.setPadding(10,0,10,0);
            poinImage.setImageResource(R.drawable.shousesctro);
            poins.add(poinImage);
            linearLayout.addView(poinImage);
        }

        //第一个默认选中
        poins.get(0).setSelected(true);
        Log.d(TAG, "getDataSuccess: ++++++++++++++++++++++="+ImageList.size());
        adapter = new MyPageAdapter(ImageList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 position =position%ImageList.size();
                for (int i = 0; i <ImageList.size() ; i++) {
                        if(i == position){
                             poins.get(i).setSelected(true);
                        }else{
                             poins.get(i).setSelected(false);
                        }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //开启线程
        adapter.notifyDataSetChanged();
        myHandle.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    public void getDataError(String error) {

    }

    //点击事件
    @Override
    public void onClick(View v) {

    }
    class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
             myHandle.sendEmptyMessageDelayed(0,1000);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
               if (linearLayout != null && linearLayout.getChildCount() !=0){
                   linearLayout.removeAllViews();
                   ImageList.clear();
                   poins.clear();
                   myHandle.removeCallbacksAndMessages(null);
               }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myHandle.removeCallbacksAndMessages(null);
    }
}
