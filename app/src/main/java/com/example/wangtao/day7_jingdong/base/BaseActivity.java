package com.example.wangtao.day7_jingdong.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public abstract class BaseActivity <P extends BasePresenter> extends Fragment {
       protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(probildId(),container,false);
        InitView(view);
        InitListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=probildPresenter();
        initViews();
    }

    protected abstract void initViews();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            presenter=probildPresenter();
            InitData();
        }else{
             NoitData();
         }
    }

    protected abstract void NoitData();

    protected abstract void InitView(View view);

    protected abstract int  probildId();

    protected abstract void InitData();

    protected abstract P probildPresenter();

    protected abstract void InitListener();



  /*  @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }*/
}
