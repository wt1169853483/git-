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
public abstract class BaseMainActtivity<P extends BasePresenter> extends AppCompatActivity {
       protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(probildId());
        presenter =probildPresenter();
        InitView();
        InitListener();
        initData();
    }

    protected abstract int probildId();

    protected abstract void initData();

    protected abstract void InitView();

    protected abstract P probildPresenter();

    protected abstract void InitListener();

  /*  @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }*/
}
