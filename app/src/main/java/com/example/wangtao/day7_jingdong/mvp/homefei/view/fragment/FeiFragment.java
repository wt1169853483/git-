package com.example.wangtao.day7_jingdong.mvp.homefei.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wangtao.day7_jingdong.R;
import com.example.wangtao.day7_jingdong.base.BaseActivity;
import com.example.wangtao.day7_jingdong.mvp.homefei.adapter.FeiLeftAdapter;
import com.example.wangtao.day7_jingdong.mvp.homefei.model.bean.FeiLeftBean;
import com.example.wangtao.day7_jingdong.mvp.homefei.presenter.FeiFragmentPresenter;
import com.example.wangtao.day7_jingdong.mvp.mainhome.view.iview.IShouFragment;
import com.example.wangtao.day7_jingdong.utils.https.config.Url_Http;
import com.google.gson.Gson;

import java.util.List;


/**
 * Created by wangtao on 2018/6/11.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class FeiFragment extends BaseActivity<FeiFragmentPresenter> implements View.OnClickListener,IShouFragment{
    private static final String TAG = "FeiFragment";
    private ListView listView;
    private List<FeiLeftBean.DataBean> data;

    @Override
    protected int probildId() {
        return R.layout.fragment_fei_item;
    }
    @Override
    protected FeiFragmentPresenter probildPresenter() {
        return new FeiFragmentPresenter(this);
    }
    @Override
    protected void InitView(View view) {
        //获取组件
        listView = view.findViewById(R.id.fei_listview);
        // presenter.shouPrement(Url_Http.fei_LeftURl);
       /* FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment= new RightFeiFragment();
        transaction.replace(R.id.fei_fragment_container,fragment);
        Bundle build =new Bundle();
        build.putString("urls","1234654");
        fragment.setArguments(build);
        transaction.commit();*/
    }

    //监听事件
    @Override
    protected void InitListener() {
    }

    @Override
    protected void InitData() {
         presenter.shouPrement(Url_Http.fei_LeftURl);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void NoitData() {

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(String json) {
        initList(json);
    }

    private void initList(String json) {
        Gson gson =new Gson();
        FeiLeftBean feiLeftBean = gson.fromJson(json, FeiLeftBean.class);
        data = feiLeftBean.getData();
        FeiLeftAdapter feiLeftAdapter=new FeiLeftAdapter(data,getActivity());
        listView.setAdapter(feiLeftAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FeiLeftBean.DataBean dataBean = data.get(position);
                String name = dataBean.getName();
               // Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment= new RightFeiFragment();
                transaction.replace(R.id.fei_fragment_container,fragment);
                Bundle build =new Bundle();
                int cid = dataBean.getCid();
                Log.d(TAG, "onItemClick:cod: ++++++++++++++++"+cid);
                build.putInt("urls",cid);
                fragment.setArguments(build);
                transaction.commit();
            }
        });
    }

    @Override
    public void getDataError(String error) {

    }



}
