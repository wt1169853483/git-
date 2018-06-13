package com.example.wangtao.day7_jingdong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wangtao on 2018/6/13.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public abstract class BaseActivity <P extends BasePresenter> extends Fragment {
       protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
        InitListener();
        presenter=probildPresenter();
        InitData();
    }

    protected abstract void InitData();

    protected abstract P probildPresenter();

    protected abstract void InitListener();

    protected  void InitView(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }
}
