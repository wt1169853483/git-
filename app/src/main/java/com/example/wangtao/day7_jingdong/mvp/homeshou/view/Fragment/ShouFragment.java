package com.example.wangtao.day7_jingdong.mvp.homeshou.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.app.MyApp;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.homeshou.adapter.MyGangRecyclerAdapter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.adapter.MyRecyclerAdapter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouUserBean;
import com.example.wangtao.day7_jingdong.mvp.homeshou.model.bean.ShouZhongUserBean;
import com.example.wangtao.day7_jingdong.mvp.homeshou.presenter.ShouFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.homewo.view.activity.MyActivity;
import com.example.wangtao.day7_jingdong.mvp.homewo.view.fragment.WoFlowlayout;
import com.example.wangtao.day7_jingdong.mvp.sousuo.view.activity.SouSuoActivity;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.Activity.WebViewActivity;
import com.example.wangtao.day7_jingdong.mvp.homeshou.adapter.GridBaseAdapter;
import com.example.wangtao.day7_jingdong.mvp.homeshou.adapter.MyPageAdapter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 2018/6/11.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class ShouFragment extends BaseActivity<ShouFragmentPresenter> implements View.OnClickListener, IShouFragment {
    private MyHandle myHandle = new MyHandle();
    private ViewPager viewPager;
    private static final String TAG = "ShouFragment";
    private ImageButton imageButton;
    private ImageButton imageButton1;
    private List<ImageView> ImageList = new ArrayList<>();
    private List<ImageView> poins = new ArrayList<>();
    private List<ShouUserBean.TuijianBean.ListBean> imageGrid = new ArrayList<>();
    private LinearLayout linearLayout;
    private MyPageAdapter adapter;
    private List<ShouUserBean.DataBean> data;
    private GridView gridView;
    private List<ShouUserBean.TuijianBean.ListBean> list;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewganduo;
    private MarqueeView marqueeView;
    private EditText editText;
    private PullToRefreshScrollView pullToRefreshScrollView;
    private WoFlowlayout linearLayoutSouSuo;

    @Override
    protected int probildId() {
        return R.layout.fragment_shou_item;
    }

    //获取P
    @Override
    protected ShouFragmentPresenter probildPresenter() {
        return new ShouFragmentPresenter(this);
    }

    @Override
    protected void InitView(View view) {
        //获取组件

        pullToRefreshScrollView = view.findViewById(R.id.pull_to_refresh_listview);
        editText = view.findViewById(R.id.shou_editText);
        viewPager = view.findViewById(R.id.shou_viewPager);
        imageButton = view.findViewById(R.id.shou_imageBtn);
        imageButton1 = view.findViewById(R.id.shou_imageBtnOne);
        linearLayout = view.findViewById(R.id.shou_layout);
        gridView = view.findViewById(R.id.shou_gridView);
        recyclerView = view.findViewById(R.id.shou_recycler);
        recyclerViewganduo = view.findViewById(R.id.shou_recycler_gangduo);
        marqueeView = view.findViewById(R.id.marqueeView);

        //presenter.shouPrement(Url_Http.shou_URl);
        List<String> info = new ArrayList<>();
        info.add("阿斯加德哈就开始就看");
        info.add("按时大大撒");
        info.add("阿斯顿assassin");
        info.add("按时大大萨达!");
        // 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    @Override
    protected void InitListener() {
        imageButton1.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        editText.setOnClickListener(this);
    }

    //成功
    @Override
    public void getDataSuccess(final String json) {
        //获取网络数据
        //设置轮播图
        initLunBo(json);
        //设置多条目加载
        InitListView(json);
    }

    //失败
    @Override
    public void getDataError(String error) {
        Log.d(TAG, "getDataError: 错误" + error);
    }

    @Override
    public void getSuccessOne(String json) {

        Log.d(TAG, "getSuccessOne: GetSuccessOne+++++++++++++++++"+json);
        Gson gson =new Gson();
        ShouZhongUserBean shouZhongUserBean = gson.fromJson(json, ShouZhongUserBean.class);
        List<ShouZhongUserBean.DataBean> data1 = shouZhongUserBean.getData();

        MyGangRecyclerAdapter myGangRecyclerAdapter=new MyGangRecyclerAdapter(data1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewganduo.setLayoutManager(gridLayoutManager);
        recyclerViewganduo.setAdapter(myGangRecyclerAdapter);
             //InitRecyceler(json);
    }
     //商品recyclerView应用
   // private void InitRecyceler(String json) {

   // }

    @Override
    public void getErrorOne(String json) {

    }

    private void initLunBo(String json) {
        final Gson gson = new Gson();
        final ShouUserBean shouUserBean = gson.fromJson(json, ShouUserBean.class);
        List<ShouUserBean.DataBean> data = shouUserBean.getData();
        //无限轮播
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            Log.d(TAG, "getDataSuccess: " + icon);
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(icon, imageView, MyApp.getOption());
            ImageList.add(imageView);
            ImageView poinImage = new ImageView(getContext());
            poinImage.setPadding(10, 0, 10, 0);
            poinImage.setImageResource(R.drawable.shousesctro);
            poins.add(poinImage);
            linearLayout.addView(poinImage);
        }

        //第一个默认选中
        poins.get(0).setSelected(true);
        Log.d(TAG, "getDataSuccess: ++++++++++++++++++++++=" + ImageList.size());
        //配置适配器
        adapter = new MyPageAdapter(ImageList);
        viewPager.setAdapter(adapter);

        //监听轮播图
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                position = position % ImageList.size();

                for (int i = 0; i < ImageList.size(); i++) {
                    if (i == position) {
                        poins.get(i).setSelected(true);
                    } else {
                        poins.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //开启线程

        myHandle.sendEmptyMessageDelayed(0, 1000);
    }

    //设置多条目加载
    private void InitListView(String json) {
        Gson gson1 = new Gson();
        ShouUserBean shouUserBean1 = gson1.fromJson(json, ShouUserBean.class);
        ShouUserBean.MiaoshaBean miaosha = shouUserBean1.getMiaosha();
        List<ShouUserBean.MiaoshaBean.ListBeanX> list = miaosha.getList();
        // 商品秒杀   配置适配器 Recycler
        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(list);
        //设置布局为线型ListView
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);


        ShouUserBean.TuijianBean tuijian = shouUserBean1.getTuijian();
        this.list = tuijian.getList();
                /*for (int i = 0; i <list.size() ; i++) {
                    String images = list.get(i).getImages();
                    String[] split = images.split("\\|");
                    ImageView imageView =new ImageView(getActivity());
                    ImageLoader.getInstance().displayImage(split[0],imageView,MyApp.getOption());
                    imageGrid.add(imageView);
                }*/

        //配置适配器
        GridBaseAdapter gridAdapter = new GridBaseAdapter(this.list, getActivity());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (MyActivity.i == 1){
                   String detailUrl = ShouFragment.this.list.get(position).getDetailUrl();
                   Log.d(TAG, "onItemClick: ++++++++++++++" + detailUrl);
                   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                   intent.putExtra("url", detailUrl);
                   startActivity(intent);
               }else{
                  Toast.makeText(getContext()," 你需要选登陆，才能加入观看",Toast.LENGTH_LONG).show();
               }

            }
        });
    }

    //初始化界面
    //懒加载
    @Override
    protected void InitData() {
        presenter.shouPrement(Url_Http.shou_URl);
        
      // presenter.shouGangduo();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void NoitData() {
        if (linearLayout != null && linearLayout.getChildCount() != 0) {
            linearLayout.removeAllViews();
            ImageList.clear();
            poins.clear();
            myHandle.removeCallbacksAndMessages(null);
        }
    }

    /*  //设置可见是调用
      @Override
      public void setUserVisibleHint(boolean isVisibleToUser) {
          super.setUserVisibleHint(isVisibleToUser);
          if (isVisibleToUser) {
              presenter.shouPrement(Url_Http.shou_URl);
              // adapter.notifyDataSetChanged();
          } else {
              if (linearLayout != null && linearLayout.getChildCount() != 0) {
                  linearLayout.removeAllViews();
                  ImageList.clear();
                  poins.clear();

                  myHandle.removeCallbacksAndMessages(null);
              }
          }
      }*/
    //点击事件
    @Override
    public void onClick(View v) {
        //扫二维码
        switch (v.getId()) {
            case R.id.shou_imageBtn:
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.shou_imageBtnOne:

                break;
            case R.id.shou_editText:
                Intent intent=new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
        }

    }

    class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            myHandle.sendEmptyMessageDelayed(0, 1000);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        myHandle.removeCallbacksAndMessages(null);
    }

    //跳转回传
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
        }
    }
}
